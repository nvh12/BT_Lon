package com.mygdx.game.ScoreBar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ScoreBar {
    private float x, y; // Tọa độ x, y của thanh điểm
    private float width, height; // Chiều rộng và chiều cao của thanh điểm
    private float currentScore; // Điểm hiện tại của người chơi
    private static final float MAX_SCORE = 100.0f;

    public ScoreBar(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentScore = 0;
    }

    public void update(int score) {
        currentScore = score;
        if(currentScore > MAX_SCORE)
            currentScore = MAX_SCORE;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Vẽ background 1 cho thanh điểm
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(x,y,width,height);

        //Vẽ vạch level up cho thanh điểm
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.rect(x+(float)(0.09*width),y,(float)(0.01*width),height);
        shapeRenderer.rect(x+(float)(0.39*width),y,(float)(0.01*width),height);

        //Vẽ thanh điểm
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, width * (currentScore / MAX_SCORE), height);
        shapeRenderer.end();
    }

    public void dispose() {
        // Giải phóng tài nguyên khi cần
    }
}
