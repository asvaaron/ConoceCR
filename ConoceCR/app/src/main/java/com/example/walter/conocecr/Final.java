package com.example.walter.conocecr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Final extends Base_Activity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        EnviarPuntajesWS hiloconexion = new EnviarPuntajesWS();
        hiloconexion.delegate = this;
        hiloconexion.execute("calcularPuntaje.php?id_usr="+idusr);
    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject obj = new JSONObject(output);
            String puntaje = obj.getString("puntaje").trim();
            idusr = obj.getString("id_usuario").trim();

            TextView text_puntaje = (TextView) findViewById(R.id.textView_puntake);

            TextView text_idusuario = (TextView) findViewById(R.id.textView_idjugador);

            text_puntaje.setText(puntaje);
            text_idusuario.setText(idusr);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
