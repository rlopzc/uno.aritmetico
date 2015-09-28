package com.videojuegos.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AtsSound {

	public static Sound correcto;
	public static Sound incorrecto;
	public static Sound seleccion;
	public static Music juego;
	public static Music main;
	public static boolean off;

	public static Preferences prefSound;

	public static void load() {
		prefSound = Gdx.app.getPreferences("prefSound");
		off = prefSound.getBoolean("off", false);
		correcto = Gdx.audio.newSound(Gdx.files.internal("music/correcto.mp3"));
		incorrecto = Gdx.audio.newSound(Gdx.files
				.internal("music/incorrecto.mp3"));
		seleccion = Gdx.audio.newSound(Gdx.files
				.internal("music/seleccion.mp3"));
		juego = Gdx.audio.newMusic(Gdx.files.internal("music/juego.mp3"));
		main = Gdx.audio.newMusic(Gdx.files.internal("music/main.mp3"));
	}

	public static void onOff() {
		if (off) {
			off = false;
		} else {
			off = true;
		}
	}

	public static void sonarMusic(Music music) {
		if (off) {
			if (!music.isPlaying())
				music.play();
			if(music.equals(main))
				juego.pause();
			else
				main.pause();
		} else {
			music.pause();
		}
	}

	public static void sonarSound(Sound sound) {
		if (off) {
			juego.pause();
			sound.play(1);
			juego.play();
		}
	}
	
	public static void pause() {
		prefSound.putBoolean("off", off);
		prefSound.flush();
		juego.pause();
		main.pause();
	}

	public static void dispose() {
		correcto.dispose();
		incorrecto.dispose();
		seleccion.dispose();
		juego.dispose();
		main.dispose();
	}

}
