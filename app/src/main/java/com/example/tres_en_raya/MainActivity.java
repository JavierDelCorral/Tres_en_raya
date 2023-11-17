package com.example.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText jugador1;
    private EditText jugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        jugador1=findViewById(R.id.j1);
        jugador2=findViewById(R.id.j2);
    }


    public void jugar(View view){

        final String nombreJ1= jugador1.getText().toString();

        final String nombreJ2= jugador2.getText().toString();

        if(nombreJ1.isEmpty()|| nombreJ2.isEmpty()){
            Toast.makeText(this, "introduzca los nombres de los jugadores", Toast.LENGTH_LONG).show();
        }else{


            Intent conectar = new Intent(this, MainActivityJuego.class);
            conectar.putExtra("nombresj1",nombreJ1);
            conectar.putExtra("nombresj2",nombreJ2);
            startActivity(conectar);


        }



    }


}