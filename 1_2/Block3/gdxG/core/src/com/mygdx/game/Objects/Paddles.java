package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

public class Paddles {
    Sprite[] paddles = new Sprite[4];
    Body body;
    final float width = MyGdxGame.WORLD_W;
    final float height = MyGdxGame.WORLD_H;
    final float midX = width / 2;
    final float midY = height / 2;

    public Paddles(World world)
    {

    }
}
