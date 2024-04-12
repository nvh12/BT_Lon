package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu implements Screen{
    MyGdxGame game;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    public MainMenu(MyGdxGame game){
        this.game = game;
        playButtonActive = new Texture("images.png");
        playButtonInactive = new Texture("images2.png");
        exitButtonActive = new Texture("Ps-x-button.png");
        exitButtonInactive = new Texture("Ps-x-button1.png");
    }
    public void show(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 2, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(exitButtonActive, 1200, 640, 100, 100);
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
