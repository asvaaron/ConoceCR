package com.example.walter.conocecr;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class menuPrincipal extends Base_Activity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        OnclickDelButton(R.id.btn1);
        OnclickDelButton(R.id.btn_puntajes);



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
                Intent intento;
                switch (v.getId()) {

                    case R.id.btn1:
                        MensajeEdText();

                        break;

                    case R.id.btn_puntajes:
                         intento = new Intent(getApplicationContext(), Puntajes_Jugadores.class);
                        startActivity(intento);
                        break;
                    default:
                        break; }// fin de casos
            }// fin del onclick
        });
    }



    private void MensajeEdText() {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.popupusuario, null);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = (EditText) dialoglayout.findViewById(R.id.edUsuario);

        alert.setView(dialoglayout);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                 usr = input.getText().toString().trim();

                //Llamar WS guardarUsuario
                EnviarPuntajesWS hiloconexion = new EnviarPuntajesWS();
                hiloconexion.delegate = menuPrincipal.this;
                hiloconexion.execute("guardarUsuario.php?usr="+usr);

            }
        });

        alert.show();



    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject obj = new JSONObject(output);
        String reslt = obj.getString("resultado").trim();
            idusr = obj.getString("id_usuario").trim();
            if(reslt.equalsIgnoreCase("OK")){
                Intent intento = new Intent(getApplicationContext(), Act_pregunta.class);
                startActivity(intento);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
