package com.videojuegos.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import com.videojuegos.unoarit.BTActivity;
import com.videojuegos.utils.iBluetooth;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.videojuegos.utils.Byte;


public class BluetoothManager implements iBluetooth {
	private static final String TAG = "BluetoothServiceUno";
	private static final boolean D = true;
	private static final String NAME = "BluetoothUno";
	private Activity currentActivity;
	private final Handler mHandler;
	private int mState;
	private AcceptThread mAcceptThread;
	private ConnectThread mConnectThread;
	private ConnectedThread mConnectedThread;
	private ArrayList<UUID> mUuids;
	public static final int STATE_NONE = 0;
	public static final int STATE_LISTEN = 1;
	public static final int STATE_CONNECTING = 2;
	public static final int STATE_CONNECTED = 3;
	public boolean canConnect = true;
	public String message;
	public boolean messageTaken = true;
	public BluetoothAdapter mAdapter;
    public String RescueName;
    public int Numeros;
    public int TurnoActual=1;
    public int JugadorID;
    public static int ContadorID=1;
    /**
     * Listas para el manejo de Operaciones
     * **/
    public LinkedList<BluetoothDevice> devices;
    private ArrayList<String> mDeviceAddresses;
    public ArrayList<ConnectedThread> mConnThreads;
    private ArrayList<BluetoothSocket> mSockets;
    public ArrayList<String> JugadoresName;
    public ArrayList<String> devicesscan;
    /**Banderas del Juego
     * **/
    public boolean EstadoConexion=true;
    public boolean CerrarPartida=false;
    public boolean IniciarPartida=false;
    public boolean isConnected = false;
    public boolean Gane=false;
    public boolean Contesto;
    public boolean Conectado=false;
    
    
    private byte[] mensaje;
    

    
/**
 * Metodo que se encarga de agregar los dispositivos que retorna la instruccion startDiscovery del BluetoothAdapter
 * **/
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if(devices.size()>0){
					
					boolean encontrado=false;
				for(int k=0;k<devices.size();k++){
				
					if(device.getName().equals(devices.get(k))){
						
						encontrado=true;
					}
					
				}
				
				if(encontrado==false)
				devices.add(device);
				
				}
				else
				devices.add(device);
			}
		}
	};
	/**
	 * Metodo que se encarga de retornar una lista de los nombres de las partidas que se encuentran disponibles para unirse
	 * **/
	public ArrayList<String> devicescan(){
		devicesscan.clear();
		
		if(devices.size()>0){
			for(int i=0;i<devices.size();i++){
			if(devices.get(i).getName().contains("partida"))
				devicesscan.add(devices.get(i).getName());
			}
			return devicesscan;
		}
		else{
			devicesscan.add("no hay partidas");
			return devicesscan;
		}
	}

	/**
	 * Metodo para retornar el valor de las conexiones en el Servidor
	 * **/
	public boolean isConnected() {
		return isConnected;
	}
	/**
	 * Metodo que se encarga de retornar el ultimo mensaje que se recibio en la Partida o Jugador
	 * **/
	public String getMessage() {
			if (this.message != null)
			return this.message;
			else
				return null;}

	
	private void init() {
		devices = new LinkedList<BluetoothDevice>();
	}
	/**
	 * Constructor Principal de la clase BluetoothManager, aqui se inicializan las listas y objetos para que puedan ser utilizados
	 * en los metodos posteriores
	 * **/
	public BluetoothManager(Activity activity, Handler handler) {
		init();
		
		currentActivity = activity;
		mState = STATE_NONE;
		mHandler = handler;
		devicesscan=new ArrayList<String>();
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		currentActivity.registerReceiver(mReceiver, filter);
		mDeviceAddresses = new ArrayList<String>();
	    mConnThreads = new ArrayList<ConnectedThread>();
	    mSockets = new ArrayList<BluetoothSocket>();
		mUuids = new ArrayList<UUID>();
	        // 7 randomly-generated UUIDs. These must match on both server and client.
	    mUuids.add(UUID.fromString("b7746a40-c758-4868-aa19-7ac6b3475dfc"));
	    mUuids.add(UUID.fromString("2d64189d-5a2c-4511-a074-77f199fd0834"));
	    mUuids.add(UUID.fromString("e442e09a-51f3-4a7b-91cb-f638491d1412"));
	    mUuids.add(UUID.fromString("a81d6504-4536-49ee-a475-7d96d09439e4"));
	    mUuids.add(UUID.fromString("aa91eab1-d8ad-448e-abdb-95ebba4a9b55"));
	    mUuids.add(UUID.fromString("4d34da73-d0a4-4f40-ac38-917e0a9dee97"));
	    mUuids.add(UUID.fromString("5e14d4df-9c8a-4db7-81e4-c937564c86e0"));
	    JugadoresName=new ArrayList<String>();
	    
	}
