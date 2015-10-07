package com.videojuegos.cartas;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsTM;


public class Carta  {
	private int color, colorComodin,id;
	private int valor;
	private String operacion;
	private int jugador;
	private Sprite sprite;
	private float x, y;

	public Carta(TextureAtlas atlas, String nombreAtlas, int color, int valor, String operacion) {
		this.color = color;
		this.colorComodin = this.color;
		this.valor = valor;
		this.operacion = operacion;
		this.jugador = 0;
		this.id = 0;
		this.sprite = atlas.createSprite(nombreAtlas);
	}

	public void setColorComodin(int color) {
		this.colorComodin = color;
	}

	public int getColorComodin() {
		return colorComodin;
	}

	public int getColor() {
		return color;
	}

	public int getValor() {
		return valor;
	}

	public String getOperacion() {
		return operacion;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public BoundingBox getRectangle() {
		return AtsTM.rectangulo(AtsTM.puntas(x, y, AtsPos.anchoCarta,
				AtsPos.altoCarta));
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void dibujar(SpriteBatch spriteBatch) {
		if (sprite != null) {
			this.sprite.draw(spriteBatch);
		}
	}

	public boolean meEstasTocando(int turno) {
		return jugador == turno && AtsTM.meEstasTocando(puntas());
	}

	public float[] puntas() {
		return AtsTM.puntas(x, y, AtsPos.anchoCarta, AtsPos.altoCarta);
	}

	public boolean meTocaste() {
		return AtsTM.meTocaste(puntas());
	}

	public void rotarSprite(float grados) {
		this.sprite.setRotation(grados);
	}

	public void setPosicion(float x, float y) {
		if (sprite != null) {
			this.x = x;
			this.y = y;
			AtsTM.setPosicion(sprite, x, y, AtsPos.anchoCarta, AtsPos.altoCarta);
		}
	}

	public void setJugador(int jugadorid) {
		this.jugador = jugadorid;
		AtsTM.setJugadorCarta(sprite, jugadorid);
	}
	
	public float getX(){
		return x;
	}

}
