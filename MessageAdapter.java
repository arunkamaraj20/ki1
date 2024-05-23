package com.example.socialmediaapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MessageAdapter extends CursorAdapter {
    public MessageAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView userIdView = view.findViewById(R.id.userId);
        TextView messageView = view.findViewById(R.id.message);

        String userId = cursor.getString(cursor.getColumnIndexOrThrow("user_id"));
        String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));

        userIdView.setText(userId);
        messageView.setText(message);
    }
}
