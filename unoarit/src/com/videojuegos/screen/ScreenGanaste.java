package com.videojuegos.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.Load;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.cartas.Boton;

/**
 * Aquí se pinta la pantalla Ganaste, cuando el jugador pone su última
 * carta que le queda de su mazo de cartas 
 */


public class ScreenGanaste implements Screen {

	private int id;
	private Boton btnGanaste;
	private SpriteBatch batch;

	public ScreenGanaste(int id) {
		this.id = id;
	}

	@Override
	public void render(float delta) {
		if (btnGanaste.meTocaste())
			AtsUtil.game.setScreen(AtsScreens.screenMain);
		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		btnGanaste.dibujar(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = AtsUtil.batch;
		btnGanaste = new Boton(Load.backgroundganaste, AtsPos.centroX,
				AtsPos.centroY, AtsTM.setJugadorAncho(id),
				AtsTM.setJugadorAlto(id), AtsTM.setJugador(id));
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		AtsSound.pause();
	}

	@Override
	public void resume() {
		batch = AtsUtil.batch;
		btnGanaste = new Boton(Load.backgroundganaste, AtsPos.centroX,
				AtsPos.centroY, AtsTM.setJugadorAncho(id),
				AtsTM.setJugadorAlto(id), AtsTM.setJugador(id));
	}

	@Override
	public void dispose() {
		Load.atlas.dispose();
		AtsUtil.batch.dispose();
		AtsSound.dispose();
	}

}
