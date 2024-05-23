package com.example.socialmediaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MessageAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Button composeButton = findViewById(R.id.composeButton);
        db = new MessageDatabaseHelper(this).getReadableDatabase();

        Cursor cursor = db.query("messages", null, null, null, null, null, null);
        adapter = new MessageAdapter(this, cursor);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                String userId = cursor.getString(cursor.getColumnIndex("user_id"));
                Intent intent = new Intent(MainActivity.this, MessageDetailActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });

        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComposeMessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
