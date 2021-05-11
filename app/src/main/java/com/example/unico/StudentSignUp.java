package com.example.unico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentSignUp extends AppCompatActivity implements  View.OnClickListener {
    EditText name,email,phone,password,username,answer,confirm_password,year,roll_no;
    Button register;
    Spinner spin2,spin3,spin4year;
    DatabaseClass dc;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        dc=new DatabaseClass(this);
        db= FirebaseDatabase.getInstance().getReference();
        spin2=findViewById(R.id.spinner2);
        spin3=findViewById(R.id.spinner3);
        spin4year=findViewById(R.id.spinner4);


        name=findViewById(R.id.editText3);
        roll_no=findViewById(R.id.editText5);
        email=findViewById(R.id.editText4);
      //  year=findViewById(R.id.editText5);
        phone=findViewById(R.id.editText7);

        password=findViewById(R.id.editText9);
        confirm_password=findViewById(R.id.editText10);
        answer=findViewById(R.id.editText11);
        register=findViewById(R.id.button5);
        register.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        String y="";
        s1 = name.getText().toString();
        s2 = email.getText().toString();
        s3 = phone.getText().toString();
        s4 = roll_no.getText().toString();
        s5=spin2.getSelectedItem().toString();


        s6=spin4year.getSelectedItem().toString();

        if(s6.equals("Year1"))
        {
            y="Y1";
        }
        else if(s6.equals("Year2"))
        {
            y="Y2";
        }
        else if(s6.equals("Year3"))
        {
            y="Y3";
        }
        else if(s6.equals("Year4"))
        {
            y="Y4";
        }

        s8 = password.getText().toString();
        s9=spin3.getSelectedItem().toString();
        s10 = answer.getText().toString();
        s11 = confirm_password.getText().toString();

        s7 = s4+y;
        getDataStudent obj1=new getDataStudent(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10);
        db.child("Student").push().setValue(obj1);
        yearViseClasses obj2=new yearViseClasses(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,"null","null");
        db.child(s6+"Students").push().setValue(obj2);

        Toast.makeText(StudentSignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        final Dialog d=new Dialog(this);
        d.setContentView(R.layout.roll_no_display);
        d.setTitle("Username");
        final TextView t=d.findViewById(R.id.textView6);

        t.setText("Your Username is "+s7);

        d.show();

        Toast.makeText(this, ""+s6, Toast.LENGTH_SHORT).show();

        }
    }

class getDataStudent {

    public String name,email,contact,roll_no,gender,year,username,password,question,answer;
    public getDataStudent(String name, String email, String contact, String roll_no, String gender, String year, String username, String password, String question, String answer) {

        {
            this.name=name;
            this.email=email;
            this.contact=contact;
            this.roll_no=roll_no;
            this.gender=gender;
            this.year=year;
            this.username=username;
            this.password=password;
            this.question=question;
            this.answer=answer;
        }




    }
}
class yearViseClasses
{
    public String name,email,contact,roll_no,gender,year,username,password,question,answer,attendance,date;
    public yearViseClasses(String name, String email, String contact, String roll_no, String gender, String year, String username, String password, String question, String answer,String attendance, String date)
    {
        this.name=name;
        this.email=email;
        this.contact=contact;
        this.roll_no=roll_no;
        this.gender=gender;
        this.year=year;
        this.username=username;
        this.password=password;
        this.question=question;
        this.answer=answer;
        this.attendance=attendance;
        this.date=date;
    }
}


