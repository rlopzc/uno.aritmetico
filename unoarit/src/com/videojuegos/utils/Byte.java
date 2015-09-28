package com.videojuegos.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Byte<T> {
	

	public synchronized static <T> byte[] send(T t) {
		
		byte[] sendObject = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;

		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			
			Object<T> object = new Object<T>();
			object.setT(t);
			
			oos.writeObject(object);
			oos.flush();

			sendObject = baos.toByteArray();

			baos.close();
			oos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sendObject;
	}

	@SuppressWarnings("unchecked")
	public synchronized static <T> T getSend(byte[] send) {
		T getObject = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;

		try {
			bais = new ByteArrayInputStream(send);
			ois = new ObjectInputStream(bais);

			Object<T> objeto = (Object<T>) ois.readObject();
			getObject = objeto.getT();

			bais.close();
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getObject;
	}
	
	@SuppressWarnings("serial")
	private static class Object<T> implements java.io.Serializable{	
		private T t;
		
		public void setT(T t){
			this.t = t;
		}
		
		public T getT(){
			return t;
		}
	}

}
