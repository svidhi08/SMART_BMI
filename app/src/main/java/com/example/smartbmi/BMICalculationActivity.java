package com.example.smartbmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Locale;

public class BMICalculationActivity extends AppCompatActivity {

    private EditText etAge, etWeight;
    private SeekBar heightSeekBar;
    private TextView tvHeightValue, btnHistory;
    private Button btnCalculate;
    private int currentHeight = 157;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);

        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight);
        heightSeekBar = findViewById(R.id.heightSeekBar);
        tvHeightValue = findViewById(R.id.tvHeightValue);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnHistory = findViewById(R.id.btnHistory);

        loadLastSession();

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentHeight = progress;
                tvHeightValue.setText(progress + " CM");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnCalculate.setOnClickListener(v -> performCalculation());
        btnHistory.setOnClickListener(v -> showHistory());
    }

    private void performCalculation() {
        String weightStr = etWeight.getText().toString();
        String ageStr = etAge.getText().toString();

        if (weightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        float weight = Float.parseFloat(weightStr);
        float heightM = currentHeight / 100.0f;
        float bmi = weight / (heightM * heightM);

        // Save inputs so the user doesn't have to re-type next time
        saveSession(ageStr, weightStr, currentHeight);

        // Launch ResultActivity - WE DO NOT SAVE TO HISTORY HERE ANYMORE
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("BMI", bmi);
        intent.putExtra("HEIGHT", currentHeight);
        intent.putExtra("WEIGHT", (int) weight);
        intent.putExtra("AGE", Integer.parseInt(ageStr));
        intent.putExtra("GENDER", "Not Specified");
        startActivity(intent);
    }

    private void saveSession(String a, String w, int h) {
        getSharedPreferences("BMI_STORE", MODE_PRIVATE).edit()
                .putString("age", a).putString("weight", w).putInt("height", h).apply();
    }

    private void loadLastSession() {
        SharedPreferences p = getSharedPreferences("BMI_STORE", MODE_PRIVATE);
        etAge.setText(p.getString("age", ""));
        etWeight.setText(p.getString("weight", ""));
        currentHeight = p.getInt("height", 157);
        heightSeekBar.setProgress(currentHeight);
        tvHeightValue.setText(currentHeight + " CM");
    }

    private void showHistory() {
        SharedPreferences p = getSharedPreferences("BMI_STORE", MODE_PRIVATE);
        ArrayList<BmiHistoryItem> list = new Gson().fromJson(p.getString("h_list", ""),
                new TypeToken<ArrayList<BmiHistoryItem>>(){}.getType());

        if (list == null || list.isEmpty()) {
            Toast.makeText(this, "No history found yet!", Toast.LENGTH_SHORT).show();
            return;
        }

        android.view.View dialogView = getLayoutInflater().inflate(R.layout.dialog_history, null);
        TextView content = dialogView.findViewById(R.id.historyContent);
        Button closeBtn = dialogView.findViewById(R.id.btnCloseHistory);

        StringBuilder sb = new StringBuilder();
        for (BmiHistoryItem i : list) {
            sb.append(i.getDisplayString()).append("\n");
            sb.append("────────────────────\n");
        }
        content.setText(sb.toString());

        AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        closeBtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}