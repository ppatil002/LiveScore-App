package com.example.zest23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Score_Updator extends AppCompatActivity {

    private TextView sportsname,matchno,team1,team2,team1_score,team2_score;
    private Button Team1_counter,Team2_counter;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://zest-23-default-rtdb.firebaseio.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_updator);


        String SportsName=getIntent().getStringExtra("SportsName");
        String MatchNo=getIntent().getStringExtra("MatchNo");
        String Team1=getIntent().getStringExtra("Team1");
        String Team2=getIntent().getStringExtra("Team2");

        sportsname=findViewById(R.id.Sport_name);
        matchno=findViewById(R.id.Match_NO);
        team1=findViewById(R.id.Team1_Name);
        team2=findViewById(R.id.Team2_Name);
        team1_score=findViewById(R.id.Team1_Score);
        team2_score=findViewById(R.id.Team2_Score);
        Team1_counter=findViewById(R.id.Team1_Counter);
        Team2_counter=findViewById(R.id.Team2_Counter);

        sportsname.setText(SportsName);
        matchno.setText("Match:"+MatchNo);
        team1.setText(Team1);
        team2.setText(Team2);

        team1_score.setText("0");
        team2_score.setText("0");



        Team1_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=team1_score.getText().toString();
                int score=Integer.parseInt(s);
                score++;
                s=String.valueOf(score);
                team1_score.setText(s);
                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM A:Scores").setValue(s);

//                databaseReference.child("Scores").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(2>4)
//                        {
//                                //Error Handling
//                        }
//                        else
//                        {
//                            databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM A:Scores").setValue(s);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });

        Team2_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s=team2_score.getText().toString();
                int score=Integer.parseInt(s);
                score++;
                s=String.valueOf(score);
                team2_score.setText(s);
                databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM B:Scores").setValue(s);

//                databaseReference.child("Scores").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(2>4)
//                        {
//                            //Error Handling
//                        }
//                        else
//                        {
//                            databaseReference.child("Scores").child(SportsName).child(MatchNo).child("TEAM B:Scores").setValue(s);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });


    }
}