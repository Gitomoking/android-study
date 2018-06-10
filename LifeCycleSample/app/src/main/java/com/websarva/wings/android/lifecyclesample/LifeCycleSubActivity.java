package com.websarva.wings.android.lifecyclesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LifeCycleSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_sub);
        setContentView(R.layout.activity_life_cycle_sub);
    }



    @Override
    public void onStart() {
        Log.i("LifeCycleSample", "Sub onStart() called.");
        super.onStart();
    }

    @Override
    public void onRestart() {
        Log.i("LifeCycleSample", "Sub onRestart() called.");
        super.onRestart();
    }

    @Override
    public void onResume() {
        Log.i("LifeCycleSample", "Sub onResume() called.");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("LifeCycleSample", "Sub onPause() called.");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("LifeCycleSample", "Sub onStop() called.");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("LifeCycleSample", "Sub onDestroy() called.");
        super.onDestroy();
    }

    // onButtonClick describes behavior when previous button tapped
    public void onButtonClick(View view) {
        // Finish this activity
        finish();
    }
}
