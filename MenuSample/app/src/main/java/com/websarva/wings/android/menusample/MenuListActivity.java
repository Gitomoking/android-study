package com.websarva.wings.android.menusample;

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

    // ListView field
    private ListView _lvMenu;
    // ListData showed in ListView
    private List<Map<String, Object>> _menuList;
    // arguments for SimpleAdapter field
    private static final String[] FROM = {"name", "price"};
    private static final int[] TO = {R.id.tvMenuName, R.id.tvMenuPrice};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        // Get lvMenu
        _lvMenu = findViewById(R.id.lvMenu);
        // Get menuList
        _menuList = createTeishokuList();
        // Create SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(MenuListActivity.this, _menuList, R.layout.row, FROM, TO);
        // Set adapter to _lvMenu
        _lvMenu.setAdapter(adapter);
        // Set listener to _lvMenu
        _lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    private List<Map<String, Object>> createTeishokuList() {
        // Prepare List object for MenuList
        List<Map<String, Object>> menuList = new ArrayList<>();
        // Prepare Map object for "からあげ定食" and add it to menuList
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "からあげ定食");
        menu.put("price", 800);
        menu.put("desc", "若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);
        // Prepare Map object for "ハンバーグ定食" and add it to menuList
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        return menuList;
    }

    // ListItemClickListener class describes behavior when the list is tapped
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get data tapped
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            // Get Name and Price
            String menuName = (String) item.get("name");
            Integer menuPrice = (Integer) item.get("price");
            // Create Intent object
            Intent intent = new Intent(MenuListActivity.this, MenuThanksActivity.class);
            // Store data sent to second display
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice + "円");
            // Run second display
            startActivity(intent);
        }
    }
}
