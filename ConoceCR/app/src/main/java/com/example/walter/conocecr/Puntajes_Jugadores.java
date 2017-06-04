package com.example.walter.conocecr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Puntajes_Jugadores extends Base_Activity implements AsyncResponse  {
    ArrayList<Jugador> milista ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes_jugadores);
        milista = new ArrayList<>();
        milista.add(new Jugador("Nombre:","Puntaje: ", "Fecha:"));
        EnviarPuntajesWS hiloconexion = new EnviarPuntajesWS();
        hiloconexion.delegate = this;
        hiloconexion.execute("obtenerPuntajes.php");



    }

    private void LlenarListView() {
        ArrayAdapter<Jugador> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listview_puntajes);
        list.setAdapter(adapter);
    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject obj = new JSONObject(output);
            JSONArray jsonMainArr = obj.getJSONArray("puntajes");
            for (int i = 0; i < jsonMainArr.length(); i++) {  // **line 2**
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                String puntaje = childJSONObject.getString("Puntaje");
                String nombre_usuario= childJSONObject.getString("Nombre_Usuario");
                String fecha= childJSONObject.getString("Fecha");
                milista.add(new Jugador(nombre_usuario,puntaje,fecha));
            }
            LlenarListView();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private class MyListAdapter extends ArrayAdapter<Jugador> {
        public MyListAdapter() {
            super(Puntajes_Jugadores.this, R.layout.desplegar_objetos, milista);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.desplegar_objetos, parent, false);
            }
            Jugador ObjetoActual = milista.get(position);
            // Fill the view
            TextView elatributo01 = (TextView) itemView.findViewById(R.id.atributo1);
            elatributo01.setText(ObjetoActual.getNombre());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.atributo2);
            elatributo02.setText("" + ObjetoActual.getPuntaje());
            TextView elatributo03 = (TextView) itemView.findViewById(R.id.atributo3);
            elatributo03.setText("" + ObjetoActual.getFecha());
            return itemView;
        }
    }
}
