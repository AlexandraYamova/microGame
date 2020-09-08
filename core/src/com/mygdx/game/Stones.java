package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



//todo Подсчёт очков (Сделать функцию update - boolean, чтоб возвращала true, когда происходит движение)


public class Stones {
    private Texture stoneBlackTexture;
    private Texture stoneRedTexture;
    private Texture stoneYellowTexture;
    private Texture stoneGreenTexture;
    private Stone[] stonesBlack;
    public Stone[] stonesColor;
    public Stones(){
        stoneRedTexture = new Texture("red.jpg");
        stoneGreenTexture = new Texture("green.jpg");
        stoneYellowTexture = new Texture("yellow.jpg");
        stoneBlackTexture = new Texture("black.jpg");
        stonesBlack = getFieldWithBlocks();
        stonesColor =getFieldWithStones();
    }
    /**
     * Заполнение случайными камнями
     */
    private char getRandomStone(){
        int random = (int)(Math.random()*3);
        char randomColor;
        switch (random){
            case 1:
                randomColor = 'R';
                break;
            case 2:
                randomColor = 'G';
                break;
            default:
                randomColor = 'Y';
                break;
        }
        return randomColor;
    }

    private Texture[] getRandomStonesTexture(){
        Texture[] randomStonesTexture = new Texture[15];
        int numGreen = 0;
        int numYellow = 0;
        int numRed = 0;
        int i=0;
        while(i<15){
            char randomStone = getRandomStone();
            switch (randomStone){
                case 'R':
                    if (numRed>=5){
                        break;
                    }
                    else{
                        randomStonesTexture[i]= stoneRedTexture;
                        numRed++;
                        i++;
                        break;
                    }
                case 'G':
                    if (numGreen>=5){
                        break;
                    }
                    else{
                        randomStonesTexture[i]= stoneGreenTexture;
                        numGreen++;
                        i++;
                        break;
                    }
                default:
                    if (numYellow>=5){
                        break;
                    }
                    else{
                        randomStonesTexture[i]= stoneYellowTexture;
                        numYellow++;
                        i++;
                        break;
                    }
            }
        }
        return randomStonesTexture;
    }

