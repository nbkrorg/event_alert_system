package com.example.eas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button addeve = findViewById(R.id.add);
        addeve.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            startActivity(intent);
        });
        Button editeve = findViewById(R.id.editeve);
        editeve.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
            startActivity(intent);
        });
        Button del = findViewById(R.id.del);
        del.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity8.class);
            startActivity(intent);
        });
        Button his = findViewById(R.id.his);
        his.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity9.class);
            startActivity(intent);
        });

    }
}