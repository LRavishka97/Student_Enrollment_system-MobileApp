package com.test.studentregistration;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class fragmentDelete extends Fragment {
    EditText studentIdEditText;
    Button searchButton,deletebtn;
    DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        // Initialize UI elements
        searchButton = view.findViewById(R.id.btnsearch);
        deletebtn= view.findViewById(R.id.btndelete);
        studentIdEditText = view.findViewById(R.id.id);
        dbHelper = new DatabaseHelper(getActivity());

        TextView nameTextView = view.findViewById(R.id.tableStudentName);
        TextView emailTextView = view.findViewById(R.id.tableStudentEmail);
        TextView dobTextView = view.findViewById(R.id.tableStudentDob);
        TextView phoneTextView = view.findViewById(R.id.tableStudentPhone);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int studentId = Integer.parseInt(studentIdEditText.getText().toString());

                Student student = dbHelper.getStudentData(studentId);

                if (student != null) {
                    // Set the student data in TextViews
                    nameTextView.setText(student.getName());
                    emailTextView.setText(student.getEmail());
                    dobTextView.setText(student.getDob());
                    phoneTextView.setText(student.getPhone());
                } else {


                    Toast.makeText(getActivity(), "Student record is not found!", Toast.LENGTH_SHORT).show();


                }
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the student ID entered by the user
                int studentId = Integer.parseInt(studentIdEditText.getText().toString());

                // Get the student data from the database
                Student student = dbHelper.getStudentData(studentId);

                if (student != null) {
                    // Set the student data in TextViews
                    nameTextView.setText(student.getName());
                    emailTextView.setText(student.getEmail());
                    dobTextView.setText(student.getDob());
                    phoneTextView.setText(student.getPhone());
                    showDeleteConfirmationDialog(studentId);
                } else {
                    // Show a toast message if student record is not found
                    Toast.makeText(getActivity(), "Student record is not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void showDeleteConfirmationDialog(final int studentId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Student");
        builder.setMessage("Are you sure you want to delete this student?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the student record
                dbHelper.deleteStudent(studentId);

                // Clear the TextViews
                TextView nameTextView = getView().findViewById(R.id.tableStudentName);
                TextView emailTextView = getView().findViewById(R.id.tableStudentEmail);
                TextView dobTextView = getView().findViewById(R.id.tableStudentDob);
                TextView phoneTextView = getView().findViewById(R.id.tableStudentPhone);

                nameTextView.setText("");
                emailTextView.setText("");
                dobTextView.setText("");
                phoneTextView.setText("");

                // Inform the user about the deletion
                Toast.makeText(getActivity(), "Student record deleted.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, just close the dialog
            }
        });
        builder.show();
    }
}