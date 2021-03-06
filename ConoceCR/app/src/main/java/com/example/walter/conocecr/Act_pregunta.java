package com.example.walter.conocecr;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Act_pregunta extends Base_Activity implements AsyncResponse{

   public int correcta = 6;
    int[] ids_respuesta= new int[4];
    public static String id_pregunta;
    public static int id_seleccionada=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_pregunta);

        //asyncTask.delegate = this;
        ObtenerPreguntasWS hiloconexion = new ObtenerPreguntasWS();
        hiloconexion.delegate = this;
        hiloconexion.execute();
        OnclickDelButton(R.id.btnA);
        OnclickDelButton(R.id.btnB);
        OnclickDelButton(R.id.btnC);
        OnclickDelButton(R.id.btnD);
        preguntas_seleccion--;
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
                String corr="Respuesta correcta!!";
                String incorr="Respuesta incorrecta!!";
                AlertDialog.Builder alert = new AlertDialog.Builder(Act_pregunta.this);
                alert.setTitle("ConoceCR");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intento = null;
                        loguearRespuesta();
                        if(preguntas_seleccion>0){
                         intento = new Intent(getApplicationContext(), Act_pregunta.class);
                        }else{
                             intento = new Intent(getApplicationContext(), Maps_Activity.class);
                        }
                        startActivity(intento);
                        finish();
                    }
                });


                switch (v.getId()) {

                    case R.id.btnA:
                        id_seleccionada=ids_respuesta[0];
                        if(correcta==ids_respuesta[0]){
                        alert.setMessage(corr);
                        }else{
                            alert.setMessage(incorr);
                        }
                        break;

                    case R.id.btnB:
                        id_seleccionada=ids_respuesta[1];
                        if(correcta==ids_respuesta[1]){
                            alert.setMessage(corr);
                        }else{
                            alert.setMessage(incorr);
                        }
                        break;

                    case R.id.btnC:
                        id_seleccionada=ids_respuesta[2];
                        if(correcta==ids_respuesta[2]){
                            alert.setMessage(corr);
                        }else{
                            alert.setMessage(incorr);
                        }
                        break;

                    case R.id.btnD:
                        id_seleccionada=ids_respuesta[3];
                        if(correcta==ids_respuesta[3]){
                            alert.setMessage(corr);
                        }else{
                            alert.setMessage(incorr);
                        }
                        break;
                    default:break; }// fin de casos
                alert.show();

            }// fin del onclick
        });
    }// fin de OnclickDelButton

    private void loguearRespuesta() {
        EnviarPuntajesWS hiloconexion = new EnviarPuntajesWS();
        hiloconexion.delegate = null;
        int pp =0;
        if(correcta==id_seleccionada){pp=puntaje_porPregunta;}
        hiloconexion.execute("loguearRespuesta.php?usr="+idusr+"&pnt="+pp+"&preg="+id_pregunta+"&resp_sel="+id_seleccionada);
    }

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();}

    @Override
    public void onBackPressed() {

        Intent intento = new Intent(getApplicationContext(), menuPrincipal.class);
        startActivity(intento);
        return;
    }


    @Override
    public void processFinish(String output) {
        String preg="";
        String[] respuestas = new String[4];

        try {
            JSONObject obj = new JSONObject(output);
            JSONObject preguntaa = obj.getJSONObject("pregunta");
            JSONArray respuestaa = obj.getJSONArray("respuestas");
            id_pregunta = preguntaa.getString("id_pregunta");
            preg=preguntaa.getString("descripcion");
            correcta=Integer.parseInt(preguntaa.getString("respuesta_correcta"));
            for(int i=0;i<respuestaa.length();i++){
            ids_respuesta[i] = Integer.parseInt(respuestaa.getJSONObject(i).getString("id_respuesta"));
            respuestas[i] = respuestaa.getJSONObject(i).getString("descripcion");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView pregunta = (TextView) findViewById(R.id.tvpreg);
        Button btn1 = (Button) findViewById(R.id.btnA);
        Button btn2 = (Button) findViewById(R.id.btnB);
        Button btn3 = (Button) findViewById(R.id.btnC);
        Button btn4 = (Button) findViewById(R.id.btnD);

        pregunta.setText("¿"+preg+"?");
        btn1.setText(respuestas[0]);
        btn2.setText(respuestas[1]);
        btn3.setText(respuestas[2]);
        btn4.setText(respuestas[3]);
    }
}





