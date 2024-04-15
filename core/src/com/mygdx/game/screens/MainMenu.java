package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu implements Screen{
    private static final int exitButtonSize = 30, playButtonSize = 100;
    MyGdxGame game;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    public MainMenu(MyGdxGame game){
        this.game = game;
        playButtonActive = new Texture("play.png");
        playButtonInactive = new Texture("play(1).png");
        exitButtonActive = new Texture("Ps-x-button.png");
        exitButtonInactive = new Texture("Ps-x-button1.png");
        //ảnh cho nút chơi và thoát
    }
    public void show(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 3, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //nền
        game.batch.begin();
        if(Gdx.input.getX() >= 1220 && Gdx.input.getY() <= 60){
            game.batch.draw(exitButtonInactive, 1250, 690, exitButtonSize, exitButtonSize);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } 
        else{
            game.batch.draw(exitButtonActive, 1250, 690, exitButtonSize, exitButtonSize);
        }
        //nút thoát
        if(Gdx.input.getX() >= 590 && Gdx.input.getX() <= 690 && Gdx.input.getY() >= 380 && Gdx.input.getY() <= 480){
            game.batch.draw(playButtonInactive, 590, 240, playButtonSize, playButtonSize);
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainScreen(game));
            }
        }
        else{
            game.batch.draw(playButtonActive, 590, 240, playButtonSize, playButtonSize);
        }
        //nút chơi
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
