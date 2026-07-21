import eventlet
eventlet.monkey_patch()

import os
import subprocess
from flask import Flask, render_template, request
from flask_socketio import SocketIO, emit

app = Flask(__name__)
app.config['SECRET_KEY'] = os.environ.get('SESSION_SECRET', 'campo-minado-secret')
socketio = SocketIO(app, cors_allowed_origins='*', async_mode='eventlet')

JAVA_CMD = [
    'java',
    '-cp', 'CampoMinado/target/classes',
    '-Dfile.encoding=UTF-8',
    '-Dstdout.encoding=UTF-8',
    'org.yourcompany.yourproject.CampoMinado'
]

processes = {}


def read_output(sid, proc):
    """Stream Java stdout to the browser, one chunk at a time."""
    try:
        while True:
            chunk = proc.stdout.read(64)
            if not chunk:
                break
            text = chunk.decode('utf-8', errors='replace')
            socketio.emit('output', {'data': text}, to=sid)
            eventlet.sleep(0)  # yield to event loop
    except Exception as e:
        socketio.emit('output', {'data': f'\r\n[Erro ao ler saída: {e}]\r\n'}, to=sid)
    finally:
        socketio.emit(
            'output',
            {'data': '\r\n[Processo encerrado. Recarregue a página para jogar novamente.]\r\n'},
            to=sid
        )


@app.route('/')
def index():
    return render_template('index.html')


@socketio.on('connect')
def on_connect():
    sid = request.sid
    try:
        proc = subprocess.Popen(
            JAVA_CMD,
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,
            bufsize=0,
        )
        processes[sid] = proc
        socketio.start_background_task(read_output, sid, proc)
    except Exception as e:
        emit('output', {'data': f'[Erro ao iniciar o jogo: {e}]\r\n'})


@socketio.on('input')
def on_input(data):
    sid = request.sid
    proc = processes.get(sid)
    if proc and proc.poll() is None:
        text = data.get('data', '')
        try:
            proc.stdin.write(text.encode('utf-8'))
            proc.stdin.flush()
        except Exception:
            pass


@socketio.on('disconnect')
def on_disconnect():
    sid = request.sid
    proc = processes.pop(sid, None)
    if proc:
        try:
            proc.terminate()
        except Exception:
            pass


if __name__ == '__main__':
    socketio.run(app, host='0.0.0.0', port=5000)
