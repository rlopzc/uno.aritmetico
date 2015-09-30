package com.videojuegos.jugador;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector3;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.DataCarta;
import com.videojuegos.asset.Load;
import com.videojuegos.basedatos.DBTurno;
import com.videojuegos.basedatos.DBTurnoHandler;
import com.videojuegos.cartas.Carta;
import com.videojuegos.screen.ScreenEligeColor;
import com.videojuegos.screen.ScreenSiguientePlayer;
import com.videojuegos.unoarit.MainP;
import com.videojuegos.utils.BluetoothSingleton;

public class Turno {
    private static Player p;
    private static float cartaMachineY = 8.4f;
    private static DBTurnoHandler db = new DBTurnoHandler(MainP.getContext());

    /**
     * Es la carta de la maquina
     */
    public static Carta cartaMachine = null;

    /**
     * Asigna el Player p al turno actual y si el turno actual es el de el
     * Player p entonces le permite realizar su movimiento
     */

    public static void setPlayer(Player p) {
        Turno.p = p;
    }

    private static ArrayList<Carta> machineCartaColor() {
        ArrayList<Carta> cartasColor = new ArrayList<Carta>();
        Carta cc = Juego.centroCarta;

        for (int i = 0; i < p.getMazoPlayer().size(); i++) {
            Carta c = p.getMazoPlayer().get(i);
            if ((cc.getColor() == c.getColor())
                    || (cc.getColorComodin() == c.getColor())
                    || (c.getColor() == DataCarta.neg)) {
                cartasColor.add(c);
            }
        }
        return cartasColor;
    }

    private static boolean machineTieneColor(int i) {

        for (int j = 0; j < p.getMazoPlayer().size(); j++) {
            if (Load.cartasBase[i].getColor() == p.getMazoPlayer().get(j)
                    .getColor()) {
                return true;
            }
        }

        return false;
    }

    private static void machineeligecolor(Carta c) {
        Random r = new Random();
        int i = r.nextInt(Load.cartasBase.length - 1);
        while (!machineTieneColor(i)) {
            i = r.nextInt(Load.cartasBase.length - 1);
        }

        Juego.addMazo(Load.cartasBase[i]);
        c.setColorComodin(Load.cartasBase[i].getColor());
    }

    private static ArrayList<Carta> machineCartaValor() {
        ArrayList<Carta> cartasValor = new ArrayList<Carta>();
        Carta cc = Juego.centroCarta;

        for (int i = 0; i < p.getMazoPlayer().size(); i++) {
            Carta c = p.getMazoPlayer().get(i);
            if (cc.getValor() == c.getValor()) {
                cartasValor.add(c);
            }
        }
        return cartasValor;
    }

    private static boolean acepta(Carta c) {
        Carta cc = Juego.centroCarta;
        if (c != null) {

            if ((cc.getOperacion() != "sinOperacion" && c.getOperacion() != "sinOperacion")) {
                if ((cc.getColor() == c.getColor())) {
                    if (cc.getValor() == c.getValor()) {
                        if (c.getOperacion().equals(cc.getOperacion())) {
                            p.sumarPuntosPorOperacionSimple();
                        } else {
                            p.sumarPuntosPorOperacionDoble();
                        }
                        save("si", cc.getOperacion(), c.getOperacion(), Integer.toString(c.getValor()));
                        return true;
                    } else {
                        p.sumarPuntosPorColor();
                        save("si", "", "", "");
                        return true;
                    }
                } else if (cc.getValor() == c.getValor()) {
                    if (c.getOperacion().equals(cc.getOperacion())) {
                        p.sumarPuntosPorOperacionSimple();
                    } else {
                        p.sumarPuntosPorOperacionDoble();
                    }
                    save("no", cc.getOperacion(), c.getOperacion(), Integer.toString(c.getValor()));
                    return true;
                }
            } else if (cc.getValor() == c.getValor()) {
                return true;
            } else if ((c.getColor() == DataCarta.neg) || (cc.getColorComodin() == c.getColor())) {
                return true;
            }

            if (p.getCorreo() == "Maquina") {
                return true;
            }

        }
        return false;
    }

    /**
     * Metodo para almacenar los turnos de cada jugador
     */