/**
 * Metodo para cambiar el Estado de la conexion
 * **/
	private synchronized void setState(int state) {
		mState = state;

		mHandler.obtainMessage(BTActivity.MESSAGE_STATE_CHANGE, state, -1)
				.sendToTarget();
	}

	public synchronized int getState() {
		return mState;
	}
	/**
	 * Metodo de la clase AccepThread que se encarga de inicializar un hilo para las conexion entrante
	 * **/
	public synchronized void start() {
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		if (mAcceptThread == null) {
			mAcceptThread = new AcceptThread();
			mAcceptThread.start();
		}
		setState(STATE_LISTEN);
	}
	/**
	 * Metodo que se encarga de realizar una conexion al Servidor o cualquier otro dispositivo Bluetooth
	 * **/
	public synchronized void connect(BluetoothDevice device) {
		 if (D) Log.d(TAG, "connect to: " + device);

	        // Cancel any thread attempting to make a connection
	        if (mState == STATE_CONNECTING) {
	            if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
	        }

	        // Cancel any thread currently running a connection
	        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

	        // Create a new thread and attempt to connect to each UUID one-by-one.    
	       if(EstadoConexion==false){
	    	   
	       }
	       else{
	    	   for (int i = 0; i < 7; i++) {
	        	try {
	                mConnectThread = new ConnectThread(device, mUuids.get(i));
	                mConnectThread.start();
	                setState(STATE_CONNECTING);
	        	} catch (Exception e) {
	        	}
	        }
	    	   
	       }
	}

	
	
	/**
	 * Metodo que retorna el numero de Jugador asignado por el Servidor
	  **/
	public int GetJugadorID(){
		return JugadorID;
	}
	/**
	 * Metodo que se encarga de retornar una lista de los jugadores que se encuentran conectados a la Partida
	 * */
	public ArrayList<String> Jugadores(){
		ArrayList<String> nombres=new ArrayList<String>();
		nombres.clear();
		if(JugadoresName.size()>=1){
		for(int i=0;i<JugadoresName.size();i++){
		nombres.add(JugadoresName.get(i));
		}
		return nombres;
		}
		else{
			return null;
		}
	}
	
	/**
	 *Metodo que se encarga de crear un hilo para poder escuchar a los dispositivos conectados y poder enviarles datos 
	 **/
	public synchronized void connected(BluetoothSocket socket,BluetoothDevice device) {
		mConnectedThread = new ConnectedThread(socket);
		mConnectedThread.start();
		mConnThreads.add(mConnectedThread);
        
		JugadoresName.add(device.getName());
		
		ContadorID++;
		
		String mensajer="ID"+ContadorID;
		
		mConnectedThread.write(Byte.send(mensajer));
		Message msg = mHandler.obtainMessage(BTActivity.MESSAGE_DEVICE_NAME);
		Bundle bundle = new Bundle();
		bundle.putString(BTActivity.DEVICE_NAME, device.getName());
		msg.setData(bundle);
		mHandler.sendMessage(msg);

		setState(STATE_CONNECTED);
	}
