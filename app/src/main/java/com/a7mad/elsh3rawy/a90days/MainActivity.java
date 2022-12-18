package com.a7mad.elsh3rawy.a90days;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static String circles;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    static ArrayList<Circle> arrayList;
    static RecAdapter recAdapter;
    SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    static ArrayList<String> cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        arrayList = new ArrayList<>();
        circles = "";
        if (circles.equals("#")) {
            circles = "";
        }
        cs = new ArrayList();
        preferences = getPreferences(MODE_PRIVATE);
        editor = preferences.edit();
        circles = preferences.getString("circles", "");
        if (!circles.equals("")) {
            Collections.addAll(cs, circles.split("#"));
            String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            for (int i = 0; i < cs.size(); i++) {

                arrayList.add(new Circle(cs.get(i), cs.get(++i), currentDate));
            }


        }


        recAdapter = new RecAdapter(this, arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CreateCircle.class);
                startActivityForResult(intent, 11);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            Toast.makeText(this, "" + data.getExtras().get("Title"), Toast.LENGTH_LONG).show();
            String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            if (circles.equals("#")) {
                circles = "";
            }
            circles = circles + data.getExtras().get("Title").toString() + "#" + currentDate + "#";
            editor.putString("circles", circles);
            editor.commit();

            arrayList.add(new Circle(data.getExtras().get("Title").toString(), currentDate, currentDate));
            recAdapter.notifyDataSetChanged();
            cs.removeAll(cs);
            Collections.addAll(cs, circles.split("#"));
        }
    }
}