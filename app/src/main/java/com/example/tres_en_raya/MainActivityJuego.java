package com.example.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityJuego extends AppCompatActivity {


    private TextView nombre1, nombre2;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, bct;
    private Button[][] table = new Button[3][3];
    private boolean turnX = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);


        nombre1=findViewById(R.id.tv1);
        nombre2=findViewById(R.id.tv2);

        String recibeNombre1= getIntent().getStringExtra("nombresj1");
        String recibeNombre2= getIntent().getStringExtra("nombresj2");
        nombre1.setText("J1: "+recibeNombre1);
        nombre2.setText("J2: "+recibeNombre2);
       ;


        b1 = findViewById(R.id.casilla1);
        b2 = findViewById(R.id.casilla2);
        b3 = findViewById(R.id.casilla3);
        b4 = findViewById(R.id.casilla4);
        b5 = findViewById(R.id.casilla5);
        b6 = findViewById(R.id.casilla6);
        b7 = findViewById(R.id.casilla7);
        b8 = findViewById(R.id.casilla8);
        b9 = findViewById(R.id.casilla9);

        // Inicializa los botones en la matriz 'table'
        table[0][0] = b1;
        table[0][1] = b2;
        table[0][2] = b3;
        table[1][0] = b4;
        table[1][1] = b5;
        table[1][2] = b6;
        table[2][0] = b7;
        table[2][1] = b8;
        table[2][2] = b9;

        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        for (Button[] row : table) {
            for (Button button : row) {
                button.setOnClickListener(v -> onButtonClick(button));
            }
        }
    }

    private void onButtonClick(Button button) {

        String j1= nombre1.getText().toString();
        String j2= nombre2.getText().toString();

        if (button.getText().toString().equals("")) {
            if (turnX) {
                button.setText("x");
            } else {
                button.setText("O");
            }

            if (checkWinner()) {
                String ganador = turnX ? j1 : j2;
                Toast.makeText(this, "¡Jugador " + ganador + " gana!", Toast.LENGTH_SHORT).show();
                resetGame();
            } else if (isBoardFull()) {
                Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                resetGame();
            } else {
                turnX = !turnX;
            }
        } else {
            Toast.makeText(this, "Casilla ocupada, elige otra", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkWinner() {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (table[i][0].getText().toString().equals(table[i][1].getText().toString())
                    && table[i][1].getText().toString().equals(table[i][2].getText().toString())
                    && !table[i][0].getText().toString().equals("")) {
                return true;
            }
        }

        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (table[0][i].getText().toString().equals(table[1][i].getText().toString())
                    && table[1][i].getText().toString().equals(table[2][i].getText().toString())
                    && !table[0][i].getText().toString().equals("")) {
                return true;
            }
        }

        // Verificar diagonal principa
        if (table[0][0].getText().toString().equals(table[1][1].getText().toString())
                && table[1][1].getText().toString().equals(table[2][2].getText().toString())
                && !table[0][0].getText().toString().equals("")) {
            return true;
        }

        // Verificar diagonal secundaria
        if (table[0][2].getText().toString().equals(table[1][1].getText().toString())
                && table[1][1].getText().toString().equals(table[2][0].getText().toString())
                && !table[0][2].getText().toString().equals("")) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].getText().toString().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j].setText("");
            }
        }
        turnX = true;
    }

}