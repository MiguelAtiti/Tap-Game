package com.example.seven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class LevelUp extends AppCompatActivity {


    String lifeS;
    String strS;
    String expS;


    int lifeval;
    int strval;
    int expoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);


        //Layout
        TextView expV=(TextView) findViewById(R.id.textView4);
        TextView lifeV=(TextView) findViewById(R.id.lifeView);
        TextView strV=(TextView)  findViewById(R.id.strV);
        SeekBar strB=(SeekBar) findViewById(R.id.strBar);
        SeekBar lifeB=(SeekBar) findViewById(R.id.lifeBar);
        Button finishB=(Button) findViewById(R.id.finishBtn);


        //Obtenemos las stats del GameManager
        lifeval = GameManager.getLife();
        lifeS = Integer.toString(lifeval);
        lifeV.setText(lifeS);


        strval = (int) GameManager.getStr();
        System.out.println("obtenemos"+strval+"fuerza");
        strS= Integer.toString(strval);
        strV.setText(strS);


        expoints = GameManager.getScore();
        System.out.println("obtenemos"+expoints+"fuerza");
        expS = Integer.toString(expoints);
        expV.setText("EXP: "+expS);









        lifeB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                lifeV.setText("Life: "+i+"");

                //con esto repartimos el total de puntos de experiencia
                strB.setMax(expoints-i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        strB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                strV.setText("Strength: "+i+"");
                lifeB.setMax(expoints-i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //BUTTON

        finishB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Guardamos los resultados en varibles

                strval=strB.getProgress();
                lifeval=lifeB.getProgress();
                //las guardamos en el Game Manager

                GameManager.setStats(strval,lifeval);
                GameManager.setEnemyStats(2,2,1);

               int expval= expoints-(strval+lifeval);

                GameManager.setExp(expval);
                Battle.playerCurrentLife=100;
                Battle.end=false;
                startActivity(new Intent(LevelUp.this,Battle.class));



            }
        });

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }








    }
}