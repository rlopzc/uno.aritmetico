package com.videojuegos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;
import com.videojuegos.input.InputScreenJuego;
import com.videojuegos.jugador.Bluetooth;
import com.videojuegos.jugador.Juego;
import com.videojuegos.jugador.Player;
import com.videojuegos.utils.BluetoothSingleton;

import java.util.ArrayList;

/**
 * �sta clase pinta la pantalla del juego y se le indica cuandos jugadores
 * vana jugar en caso de que sea en el modo multijugador
 */

public class ScreenJuego implements Screen {

    private Juego juego;
    private SpriteBatch batch;
    private Boton btnAtras;
    private ArrayList<Player> player;

    public static InputScreenJuego marcadorJugador1, marcadorJugador2, marcadorJugador3, marcadorJugador4;

    public ScreenJuego(int numJug) {
        try {
            juego = new Juego(numJug);
            crearMarcadorSiNoExiste(numJug);
            iniciarMarcador('0');
        } catch (Exception e) {
            AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
        }
    }

    public ScreenJuego(int numJug, ArrayList<String> jugadores) {
        try {
            juego = new Juego(numJug, jugadores);
            crearMarcadorSiNoExiste(numJug);
            iniciarMarcador('0');
        } catch (Exception e) {
            AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
        }
    }

    public void setPlayers(ArrayList<Player> player) {
        this.player = player;
    }

    /**
     * En �ste m�todo se establecen variables que identifican si
     * Si se va a jugar en el mismo dispositivo (multijugador) o
     * contra el dispositivo (modo jugador solo)
     */


    @Override
    public void render(float delta) {
        AtsUtil.limpiarP();
        batch.disableBlending();
        batch.begin();
        batch.draw(AtsUtil.getBackground(), 0, 0, 15, 10);
        batch.end();

        batch.enableBlending();
        batch.begin();

        if ((!AtsUtil.mismoDispositivo) && (!AtsUtil.machine)) {
            if (BluetoothSingleton.getInstance().bluetoothManager.CerrarPartida()) {
                BluetoothSingleton.getInstance().bluetoothManager.stop();
                AtsUtil.game.setScreen(AtsScreens.screenMain);
            }
            if (BluetoothSingleton.getInstance().bluetoothManager.isConnected())
                batch.draw(Load.good, 1.0f, 1.0f, 1.0f, 1.0f);
            else
                AtsUtil.game.setScreen(AtsScreens.screenMain);
        }

        try {
            juego.dibujarJuego(batch);
        } catch (Exception ignored) {
        }

        btnAtras.dibujar(batch);
        batch.end();

        if (btnAtras.meTocaste() && Bluetooth.machine()) {
            Load.mazo.rellenarMazo(player);
            AtsUtil.game.setScreen(AtsScreens.screenMain);
            destruirMarcadores();
        } else if (btnAtras.meTocaste() && Bluetooth.mismoDispositivo()) {
            Load.mazo.rellenarMazo(player);
            AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
            destruirMarcadores();
        } else if (btnAtras.meTocaste() && !AtsUtil.mismoDispositivo && !AtsUtil.machine && Juego.idMachine == 1) {
            Load.mazo.rellenarMazo(player);
            BluetoothSingleton.getInstance().bluetoothManager
                    .sendMessage2("Cerrar");
            BluetoothSingleton.getInstance().bluetoothManager.stop();
            AtsUtil.game.setScreen(AtsScreens.screenMain);

            destruirMarcadores();
        }

        if (marcadorJugador1 != null) marcadorJugador1.Actualizar();
        if (marcadorJugador2 != null) marcadorJugador2.Actualizar();
        if (marcadorJugador3 != null) marcadorJugador3.Actualizar();
        if (marcadorJugador4 != null) marcadorJugador4.Actualizar();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        //crearMarcadorSiNoExiste();
        dibujar_boton_en_pantalla();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        AtsSound.pause();
    }

    @Override
    public void resume() {
        //crearMarcadorSiNoExiste();

        dibujar_boton_en_pantalla();
    }

    private void dibujar_boton_en_pantalla() {
        batch = AtsUtil.batch;
        if (!AtsUtil.mismoDispositivo && !AtsUtil.machine) {
            btnAtras = new Boton(Load.btnatras, 14.2f, 0.5f, 1.0f, 0.7f, 0);
        }
        if (AtsUtil.machine) {
            btnAtras = new Boton(Load.btnatras, 14.2f, 0.5f, 1.0f, 0.7f, 0);
        }
        if (AtsUtil.mismoDispositivo) {
            if (Juego.turno == 1) {
                btnAtras = new Boton(Load.btnatras, 14.3f, 0.5f, 1.0f, 0.7f, 0);
            }
            if (Juego.turno == 2) {
                btnAtras = new Boton(Load.btnatras, 0.7f, 9.5f, 1.0f, 0.7f, 180);
            }
            if (Juego.turno == 3) {
                btnAtras = new Boton(Load.btnatras, 0.7f, 0.5f, 1.0f, 0.7f, -90);
            }
            if (Juego.turno == 4) {
                btnAtras = new Boton(Load.btnatras, 14.2f, 9.4f, 1.0f, 0.7f, 90);
            }
        }


    }

