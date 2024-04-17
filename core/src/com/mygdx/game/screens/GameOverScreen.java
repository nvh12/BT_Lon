package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Screens.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.GL20;

public class GameOverScreen implements Screen{
    MyGdxGame game;
    private static final Texture gameOverTexture = new Texture("gameover.png");
    public static int highScore = 0;
    public int score;
    public GameOverScreen(MyGdxGame game){
        this.game = game;
        score = MainScreen.score;
        if(score > highScore) highScore = score;
    }
    public void show(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 3, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(gameOverTexture, Gdx.graphics.getWidth()/3,  Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/4 );
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
