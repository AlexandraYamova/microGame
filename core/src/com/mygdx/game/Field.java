package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Field {
    private Texture img;
    private Vector2 position;

    public Field(){
        img =new Texture("scene.png");
        position = new Vector2(50, 50);
    }
    public void render(SpriteBatch batch){
        batch.draw(img, position.x, position.y);
    }
}
