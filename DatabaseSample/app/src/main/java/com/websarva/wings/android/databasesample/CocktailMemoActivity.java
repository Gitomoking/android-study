package com.websarva.wings.android.databasesample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CocktailMemoActivity extends AppCompatActivity {

    int _cocktailId = -1;
    String _cocktailName = "";
    TextView _tvCocktailName;
    Button _btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_memo);

        _tvCocktailName = findViewById(R.id.tvCocktailName);
        _btnSave = findViewById(R.id.btnSave);
        ListView lvCocktail = findViewById(R.id.lvCocktail);
        lvCocktail.setOnItemClickListener(new ListItemClickListener());
    }

    public void onSaveButtonClick(View view) {

        // Get input note
        EditText etNote = findViewById(R.id.etNote);
        String note = etNote.getText().toString();

        // Create DatabaseHelper object
        DatabaseHelper helper = new DatabaseHelper(CocktailMemoActivity.this);
        // Get Database Connector
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            // Delete old version of selected cocktail
            String sqlDelete = "DELETE FROM cocktailmemo WHERE _id = ?";
            SQLiteStatement stmt = db.compileStatement(sqlDelete);
            stmt.bindLong(1, _cocktailId);
            stmt.executeUpdateDelete();

            // Insert new version of selected cocktail
            String sqlInsert = "INSERT INTO cocktailmemo (_id, name, note) VALUES (?, ?, ?)";
            stmt = db.compileStatement(sqlInsert);
            stmt.bindLong(1, _cocktailId);
            stmt.bindString(2, _cocktailName);
            stmt.bindString(3, note);
            stmt.executeInsert();
        }
        finally {
            // Close database connector
            db.close();
        }

        _tvCocktailName.setText(getString(R.string.tv_name));
        etNote.setText("");
        _btnSave.setEnabled(false);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            _cocktailId = position;
            _cocktailName = (String) parent.getItemAtPosition(position);
            _tvCocktailName.setText(_cocktailName);
            _btnSave.setEnabled(true);

            // Create DatabaseHelper object
            DatabaseHelper helper = new DatabaseHelper((CocktailMemoActivity.this));
            // Get Database connector
            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                // Get selected data from db
                String sql = "SELECT * FROM cocktailmemo WHERE _id = " + _cocktailId;
                Cursor cursor = db.rawQuery(sql, null);

                // Prepare variable that will have value from db
                String note = "";
                while(cursor.moveToNext()) {
                    // Get Index value
                    int idxNote = cursor.getColumnIndex("note");
                    // Get note
                    note = cursor.getString(idxNote);
                }
                // Reflect note from database to EditText
                EditText etNote = findViewById(R.id.etNote);
                etNote.setText(note);
            }
            finally {
                // Close Database connector
                db.close();
            }
        }
    }
}
