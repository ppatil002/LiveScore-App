package com.example.zest23;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


public class Event_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_event_, container, false);
        GridLayout mainGrid;
        mainGrid=(GridLayout) v.findViewById(R.id.mainGrid);

        for(int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = null;
                    switch(finalI){
                        case 0:
                            s="Chess";
                            break;
                        case 1:
                            s="Football";
                            break;
                        case 2:
                            s="kabaddi";
                            break;
                        case 3:
                            s="Kho-Kho";
                            break;
                        case 4:
                            s="Volleyball";
                            break;
                        case 5:
                            s="Fencing";
                            break;
                        case 6:
                            s="Tennis";
                            break;
                        case 7:
                            s="Badminton";
                            break;
                        default:
                            break;
                    }
                    Intent intent=new Intent(getContext(),Check_LiveScore.class);
                    intent.putExtra("Event_Name",s);
                    startActivity(intent);
                }
            });
        }



        return v;
    }
}