# Campo Minado (Minesweeper)

A Java Minesweeper game playable in the browser via a web terminal.

## Stack

- **Game logic**: Java 11, Maven (`CampoMinado/`)
- **Web server**: Python 3, Flask + Flask-SocketIO (eventlet)
- **Frontend**: xterm.js terminal emulator in the browser

## How to run

The workflow `Start Web Terminal` starts the server:

```
python3 server.py
```

This serves the web terminal at port 5000. Open the preview pane to play.

## How to play

1. Open the preview pane (or visit the dev URL).
2. Wait for the Java process to start — the board will appear.
3. Choose an action by typing its number and pressing Enter:
   - `1` — Open a cell (then enter coordinates as `x,y`, e.g. `3,5`)
   - `2` — Flag a cell (same coordinate format)
   - `3` — Reset the board
   - `4` — Exit

## Project structure

```
CampoMinado/
  pom.xml                          Maven build file (Java 11)
  src/main/java/...
    CampoMinado.java               Entry point
    controller/CampoController.java
    model/Campo.java               Cell model
    model/Tabuleiro.java           Board model
    view/Tela.java                 Console UI
  src/main/resources/static/
    index.html                     Standalone browser version (no server needed)
  target/                          Compiled classes (Maven output)
server.py                          Flask-SocketIO web terminal server
templates/index.html               Browser terminal UI (xterm.js)
```

## User preferences

- Wants the console game accessible online via a browser-based terminal.
