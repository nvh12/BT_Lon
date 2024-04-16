package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu implements Screen{
    private static final int exitButtonSize = Gdx.graphics.getHeight()/24, playButtonSize = Gdx.graphics.getHeight()*5/36;
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
        if(Gdx.input.getX() >= Gdx.graphics.getWidth()-exitButtonSize && Gdx.input.getY() <= exitButtonSize){
            game.batch.draw(exitButtonInactive, Gdx.graphics.getWidth()-exitButtonSize, Gdx.graphics.getHeight()-exitButtonSize, exitButtonSize, exitButtonSize);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } 
        else{
            game.batch.draw(exitButtonActive, Gdx.graphics.getWidth()-exitButtonSize, Gdx.graphics.getHeight()-exitButtonSize, exitButtonSize, exitButtonSize);
        }
        //nút thoát
        if(Gdx.input.getX() >= Gdx.graphics.getWidth()/2-playButtonSize/2 && Gdx.input.getX() <= Gdx.graphics.getWidth()/2+playButtonSize/2 && Gdx.input.getY() >= Gdx.graphics.getHeight()*2/3-playButtonSize/2 && Gdx.input.getY() <= Gdx.graphics.getHeight()*2/3+playButtonSize/2){
            game.batch.draw(playButtonInactive, Gdx.graphics.getWidth()/2-playButtonSize/2, Gdx.graphics.getHeight()/3-playButtonSize/2, playButtonSize, playButtonSize);
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainScreen(game));
            }
        }
        else{
            game.batch.draw(playButtonActive, Gdx.graphics.getWidth()/2-playButtonSize/2, Gdx.graphics.getHeight()/3-playButtonSize/2, playButtonSize, playButtonSize);
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
