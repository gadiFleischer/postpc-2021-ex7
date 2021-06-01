package com.example.postpc_ex7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    String orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.orderId=new MyApp(this).orderId;

        if(!this.orderId.equals("")){
            db.collection("orders").document(this.orderId).get().addOnSuccessListener(documentSnapshot -> {
                String OrderStatus = documentSnapshot.getString("status");

                if(OrderStatus==null){
                    Intent createOrderIntent = new Intent(this, CreateOrder.class);
                    this.startActivity(createOrderIntent);
                    finish();
                }else{
                    switch (OrderStatus) {
                        case "waiting":
                            Intent editActivityIntent = new Intent(this, EditOrder.class);
                            startActivity(editActivityIntent);
                            finish();
                            break;
                        case "in-progress":
                            Intent inProgressIntent  = new Intent(this, InProgress.class);
                            startActivity(inProgressIntent);
                            finish();
                            break;
                        case "ready":
                            Intent readyIntent  = new Intent(this, ReadyOrder.class);
                            startActivity(readyIntent);
                            finish();
                            break;
                        default:
                            Intent createOrderIntent = new Intent(this, CreateOrder.class);
                            this.startActivity(createOrderIntent);
                            finish();
                    }
                }
            });
        }
    }
}