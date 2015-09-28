package com.videojuegos.cartas;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsTM;

public class Boton {

	private float x, y, ancho, alto;
	private Sprite sprite;

	public Boton(Sprite sprite, float x, float y, float ancho, float alto,
			float grados) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		if (sprite != null) {
			this.sprite = new Sprite(sprite);
			setPosicion(x, y, grados);
		}
	}

	public void setPosicion(float x, float y, float grados) {
		this.x = x;
		this.y = y;
		AtsTM.rotar(this.sprite, ancho, alto, grados);
		AtsTM.setPosicion(this.sprite, x, y, ancho, alto);
	}
	
	public Sprite getSprite(){
		return sprite;
	}

	public boolean meTocaste() {
		return AtsTM.meTocaste(AtsTM.puntas(x, y, ancho, alto));
	}

	public void dibujar(SpriteBatch spriteBatch) {
		if (sprite != null) {
			sprite.draw(spriteBatch);
		}
	}

}