/**
 * Metodo para desactivar o detener el servidor
 * 
 **/
	public synchronized void stop() {
		ReasignarNombre();
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}
		if (mAcceptThread != null) {
			mAcceptThread.cancel();
			mAcceptThread = null;
		}
		setState(STATE_NONE);
		isConnected = false;
		CerrarPartida=false;
		IniciarPartida=false;
		EstadoConexion=true;
		Conectado=false;
	}

	/**
	 * Metodo para escribir bytes a los dispositivos conectados 
	 * @param out
	 **/
	public void write(byte[] out) {
		for (int i = 0; i < mConnThreads.size(); i++) {
    		try {
                // Create temporary object
                ConnectedThread r;
                // Synchronize a copy of the ConnectedThread
                synchronized (this) {
                    if (mState != STATE_CONNECTED) return;
                    r = mConnThreads.get(i);
                }
                // Perform the write unsynchronized
                r.write(out);
    		} catch (Exception e) {    			
    		}
    	}
	}
	
	/**
	 *Metodo para retornar el numero de dispositivos conectados al Servidor 
	 ***/
	public int Conectados(){
		return mConnThreads.size();
	}
	
	public boolean Contestado(){
		return Contesto;
	}

	private void connectionFailed() {
		setState(STATE_LISTEN);

//		canConnect = false;
//		Message msg = mHandler.obtainMessage(BTActivity.MESSAGE_TOAST);
//		Bundle bundle = new Bundle();
//		bundle.putString(BTActivity.TOAST, "Unable to connect device");
//		msg.setData(bundle);
//		mHandler.sendMessage(msg);
	}

	private void connectionLost() {
		setState(STATE_LISTEN);

		Message msg = mHandler.obtainMessage(BTActivity.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BTActivity.TOAST, "Device connection was lost");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	
	/**
	 * Metodo que se encarga de hacer visible el Bluetooth po run lapse de tiempo de 5 minutos
	 **/
	public void enableDiscoveribility() {
		
		Intent discoverableIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(
				BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		currentActivity.startActivity(discoverableIntent);
	}
	/**
	 * Metodo para asignarle un nombre de Partida o Jugador al Bluetooth, de igual forma se respalda el nombre anterior del 
	 * Bluetooth para que pueda ser repuesto
	 **/
	public void AsignarPartida(String Name,String campo2){
		RescueName=mAdapter.getName();
		mAdapter.setName(Name);
		JugadoresName.add(campo2);
	}
	
	
	/**
	 * Metodo para reasignar el nombre Original que tenia el Bluetooth
	 **/
	public void ReasignarNombre(){
		mAdapter.setName(RescueName);
	}

	public void CleanLista(){
		JugadoresName.clear();
	}
	public BluetoothAdapter getAdapter() {
		if (mAdapter == null) {
			mAdapter = BluetoothAdapter.getDefaultAdapter();
		}

		return mAdapter;
	}
	public boolean EstadoAdapter(){
		if(getAdapter().isEnabled()){
			return true;
		}
		else
			return false;
	}
/**
 * Metodo para activar el Bluetooth en caso de que se encuentre desactivado
 * 
 **/
	public void enableBluetooth() {
		if (!getAdapter().isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			currentActivity.startActivityForResult(enableIntent, 2);
		}
	}

	public boolean isDiscovering() {
		return getAdapter().isDiscovering();
	}
/**
 * Metodo para realizar un scan de los dispositivos que se encuentran dentro del rango del Bluetooth	
 **/
	public void discoverDevices() {
		if (getAdapter().isDiscovering()) {
			getAdapter().cancelDiscovery();
		}
		devices.clear();
		getAdapter().startDiscovery();
	}

	public void stopDiscovering() {
		getAdapter().cancelDiscovery();
	}

	/**
	 * Metodo que inicializa el Servidor, y permite las conexiones a el
	 * 
	 **/
	public boolean startServer() { 
		if (getAdapter().isEnabled()) {
			this.start();
			return true;
		}
		
		return false;
	}

	public boolean canConnect() {
		return this.canConnect;
	}
/**
 * Metodo que se emplea unicamente en el Screen Unirse Partida, se encarga de unir el Jugador a alguna partida disponible
 * 
 **/
	public void connectToServer(String adress) {
		canConnect = true;
		
		if (getAdapter().isDiscovering()) {
			getAdapter().cancelDiscovery();
		}
		try {
			BluetoothDevice device = getAdapter().getRemoteDevice(
					adress);
			this.connect(device);
			
			JugadorID=1;
		} catch (Exception exc) {
			
		}
	}

	
	
	/**
	 * Metodo que se encarga de transformar cualquier objeto a bytes,esto con el fin de poder enviarlo
	 **/
	public <T> byte[] sendMessage2(T mensaje) {
		
		Contesto=false;
		this.mensaje = null;

		try {
			this.mensaje = Byte.send(mensaje);
			this.write(this.mensaje);
			return this.mensaje;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo que retorna los bytes recibidos del Servidor o Cliente
	 **/
public <T> T getMessage2() {
		try {
			return Byte.getSend(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	
	public int RecuperarNumero(){
		return Numeros;
		
	}
	
	public int RecuperarTurno(){
		return TurnoActual;
	}
	public boolean CerrarPartida(){
		return CerrarPartida;
	}
	/**
	 * Bandera para determinar que el Jugador puede iniciar su partida
	 **/
	public boolean IniciarPartida(){
		return IniciarPartida;
	}
	
	public void ChangetoClose(){
		EstadoConexion=false;
	}
	
	public boolean Estado(){
		return EstadoConexion;
	}
	
	
	public boolean Conectado(){
		return Conectado;
	}
	/**
	 * Metodo que se encarga de levantar el Servidor y permite realizar las conexiones a el
	 * @author Erak2
	 *
	 **/
	private class AcceptThread extends Thread {
		  BluetoothServerSocket mmServerSocket=null;

		public AcceptThread() {
			getAdapter();
		}

		public void run() {
			
			BluetoothSocket socket = null;
			
			if(EstadoConexion==true){
				
			
			 try {
	            	// Listen for all 7 UUIDs
	            	for (int i = 0; i < 7; i++) {
	            		mmServerSocket = mAdapter.listenUsingRfcommWithServiceRecord(NAME, mUuids.get(i));
	                    socket = mmServerSocket.accept();
	                    if (socket != null) {
	                    	String address = socket.getRemoteDevice().getAddress();
		                    mSockets.add(socket);
		                    mDeviceAddresses.add(address);
		                    connected(socket, socket.getRemoteDevice());
	                    }	                    
	            	}
	            } catch (IOException e) {
					
				}
				
			
			}
			else{
				
			}
			 
			 
		}

		public void cancel() {
			try {
				mmServerSocket.close();
			} catch (IOException e) {
			}
		}
	}
/**
 * Objeto que se utiliza para crear las conexiones entre el socket del cliente y el socket del servidor
 * @author Erak2
 *
 **/
	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;
		private UUID tempUuid;

		  public ConnectThread(BluetoothDevice device, UUID uuidToTry) {
	            mmDevice = device;
	            BluetoothSocket tmp = null;
	            tempUuid = uuidToTry;

	            // Get a BluetoothSocket for a connection with the
	            // given BluetoothDevice
	            try {
	                tmp = device.createRfcommSocketToServiceRecord(uuidToTry);        	
	            } catch (IOException e) {
	                Log.e(TAG, "create() failed", e);
	            }
	            mmSocket = tmp;
	        }

		public void run() {
			getAdapter().cancelDiscovery();

			try {
				mmSocket.connect();
			} catch (IOException connectException) {
				if (tempUuid.toString().contentEquals(mUuids.get(6).toString())) {
                    connectionFailed();
            	}
				try {
					mmSocket.close();
				} catch (IOException closeException) {
				}
				BluetoothManager.this.start();
				return;
			}

			synchronized (BluetoothManager.this) {
				mConnectThread = null;
			}

			connected(mmSocket, mmDevice);
		}

		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
			}
		}
	}
/**
 * Metodo que hereda de un Thread,esto con el fin de poder tener estable y activas todas las conexiones hechas al servidor,
 * tambien de igual forma sirve como apoyo para el intercambio de informacion entre Servidor y cliente
 * @author Erak2
 *
 */
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread(BluetoothSocket socket) {
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;
			isConnected = true;

			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		public void run() {
			byte[] buffer = new byte[1024];
			@SuppressWarnings("unused")
			int bytes;

			while (true) {
				
				try {
					bytes = mmInStream.read(buffer);
						mensaje=buffer;
						setMessage();
						
						//sendMessage2("Ya");
						
				} catch (IOException e) {
					connectionLost();
					break;
				}
			}
		}
/**
 * Metodo para escribir bytes en el Buffer del Cliente
 * @param bytes
 **/
		public void write(byte[] bytes) {
			try {
				mmOutStream.write(bytes);
			} catch (IOException e) {
			}
		}

		public void cancel() {
			try {
				mmSocket.close();
				isConnected = false;
			} catch (IOException e) {
			}
		}
	}
/**
 * Metodo que se encarga de realizar condiciones de acuerdo a los mensajes recibidos, activando banderas y asignando valores
 **/
	private void setMessage() {
		try{
			this.message=getMessage2();
			
			System.out.println("mensaje"+this.message);
			if(this.message.equalsIgnoreCase("Cerrar")){
				CerrarPartida=true;
			}
			if(this.message.equalsIgnoreCase("Iniciar")){
				IniciarPartida=true;
			}
			
			if(this.message.equalsIgnoreCase("Ya")){
				Contesto=true;
			}
			
			if(this.message.equalsIgnoreCase("ID2")){
				this.JugadorID=2;
				
				Conectado=true;
				
			}
			if(this.message.equalsIgnoreCase("ID3")){
				this.JugadorID=3;
				Conectado=true;
			}
			if(this.message.equalsIgnoreCase("ID4")){
				this.JugadorID=4;
				Conectado=true;
			}
			
			if(this.message.equals("1")){
				Numeros=1;}
			if(this.message.equals("2")){
				Numeros=2;}
			if(this.message.equals("3")){
				Numeros=3;}
			if(this.message.equals("4")){
				Numeros=4;
			}
			
			if(this.message.equalsIgnoreCase("T1")){
				TurnoActual=1;}
			if(this.message.equalsIgnoreCase("T2")){
				TurnoActual=2;}
			if(this.message.equalsIgnoreCase("T3")){
				TurnoActual=3;}
			if(this.message.equalsIgnoreCase("T4")){
				TurnoActual=4;
			}
			
			
			System.out.println("Turno"+TurnoActual);
		}
		
		catch(Exception e){
			
		}
		
		
		
		
		

			
		

	
}
	
}