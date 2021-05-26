package com.example.postpc_ex7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.MainActivityButton);
        button.setOnClickListener(view -> {
            Intent newOrderIntent = new Intent(this, CreateOrder.class);
            this.startActivity(newOrderIntent);
        });

    }
}