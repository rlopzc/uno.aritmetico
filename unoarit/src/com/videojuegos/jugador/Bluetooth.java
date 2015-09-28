package com.videojuegos.jugador;

import com.videojuegos.asset.AtsUtil;
import com.videojuegos.utils.BluetoothSingleton;

public class Bluetooth {

	public static boolean bluetoothTurno() {
		if (Juego.turno == Juego.idMachine)
			return true;
		return false;
	}

	public static boolean machineTurno(Player p) {
		if ((machine()) && (p.getId() == 2))
			return true;
		return false;
	}

	public static boolean bluetooth() {
		if ((!AtsUtil.mismoDispositivo) && (!AtsUtil.machine))
			return true;
		return false;
	}

	public static boolean machine() {
		if ((!AtsUtil.mismoDispositivo) && (AtsUtil.machine))
			return true;
		return false;
	}

	public static boolean mismoDispositivo() {
		if ((AtsUtil.mismoDispositivo) && (!AtsUtil.machine))
			return true;
		return false;
	}

	public static synchronized <T> void sendMessage(T message) {
		if (bluetooth() && (bluetoothTurno())) {
			System.out.println("entro a enviarse");
			BluetoothSingleton.getInstance().bluetoothManager
					.sendMessage2(message);
		}
	}

	public static synchronized <T> T getMessage() {
		if (bluetooth() && (!bluetoothTurno())) {
			try {
				T mensage = BluetoothSingleton.getInstance().bluetoothManager
						.getMessage2();
				return mensage;
			} catch (Exception e) {
			}
		}
		return null;
	}

}
