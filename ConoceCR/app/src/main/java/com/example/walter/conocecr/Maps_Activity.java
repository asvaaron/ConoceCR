package com.example.walter.conocecr;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Maps_Activity extends Base_Activity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng respuesta_coordenadas;// Respuesta Corrdenadas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button Mi_button = (Button) findViewById(R.id.btn_cambiar_tipo_mapa);
        registerForContextMenu(Mi_button);
        respuesta_coordenadas= new LatLng(10.160180908178564,-83.97468566894531); // Parque Nacioal braulio Carillo

    }

    private void set_respuesta_coordenadas(double lat, double lon){
        this.respuesta_coordenadas= new LatLng(lat, lon);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng SJ_CR = new LatLng(9.9280694, -84.09072459999999);
        mMap.addMarker(new MarkerOptions().position(SJ_CR).title("Costa Rica"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SJ_CR));
        int zoomLevel = 16; //Esto puede ir hasta  21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SJ_CR, zoomLevel));
        // Colocar dentro de onMapReady
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Mi marca"));

                if(respuesta_correcta(respuesta_coordenadas,latLng,10))
                Mensaje("Correcto Posición: (" +calcular_distancia(respuesta_coordenadas,latLng)+ ")");
                else
                    Mensaje("Respuesta Incorrecta, Intente de neuvo");
                //Mensaje("Correcto Posición: (" +calcular_distancia(respuesta_coordenadas,latLng)+ ")");
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        switch (v.getId()) {
            case R.id.btn_cambiar_tipo_mapa:
                MenuInflater infla =getMenuInflater();
                infla.inflate(R.menu.tipo_mapa_menu, menu);
                break;

            default:  Mensaje("No clasificado"); break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    private double calcular_distancia(LatLng primero, LatLng segundo){
        //Coordenadas de los puntos entre los que se calcula la distancia
        double distancia;
        Location locationA = new Location("");
        locationA.setLatitude(primero.latitude);
        locationA.setLongitude(primero.longitude);
        Location locationB = new Location("");
        locationB.setLatitude(segundo.latitude);
        locationB.setLongitude(segundo.longitude);
        distancia = locationA.distanceTo(locationB)/1000;
        //La distancia se da en kilomotros (km) SI
       return distancia;
    }
    private boolean respuesta_correcta(LatLng primero, LatLng segundo, int dist_min){
        if(calcular_distancia(primero,segundo)<=dist_min)
            return true;
        else
            return false;
    }


    private void TrazarLinea(LatLng primero, LatLng segundo) {
        // creamos las líneas
        PolylineOptions linea = new PolylineOptions()
                .add(new LatLng(primero.latitude, primero.longitude))
                .add(new LatLng(segundo.latitude, segundo.longitude));
        // grosor y color
        linea.width(8);
        linea.color(Color.RED);
        // trazamos la línea
        mMap.addPolyline(linea);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int opcionseleccionada = item.getItemId();
        switch (opcionseleccionada) {
            case R.id.item_map_hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.item_map_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.item_map_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.item_map_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:  Mensaje("No clasificado"); break;
        }

        return true;
    }





}
