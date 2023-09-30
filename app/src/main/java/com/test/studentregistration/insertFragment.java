package com.test.studentregistration;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Date;


public class insertFragment extends Fragment {


    private EditText Name, Email, dob, phone;
    private Button Insert;

    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Name = view.findViewById(R.id.name);
        Email = view.findViewById(R.id.email);
        dob = view.findViewById(R.id.dob);
        phone = view.findViewById(R.id.mobile);

        Insert = view.findViewById(R.id.insertstudent);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String dateb = dob.getText().toString();
                String pno = phone.getText().toString();

                databaseHelper.insertStudent(name, email, dateb, pno);
                Toast.makeText(getActivity(), "Student registered successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
