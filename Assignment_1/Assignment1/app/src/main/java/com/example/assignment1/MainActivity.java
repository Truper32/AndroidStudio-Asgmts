/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * MainActivity Class.
 * This class is the launcher activity of the application.
 */

public class MainActivity extends AppCompatActivity {

    protected SharedPreferenceHelper sharedPreferenceHelper;
    private Button buttonGrades;
    private Button buttonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    // Initializing UI

    private void setupUI (){

        buttonGrades = (Button) findViewById(R.id.buttonGrades);
        buttonName = (Button) findViewById(R.id.button_name);

        buttonGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGradeActivity();
            }
        });

        buttonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });

        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
    }

    // Managing of Up Navigation or Return Action Before any Information is Stored
    
    @Override
    protected void onResume() {
        super.onResume();

        String name = sharedPreferenceHelper.getProfileName();
        if (name == null)
            openProfileActivity();
        else
            buttonName.setText(name);

    }

    // Open Grade Activity

    public void openGradeActivity(){

        Intent intent = new Intent (this, GradeActivity.class);
        startActivity(intent);
    }

    // Open Profile Activity

    public void openProfileActivity(){
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }
}