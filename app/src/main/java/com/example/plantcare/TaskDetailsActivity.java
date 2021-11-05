package com.example.plantcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailsActivity extends AppCompatActivity {
    ImageButton mDoneButton, mBackButton;
    TextView mDetailsPlantNameText, mTypeText, mPeriodText;

    ImageView mTypeImage;

    String name, type, period, date, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        mBackButton = findViewById(R.id.backButton);
        mDoneButton = findViewById(R.id.completeButton);

        mDetailsPlantNameText = findViewById(R.id.detailsNameText);
        mTypeText = findViewById(R.id.typeText);
        mPeriodText = findViewById(R.id.periodText);

        mTypeImage = findViewById(R.id.typeImage);

        DBHandler dbHandler;

        dbHandler = new DBHandler(TaskDetailsActivity.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            name = (String) bundle.get("NAME");
            type = (String) bundle.get("TYPE");
            period = (String) bundle.get("PERIOD");
            date = (String) bundle.get("DATE");
            status = (String)bundle.get("STATUS");

            Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG);

            mDetailsPlantNameText.setText(name);
            mTypeText.setText(type);
            mPeriodText.setText(period);

            if (type.equals("Watering")) {
                mTypeImage.setImageResource(R.mipmap.watergreen);
            } else {
                mTypeImage.setImageResource(R.mipmap.feedgreen);
            }
        }

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.updatePlants(name,date,"Done");

                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });

    }
}