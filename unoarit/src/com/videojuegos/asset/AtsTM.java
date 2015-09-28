package com.videojuegos.asset;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.videojuegos.cartas.Carta;
import com.videojuegos.jugador.Juego;

public class AtsTM {
	private static BoundingBox rectangulo;
	private static Vector3 dondeTocaste;

	/**
	 * @param float[] puntas
	 * 
	 *        <pre>
	 * Recibe un arreglo de puntas, que corresponde a las coordenadas (x,y)
	 * de las puntas inferior izquierda y superior derecha, de un rectangulo
	 * en una posicion especifica de la pantalla.
	 * </pre>
	 * 
	 * @return boolean meTocaste
	 * 
	 *         <pre>
	 * Retorna verdadero SI SE TOCÓ la pantalla en algun punto de la pantalla
	 * que este contenido en el interior del rectangulo, delimitado por las
	 * puntas, que se reciben como parametro y falso en caso contrario.
	 * </pre>
	 */

	public static boolean meTocaste(float[] puntas) {
		rectangulo = rectangulo(puntas);
		dondeTocaste = new Vector3();

		if (Gdx.input.justTouched()) {
			AtsUtil.camera.unproject(dondeTocaste.set(Gdx.input.getX(),
					Gdx.input.getY(), 0));
			if (rectangulo.contains(dondeTocaste))
				return true;
		}
		return false;
	}

	/**
	 * @param float[] puntas
	 * 
	 *        <pre>
	 * Recibe un arreglo de puntas, que corresponde a las coordenadas (x,y)
	 * de las puntas inferior izquierda y superior derecha, de un rectangulo
	 * en una posicion especifica de la pantalla.
	 * </pre>
	 * 
	 * @return boolean meTocaste
	 * 
	 *         <pre>
	 * Retorna verdadero SI SE ESTA TOCANDO la pantalla en algun punto de la pantalla
	 * que este contenido en el interior del rectangulo, delimitado por las
	 * puntas, que se reciben como parametro y falso en caso contrario.
	 * </pre>
	  */

	public static boolean meEstasTocando(float[] puntas) {
		rectangulo = rectangulo(puntas);
		dondeTocaste = new Vector3();

		if (Gdx.input.isTouched()) {
			AtsUtil.camera.unproject(dondeTocaste.set(Gdx.input.getX(),
					Gdx.input.getY(), 0));
			if (rectangulo.contains(dondeTocaste))
				return true;
		}
		return false;
	}

	/**
	 * @param float[] puntas
	 * 
	 *        <pre>
	 * Recibe un arreglo de puntas, que corresponde a las coordenadas (x,y)
	 * de las puntas inferior izquierda y superior derecha, de un rectangulo
	 * en una posicion especifica de la pantalla.
	 * </pre>
	 * 
	 * @return BoundingBox rectangulo
	 * 
	 *         <pre>
	 * Retorna un rectangulo (BoundingBox) con un area delimitado por las puntas
	 * del arreglo pasado como parametro.
	 * </pre>
	  */

	public static BoundingBox rectangulo(float[] puntas) {
		return new BoundingBox(new Vector3(puntas[0], puntas[1], 0),
				new Vector3(puntas[2], puntas[3], 0));
	}

	public static float[] puntas(float x, float y, float ancho, float alto) {
		float[] punta = new float[4];
		punta[0] = x - (ancho / 2);
		punta[1] = y - (alto / 2);
		punta[2] = x + (ancho / 2);
		punta[3] = y + (alto / 2);
		return punta;
	}

	public static Vector3 tocasteAqui() {
		dondeTocaste = new Vector3();
		AtsUtil.camera.unproject(dondeTocaste.set(Gdx.input.getX(),
				Gdx.input.getY(), 0));
		return dondeTocaste;
	}

	public static void rotar(Sprite sprite, float width, float height,
			float grados) {
		sprite.setSize(width, height);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setRotation(grados);
	}

	public static void setPosicion(Sprite sprite, float x, float y,
			float width, float height) {
		float[] puntas = puntas(x, y, width, height);
		sprite.setPosition(puntas[0], puntas[1]);
	}

	public static void setPosicionYRotar(Sprite sprite, float width,
			float height, float x, float y, float grados) {
		rotar(sprite, width, height, grados);
		setPosicion(sprite, x, y, width, height);
	}

	public static void escalar(Sprite sprite) {
		if (sprite != null)
			sprite.setSize(AtsPos.anchoCarta * 1.5f, AtsPos.altoCarta * 1.5f);
	}

	public static void reducir(Sprite sprite) {
		if (sprite != null)
			sprite.setSize(AtsPos.anchoCarta, AtsPos.altoCarta);
	}

	public static float setJugador(int jugador) {
		if (Juego.numJug == 2) {
			if ((jugador == 0) || (jugador == 2))
				return 180;
			else
				return 0;
		} else if (Juego.numJug == 3) {
			if (jugador == 1)
				return 0;
			else if (jugador == 2)
				return 180;
			else
				return -90;
		} else {
			if (jugador == 1)
				return 0;
			else if (jugador == 2)
				return 180;
			else if (jugador == 3)
				return -90;
			else
				return 90;
		}
	}

	public static float setJugadorAncho(int jugador) {
		if ((jugador == 0) || (jugador == 1) || (jugador == 2)) {
			return 15;
		} else {
			return 10;
		}
	}

	public static float setJugadorAlto(int jugador) {
		if ((jugador == 0) || (jugador == 1) || (jugador == 2)) {
			return 10;
		} else {
			return 15;
		}
	}

	public static void setJugadorCarta(Sprite sprite, int jugador) {
		if (jugador == 0)
			AtsTM.rotar(sprite, AtsPos.anchoCarta, AtsPos.altoCarta, 0);
		else if (jugador == 1)
			AtsTM.rotar(sprite, AtsPos.anchoCarta, AtsPos.altoCarta, 0);
		else if (jugador == 2)
			AtsTM.rotar(sprite, AtsPos.anchoCarta, AtsPos.altoCarta, 180);
		else if (jugador == 3)
			AtsTM.rotar(sprite, AtsPos.anchoCarta, AtsPos.altoCarta, -90);
		else if (jugador == 4)
			AtsTM.rotar(sprite, AtsPos.anchoCarta, AtsPos.altoCarta, 90);
	}

	/**
	 * Devuelve una carta aleatoria del conjunto total de cartas y la remueve
	 * del conjunto equivalente a tomar una carta del mazo
	  */
	public static Carta getCartaAleatoria(int centro, boolean bluetooth) {
		Random r = new Random();
		try {
			if (Load.mazo.getCartas().size() <= 7) {
				Load.mazo.setCartas();
			}
			int index = r.nextInt(Load.mazo.getCartas().size() - 1);
			if (centro == 0) {
				while (true) {
					Carta c = Load.mazo.getCarta(index);
					if ((c.getColor() == DataCarta.neg)
							|| (c.getValor() == DataCarta.cBloq)
							|| (c.getValor() == DataCarta.cMas1)
							|| (c.getValor() == DataCarta.cReg)) {
						index = r.nextInt(Load.mazo.getCartas().size() - 1);
					} else {
						return Load.mazo.getCartaRemove(index);
					}
				}
			} else {
				return Load.mazo.getCartaRemove(index);
			}
		} catch (Exception e) {

		}
		return null;
	}
}
