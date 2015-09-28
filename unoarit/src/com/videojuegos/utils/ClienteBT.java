package com.videojuegos.utils;

import java.util.ArrayList;

public class ClienteBT implements iBluetooth{
	
	public BluetoothSingleton bt;
	@Override
	public <T> byte[] sendMessage2(T message) {
		return bt.bluetoothManager.sendMessage2(message);
	}

	@Override
	public <T> T getMessage2() {
		return bt.bluetoothManager.getMessage2();
	}

	@Override
	public void enableBluetooth() {
		bt.bluetoothManager.enableBluetooth();
	}

	@Override
	public void enableDiscoveribility() {
		bt.bluetoothManager.enableDiscoveribility();
	}

	@Override
	public void discoverDevices() {
		bt.bluetoothManager.discoverDevices();		
	}

	@Override
	public void stopDiscovering() {
		bt.bluetoothManager.stopDiscovering();
	}

	@Override
	public boolean startServer() {
		return bt.bluetoothManager.startServer();
	}

	@Override
	public void connectToServer(String adress) {
		bt.bluetoothManager.connectToServer(adress);
	}

	@Override
	public String getMessage() {
		return bt.bluetoothManager.getMessage();
	}

	@Override
	public boolean isConnected() {
		return bt.bluetoothManager.isConnected();
	}

	@Override
	public boolean canConnect() {
		return bt.bluetoothManager.canConnect();
	}

	@Override
	public void stop() {
		bt.bluetoothManager.stop();
	}

	@Override
	public boolean isDiscovering() {
		return bt.bluetoothManager.isDiscovering();
	}
	
	
	public boolean EstadoAdapter(){
		return bt.bluetoothManager.EstadoAdapter();
	}
	public void AsignarPartida(String Name,String campo2){
		bt.bluetoothManager.AsignarPartida(Name,campo2);
	}
	public void ReasignarNombre(){
		bt.bluetoothManager.ReasignarNombre();
	}
	public boolean CerrarPartida(){
		return bt.bluetoothManager.CerrarPartida();
	}
	
	public int Conectados(){
		return bt.bluetoothManager.Conectados();
	}
	public boolean IniciarPartida(){
		return bt.bluetoothManager.IniciarPartida();
	}
	
	public ArrayList<String> Jugadores(){
		return bt.bluetoothManager.Jugadores();
	}

	public int RecuperarNumero(){
		return bt.bluetoothManager.RecuperarNumero();
	}
	public ArrayList<String> devicescan(){
		return bt.bluetoothManager.devicescan();
	}
	 public void ChangetoClose(){
		 bt.bluetoothManager.ChangetoClose();
	 }
	public boolean Estado(){
		return bt.bluetoothManager.Estado();
	}
	public int GetJugadorID(){
		return bt.bluetoothManager.GetJugadorID();
	}
	public void CleanLista(){
		bt.bluetoothManager.CleanLista();
	}
	public boolean Contestado(){
		return bt.bluetoothManager.Contestado();
	}
	public boolean Conectado(){
		return bt.bluetoothManager.Conectado();
	}
	public int RecuperarTurno(){
		return bt.bluetoothManager.RecuperarTurno();
	}
}
