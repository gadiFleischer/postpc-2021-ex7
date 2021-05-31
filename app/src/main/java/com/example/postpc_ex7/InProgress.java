package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class InProgress extends AppCompatActivity {

    MyApp myApp;
    ListenerRegistration changesFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_progress_activity);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.myApp= new MyApp(this);
        myApp.loadOrderId();
        String id = myApp.orderId;

        changesFromDB = db.collection("orders").document(id)
                .addSnapshotListener((value, error) -> {
                    if (error == null && value != null) {
                        String status = value.getString("status");
                        if (status != null && status.equals("ready"))
                        {
                            Intent readyIntent = new Intent(this, ReadyOrder.class);
                            this.startActivity(readyIntent);
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        changesFromDB.remove();
    }
}
