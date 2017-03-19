package com.example.amr_medo_pc.jackscalc;


import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class playerSLayout {

     LinearLayout colorLy;
    private  MainActivity Main;
    private  ArrayList<Player> PlayersList;
    private  ArrayList<TextView> RoundsViews;


    public playerSLayout(ArrayList<Player> PlayersList,MainActivity activity){
        this.PlayersList=PlayersList;
        Main=activity;
        colorLy= (LinearLayout) activity.findViewById(R.id.colorsLy);
        RoundsViews=new ArrayList<>();



    }
     void buildRows() {
        String TagSList[] =new String[]{"☺","k♥","♦","Q","☀","J","sc","k♥","♦","Q","☀","J","sc","k♥","♦","Q","☀","J","sc","k♥","♦","Q","☀","J","sc","TS"};


String Tag,Text ;
        for(int i=0;i<26;i++){
            Tag=TagSList[i];
            for(int p=0;p<4;p++){
                Text="";
               if(i==0)Text=PlayersList.get(p).name;

                   addPlayerRowView(p,PlayersList.get(p).getColumnLY(),ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,Color.BLACK,Color.WHITE,Text,23,PlayersList.get(p).name);

            }
            addColorRowView(colorLy,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,Color.BLACK,Color.WHITE,Tag,23,Tag);



        }
    }



     View creatRowView(int Height,int Width,int TextColor,int backgroundColor,String Text,int TextSize,String Tag) {
        TextView tv=new TextView(Main);
        tv.setLayoutParams(new LinearLayoutCompat.LayoutParams(Width, Height,2));
        tv.setTextSize(TextSize);
        tv.setSingleLine();
        tv.setTextColor(TextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        tv.setTag(Tag);
        tv.setText(Text);
        tv.setBackgroundResource(R.drawable.rect_shape);
        return tv;

    }
     void addColorRowView(LinearLayout columnLY,int Height,int Width,int TextColor,int backgroundColor,String Text,int TextSize,String Tag) {

        TextView tv= (TextView) creatRowView(Height,Width,TextColor,backgroundColor,Text,TextSize,Tag);
        columnLY.addView(tv);
         RoundsViews.add(tv);
        playerRowOnClick(tv);

    }
    private void addPlayerRowView(int p,LinearLayout columnLY,int Height,int Width,int TextColor,int backgroundColor,String Text,int TextSize,String Tag) {
       TextView tv= (TextView) creatRowView(Height,Width,TextColor,backgroundColor,Text,TextSize,Tag);
        columnLY.addView(tv);
        switch (p){
            case 0:PlayersList.get(0).Rows.add(tv);break;
            case 1:PlayersList.get(1).Rows.add(tv);break;
            case 2:PlayersList.get(2).Rows.add(tv);break;
            case 3:PlayersList.get(3).Rows.add(tv);break;



        }


    }

    private void playerRowOnClick(TextView tv) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tag= (String) v.getTag();
                int RoundNum =RoundsViews.indexOf(v);

switch (Tag){

    case "k♥":Main.ScoreCalc.CalcKingHeart(RoundNum);break;
    case "♦":Main.ScoreCalc.CalcKaroo(RoundNum,"♦");break;
    case "Q":Main.ScoreCalc.CalcDamat(RoundNum,"Q");break;
    case "J":Main.ScoreCalc.CalcJaks(RoundNum,"J");break;
    case "☀":Main.ScoreCalc.CalcSuns(RoundNum,"☀");break;
    case "sc":Main.ScoreCalc.CalcALL(RoundNum);break;
    case "TS":Main.ScoreCalc.CalcTotal(RoundNum);break;
}
            }
        });

    }


}
