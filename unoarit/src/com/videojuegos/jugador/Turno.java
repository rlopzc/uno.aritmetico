package com.videojuegos.jugador;

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

import java.util.ArrayList;
import java.util.Random;

public class Turno {
    private static Player p;
    private static float cartaMachineY = 8.4f;
    private static DBTurnoHandler db = new DBTurnoHandler(MainP.getContext());

    private static long segundos_inicio, segundos_final;

    /**
     * Es la carta de la maquina
     */
    public static Carta cartaMachine = null;

    /**
     * Asigna el Player p al turno actual y si el turno actual es el de el
     * Player p entonces le permite realizar su movimiento
     */

    public static void setPlayer(Player p) {
        Turno.setP(p);
    }

    private static ArrayList<Carta> machineCartaColor() {
        ArrayList<Carta> cartasColor = new ArrayList<Carta>();
        Carta cc = Juego.centroCarta;

        for (int i = 0; i < getP().getMazoPlayer().size(); i++) {
            Carta c = getP().getMazoPlayer().get(i);
            if ((cc.getColor() == c.getColor())
                    || (cc.getColorComodin() == c.getColor())
                    || (c.getColor() == DataCarta.neg)) {
                cartasColor.add(c);
            }
        }
        return cartasColor;
    }

    private static boolean machineTieneColor(int i) {

        for (int j = 0; j < getP().getMazoPlayer().size(); j++) {
            if (Load.cartasBase[i].getColor() == getP().getMazoPlayer().get(j)
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

        for (int i = 0; i < getP().getMazoPlayer().size(); i++) {
            Carta c = getP().getMazoPlayer().get(i);
            if (cc.getValor() == c.getValor()) {
                cartasValor.add(c);
            }
        }
        return cartasValor;
    }

    private static boolean acepta(Carta c) {
        Carta cc = Juego.centroCarta;
        if (c != null) {
            if ((!cc.getOperacion().equalsIgnoreCase("sinOperacion") && !c.getOperacion().equalsIgnoreCase("sinOperacion"))) {
                if ((cc.getColor() == c.getColor())) {
                    if (cc.getValor() == c.getValor()) {
                        if (c.getOperacion().equals(cc.getOperacion())) {
                            getP().sumarPuntosPorOperacionSimple();

                        } else {
                            getP().sumarPuntosPorOperacionDoble();
                        }
                        segundos_final = System.currentTimeMillis();
                        save("si", cc.getOperacion(), c.getOperacion(), Integer.toString(c.getValor()), obtenerTiempoDelTurno());
                        return true;
                    } else {
                        getP().sumarPuntosPorColor();

                        segundos_final = System.currentTimeMillis();
                        save("si", "", "", "", obtenerTiempoDelTurno());

                        return true;
                    }
                } else if (cc.getValor() == c.getValor()) {
                    if (c.getOperacion().equals(cc.getOperacion())) {
                        getP().sumarPuntosPorOperacionSimple();

                    } else {
                        getP().sumarPuntosPorOperacionDoble();

                    }
                    segundos_final = System.currentTimeMillis();
                    save("no", cc.getOperacion(), c.getOperacion(), Integer.toString(c.getValor()), obtenerTiempoDelTurno());

                    return true;
                }
            } else if (cc.getValor() == c.getValor()) {
                getP().sumarPuntosPorColor();
                segundos_final = System.currentTimeMillis();

                return true;
            } else if ((c.getColor() == DataCarta.neg) || (cc.getColorComodin() == c.getColor())) {
                segundos_final = System.currentTimeMillis();

                return true;
            }
            segundos_final = System.currentTimeMillis();

            return getP().getCorreo().equalsIgnoreCase("Maquina"); //return true.
        }
        segundos_final = System.currentTimeMillis();

        return false;
    }

    public static long obtenerTiempoDelTurno() {
        esPrimeraVez = true;//Volvemos a empezar para tomar el tiempo del siguiente turno.
        return ((segundos_final - segundos_inicio) / 1000); //Convertimos el tiempo de milisegundos a segundos.
    }

    /**
     * Metodo para almacenar los turnos de cada jugador
     */

    public static void save(String color, String operacionMazo, String operacionJugada, String valor, long tiempoTardadoEnMoverCarta) {
        Juego.turnoDB++;
        //Se agregan los datos a la tabla y se imprime el movimiento
        db.agregarTurno(new DBTurno(Juego.idPartidaDB, Juego.turnoDB, getP().getCorreo(),
                Juego.colorDB, operacionMazo, operacionJugada, valor, tiempoTardadoEnMoverCarta));

        String jugada = "Número de Partida: " + Juego.idPartidaDB
                + ", Turno: " + Juego.turnoDB + ", Jugador: " + getP().getCorreo()
                + ", Color: " + color
                + ", Operacion Mazo: " + operacionMazo
                + ", Operacion Jugada: " + operacionJugada
                + ", Valor: " + valor +
                ", Tiempo: " + tiempoTardadoEnMoverCarta;
        System.out.println(jugada);
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
                c.setJugador(getP().getId());
            }
            AtsSound.sonarSound(AtsSound.correcto);
            getP().getMazoPlayer().add(getP().getIndex(), c);
            return c;
        } catch (Exception e) {
            // System.out.println(e.toString());
        }
        return null;
    }

