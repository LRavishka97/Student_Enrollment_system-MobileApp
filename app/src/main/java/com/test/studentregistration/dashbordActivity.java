package com.test.studentregistration;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class dashbordActivity extends AppCompatActivity {
    CardView ViewCard,InsertCard,UpdateCard,DeleteCard;
    Button searchButton;

    EditText studentIdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        int studentId = 1; // Replace with the desired student ID
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Student student = dbHelper.getStudentData(studentId);



       ViewCard=findViewById(R.id.view);
        InsertCard=findViewById(R.id.insartCard);
        UpdateCard=findViewById(R.id.updateCard);
        DeleteCard=findViewById(R.id.deleteCard);

        searchButton=findViewById(R.id.btnsearch);

        studentIdEditText=findViewById(R.id.searchid);

        TextView nameTextView = findViewById(R.id.tableStudentName);
        TextView emailTextView = findViewById(R.id.tableStudentEmail);
        TextView dobTextView = findViewById(R.id.tableStudentDob);
        TextView phoneTextView = findViewById(R.id.tableStudentPhone);

        if (student != null) {
            // Set the student data in TextViews
            nameTextView.setText(student.getName());
            emailTextView.setText(student.getEmail());
            dobTextView.setText(student.getDob());
            phoneTextView.setText(student.getPhone());
        }


        ViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                List<Fragment> fragments = fragmentManager.getFragments();
                if (fragments != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    for (Fragment fragment : fragments) {
                        fragmentTransaction.remove(fragment);
                    }
                    fragmentTransaction.commitNow();
                }

            }
        });

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


                    Toast.makeText(getApplicationContext(), "Student record is not found!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        InsertCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragment(new insertFragment());
            }
        });
        UpdateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragment(new fragmentUpdate());
            }
        });
        DeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragment(new fragmentDelete());
            }
        });


    }
    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frm1,fragment);
        fragmentTransaction.commit();

    }

}