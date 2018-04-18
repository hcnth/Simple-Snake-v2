package com.pttx.snakeGame;

import com.pttx.game_engine.AbstractGame;
import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.util.ArrayList;

public class Game implements AbstractGame {
    private int gridNumber = 16;
    private Snake snake = new Snake(gridNumber);
    private ArrayList<ItemComponent> items = new ArrayList<>();
    private Image itemImage = new Image("/redSquare.png");

    public Game(){
    }

    @Override
    public void update(GameContainer g, double delta) {
        checkForCollision(g);
        snake.updateSnake(g);
        if (items.size() < 1){
            items.add(new ItemComponent((int)(Math.random()*gridNumber),(int)(Math.random()*gridNumber)));
        }
    }

    @Override
    public void render(GameContainer g, Renderer r) {
        r.drawGrid(gridNumber, gridNumber,0xff436325);
        snake.drawSnake(r,g.getWidth());
        for(Component item : items){
            item.drawComponent(r,itemImage,g.getHeight()/gridNumber);
        }
    }

    public void checkForCollision(GameContainer g){
        for(int i = 0; i<items.size();i++){
            if (items.get(i).x == snake.getHead().x && items.get(i).y == snake.getHead().y){
                snake.getComponents().add(new Component(snake.getComponents().get(1).x,snake.getComponents().get(1).y));
                items.remove(i);
            }
        }
        for(int i = 1; i<snake.getComponents().size();i++){
            //if(snake.getHead().x == snake.getComponents().get(i).x && snake.getHead().y == snake.getComponents().get(i).y){
             //   g.stop();
            //}
        }
        if(snake.getHead().x < 0 || snake.getHead().y < 0 || snake.getHead().x > gridNumber-1 || snake.getHead().y > gridNumber-1){
            g.stop();
        }

    }

    public static void main(String[] args) {
        Game g = new Game();
        GameContainer gc = new GameContainer(g);
        gc.start();
    }
}
