package com.videojuegos.cartas;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.videojuegos.asset.DataCarta;
import com.videojuegos.asset.Load;
import com.videojuegos.jugador.Juego;
import com.videojuegos.jugador.Player;

import java.util.ArrayList;

public class Mazo {
	// ArrayList que contiene las cartas
	private ArrayList<Carta> mazo,cartasAyuda;
	private Carta[] eligeColor;

	/**
	 * Constructor crea instancia de el ArrayList que contiene las cartas
	  */

	public Mazo() {
		mazo = new ArrayList<Carta>();
        eligeColor = new Carta[5];
        cartasAyuda = new ArrayList<Carta>();
	}

	public void cargarMazo(TextureAtlas atlas) {
		cargarCartasAMFU(atlas);
        cargarCartasAZFU(atlas);
        cargarCartasMO(atlas);
        cargarCartasROJ(atlas);
        cargarCartasVEFU(atlas);
        cargarCartasCOMODIN(atlas);

//		cargarCartasAZBA(atlas);
//		cargarCartasAMBA(atlas);
//		cargarCartasNA(atlas);
//		cargarCartasROS(atlas);
//		cargarCartasVEBA(atlas);
	}
	
	public void setCartasId(){
		for (int i = 0; i < mazo.size(); i++) {
			mazo.get(i).setId(i);
		}
	}

    /**
     * ESTE METODO INICIALIZA LA INFORMACION DE LAS CARTAS DE COLOR: ROJO, AMARILLO, VERDE, AZUL Y MORADO
     * LOS CUALES SON LLAMADOS "CARTAS BASE".
     * SI QUISIERAMOS AGREGAS MAS COLORES, DEBERIAMOS AUMENTAR EL TAMAÑO DEL ARREGLO EN EL CONSTRUCTOR DE ESTA CLASE
     * Y AGREGAS MAS LINEAS (IGUALES), AL FINAL DE ESTE METODO.
     * <p>
     * ESTE METODO TOMA EN CONSIDERACION, QUE LAS VARIABLES COMO amfubase, HAN SIDO CREADAS EN LA CLASE "DataCarta".
     */
    private void cargarCartasBase() {

        eligeColor[0] = new Carta(Load.atlas, DataCarta.amfuBase, DataCarta.amfu, DataCarta.cBase, DataCarta.sinOperacion);
        eligeColor[1] = new Carta(Load.atlas, DataCarta.azfuBase, DataCarta.azfu, DataCarta.cBase, DataCarta.sinOperacion);
        eligeColor[2] = new Carta(Load.atlas, DataCarta.morBase, DataCarta.mo, DataCarta.cBase, DataCarta.sinOperacion);
        eligeColor[3] = new Carta(Load.atlas, DataCarta.rojBase, DataCarta.roj, DataCarta.cBase, DataCarta.sinOperacion);
        eligeColor[4] = new Carta(Load.atlas, DataCarta.vefuBase, DataCarta.vefu, DataCarta.cBase, DataCarta.sinOperacion);

	}

	public ArrayList<Carta> cargarCartasAyuda(){
		cartasAyuda.add(new Carta(Load.atlas, DataCarta.ros4r3, DataCarta.ros, DataCarta.uno, DataCarta.resta));
		cartasAyuda.add(new Carta(Load.atlas, DataCarta.ros0m2, DataCarta.ros, DataCarta.uno, DataCarta.multiplicacion));
		cartasAyuda.add(new Carta(Load.atlas, DataCarta.vefu1s0, DataCarta.vefu, DataCarta.uno, DataCarta.suma));
		cartasAyuda.add(new Carta(Load.atlas, DataCarta.mor3r1, DataCarta.mo, DataCarta.uno, DataCarta.resta));
		cartasAyuda.add(new Carta(Load.atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion));
		return cartasAyuda;
	}
	
	/**
	 * Regresa el ArrayList que contiene las cartas
	  */

	public ArrayList<Carta> getCartas() {
		return mazo;
	}

	public void setCartas() {
		for (int i = 0; i < (Juego.centroCartaMazo.size() - 1); i++) {
			this.mazo.add(Juego.centroCartaMazo.remove(i));
		}
	}

