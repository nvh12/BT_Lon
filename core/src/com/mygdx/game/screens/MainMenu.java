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
    Texture background_start_game;
    public MainMenu(MyGdxGame game){
        this.game = game;
        playButtonActive = new Texture("Start_button_2.jpg");
        playButtonInactive = new Texture("Start_button_1.jpg");
        exitButtonActive = new Texture("Ps-x-button.png");
        exitButtonInactive = new Texture("Ps-x-button1.png");
        background_start_game = new Texture("Feeding-Frenzy-Background_Screen.jpg");
        //ảnh cho nút chơi và thoát
    }
    public void show(){

    }
    public void render(float delta){
        //nền
        Gdx.gl.glClearColor(0, 0, 3, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //nút thoát
        game.batch.begin();
        game.batch.draw(background_start_game,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(Gdx.input.getX() >= Gdx.graphics.getWidth()-exitButtonSize && Gdx.input.getY() <= exitButtonSize){
            game.batch.draw(exitButtonInactive, Gdx.graphics.getWidth()-exitButtonSize, Gdx.graphics.getHeight()-exitButtonSize, exitButtonSize, exitButtonSize);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } 
        else{
            game.batch.draw(exitButtonActive, Gdx.graphics.getWidth()-exitButtonSize, Gdx.graphics.getHeight()-exitButtonSize, exitButtonSize, exitButtonSize);
        }

        //nút chơi
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
