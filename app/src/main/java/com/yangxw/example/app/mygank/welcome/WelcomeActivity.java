package com.yangxw.example.app.mygank.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.yangxw.example.app.mygank.R;
import com.yangxw.example.app.mygank.home.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        AlphaAnimation animation = new AlphaAnimation(0.2f, 1.0f);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toNextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        getWindow().getDecorView().findViewById(android.R.id.content).startAnimation(animation);

    }

    private void toNextActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
