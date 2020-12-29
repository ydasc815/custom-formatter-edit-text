package com.aditya.customtextview;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import java.math.BigInteger;
import java.text.DecimalFormat;

/*
* Created by Aditya on 12-12-2020
* */
public class CustomEditText extends TextInputEditText implements TextWatcher {
    private static final String TAG = "CustomEditText";
    private final EditText editText = findViewById(this.getId());
    public CustomEditText(@NonNull Context context) {
        super(context);
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.editText.addTextChangedListener(this);
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        editText.removeTextChangedListener(this);
        Log.d(TAG, "afterTextChanged: " + this.getHint().toString());
        try {
            String str = editable.toString();
            if(this.getHint().toString().equals("Total Amount to be paid")) {
                editText.setText(customFormatter(str));
            } else {
                editText.setText(str);
            }
            editText.setSelection(editText.getText().length());

        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        editText.addTextChangedListener(this);
    }

    @Nullable
    @Override
    public Editable getText() {
        return super.getText();
    }

    public Editable getAmount() {
        Log.d(TAG, "Overloaded method getText() accessed with code ");
        String s = super.getText().toString();
        s = s.replaceAll(",", "");
        return new SpannableStringBuilder(s);
    }

    public String customFormatter(String str) {
        String integralPart = "", fractionalPart = "";
        str = str.replaceAll(",", "");
        if(str.contains(".")) {
            fractionalPart = str.substring(str.indexOf('.'));
            integralPart = str.substring(0, str.indexOf('.'));
        } else integralPart = str;
        Log.d(TAG, "customFormatter: " + integralPart.length());
        if(integralPart.length() < 4) return str;
        else {
            StringBuffer sb = new StringBuffer(integralPart);
            sb.insert(integralPart.length() - 3, ",");
            int i = integralPart.length() - 3;
            while (i >= 0) {
                if (i - 3 >= 0) {
                    i -= 2;
                    sb.insert(i, ",");
                } else break;
            }
            return sb.toString() + fractionalPart;
        }
    }
}
