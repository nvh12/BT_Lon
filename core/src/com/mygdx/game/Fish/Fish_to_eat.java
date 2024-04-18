package com.mygdx.game.Fish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.math.MathUtils.random;

public class Fish_to_eat {
    public static final int SPEED = 1200;
    public static final float default_size = 80, size_increase = 0.25f;
    public float level, size;
    private Texture texture;
    public float x,y;
    public boolean remove = false;
    private int x_summon_location =random.nextInt(2);
    public void show(){
    }


    
    public Fish_to_eat(int i){
        int[] x_value_arr = {0, Gdx.graphics.getWidth()};
        this.x = x_value_arr[x_summon_location];
        this.y = random.nextFloat()*((float) (Gdx.graphics.getHeight() * 6.25) /10)+ (float) Gdx.graphics.getHeight() /10;
        this.texture = new Texture("Fish"+i+"."+x_summon_location+".png");
        this.level = i;
        this.size = (float)(default_size*(1+(level-1)*size_increase));
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
        batch.draw(texture,x-size/2,y-size/2,size,size);
    }
    }