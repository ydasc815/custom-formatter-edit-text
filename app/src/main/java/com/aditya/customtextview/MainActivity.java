package com.aditya.customtextview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.aditya.customtextview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnMaterial
                .setOnClickListener(view -> Toast.makeText(getApplicationContext(),
                        binding.etTotalInvoiceAmount.getText(0), Toast.LENGTH_LONG).show());
    }
}