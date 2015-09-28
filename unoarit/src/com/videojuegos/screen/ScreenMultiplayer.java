package com.videojuegos.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;

/**
 * En ésta clase se pinta la pantalla con las opciones
 * para el modo multijugador 
 */


public class ScreenMultiplayer implements Screen {

	private Boton btnJugarMismoDisp, btnCrearPartida, btnUnirsePartida,
			btnAtras;
	private SpriteBatch batch;
	/**
     * En éste método se asignan botones y su función
	 * según las deficiones de las etiquetas
	 */
	

	@Override
	public void render(float delta) {

		if (btnJugarMismoDisp.meTocaste()) {
			AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
			AtsUtil.machine = false;
			AtsUtil.mismoDispositivo = true;
		} else if (btnUnirsePartida.meTocaste()) {
			AtsUtil.game.setScreen(AtsScreens.screenUnirsePartida);
			AtsUtil.machine = false;
			AtsUtil.mismoDispositivo = false;
		} else if (btnCrearPartida.meTocaste()) {
			AtsUtil.game.setScreen(AtsScreens.screenCrearPartida);
			AtsUtil.machine = false;
			AtsUtil.mismoDispositivo = false;
		} else if (btnAtras.meTocaste()) {
			AtsUtil.game.setScreen(AtsScreens.screenMain);
		}
		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundmultijugador, 0, 0, 15, 10);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = AtsUtil.batch;
		btnJugarMismoDisp = new Boton(null, 7.7f, 7.3f, 10.0f, 1.9f, 0);
		btnUnirsePartida = new Boton(null, 7.7f, 5.0f, 10.0f, 1.9f, 0);
		btnCrearPartida = new Boton(null, 7.7f, 2.4f, 10.0f, 1.9f, 0);
		btnAtras = new Boton(null, 7.5f, 0.8f, 1.5f, 1.2f, 0);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		batch = AtsUtil.batch;
		btnJugarMismoDisp = new Boton(null, 7.7f, 7.3f, 10.0f, 1.9f, 0);
		btnUnirsePartida = new Boton(null, 7.7f, 5.0f, 10.0f, 1.9f, 0);
		btnCrearPartida = new Boton(null, 7.7f, 2.4f, 10.0f, 1.9f, 0);
		btnAtras = new Boton(null, 7.5f, 0.8f, 1.5f, 1.2f, 0);
	}

	@Override
	public void dispose() {
		 Load.atlas.dispose();
		 AtsUtil.batch.dispose();
		 AtsSound.dispose();
	}

}
