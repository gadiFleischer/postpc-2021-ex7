package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditOrder extends AppCompatActivity {
    OrderModel order;
    EditText numOfpickles;
    EditText commentText;
    EditText costumerName;
    CheckBox ishummus;
    CheckBox istahini;
    Button saveButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order_activity);

        Intent orderFromCreateIntent=getIntent();
        this.order = (OrderModel) orderFromCreateIntent.getSerializableExtra("order");
        //get views
        numOfpickles = findViewById(R.id.picklesNumberEdit);
        commentText = findViewById(R.id.commentEdit);
        costumerName = findViewById(R.id.costumerNameEdit);
        ishummus = findViewById(R.id.checkBoxHummusEdit);
        istahini = findViewById(R.id.checkBoxTahinaEdit);
        numOfpickles.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)});
        saveButton = findViewById(R.id.buttonSave);
        deleteButton = findViewById(R.id.buttonDelete);
        //set views
        numOfpickles.setText(String.valueOf(order.pickles));
        commentText.setText(order.comment);
        costumerName.setText(order.costumerName);
        ishummus.setChecked(order.hummus);
        istahini.setChecked(order.tahini);

        saveButton.setOnClickListener(view -> {
           //edit in db
            Toast toast = Toast.makeText(this,"your order has been saved", Toast.LENGTH_SHORT);
            toast.show();
            Intent MainActivityIntent = new Intent(this, MainActivity.class); //delete this
            this.startActivity(MainActivityIntent); //delete this
        });
        deleteButton.setOnClickListener(view -> {
            //delete in db
            Toast toast = Toast.makeText(this,"your order has been Deleted", Toast.LENGTH_LONG);
            toast.show();
            Intent MainActivityIntent = new Intent(this, MainActivity.class);
            this.startActivity(MainActivityIntent);
        });

    }

}
