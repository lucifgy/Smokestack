package com.mygdx.game.Scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;


public class MainMenu implements Screen {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;

    public MainMenu(MyGdxGame g){this.game = g;}

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WORLD_W, game.WORLD_H);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        Gdx.input.setInputProcessor(stage);

        //game.setScreen(new Gameplay());
        dispose();
        //Gdx.app.exit();

    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,false);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
