package com.videojuegos.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.videojuegos.jugador.Juego;

public class AtsGuardar {
	
	public static void Guardar(Juego juego){
		Json json = new Json();
		FileHandle juegoG = Gdx.files.local("Juego.ic");
		juegoG.writeString(json.prettyPrint(juego), false);
	}
	
	public static Juego leer(){
		Json json = new Json();
		FileHandle juegoG = Gdx.files.local("Juego.ic");
		return json.fromJson(Juego.class, juegoG);
	}

}
