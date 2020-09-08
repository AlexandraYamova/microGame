package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Stone {
    Texture img;
    Vector2 position;
    float speed;
    boolean isStoneChoose;
    char color;
    public Stone(Vector2 position, Texture img, char color){
        this.position = position;
        this.img = img;
        this.color = color;
        speed = 80f;
        isStoneChoose = false;
    }
    public void setLeftMove(){
        if (position.x-speed>=50)
            position.x-=speed;
    }
    public void setRightMove(){
        if (position.x+speed<450)
            position.x+=speed;
    }
    public void setDownMove(){
        if (position.y-speed>=50)
            position.y-=speed;
    }
    public void setUpMove(){
        if (position.y+speed<450)
            position.y+=speed;
    }
}