    private Stone[] getFieldWithBlocks(){
        float fieldSize = 400;
        float delta = 50;
        Stone[] stonesBlock =new Stone[6];
        int x =0;
        for (float i = 80+delta; i<fieldSize+delta; i = i+160){
            for (float j =0+delta; j<fieldSize+delta; j=j+160){
                Vector2 pos = new Vector2(i,j);
                stonesBlock[x] = new Stone(pos, stoneBlackTexture, 'B');
                x++;
            }
        }
        return stonesBlock;
    }
    private Stone[] getFieldWithStones(){
        Texture[] stoneColor = getRandomStonesTexture();
        float fieldSize = 400;
        float delta = 50;
        Stone[] stonesGame =new Stone[15];
        int x = 0;
        char color = 'R';
        for (float i = 0+delta; i<fieldSize+delta; i = i+160){
            for (float j =0+delta; j<fieldSize+delta; j=j+80){
                Vector2 pos = new Vector2(i,j);
                if (stoneColor[x]==stoneGreenTexture){
                    color ='G';
                }
                if (stoneColor[x]==stoneRedTexture){
                    color ='R';
                }
                if (stoneColor[x]==stoneYellowTexture){
                    color ='Y';
                }
                stonesGame[x] = new Stone(pos, stoneColor[x],color);
                x++;
            }
        }
        return stonesGame;
    }
    /**
     * Отрисовка
     */
    public  void render(SpriteBatch batch, Stone[] stonesColor){
        Stone[] stonesBlack = getFieldWithBlocks();
        for (Stone stone : stonesBlack) {
            batch.draw(stone.img, stone.position.x + 5, stone.position.y + 5);
        }
        for (Stone stone : stonesColor) {
            batch.draw(stone.img, stone.position.x + 5, stone.position.y + 5);
        }
    }
    /**
     * Игровая логика+отслеживание событий с клавиатуры и мыши
    */
    public void update(SpriteBatch batch){
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            float clickX = Gdx.input.getX();
            float clickY = 500-Gdx.input.getY();
            stoneChooseMethod(clickX,clickY);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            for (Stone stone : stonesColor) {
                if (stone.isStoneChoose) {
                    if (!checkNextLeftStepStone(stone)) {
                        stone.setLeftMove();
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            for (Stone stone : stonesColor) {
                if (stone.isStoneChoose) {
                    if (!checkNextRightStepStone(stone)) {
                        stone.setRightMove();
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            for (Stone stone : stonesColor) {
                if (stone.isStoneChoose) {
                    if (!checkNextUpStepStone(stone)) {
                        stone.setUpMove();
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            for (Stone stone : stonesColor) {
                if (stone.isStoneChoose) {
                    if (!checkNextDownStepStone(stone)) {
                        stone.setDownMove();
                    }
                }
            }
        }
    }
    public boolean winUpdate(){
        if (winCheck()){
            return true;
        }
        return false;
    }
    /**
     *Передвижение
     */
    private boolean checkIsClickOnStone(float clickX, float clickY, Stone stone){
        float stoneX0 = stone.position.x;
        float stoneY0 = stone.position.y;
        float stoneX1 = stoneX0+70;
        float stoneY1 = stoneY0+70;
        if (stoneX0<=clickX && stoneX1>=clickX && stoneY0<=clickY && stoneY1>=clickY){
            return true;
        }
        return false;
    }
    private void stoneChooseMethod(float clickX, float clickY){
        for (int i=0; i<stonesColor.length;i++){
            if (checkIsClickOnStone(clickX, clickY, stonesColor[i])){
                if (stonesColor[i].isStoneChoose){
                    stonesColor[i].isStoneChoose = false;
                }
                else {
                    stonesColor[i].isStoneChoose = true;
                }
            }
            else
                stonesColor[i].isStoneChoose = false;
        }
    }
    private boolean checkNextRightStepStone(Stone stone){
        float stoneWaitingPositionX = stone.position.x+80;
        for (int i=0; i<stonesColor.length;i++){
            if ((stonesColor[i].position.x == stoneWaitingPositionX &&
                    stonesColor[i].position.y == stone.position.y))
                return true;
        }
        for (int i=0; i<stonesBlack.length;i++){
            if ((stonesBlack[i].position.x == stoneWaitingPositionX &&
                    stonesBlack[i].position.y == stone.position.y))
                return true;
        }
        return false;
    }
    private boolean checkNextLeftStepStone(Stone stone){
        float stoneWaitingPositionX = stone.position.x-80;
        for (int i=0; i<stonesColor.length;i++){
            if (stonesColor[i].position.x == stoneWaitingPositionX &&
                    stonesColor[i].position.y == stone.position.y)
                return true;
        }
        for (int i=0; i<stonesBlack.length;i++){
            if (stonesBlack[i].position.x == stoneWaitingPositionX &&
                    stonesBlack[i].position.y == stone.position.y)
                return true;
        }
        return false;
    }
    private boolean checkNextUpStepStone(Stone stone){
        float stoneWaitingPositionY = stone.position.y+80;
        for (int i=0; i<stonesColor.length;i++){
            if ((stonesColor[i].position.x == stone.position.x &&
                    stonesColor[i].position.y == stoneWaitingPositionY))
                return true;
        }
        for (int i=0; i<stonesBlack.length;i++){
            if ((stonesBlack[i].position.x == stone.position.x &&
                    stonesBlack[i].position.y == stoneWaitingPositionY))
                return true;
        }
        return false;
    }
    private boolean checkNextDownStepStone(Stone stone){
        float stoneWaitingPositionY = stone.position.y-80;
        for (int i=0; i<stonesColor.length;i++){
            if ((stonesColor[i].position.x == stone.position.x &&
                    stonesColor[i].position.y == stoneWaitingPositionY))
                return true;
        }
        for (int i=0; i<stonesBlack.length;i++){
            if (stonesBlack[i].position.x == stone.position.x &&
                    stonesBlack[i].position.y == stoneWaitingPositionY)
                return true;
        }
        return false;
    }
    /**
     *Проверка победы
     */
    private boolean winCheck(){
        float delta = 50;
        float sizeStone = 80;
        if (winCheckColumn(delta) && winCheckColumn(delta+2*sizeStone) && winCheckColumn(delta+4*sizeStone)){
            return true;
        }
        return false;
    }
    private boolean winCheckColumn(float posX){
        Stone[] stoneInColumn = {stonesColor[0], stonesColor[0],
                stonesColor[0], stonesColor[0], stonesColor[0]};
        int index=0;
        for (Stone stone : stonesColor) {
            if (stone.position.x == posX){
                stoneInColumn[index] = stone;
                index++;
            }
        }
        if (index!=5)
            return false;
        for (int i = 1; i < stoneInColumn.length; i++) {
            if (stoneInColumn[0].color!=stoneInColumn[i].color)
                return false;
        }
        return true;
    }
}