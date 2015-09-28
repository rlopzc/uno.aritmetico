package com.videojuegos.jugador;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector3;

@SuppressWarnings("serial")
public class Movimiento implements Serializable{
	private Vector3 movimiento;
	private int idCarta;

	public Movimiento(int idCarta, Vector3 movimiento) {
		this.movimiento = movimiento;
		this.idCarta = idCarta;
	}
	
	public int getIdCarta(){
		return idCarta;
	}

	public Vector3 getMoveVector() {
		return this.movimiento;
	}
	
	public String toString(){
		return "ID: " + idCarta + "\tX: " + movimiento.x + "\tY: " + movimiento.y;
	}

}
