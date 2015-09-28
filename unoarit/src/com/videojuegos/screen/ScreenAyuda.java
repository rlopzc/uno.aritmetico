package com.videojuegos.screen;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.ayuda.Ayuda;
import com.videojuegos.cartas.Boton;


/**
 * Aquí se pinta la pantalla de la Ayuda
 */

public class ScreenAyuda implements Screen {
	
	private SpriteBatch batch;
	private Ayuda ayuda;
	private Boton btnAtras;
	/**
	 * Éste método pinta la pantalla de ayuda llamando al método 
	 * dibujarAyuda de la clase Ayuda y dibuja los botones
	 * anterior, mostrar y siguiente, para animar la ayuda
	 */


	@Override
	public void render(float delta) {

		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundayuda, 0, 0, 15, 10);
		batch.end();
		
		if (btnAtras.meTocaste()) {
			AtsUtil.game.setScreen(AtsScreens.screenMain);
			return;
		}

		batch.enableBlending();
		batch.begin();
		try {
			ayuda.dibujarAyuda(batch);
		} catch (Exception e) {
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	/**
	 * Inicializa los botones y sus posiciones
	 */
	


	@Override
	public void show() {
		ayuda = new Ayuda();
		batch = AtsUtil.batch;
		btnAtras = new Boton(null, 13.5f, 0.8f, 1.5f, 1.2f, 0);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		AtsSound.pause();
	}

	/** Inicializa los botones y sus posiciones de nuevo
	 * cuando la aplicación ha sido interrumpida
	 */
	

	@Override
	public void resume() {
		ayuda = new Ayuda();
		batch = AtsUtil.batch;
		btnAtras = new Boton(null, 13.5f, 0.8f, 1.5f, 1.2f, 0);
	}

	@Override
	public void dispose() {
		Load.atlas.dispose();
		AtsUtil.batch.dispose();
		AtsSound.dispose();
	}

}
