package com.example.zest23;

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
import com.google.firebase.database.ValueEventListener;

public class Event_Registration extends AppCompatActivity {

    private EditText sportsname,matchno,team1,team2;
    private Button eventgenerator;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://zest-23-default-rtdb.firebaseio.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);


        sportsname=findViewById(R.id.event_generator_sportsname);
        matchno=findViewById(R.id.event_generator_matchno);
        team1=findViewById(R.id.event_generator_team1);
        team2=findViewById(R.id.event_generator_team2);
        eventgenerator=findViewById(R.id.event_generator_submit);

        eventgenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SportsName=sportsname.getText().toString();
                String MatchNo=matchno.getText().toString();
                String Team1=team1.getText().toString();
                String Team2=team2.getText().toString();

                if(SportsName.isEmpty() || MatchNo.isEmpty() || Team1.isEmpty() || Team2.isEmpty())
                {
                    Toast.makeText(Event_Registration.this, "Please Enter All Field", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    databaseReference.child(SportsName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child("Scores").child(SportsName).hasChild(MatchNo))
                            {
                                //Needs to work on Error Handling
                                Toast.makeText(Event_Registration.this, "Match is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("Match No").setValue(MatchNo);
                                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM A").setValue(Team1);
                                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM B").setValue(Team2);;
                                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM A:Scores").setValue("0");
                                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM B:Scores").setValue("0");

                                Toast.makeText(Event_Registration.this, "Event Added Successful", Toast.LENGTH_SHORT).show();
                                clear();

                                Intent intent=new Intent(Event_Registration.this,Score_Updator.class);
                                intent.putExtra("SportsName",SportsName);
                                intent.putExtra("MatchNo", MatchNo);
                                intent.putExtra("Team1", Team1);
                                intent.putExtra("Team2", Team2);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    public void clear()
    {
        sportsname.setText("");
        matchno.setText("");
        team1.setText("");
        team2.setText("");
    }
}