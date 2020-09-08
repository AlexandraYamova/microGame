package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Buttons {
    static class RestartButton{
        Texture img;
        Vector2 position;
        Vector2 size;
        public RestartButton(){
            img = new Texture("restartButton.png");
            position = new Vector2(600, 350);
            size = new Vector2(200, 90);
        }
        public void render(SpriteBatch batch){
            batch.draw(img, position.x, position.y, size.x, size.y);
        }
        public boolean update(){
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                float clickX = Gdx.input.getX();
                float clickY = 500-Gdx.input.getY();
                float buttonX0 = position.x;
                float buttonX1 = position.x+size.x;
                float buttonY0 = position.y;
                float buttonY1 = position.y+size.y;
                if (clickX>=buttonX0 && clickX<=buttonX1
                        && clickY>=buttonY0 && clickY<=buttonY1){
                    return true;
                }
            }
            return false;
        }
    }
    static class RulesButton{
        static class RulesField{
            Texture img;
            Vector2 size;
            Vector2 position;
            boolean isVisible;
            public RulesField(){
                img =new Texture("RuleField.jpg");
                size = new Vector2(400, 400);
                position = new Vector2(50, 50);
                isVisible = false;
            }
            public void render(SpriteBatch batch){
                if (isVisible){
                    batch.draw(img, position.x, position.y, size.x, size.y);
                }
            }
            public void changeVisibleStatus(){
                isVisible = !isVisible;
            }
        }
        Texture img;
        Vector2 position;
        Vector2 size;
        public RulesButton() {
            img = new Texture("rulesButton.png");
            position = new Vector2(600, 240);
            size = new Vector2(200, 90);
        }
        public void render(SpriteBatch batch){
            batch.draw(img, position.x, position.y, size.x, size.y);
        }
        public boolean updateNotVisible(){
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                float clickX = Gdx.input.getX();
                float clickY = 500-Gdx.input.getY();
                float buttonX0 = position.x;
                float buttonX1 = position.x+size.x;
                float buttonY0 = position.y;
                float buttonY1 = position.y+size.y;
                if (clickX>=buttonX0 && clickX<=buttonX1
                        && clickY>=buttonY0 && clickY<=buttonY1){
                    return true;
                }
            }
            return false;
        }
        public boolean updateVisible(boolean check){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&&check){
                return true;
            }
            return false;
        }
    }
    static class WinButton{
        private Texture img;
        private Vector2 position;
        private Vector2 size;
        private boolean isVisible;
        public WinButton(){
            img =new Texture("winjpg.jpg");
            position = new Vector2(50,50);
            size = new Vector2(400,400);
            isVisible = false;
        }
        public void render(SpriteBatch batch){
            if (isVisible){
                batch.draw(img, position.x, position.y, size.x, size.y);
            }
        }
        public void changeWinCheckStatus(){
            isVisible = true;
        }
    }
    static class Exit{
        public static void render(){
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                System.exit(0);
            }
        }
    }
}
