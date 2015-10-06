package com.videojuegos.jugador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;
import com.videojuegos.cartas.Carta;
import com.videojuegos.screen.ScreenJuego;

import java.util.ArrayList;

public class Player {

    private String nombre, correo;
    private int index, id, numEle;
    private float[] posX, posY;
    private float antY, sigY, antX, sigX, antG, sigG, mazoX, mazoY, mazoG;
    private ArrayList<Carta> mazoPlayer;
    private Boton btnPlayerAnt, btnPlayerSig;
    private int puntuacion = 0;


    public Player(int id) {
        this.nombre = "";
        this.id = id;
        inicializa();
    }

    public Player(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        inicializa();
    }

    public Player(int id, String correo) {
        this.id = id;
        this.correo = correo;
        inicializa();
    }

    private void inicializa() {
        this.index = 0;
        mazoPlayer = new ArrayList<Carta>();
        // Inicializamos jugador
        down(id);
        up(id);
        left(id);
        right(id);
        // Inicializamos los botones de siguiente y anterior
        this.btnPlayerAnt = new Boton(Load.btncambiar, antX, antY,
                AtsPos.anchoBtnCambio, AtsPos.altoBtnCambio, antG);
        this.btnPlayerSig = new Boton(Load.btncambiar, sigX, sigY,
                AtsPos.anchoBtnCambio, AtsPos.altoBtnCambio, sigG);

        Turno.setPlayer(this);
        // Cargamos cinco cartas aleatorias
        for (int i = 0; i < 5; i++) {
            Turno.addCarta();
        }
        // Posicionamos las cartas
        setPositionCartas();
    }

    private void down(int id) {
        if (id == 1) {
            posX = AtsPos.downX;
            numEle = posX.length;
            antY = AtsPos.downY;
            sigY = AtsPos.downY;
            antX = AtsPos.downBtnAntX;
            sigX = AtsPos.downBtnSigX;
            antG = 0;
            sigG = 180;
            mazoX = AtsPos.mazoP1X;
            mazoY = AtsPos.mazoP1Y;
            mazoG = 0;
        }
    }

    private void up(int id) {
        if (id == 2) {
            posX = AtsPos.upX;
            numEle = posX.length;
            antY = AtsPos.upY;
            sigY = AtsPos.upY;
            antX = AtsPos.upBtnAntX;
            sigX = AtsPos.upBtnSigX;
            antG = 180;
            sigG = 0;
            mazoX = AtsPos.mazoP2X;
            mazoY = AtsPos.mazoP2Y;
            mazoG = 180;
        }
    }

    private void left(int id) {
        if (id == 3) {
            posY = AtsPos.leftY;
            numEle = posY.length;
            antY = AtsPos.leftBtnAntY;
            sigY = AtsPos.leftBtnSigY;
            antX = AtsPos.leftX;
            sigX = AtsPos.leftX;
            antG = -90;
            sigG = 90;
            mazoX = AtsPos.mazoP3X;
            mazoY = AtsPos.mazoP3Y;
            mazoG = -90;
        }
    }

    private void right(int id) {
        if (id == 4) {
            posY = AtsPos.rightY;
            numEle = posY.length;
            antY = AtsPos.rightBtnAntY;
            sigY = AtsPos.rightBtnSigY;
            antX = AtsPos.rightX;
            sigX = AtsPos.rightX;
            antG = 90;
            sigG = -90;
            mazoX = AtsPos.mazoP4X;
            mazoY = AtsPos.mazoP4Y;
            mazoG = 90;
        }
    }

    /**
     * Re-ordena las cartas del jugador a su posicion inicial
     */
    public void setPositionCartas() {
        if (getId() < 3) {
            for (int i = 0; i < getNumEle(); i++) {
                if ((getIndex() + i) < getMazoPlayer().size()) {
                    getMazoPlayer().get(getIndex() + i).setPosicion(
                            getPosX()[i], getAntY());
                }
            }
        } else {
            for (int i = 0; i < getNumEle(); i++) {
                if ((getIndex() + i) < getMazoPlayer().size()) {
                    getMazoPlayer().get(getIndex() + i).setPosicion(getAntX(),
                            getPosY()[i]);
                }
            }
        }

    }

    /**
     * <pre>
     * Muestra la siguente carta del mazo del jugador,
     * retorna verdadero si el jugador tiene mas de cuatro
     * cartas, si es eljugador 1 o 2 y tres si es el jugador 3 o 4.
     * En caso contrario retorna false
     * </pre>
     *
     * @return boolean sig
     */

