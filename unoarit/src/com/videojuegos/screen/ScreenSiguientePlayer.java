package com.videojuegos.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;


/** Clase que pinta la pantalla de siguiente jugador
 * cuando ya se esta jugando 
 */
public class ScreenSiguientePlayer implements Screen {
	private Boton btnSiguiente;
	private SpriteBatch batch;
	private int id;
	
	public ScreenSiguientePlayer(int id) {
		this.id = id;
	}

	@Override
	public void render(float delta) {
		
		if (btnSiguiente.meTocaste()){
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
		}
		
		AtsUtil.limpiarP();
		
		batch.disableBlending();
		batch.begin();

        batch.draw(AtsUtil.getBackground(), 0, 0, 15, 10);
        batch.end();
		
		batch.enableBlending();
		batch.begin();
		btnSiguiente.dibujar(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = AtsUtil.batch;
		if ( id == 1) {
			btnSiguiente = new Boton(Load.backgroundsiguientelandscape, AtsPos.centroX,
					AtsPos.centroY, 14, 9, AtsTM.setJugador(id));
		}
		if (id == 2) {
			btnSiguiente = new Boton(Load.backgroundsiguientelandscape, AtsPos.centroX,
					AtsPos.centroY, 14, 9, AtsTM.setJugador(id));
		}
		if (id == 3) {
			btnSiguiente = new Boton(Load.backgroundsiguienteportrait, AtsPos.centroX,
					AtsPos.centroY, 9, 14, AtsTM.setJugador(id));
		}
		if (id == 4) {
			btnSiguiente = new Boton(Load.backgroundsiguienteportrait, AtsPos.centroX,
					AtsPos.centroY, 9, 14, AtsTM.setJugador(id));
		}
		
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
		btnSiguiente = new Boton(Load.backgroundsiguienteportrait, AtsPos.centroX,
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
