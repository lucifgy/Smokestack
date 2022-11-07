package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

public class Object {
    Sprite ball;
    Body body;
    final float width = MyGdxGame.WORLD_W;
    final float height = MyGdxGame.WORLD_H;
    final float midX = width / 2;
    final float midY = height / 2;
    public Object(World world, float w, float h, String path, boolean isDynamic)
    {
        ball = new Sprite(new Texture(path));

        ball.setSize(w, h);
        ball.setPosition(midX - ball.getWidth() / 2,midY - ball.getHeight() / 2);

        BodyDef bodyDef = new BodyDef();
        if (isDynamic) bodyDef.type = BodyDef.BodyType.DynamicBody;
        if (!isDynamic)  bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(ball.getX(), ball.getY());
        bodyDef.fixedRotation =  true;


        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ball.getWidth() / 2, ball.getHeight() / 2, new Vector2(ball.getWidth() / 2, ball.getHeight() / 2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1f;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }
    public void draw(SpriteBatch batch)
    {
        ball.setPosition(body.getPosition().x, body.getPosition().y);
        ball.draw(batch);
    }
    public void pushBall(Vector2 vec){
        body.setLinearVelocity(vec);
    }
    public void move(float x, float y){
        //ball.setPosition(x,y);
        body.setTransform(new Vector2(x - ball.getWidth() / 2, y - ball.getHeight() / 2), 0);

    }
    public float getXPos()
    {
        return ball.getX();
    }
    public float getYPos()
    {
        return ball.getY();
    }
}
