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
        try {
            String str = editable.toString();
            if(str.contains(",")) str = str.replaceAll(",", "");
            DecimalFormat formatter = new DecimalFormat("#,##,###");
            String fS = formatter.format(new BigInteger(str));
            editText.setText(fS);
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

    public Editable getText(int code) {
        Log.d(TAG, "Overloaded method getText() accessed with code " + code);
        String s = super.getText().toString();
        s = s.replaceAll(",", "");
        return new SpannableStringBuilder(s);
    }

}
