package com.videojuegos.jugador;

/**
 * Created by Lalo on 10/6/15.
 */
public class Marcador {

    private static Marcador instanciaMarcador;


    private Marcador() {
    }


    public static Marcador obtenerInstanciaMarcador() {
        if (instanciaMarcador == null)
            instanciaMarcador = new Marcador();

        return instanciaMarcador;
    }


}
