package com.mygdx.game.Fish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.math.MathUtils.random;

public class Fish_to_eat {
    public static final int SPEED = 150;
    public static final int WIDTH = 0;
    public static final float size = 80;
    public float level = 1;
    private Texture texture;
    public float x,y;
    public boolean remove = false;
    private int x_summon_location =random.nextInt(2);
    public void show(){
    }


    
    public Fish_to_eat(int i){
        int[] x_value_arr = {0, Gdx.graphics.getWidth()};
        this.x = x_value_arr[x_summon_location];
        this.y = random.nextFloat()*(Gdx.graphics.getHeight()-WIDTH);
        this.texture = new Texture("fish"+i+"."+x_summon_location+".png");
        this.level += 0.15*random.nextInt(5);
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
        batch.draw(texture,x-size/2,y-size/2,size*level,size*level);
    }
    }