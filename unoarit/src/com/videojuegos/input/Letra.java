package com.videojuegos.input;

import java.util.ArrayList;

import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsTM;

public class Letra {
	// /////////////////////////////////////////////////////////////////////////
	// numeros
	private static String[] numero = { "cero", "uno", "dos", "tres", "cuatro",
			"cinco", "seis", "siete", "ocho", "nueve" };
	// letras
	private static String[] letra = { "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "enie", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z" };

	private static String[] blanco = { "espacio", "tab", "salto" };

	public static ArrayList<Char> numeros, letras, blancos;

	@SuppressWarnings("unused")
	private static void ascll() {
		// 65 = A entonces 65 + 32 = a
		// minuscula = mayuscula + 32;
		// � = 209 entonces � = 209 + 32 = 241
		for (int i = 0; i < 256; i++) {
			System.out.println((char) i + "\t" + i);
		}
	}

	public static void load() {
		numeros = new ArrayList<Char>();
		letras = new ArrayList<Char>();
		blancos = new ArrayList<Char>();
		// ascll();
		Char c = null;
		int m32 = 0;

		for (int i = 0; i < numero.length; i++) {
			c = new Char(numero[i], ((char) (48 + i)), 48 + i,
					AtsPos.anchoNumero, AtsPos.altoNumero, 0);
			numeros.add(c);
		}

		for (int i = 0; i < letra.length; i++) {
			// Mayuscula
			if (letra[i].equals("enie")) {
				c = new Char(letra[i], ((char) (209)), 209, AtsPos.anchoLetra,
						AtsPos.altoLetra, 0);
				letras.add(c);
				m32--;
			} else {
				c = new Char(letra[i], ((char) (65 + m32)), 65 + m32,
						AtsPos.anchoLetra, AtsPos.altoLetra, 0);
				letras.add(c);
			}
			m32++;
			// System.out.println(c.getCaracter() + "\t" + c.getCodigo());
		}

		// Blancos
		// System.out.println((int) ' ' + "\tespacio");
		// System.out.println((int) '\t' + "\ttab");
		// System.out.println((int) '\n' + "\tsalto");
		c = new Char(blanco[0], ((char) (32)), 32, AtsPos.anchoLetra,
				AtsPos.altoLetra, 0);
		blancos.add(c);
		c = new Char(blanco[1], ((char) (9)), 9, AtsPos.anchoLetra * 4,
				AtsPos.altoLetra, 0);
		blancos.add(c);
		c = new Char(blanco[2], ((char) (10)), 10, AtsPos.anchoLetra,
				AtsPos.altoLetra, 0);
		blancos.add(c);
	}

	public static Char getSprite(char c) {
		for (int i = 0; i < letras.size(); i++) {
			if (c == letras.get(i).getCaracter())
				return letras.get(i);
		}

		for (int i = 0; i < numeros.size(); i++) {
			if (c == numeros.get(i).getCaracter())
				return numeros.get(i);
		}

		for (int i = 0; i < blancos.size(); i++) {
			if (c == blancos.get(i).getCaracter()) {
				return blancos.get(i);
			}
		}
		return null;
	}

	public static Char getSprite(int codigo) {
		for (int i = 0; i < letras.size(); i++) {
			if (codigo == letras.get(i).getCodigo())
				return letras.get(i);
		}

		for (int i = 0; i < numeros.size(); i++) {
			if (codigo == numeros.get(i).getCodigo())
				return numeros.get(i);
		}

		for (int i = 0; i < blancos.size(); i++) {
			if (codigo == blancos.get(i).getCodigo()) {
				return blancos.get(i);
			}
		}
		return null;
	}

	// /////////////////////////////////////////////////////////////////////////
	private float posX, posY;
	private Char caracter;

	public Letra(char c) {
		caracter = new Char(getSprite(c));
//		System.out.println("u:" + caracter.getCaracter() + ":u");
	}

	public Letra(int codigo) {
		caracter = new Char(getSprite(codigo));
	}

	public void setPosicion(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
		if (caracter.getSprite() != null)
			AtsTM.setPosicion(caracter.getSprite(), posX, posY,
					caracter.getAncho(), caracter.getAlto());
	}

	public void setTamano(float ancho, float alto) {
		caracter.setAncho(ancho);
		caracter.setAlto(alto);
		AtsTM.setPosicionYRotar(caracter.getSprite(), caracter.getAncho(),
				caracter.getAlto(), posX, posY, caracter.getRotacion());
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public Char getCaracter() {
		return caracter;
	}
}
