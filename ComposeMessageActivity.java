package com.example.socialmediaapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ComposeMessageActivity extends AppCompatActivity {
    private EditText userIdEditText;
    private EditText messageEditText;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        userIdEditText = findViewById(R.id.userIdEditText);
        messageEditText = findViewById(R.id.messageEditText);
        Button sendButton = findViewById(R.id.sendButton);
        db = new MessageDatabaseHelper(this).getWritableDatabase();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdEditText.getText().toString();
                String message = messageEditText.getText().toString();

                if (userId.isEmpty() || message.isEmpty()) {
                    Toast.makeText(ComposeMessageActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("user_id", userId);
                    values.put("message", message);
                    db.insert("messages", null, values);
                    Toast.makeText(ComposeMessageActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
