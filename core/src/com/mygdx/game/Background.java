package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private Texture img;
    private Vector2 position;
    public Background(){
        img = new Texture("space.png");
        position = new Vector2(0,0);
    }
    public void render(SpriteBatch batch){
        batch.draw(img, position.x, position.y);
    }
}
