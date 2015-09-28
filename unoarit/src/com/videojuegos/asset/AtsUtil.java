package com.videojuegos.asset;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class AtsUtil {
	public static boolean machine;
	public static boolean mismoDispositivo;
	// Nuestro GL10
	public static GL10 gl;

	// Nuestro Game para cambiar pantallas
	public static Game game;

	// Nuestra camara para dibujar
	public static OrthographicCamera camera;

	// Nuestro Batcher
	public static SpriteBatch batch;

	// Para crear un atlas de texturas
	public static void crearAtlas() {
		Settings settings = new Settings();
		settings.paddingX = 2;
		settings.paddingY = 2;
		settings.maxWidth = (int)Math.pow(2, 11);
		settings.maxHeight = (int)Math.pow(2, 11);
		// TexturePacker2.process(settings, INPUT_DIR, OUTPUT_TEST_DIR,
		// PACK_FILE_NAME);
		TexturePacker2.process(settings, "imagenes", "assets", "atlas");
	}
	
	/**
	 * Limpia la pantalla
	  */
	
	public static void limpiarP(){
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

	public static void load(Game game) {
		machine = false;
		mismoDispositivo = true;
		AtsUtil.game =  game;
		// Creamos la camara
		camera = new OrthographicCamera(15, 10);
		// Ponemos la camara en el centro de la pantalla
		camera.position.set(15f / 2, 10f / 2, 0);
		// Creamos el batch para dibujar
		batch = new SpriteBatch();
		//Nuestro gl10
		gl = Gdx.gl10;
	}

}
