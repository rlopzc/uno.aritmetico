package com.videojuegos.ayuda;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;

public class Ayuda {
	private Boton btnSig, btnAnt, btnMos;
	private Boton[] titulo;
	private int index, indexT;
	private float grados, incX;
	private boolean animar;

	public Ayuda() {
		index = 1;
		indexT = 0;
		grados = 0;
		incX = 0;
		animar = false;
		btnSig = new Boton(Load.btncambiar, AtsPos.btnSAyudaX,
				AtsPos.btnSAMAyudaY, AtsPos.anchoBtnCambio,
				AtsPos.altoBtnCambio, 180);
		btnAnt = new Boton(Load.btncambiar, AtsPos.btnAAyudaX,
				AtsPos.btnSAMAyudaY, AtsPos.anchoBtnCambio,
				AtsPos.altoBtnCambio, 0);
		btnMos = new Boton(Load.btnmostrar, AtsPos.btnMAyudaX,
				AtsPos.btnSAMAyudaY, AtsPos.anchoBtnMos, AtsPos.altoBtnMos, 0);
		titulo = new Boton[4];
		titulo[0] = new Boton(Load.correctoporcolor, 7.3f, 8.0f, 7.7f, 1.0f, 0);
		titulo[1] = new Boton(Load.correctoporresultado, 7.3f, 8.0f, 9.5f, 1.0f, 0);
		titulo[2] = new Boton(Load.incorrecto, 7.4f, 8.0f, 5.0f, 0.8f, 0);
		titulo[3] = new Boton(Load.correctocomodin, 7.2f, 8.0f, 3.8f, 0.8f, 0);
		for (int i = 0; i < Load.help.size(); i++) {
			Load.help.get(i).setJugador(0);
		}
		Load.help.get(0).setPosicion(AtsPos.cartaADerX, AtsPos.cartaAyudaY);
		for (int i = 1; i < Load.help.size(); i++) {
			Load.help.get(i).setPosicion(AtsPos.cartaAIzqX, AtsPos.cartaAyudaY);
		}
	}

	private boolean siguiente() {
		if ((index + 1) < Load.help.size()) {
			if (btnSig.meTocaste()) {
				grados = 0;
				incX = 0;
				animar = false;
				index++;
				indexT++;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean anterior() {
		if ((index - 1) > 0) {
			if (btnAnt.meTocaste()) {
				grados = 0;
				incX = 0;
				animar = false;
				index--;
				indexT--;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean mostrar() {
		if (animar) {
			if ((Load.help.get(0).getX() - 0.01) > AtsPos.centroX) {
				grados += 7;
				incX += 0.03;
			} else {
				incX = 5.2f;
				grados = 0;
				animar = false;
			}
		}
		
		if (!animar) {
			if (btnMos.meTocaste()) {
				grados = 0;
				incX = 0;
				animar = true;
			}
		}

		Load.help.get(0).rotarSprite(grados);
		Load.help.get(index).rotarSprite(-grados);
		Load.help.get(0).setPosicion(AtsPos.cartaADerX - incX, AtsPos.cartaAyudaY);
		Load.help.get(index).setPosicion(AtsPos.cartaAIzqX + incX, AtsPos.cartaAyudaY);

		return animar;
	}

	public void dibujarAyuda(SpriteBatch batch) {
		Load.help.get(0).dibujar(batch);
		Load.help.get(index).dibujar(batch);
		titulo[indexT].dibujar(batch);
		if (siguiente())
			btnSig.dibujar(batch);
		if (anterior())
			btnAnt.dibujar(batch);
		if (!mostrar())
			btnMos.dibujar(batch);
	}

}
