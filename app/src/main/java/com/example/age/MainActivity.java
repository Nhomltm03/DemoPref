package com.example.age;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonSave;
    EditText editTextAge;
    ViewGroup parrent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.buttonSave = this.findViewById(R.id.bt_save);
        this.editTextAge = this.findViewById(R.id.ed_age);
        this.parrent = this.findViewById(R.id.parent);
        this.sharedPreferences = this.getSharedPreferences("configure", MODE_PRIVATE);
        int age = this.sharedPreferences.getInt("age", 0);

        if (age <= 10) {
            this.parrent.setBackgroundResource(R.drawable.bg_chirld);
        } else if (age <= 17) {
            this.parrent.setBackgroundResource(R.drawable.bg_medium);
        }else {
            this.parrent.setBackgroundResource(R.drawable.bg_old);
        }

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = this.sharedPreferences.edit();
        this.buttonSave.setOnClickListener(v -> {
            Editable text = this.editTextAge.getText();
            if (text == null || TextUtils.isEmpty(text.toString())) {
                return;
            }

            editor.putInt("age", Integer.parseInt(text.toString())).apply();
            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        });
    }
}