package com.websarva.wings.android.menusample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);

        // Get Intent object
        Intent intent = getIntent();
        // Get datas from ListView
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");
        // Get TextView that shows name and price
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        // Show name and price
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);
    }

    // onBackButtonClick runs when back button is tapped
    public void onBackButtonClick(View view) {
        finish();
    }
}
