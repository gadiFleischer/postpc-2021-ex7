package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class CreateOrder extends AppCompatActivity {

    EditText numOfpickles;
    EditText commentText;
    EditText costumerName;
    CheckBox  ishummus;
    CheckBox istahini;
    Button addOrderButton;
    MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order_activity);

        this.myApp= new MyApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        numOfpickles = findViewById(R.id.editTextNumber);
        commentText = findViewById(R.id.editTextTextPersonName);
        costumerName = findViewById(R.id.costumerNameEdit);
        ishummus = findViewById(R.id.checkBoxHummus);
        istahini = findViewById(R.id.checkBoxTahina);
        numOfpickles.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)});
        addOrderButton = findViewById(R.id.buttonAddOrder);

        addOrderButton.setOnClickListener(view -> {
            int pickles = 0;
            if(!numOfpickles.getText().toString().equals("")){
                pickles=Integer.parseInt(numOfpickles.getText().toString());
            }
            OrderModel order = new OrderModel(costumerName.getText().toString(),
                    pickles,ishummus.isChecked(),istahini.isChecked(),commentText.getText().toString());
            db.collection("orders").document(order.id).set(order);
            this.myApp.saveOrderId(order.id);
            Intent editActivity = new Intent(this, EditOrder.class);
            editActivity.putExtra("order",order);
            this.startActivity(editActivity);
        });
    }

}




