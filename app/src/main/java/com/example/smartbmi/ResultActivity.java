package com.example.smartbmi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView bmiValueTextView, bmiStatusTextView;
    private Button saveButton;
    private float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bmiValueTextView = findViewById(R.id.bmiValueTextView);
        bmiStatusTextView = findViewById(R.id.bmiStatusTextView);
        saveButton = findViewById(R.id.saveButton);

        // Get data from Intent
        bmi = getIntent().getFloatExtra("BMI", 0);

        // Display values
        bmiValueTextView.setText(String.format(Locale.getDefault(), "%.2f", bmi));
        bmiStatusTextView.setText(getBmiCategory(bmi));

        saveButton.setOnClickListener(v -> saveToHistoryList());
    }

    private void saveToHistoryList() {
        SharedPreferences p = getSharedPreferences("BMI_STORE", MODE_PRIVATE);
        Gson gson = new Gson();

        // 1. Get the existing list from SharedPreferences
        String json = p.getString("h_list", "");
        Type type = new TypeToken<ArrayList<BmiHistoryItem>>() {}.getType();
        ArrayList<BmiHistoryItem> historyList = gson.fromJson(json, type);

        // If no history exists yet, create a new list
        if (historyList == null) {
            historyList = new ArrayList<>();
        }

        // 2. Prepare new entry
        String date = new SimpleDateFormat("dd MMM", Locale.getDefault()).format(new Date());
        String category = getBmiCategory(bmi);
        BmiHistoryItem newItem = new BmiHistoryItem(date, String.format("%.1f", bmi), category);

        // 3. Logic to keep only TOP 5
        historyList.add(0, newItem); // Add new result at the top (index 0)

        if (historyList.size() > 5) {
            historyList.remove(5); // Remove the 6th item if it exists
        }

        // 4. Save the updated list back to SharedPreferences
        p.edit().putString("h_list", gson.toJson(historyList)).apply();

        // UI Feedback
        Toast.makeText(this, "Saved to History!", Toast.LENGTH_SHORT).show();
        saveButton.setEnabled(false); // Disable button so they don't save twice
        saveButton.setAlpha(0.5f);
    }

    private String getBmiCategory(float bmi) {
        if (bmi < 18.5f) return "UNDERWEIGHT 🔵";
        else if (bmi < 25f) return "NORMAL BMI 🟢";
        else if (bmi < 30f) return "OVERWEIGHT 🟡";
        else return "OBESITY 🔴";
    }
}