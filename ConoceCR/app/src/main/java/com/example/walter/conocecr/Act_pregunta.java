package com.example.walter.conocecr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Act_pregunta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_pregunta);
        TextView pregunta = (TextView) findViewById(R.id.tvpreg);

        String str="http://localhost/CCR_ws/CCR_WS.asmx/obtenerPregunta_aleatoria";
        try{
            URL url=new URL(str);
            URLConnection urlc=url.openConnection();
            BufferedReader bfr=new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String line;
            while((line=bfr.readLine())!=null) {
                JSONArray jsa = new JSONArray(line);

                    JSONObject jo = (JSONObject) jsa.get(0);
                    pregunta.setText(jo.getString("descripcion"));  //tag name "deal_title",will return value that we save in title string
                    //des = jo.getString("deal_description");

            }
        } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        //ObtenerWebService hiloconexion;
        //hiloconexion = new ObtenerWebService();
        //hiloconexion.execute();

    }


}


