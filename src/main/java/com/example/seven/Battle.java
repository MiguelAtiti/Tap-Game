package com.example.seven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Battle extends AppCompatActivity {


//Variables
    static int lifeval;
    static int strval;
    static boolean end;
    static int CurrentProgress=100;
    static int playerCurrentLife=100;
    static int strEnemy=3;
    static int enemyMax;
    static int enemyMin;
    static int enemydmg;
    static int enemydef;
    int exp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        //layout
        TextView enemyV=(TextView) findViewById(R.id.textView2);
        TextView playerV=(TextView)  findViewById(R.id.textView);

        ProgressBar playerLifeB=(ProgressBar) findViewById(R.id.progressBar2);
        ProgressBar enemyLifeB=(ProgressBar) findViewById(R.id.progressBar);

        ConstraintLayout cl=(ConstraintLayout) findViewById(R.id.cl);


        //Mostramos los nombres

        enemyV.setText(GameManager.getNameE());
        playerV.setText(GameManager.getName());


       //Seteamos las stads del player y el enemigo

        lifeval=GameManager.getLife();

        playerLifeB.setProgress(playerCurrentLife);

        strval=GameManager.getStr();




        enemydmg=GameManager.getStrE();
        enemydef=GameManager.getLifeE();

        enemyMax=GameManager.getEnemyPowerMax();
        enemyMin=GameManager.getEnemyPowerMin();
        System.out.println("max:"+enemyMax);
        System.out.println("min:"+enemyMin);




        //Iniciamos la clase asincrona
        new Wait().execute();



        //Player Gameplay cuando hacemos clic le quitamos vida al enemigo
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("enemy:"+CurrentProgress);

                int finaldamage=(strval-enemydef);
                if (finaldamage<=0){
                    finaldamage=1;
                }

                CurrentProgress =  (CurrentProgress-finaldamage);
                enemyLifeB.setProgress(CurrentProgress);

                if (CurrentProgress <=0){

                    playerCurrentLife=100;
                    CurrentProgress=100;
                    end=true;
                   exp= GameManager.getExp();
                    GameManager.setExp(exp+1);
                    startActivity(new Intent(Battle.this,LevelUp.class));

                }

                if (playerCurrentLife<=0){

                    startActivity(new Intent(Battle.this,TheEnd.class));





                }




            }
        });






        //ENEMY IA















        //Cerrar actividad cuando se ordene
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
















    }


   /*Mediante el uso de una clase Asyncrona, creo un bucle infinito para que el enemigo
   realice un ataques de forma inpredecible
    */

    class Wait extends AsyncTask<Void, Void, Void>
    {




        protected Void doInBackground(Void... params)
        {
            try
            {

               //generamos un valor random para la frecuencia de ataque del enemigo y dormimos el proceso
                final int min = 1000;
                final int max = 3000;
                final int random = new Random().nextInt((max - min) + 1) + min;




               System.out.println(random);


                Thread.sleep(random);
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
                System.out.println("f");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            System.out.println(lifeval);
            //Generamos un numero random para el daño del enemigo
            int arandom = new Random().nextInt((enemyMax - enemyMin) + 1) + enemyMin;

              System.out.println("random"+arandom);


            //Este daño random se le restara a la defensa del juegador y se le sumara la fuerza del enemigo
                    arandom= (arandom-lifeval)+enemydmg;
            System.out.println("random"+arandom);
                     playerCurrentLife=((playerCurrentLife-arandom));
                     System.out.println("lifess"+playerCurrentLife);
            ProgressBar playerLifeB=(ProgressBar) findViewById(R.id.progressBar2);

            playerLifeB.setProgress(playerCurrentLife);
           System.out.println(end);
           //volvemos a iniciar el proceso hasta quese le acabe la vida a nuestro jugador
          if (playerCurrentLife>=0) {
              new Wait().execute();
          }
        }

    }













}