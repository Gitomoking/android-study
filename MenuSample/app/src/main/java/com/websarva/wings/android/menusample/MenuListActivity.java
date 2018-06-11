package com.websarva.wings.android.menusample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
        // Register _lvMenu for context menu
        registerForContextMenu(_lvMenu);
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

    private List<Map<String, Object>> createCurryList() {
        // Prepare List object for Curry menu list
        List<Map<String, Object>> menuList = new ArrayList<>();
        // Prepare Map object for "ビーフカレー" and add it to menuList
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選スパイスをきかせた国産ビーフ100％のカレーです。");
        menuList.add(menu);
        // Prepare Map object for "ポークカレー" and add it to menuList
        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選スパイスをきかせた国産ポーク100%のカレーです。");
        menuList.add(menu);

        return menuList;
    }

    // ListItemClickListener class describes behavior when the list is tapped
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get data tapped
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            // Order
            order(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Get menu inflater
        MenuInflater inflater = getMenuInflater();
        // Inflate .xml file for option menu
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get id of selected menu
        int itemId = item.getItemId();
        // Branch by R value
        switch(itemId) {
            // Teishoku menu
            case R.id.menuListOptionTeishoku:
                // Create data of Teishoku
                _menuList = createTeishokuList();
                break;
            // Curry menu
            case R.id.menuListOptionCurry:
                // Create data of Curry
                _menuList = createCurryList();
                break;
        }

        // Create SimpleAdapter by selected menu datas
        SimpleAdapter adapter = new SimpleAdapter(MenuListActivity.this, _menuList, R.layout.row, FROM, TO);
        // Set adapter to _lvMenu
        _lvMenu.setAdapter(adapter);
        // super
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        // Call super
        super.onCreateContextMenu(menu, view, menuInfo);
        // Get menu inflater
        MenuInflater inflater = getMenuInflater();
        // Inflate .xml file for context menu
        inflater.inflate(R.menu.menu_context_menu_list, menu);
        // Set header title of context menu
        menu.setHeaderTitle(R.string.menu_list_context_header);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Get object that has info about view
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // Get position of list
        int listPosition = info.position;
        // Get map object of menu info
        Map<String, Object> menu = _menuList.get(listPosition);

        // Get menu id
        int itemId = item.getItemId();
        // Branch by R value
        switch(itemId) {
            // Desc menu
            case R.id.menuListContextDesc:
                // Get String
                String desc = (String) menu.get("desc");
                // Show Toast
                Toast.makeText(MenuListActivity.this, desc, Toast.LENGTH_LONG).show();
                break;
            // Order menu
            case R.id.menuListContextOrder:
                // Order
                order(menu);
                break;
        }
        // call super
        return super.onContextItemSelected(item);
    }

    private void order(Map<String, Object> menu) {
        // Get name and price
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");

        // Create Intent object
        Intent intent = new Intent(MenuListActivity.this, MenuThanksActivity.class);
        // Store data sent to second page
        intent.putExtra("menuName", menuName);
        intent.putExtra("menuPrice", menuPrice + "円");
        // Run second page
        startActivity(intent);
    }
}
