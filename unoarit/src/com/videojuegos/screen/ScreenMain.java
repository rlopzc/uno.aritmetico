package com.videojuegos.screen;

import android.provider.Settings.Secure;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.basedatos.DBTurno;
import com.videojuegos.basedatos.DBTurnoHandler;
import com.videojuegos.basedatos.VolleySingleton;
import com.videojuegos.cartas.Boton;
import com.videojuegos.unoarit.MainP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * �sta clase pinta el men� principal y los bot�nes
 * de las opciones 
 */

public class ScreenMain implements Screen {

	private Boton btnJugarSolo, btnMultiPlayer, btnSoundoff, btnsalir, btnAyuda, btnExportarDb;
	private SpriteBatch batch;
	private ArrayList<String> emailsJugadores;
	private AtsInputListener listener;
	private static DBTurnoHandler db = new DBTurnoHandler(MainP.getContext());
	private int id_partida, turno;
	private String jugador, color, operacionMazo, operacionJugada, valor;
	private String android_id = Secure.getString(MainP.getContext().getContentResolver(),
			Secure.ANDROID_ID);

	/** 
	 * En �ste m�todo se crean los botones y se les da la
	 * funcionalidad de dirigir a las dem�s pantallas seg�n
	 * su definici�n
	 */

	@Override
	public void render(float delta) {
		if (btnJugarSolo.meTocaste()) {
			AtsUtil.machine = true;
			AtsUtil.mismoDispositivo = false;
			listener = new AtsInputListener();
			AtsUtil.setBackground(Load.backgroundplay1);
			AtsScreens.screenJuego = new ScreenJuego(2);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
			return;
		} else if (btnMultiPlayer.meTocaste()) {
			AtsUtil.machine = false;
			AtsUtil.mismoDispositivo = true;
			AtsUtil.game.setScreen(AtsScreens.screenMultiPlayer);
			return;
		} else if (btnsalir.meTocaste()) {
			
			Gdx.app.exit();
			
			
			return;
		}else if(btnAyuda.meTocaste()){
			AtsUtil.game.setScreen(AtsScreens.screenAyuda);
			return;
		}
		if (btnExportarDb.meTocaste()) {
			List<DBTurno> turnos = db.obtenerTodosTurnos();
			//creamos gson
			exportarBaseDatos();
			db.borrarTodosTurnos();
		}
		if (btnSoundoff.meTocaste()) {
			AtsSound.onOff();
		}
		AtsSound.sonarMusic(AtsSound.main);
		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundmain, 0, 0, 15, 10);
		batch.end();

