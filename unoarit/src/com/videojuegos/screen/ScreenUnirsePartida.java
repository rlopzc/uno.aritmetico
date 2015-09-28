package com.videojuegos.screen;

import java.util.ArrayList;

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


public class ScreenUnirsePartida implements Screen{
	
	/**
	 * Objetos declarados para poder utilizar los campos y botones que se encuentran en la ventana UnirsePartida
	 */
	public SpriteBatch batch;
	public Boton namePlayer, btnOK,btnActualizar, btnAtras,btnUnirse;
	public InputScreenJuego campoT;
	public static int contador;
	public ListaDevices listaencontrados;
	public Boton device1,device2,device3,device4,device5,device6,device7;
	public boolean Disponible;
	public InputScreenJuego mensaje;
	public ArrayList<Character> Hecho;
	/**
	 * Metodo que se encarga de visualizar la ventana y de responder a los eventos que se ejecutan en ella
	 */
	@Override
	public void render(float delta) {
		
		contador++;
		AtsUtil.limpiarP();
		batch.disableBlending();
		batch.begin();
		batch.draw(Load.backgroundunirsepartida, 0, 0, 15, 10);
		batch.end();	
		
	

		
		if(namePlayer.meTocaste()) {
			Gdx.input.setOnscreenKeyboardVisible(true);
			Gdx.input.setInputProcessor(campoT);
			
		}
		
		if(btnOK.meTocaste()) {
			System.out.println("Ok");
			
			BluetoothSingleton.getInstance().bluetoothManager.AsignarPartida(campoT.getPalabra(),"1");
			BluetoothSingleton.getInstance().bluetoothManager.enableDiscoveribility();
			Disponible=true;
			
			
		}
		if(Disponible==true){
			batch.enableBlending();
			batch.begin();
			batch.draw(Load.good,13.394648f, 5.2222f, 1.0f, 1.0f);
			batch.end();
		}
		if(btnAtras.meTocaste()){
			CerrarAll();
			AtsUtil.game.setScreen(AtsScreens.screenMultiPlayer);
			return;
		}
		if(btnActualizar.meTocaste()){
			System.out.println("Actualizando..");
			BluetoothSingleton.getInstance().bluetoothManager.discoverDevices();
			
		}
		mensaje.setJugador(Hecho);
		
		if(device1.meTocaste()){
			System.out.println("1");
		}
		if(device2.meTocaste()){
			System.out.println("2");
		}
		if(device3.meTocaste()){
			System.out.println("3");
		}
		if(device4.meTocaste()){
			System.out.println("4");
		}
		if(device5.meTocaste()){
			System.out.println("5");
		}
		if(device6.meTocaste()){
			System.out.println("6");
		}
		if(device7.meTocaste()){
			System.out.println("7");
		}
		
		if(btnUnirse.meTocaste()){
			
		BluetoothSingleton.getInstance().bluetoothManager.connectToServer("58:C3:8B:65:0C:C5");
			
		}
		
		if(BluetoothSingleton.getInstance().bluetoothManager.Conectado()==true){
			
			mensaje.Actualizar();
			
		}
		/**Sentencia que se encarga de inicializar la ventana de Juego como respuesta del Servidor de Crear Partida 
		 **/
		if(BluetoothSingleton.getInstance().bluetoothManager.IniciarPartida()==true){
			
			Juego.idMachine=BluetoothSingleton.getInstance().bluetoothManager.GetJugadorID();
			int a=BluetoothSingleton.getInstance().bluetoothManager.RecuperarNumero();
			AtsScreens.screenJuego = new ScreenJuego(a);
			AtsUtil.game.setScreen(AtsScreens.screenJuego);
		}
				
		campoT.Actualizar();
		/**Lista de partidas creadas, a las que posiblemente el Jugador desea unirse
		  **/
		listaencontrados=new ListaDevices(batch,AtsPos.SuperiorIzquiero2,AtsPos.Alto2,AtsPos.anchoLetra,AtsPos.altoLetra,AtsPos.NumMax2);
		if(BluetoothSingleton.getInstance().bluetoothManager.devicescan()!=null){
			listaencontrados.Asignar(BluetoothSingleton.getInstance().bluetoothManager.devicescan());	
		}
		
		
		
			
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		/**
		 * Aqui se inicializan los botones, eventos de los componentes que se encuentran en la ventana,
		 * de igual forma se encuentran las sentencias para validar que el Bluetooth se encuentra activado, esto
		 * con el fin de poder realizar algunas operaciones de la ventana Unirse Partida
		 */
		Hecho=new ArrayList<Character>();
		
		Hecho.add('u');
		Hecho.add('n');
		Hecho.add('i');
		Hecho.add('d');
		Hecho.add('o');
		
		Disponible=false;
		batch = AtsUtil.batch;
		namePlayer = new Boton(null, 10.0f, 7.3f, 6.0f, 0.8f, 0);
		btnOK = new Boton(null, 14.0f, 7.474f, 1.0f, 1.0f, 0);
		btnAtras = new Boton(null, 13.8f, 1.0f, 1.5f, 1.2f, 0);
		campoT = new InputScreenJuego(batch,7.6f,7.574f);
		btnUnirse=new Boton(null,10.856249f,3.770833f,2.41875f,0.6666662f,0);
		btnActualizar=new Boton(null,10.856249f,4.645833f,2.41875f,0.6874997f,0);
		mensaje=new InputScreenJuego(batch, 11.0f, 2.645833f);
		BluetoothSingleton.getInstance().bluetoothManager.enableBluetooth();
		if(BluetoothSingleton.getInstance().bluetoothManager.EstadoAdapter()==false){
			AtsUtil.game.setScreen(AtsScreens.screenMultiPlayer);
		}
		
		float  y=AtsPos.Alto2;
			device1=new Boton(null,AtsPos.SuperiorIzquiero2,y,6.0f,.7f,0);
			device2=new Boton(null,AtsPos.SuperiorIzquiero2,y-.7f,6.0f,.7f,0);
			device3=new Boton(null,AtsPos.SuperiorIzquiero2,y-1.4f,6.0f,.7f,0);
			device4=new Boton(null,AtsPos.SuperiorIzquiero2,y-2.1f,6.0f,.7f,0);
			device5=new Boton(null,AtsPos.SuperiorIzquiero2,y-2.8f,6.0f,.7f,0);
			device6=new Boton(null,AtsPos.SuperiorIzquiero2,y-3.5f,6.0f,.7f,0);
			device7=new Boton(null,AtsPos.SuperiorIzquiero2,y-4.2f,6.0f,.7f,0);
		
		
		
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
		namePlayer = new Boton(null, 7.7f, 7.3f, 10.0f, 1.9f, 0);
		btnOK = new Boton(null, 15.0f, 7.574f, 1.0f, 1.0f, 0);
		btnAtras = new Boton(null, 13.8f, 1.0f, 1.5f, 1.2f, 0);
		campoT = new InputScreenJuego(batch,7.6f,7.574f);
	}

	@Override
	public void dispose() {
//		System.out.println("Dispose");
		Load.atlas.dispose();
		AtsUtil.batch.dispose();
		AtsSound.dispose();
	}
	public void CerrarAll(){
		
		BluetoothSingleton.getInstance().bluetoothManager.stop();
	}

	
	
}
