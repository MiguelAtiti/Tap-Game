package com.example.seven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables
    String nameval;
    int lifeval;
    int strval;
    int exp;
    int expoints=5;

    //conexion con la base de datos
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout
        EditText name=(EditText) findViewById(R.id.input);
        TextView lifeV=(TextView) findViewById(R.id.lifeView);
        TextView expV=(TextView) findViewById(R.id.textView6);
        TextView strV=(TextView)  findViewById(R.id.strV);
        SeekBar strB=(SeekBar) findViewById(R.id.strBar);
        SeekBar lifeB=(SeekBar) findViewById(R.id.lifeBar);
        Button finishB=(Button) findViewById(R.id.finishBtn);



        //INPUT SET

           expV.setText("Points: "+expoints);








        //SEEKBAR


        lifeB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                lifeV.setText("Life: "+i+"");
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
               nameval=name.getText().toString();
               strval=strB.getProgress();
               lifeval=lifeB.getProgress();
               //las guardamos en el Game Manager
                GameManager.setName(nameval);
               exp= expoints-(strval+lifeval);
               GameManager.setStats(strval,lifeval);
               GameManager.setExp(exp);


               GameManager.setEnemyStats(2,3,0);
               GameManager.setNameE("Artorias");


                //Ejecutamos la clase asincrona
                new Bbdd().execute();

                //Iniciamos el juego
                startActivity(new Intent(MainActivity.this,Battle.class));



            }









        });

       //Cerrar la actividad cuando se ordene
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }






    }


    //Clase asincrona para enviar el nombre a la base de datos despues de esperar unos segundos

    class Bbdd extends AsyncTask<Void, Void, Void>
    {




        protected Void doInBackground(Void... params)
        {
            try
            {

                Thread.sleep(5000);

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
            DatabaseReference myRef = database.getReference("message");
            myRef.setValue(nameval);
            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    System.out.println("Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    System.out.println("failllllll:"+ error.toException());
                }
            });



        }



    }
}