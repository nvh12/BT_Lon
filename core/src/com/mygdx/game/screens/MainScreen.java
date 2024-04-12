package com.mygdx.game.screens;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


public class MainScreen implements Screen{
    public static final float speed = 40;
    Texture img;
    float x, y;
    MyGdxGame game;
    public MainScreen(MyGdxGame game){
        this.game = game;
    }
    public void show(){
        img = new Texture("fishie.png");
    }
    public void render(float delta){
        if(Gdx.input.isKeyPressed(Keys.W)){
            y += speed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            y -= speed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            x += speed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Keys.A)){
            x -= speed * Gdx.graphics.getDeltaTime();
        }
        Gdx.gl.glClearColor(0, 0, 2, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, x, y);
        game.batch.end();
    }
    public void resize(int width, int height){

    }
    public void pause(){

    }
    public void resume(){

    }
    public void hide(){

    }
    public void dispose(){

    }
}
