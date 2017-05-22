package com.example.walter.conocecr;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jean Abarca on 21-May-17.
 */

public class ObtenerWebService extends AsyncTask<Void, Integer, String> {


    String urlconexion;
    public ObtenerWebService(String urlconexion) {
        this.urlconexion = urlconexion;
    }

    @Override
        protected String doInBackground(Void... params) {

            String salida="";
            int i=0, j=0;

            try {

                URL url = new URL(urlconexion); // Url de donde queremos obtener información
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                //Añadimos una cabecera HTTP para que identificarnos y evitar obtener un error de aquellos
                //servidores que prohiben la respuesta a aquellos clientes que no se identifican.
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                int respuesta = connection.getResponseCode();

                if (respuesta==HttpURLConnection.HTTP_OK){

                    BufferedReader lector = new BufferedReader (
                            new InputStreamReader(connection.getInputStream()));
                    String linea = lector.readLine();
                    // esto puede variar segun la estructura del html
                    while (linea != null) {
                        //Si encontramos la etiqueta <title> podemos recuperar la noticia
                        if (linea.indexOf("<title><![CDATA") >= 0) {
                            i = linea.indexOf("<title>")+16;
                            j = linea.indexOf("</title>")-3;
                            salida += linea.substring(i,j);
                            salida += "\n----------------\n";
                        }
                        //Leemos la siguiente línea
                        linea = lector.readLine();
                    }
                    lector.close();
                } else {
                    salida = "No encontrado";
                }
                connection.disconnect();

                return salida;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onPostExecute(String resultado) {

            //textView.append(resultado);

            super.onPostExecute(resultado);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }