package com.test.studentregistration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fragmentUpdate extends Fragment {
    EditText studentIdEditText, nameEditText, emailEditText, dobEditText, phoneEditText;
    Button searchButton, updateButton;
    DatabaseHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);


        searchButton = view.findViewById(R.id.btnsearch);
        updateButton = view.findViewById(R.id.btnupdate);
        studentIdEditText = view.findViewById(R.id.id);
        nameEditText = view.findViewById(R.id.stdname);
        emailEditText = view.findViewById(R.id.email);
        dobEditText = view.findViewById(R.id.dob);
        phoneEditText = view.findViewById(R.id.mobile);
        dbHelper = new DatabaseHelper(getActivity());


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the student ID entered by the user
                int studentId = Integer.parseInt(studentIdEditText.getText().toString());

                // Get the student data from the database
                Student student = dbHelper.getStudentData(studentId);

                if (student != null) {
                    // Display student data in EditText fields
                    nameEditText.setText(student.getName());
                    emailEditText.setText(student.getEmail());
                    dobEditText.setText(student.getDob());
                    phoneEditText.setText(student.getPhone());
                } else {
                    // Show a toast message if student record is not found
                    Toast.makeText(getActivity(), "Student record is not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set a click listener for the "Update" button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated values from EditText fields
                int studentId = Integer.parseInt(studentIdEditText.getText().toString());
                String updatedName = nameEditText.getText().toString();
                String updatedEmail = emailEditText.getText().toString();
                String updatedDob = dobEditText.getText().toString();
                String updatedPhone = phoneEditText.getText().toString();

                // Update the student record in the database
                dbHelper.updateStudent(studentId, updatedName, updatedEmail, updatedDob, updatedPhone);

                // Show a toast message to indicate the update
                Toast.makeText(getActivity(), "Student record updated.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}