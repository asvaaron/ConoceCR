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




public class ObtenerWebService extends AsyncTask<String, Integer, String> {
    public AsyncResponse delegate = null;
    @Override
    protected String doInBackground(String... params) {

        String conn_aaron = "http://192.168.1.107/ConoceCR/obtenerPregunta_aleatoria.php";
        //
        String conn_walter = "http://10.0.3.3/CCR_ws/obtenerPregunta_aleatoria.php";

        String devuelve = "";

        URL url = null; // Url de donde queremos obtener información
        try {
            // Cambiar conexion dependiendo de sus especificaciones

            url = new URL(conn_aaron);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
            connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES) Ejemplo HTTP");


            int respuesta = connection.getResponseCode();

            StringBuilder result = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK){


                InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                // StringBuilder.

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);        // Paso toda la entrada al StringBuilder
                }

                //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                //JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                //Accedemos al vector de resultados
                //JSONObject resultJSON = respuestaJSON.getJSONObject("pregunta");   // results es el nombre del campo en el JSON

                //Vamos obteniendo todos los campos que nos interesen.
                //En este caso obtenemos la primera dirección de los resultados.
                //String preg="no se recibio la info";


                    //preg = resultJSON.getString("descripcion");
                    //res =  resultJSON.getJSONObject(0).getString("respuestas");
                    // dentro del results pasamos a Objeto la seccion formated_address

                devuelve = result.toString();   // variable de salida que mandaré al onPostExecute para que actualice la UI

            }else{
                devuelve = connection.getErrorStream().toString();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        //} catch (JSONException e) {
          //  e.printStackTrace();
        }
        return devuelve;
    }

    @Override
    protected void onCancelled(String aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        delegate.processFinish(aVoid);
        //super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
       // delegate.processFinish("D:");
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

}
