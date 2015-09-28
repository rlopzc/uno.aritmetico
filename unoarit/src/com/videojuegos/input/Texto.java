package com.videojuegos.input;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.cartas.Boton;

public class Texto {
	private String texto;
	private ArrayList<Letra> letras;
	private int maxNumCaracteres, minNumCaracteres;
	private float rotacion, anchoCont, altoCont, posX, posY, anchoLetra,
			altoLetra, r, g, b, a;
	private Boton btnCont;

	public Texto(float posX, float posY, float anchoCont, float altoCont,
			float rotacion, float anchoLetra, float altoLetra) {
		inicializar(posX, posY, anchoCont, altoCont, rotacion, anchoLetra,
				altoLetra, 0, -1);
	}

	public Texto(float posX, float posY, float anchoCont, float altoCont,
			float rotacion, float anchoLetra, float altoLetra,
			int minNumCaracteres, int maxNumCaracteres) {
		inicializar(posX, posY, anchoCont, altoCont, rotacion, anchoLetra,
				altoLetra, minNumCaracteres, maxNumCaracteres);
	}

	private void inicializar(float posX, float posY, float anchoCont,
			float altoCont, float rotacion, float anchoLetra, float altoLetra,
			int minNumCaracteres, int maxNumCaracteres) {
		this.texto = "";
		this.posX = posX;
		this.posY = posY;
		this.anchoCont = anchoCont;
		this.altoCont = altoCont;
		this.rotacion = rotacion;
		this.anchoLetra = anchoLetra;
		this.altoLetra = altoLetra;
		this.minNumCaracteres = minNumCaracteres;
		this.maxNumCaracteres = maxNumCaracteres;
		this.letras = new ArrayList<Letra>();
		this.btnCont = new Boton(null, this.posX, this.posY, this.anchoCont,
				this.altoCont, this.rotacion);
	}

	public void setBackgroungColor(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public void setPosicion(float x, float y) {
		this.posX = x;
		this.posY = y;
	}

	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
		if (letras != null) {
			for (int i = 0; i < letras.size(); i++) {
				letras.get(i).getCaracter().setRotacion(rotacion);
			}
		} else {
			letras = new ArrayList<Letra>();
		}
	}

	private float inicioX() {
		int numChar = texto.length();
		int centro = numChar % 2;
		float posX = this.posX;

		if (centro == 0) {
			posX = posX - ((this.anchoLetra + 0.01f) * ((numChar / 2) - 2));
		} else {
			posX = posX - ((this.anchoLetra + 0.01f) * ((numChar / 2) - 1));
		}

		return posX;
	}

	private float inicioY() {
		return this.posY - this.altoLetra - 0.01f;
	}

	public ArrayList<Letra> setTexto(String texto) {
		if (maxNumCaracteres == -1) {
			this.texto = texto;
		} else {
			if (texto.length() >= minNumCaracteres) {
				if (texto.length() <= maxNumCaracteres)
					this.texto = texto;
				else
					this.texto = texto.substring(0, maxNumCaracteres);
			} else {
				this.texto = "";
			}
		}

		letras = new ArrayList<Letra>();
		Letra l;
		this.texto = this.texto.toUpperCase();
		float inicioX = inicioX();
		float inicioY = this.posY;

		for (int i = 0; i < this.texto.length(); i++) {
			try {
				l = new Letra(this.texto.charAt(i));
				l.getCaracter().setRotacion(rotacion);
				if (l.getCaracter().getCaracter() == '\n') {
					inicioX = inicioX();
					inicioY = inicioY();
					l.setPosicion(inicioX, inicioY);
				} else {
					l.setPosicion(inicioX, inicioY);
				}
				letras.add(l);
				inicioX += l.getCaracter().getAncho() + 0.01;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return letras;
	}

	public void dibujarTexto(SpriteBatch spriteBatch) {
		try {
			this.btnCont.dibujar(spriteBatch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < letras.size(); i++) {
			Sprite sprite = letras.get(i).getCaracter().getSprite();
			if (sprite != null) {
				try {
					sprite.draw(spriteBatch);
					sprite.setColor(r, g, b, a);
				} catch (Exception e) {

				}
			}
		}
	}

	public boolean minNumCaracteres() {
		if (texto.length() >= minNumCaracteres)
			return true;
		return false;
	}

	public boolean maxNumCaracteres() {
		if (texto.length() >= maxNumCaracteres)
			return true;
		return false;
	}

	public void setMinMaxNumCaracteres(int minimo, int maximo) {
		this.minNumCaracteres = minimo;
		this.maxNumCaracteres = maximo;
	}

	public boolean meTocaste() {
		if (this.btnCont.meTocaste()) {
			return true;
		}
		return false;
	}
	
	public boolean meTocaste(float  r,float g,float b,float a) {
		if (this.btnCont.meTocaste()) {
			setBackgroungColor(r, g, b, a);
			return true;
		}
		return false;
	}


}
