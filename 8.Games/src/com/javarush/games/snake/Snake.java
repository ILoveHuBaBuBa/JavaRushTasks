package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private Direction direction = Direction.LEFT;

    private List<GameObject> snakeParts = new ArrayList<>();

    public boolean isAlive = true;

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        Color color = isAlive ? Color.BLACK : Color.RED;
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            String string = i == 0 ? HEAD_SIGN : BODY_SIGN;
            game.setCellValueEx(part.x, part.y, Color.NONE, string, color, 75);

        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void move() {

    }
}
