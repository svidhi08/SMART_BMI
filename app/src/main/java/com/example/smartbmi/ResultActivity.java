package com.example.smartbmi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView bmiValueTextView, bmiStatusTextView;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Bind views
        bmiValueTextView = findViewById(R.id.bmiValueTextView);
        bmiStatusTextView = findViewById(R.id.bmiStatusTextView);
        saveButton = findViewById(R.id.saveButton);

        float bmi = getIntent().getFloatExtra("BMI", 0);
        int ht=getIntent().getIntExtra("HEIGHT",0);
        String gender=getIntent().getStringExtra("GENDER");
        int wt=getIntent().getIntExtra("WEIGHT",0);
        int age=getIntent().getIntExtra("AGE",0);

        bmiValueTextView.setText(String.format("%.2f", bmi));
        bmiStatusTextView.setText(getBmiCategory(bmi));


//        adb shell==>run-as com.example.smartbmi==>cd shared_prefs==>cat BMIData.xml
        saveButton.setOnClickListener(v -> {
            SharedPreferences sharedPref = getSharedPreferences("BMIData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putFloat("LatestBMI", bmi);
            editor.putFloat("HEIGHT",ht);
            editor.putInt("WEIGHT",wt);
            editor.putInt("AGE",age);
            editor.putString("GENDER",gender);
            editor.apply();
            Toast.makeText(this, "BMI saved!", Toast.LENGTH_SHORT).show();
        });
    }

    private String getBmiCategory(float bmi) {
        if (bmi < 18.5f) return "UNDERWEIGHT 🔵";
        else if (bmi < 25f) return "NORMAL BMI 🟢";
        else if (bmi < 30f) return "OVERWEIGHT 🟡";
        else return "OBESITY 🔴";
    }
}
