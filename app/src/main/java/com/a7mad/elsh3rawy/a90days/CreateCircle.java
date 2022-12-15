package com.a7mad.elsh3rawy.a90days;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCircle extends AppCompatActivity {
    EditText newTitle;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);
        newTitle = findViewById(R.id.newTitle);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newTitle.getText().toString().equals("")) {
                    setResult(11, new Intent().putExtra("Title", newTitle.getText().toString()));
                    finish();
                }
            }
        });
    }
}