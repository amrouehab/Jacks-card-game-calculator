package com.example.amr_medo_pc.jackscalc;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Amr_medo-Pc on 23/02/2017.
 */

public class ScoresCalc extends playerSLayout {
    private  MainActivity Main;
    private ArrayList<Player>  Players;
    private TextView LastView1;
    private TextView LastView2;
    int Ts[]=new int[]{0,0,0,0};

    ScoresCalc(ArrayList<Player> PlayerList, MainActivity main){
        super(PlayerList,main);
        Players=PlayerList;
        this.Main=main;
    }

    void CalcKingHeart(final int roundNum) {
        try {
            final android.support.v7.app.AlertDialog.Builder ad = new android.support.v7.app.AlertDialog.Builder(Main);
            final View KingHeartLy = LayoutInflater.from(Main).inflate(R.layout.input_ly, null);
            final LinearLayout names = (LinearLayout) KingHeartLy.findViewById(R.id.container);
            final TextView Messagee= (TextView) KingHeartLy.findViewById(R.id.message);
            Messagee.setText("click on the one who got it and long click on the one who gaved it ");
            for (int i = 0; i < 4; i++) {
                final TextView tv = (TextView) creatRowView(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Color.WHITE, KingHeartLy.getSolidColor(), Main.names[i], 23, Main.names[i]);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((TextView) v).setTextColor(Color.RED);
                        if (LastView1 != null)
                            LastView1.setTextColor(Color.WHITE);
                        LastView1 = tv;
                    }
                });
                tv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ((TextView) v).setTextColor(Color.GREEN);
                        if (LastView2 != null)
                            LastView2.setTextColor(Color.WHITE);
                        LastView2 = tv;
                        return true;
                    }
                });
                names.addView(tv);
                tv.setBackgroundColor(KingHeartLy.getSolidColor());
            }

            ad
                    .setTitle("KingHeart")
                    .setNeutralButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            names.removeAllViews();
                            writeKingHeartScores(roundNum);

                        }
                    })
                    .setView(KingHeartLy)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            names.removeAllViews();
                            writeKingHeartScores(roundNum);
                        }
                    })
                    .show();


        } catch (Exception e) {

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }
    }
