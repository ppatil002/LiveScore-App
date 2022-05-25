package com.example.zest23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {

    private EditText firstname,lastname,mobile_no,username,password,repassword;
    private Button registerbutton;
    private TextView loginnow;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://zest-23-default-rtdb.firebaseio.com");

    ///////////PENDING TO ADD EMAIL  AND ITS VERIFICATION AND GOOGLE VERTIFICATION FORGOT PASSWORD
    ////////////
    ///////////
    ///////////
    /////////////
    //////////
    ///////////
    ///////////
    /////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname=findViewById(R.id.firstname_edittext);
        lastname=findViewById(R.id.lastname_edittext);
        mobile_no=findViewById(R.id.Phone_edittext);
        username=findViewById(R.id.register_username_edittext);
        password=findViewById(R.id.register_password_edittext);
        repassword=findViewById(R.id.register_Re_password_edittext);
        registerbutton=findViewById(R.id.RegisterButton);
        loginnow=findViewById(R.id.Login_Link);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String FirstName=firstname.getText().toString();
                final String LastName=lastname.getText().toString();
                final String Mobile_NO=mobile_no.getText().toString();
                final String UserName=username.getText().toString();
                final String Password=password.getText().toString();
                final String RePassword=repassword.getText().toString();
                
                    //Checking Conditions
                if(isValidPassword(Password,RePassword))
                {
                    if(FirstName.isEmpty() || LastName.isEmpty() || Mobile_NO.isEmpty() || UserName.isEmpty() ||
                    Password.isEmpty() || RePassword.isEmpty())
                    {
                        Toast.makeText(Register.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                    }
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //Checking if username is already taken
                            if(snapshot.hasChild(UserName))
                            {
                                Toast.makeText(Register.this, "Username already taken", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                //Sending data to Firebase
                                databaseReference.child("user").child(UserName).child("First Name").setValue(FirstName);
                                databaseReference.child("user").child(UserName).child("Last Name").setValue(LastName);
                                databaseReference.child("user").child(UserName).child("Mobile Number").setValue(Mobile_NO);
                                databaseReference.child("user").child(UserName).child("User Name").setValue(UserName);
                                databaseReference.child("user").child(UserName).child("Password").setValue(Password);

                                Toast.makeText(Register.this, "Successufully Register", Toast.LENGTH_SHORT).show();
                                clear();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });

        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void clear() {
        firstname.setText("");
        lastname.setText("");
        mobile_no.setText("");
        username.setText("");
        password.setText("");
        repassword.setText("");
    }

    private boolean isValidPassword(String Password,String RePassword) {
        if(!Password.equals(RePassword))
        {
            Toast.makeText(this, "Password You have Enter is not matching", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Password.length()<2)
        {
            Toast.makeText(this, "Password size must be more than 2", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}