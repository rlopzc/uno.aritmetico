package com.videojuegos.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;
import com.videojuegos.jugador.Bluetooth;
import com.videojuegos.jugador.Juego;
import com.videojuegos.jugador.Player;
import com.videojuegos.utils.BluetoothSingleton;

/**
 * �sta clase pinta la pantalla del juego y se le indica cuandos jugadores
 * vana jugar en caso de que sea en el modo multijugador
 */

public class ScreenJuego implements Screen {

	private Juego juego;
	private SpriteBatch batch;
	private Boton btnAtras;
	private ArrayList<Player> player;

	public ScreenJuego(int numJug) {
		try {
			juego = new Juego(numJug);
		} catch (Exception e) {
			AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
		}
	}

	public ScreenJuego(int numJug, ArrayList<String> jugadores) {
		try {
			juego = new Juego(numJug, jugadores);
		} catch (Exception e) {
			AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
		}
	}
	
	public void setPlayers(ArrayList<Player> player){
		this.player = player;
	}

	/**
     * En �ste m�todo se establecen variables que identifican si
     * Si se va a jugar en el mismo dispositivo (multijugador) o 
     * contra el dispositivo (modo jugador solo)	 
     */


	@Override
	public void render(float delta) {

		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundplay1, 0, 0, 15, 10);
		batch.end();

		batch.enableBlending();
		batch.begin();

		if ((!AtsUtil.mismoDispositivo) && (!AtsUtil.machine)) {
			if ( BluetoothSingleton.getInstance().bluetoothManager.CerrarPartida() ) {
				BluetoothSingleton.getInstance().bluetoothManager.stop();
				AtsUtil.game.setScreen(AtsScreens.screenMain);
			}
			if (  BluetoothSingleton.getInstance().bluetoothManager.isConnected()  )
				batch.draw(Load.good, 1.0f, 1.0f, 1.0f, 1.0f);
			else
				AtsUtil.game.setScreen(AtsScreens.screenMain);
		}

		try {
			juego.dibujarJuego(batch);
		} catch (Exception ignored) {
		}

		btnAtras.dibujar(batch);
		batch.end();

		if (btnAtras.meTocaste() && Bluetooth.machine()) {
			Load.mazo.rellenarMazo(player);
			AtsUtil.game.setScreen(AtsScreens.screenMain);
		} else if (btnAtras.meTocaste() && Bluetooth.mismoDispositivo()) {
			Load.mazo.rellenarMazo(player);
			AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
		} else if (btnAtras.meTocaste() && !AtsUtil.mismoDispositivo && !AtsUtil.machine && Juego.idMachine==1) {
			Load.mazo.rellenarMazo(player);
			BluetoothSingleton.getInstance().bluetoothManager
					.sendMessage2("Cerrar");
			BluetoothSingleton.getInstance().bluetoothManager.stop();
			AtsUtil.game.setScreen(AtsScreens.screenMain);
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = AtsUtil.batch;
		if (AtsUtil.mismoDispositivo == false && AtsUtil.machine == false) {
			btnAtras = new Boton(Load.btnatras, 14.2f, 0.5f, 1.0f, 0.7f, 0);
		}
		if (AtsUtil.machine == true) {
			btnAtras = new Boton(Load.btnatras, 14.2f, 0.5f, 1.0f, 0.7f, 0);
		}
		if (AtsUtil.mismoDispositivo == true) {
			if (Juego.turno == 1) {
				btnAtras = new Boton(Load.btnatras, 14.3f, 0.5f, 1.0f, 0.7f, 0);
			}
			if (Juego.turno == 2) {
				btnAtras = new Boton(Load.btnatras, 0.7f, 9.5f, 1.0f, 0.7f, 180);
			}
			if (Juego.turno == 3) {
				btnAtras = new Boton(Load.btnatras, 0.7f, 0.5f, 1.0f, 0.7f, -90);
			}
			if (Juego.turno == 4) {
				btnAtras = new Boton(Load.btnatras, 14.2f, 9.4f, 1.0f, 0.7f, 90);
			}
		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		AtsSound.pause();
	}

	@Override
	public void resume() {
		batch = AtsUtil.batch;
		if (AtsUtil.mismoDispositivo == false && AtsUtil.machine == false) {
			btnAtras = new Boton(Load.btnatras, 14.2f, 0.5f, 1.0f, 0.7f, 0);
		}
		if (AtsUtil.machine == true) {
			btnAtras = new Boton(Load.btnatras, 14.2f, 0.5f, 1.0f, 0.7f, 0);
		}
		if (AtsUtil.mismoDispositivo == true) {
			if (Juego.turno == 1) {
				btnAtras = new Boton(Load.btnatras, 14.3f, 0.5f, 1.0f, 0.7f, 0);
			}
			if (Juego.turno == 2) {
				btnAtras = new Boton(Load.btnatras, 0.7f, 9.5f, 1.0f, 0.7f, 180);
			}
			if (Juego.turno == 3) {
				btnAtras = new Boton(Load.btnatras, 0.7f, 0.5f, 1.0f, 0.7f, -90);
			}
			if (Juego.turno == 4) {
				btnAtras = new Boton(Load.btnatras, 14.2f, 9.4f, 1.0f, 0.7f, 90);
			}
		}
	}

	@Override
	public void dispose() {

		Load.atlas.dispose();
		AtsUtil.batch.dispose();
		AtsSound.dispose();
	}

}
