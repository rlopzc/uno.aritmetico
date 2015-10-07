package com.videojuegos.basedatos;

public class DBTurno {

    // variables privadas
    int id;
    int id_partida;
    int turno;
    String jugador;
    String color;
    String operacion_mazo;
    String operacion_jugada;
    String valor;
    private String fecha;

    // Constructor vacio
    public DBTurno() {
    }


    // Constructor
    public DBTurno(int id_partida, int turno, String jugador, String color, String operacion_mazo, String operacion_jugada, String valor) {
        this.id_partida = id_partida;
        this.turno = turno;
        this.jugador = jugador;
        this.color = color;
        this.operacion_mazo = operacion_mazo;
        this.operacion_jugada = operacion_jugada;
        this.valor = valor;

    }

    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // getting name
    public int getIdPartidaDB() {
        return this.id_partida;
    }

    // setting name
    public void setIdPartidaDB(int id_partida) {
        this.id_partida = id_partida;
    }

    // getting turno
    public int getTurnoDB() {
        return this.turno;
    }

    // setting turno
    public void setTurnoDB(int turno) {
        this.turno = turno;
    }

    // getting ID
    public String getJugadorDB() {
        return this.jugador;
    }

    // setting id
    public void setJugadorDB(String jugador) {
        this.jugador = jugador;
    }

    // getting ID
    public String getColorDB() {
        return this.color;
    }

    // setting id
    public void setColorDB(String color) {
        this.color = color;
    }

    // getting ID
    public String getOperacionMazoDB() {
        return this.operacion_mazo;
    }

    // setting id
    public void setOperacionMazoDB(String operacion_mazo) {
        this.operacion_mazo = operacion_mazo;
    }

    // getting ID
    public String getOperacionJugadaDB() {
        return this.operacion_jugada;
    }

    // setting id
    public void setOperacionJugadaDB(String operacion_jugada) {
        this.operacion_jugada = operacion_jugada;
    }

    // getting ID
    public String getValorDB() {
        return this.valor;
    }

    // setting id
    public void setValorDB(String valor) {
        this.valor = valor;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "DBTurno{" +
                "id=" + id +
                ", id_partida=" + id_partida +
                ", turno=" + turno +
                ", jugador='" + jugador + '\'' +
                ", color='" + color + '\'' +
                ", operacion_mazo='" + operacion_mazo + '\'' +
                ", operacion_jugada='" + operacion_jugada + '\'' +
                ", valor='" + valor + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
