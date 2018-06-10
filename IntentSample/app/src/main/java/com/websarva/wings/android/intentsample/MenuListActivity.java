package com.websarva.wings.android.intentsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        // get ListView
        ListView lvMenu = findViewById(R.id.lvMenu);
        // Prepare List object used in SimpleAdapter
        List<Map<String, String>> menuList = new ArrayList<>();
        // Prepare "からあげ定食" Map object and add data to menuList
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "からあげ定食");
        menu.put("price", "800円");
        menuList.add(menu);
        // Prepare "ハンバーグ定食" Map object and add data to menuList
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        // Prepare arguments of SimpleAdapter
        String[] from = {"name", "price"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        // Create SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(MenuListActivity.this, menuList,
                android.R.layout.simple_list_item_2, from, to);
        // Set adapter to ListView
        lvMenu.setAdapter(adapter);

        // Set onClickListener to ListView
        lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    // ListItemClickListener class describes behavior when the list is tapped
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get data tapped
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            // Get Name and Price
            String menuName = item.get("name");
            String menuPrice = item.get("price");
            // Create Intent object
            Intent intent = new Intent(MenuListActivity.this, MenuThanksActivity.class);
            // Store data sent to second display
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);
            // Run second display
            startActivity(intent);
        }
    }
}
