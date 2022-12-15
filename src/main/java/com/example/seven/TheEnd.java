package com.example.seven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TheEnd extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_end);



        TextView defeated=(TextView) findViewById(R.id.textView8);


        Button finishB=(Button) findViewById(R.id.finishBtn);


        int numdefeated=GameManager.getDefeated();
        defeated.setText("Enemy defeated: "+String.valueOf(numdefeated));


       new Bbdd().execute();

        finishB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);




            }
        });





    }

    //Creamos una clase asincrona para poder retronar el nombre que guardamos en la base de datos

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

            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    System.out.println("Value is: " + value);
                    TextView name=(TextView) findViewById(R.id.textView7);
                    name.setText(value);
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