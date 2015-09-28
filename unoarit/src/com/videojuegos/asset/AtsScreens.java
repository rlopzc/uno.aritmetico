package com.videojuegos.asset;

import com.badlogic.gdx.Screen;
import com.videojuegos.screen.ScreenAyuda;
import com.videojuegos.screen.ScreenCrearPartida;
import com.videojuegos.screen.ScreenMain;
import com.videojuegos.screen.ScreenMultiplayer;
import com.videojuegos.screen.ScreenNumJugadores;
import com.videojuegos.screen.ScreenUnirsePartida;

public class AtsScreens {
	public static Screen screenMain;
	public static Screen screenJuego;
	public static Screen screenNumPlayer;
	public static Screen screenAyuda;
	public static Screen screenMultiPlayer;
	public static Screen screenCrearPartida;
	public static Screen screenUnirsePartida;
	public static Screen screenUnirsePartidaUp;

	public static void load() {
		screenMain = new ScreenMain();
		screenNumPlayer = new ScreenNumJugadores();
		screenAyuda = new ScreenAyuda();
		screenMultiPlayer = new ScreenMultiplayer();
		screenCrearPartida = new ScreenCrearPartida();
		screenUnirsePartida = new ScreenUnirsePartida();
	}
}