    public static void save(String color, String operacionMazo, String operacionJugada, String valor) {
        Juego.turnoDB++;
        //Se agregan los datos a la tabla y se imprime el movimiento
        db.agregarTurno(new DBTurno(Juego.idPartidaDB, Juego.turnoDB, p.getCorreo(),
                Juego.colorDB, operacionMazo, operacionJugada, valor));

        String partida = "Número de Partida: " + Juego.idPartidaDB
                + ", Turno: " + Juego.turnoDB + ", Jugador: " + p.getCorreo()
                + ", Color: " + color
                + ", Operacion Mazo: " + operacionMazo
                + ", Operacion Jugada: " + operacionJugada
                + ", Valor: " + valor;
        System.out.println("Partida: " + partida);
    }


    private static Carta machine() {
        ArrayList<Carta> color = machineCartaColor();
        ArrayList<Carta> valor = machineCartaValor();

        if ((color.size() == 0) && (valor.size() == 0)) {
            cartaMachine = addCarta();
            while (!acepta(cartaMachine)) {
                cartaMachine = addCarta();
            }
            cartaMachine.setPosicion(AtsPos.centroX, cartaMachineY);
            return cartaMachine;
        } else if ((color.size() < valor.size())) {
            cartaMachine = valor.get(0);
            cartaMachine.setPosicion(AtsPos.centroX, cartaMachineY);
            return cartaMachine;
        } else if ((color.size() > valor.size())) {
            cartaMachine = color.get(0);
            cartaMachine.setPosicion(AtsPos.centroX, cartaMachineY);
            return cartaMachine;
        } else {
            cartaMachine = valor.get(0);
            cartaMachine.setPosicion(AtsPos.centroX, cartaMachineY);
            return cartaMachine;
        }
    }

    /**
     * Rota la carta del centro, y la pone en direccion, al jugador en turno,
     * para su facil interpretacion a la hora de realizar el calculo de la
     * operacion que esta contiene en el centro.
     */

    private static void rotarCentro() {
        if (Juego.turno == 1)
            Juego.centroCarta.rotarSprite(0);
        if (Juego.turno == 2)
            Juego.centroCarta.rotarSprite(180);
        if (Juego.turno == 3)
            Juego.centroCarta.rotarSprite(-90);
        if (Juego.turno == 4)
            Juego.centroCarta.rotarSprite(90);
    }

    /**
     * Se encarga de asignar el turno al siguiente jugador, una vez que el turno
     * del jugador actual finaliza.
     */

    private static int sigJugador() {
        if (Juego.regresar) {
            if ((Juego.turno - 1) < 1) { //1 = primer jugador
                return Juego.turno = Juego.numJug;
            } else {
                return Juego.turno--;
            }
        } else {
            if ((Juego.turno + 1) > Juego.numJug) {
                return Juego.turno = 1;
            } else {
                return Juego.turno++;
            }
        }
    }

    /**
     * A�ade una carta al mazo del jugador en turno
     */

    public static Carta addCarta() {
        try {
            Carta c = AtsTM.getCartaAleatoria(1, Bluetooth.bluetooth());
            if (c != null) {
                c.setJugador(p.getId());
            }
            AtsSound.sonarSound(AtsSound.correcto);
            p.getMazoPlayer().add(p.getIndex(), c);
            return c;
        } catch (Exception e) {
            // System.out.println(e.toString());
        }
        return null;
    }

    public static Carta cartaPorID(int id) {
        for (int i = 0; i < p.getMazoPlayer().size(); i++) {
            if (p.getMazoPlayer().get(i).getId() == id) {
                return p.getMazoPlayer().get(i);
            }
        }
        return null;
    }

    public static void moverCartaID(Carta c, Vector3 v) {
        c.setPosicion(v.x, v.y);
    }

    /**
     * Este metodo a�ade una carta del mazo al mazo del jugador cada vez que
     * estocada la figura que representa el mazo en pantalla.
     */

    public static void tomarMazo() {
        if (Juego.btnMazo.meTocaste()) {
            addCarta();
            p.setPositionCartas();
        }
    }

    /**
     * @param int turno
     *            <p>
     *            <pre>
     *                                                                                                                                                                                                                                        Devuelve la carta que esta siendo tocada o null
     *                                                                                                                                                                                                                                        en caso de que ninguna este siendo tocada.
     *                                                                                                                                                                                                                                        </pre>
     * @return Carta seleccionada
     */

