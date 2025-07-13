package com.example.smartbmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getStartedButton = findViewById(R.id.getStartedButton);
        ImageView img=findViewById(R.id.imageView);
        img.animate().alpha(1).scaleX(1.5f).scaleY(1.5f).setDuration(4000).start();
        getStartedButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BMICalculationActivity.class);
            startActivity(intent);
        });
    }
}
