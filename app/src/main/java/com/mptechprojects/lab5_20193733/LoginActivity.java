package com.mptechprojects.lab5_20193733;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mptechprojects.lab5_20193733.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.buttonLogin.setOnClickListener(v -> {
            String userCode = binding.editTextCode.getText().toString();
            if (!userCode.isEmpty()){
                binding.editTextCode.getText().clear();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userCode", userCode);
                startActivity(intent);
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Por favor ingrese su código primero");
                builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                builder.show();
            }
        });
    }
}