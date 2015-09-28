package com.videojuegos.unoarit;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.videojuegos.communication.BluetoothManager;
import com.videojuegos.utils.BluetoothSingleton;
 import android.os.Handler;

 
public class BTActivity extends AndroidApplication {
 
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
 
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
       BluetoothManager btm = new BluetoothManager(this, new Handler());
        BluetoothSingleton.getInstance().bluetoothManager = btm;
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        initialize(new MainP(this), cfg);

    }
    
    
  
    
    
   
}
