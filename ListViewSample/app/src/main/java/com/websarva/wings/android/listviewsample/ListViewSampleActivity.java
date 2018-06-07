package com.websarva.wings.android.listviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);

        // Get ListView object
        ListView lvMenu = findViewById(R.id.lvMenu);
        /* Set Listener to ListView */
        lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    // Called when the list is tapped
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get the menu tapped
            String item = (String) parent.getItemAtPosition(position);
            // Create string displayed on Toast
            String show = "あなたが選んだ定食：" + item;
            // Display Toast
            Toast.makeText(ListViewSampleActivity.this, show, Toast.LENGTH_LONG).show();
        }
    }
}
