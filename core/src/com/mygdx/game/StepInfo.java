package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;

public class StepInfo {
    BitmapFont font;
    int numSteps;
    String textStep;
    float widht;
    float height;
    public StepInfo(int numSteps){
        numSteps = this.numSteps;
        textStep = "Сделано ходов: "+numSteps;
        widht = 200;
        height = 90;
        font = new BitmapFont();
//        font.
//        font.setColor(Color.WHITE);
    }
}
