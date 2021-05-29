package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditOrder extends AppCompatActivity {
    OrderModel order;
    EditText numOfpickles;
    EditText commentText;
    TextView costumerName;
    CheckBox ishummus;
    CheckBox istahini;
    Button saveButton;
    Button deleteButton;
    MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order_activity);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        this.myApp= new MyApp(this);
        myApp.loadOrderId();

        //get views
        costumerName = findViewById(R.id.textCostumerName);
        numOfpickles = findViewById(R.id.picklesNumberEdit);
        ishummus = findViewById(R.id.checkBoxHummusEdit);
        istahini = findViewById(R.id.checkBoxTahinaEdit);
        commentText = findViewById(R.id.commentEdit);
        numOfpickles.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)});
        saveButton = findViewById(R.id.buttonSave);
        deleteButton = findViewById(R.id.buttonDelete);
        String id = myApp.orderId;
        db.collection("orders").document(id).get().
        addOnSuccessListener(documentSnapshot -> {
            String CostumerName = documentSnapshot.getString("costumerName");
            Number pickles = (Number)documentSnapshot.get("pickles");
            Boolean hummus = documentSnapshot.getBoolean("hummus");
            Boolean tahini = documentSnapshot.getBoolean("tahini");
            String comment = documentSnapshot.getString("comment");

            numOfpickles.setText(String.valueOf(pickles));
            commentText.setText(comment);
            String nameText = "Thank you:"+CostumerName+" for ordering";
            costumerName.setText(nameText);
            ishummus.setChecked(hummus);
            istahini.setChecked(tahini);

        });


        saveButton.setOnClickListener(view -> {
           //edit in db
            Toast toast = Toast.makeText(this,"your order has been saved", Toast.LENGTH_SHORT);
            toast.show();

//            Intent temp = new Intent(this, ReadyOrder.class); //delete this
//            this.startActivity(temp); //delete this
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
