package com.example.unico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class TeacherSignIn extends AppCompatActivity {
    EditText e,e2,e7;
    Button b4,b6;
    ImageButton b;

    String username, password;

    DatabaseReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_in);

        e=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e7=findViewById(R.id.editText7);

        dr= FirebaseDatabase.getInstance().getReference();
        b=findViewById(R.id.eyeButton);
        b4=findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

//                Toast.makeText(TeacherSignIn.this, "Login Successfull", Toast.LENGTH_SHORT).show();
//                Intent i= new Intent(TeacherSignIn.this,TeacherHome.class);
//                startActivity(i);
                final String a=e.getText().toString();
                final String b=e2.getText().toString();

                Query q= dr.child("Teacher").orderByChild("username").equalTo(a);

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map m = (Map) ds.getValue();

                            username = (String) m.get("username");
                            password = (String) m.get("password");

                            if(a.equals(username) && b.equals(password))
                            {
                                Toast.makeText(TeacherSignIn.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(TeacherSignIn.this,TeacherHome.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(TeacherSignIn.this, "Incorrect Username/password", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        b6=findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TeacherSignIn.this,TeacherSignUp.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    });
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    });
                }
        });

    }
}
