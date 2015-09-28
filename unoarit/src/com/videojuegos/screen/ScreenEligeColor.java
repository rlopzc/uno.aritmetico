package com.videojuegos.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.DataCarta;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;
import com.videojuegos.cartas.Carta;
import com.videojuegos.jugador.Juego;

public class ScreenEligeColor implements Screen {
	private Carta cc;
	private Boton btnvefu, btnveba, btnamba, btnamfu, btnazfu, btnazba, btnroj,
			btnna, btnmo, btnros;
	private SpriteBatch batch;
	/** 
	 * Aquí se pinta la pantalla de elegir color
	 * cuando se selecciona y se pone al mazo del centro
	 * la carta comodin +2
	 */



	public ScreenEligeColor(Carta cc) {
		this.cc = cc;
	}
	/**
	 * Éste método pinta botones encima de las cartas 
	 * asignandoles el color en que se haya puesto, luego
	 * pinta encimadel mazo del centro la carta de color
	 * seleccionada 
	 */
	@Override
	public void render(float delta) {
		if (btnamba.meTocaste()) {
			cc.setColorComodin(DataCarta.amba);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[0]);
		} else if (btnamfu.meTocaste()) {
			cc.setColorComodin(DataCarta.amfu);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[1]);
		} else if (btnazba.meTocaste()) {
			cc.setColorComodin(DataCarta.azba);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[2]);
		} else if (btnazfu.meTocaste()) {
			cc.setColorComodin(DataCarta.azfu);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[3]);
		} else if (btnmo.meTocaste()) {
			cc.setColorComodin(DataCarta.mo);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[4]);
		} else if (btnna.meTocaste()) {
			cc.setColorComodin(DataCarta.na);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[5]);
		} else if (btnroj.meTocaste()) {
			cc.setColorComodin(DataCarta.roj);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[6]);
		} else if (btnveba.meTocaste()) {
			cc.setColorComodin(DataCarta.veba);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[7]);
		} else if (btnvefu.meTocaste()) {
			cc.setColorComodin(DataCarta.vefu);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[8]);
		} else if (btnros.meTocaste()) {
			cc.setColorComodin(DataCarta.ros);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			Juego.addMazo(Load.cartasBase[9]);
		}

		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundeligecolor, 0, 0, 15, 10);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	/**
	 * Inicializa los botones y sus posiciones *
	 */



	@Override
	public void show() {
		batch = AtsUtil.batch;
		btnamba = new Boton(null, AtsPos.ambaX, AtsPos.ambaY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 1
		btnamfu = new Boton(null, AtsPos.amfuX, AtsPos.amfuY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 2
		btnazba = new Boton(null, AtsPos.azbaX, AtsPos.azbaY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 3
		btnazfu = new Boton(null, AtsPos.azfuX, AtsPos.azfuY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 4
		btnna = new Boton(null, AtsPos.naX, AtsPos.naY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 5
		btnmo = new Boton(null, AtsPos.moX, AtsPos.moY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 6
		btnroj = new Boton(null, AtsPos.rojX, AtsPos.rojY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 7
		btnveba = new Boton(null, AtsPos.vebaX, AtsPos.vebaY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 8
		btnvefu = new Boton(null, AtsPos.vefuX, AtsPos.vefuY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 9
		btnros = new Boton(null, AtsPos.rosX, AtsPos.rosY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 10
		// Gdx.input.setInputProcessor(new InputScreenJuego());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		AtsSound.pause();
	}

	/**
	 * Inicializa los botones y sus posiciones nuevamente
	 * cuando la aplicación ha sido interrumpida 
	 */
	

	@Override
	public void resume() {
		batch = AtsUtil.batch;
		btnamba = new Boton(null, AtsPos.ambaX, AtsPos.ambaY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 1
		btnamfu = new Boton(null, AtsPos.amfuX, AtsPos.amfuY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 2
		btnazba = new Boton(null, AtsPos.azbaX, AtsPos.azbaY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 3
		btnazfu = new Boton(null, AtsPos.azfuX, AtsPos.azfuY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 4
		btnna = new Boton(null, AtsPos.naX, AtsPos.naY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 5
		btnmo = new Boton(null, AtsPos.moX, AtsPos.moY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 6
		btnroj = new Boton(null, AtsPos.rojX, AtsPos.rojY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 7
		btnveba = new Boton(null, AtsPos.vebaX, AtsPos.vebaY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 8
		btnvefu = new Boton(null, AtsPos.vefuX, AtsPos.vefuY,
				AtsPos.anchoCarta, AtsPos.altoCarta, 0);// 9
		btnros = new Boton(null, AtsPos.rosX, AtsPos.rosY, AtsPos.anchoCarta,
				AtsPos.altoCarta, 0);// 10
	}

	@Override
	public void dispose() {
		Load.atlas.dispose();
		AtsUtil.batch.dispose();
		AtsSound.dispose();
	}

}
