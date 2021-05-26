package com.example.postpc_ex7;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order_activity);

        EditText picklesText = findViewById(R.id.editTextNumber);
        picklesText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10)});
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
