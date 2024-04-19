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
    public static final float X = Gdx.graphics.getWidth(), Y = Gdx.graphics.getHeight();
    private static final float UPDATE_INTERVAL = 0.1f;
    public static final float SPEED = 100;
    public static final float MIN_FISH_TO_EAT_SUMMON_TIME = 0.2f;
    public static final float MAX_FISH_TO_EAT_SUMMON_TIME = 0.5f;
    public static final float start_size = Y*2/27;
    private static final float NAV_BAR_Y_POSITION = Y*20/27;
    public static final float FISH_SIZE_IN_NAV_BAR = Y*5/54;
    public float size;
    public int score, level;
    Texture img, background_in_game, bar;
    Texture fish1,fish2,fish3,fish4,fish5;
    Texture bar1, bar2, mark;
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
        background_in_game = new Texture("Background_in_game.jfif");
        bar = new Texture("Nav_bar.png");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2.0f);
        score = 0;
        level = 1;
        size = start_size;
        fish1 = new Texture("fish1.0.png");
        fish2 = new Texture("fish2.0.png");
        fish3 = new Texture("fish3.0.png");
        fish4 = new Texture("fish4.0.png");
        fish5 = new Texture("fish5.0.png");
        bar1 = new Texture("GreyBar.png");
        bar2 = new Texture("GreenBar.png");
        mark = new Texture("Untitled.png");
    }

    // Tạo ảnh cá
    public void show() {
        // ảnh cá tạm thời
    }

    // Quay cá theo hướng di chuyển của chuột
    private Vector2 lastMousePosition = new Vector2();

    public void render(float delta) {
        float progress = (score<=160)?(float)score/160:1;
        // Cập nhật thời gian đã trôi qua
        elapsedTime += delta;

        if (elapsedTime >= UPDATE_INTERVAL) {
            // Lấy vị trí chuột hiện tại
            Vector2 currentMousePosition = new Vector2(Gdx.input.getX(), Y - Gdx.input.getY());
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
            if(Math.abs(a)<=(fishToEat.size+size)*7/16 && Math.abs(b)<=(fishToEat.size*3/4+size*5/8)/2){
                if(level>=fishToEat.level){
                    fishToEatsremove.add(fishToEat);
                    score += fishToEat.level;
                    if((score == 10 || (score >= 40 && score - fishToEat.level < 40) || (score >= 100 && score - fishToEat.level < 100)) && level < 4){
                        level++;
                        size+=16;
                    }
                }
                else{
                    this.dispose();
                    game.setScreen(new GameOverScreen(game, score));
                }
            }
        }
        fishToEats.removeAll(fishToEatsremove);

        // Đặt vị trí cho cá chính
        // di chuyển cá
        x_coordinates = Gdx.input.getX();
        y_coordinates = Y - Gdx.input.getY();
        if (x_coordinates < size/2) {
            x_coordinates = size/2;
        }
        if (x_coordinates  > X - size/2) {
            x_coordinates = X - size/2;
        }
        if (y_coordinates < size/2) {
            y_coordinates = size/2;
        }
        if (y_coordinates > NAV_BAR_Y_POSITION - size/2+40) {
            y_coordinates = NAV_BAR_Y_POSITION - size/2+40;
        }
        // nền
        Gdx.gl.glClearColor(0.1f, 0.637f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // vẽ cá
        game.batch.begin();
        game.batch.draw(background_in_game,0,0,X,Y);
        game.batch.draw(bar,0,NAV_BAR_Y_POSITION,X,Y-NAV_BAR_Y_POSITION);
        game.batch.draw(fish1,X*5/96-FISH_SIZE_IN_NAV_BAR/2,Y*27/32,FISH_SIZE_IN_NAV_BAR,FISH_SIZE_IN_NAV_BAR);
        game.batch.draw(fish2,X*5/96 + X*19/24*1/16-FISH_SIZE_IN_NAV_BAR/2,Y*27/32,FISH_SIZE_IN_NAV_BAR,FISH_SIZE_IN_NAV_BAR);
        game.batch.draw(fish3,X*5/96 + X*19/24/4-FISH_SIZE_IN_NAV_BAR/2,Y*27/32,FISH_SIZE_IN_NAV_BAR,FISH_SIZE_IN_NAV_BAR);
        game.batch.draw(fish4,X*5/96 + X*19/24*5/8-FISH_SIZE_IN_NAV_BAR/2,Y*27/32,FISH_SIZE_IN_NAV_BAR,FISH_SIZE_IN_NAV_BAR);
        game.batch.draw(fish5,X*5/96 + X*19/24-FISH_SIZE_IN_NAV_BAR/2,Y*27/32,FISH_SIZE_IN_NAV_BAR,FISH_SIZE_IN_NAV_BAR);
        for (Fish_to_eat fishToEat : fishToEats) {
            fishToEat.render(game.batch);
        }
        game.batch.draw(img, x_coordinates-size/2, y_coordinates-size/2, size, size);
        // vẽ điểm, level, quá trình lên level 
        game.batch.draw(bar1, X*5/96, Y*22/27, X*19/24, Y/27);
        game.batch.draw(bar2, X*5/96, Y*22/27, X*19/24*progress, Y/27);
        game.batch.draw(mark, X*5/96 + X*19/24*1/16, Y*22/27, X*19/24/160, Y/27);
        game.batch.draw(mark, X*5/96 + X*19/24/4, Y*22/27, X*19/24/160, Y/27);
        game.batch.draw(mark, X*5/96 + X*19/24*5/8, Y*22/27, X*19/24/160, Y/27);
        font.draw(game.batch, Integer.toString(score), X-100, Y-100);
        font.draw(game.batch, Integer.toString(level), X-100, Y-140);
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
