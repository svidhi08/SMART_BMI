package com.example.smartbmi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMICalculationActivity extends AppCompatActivity {
    private EditText ageInput, weightInput;
    private SeekBar heightInput;
    private TextView heightDisplay;
    private RadioGroup genderGroup;
    private Button calculateButton;

    private int selectedHeight = 0;
    int minHeight = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);
        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        heightDisplay = findViewById(R.id.heightDisplay);
        genderGroup = findViewById(R.id.genderGroup);
        calculateButton = findViewById(R.id.calculateButton);
        heightInput.setMax(300);
        heightInput.setMin(minHeight);
        selectedHeight = minHeight;

        heightDisplay.setText(selectedHeight + " CM");
        heightInput.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedHeight = progress;
                heightDisplay.setText(progress + " CM");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        calculateButton.setOnClickListener(v -> {
            String ageStr = ageInput.getText().toString().trim();
            String weightStr = weightInput.getText().toString().trim();
            int selectedGenderId = genderGroup.getCheckedRadioButtonId();

            if (TextUtils.isEmpty(ageStr)) {
                Toast.makeText(this, "Please enter age", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(weightStr)) {
                Toast.makeText(this, "Please enter weight", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedHeight == 0) {
                Toast.makeText(this, "Please select height", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedGenderId == -1) {
                Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            int weight = Integer.parseInt(weightStr);
            float heightInMeters = selectedHeight / 100f;

            float bmi = weight / (heightInMeters * heightInMeters);

            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender.getText().toString();

            // Pass data to result screen
            Intent intent = new Intent(BMICalculationActivity.this, ResultActivity.class);
            intent.putExtra("BMI", bmi);
            intent.putExtra("GENDER", gender);
            intent.putExtra("AGE", age);
            intent.putExtra("HEIGHT",selectedHeight);
            intent.putExtra("WEIGHT",weight);
            startActivity(intent);
        });
    }
}
