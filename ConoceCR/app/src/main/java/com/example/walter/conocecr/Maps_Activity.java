package com.example.walter.conocecr;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.walter.conocecr.R.id.map;

public class Maps_Activity extends Base_Activity implements OnMapReadyCallback, AsyncResponse {
    private int cnt=0;
    private boolean resp = false;
    private GoogleMap mMap;
    private LatLng respuesta_coordenadas;// Respuesta Corrdenadas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        Button Mi_button = (Button) findViewById(R.id.btn_cambiar_tipo_mapa);
        registerForContextMenu(Mi_button);

        ObtenerCoordenadasWS hiloconexion = new ObtenerCoordenadasWS();
        hiloconexion.delegate = this;
        hiloconexion.execute();

        cnt=3;
        //respuesta_coordenadas= new LatLng(10.160180908178564,-83.97468566894531); // Parque Nacioal braulio Carillo

        OnclickDelButton(R.id.btn_limpiar_marca);
        OnclickDelButton(R.id.btn_respuesta);
        OnclickDelButton(R.id.btn_location_info);



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

                    case R.id.btn_limpiar_marca:
                        mMap.clear();
                        break;

                    case R.id.btn_respuesta:
                        mostrar_respuesta();
                        break;

                    case R.id.btn_location_info:

                        //Uri uri = Uri.parse("https://es.wikipedia.org/wiki/Parque_nacional_Braulio_Carrillo");
                        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        //startActivity(intent);

                    default:
                        break;
                }
            }// fin del onclick
        });
    }// fin de OnclickDelButton

    private void mostrar_respuesta() {
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(respuesta_coordenadas)
                .radius(10000)
                .strokeColor(0x8044FF5D)
                .fillColor(0x7344FF5D));
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

        mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.style_json));

        //LatLngBounds  CR = new LatLngBounds( new LatLng(11.295735, -83.897085), new LatLng(8.047543, -82.900913));
        //mMap.setLatLngBoundsForCameraTarget(CR);
        //limitar mapa a costa rica

        // Add a marker in Sydney and move the camera
        LatLng SJ_CR = new LatLng(9.9280694, -84.09072459999999);
        mMap.addMarker(new MarkerOptions().position(SJ_CR).title("Costa Rica"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SJ_CR));
        int zoomLevel = 7; //Esto puede ir hasta  21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SJ_CR, zoomLevel));
        // Colocar dentro de onMapReady
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marca").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).draggable(true).alpha(0.7f));
                if(respuesta_correcta(respuesta_coordenadas,latLng,10)) {

                    mostrar_respuesta();
                    Mensaje("Respuesta correcta! Posición: (" + latLng + ")");
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    try{
                        Thread.sleep(3000);
                    }catch (InterruptedException e){}
                    resp = true;
                    //pasar a otro activity
                    siguientePregunta();
                }
                else {
                    cnt--;
                    Mensaje("Respuesta Incorrecta, Intente de nuevo! (Intentos restantes: "+cnt+".)");
                    siguientePregunta();
                }
                    //Mensaje("Correcto Posición: (" +calcular_distancia(respuesta_coordenadas,latLng)+ ")");
            }
        });


       UiSettings ui=(UiSettings) mMap.getUiSettings();
        ui.setZoomControlsEnabled(true);
        ui.setCompassEnabled(true);
        ui.setIndoorLevelPickerEnabled(true);
    }

    private void siguientePregunta(){
        if(cnt==0||resp){
            Intent intento = new Intent(getApplicationContext(), Maps_Activity.class);
            startActivity(intento);
            finish();
        }
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
            case R.id.item_map_custom:
                mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                this, R.raw.style_json));
                break;
            default:  Mensaje("No clasificado"); break;
        }

        return true;
    }


    @Override
    public void processFinish(String output) {
        String preg="";
        String correcta = "";
        String[] rsp;
        String coord[];
        String[] respuestas = new String[4];

        try {
            JSONObject obj = new JSONObject(output);
            JSONObject preguntaa = obj.getJSONObject("pregunta");
            JSONObject respuestaa = obj.getJSONObject("respuesta");
            preg=preguntaa.getString("descripcion");
            correcta=respuestaa.getString("descripcion");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rsp = correcta.split("_");
        coord = rsp[1].split(",");
        respuesta_coordenadas = new LatLng(Double.parseDouble(coord[0]),Double.parseDouble(coord[1]));
        TextView pregunta = (TextView) findViewById(R.id.tvpregunta);
        pregunta.setText("¿"+preg+"?");

    }
}
