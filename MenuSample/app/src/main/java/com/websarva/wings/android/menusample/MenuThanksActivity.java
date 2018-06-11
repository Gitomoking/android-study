package com.websarva.wings.android.menusample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

        // Get action bar
        ActionBar actionBar = getSupportActionBar();
        // Set back menu to enable
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get menu id
        int itemId = item.getItemId();
        // Finish activity when back menu selected
        if(itemId == android.R.id.home) {
            finish();
        }

        // super
        return super.onOptionsItemSelected(item);
    }
}
