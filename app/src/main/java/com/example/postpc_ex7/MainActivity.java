package com.example.postpc_ex7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MyApp myApp;
    String orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.MainActivityButton);
        myApp = new MyApp(this);
        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.orderId=myApp.orderId;


        if(!this.orderId.equals("")){
            db.collection("orders").document(this.orderId).get().addOnSuccessListener(documentSnapshot -> {
                String OrderStatus = documentSnapshot.getString("status");

                if(OrderStatus==null){
                    Intent createOrderIntent = new Intent(this, CreateOrder.class);
                    this.startActivity(createOrderIntent);
                }else{
                    switch (OrderStatus) {
                        case "waiting":
                            Intent editActivityIntent = new Intent(this, EditOrder.class);
                            startActivity(editActivityIntent);
                            break;
                        case "in-progress":
                            Intent inProgressIntent  = new Intent(this, InProgress.class);
                            startActivity(inProgressIntent);
                            break;
                        case "ready":
                            Intent readyIntent  = new Intent(this, ReadyOrder.class);
                            startActivity(readyIntent);
                            break;
                        default:
                            Intent createOrderIntent = new Intent(this, CreateOrder.class);
                            this.startActivity(createOrderIntent);
                    }
                }
            });
        }

        button.setOnClickListener(view -> {
            Intent newOrderIntent = new Intent(this, CreateOrder.class);
            this.startActivity(newOrderIntent);
        });



    }
}