    public static Carta cartaPorID(int id) {
        for (int i = 0; i < getP().getMazoPlayer().size(); i++) {
            if (getP().getMazoPlayer().get(i).getId() == id) {
                return getP().getMazoPlayer().get(i);
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
            getP().setPositionCartas();
        }
    }

    /**
     * @return Carta seleccionada
     */

    private static Carta seleccionada() {
        Carta c = null;
        if (getP().getId() == Juego.turno) {
            for (int i = 0; i < getP().getNumEle(); i++) {
                int j = getP().getIndex() + i;
                if (j < getP().getMazoPlayer().size()) {
                    c = getP().getMazoPlayer().get(j);
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
     * @param m Movimiento
     *          </pre>
     */

    private static void sobreOtraCarta(Movimiento m) {
        Carta s = cartaPorID(m.getIdCarta());
        Vector3 v = m.getMoveVector();
        for (int i = 0; i < getP().getNumEle(); i++) {
            int j = getP().getIndex() + i;
            if (j < getP().getMazoPlayer().size()) {
                Carta c = getP().getMazoPlayer().get(j);
                if (c != s) {
                    if (c.getRectangle().contains(v)) {
                        getP().setPositionCartas();
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
        for (int i = 0; i < getP().getMazoPlayer().size(); i++) {
            Carta c = getP().getMazoPlayer().get(i);
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
     * @param m Movimiento
     *          <p>
     *          <pre>
     *                                                                                    Realiza el movimiento de la carta seleccionada; si la carta no esta sobre
     *                                                                                   otra carta o la carta no se encuentra sobre la carta del centro, entonces
     *                                                                                   a la carta seleccionada se le asigna la posicision en pantalla en direcccion
     *                                                                                   a donde la mueva el jugador; en caso de que la carta seleccionada se encuentre
     *                                                                                   sobre la carta que se encuentra en el centro, entonces si el color
     *                                                                                   o valor aritmetico de la carta seleccionada es igual al color, color comodin
     *                                                                                   o valor aritmetico de la carta que se encuentra en el centro, entonces
     *                                                                                   la carta seleccionada se convierte en la nueva carta del centro, se elimina del
     *                                                                                   mazo del jugador en turno y se finaliza el turno del jugador actual; en caso
     *                                                                                   contrario se emite un pitido y se reacomoda la carta en su lugar original.
     *                                                                                   </pre>
     *
     *          <pre>     de la siguiente carta del centro y se finaliza el turno del jugador actual.
     *                                                                                   </pre>
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
                            Juego.regresar = (!Juego.regresar);
                        } else if (c.getValor() == DataCarta.cMas1) {
                            Juego.masuno = true;
                        } else if (c.getValor() == DataCarta.cMas2) {
                            Juego.masdos = true;
                        }

                        AtsSound.sonarSound(AtsSound.correcto);
                        Juego.addMazo(c);
                        getP().getMazoPlayer().remove(c);
                        getP().setPositionCartas();

                        if (getP().getMazoPlayer().size() == 0) {
                            Juego.terminoJuego = true;
                            Juego.idPlayer = getP().getId();
                            return;
                        } else {
                            if (c.getColor() == DataCarta.neg) {
                                if (((AtsUtil.machine) && (getP().getId() != 2))
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

                        if ((getP().getIndex() - 1) >= 0) {
                            getP().setIndex(getP().getIndex() - 1);
                            getP().setPositionCartas();
                        }
                    } else {
                        AtsSound.sonarSound(AtsSound.incorrecto);
                        getP().setPositionCartas();
                    }
                } else {
                    moverCartaID(c, v);
                }
            }
        } else {
            getP().setPositionCartas();
        }


    }

    /**
     * </pre>
     */

    private static boolean esPrimeraVez = true;

    public static void turno() {

        if (esPrimeraVez) {
            segundos_inicio = System.currentTimeMillis();
            esPrimeraVez = false;
        }

        if (Bluetooth.bluetooth()) {
            System.out.println("turnorecibido" + BluetoothSingleton.getInstance().bluetoothManager.RecuperarNumero());
            Juego.turno = BluetoothSingleton.getInstance().bluetoothManager.RecuperarTurno();
        }
        if (Juego.turno == getP().getId()) {

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

                if (!Bluetooth.machineTurno(getP())) {
                    Carta c = seleccionada();
                    Vector3 v = AtsTM.tocasteAqui();

                    if (c != null) {
                        Movimiento m = new Movimiento(c.getId(), v);
                        if (Bluetooth.bluetooth()) {
                            Bluetooth.sendMessage(m);
                        }

                        moverCarta(m);
                        sobreOtraCarta(m);
                    } else {
                        getP().setPositionCartas();
                    }
                } else {
                    getP().setPositionCartas();
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

    public static Player getP() {
        return p;
    }

    public static void setP(Player p) {
        Turno.p = p;
    }
}