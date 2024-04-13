package com.mygdx.game.screens;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


public class MainScreen implements Screen{
    public static final float speed = 100;
    Texture img;
    float x, y;
    MyGdxGame game;
    public MainScreen(MyGdxGame game){
        this.game = game;
    }
    public void show(){
        img = new Texture("pixil-frame-1.png");
        //ảnh cá tạm thời
    }
    public void render(float delta){
        x = Gdx.input.getX();
        y = 720 - Gdx.input.getY();
        //di chuyển cá
        Gdx.gl.glClearColor(0.1f, 0.637f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //nền
        game.batch.begin();
        game.batch.draw(img, x, y, 80, 80);
        //vẽ cá
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
