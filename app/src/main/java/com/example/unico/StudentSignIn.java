package com.example.unico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class StudentSignIn extends AppCompatActivity implements View.OnClickListener {
    EditText e,e2,e7;
    Button b4,b6,b;

  static String username,password;
   static String Sname,rollNo,Syear;

    DatabaseReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        e=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e7=findViewById(R.id.editText7);
        b4=findViewById(R.id.button4);
        b6=findViewById(R.id.button6);
        b4.setOnClickListener(this);
        b6.setOnClickListener(this);

        dr= FirebaseDatabase.getInstance().getReference();


    }

    @Override
    public void onClick(View v) {



        if(v.getId()==R.id.button4)
        {
            final String a=e.getText().toString();
            final String b=e2.getText().toString();

            Query q= dr.child("Student").orderByChild("username").equalTo(a);

            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Map m = (Map) ds.getValue();

                        username = (String) m.get("username");
                        password = (String) m.get("password");
                        Sname = (String) m.get("name");
                        rollNo = (String) m.get("roll_no");
                        Syear = (String) m.get("year");



                        if(a.equals(username) && b.equals(password))
                        {
                            Toast.makeText(StudentSignIn.this, "Login Successfull", Toast.LENGTH_SHORT).show();



                            Intent i= new Intent(StudentSignIn.this, StudentHome.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(StudentSignIn.this, "Incorrect Username/password", Toast.LENGTH_SHORT).show();
                        }





                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        else if (v.getId()==R.id.button6)
        {
            Intent i=new Intent(StudentSignIn.this,StudentSignUp.class);
            startActivity(i);
        }
    }
}