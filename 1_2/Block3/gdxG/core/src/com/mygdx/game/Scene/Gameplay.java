package com.mygdx.game.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Objects.Object;
import com.mygdx.game.MyGdxGame;

public class Gameplay implements Screen
{
    MyGdxGame game;

    public Gameplay(MyGdxGame g) {this.game = g;
    }

    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    SpriteBatch batch;
    OrthographicCamera camera;
    ExtendViewport viewport;
    World world;

    final float width = MyGdxGame.WORLD_W;
    final float height = MyGdxGame.WORLD_H;

    final float midX = width / 2;
    final float midY = height / 2;

    final float paddleSizeX = width / 50;
    final float paddleSizeY = height / 4;

    final float ballSize = height / 25;
    final int padding = 10;

    final float PIXELS_TO_METERS = 100f;

    Object ball;
    Object paddle_L, paddle_R, paddle_T, paddle_B;

    @Override
    public void show() {
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0,0), true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        //camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        viewport = new ExtendViewport(width,height,camera);
        ball = new Object(world, ballSize, ballSize, "Circle.png", true);
        paddle_L = new Object(world, paddleSizeX, paddleSizeY, "Rectangle.png", false);
        paddle_T = new Object(world, paddleSizeY, paddleSizeX, "Rectangle.png", false);
        paddle_B = new Object(world, paddleSizeY, paddleSizeX, "Rectangle.png", false);
        paddle_R = new Object(world, paddleSizeX, paddleSizeY, "Rectangle.png", false);
        paddle_T.move(midX, height - padding);
        paddle_L.move(padding, midY);
        paddle_B.move(midX, padding);
        paddle_R.move(width - padding, midY);

        ball.pushBall(new Vector2(-1000, -666));
    }

    @Override
    public void render(float delta) {
        camera.update();

        world.step(Gdx.graphics.getDeltaTime(), 8, 2);

        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);

        batch.begin();
        ball.draw(batch);
        paddle_L.draw(batch);
        paddle_T.draw(batch);
        paddle_R.draw(batch);
        paddle_B.draw(batch);
        batch.end();
        //debugRenderer.render(world, camera.combined);


        //Mouse input
        Vector3 mousePos = viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        paddle_T.move(mousePos.x, height - padding);
        paddle_L.move(padding, mousePos.y);
        paddle_R.move(width - padding, mousePos.y);
        paddle_B.move(mousePos.x, padding);
        //Mouse input


        //Touch input
        if(Gdx.input.isTouched())
        {
            paddle_T.move(Gdx.input.getX(), height - padding);
            paddle_L.move(padding, Gdx.input.getY());
            paddle_R.move(width - padding, Gdx.input.getY());
            paddle_B.move(Gdx.input.getX(), padding);
        }
        //Touch input

        //Physics contact listener
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        //Temp
        if(ball.getXPos() >= width || ball.getXPos() <= 0)
        {
            game.setScreen(new Gameplay(game));
        }
        if(ball.getYPos() >= height || ball.getYPos() <= 0)
        {
            game.setScreen(new Gameplay(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
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

    }
}