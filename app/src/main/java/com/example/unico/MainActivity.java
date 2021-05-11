package com.example.unico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button b8,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Choose Account");
        b3=findViewById(R.id.button3);
        b8=findViewById(R.id.button8);
        b3.setOnClickListener(this);
        b8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button3)
        {
            Intent i=new Intent(MainActivity.this,TeacherSignIn.class);
            startActivity(i);
        }
        if(v.getId()==R.id.button8)
        {
            Intent i1=new Intent(MainActivity.this,StudentSignIn.class);
            startActivity(i1);
        }
    }
}
