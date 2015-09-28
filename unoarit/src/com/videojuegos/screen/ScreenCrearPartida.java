package com.videojuegos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;
import com.videojuegos.input.InputScreenJuego;
import com.videojuegos.input.ListaDevices;
import com.videojuegos.jugador.Juego;
import com.videojuegos.utils.BluetoothSingleton;
/**
 * Aqui se definen objetos del tipo Boton,ListaDevices e InputScreenJuego para el manejo
 * de los componentes de la pantalla CrearPartida
  */
public class ScreenCrearPartida implements Screen{
	
	private SpriteBatch batch;
	public Boton namePlayer, namePartida, btnOK, btnAtras,btnJuego;
	public InputScreenJuego campoT1, campoT2, listaPlayer;
	public static int contador;
	public ListaDevices  lista;
	public boolean Entro=false;
	
	/**
	 * Metodo render que se encarga de dibujar la interfaz de la ventana y de responder a los eventos que se dan sobre ella
	 */
	@Override
	public void render(float delta) {
		
		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundcrearpartida, 0, 0, 15, 10);
		batch.end();
		
		
		
		if(namePartida.meTocaste()) {
			Gdx.input.setOnscreenKeyboardVisible(true);
			System.out.println("namePartida");
			Gdx.input.setInputProcessor(campoT1);	
		}
		if(namePlayer.meTocaste()) {
			listaPlayer = new InputScreenJuego(batch, 2.05f, 5.1f);
			Gdx.input.setOnscreenKeyboardVisible(true);
			System.out.println("namePlayer");
			Gdx.input.setInputProcessor(campoT2);
		}	
		/**
		 *Sentencia que se encarga de escuchar el evento del Boton OK para poder crear una Partida 
		 */
		if(btnOK.meTocaste()) {
			
			Entro=true;
			Gdx.input.setOnscreenKeyboardVisible(false);
			
			System.out.println(campoT1.getPalabra());
			System.out.println(campoT2.getPalabra());
			listaPlayer.setJugador(campoT2.texto);	
			
				BluetoothSingleton.getInstance().bluetoothManager.startServer();
				BluetoothSingleton.getInstance().bluetoothManager.enableDiscoveribility();
				BluetoothSingleton.getInstance().bluetoothManager.AsignarPartida("partida : "+campoT1.getPalabra(),campoT2.getPalabra());
				BluetoothSingleton.getInstance().bluetoothManager.discoverDevices();
			
			
		}
		/**
		 * Metodo que se encarga de escuchar el evento de Jugar, en este caso solo se puede iniciar la partida cuando al menos
		 * un jugador se encuentra conectado a ella, en caso contrario simplemente no la iniciara, hasta que alla conectados
		 */
		if(btnJuego.meTocaste()){
			
			
			
			if(BluetoothSingleton.getInstance().bluetoothManager.Conectados()>=1){
			
			
			String numero= Integer.toString(BluetoothSingleton.getInstance().bluetoothManager.Jugadores().size());
			BluetoothSingleton.getInstance().bluetoothManager.sendMessage2(numero);
			
			BluetoothSingleton.getInstance().bluetoothManager.sendMessage2("Iniciar");
			Juego.idMachine=1;
			AtsScreens.screenJuego = new ScreenJuego(BluetoothSingleton.getInstance().bluetoothManager.Jugadores().size());
			BluetoothSingleton.getInstance().bluetoothManager.ChangetoClose();
			AtsUtil.game.setScreen(AtsScreens.screenJuego);}
			else{
				
				
				
			}
			
		}
		/**
		 * Metodo que se encarga de escuchar el evento de Boton atras, para regresar al menu Principal,
		 * si el servidor ha sido iniciado, lo cancela y regresa al menu anterior
		 */
		if (btnAtras.meTocaste()) {
			CerrarAll();
			AtsUtil.game.setScreen(AtsScreens.screenMultiPlayer);
			return;
		}
		/**
		 * Se encarga de actualizar los campos de Nombre de Partida, de Nombre de jugador
		 */
		campoT1.Actualizar();
		campoT2.Actualizar();
		listaPlayer.Actualizar();
		lista=new ListaDevices(batch,AtsPos.SuperiorIzquierdo,AtsPos.Alto,.4f,.5f,AtsPos.NumMax);
		/**
		 * Metodo  que se encarga de actualizar la lista de los jugadores unidos a la partida
		 */
		if(BluetoothSingleton.getInstance().bluetoothManager.Jugadores()==null){
			
		}
		else
		lista.Asignar(BluetoothSingleton.getInstance().bluetoothManager.Jugadores());
		
		
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Primer metodo que se ejecuta al visualizar el ScreenCrearPartida, aqui unicamente se inicializan los objetos de tipo 
	 * boton, ademas de los campos para el ingreso de texto
	 */
	@Override
	public void show() {
		BluetoothSingleton.getInstance().bluetoothManager.CleanLista();
		batch = AtsUtil.batch;
		namePartida= new Boton(null, 9.8f, 8.3f, 6.0f, 0.8f, 0);
		namePlayer = new Boton(null, 10.0f, 7.4f, 6.0f, 0.8f, 0);
		btnOK = new Boton(null, 14.0f, 7.274f, 1.0f, 1.0f, 0);
		btnAtras = new Boton(null, 13.7f, 0.7f, 1.5f, 1.2f, 0);
		campoT1 = new InputScreenJuego(batch, 7.0f,8.474f);
		campoT2 = new InputScreenJuego(batch, 7.5f, 7.224f);
		listaPlayer = new InputScreenJuego(batch, 2.05f, 5.1f);
		btnJuego=new Boton(null,10.799999f,4.7499995f,2.45f,0.6874995f,0);
		
		
		
		
		
		/**
		 * Antes de iniciarse la ventana, primero se comprueba que el Bluetooth se encuentra activado,el cual es necesario
		 * para poder Crear una PARTIDA, y permitir las conexiones a la partida
		 */
		BluetoothSingleton.getInstance().bluetoothManager.enableBluetooth();/**Metodo encargado de validar la inicializacion del Bluetooth**/
		if(BluetoothSingleton.getInstance().bluetoothManager.EstadoAdapter()==false){
			/**Si el Bluetooth no se encuentra activado se regresa a la ventana anterior, no se permite visualizar la ventana de CrearPartida
			 * 
			 */
			AtsUtil.game.setScreen(AtsScreens.screenMultiPlayer);
		}
		
			
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
		namePartida= new Boton(null, 9.8f, 8.3f, 6.0f, 0.8f, 0);
		namePlayer = new Boton(null, 10.0f, 7.4f, 6.0f, 0.8f, 0);
		btnOK = new Boton(null, 14.0f, 7.274f, 1.0f, 1.0f, 0);
		btnAtras = new Boton(null, 13.7f, 0.7f, 1.5f, 1.2f, 0);
		campoT1 = new InputScreenJuego(batch, 7.0f,8.474f);
		campoT2 = new InputScreenJuego(batch, 7.5f, 7.224f);
		listaPlayer = new InputScreenJuego(batch, 2.05f, 5.1f);		
	}

	@Override
	public void dispose() {
		
		Load.atlas.dispose();
		AtsUtil.batch.dispose();
		AtsSound.dispose();
	}
	public void CerrarAll(){
		/**Metodo que se encarga de Eliminar o Cerrar la partida que se encuentra en ejecucion de igual forma se encarga
		 * de que los jugadores unidos a esta partida, tambien se cierren
		 */
		if(Entro==true)
		BluetoothSingleton.getInstance().bluetoothManager.stop();
	}

}
