package com.test.studentregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class dashbordActivity extends AppCompatActivity {
    CardView ViewCard,InsertCard,UpdateCard,DeleteCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        ViewCard=findViewById(R.id.view);
        InsertCard=findViewById(R.id.insartCard);
        UpdateCard=findViewById(R.id.updateCard);
        DeleteCard=findViewById(R.id.deleteCard);

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