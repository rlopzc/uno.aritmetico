package com.videojuegos.unoarit;

import android.content.Context;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.Load;

public class MainP extends Game {
	public static Context context;

	public MainP(Context context) {
		this.context = context;
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void create() {
//		AtsUtil.crearAtlas();
		Load.load(this);
		Gdx.app.postRunnable(new Runnable() {

			@Override
			public void run() {
				setScreen(AtsScreens.screenMain);
			}


		});
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	

}
