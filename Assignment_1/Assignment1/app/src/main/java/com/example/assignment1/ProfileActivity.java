/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * ProfileActivity class
 * This class is a child activity to MainActivity.
 * It uses the StudentProfile class and the SharedPreferenceHelper class to save and retrieve students' information.
 */

public class ProfileActivity extends AppCompatActivity implements TextWatcher {

    private EditText et_name, et_age, et_StudentID;
    private EditText text_name, text_age, text_ID;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private StudentProfile studentProfile;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupUI();
    }

    // Initializing UI

    private void setupUI (){

        getSupportActionBar().setTitle(R.string.Profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name = (EditText) findViewById(R.id.Name);
        et_age = (EditText) findViewById((R.id.Age));
        et_StudentID = (EditText) findViewById((R.id.StudentID));
        buttonSave = (findViewById(R.id.Save));
        et_age.addTextChangedListener(this);

        sharedPreferenceHelper = new SharedPreferenceHelper(ProfileActivity.this);

        // Save Button

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { SaveProfile();

            }
        });
    }

    // Displayed Menu - Inflater

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate((R.menu.menu_edit), menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Edit Menu (Top Right Corner)

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Edit:
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

    // Save Student Profile
    
    public void SaveProfile() {


        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String id = et_StudentID.getText().toString();

        studentProfile = new StudentProfile(name, age, id);

        sharedPreferenceHelper.saveProfile(studentProfile);

        // Feedback to User to Indicate that their Information was Successfully Saved

        Toast.makeText(ProfileActivity.this, "Contact has been saved", Toast.LENGTH_SHORT).show();

        // Call to DisplayInfo Method to Display Saved Information

        DisplayInfo();

    }

    // Display Student Information

    public void DisplayInfo(){

        setContentView(R.layout.activity_profile_2);

        text_name = (EditText) findViewById(R.id.Name_txt);
        text_age = (EditText) findViewById((R.id.Age_txt));
        text_ID = (EditText) findViewById((R.id.StudentID_txt));

        studentProfile = sharedPreferenceHelper.getProfile();

        String name_txt = studentProfile.getName();
        String age_txt = studentProfile.getAge();
        String id_txt = studentProfile.getId();


        // Set Student Information in New Layout and Lock the Display

        if (name_txt != null || age_txt != null || id_txt != null) {

            text_name.setText(name_txt);
            text_age.setText(age_txt);
            text_ID.setText(id_txt);
            text_name.setEnabled(false);
            text_age.setEnabled(false);
            text_ID.setEnabled(false);

        }
    }

    // This Method is NOT used

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    // This Method is NOT used

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    // Listener Method for Age Edit Text.
    // Enable and disable Save Button if age is not correct.

    @Override
    public void afterTextChanged(Editable s) {
        try {
            int age_number = Integer.parseInt(s.toString());
            if (age_number < 18) {
                  buttonSave.setEnabled(false);
                  Toast.makeText(ProfileActivity.this, " Age has to be between 18 and 99 !! ", Toast.LENGTH_SHORT).show();
            }else
                buttonSave.setEnabled(true);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    

}