    private boolean sig() {
        if ((index + numEle) < mazoPlayer.size()) {
            if (btnPlayerSig.meTocaste()) {
                index++;
                setPositionCartas();
            }
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Muestra la carta anterior del mazo del jugador,
     * retorna verdadero si el jugador tiene mas de cuatro
     * cartas, si es eljugador 1 o 2 y tres si es el jugador 3 o 4.
     * En caso contrario retorna false
     * </pre>
     *
     * @return boolean ant
     */

    private boolean ant() {
        if ((index - 1) >= 0) {
            if (btnPlayerAnt.meTocaste()) {
                index--;
                setPositionCartas();
            }
            return true;
        }
        return false;
    }

    public void turno(SpriteBatch spriteBatch) {
        if (Juego.turno == this.id) {
            Turno.setPlayer(this);
            Juego.btnMazo.setPosicion(mazoX, mazoY, mazoG);
            Juego.btnMazo.dibujar(spriteBatch);
            if ((AtsUtil.machine) && (id != 2)) {
                Turno.tomarMazo();
            } else if (!AtsUtil.machine) {
                Turno.tomarMazo();
            }
            Turno.turno();
        }
    }

    private void dibujarDemasJugadores(SpriteBatch spriteBatch) {
        if (id < 3) {
            for (int i = 0; i < numEle; i++) {
                AtsTM.setJugadorCarta(Load.logo, id);
                AtsTM.setPosicion(Load.logo, posX[i], antY, AtsPos.anchoCarta,
                        AtsPos.altoCarta);
                Load.logo.draw(spriteBatch);
            }
        } else {
            for (int i = 0; i < numEle; i++) {
                AtsTM.setJugadorCarta(Load.logo, id);
                AtsTM.setPosicion(Load.logo, antX, posY[i], AtsPos.anchoCarta,
                        AtsPos.altoCarta);
                Load.logo.draw(spriteBatch);
            }
        }
    }

    private void dibujarJugador(SpriteBatch spriteBatch) {
        if (ant())
            this.btnPlayerAnt.dibujar(spriteBatch);
        if (sig())
            this.btnPlayerSig.dibujar(spriteBatch);
        for (int i = 0; i < numEle; i++) {
            int j = index + i;
            if (j < mazoPlayer.size())
                mazoPlayer.get(j).dibujar(spriteBatch);
        }
    }

    private void dibujarMaquina(SpriteBatch spriteBatch) {
        if (Bluetooth.machine()) {
            if (Bluetooth.machineTurno(this)) {
                for (int i = 0; i < numEle; i++) {
                    AtsTM.setJugadorCarta(Load.logo, id);
                    AtsTM.setPosicion(Load.logo, posX[i], antY,
                            AtsPos.anchoCarta, AtsPos.altoCarta);
                    Load.logo.draw(spriteBatch);
                    if (Turno.cartaMachine != null) {
                        if (Juego.turno == 2)
                            Turno.cartaMachine.dibujar(spriteBatch);
                    }
                }
            } else {
                dibujarJugador(spriteBatch);
            }
        }
    }

    public void dibujarPlayer(SpriteBatch spriteBatch) {
        if (Bluetooth.mismoDispositivo() || Bluetooth.machine()) {
            Juego.idMachine = Juego.turno;
        }
        dibujarMaquina(spriteBatch);
        if ((Bluetooth.bluetooth())) {
            if ((this.id == Juego.idMachine)) {
                dibujarJugador(spriteBatch);
            } else {
                dibujarDemasJugadores(spriteBatch);
            }
        } else if (Bluetooth.mismoDispositivo()) {
            if ((this.id == Juego.turno))
                dibujarJugador(spriteBatch);
            else
                dibujarDemasJugadores(spriteBatch);
        }
    }

    public ArrayList<Carta> getMazoPlayer() {
        return mazoPlayer;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIndex() {
        return index;
    }

    public int getNumEle() {
        return numEle;
    }

    public float[] getPosX() {
        return posX;
    }

    public float[] getPosY() {
        return posY;
    }

    public float getAntY() {
        return antY;
    }

    public float getAntX() {
        return antX;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    /**
     * Método que suma 1 punto al jugador, se llama cuando una carta de color del mazo
     * la une con otra de color en el centro.
     */
    public void sumarPuntosPorColor() {
        System.out.println("+1 punto");
        this.puntuacion++;

        publicarMarcador();
    }


    /**
     * Método que suma 2 puntos al jugador, se llama cuando una carta de operación del mazo
     * la une con otra del mismo tipo de operación en el centro.
     * <p>
     * Ejemplo: De su mazo elije 2+2 y en el centro está la carta 3+1
     */
    public void sumarPuntosPorOperacionSimple() {
        System.out.println("+2 puntos");
        this.puntuacion += 2;

        publicarMarcador();
    }

    /**
     * Método que suma 3 puntos al jugador, se llama cuando una carta de operación del mazo
     * la une con otra de diferente tipo de operación en el centro.
     * <p>
     * Ejemplo: De su mazo elije 2+2 y en el centro está la carta 8/2
     */
    public void sumarPuntosPorOperacionDoble() {
        System.out.println("+3 puntos");
        this.puntuacion += 3;

        publicarMarcador();

    }

    public int obtenerPuntuacion() {
        return this.puntuacion;
    }

    /**
     * Metodo que se encarga de pintar el marcador en la pantalla.
     */

    public void publicarMarcador() {
        //ScreenJuego.crearMarcadorSiNoExiste();

        if (this.getCorreo().equalsIgnoreCase("Maquina") || this.getCorreo().equalsIgnoreCase("Player 2"))
            ScreenJuego.actualizarMarcador(ScreenJuego.marcadorJugador2, this.obtenerPuntuacion());
        else if (this.getCorreo().equalsIgnoreCase("Player 1"))
            ScreenJuego.actualizarMarcador(ScreenJuego.marcadorJugador1, this.obtenerPuntuacion());
        else if (this.getCorreo().equalsIgnoreCase("Player 3"))
            ScreenJuego.actualizarMarcador(ScreenJuego.marcadorJugador3, this.obtenerPuntuacion());
        else
            ScreenJuego.actualizarMarcador(ScreenJuego.marcadorJugador4, this.obtenerPuntuacion());

    }
}
