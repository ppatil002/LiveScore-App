package com.example.zest23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText username_edittext,password_edittext;
    private Button loginbutton;
    private TextView registernow;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://zest-23-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_edittext=findViewById(R.id.username_edittext);
        password_edittext=findViewById(R.id.password_edittext);
        loginbutton=findViewById(R.id.LoginButton);
        registernow=findViewById(R.id.Register_Link);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=username_edittext.getText().toString();
                String password=password_edittext.getText().toString();

                if(username.isEmpty())
                {
                    Toast.makeText(Login.this, "Please Enter Username ", Toast.LENGTH_SHORT).show();
                }
                else  if (password.isEmpty())
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //Checking if Username Exists in database or not
                            if(snapshot.hasChild(username))
                            {
                                final String actual_password=snapshot.child(username).child("Password").getValue(String.class);
                                if(password.equals(actual_password))
                                {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,MainActivity.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                    password_edittext.setText("");
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Username  doesn't Exist.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //Going to Register activity
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }

}