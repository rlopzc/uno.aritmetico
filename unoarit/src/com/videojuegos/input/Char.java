package com.videojuegos.input;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.Load;

public class Char {

	private char caracter;
	private int codigo;
	private Sprite sprite;
	private float ancho, alto, rotacion;
	private String nombre;

	public Char(String nombre, char caracter, int codigo, float ancho,
			float alto, float rotacion) {
		inicializa(nombre, caracter, codigo, ancho, alto, rotacion);
	}

	public Char(Char c) {
		inicializa(c.getNombre(), c.getCaracter(), c.getCodigo(), c.getAncho(),
				c.getAlto(), c.getRotacion());
	}

	private void inicializa(String nombre, char caracter, int codigo,
			float ancho, float alto, float rotacion) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.codigo = codigo;
		this.ancho = ancho;
		this.alto = alto;
		this.rotacion = rotacion;
		this.sprite = Load.atlas.createSprite(nombre);
		if (sprite != null) {
			AtsTM.rotar(sprite, this.ancho, this.alto, this.rotacion);
		}
	}

	public char getCaracter() {
		return caracter;
	}

	public int getCodigo() {
		return codigo;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public float getAncho() {
		return ancho;
	}

	public float getAlto() {
		return alto;
	}

	public float getRotacion() {
		return rotacion;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
		if (this.sprite != null)
			this.sprite.setRotation(rotacion);
	}

	public String getNombre() {
		return nombre;
	}

}
