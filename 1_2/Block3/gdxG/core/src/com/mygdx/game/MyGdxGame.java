package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Scene.Gameplay;
import com.mygdx.game.Scene.MainMenu;

public class MyGdxGame extends Game {
	public static final int WORLD_W = 400;
	public static final int WORLD_H = 300;

	@Override
	public void create() {
		setScreen(new Gameplay(this));
	}
}