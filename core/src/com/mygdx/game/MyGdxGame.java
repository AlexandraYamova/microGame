package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bcg;
	Field field;
	Stones stones;
	Buttons.RestartButton rst;
	Buttons.RulesButton rule;
	Buttons.WinButton win;
	Buttons.RulesButton.RulesField ruleField;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bcg = new Background();
		field = new Field();
		stones = new Stones();
		rst = new Buttons.RestartButton();
		rule = new Buttons.RulesButton();
		win = new Buttons.WinButton();
		ruleField = new Buttons.RulesButton.RulesField();
	}

	@Override
	public void render () {
		update();
		batch.begin();
		bcg.render(batch);
		field.render(batch);
		rst.render(batch);
		rule.render(batch);
		stones.render(batch, stones.stonesColor);
		ruleField.render(batch);
		win.render(batch);
		Buttons.Exit.render();
		batch.end();
	}

	public void update(){
		stones.update(batch);
		if (stones.winUpdate()){
			win.changeWinCheckStatus();
		}
		if (rst.update()){
			create();
		}
		if (rule.updateNotVisible()){
			ruleField.changeVisibleStatus();
		}
		if (rule.updateVisible(ruleField.isVisible)){
			ruleField.changeVisibleStatus();
		}
	}
	
	@Override
	public void dispose () {

		batch.dispose();
	}
}
