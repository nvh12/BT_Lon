package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Fish.Fish_to_eat;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

import static com.badlogic.gdx.math.MathUtils.random;

public class MainScreen implements Screen {
    private float elapsedTime = 0.0f;
    private static final float UPDATE_INTERVAL = 0.1f;
    public static final float SPEED = 100;
    public static final float MIN_FISH_TO_EAT_SUMMON_TIME = 0.5f;
    public static final float MAX_FISH_TO_EAT_SUMMON_TIME = 1f;
    public static final float start_size = 80;
    public float size;
    private int score, level, levelProgress;
    Texture img;
    Random random1 = new Random();
    float x_coordinates, y_coordinates;
    float fish_summon_timer;
    MyGdxGame game;
    private BitmapFont font;

    ArrayList<Fish_to_eat> fishToEats;

    public MainScreen(MyGdxGame game) {
        this.game = game;
        fish_summon_timer = random1.nextFloat() * (MAX_FISH_TO_EAT_SUMMON_TIME - MIN_FISH_TO_EAT_SUMMON_TIME)
                + MIN_FISH_TO_EAT_SUMMON_TIME;
        fishToEats = new ArrayList<Fish_to_eat>();
        img = new Texture("Mainfish1.png");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        score = 0;
        level = 1;
        levelProgress = 0;
        size = start_size;
    }

    // Tạo ảnh cá
    public void show() {
        
        // ảnh cá tạm thời
    }

    // Quay cá theo hướng di chuyển của chuột
    private Vector2 lastMousePosition = new Vector2();

    public void render(float delta) {
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

        // Tạo mấy con cá cảnh lượn lờ
        fish_summon_timer -= delta;
        if (fish_summon_timer <= 0) {
            fish_summon_timer = random1.nextFloat() * (MAX_FISH_TO_EAT_SUMMON_TIME - MIN_FISH_TO_EAT_SUMMON_TIME)
                    + MIN_FISH_TO_EAT_SUMMON_TIME;
            int index_type_of_fish = random.nextInt(5) + 1;
            fishToEats.add(new Fish_to_eat(index_type_of_fish));
        }

        // Xóa mấy con cá cảnh
        ArrayList<Fish_to_eat> fishToEatsremove = new ArrayList<Fish_to_eat>();
        for (Fish_to_eat fishToEat : fishToEats) {
            fishToEat.update(delta);
            if (fishToEat.remove)
                fishToEatsremove.add(fishToEat);
            float a = fishToEat.x - x_coordinates, b = fishToEat.y - y_coordinates;
            if(Math.abs(a)<=(fishToEat.size+size)/2 && Math.abs(b)<=(fishToEat.size+size)/2 && level>=fishToEat.level){
                fishToEatsremove.add(fishToEat);
                score++;
                levelProgress++;
            }
        }
        fishToEats.removeAll(fishToEatsremove);
        if(levelProgress == 7){
            levelProgress = 0;
            level++;
            size += 10;
        }

        // Đặt vị trí cho cá chính
        x_coordinates = Gdx.input.getX();
        y_coordinates = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x_coordinates < size) {
            x_coordinates = size;
        }
        if (x_coordinates + size > Gdx.graphics.getWidth()) {
            x_coordinates = Gdx.graphics.getWidth() - size;
        }
        if (y_coordinates < size) {
            y_coordinates = size;
        }
        if (y_coordinates > Gdx.graphics.getHeight() - size) {
            y_coordinates = Gdx.graphics.getHeight() - size;
        }
        // di chuyển cá
        Gdx.gl.glClearColor(0.1f, 0.637f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // nền
        game.batch.begin();
        for (Fish_to_eat fishToEat : fishToEats) {
            fishToEat.render(game.batch);
        }
        game.batch.draw(img, x_coordinates-size/2, y_coordinates-size/2, size, size);
        // vẽ cá
        font.draw(game.batch, Integer.toString(score), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-100);
        font.draw(game.batch, Integer.toString(levelProgress), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-150);
        game.batch.end();
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }
    // Hello
}