//aktb el scores
    private void writeKingHeartScores(int roundNum) {
        try{
        boolean positiveKing,NegativeKing;
        for (int i = 0; i < 4; i++) {
            positiveKing=LastView2.getTag().toString().equals(Main.names[i]);
            NegativeKing=LastView1.getTag().toString().equals(Main.names[i]);
            Players.get(i).Rows.get(roundNum).setText("0");
            if (NegativeKing)Players.get(i).Rows.get(roundNum).setText("-150");
            if (positiveKing)Players.get(i).Rows.get(roundNum).setText("75");
            if (positiveKing&&NegativeKing)Players.get(i).Rows.get(roundNum).setText("-75");
        }
        }catch (Exception e){

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void showInputLy(final int roundNum, String message, final String RoundType) {
        try{
        final android.support.v7.app.AlertDialog.Builder ad= new android.support.v7.app.AlertDialog.Builder(Main);
        final View KarooLy=  LayoutInflater.from(Main).inflate(R.layout.input_ly,null);
        final LinearLayout ScoresEditLy= (LinearLayout) KarooLy.findViewById(R.id.container);
            final TextView Messagee= (TextView) KarooLy.findViewById(R.id.message);
            Messagee.setText(message);
        final ArrayList<EditText> Texts=new ArrayList<>();
        for(int i=0;i<4;i++){
            final EditText EditView= (EditText) creatRowView(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, Color.WHITE,KarooLy.getSolidColor(),Main.names[i],23,"Editable");
            ScoresEditLy.addView(EditView,i);
           Texts.add(EditView);
        }

        ad
                .setTitle("Score")
                .setNeutralButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ScoresEditLy.removeAllViews();
                        writeScores(roundNum,Texts.get(0).getText().toString(),Texts.get(1).getText().toString(),
                                Texts.get(2).getText().toString(),Texts.get(3).getText().toString(),RoundType);

                    }
                })
                .setView(KarooLy)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        ScoresEditLy.removeAllViews();

                    }
                })
                .show();

        }catch (Exception e){

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private void writeScores(int roundNum, String s, String s1, String s2, String s3, String roundType) {
        try {
            String input[] = new String[]{s, s1, s2, s3};
            int score=0;
            for (int i = 0; i < 4; i++) {
                switch (roundType) {
                    case "♦":
                        score=calculateKarooScores(Integer.parseInt(input[i]),Integer.parseInt(input[0]),Integer.parseInt(input[1]),Integer.parseInt(input[2]),Integer.parseInt(input[3]));

                        break;
                    case "Q":
                        score=calculateDamatScores(Integer.parseInt(String.valueOf(input[i].charAt(0))),Integer.parseInt(String.valueOf(input[i].charAt(1))));
                        break;
                    case "J":
                        score=calculateJacksScores(Integer.parseInt(input[i]));

                        break;
                    case "☀":
                        score=(Integer.parseInt(input[i]) * -10);
                        break;

                }
                Players.get(i).Rows.get(roundNum).setText(String.valueOf(score));

            }
        }catch (Exception e){

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private int calculateKarooScores(int currentScore, int i1, int i2, int i3, int i4) {
        int score=0;
        if(i1==13||i2==13||i3==13||i4==13){
            if(currentScore==13) score=0;
                else score=-65;
        }
        else score=currentScore*-15;
        return score;
    }

    private int calculateJacksScores(int order) {
        int score=0;
        switch(order){
            case 1:score=200 ;break;
            case 2:score=150;break;
            case 3:score=100;break;
            case 4:score=50;break;
        }
        return score;
    }

    private int calculateDamatScores(int GottenQ, int GivenQ) {

        return ((GottenQ*(-50))+GivenQ*25);
    }

    @Override
    View creatRowView(int Height, int Width, int TextColor, int backgroundColor, String Text, int TextSize, String Tag) {
        if(Tag.equals("Editable")){
            EditText tv=new EditText(Main);
            tv.setLayoutParams(new LinearLayout.LayoutParams(Width, Height,2));
            tv.setTextSize(TextSize);
            tv.setHint(Text);
            tv.setHintTextColor(Color.GRAY);
            tv.setInputType(InputType.TYPE_CLASS_NUMBER);
            tv.setSingleLine();
            tv.setTextColor(Color.BLACK);
            tv.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            tv.setBackgroundColor(Color.RED);
            tv.setBackgroundResource(R.drawable.rect_shape);
            tv.setTag(Tag);

            return tv;

        }else
        return super.creatRowView(Height, Width, TextColor, backgroundColor, Text, TextSize, Tag);

    }

    public void CalcDamat(int roundNum, String q) {
        try{
        showInputLy(roundNum,"EX: if u enter 12 it means that the player got 1Q And gave 2Q","Q");
    }catch (Exception e){

        Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
    }
    }

    public void CalcJaks(int roundNum, String j) {
        try{
        showInputLy(roundNum,"Ex:if u  enter 1 it means that the player  had finished first and so on...  ","J");
    }catch (Exception e){

        Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
    }
    }
    public void CalcKaroo(int roundNum, String j) {
        try{
            showInputLy(roundNum,"enter how many diamonds card each player had got ","♦");
        }catch (Exception e){

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void CalcSuns(int roundNum, String s) {
        try{
        showInputLy(roundNum,"enter how many tricks each player had got ","☀");
    }catch (Exception e){

        Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
    }

    }
    public void CalcALL(int roundNum) {
        try {
            double Scoretotal = 0;
            for (int i = 0; i < 4; i++) {
                for (int RoundNum = 1; RoundNum < 6; RoundNum++)
                    Scoretotal = Scoretotal + Double.parseDouble(Players.get(i).Rows.get(roundNum - RoundNum ).getText().toString());
                Players.get(i).Rows.get(roundNum).setText(String.valueOf(Scoretotal).substring(0,String.valueOf(Scoretotal).indexOf(".")));
                Ts[i]=roundNum;
                Scoretotal=0;



            }

        }catch (Exception e){

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void CalcTotal(int roundNum) {
        try {
            double Scoretotal = 0;
            for (int i = 0; i < 4; i++) {
                for (int RoundNum = 6; RoundNum <=24 ; RoundNum=RoundNum+6)
                    Scoretotal = Scoretotal + Double.parseDouble(Players.get(i).Rows.get(RoundNum).getText().toString());
                Players.get(i).Rows.get(roundNum).setText(String.valueOf(Scoretotal).substring(0,String.valueOf(Scoretotal).indexOf(".")));
                Scoretotal=0;

            }

        }catch (Exception e){

            Toast.makeText(Main, "Error", Toast.LENGTH_SHORT).show();
        }

    }
}
