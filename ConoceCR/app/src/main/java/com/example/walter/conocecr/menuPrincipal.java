package com.example.walter.conocecr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // alambramos el boton

        Button mb = (Button) findViewById(R.id.btn1);

        //Programamos el evento onclick

        mb.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {

                Intent intento = new Intent(getApplicationContext(), Act_pregunta.class);
                startActivity(intento);

                // escriba lo que desea hacer

            }

        });
    }
}
