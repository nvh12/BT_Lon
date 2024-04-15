package com.mygdx.game.Screens;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainScreen implements Screen{
    public static final float fish_x = 175, fish_y =110;
    public static final float speed = 100, animationSpeed = 0.5f;
    int score = 0, level = 1;
    //Animation[] fishAnimations;
    Texture img_1, img_2;
    MyGdxGame game;
    float x, y, a, stateTime;
    public MainScreen(MyGdxGame game){
        this.game = game;
        /*fishAnimations = new Animation[1];
        TextureRegion[][] fishSpriteSheet = TextureRegion.split(new Texture("red.png"), (int)fish_x, (int)fish_y);
        fishAnimations[0] = new Animation<TextureRegion>(animationSpeed, fishSpriteSheet[0]);*/
    }
    public void show(){
        img_1 = new Texture("pixil-frame-1.png");
        img_2 = new Texture("pixil-frame-2.png");
        //ảnh cá tạm thời
    }
    public void render(float delta){
        a = Gdx.input.getX() - 40 - x;
        x = Gdx.input.getX() - 40;
        y = 720 - Gdx.input.getY() - 40;
        if( x < 0) x = 0;
        if( x > 1200) x = 1200;
        if( y < 0) y = 0;
        if( y > 640) y = 640;
        //di chuyển cá
        stateTime += delta;
        Gdx.gl.glClearColor(0.1f, 0.637f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //nền
        game.batch.begin();
        if(a >= 0){
            game.batch.draw(img_1, x, y, 80, 80);
        }
        else{
            game.batch.draw(img_2, x, y, 80, 80);
        }
        //game.batch.draw(fishAnimations[0].getKeyFrame(stateTime, true), x,  y, fish_x, fish_y);
        
        
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
