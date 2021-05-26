package com.example.postpc_ex7;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateOrder extends AppCompatActivity {

    EditText numOfpickles;
    EditText commentText;
    EditText costumerName;
    CheckBox  ishummus;
    CheckBox istahini;
    Button addOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order_activity);

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


            Intent editActivity = new Intent(this, EditOrder.class);
            this.startActivity(editActivity);
        });
    }

}




class InputFilterMinMax implements InputFilter {
    private int minimumValue;
    private int maximumValue;

    public InputFilterMinMax(int minimumValue, int maximumValue) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));
            if (isInRange(minimumValue, maximumValue, input))
                return null;
        }
        catch (NumberFormatException nfe) {
        }
        return "";
    }
}
