package com.videojuegos.utils;

import java.util.ArrayList;



public interface iBluetooth {
	
	 public <T> byte[] sendMessage2(T message);
	 public <T> T getMessage2();
	 public void enableBluetooth();
	 public void enableDiscoveribility();
	 public void discoverDevices();
	 public void stopDiscovering();
	 public boolean startServer();
	 public void connectToServer(String adress);
	 public boolean Estado();
	 public String getMessage();
	 public boolean isConnected();
	 public boolean canConnect();
	 public void stop();
	 public void ChangetoClose();
	 public boolean isDiscovering();
	 public boolean EstadoAdapter();
	 public void AsignarPartida(String Name,String campo2);
	 public void ReasignarNombre();
	 public boolean CerrarPartida();
	 public int Conectados();
	 public boolean IniciarPartida();
	 public ArrayList<String> Jugadores();
	 public int RecuperarNumero();
	 public ArrayList<String> devicescan();
	 public int GetJugadorID();
	 public void CleanLista();
	 public boolean Contestado();
	 public boolean Conectado();
	 public int RecuperarTurno();
	
}
