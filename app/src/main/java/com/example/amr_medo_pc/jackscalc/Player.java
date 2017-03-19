package com.example.amr_medo_pc.jackscalc;

import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Player {

    public  String name;
    private  MainActivity Activity;
    private LinearLayout ColumLy;
    ArrayList<TextView> Rows;

    public Player( MainActivity main,String Name,int LyoutId) {
        this.Activity=main;
        this.name=Name;
        Rows=new ArrayList<>();
        ColumLy= (LinearLayout) main.findViewById(LyoutId);
    }
//hn7ot feh functions el score Ex getDAmatScore SetDamatScore
    LinearLayout getColumnLY(){

      return ColumLy;
    }

}
