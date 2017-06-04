package com.example.walter.conocecr;

/**
 * Created by asvaa on 4/6/2017.
 */

public class Jugador {
    private String Nombre;
    private String Puntaje;
    private String  Fecha;

    public Jugador(String nombre, String puntaje, String fecha) {
        Nombre = nombre;
        Puntaje = puntaje;
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getPuntaje() {
        return Puntaje;
    }

    public String getFecha() {
        return Fecha;
    }
}