    public static void crearMarcadorSiNoExiste(int numMarcadoresNecesarios) {
        if (marcadorJugador1 == null) {
            marcadorJugador1 = new InputScreenJuego(AtsUtil.batch, 7.5f, 3f);
            Gdx.input.setInputProcessor(marcadorJugador1);
        }

        if (marcadorJugador2 == null) {
            marcadorJugador2 = new InputScreenJuego(AtsUtil.batch, 7.6f, 7.3f);
            Gdx.input.setInputProcessor(marcadorJugador2);
            marcadorJugador2.setRotarPosicion(true);
            marcadorJugador2.setGradosRotacion(180);
        }


        if (numMarcadoresNecesarios > 2) {//jugaran 3
            if (marcadorJugador3 == null) {
                marcadorJugador3 = new InputScreenJuego(AtsUtil.batch, 5f, 5.0f);
                Gdx.input.setInputProcessor(marcadorJugador3);
                marcadorJugador3.setRotarPosicion(true);
                marcadorJugador3.setGradosRotacion(270);
            }

            if (numMarcadoresNecesarios > 3) {//jugaran 4.
                if (marcadorJugador4 == null) {
                    marcadorJugador4 = new InputScreenJuego(AtsUtil.batch, 9.5f, 5.0f);
                    Gdx.input.setInputProcessor(marcadorJugador4);
                    marcadorJugador4.setRotarPosicion(true);
                    marcadorJugador4.setGradosRotacion(90);
                }

            }
        }
    }

    public static void destruirMarcadores() {
        marcadorJugador1 = null;
        marcadorJugador2 = null;
        marcadorJugador3 = null;
        marcadorJugador4 = null;
    }

    public static void iniciarMarcador(char puntuacion) {
        dibujarMarcadores(marcadorJugador1, puntuacion);
        dibujarMarcadores(marcadorJugador2, puntuacion);
        dibujarMarcadores(marcadorJugador3, puntuacion);
        dibujarMarcadores(marcadorJugador4, puntuacion);
    }

    private static void dibujarMarcadores(InputScreenJuego marcadorJugador, char puntuacion) {
        if (marcadorJugador != null) {
            marcadorJugador.limpiarTexto();
            marcadorJugador.llenar_texto(puntuacion);
        }
    }

    private static final int NUM_DIGITOS = 9;
    private static final int PRIMER_DIGITO = 0;
    private static final int SEGUNDO_DIGITO = 1;

    /**
     * @param marcadorJugador Es el marcador actual del jugador.
     * @param puntuacion      es la nueva puntuacion. (Entero).
     *                        Metodo que se encarga de refrescar en pantalla, los marcadores actuales de los jugadores.
     *                        Se le pasa el marcador del jugador actual, junto con la puntuacion. Si el numero de digitos del numero
     *                        no esta en el rango 0 - 9, entonces lo que hace es convertir el entero en String y obtener, tanto el primer caracter
     *                        como el segundo caracter de la cadena de String.
     */
    public static void actualizarMarcador(InputScreenJuego marcadorJugador, int puntuacion) {
        marcadorJugador.limpiarTexto();

        if (puntuacion <= NUM_DIGITOS) {
            // marcadorJugador.llenar_texto((char) ('0' + puntuacion));
            marcadorJugador.llenar_texto(Integer.toString(puntuacion).charAt(PRIMER_DIGITO));
        } else {
            if (marcadorJugador.isRotarPosicion() && marcadorJugador.getGradosRotacion() != 90) {//Que esté rotado 90 grados, significa que es el marcador del jugador 4.

                marcadorJugador.llenar_texto(Integer.toString(puntuacion).charAt(SEGUNDO_DIGITO));//Primer digito.
                marcadorJugador.llenar_texto(Integer.toString(puntuacion).charAt(PRIMER_DIGITO));//Segundo digito

            } else {

                marcadorJugador.llenar_texto(Integer.toString(puntuacion).charAt(PRIMER_DIGITO));//Primer digito
                marcadorJugador.llenar_texto(Integer.toString(puntuacion).charAt(SEGUNDO_DIGITO));//Segundo digito.

            }
        }
    }//fin de actualizarMarcador.


    @Override
    public void dispose() {

        Load.atlas.dispose();
        AtsUtil.batch.dispose();
        AtsSound.dispose();
    }
}
