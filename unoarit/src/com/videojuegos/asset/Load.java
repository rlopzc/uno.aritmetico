package com.videojuegos.asset;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.videojuegos.cartas.Carta;
import com.videojuegos.cartas.Mazo;
import com.videojuegos.input.Letra;

public class Load {
	// atlas
	public static TextureAtlas atlas;
	//public static TextureAtlas letrasnumeros;
	// logo
	public static Sprite logo;
	// Mazo
	public static Sprite mazoS;
	// fondos
	public static Sprite good;
	public static Sprite bad;
	public static Sprite backgroundayuda;
    public static Sprite backgroundplay1;
	public static Sprite backgroundplay2;
	public static Sprite backgroundplay3;
	public static Sprite backgroundmain;
	public static Sprite backgroundnumplayers;
	public static Sprite backgroundeligecolor;
	public static Sprite backgroundganaste;
	public static Sprite backgroundperdiste;
	public static Sprite backgroundsiguientelandscape;
	public static Sprite backgroundsiguienteportrait;
	public static Sprite backgroundmultijugador;
	public static Sprite backgroundcrearpartida;
	public static Sprite backgroundunirsepartida;	
	// botones
	public static Sprite btncambiar;
	public static Sprite btnmostrar;
	public static Sprite btnsoundoff;
	public static Sprite btnexportarDb;
	public static Sprite btnatras;
	// titulos
	public static Sprite correctoporcolor;
	public static Sprite correctoporresultado;
	public static Sprite correctocomodin;
	public static Sprite incorrecto;
	// cartas
	public static Mazo mazo;
	public static Carta[] cartasBase;
	public static ArrayList<Carta> help;
	//numeros
	public static Sprite cero;
	public static Sprite uno;
	public static Sprite dos;
	public static Sprite tres;
	public static Sprite cuatro;
	public static Sprite cinco;
	public static Sprite seis;
	public static Sprite siete;
	public static Sprite ocho;
	public static Sprite nueve;
	//letras
	public static Sprite a;
	public static Sprite b;
	public static Sprite c;
	public static Sprite d;
	public static Sprite e;
	public static Sprite f;
	public static Sprite g;
	public static Sprite h;
	public static Sprite i;
	public static Sprite j;
	public static Sprite k;
	public static Sprite l;
	public static Sprite m;
	public static Sprite n;
	public static Sprite enie;
	public static Sprite o;
	public static Sprite p;
	public static Sprite q;
	public static Sprite r;
	public static Sprite s;
	public static Sprite t;
	public static Sprite u;
	public static Sprite v;
	public static Sprite w;
	public static Sprite x;
	public static Sprite y;
	public static Sprite z;

	public static void load(Game game) {
		AtsUtil.load(game);
		AtsSound.load();
		AtsScreens.load();
		// Creamos el atlas
		atlas = new TextureAtlas(Gdx.files.internal("assets/atlas.atlas"));
		//letrasnumeros = new TextureAtlas(Gdx.files.internal("assets/letrasnumeros.atlas"));
		// Creamos un ArrayList donde se guardaran las cartas
		mazo = new Mazo();
		// Cargamos el logo
		logo = atlas.createSprite("logo");
		// Cargamos el mazo
		mazoS = atlas.createSprite("mazo");
		// cargamos los fondos
		backgroundayuda = atlas.createSprite("backgroundayuda");
		backgroundplay1 = atlas.createSprite("backgroundplay1");
		backgroundplay2 = atlas.createSprite("backgroundplay2");
		backgroundplay3 = atlas.createSprite("backgroundplay3");
		backgroundmain = atlas.createSprite("backgroundmain2");
		backgroundnumplayers = atlas.createSprite("backgroundnumplayers");
		backgroundeligecolor = atlas.createSprite("backgroundeligecolor");
		backgroundganaste = atlas.createSprite("backgroundganaste");
		backgroundperdiste = atlas.createSprite("backgroundperdiste");
		backgroundsiguientelandscape = atlas.createSprite("backgroundsiguientelandscape");
		backgroundsiguienteportrait = atlas.createSprite("backgroundsiguienteportrait");
		backgroundmultijugador = atlas.createSprite("backgroundmultijugador");
		backgroundcrearpartida = atlas.createSprite("backgroundcrearpartida");
		backgroundunirsepartida = atlas.createSprite("backgroundunirsepartida");
		good=atlas.createSprite("good");
		bad=atlas.createSprite("bad");

		// cargamos los botones
		btncambiar = atlas.createSprite("btncambiar");
		btnmostrar = atlas.createSprite("btnmostrar");
		btnsoundoff = atlas.createSprite("soundoff");
		btnexportarDb = atlas.createSprite("btnmostrar");
		btnatras = atlas.createSprite("atras");
		//Cargamos el mazo
		mazo.cargarMazo(atlas);
		mazo.setCartasId();
		cartasBase = mazo.getCartasBase();
		help = mazo.cargarCartasAyuda();
		// Cargamos titulos
		correctoporcolor = atlas.createSprite("correctoporcolor");
		correctoporresultado = atlas.createSprite("correctoporresultado");
		correctocomodin = atlas.createSprite("correctocomodin");
		incorrecto = atlas.createSprite("incorrecto");
		//Cargamos los numeros
		cero = atlas.createSprite("0");
		uno = atlas.createSprite("1");
		dos = atlas.createSprite("2");
		tres = atlas.createSprite("3");
		cuatro = atlas.createSprite("4");
		cinco = atlas.createSprite("5");
		seis = atlas.createSprite("6");
		siete = atlas.createSprite("7");
		ocho = atlas.createSprite("8");
		nueve = atlas.createSprite("9");
		
		//Cargamos las letras
		a = atlas.createSprite("a");
		b = atlas.createSprite("b");
		c = atlas.createSprite("c");
		d = atlas.createSprite("d");
		e = atlas.createSprite("e");
		f = atlas.createSprite("f");
		g = atlas.createSprite("g");
		h = atlas.createSprite("h");
		i = atlas.createSprite("i");
		j = atlas.createSprite("j");
		k = atlas.createSprite("k");
		l = atlas.createSprite("l");
		m = atlas.createSprite("m");
		n = atlas.createSprite("n");
		enie = atlas.createSprite("enie");
		o = atlas.createSprite("o");
		p = atlas.createSprite("p");
		q = atlas.createSprite("q");
		r = atlas.createSprite("r");
		s = atlas.createSprite("s");
		t = atlas.createSprite("t");
		u = atlas.createSprite("u");
		v = atlas.createSprite("v");
		w = atlas.createSprite("w");
		x = atlas.createSprite("x");
		y = atlas.createSprite("y");
		z = atlas.createSprite("z");
		
		Letra.load();
	}
	
}
