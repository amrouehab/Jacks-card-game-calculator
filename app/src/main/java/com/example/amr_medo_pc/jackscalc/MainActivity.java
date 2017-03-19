package com.example.amr_medo_pc.jackscalc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ScoresCalc ScoreCalc;
    ArrayList<Player>Players;
    String[]names=new String[]{String.valueOf(R.id.p1), String.valueOf(R.id.p2), String.valueOf(R.id.p3), String.valueOf(R.id.p4)};

    int[]ids;
    LinearLayout Ly;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(v==null)v=getLayoutInflater().inflate(R.layout.names,null);
        setContentView(v);

        Button but=(Button)findViewById(R.id.Button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ET;
                for(int i=0;i<4;i++){
                    ET= (EditText) findViewById(Integer.parseInt(names[i]));
                    names[i]=ET.getText().toString();
                }
                v=getLayoutInflater().inflate(R.layout.activity_main,null);
                setContentView(v);
                start();
            }
        });




    }

    private void start() {
        Players=new ArrayList<>();
        ids=new int[]{R.id.p1Ly,R.id.p2Ly,R.id.p3Ly,R.id.p4Ly};
        createPlayers();
        ScoreCalc=new ScoresCalc(Players,this);
        playerSLayout playerSLayout=new playerSLayout(Players,this);
        Ly=(LinearLayout)findViewById(R.id.columLy) ;
        playerSLayout.buildRows();

    }


    private void createPlayers() {
        for(int i=0;i<4;i++){
            Players.add(i,new Player(this,names[i],ids[i]));
        }
    }


}
