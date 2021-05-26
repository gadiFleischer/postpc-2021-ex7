package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadyOrder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_activity);

        Button gotItButton = findViewById(R.id.ButtonGotIt);
        //get order and change its status to in-progress
        gotItButton.setOnClickListener(view -> {
            //change status to done in db to done in db
            Toast toast = Toast.makeText(this,"bon appetit:)", Toast.LENGTH_LONG);
            toast.show();
            Intent mainActivityIntent = new Intent(this, MainActivity.class); //delete this
            this.startActivity(mainActivityIntent); //delete this
        });

    }
}
