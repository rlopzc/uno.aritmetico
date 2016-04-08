package com.videojuegos.jugador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsGuardar;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.DataCarta;
import com.videojuegos.asset.Load;
import com.videojuegos.basedatos.DBTurnoHandler;
import com.videojuegos.cartas.Boton;
import com.videojuegos.cartas.Carta;
import com.videojuegos.screen.ScreenGanaste;
import com.videojuegos.screen.ScreenJuego;
import com.videojuegos.unoarit.MainP;

import java.util.ArrayList;

public class Juego {

    public static ArrayList<Carta> centroCartaMazo;
    private ArrayList<Player> player;
    public static Carta centroCarta;
    public static Boton btnMazo;
    public static boolean regresar, bloquear, masuno, masdos, terminoTurno,
            terminoJuego;
    public static int turno, idPlayer, numJug, idMachine, idPartidaDB, turnoDB;
    public static String colorDB, valorDB;
    private static DBTurnoHandler db = new DBTurnoHandler(MainP.getContext());

    public Juego(int numJug) {
        Juego.numJug = numJug;
        Juego.turno = 1;
        Juego.turnoDB = 0;
        Juego.colorDB = "";
        Juego.valorDB = "";
        Juego.idPartidaDB = db.asignarUltimoIdPartida();

        player = new ArrayList<Player>(numJug);
        centroCartaMazo = new ArrayList<Carta>();
        // Inicializamos el boton mazo
        btnMazo = new Boton(Load.mazoS, 7.5f, 5.0f, AtsPos.anchoCarta,
                AtsPos.altoCarta, 0);

        terminoJuego = false;
        terminoTurno = false;
        bloquear = false;
        regresar = false;
        masuno = false;
        masdos = false;

        Carta c = AtsTM.getCartaAleatoria(0, Bluetooth.bluetooth());
        addMazo(c);

        if (AtsUtil.machine) {
            player.add(new Player(1, "Player 1"));
            player.add(new Player(2, "Maquina"));
        } else {
            for (int i = 0; i < numJug; i++) {
                player.add(new Player(i + 1, "Player " + (i + 1)));
            }
        }


    }

    public Juego(int numJug, ArrayList<String> jugadores) {
        Juego.numJug = numJug;
        Juego.turno = 1;
        Juego.turnoDB = 0;
        Juego.colorDB = "";
        Juego.valorDB = "";
        Juego.idPartidaDB = db.asignarUltimoIdPartida();

        player = new ArrayList<>(numJug);
        centroCartaMazo = new ArrayList<>();
        // Inicializamos el boton mazo
        btnMazo = new Boton(Load.mazoS, 7.5f, 5.0f, AtsPos.anchoCarta, AtsPos.altoCarta, 0);

        terminoJuego = false;
        terminoTurno = false;
        bloquear = false;
        regresar = false;
        masuno = false;
        masdos = false;

        Carta c = AtsTM.getCartaAleatoria(0, Bluetooth.bluetooth());
        addMazo(c);

        if (AtsUtil.machine) {
            player.add(new Player(1, jugadores.get(0)));
            player.add(new Player(2, "Maquina"));
        } else {
            for (int i = 0; i < numJug; i++) {
                player.add(new Player(i + 1, jugadores.get(i)));
            }
        }
    }

    public static void addMazo(Carta c) {
        try {
            c.setJugador(0);
            c.setPosicion(AtsPos.centroX, AtsPos.centroY);
            centroCartaMazo.add(c);
            // System.out.println("A�adido: " + c.getColor() + "\t" +
            // c.getValor());
            centroCarta = centroCartaMazo.get(centroCartaMazo.size() - 1);
        } catch (Exception e) {
            AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
        }
    }

    public static void removeMazo(Carta c) {
        try {
            if (c.getValor() == DataCarta.cBase) {
                centroCartaMazo.remove(c);
                // System.out.println("Removido: " + c.getColor() + "\t" +
                // c.getValor());
                centroCarta = centroCartaMazo.get(centroCartaMazo.size() - 1);
            }
        } catch (Exception e) {
            AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
        }
    }

    public void dibujarJuego(SpriteBatch spriteBatch) {
        ((ScreenJuego) AtsScreens.screenJuego).setPlayers(player);
        AtsSound.sonarMusic(AtsSound.juego);
        if (terminoJuego) {
            AtsUtil.game.setScreen(new ScreenGanaste(idPlayer));
            Load.mazo.rellenarMazo(player);
            return;
        }
        centroCarta.dibujar(spriteBatch);
        for (int i = 0; i < numJug; i++) {
            player.get(i).dibujarPlayer(spriteBatch);
            player.get(i).turno(spriteBatch);
        }

    }

    public void onPause() {
        AtsGuardar.Guardar(this);
    }

}