		batch.enableBlending();
		batch.begin();
		btnExportarDb.dibujar(batch);
		if (!AtsSound.off) {
			btnSoundoff.dibujar(batch);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = AtsUtil.batch;
		btnJugarSolo = new Boton(null, AtsPos.btnjugSX, AtsPos.btnJugSY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnMultiPlayer = new Boton(null, AtsPos.btnMulPX, AtsPos.btnMulPY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnsalir = new Boton(null, AtsPos.btnSalirX, AtsPos.btnSalirY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnSoundoff = new Boton(Load.btnsoundoff, AtsPos.btnSoundX,
				AtsPos.btnSoundY, AtsPos.anchoBtnSound, AtsPos.altoBtnSound, 0);
		btnAyuda = new Boton(null, AtsPos.btnAyudaX, AtsPos.btnAyudaY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnExportarDb = new Boton(Load.btnexportarDb, AtsPos.btnExportarDbX,
				AtsPos.btnExportarDbY, AtsPos.anchoBtnExportarDb, AtsPos.altoBtnExportarDb, 0);

		
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
		btnJugarSolo = new Boton(null, AtsPos.btnjugSX, AtsPos.btnJugSY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnMultiPlayer = new Boton(null, AtsPos.btnMulPX, AtsPos.btnMulPY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnsalir = new Boton(null, AtsPos.btnSalirX, AtsPos.btnSalirY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnSoundoff = new Boton(Load.btnsoundoff, AtsPos.btnSoundX,
				AtsPos.btnSoundY, AtsPos.anchoBtnSound, AtsPos.altoBtnSound, 0);
		btnAyuda = new Boton(null, AtsPos.btnAyudaX, AtsPos.btnAyudaY,
				AtsPos.anchoBtnMain, AtsPos.altoBtnMain, 0);
		btnExportarDb = new Boton(Load.btnexportarDb, AtsPos.btnExportarDbX,
				AtsPos.btnExportarDbY, AtsPos.anchoBtnExportarDb, AtsPos.altoBtnExportarDb, 0);
	}

	@Override
	public void dispose() {
		AtsUtil.batch.dispose();
		Load.atlas.dispose();
		AtsSound.dispose();
	}

	/**
	 * Clase privada se gestiona la gestion del dialogo para ingresar el correo del jugador.
	 */
	private class AtsInputListener implements Input.TextInputListener {

		private AtsInputListener() {
			emailsJugadores = new ArrayList<String>();
		}
		/**
		 * Metodo para recepcionar el correo del jugador
		 */
		@Override
		public void input(String correo) {
//			if(!esCorreo(correo)) {
//				AtsSound.sonarSound(AtsSound.incorrecto);
//			} else {
				AtsSound.sonarSound(AtsSound.correcto);
//				agregaJugador(correo);
//				AtsScreens.screenJuego = new ScreenJuego(2, emailsJugadores);
				AtsScreens.screenJuego = new ScreenJuego(2);
				AtsUtil.game.setScreen(AtsScreens.screenJuego);
//			}
		}

		/**
		 * Metodo para cancelar el ingreso del correo del jugador.
		 */
		@Override
		public void canceled() {
			emailsJugadores.clear();
			AtsUtil.game.setScreen(AtsScreens.screenMain);
		}

		/**
		 * Metodo para agregar jugadores a la lista
		 */

		public void agregaJugador(String correo) {
			emailsJugadores.add(correo);
			//System.out.println(emailsJugadores);
		}

		/**
		 * Metodos para validar si el correo ingresado es un correo valido
		 */

		private final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
				"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
						"\\@" +
						"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
						"(" +
						"\\." +
						"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
						")+"
		);

		private boolean esCorreo(String correo) {
			return EMAIL_ADDRESS_PATTERN.matcher(correo).matches();
		}
	}

    public void exportarBaseDatos(final int id_partida, final int turno, final String jugador,
                                  final String color, final String operacionMazo, final String operacionJugada, final String valor) {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
		String url = "http://192.168.228.186:3000/api/v1/unoartimetico/importdb";
		StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
                System.out.println("Response: " + response);
            }
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
                //System.out.println("Algo salio mal.");
				System.out.println(error.getLocalizedMessage());
				error.printStackTrace();
			}
		}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("id_partida", String.valueOf(id_partida));
				params.put("turno", String.valueOf(turno));
				params.put("jugador", jugador);
				params.put("color", color);
				params.put("operacionMazo", operacionMazo);
				params.put("operacionJugada", operacionJugada);
				params.put("valor", valor);
				params.put("id_dispositivo", android_id);
				System.out.println(params);
				return params;
			}
		};
		requestQueue.add(request);
	}

	//Recibe objeto gson

	public void exportarBaseDatos() {
		RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
		String url = "http://192.168.228.186:3000/api/v1/unoartimetico/importdb";
		StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println("Response: " + response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				//System.out.println("Algo salio mal.");
				System.out.println(error.getLocalizedMessage());
				error.printStackTrace();
			}
		}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("id_partida", String.valueOf(id_partida));
				params.put("turno", String.valueOf(turno));
				params.put("jugador", jugador);
				params.put("color", color);
				params.put("operacionMazo", operacionMazo);
				params.put("operacionJugada", operacionJugada);
				params.put("valor", valor);
				params.put("id_dispositivo", android_id);
				System.out.println(params);
				return params;
			}
		};
		requestQueue.add(request);
	}

}
