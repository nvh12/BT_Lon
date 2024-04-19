package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameOverScreen implements Screen{
    private static final float X = Gdx.graphics.getWidth(), Y =Gdx.graphics.getHeight();
    private static final Texture gameOverTexture = new Texture("gameover.png");
    int score, highScore;
    private BitmapFont font;
    MyGdxGame game;
    Texture tryAgainActive;
    Texture tryAgainInactive;
    Texture mainMenuActive;
    Texture mainMenuInactive;
    public GameOverScreen(MyGdxGame game, int score){
        this.game = game;
        this.score = score;
        com.badlogic.gdx.Preferences prefs = Gdx.app.getPreferences("MyGdxGame");
        this.highScore = prefs.getInteger("highScore", 0);
        if(score > highScore){
            prefs.putInteger("highScore", score);
            prefs.flush();
        }
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2.0f);
        tryAgainActive = new Texture("TryAgain.png");
        tryAgainInactive = new Texture("TryAgain1.png");
        mainMenuActive = new Texture("Mainmenu.png");
        mainMenuInactive = new Texture("Mainmenu1.png");
    }
    public void show(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 3, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(gameOverTexture, Gdx.graphics.getWidth()/3,  Gdx.graphics.getHeight()*5/8, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/4);
        font.draw(game.batch, "Score: " + Integer.toString(score), Gdx.graphics.getWidth()*2/5, Gdx.graphics.getHeight()*9/16);
        font.draw(game.batch, "High score: " + Integer.toString(highScore), Gdx.graphics.getWidth()*2/5, Gdx.graphics.getHeight()/2);
    if(Gdx.input.getX() >= X*2/5 && Gdx.input.getX() <= X*3/5 && Gdx.input.getY() >= Y*5/9 && Gdx.input.getY() <= Y*29/45){
        game.batch.draw(tryAgainInactive, X*2/5, Y*16/45, X/5, Y*8/90);
        if(Gdx.input.isTouched()){
            this.dispose();
            game.setScreen(new MainScreen(game));
        }
    }
    else{
        game.batch.draw(tryAgainActive, X*2/5, Y*16/45, X/5, Y*8/90);
    }
    if(Gdx.input.getX() >= X*2/5 && Gdx.input.getX() <= X*3/5 && Gdx.input.getY() >= Y*11/15 && Gdx.input.getY() <= Y*37/45){
        game.batch.draw(mainMenuInactive, X*2/5, Y*8/45, X/5, Y*8/90);
        if(Gdx.input.isTouched()){
            this.dispose();
            game.setScreen(new MainMenu(game));
        }
    }
    else{
        game.batch.draw(mainMenuActive, X*2/5, Y*8/45, X/5, Y*8/90);
    }
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
