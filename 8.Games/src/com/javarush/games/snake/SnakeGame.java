package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private int score;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if(!apple.isAlive) {
            setScore(score += 5);
            setTurnTimer(turnDelay -= 10);
            createNewApple();
        }
        if(!snake.isAlive) {
            gameOver();
        }
        if(snake.getLength() > GOAL){
            win();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if(key.equals(Key.LEFT)) {
            snake.setDirection(Direction.LEFT);
        } else if(key.equals(Key.RIGHT)) {
            snake.setDirection(Direction.RIGHT);
        } else if(key.equals(Key.UP)) {
            snake.setDirection(Direction.UP);
        } else if(key.equals(Key.DOWN)) {
            snake.setDirection(Direction.DOWN);
        } else if(key.equals((Key.SPACE)) && isGameStopped) {
            createGame();
        }
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }
    private void createGame() {
        score = 0;
        setScore(score);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        isGameStopped = false;
        drawScene();
    }
    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Game Over", Color.RED, 75);
    }
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "You Win", Color.GREEN, 75);
    }
    private void createNewApple() {
        while(true){
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
            if(!snake.checkCollision(apple)) {
                return;
            }
        }
    }
}

//--module-path "D:\MyProject\JavaRushTasks\lib\javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml