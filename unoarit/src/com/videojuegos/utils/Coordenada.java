package com.videojuegos.utils;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector3;

@SuppressWarnings("serial")
public class Coordenada implements Serializable{
	
	private Vector3 v;
	
	public Coordenada(Vector3 v){
		this.v = v;
	}
	
	public String toString(){
		return "x: " + v.x + "\ty: " + v.y + "\tz: " + v.z;
	}

}
