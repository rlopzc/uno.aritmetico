package com.videojuegos.basedatos;

public class DBTurno {

	// variables privadas
	int _id;
	int _id_partida;
	int _turno;
	String _jugador;
	String _color;
	String _operacion_mazo;
	String _operacion_jugada;
	String _valor;

	// Constructor vacio
	public DBTurno(){

	}
	// Constructor
	public DBTurno(int id, int id_partida, int turno, String jugador, String color, String operacion_mazo, String operacion_jugada, String valor){
		this._id = id;
		this._id_partida = id_partida;
		this._turno = turno;
		this._jugador = jugador;
		this._color = color;
		this._operacion_mazo = operacion_mazo;
		this._operacion_jugada = operacion_jugada;
		this._valor = valor;
	}

	// Constructor
	public DBTurno(int id_partida, int turno, String jugador, String color, String operacion_mazo, String operacion_jugada, String valor) {
		this._id_partida = id_partida;
		this._turno = turno;
		this._jugador = jugador;
		this._color = color;
		this._operacion_mazo = operacion_mazo;
		this._operacion_jugada = operacion_jugada;
		this._valor = valor;
	}

	// getting ID
	public int getID(){
		return this._id;
	}

	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public int getIdPartidaDB(){
		return this._id_partida;
	}
	
	// setting name
	public void setIdPartidaDB(int id_partida){
		this._id_partida = id_partida;
	}

	// getting turno
	public int getTurnoDB(){
		return this._turno;
	}

	// setting turno
	public void setTurnoDB(int turno){
		this._turno = turno;
	}

	// getting ID
	public String getJugadorDB(){
		return this._jugador;
	}

	// setting id
	public void setJugadorDB(String jugador){
		this._jugador = jugador;
	}

	// getting ID
	public String getColorDB(){
		return this._color;
	}

	// setting id
	public void setColorDB(String color){
		this._color = color;
	}

	// getting ID
	public String getOperacionMazoDB(){
		return this._operacion_mazo;
	}

	// setting id
	public void setOperacionMazoDB(String operacion_mazo){
		this._operacion_mazo = operacion_mazo;
	}

	// getting ID
	public String getOperacionJugadaDB(){
		return this._operacion_jugada;
	}

	// setting id
	public void setOperacionJugadaDB(String operacion_jugada){
		this._operacion_jugada = operacion_jugada;
	}

	// getting ID
	public String getValorDB(){
		return this._valor;
	}

	// setting id
	public void setValorDB(String valor){
		this._valor = valor;
	}


	

}