    private static Carta seleccionada() {
        Carta c = null;
        if (p.getId() == Juego.turno) {
            for (int i = 0; i < p.getNumEle(); i++) {
                int j = p.getIndex() + i;
                if (j < p.getMazoPlayer().size()) {
                    c = p.getMazoPlayer().get(j);
                    if (c.meEstasTocando(Juego.turno)) {
                        if (c.meTocaste()) {
                            AtsSound.sonarSound(AtsSound.seleccion);
                        }
                        return c;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param Vector3 v
     * @param Carta   s
     *                <p>
     *                <pre>
     *                                                                                                                                                                                                                                                                                                                            Realiza la reordenacion de las cartas, cuando
     *                                                                                                                                                                                                                                                                                                                            un jugador posiciona la carta seleccionada s sobre otra carta.
     *                                                                                                                                                                                                                                                                                                                            </pre>
     */

    private static void sobreOtraCarta(Movimiento m) {
        Carta s = cartaPorID(m.getIdCarta());
        Vector3 v = m.getMoveVector();
        for (int i = 0; i < p.getNumEle(); i++) {
            int j = p.getIndex() + i;
            if (j < p.getMazoPlayer().size()) {
                Carta c = p.getMazoPlayer().get(j);
                if (c != s) {
                    if (c.getRectangle().contains(v)) {
                        p.setPositionCartas();
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * Este metodo se encarga de pasarbolas al jugador actual,en el caso extremo
     * de que, este no tenga ninguna carta que coindida en su operacion aritmetica
     * con la operacion aritmetica de la carta del centro o en su caso ninguna que
     * coincida con el color y se de el caso de que en el mazo ya no queden cartas;
     * ademas de que en el centro tampoco quede mas que la carta que se mira.
     * En ese caso el jugador actual esta frio y se le pasa bolas y se retorna verdadero,
     * para continuar con el juego normal. En otro caso se retorna falso.
     * </pre>
     *
     * @return boolean frio
     */

    private static boolean frio() {
        Carta cc = Juego.centroCarta;
        for (int i = 0; i < p.getMazoPlayer().size(); i++) {
            Carta c = p.getMazoPlayer().get(i);
            if ((cc.getColor() == c.getColor())
                    || (cc.getValor() == c.getValor())
                    || (c.getColor() == DataCarta.neg)
                    || (cc.getColorComodin() == c.getColor())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param Carta   c
     * @param Vector3 v
     *                <p>
     *                <pre>
*      Realiza el movimiento de la carta seleccionada; si la carta no esta sobre
*     otra carta o la carta no se encuentra sobre la carta del centro, entonces
*     a la carta seleccionada se le asigna la posicision en pantalla en direcccion
*     a donde la mueva el jugador; en caso de que la carta seleccionada se encuentre
*     sobre la carta que se encuentra en el centro, entonces si el color
*     o valor aritmetico de la carta seleccionada es igual al color, color comodin
*     o valor aritmetico de la carta que se encuentra en el centro, entonces
*     la carta seleccionada se convierte en la nueva carta del centro, se elimina del
*     mazo del jugador en turno y se finaliza el turno del jugador actual; en caso
*     contrario se emite un pitido y se reacomoda la carta en su lugar original.
*     </pre>
*
*                <pre>
*                                                                                                                                                                                                                                                                                                                            Caso especial cuando la carta seleccionada es una carta comodin, es decir, es
*                                                                                                                                                                                                                                                                                                                            una carta de color negro, entonces la carta seleccionada se convierte en la
*                                                                                                                                                                                                                                                                                                                            nueva carta del centro, se elimina del mazo del jugador en turno, se muestra
*                                                                                                                                                                                                                                                                                                                            al jugador en turno una pantalla para que elija el color que desea para el color
*                                                                                                                                                                                                                                                                                                                            de la siguiente carta del centro y se finaliza el turno del jugador actual.
*     </pre>
     */

    private static void moverCarta(Movimiento m) {
        Carta c = cartaPorID(m.getIdCarta());
        Vector3 v = m.getMoveVector();
        Carta cc = Juego.centroCarta;
        if (c != null) {
            if ((c.meEstasTocando(Juego.turno)) || (AtsUtil.machine) || (AtsUtil.mismoDispositivo)) {
                if (cc.getRectangle().contains(v)) {

                    if (acepta(c)) {
                        if (c.getValor() == DataCarta.cBloq) {
                            Juego.bloquear = true;
                        } else if (c.getValor() == DataCarta.cReg) {
                            Juego.regresar = (Juego.regresar) ? false : true;
                        } else if (c.getValor() == DataCarta.cMas1) {
                            Juego.masuno = true;
                        } else if (c.getValor() == DataCarta.cMas2) {
                            Juego.masdos = true;
                        }

                        AtsSound.sonarSound(AtsSound.correcto);
                        Juego.addMazo(c);
                        p.getMazoPlayer().remove(c);
                        p.setPositionCartas();

                        if (p.getMazoPlayer().size() == 0) {
                            Juego.terminoJuego = true;
                            Juego.idPlayer = p.getId();
                            return;
                        } else {
                            if (c.getColor() == DataCarta.neg) {
                                if (((AtsUtil.machine) && (p.getId() != 2))
                                        || ((!AtsUtil.machine)))
                                    AtsUtil.game
                                            .setScreen(new ScreenEligeColor(c));
                                else
                                    machineeligecolor(c);
                            }
                            Juego.removeMazo(cc);
                            Juego.terminoTurno = true;
                            cartaMachineY = 8.4f;
                        }

                        if ((p.getIndex() - 1) >= 0) {
                            p.setIndex(p.getIndex() - 1);
                            p.setPositionCartas();
                        }
                    } else {
                        AtsSound.sonarSound(AtsSound.incorrecto);
                        p.setPositionCartas();
                    }
                } else {
                    moverCartaID(c, v);
                }
            }
        } else {
            p.setPositionCartas();
        }
    }

    /**
     * @param int id
     *            <p>
     *            <pre>
     *                                                                                                                                                                                                                                        Recibe como parametro el id del jugador que esta en turno
     *                                                                                                                                                                                                                                        y si el id del jugador es igual al id del jugador que esta en turno
     *                                                                                                                                                                                                                                        entonces se puede realizar el movimiento de las cartas, hacia el
     *                                                                                                                                                                                                                                        centro.
     *                                                                                                                                                                                                                                        </pre>
     */

    public static void turno() {
        if (Bluetooth.bluetooth()) {


            System.out
                    .println("turnorecibido"
                            + BluetoothSingleton.getInstance().bluetoothManager
                            .RecuperarNumero());
            Juego.turno = BluetoothSingleton.getInstance().bluetoothManager
                    .RecuperarTurno();

        }
        // System.out.println("turno juego"+Juego.turno+"Jugador ID"+p.getId());
        if (Juego.turno == p.getId()) {
            if (Juego.terminoTurno) {
                if (Bluetooth.bluetooth()) {
                    if (Bluetooth.bluetoothTurno()) {
                        int turno = sigJugador();
                        String a = "T" + turno;
                        System.out.println("turnoaa" + a);
                        BluetoothSingleton.getInstance().bluetoothManager.sendMessage2(a);
                    } else {
                        System.out
                                .println("turnorecibido"
                                        + BluetoothSingleton.getInstance().bluetoothManager
                                        .RecuperarNumero());
                        Juego.turno = BluetoothSingleton.getInstance().bluetoothManager
                                .RecuperarTurno();
                    }
                } else {
                    sigJugador();
                }
                rotarCentro();
                if (Bluetooth.machine())
                    cartaMachineY = 8.4f;
                if (Bluetooth.mismoDispositivo()) {
                    AtsUtil.game.setScreen(new ScreenSiguientePlayer(
                            Juego.turno));
                }
                Juego.terminoTurno = false;
                return;
            } else {
                if (Juego.masuno) {
                    addCarta();
                    Juego.masuno = false;
                } else if (Juego.masdos) {
                    addCarta();
                    addCarta();
                    Juego.masdos = false;
                } else if (Juego.bloquear) {
                    Juego.terminoTurno = true;
                    Juego.bloquear = false;
                } else if ((Load.mazo.getCartas().size() == 0)
                        && (Juego.centroCartaMazo.size() == 1) && (!frio())) {
                    Juego.terminoTurno = true;
                }

                if (!Bluetooth.machineTurno(p)) {
                    Carta c = seleccionada();
                    Vector3 v = AtsTM.tocasteAqui();

                    if (c != null) {
                        Movimiento m = new Movimiento(c.getId(), v);
//						System.out.println("movimiento" + m.toString());
                        if (Bluetooth.bluetooth()) {
                            Bluetooth.sendMessage(m);
                        }

                        moverCarta(m);
                        sobreOtraCarta(m);
                    } else {
                        p.setPositionCartas();
                    }
                } else {
                    p.setPositionCartas();
                    if (!Juego.terminoTurno)
                        machine();
                    Vector3 v = new Vector3(AtsPos.centroX, cartaMachineY, 0);
                    moverCarta(new Movimiento(cartaMachine.getId(), v));
                    if ((cartaMachineY - 0.1f) > AtsPos.centroY)
                        cartaMachineY -= 0.1f;
                }
            }
        } else if (!Bluetooth.bluetoothTurno() && Bluetooth.bluetooth()) {
            Movimiento bMove = null;
            bMove = Bluetooth.getMessage();
            System.out.println(bMove.toString());
            if (bMove != null) {
                moverCarta(bMove);
            }
        }
    }

}