package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Fish.Fish_to_eat;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;


public class MainScreen implements Screen{
    private float elapsedTime = 0.0f;
    private static final float UPDATE_INTERVAL = 0.1f;
    public static final float SPEED = 100;
    public static final float MIN_FISH_TO_EAT_SUMMON_TIME = 0.3f;
    public static final float MAX_FISH_TO_EAT_SUMMON_TIME = 1f;

    Texture img;
    Random random1 = new Random();
    float x_coordinates, y_coordinates;
    float fish_summon_timer  ;
    MyGdxGame game;

    ArrayList<Fish_to_eat> fishToEats;
    public MainScreen(MyGdxGame game){
        this.game = game;
        fish_summon_timer = random1.nextFloat()*(MAX_FISH_TO_EAT_SUMMON_TIME-MIN_FISH_TO_EAT_SUMMON_TIME)+MIN_FISH_TO_EAT_SUMMON_TIME;
        fishToEats = new ArrayList<Fish_to_eat>();
    }

    //Tạo ảnh cá
    public void show(){
        img = new Texture("Mainfish2.png");
        //ảnh cá tạm thời
    }



    //Quay cá theo hướng di chuyển của chuột
    private Vector2 lastMousePosition = new Vector2();
    public void render(float delta){
        // Cập nhật thời gian đã trôi qua
        elapsedTime += delta;

        if (elapsedTime >= UPDATE_INTERVAL) {
            // Lấy vị trí chuột hiện tại
            Vector2 currentMousePosition = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            // Tính độ chênh lệch giữa vị trí chuột hiện tại và trước đó
            float deltaX = currentMousePosition.x - lastMousePosition.x;
            // Cập nhật vị trí chuột trước đó
            lastMousePosition.x = currentMousePosition.x;
            // Quay chú cá dựa trên độ chênh lệch
            if (deltaX < 0) {
                img = new Texture("Mainfish2.png");
            }
            if (deltaX > 0) {
                // Quay sang phải
                img = new Texture("Mainfish1.png");
            }
            elapsedTime -= UPDATE_INTERVAL;
        }



        //Tạo mấy con cá cảnh lượn lờ
        fish_summon_timer -= delta;
        if(fish_summon_timer<=0){
            fish_summon_timer = random1.nextFloat()*(MAX_FISH_TO_EAT_SUMMON_TIME-MIN_FISH_TO_EAT_SUMMON_TIME)+MIN_FISH_TO_EAT_SUMMON_TIME;
            int index_type_of_fish = random.nextInt(5)+1;
            fishToEats.add(new Fish_to_eat(index_type_of_fish));
        }

        //Xóa mấy con cá cảnh
        ArrayList<Fish_to_eat> fishToEatsremove = new ArrayList<Fish_to_eat>();
        for(Fish_to_eat fishToEat : fishToEats){
            fishToEat.update(delta);
            if(fishToEat.remove)
                fishToEatsremove.add(fishToEat);
        }
        fishToEats.removeAll(fishToEatsremove);

        //Đặt vị trí cho cá chính
        x_coordinates = Gdx.input.getX()-30;
        y_coordinates = 690 - Gdx.input.getY();
        if(x_coordinates<0){
            x_coordinates = 0;
        }
        if(x_coordinates+img.getWidth()>Gdx.graphics.getWidth()){
            x_coordinates = Gdx.graphics.getWidth()- img.getWidth();
        }
        //di chuyển cá
        Gdx.gl.glClearColor(0.1f, 0.637f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //nền
        game.batch.begin();
        for (Fish_to_eat fishToEat : fishToEats){
            fishToEat.render(game.batch);
        }
        game.batch.draw(img, x_coordinates, y_coordinates, 80, 80);
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
    //Hello
}
