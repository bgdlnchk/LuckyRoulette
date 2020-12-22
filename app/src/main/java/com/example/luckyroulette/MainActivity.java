package com.example.luckyroulette;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView spinningResult;
    private ImageView roulette;
    private Random random;
    private int degree = 0;
    private static final float FACTOR = 4.86f;
    private final String[] numbers = {"32 RED","15 BLACK","19 RED","4 BLACK",
            "21 RED","2 BLACK","25 RED","17 BLACK", "34 RED",
            "6 BLACK","27 RED","13 BLACK","36 RED","11 BLACK","30 RED",
            "8 BLACK","23 RED","10 BLACK","5 RED","24 BLACK","16 RED","33 BLACK",
            "1 RED","20 BLACK","14 RED","31 BLACK","9 RED","22 BLACK","18 RED",
            "29 BLACK","7 RED","28 BLACK","12 RED","35 BLACK","3 RED","26 BLACK","0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void init () {
        spinningResult = findViewById(R.id.spinningResult);
        roulette = findViewById(R.id.roulette);
        random = new Random();
    }

    public void startSpinning(View view) {
        int oldDegree = degree % 360;
        degree = random.nextInt(3600) + 720;
        RotateAnimation rotateAnimation = new RotateAnimation(oldDegree, degree, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                spinningResult.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                spinningResult.setText(getResult(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        roulette.startAnimation(rotateAnimation);
    }

    private String getResult(int degree){
        String text = "";
        int factorX = 1;
        int factorY = 3;
        for (int i = 0; i < 37; i++){
            if(degree >= (FACTOR * factorX) && degree < (FACTOR * factorY)) {
                text = numbers[i];
            }

            factorX += 2;
            factorY += 2;
        }

        if(degree >= FACTOR * 73 && degree < 360 || degree >= 0 && degree < FACTOR * 1) {
            text = numbers[numbers.length - 1];
        }

        return text;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}