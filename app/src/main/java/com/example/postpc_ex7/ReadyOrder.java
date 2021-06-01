package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class ReadyOrder extends AppCompatActivity {

    MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_activity);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.myApp= new MyApp(this);

        Button gotItButton = findViewById(R.id.ButtonGotIt);
        gotItButton.setOnClickListener(view -> {
            db.collection("orders").document(myApp.orderId).update("status", "done");
            Toast toast = Toast.makeText(this,"bon appetit:)", Toast.LENGTH_LONG);
            toast.show();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            this.startActivity(mainActivityIntent);
            finish();
        });

    }

}
