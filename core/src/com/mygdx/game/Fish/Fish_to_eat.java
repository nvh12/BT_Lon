package com.mygdx.game.Fish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.math.MathUtils.random;

public class Fish_to_eat {
    public static final int SPEED = 150;
    public static final int WIDTH = 0;
    private Texture texture;
    float x,y;
    public boolean remove = false;
    private int x_summon_location =random.nextInt(2);
    public void show(){
    }


    
    public Fish_to_eat(int i){
        int[] x_value_arr = {0, Gdx.graphics.getWidth()};
        x = x_value_arr[x_summon_location];
        y = random.nextFloat()*(Gdx.graphics.getHeight()-WIDTH);
        int fish_choose = random.nextInt(5);
        texture = new Texture("fish"+i+".png");

    }

    public void update(float deltaTime){
        if(x_summon_location == 0){
            x += SPEED*deltaTime;
            if(x>Gdx.graphics.getWidth())
                remove = true;
        }else{
            x-=SPEED*deltaTime;
            if(x<0)
                remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y,80,80);
    }
    }