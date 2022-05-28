package com.example.zest23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Check_LiveScore extends AppCompatActivity {

    private TextView sportsname,matchno,team1,team2,team1_score,team2_score;
    private Button Team1_counter,Team2_counter;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://zest-23-default-rtdb.firebaseio.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_live_score);


        sportsname=findViewById(R.id.Sport_name);
        matchno=findViewById(R.id.Match_NO);
        team1=findViewById(R.id.Team1_Name);
        team2=findViewById(R.id.Team2_Name);
        team1_score=findViewById(R.id.Team1_Score);
        team2_score=findViewById(R.id.Team2_Score);
        Team1_counter=findViewById(R.id.Team1_Counter);
        Team2_counter=findViewById(R.id.Team2_Counter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String SPORTSNAME,MATCHNO,TEAM1,TEAM2,TEAM1_SCORE,TEAM2_SCORE;
                TEAM1_SCORE=snapshot.child("Scores").child("Chess").child("2").child("TEAM A:Scores").getValue(String.class);
                TEAM2_SCORE=snapshot.child("Scores").child("Chess").child("2").child("TEAM B:Scores").getValue(String.class);
                team1_score.setText(TEAM1_SCORE);
                team2_score.setText(TEAM2_SCORE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}