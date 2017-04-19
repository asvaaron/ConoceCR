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

        OnclickDelButton(R.id.btn1);
        OnclickDelButton(R.id.btn_mapa);
    }


    public void OnclickDelButton(int ref) {

        // Ejemplo  OnclickDelButton(R.id.MiButton);
        // 1 Doy referencia al Button
        View view =findViewById(ref);
        Button miButton = (Button) view;
        //  final String msg = miButton.getText().toString();
        // 2.  Programar el evento onclick
        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.btn1:
                        Intent intento = new Intent(getApplicationContext(), Act_pregunta.class);
                        startActivity(intento);

                        break;

                    case R.id.btn_mapa:
                        intento = new Intent(getApplicationContext(), Maps_Activitygit .class);
                        startActivity(intento);

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton

}
