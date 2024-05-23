package com.example.socialmediaapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MessageDetailActivity extends AppCompatActivity {
    private ListView listView;
    private MessageAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        listView = findViewById(R.id.listView);
        db = new MessageDatabaseHelper(this).getReadableDatabase();

        String userId = getIntent().getStringExtra("user_id");
        Cursor cursor = db.query("messages", null, "user_id=?", new String[] { userId }, null, null, null);
        adapter = new MessageAdapter(this, cursor);
        listView.setAdapter(adapter);
    }
}