	public void rellenarMazo(ArrayList<Player> player) {
		for (int i = 0; i < Juego.centroCartaMazo.size(); i++) {
			this.mazo.add(Juego.centroCartaMazo.remove(i));
		}

		for (int i = 0; i < player.size(); i++) {
			ArrayList<Carta> p = player.get(i).getMazoPlayer();
			for (int j = 0; j < p.size(); j++) {
				this.mazo.add(p.remove(j));
			}
		}
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * @param String
	 *            nombreCarta
	 * @param int color
	 * @param int valor @ Agrega una carta nueva al ArrayList que contiene las
	 *        cartas
	 * @param String operacion
	  */

	public void addCarta(TextureAtlas atlas, String nombreCarta, int color,
						 int valor, String operacion) {
		mazo.add(new Carta(atlas, nombreCarta, color, valor, operacion));
	}

	/**
	 * @param int index
	 * 
	 *        <pre>
	 * Regresa y remueve la carta que se encuentra en el indice del ArrayList que contiene las cartas,
	 * que coincide con el valor del parametro index.
	 * </pre>
	  */

	public Carta getCartaRemove(int index) {
		return mazo.remove(index);
	}

	/**
	 * @param int index
	 * 
	 *        <pre>
	 * Regresa la carta que se encuentra en el indice del ArrayList que contiene las cartas,
	 * que coincide con el valor del parametro index.
	 * </pre>
	  */

	public Carta getCarta(int index) {
		return mazo.get(index);
	}
	
	public Carta getCartaPorId(int index) {
		for (int i = 0; i < mazo.size(); i++) {
			if(mazo.get(i).getId() == index)
				return mazo.get(i);
		}
		return null;
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color amarillo bajo
	 * </pre>
	 */

	public void cargarCartasAMBA(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.amba0s0, DataCarta.amba, DataCarta.cero, DataCarta.suma);// 1
		addCarta(atlas, DataCarta.amba0s1, DataCarta.amba, DataCarta.uno, DataCarta.suma);// 2
		addCarta(atlas, DataCarta.amba12d12, DataCarta.amba, DataCarta.uno, DataCarta.division);// 3
		addCarta(atlas, DataCarta.amba12r6, DataCarta.amba, DataCarta.seis, DataCarta.resta);// 4
		addCarta(atlas, DataCarta.amba13r9, DataCarta.amba, DataCarta.cuatro, DataCarta.resta);// 5
		addCarta(atlas, DataCarta.amba16d8, DataCarta.amba, DataCarta.dos, DataCarta.division);// 6
		addCarta(atlas, DataCarta.amba1s5, DataCarta.amba, DataCarta.seis, DataCarta.suma);// 7
		addCarta(atlas, DataCarta.amba25d5, DataCarta.amba, DataCarta.cinco, DataCarta.division);// 8
		addCarta(atlas, DataCarta.amba2s1, DataCarta.amba, DataCarta.tres, DataCarta.suma);// 9
		addCarta(atlas, DataCarta.amba3m0, DataCarta.amba, DataCarta.cero, DataCarta.multiplicacion);// 10
		addCarta(atlas, DataCarta.amba3r0, DataCarta.amba, DataCarta.tres, DataCarta.resta);// 11
		addCarta(atlas, DataCarta.amba4r0, DataCarta.amba, DataCarta.cuatro, DataCarta.resta);// 12
		addCarta(atlas, DataCarta.amba5r0, DataCarta.amba, DataCarta.cinco, DataCarta.resta);// 13
		addCarta(atlas, DataCarta.amba6r4, DataCarta.amba, DataCarta.dos, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color amarillo fuerte
	 * </pre>
	  */

	public void cargarCartasAMFU(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.amfu0d1, DataCarta.amfu, DataCarta.cero, DataCarta.division);// 1
		addCarta(atlas, DataCarta.amfu0s2, DataCarta.amfu, DataCarta.dos, DataCarta.suma);// 2
		addCarta(atlas, DataCarta.amfu0s5, DataCarta.amfu, DataCarta.cinco, DataCarta.suma);// 3
		addCarta(atlas, DataCarta.amfu10r9, DataCarta.amfu, DataCarta.uno, DataCarta.resta);// 4
		addCarta(atlas, DataCarta.amfu12d2, DataCarta.amfu, DataCarta.seis, DataCarta.division);// 5
		addCarta(atlas, DataCarta.amfu12d4, DataCarta.amfu, DataCarta.tres, DataCarta.division);// 6
		addCarta(atlas, DataCarta.amfu17r12, DataCarta.amfu, DataCarta.cinco, DataCarta.resta);// 7
		addCarta(atlas, DataCarta.amfu1m4, DataCarta.amfu, DataCarta.cuatro, DataCarta.multiplicacion);// 8
		addCarta(atlas, DataCarta.amfu20d10, DataCarta.amfu, DataCarta.dos, DataCarta.division);// 9
		addCarta(atlas, DataCarta.amfu20d5, DataCarta.amfu, DataCarta.cuatro, DataCarta.division);// 10
		addCarta(atlas, DataCarta.amfu4r4, DataCarta.amfu, DataCarta.cero, DataCarta.resta);// 11
		addCarta(atlas, DataCarta.amfu7d7, DataCarta.amfu, DataCarta.uno, DataCarta.division);// 12
		addCarta(atlas, DataCarta.amfu9r3, DataCarta.amfu, DataCarta.seis, DataCarta.resta);// 13
		addCarta(atlas, DataCarta.amfu9r6, DataCarta.amfu, DataCarta.tres, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color azul bajo
	 * </pre>
	 */

	public void cargarCartasAZBA(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.azba0d13, DataCarta.azba, DataCarta.cero, DataCarta.division);// 1
		addCarta(atlas, DataCarta.azba0s3, DataCarta.azba, DataCarta.tres, DataCarta.suma);// 2
		addCarta(atlas, DataCarta.azba15d3, DataCarta.azba, DataCarta.cinco, DataCarta.division);// 3
		addCarta(atlas, DataCarta.azba19r13, DataCarta.azba, DataCarta.seis, DataCarta.resta);// 4
		addCarta(atlas, DataCarta.azba19r15, DataCarta.azba, DataCarta.cuatro, DataCarta.resta);// 5
		addCarta(atlas, DataCarta.azba1m0, DataCarta.azba, DataCarta.cero, DataCarta.multiplicacion);// 6
		addCarta(atlas, DataCarta.azba20d20, DataCarta.azba, DataCarta.uno, DataCarta.division);// 7
		addCarta(atlas, DataCarta.azba20r17, DataCarta.azba, DataCarta.tres, DataCarta.resta);// 8
		addCarta(atlas, DataCarta.azba2d2, DataCarta.azba, DataCarta.uno, DataCarta.division);// 9
		addCarta(atlas, DataCarta.azba2r0, DataCarta.azba, DataCarta.dos, DataCarta.resta);// 10
		addCarta(atlas, DataCarta.azba2s3, DataCarta.azba, DataCarta.cinco, DataCarta.suma);// 11
		addCarta(atlas, DataCarta.azba3m2, DataCarta.azba, DataCarta.seis, DataCarta.multiplicacion);// 12
		addCarta(atlas, DataCarta.azba4m1, DataCarta.azba, DataCarta.cuatro, DataCarta.multiplicacion);// 13
		addCarta(atlas, DataCarta.azba4r2, DataCarta.azba, DataCarta.dos, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color azul fuerte
	 * </pre>
	  */

	public void cargarCartasAZFU(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.azfu0m0, DataCarta.azfu, DataCarta.cero, DataCarta.multiplicacion);// 1
		addCarta(atlas, DataCarta.azfu10r10, DataCarta.azfu, DataCarta.cero, DataCarta.resta);// 2
		addCarta(atlas, DataCarta.azfu11r6, DataCarta.azfu, DataCarta.cinco, DataCarta.resta);// 3
		addCarta(atlas, DataCarta.azfu11r7, DataCarta.azfu, DataCarta.cuatro, DataCarta.resta);// 4
		addCarta(atlas, DataCarta.azfu15d5, DataCarta.azfu, DataCarta.tres, DataCarta.division);// 5
		addCarta(atlas, DataCarta.azfu15r12, DataCarta.azfu, DataCarta.tres, DataCarta.resta);// 6
		addCarta(atlas, DataCarta.azfu18d3, DataCarta.azfu, DataCarta.seis, DataCarta.division);// 7
		addCarta(atlas, DataCarta.azfu20r19, DataCarta.azfu, DataCarta.uno, DataCarta.resta);// 8
		addCarta(atlas, DataCarta.azfu2d1, DataCarta.azfu, DataCarta.dos, DataCarta.division);// 9
		addCarta(atlas, DataCarta.azfu3s1, DataCarta.azfu, DataCarta.cuatro, DataCarta.suma);// 10
		addCarta(atlas, DataCarta.azfu3s2, DataCarta.azfu, DataCarta.cinco, DataCarta.suma);// 11
		addCarta(atlas, DataCarta.azfu3s3, DataCarta.azfu, DataCarta.seis, DataCarta.suma);// 12
		addCarta(atlas, DataCarta.azfu6d6, DataCarta.azfu, DataCarta.uno, DataCarta.division);// 13
		addCarta(atlas, DataCarta.azfu9r7, DataCarta.azfu, DataCarta.dos, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color morado
	 * </pre>
	  */

	public void cargarCartasMO(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.mor0d3, DataCarta.mo, DataCarta.cero, DataCarta.division);// 1
		addCarta(atlas, DataCarta.mor11r10, DataCarta.mo, DataCarta.uno, DataCarta.resta);// 2
		addCarta(atlas, DataCarta.mor14r13, DataCarta.mo, DataCarta.uno, DataCarta.resta);// 3
		addCarta(atlas, DataCarta.mor15r10, DataCarta.mo, DataCarta.cinco, DataCarta.resta);// 4
		addCarta(atlas, DataCarta.mor15r15, DataCarta.mo, DataCarta.cero, DataCarta.resta);// 5
		addCarta(atlas, DataCarta.mor1m6, DataCarta.mo, DataCarta.seis, DataCarta.multiplicacion);// 6
		addCarta(atlas, DataCarta.mor21r15, DataCarta.mo, DataCarta.seis, DataCarta.resta);// 7
		addCarta(atlas, DataCarta.mor2m2, DataCarta.mo, DataCarta.cuatro, DataCarta.multiplicacion);// 8
		addCarta(atlas, DataCarta.mor3r1, DataCarta.mo, DataCarta.dos, DataCarta.resta);// 9
		addCarta(atlas, DataCarta.mor3s0, DataCarta.mo, DataCarta.tres, DataCarta.suma);// 10
		addCarta(atlas, DataCarta.mor4d2, DataCarta.mo, DataCarta.dos, DataCarta.division);// 11
		addCarta(atlas, DataCarta.mor4s0, DataCarta.mo, DataCarta.cuatro, DataCarta.suma);// 12
		addCarta(atlas, DataCarta.mor5m1, DataCarta.mo, DataCarta.cinco, DataCarta.multiplicacion);// 13
		addCarta(atlas, DataCarta.mor8r5, DataCarta.mo, DataCarta.tres, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color naranja
	 * </pre>
	  */

	public void cargarCartasNA(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.nar10d2, DataCarta.na, DataCarta.cinco, DataCarta.division);// 1
		addCarta(atlas, DataCarta.nar10r6, DataCarta.na, DataCarta.cuatro, DataCarta.resta);// 2
		addCarta(atlas, DataCarta.nar11r5, DataCarta.na, DataCarta.seis, DataCarta.resta);// 3
		addCarta(atlas, DataCarta.nar13r11, DataCarta.na, DataCarta.dos, DataCarta.resta);// 4
		addCarta(atlas, DataCarta.nar16d16, DataCarta.na, DataCarta.uno, DataCarta.division);// 5
		addCarta(atlas, DataCarta.nar17r17, DataCarta.na, DataCarta.cero, DataCarta.resta);// 6
		addCarta(atlas, DataCarta.nar1s1, DataCarta.na, DataCarta.dos, DataCarta.suma);// 7
		addCarta(atlas, DataCarta.nar1s2, DataCarta.na, DataCarta.tres, DataCarta.suma);// 8
		addCarta(atlas, DataCarta.nar1s4, DataCarta.na, DataCarta.cinco, DataCarta.suma);// 9
		addCarta(atlas, DataCarta.nar2m3, DataCarta.na, DataCarta.seis, DataCarta.multiplicacion);// 10
		addCarta(atlas, DataCarta.nar2r1, DataCarta.na, DataCarta.uno, DataCarta.resta);// 11
		addCarta(atlas, DataCarta.nar4r1, DataCarta.na, DataCarta.tres, DataCarta.resta);// 12
		addCarta(atlas, DataCarta.nar7r7, DataCarta.na, DataCarta.cero, DataCarta.resta);// 13
		addCarta(atlas, DataCarta.nar8d2, DataCarta.na, DataCarta.cuatro, DataCarta.division);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color rojo
	 * </pre>
	  */

	public void cargarCartasROJ(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.roj0d10, DataCarta.roj, DataCarta.cero, DataCarta.division);// 1
		addCarta(atlas, DataCarta.roj10d5, DataCarta.roj, DataCarta.dos, DataCarta.division);// 2
		addCarta(atlas, DataCarta.roj12d3, DataCarta.roj, DataCarta.cuatro, DataCarta.division);// 3
		addCarta(atlas, DataCarta.roj1m1, DataCarta.roj, DataCarta.uno, DataCarta.multiplicacion);// 4
		addCarta(atlas, DataCarta.roj1s3, DataCarta.roj, DataCarta.cuatro, DataCarta.suma);// 5
		addCarta(atlas, DataCarta.roj20d4, DataCarta.roj, DataCarta.cinco, DataCarta.division);// 6
		addCarta(atlas, DataCarta.roj20m0, DataCarta.roj, DataCarta.cero, DataCarta.multiplicacion);// 7
		addCarta(atlas, DataCarta.roj2m1, DataCarta.roj, DataCarta.dos, DataCarta.multiplicacion);// 8
		addCarta(atlas, DataCarta.roj30d5, DataCarta.roj, DataCarta.seis, DataCarta.division);// 9
		addCarta(atlas, DataCarta.roj3d1, DataCarta.roj, DataCarta.tres, DataCarta.division);// 10
		addCarta(atlas, DataCarta.roj3m1, DataCarta.roj, DataCarta.tres, DataCarta.multiplicacion);// 11
		addCarta(atlas, DataCarta.roj6d1, DataCarta.roj, DataCarta.seis, DataCarta.division);// 12
		addCarta(atlas, DataCarta.roj7r2, DataCarta.roj, DataCarta.cinco, DataCarta.resta);// 13
		addCarta(atlas, DataCarta.roj8r7, DataCarta.roj, DataCarta.uno, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color rosado
	 * </pre>
	  */

	public void cargarCartasROS(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.ros0m14, DataCarta.ros, DataCarta.cero, DataCarta.multiplicacion);// 1
		addCarta(atlas, DataCarta.ros0m2, DataCarta.ros, DataCarta.cero, DataCarta.multiplicacion);// 2
		addCarta(atlas, DataCarta.ros14r10, DataCarta.ros, DataCarta.cuatro, DataCarta.resta);// 3
		addCarta(atlas, DataCarta.ros18d6, DataCarta.ros, DataCarta.tres, DataCarta.division);// 4
		addCarta(atlas, DataCarta.ros18r12, DataCarta.ros, DataCarta.seis, DataCarta.resta);// 5
		addCarta(atlas, DataCarta.ros19r17, DataCarta.ros, DataCarta.dos, DataCarta.resta);// 6
		addCarta(atlas, DataCarta.ros20r15, DataCarta.ros, DataCarta.cinco, DataCarta.resta);// 7
		addCarta(atlas, DataCarta.ros4d1, DataCarta.ros, DataCarta.cuatro, DataCarta.division);// 8
		addCarta(atlas, DataCarta.ros4r3, DataCarta.ros, DataCarta.uno, DataCarta.resta);// 9
		addCarta(atlas, DataCarta.ros4s2, DataCarta.ros, DataCarta.seis, DataCarta.suma);// 10
		addCarta(atlas, DataCarta.ros5d1, DataCarta.ros, DataCarta.cinco, DataCarta.division);// 11
		addCarta(atlas, DataCarta.ros5r4, DataCarta.ros, DataCarta.uno, DataCarta.resta);// 12
		addCarta(atlas, DataCarta.ros7r5, DataCarta.ros, DataCarta.dos, DataCarta.resta);// 13
		addCarta(atlas, DataCarta.ros9d3, DataCarta.ros, DataCarta.tres, DataCarta.division);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color verde bajo
	 * </pre>
	  */

	public void cargarCartasVEBA(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.veba0d11, DataCarta.veba, DataCarta.cero, DataCarta.division);// 1
		addCarta(atlas, DataCarta.veba14r9, DataCarta.veba, DataCarta.cinco, DataCarta.resta);// 2
		addCarta(atlas, DataCarta.veba17r16, DataCarta.veba, DataCarta.uno, DataCarta.resta);// 3
		addCarta(atlas, DataCarta.veba1d1, DataCarta.veba, DataCarta.uno, DataCarta.division);// 4
		addCarta(atlas, DataCarta.veba1m2, DataCarta.veba, DataCarta.dos, DataCarta.multiplicacion);// 5
		addCarta(atlas, DataCarta.veba1m3, DataCarta.veba, DataCarta.tres, DataCarta.multiplicacion);// 6
		addCarta(atlas, DataCarta.veba1s4, DataCarta.veba, DataCarta.cinco, DataCarta.suma);// 7
		addCarta(atlas, DataCarta.veba24d4, DataCarta.veba, DataCarta.seis, DataCarta.division);// 8
		addCarta(atlas, DataCarta.veba2s2, DataCarta.veba, DataCarta.cuatro, DataCarta.suma);// 9
		addCarta(atlas, DataCarta.veba5r5, DataCarta.veba, DataCarta.cero, DataCarta.resta);// 10
		addCarta(atlas, DataCarta.veba7r4, DataCarta.veba, DataCarta.tres, DataCarta.resta);// 11
		addCarta(atlas, DataCarta.veba8d4, DataCarta.veba, DataCarta.dos, DataCarta.division);// 12
		addCarta(atlas, DataCarta.veba8r2, DataCarta.veba, DataCarta.seis, DataCarta.resta);// 13
		addCarta(atlas, DataCarta.veba8r4, DataCarta.veba, DataCarta.cuatro, DataCarta.resta);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color verde fuerte
	 * </pre>
	 */

	public void cargarCartasVEFU(TextureAtlas atlas) {
		addCarta(atlas, DataCarta.vefu0m13, DataCarta.vefu, DataCarta.cero, DataCarta.multiplicacion);// 1
		addCarta(atlas, DataCarta.vefu0s4, DataCarta.vefu, DataCarta.cuatro, DataCarta.suma);// 2
		addCarta(atlas, DataCarta.vefu10r5, DataCarta.vefu, DataCarta.cinco, DataCarta.resta);// 3
		addCarta(atlas, DataCarta.vefu16d4, DataCarta.vefu, DataCarta.cuatro, DataCarta.division);// 4
		addCarta(atlas, DataCarta.vefu16r13, DataCarta.vefu, DataCarta.tres, DataCarta.resta);// 5
		addCarta(atlas, DataCarta.vefu1s0, DataCarta.vefu, DataCarta.uno, DataCarta.suma);// 6
		addCarta(atlas, DataCarta.vefu23r17, DataCarta.vefu, DataCarta.seis, DataCarta.resta);// 7
		addCarta(atlas, DataCarta.vefu2s0, DataCarta.vefu, DataCarta.dos, DataCarta.suma);// 8
		addCarta(atlas, DataCarta.vefu30d6, DataCarta.vefu, DataCarta.cinco, DataCarta.division);// 9
		addCarta(atlas, DataCarta.vefu5s1, DataCarta.vefu, DataCarta.seis, DataCarta.suma);// 10
		addCarta(atlas, DataCarta.vefu6d2, DataCarta.vefu, DataCarta.tres, DataCarta.division);// 11
		addCarta(atlas, DataCarta.vefu6d3, DataCarta.vefu, DataCarta.dos, DataCarta.division);// 12
		addCarta(atlas, DataCarta.vefu9d9, DataCarta.vefu, DataCarta.uno, DataCarta.division);// 13
		addCarta(atlas, DataCarta.vefu9m0, DataCarta.vefu, DataCarta.cero, DataCarta.multiplicacion);// 14
	}

	/**
	 * @param TextureAtlas
	 *            atlas
	 * 
	 *            <pre>
	 * Carga las Cartas de color negro (comodines)
	 * </pre>
	 */

	public void cargarCartasCOMODIN(TextureAtlas atlas) {
		// amba
//		addCarta(atlas, DataCarta.ambas, DataCarta.amba, DataCarta.cBloq, DataCarta.sinOperacion);// 1
//		addCarta(atlas, DataCarta.ambar, DataCarta.amba, DataCarta.cReg, DataCarta.sinOperacion);// 2
//		addCarta(atlas, DataCarta.ambamasuno, DataCarta.amba, DataCarta.cMas1, DataCarta.sinOperacion);// 3
//		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// amfu
		addCarta(atlas, DataCarta.amfus, DataCarta.amfu, DataCarta.cBloq, DataCarta.sinOperacion);// 1
		addCarta(atlas, DataCarta.amfur, DataCarta.amfu, DataCarta.cReg, DataCarta.sinOperacion);// 2
		addCarta(atlas, DataCarta.amfumasuno, DataCarta.amfu, DataCarta.cMas1, DataCarta.sinOperacion);// 3
		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// azba
//		addCarta(atlas, DataCarta.azbas, DataCarta.azba, DataCarta.cBloq, DataCarta.sinOperacion);// 1
//		addCarta(atlas, DataCarta.azbar, DataCarta.azba, DataCarta.cReg, DataCarta.sinOperacion);// 2
//		addCarta(atlas, DataCarta.azbamasuno, DataCarta.azba, DataCarta.cMas1, DataCarta.sinOperacion);// 3
//		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// azfu
		addCarta(atlas, DataCarta.azfus, DataCarta.azfu, DataCarta.cBloq, DataCarta.sinOperacion);// 1
		addCarta(atlas, DataCarta.azfur, DataCarta.azfu, DataCarta.cReg, DataCarta.sinOperacion);// 2
		addCarta(atlas, DataCarta.azfumasuno, DataCarta.azfu, DataCarta.cMas1, DataCarta.sinOperacion);// 3
		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// mor
		addCarta(atlas, DataCarta.mors, DataCarta.mo, DataCarta.cBloq, DataCarta.sinOperacion);// 1
		addCarta(atlas, DataCarta.morr, DataCarta.mo, DataCarta.cReg, DataCarta.sinOperacion);// 2
		addCarta(atlas, DataCarta.mormasuno, DataCarta.mo, DataCarta.cMas1, DataCarta.sinOperacion);// 3
		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// nar
//		addCarta(atlas, DataCarta.nars, DataCarta.na, DataCarta.cBloq, DataCarta.sinOperacion);// 1
//		addCarta(atlas, DataCarta.narr, DataCarta.na, DataCarta.cReg, DataCarta.sinOperacion);// 2
//		addCarta(atlas, DataCarta.narmasuno, DataCarta.na, DataCarta.cMas1, DataCarta.sinOperacion);// 3
//		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// roj
		addCarta(atlas, DataCarta.rojs, DataCarta.roj, DataCarta.cBloq, DataCarta.sinOperacion);// 1
		addCarta(atlas, DataCarta.rojr, DataCarta.roj, DataCarta.cReg, DataCarta.sinOperacion);// 2
		addCarta(atlas, DataCarta.rojmasuno, DataCarta.roj, DataCarta.cMas1, DataCarta.sinOperacion);// 3
		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// ros
//		addCarta(atlas, DataCarta.ross, DataCarta.ros, DataCarta.cBloq, DataCarta.sinOperacion);// 1
//		addCarta(atlas, DataCarta.rosr, DataCarta.ros, DataCarta.cReg, DataCarta.sinOperacion);// 2
//		addCarta(atlas, DataCarta.rosmasuno, DataCarta.ros, DataCarta.cMas1, DataCarta.sinOperacion);// 3
//		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// veba
//		addCarta(atlas, DataCarta.vebas, DataCarta.veba, DataCarta.cBloq, DataCarta.sinOperacion);// 1
//		addCarta(atlas, DataCarta.vebar, DataCarta.veba, DataCarta.cReg, DataCarta.sinOperacion);// 2
//		addCarta(atlas, DataCarta.vebamasuno, DataCarta.veba, DataCarta.cMas1, DataCarta.sinOperacion);// 3
//		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4
		// vefu
		addCarta(atlas, DataCarta.vefus, DataCarta.vefu, DataCarta.cBloq, DataCarta.sinOperacion);// 1
		addCarta(atlas, DataCarta.vefur, DataCarta.vefu, DataCarta.cReg, DataCarta.sinOperacion);// 2
		addCarta(atlas, DataCarta.vefumasuno, DataCarta.vefu, DataCarta.cMas1, DataCarta.sinOperacion);// 3
		addCarta(atlas, DataCarta.masdos, DataCarta.neg, DataCarta.cMas2, DataCarta.sinOperacion);// 4

		cargarCartasBase();
	}

	public Carta[] getCartasBase() {
		return eligeColor;
	}
}
