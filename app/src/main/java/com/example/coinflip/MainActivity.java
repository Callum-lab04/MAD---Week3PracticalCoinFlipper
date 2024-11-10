package com.example.coinflip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public Random random = new Random();
    ImageView coin;
    Button flipBtn, btnHeads, btnTails;
    TextView textView;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coin = findViewById(R.id.imageView);
        flipBtn = findViewById(R.id.flip_btn);
        btnHeads = findViewById(R.id.btn_heads);
        btnTails = findViewById(R.id.btn_tails);
        textView = findViewById(R.id.textView);

        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCoin();
            }
        });

        btnHeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePrediction("heads");
            }
        });

        btnTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePrediction("tails");
            }
        });
    }

    public void flipCoin() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coin.setImageResource(random.nextFloat() > 0.5f ? R.drawable.pound_tails : R.drawable.pound_heads);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);
    }

    public void makePrediction(String prediction) {

        boolean isHeads = random.nextFloat() > 0.5f;

        if (isHeads) {
            coin.setImageResource(R.drawable.pound_heads);
        } else {
            coin.setImageResource(R.drawable.pound_tails);
        }

        if ((prediction.equals("heads") && isHeads) || (prediction.equals("tails") && !isHeads)) {
            score++;
        } else {
            score--;
        }

        textView.setText("Score: " + score);
    }
}
