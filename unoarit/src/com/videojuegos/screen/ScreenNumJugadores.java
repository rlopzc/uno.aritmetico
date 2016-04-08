package com.videojuegos.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * �sta clase establece el n�mero de jugadores que se mostrar�n
 * en el juego en el modo jugar en el mismo dispositivo (en el men�
 * Multijugador) y se crean botones para que el jugador decida
 * �l n�mero
 */

public class ScreenNumJugadores implements Screen {
    private SpriteBatch batch;
    private Boton btndos, btntres, btncuatro, btnAtras;
    private ArrayList<String> emailsJugadores;
    private AtsInputListener listener;

    @Override
    public void render(float delta) {
        if (btndos.meTocaste()) {
            listener = new AtsInputListener(2);
            Gdx.input.getTextInput(listener, "Ingresa correo del Jugador 2", "");
            AtsUtil.setBackground(Load.backgroundplay2);
            AtsScreens.screenJuego = new ScreenJuego(2, emailsJugadores);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            return;
        } else if (btntres.meTocaste()) {
            listener = new AtsInputListener(3);
            Gdx.input.getTextInput(listener, "Ingresa correo del Jugador 3", "");
            AtsUtil.setBackground(Load.backgroundplay3);
            AtsScreens.screenJuego = new ScreenJuego(3, emailsJugadores);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            return;
        } else if (btncuatro.meTocaste()) {
            listener = new AtsInputListener(4);
            Gdx.input.getTextInput(listener, "Ingresa correo del Jugador 4", "");
            AtsUtil.setBackground(Load.backgroundplay4);
            AtsScreens.screenJuego = new ScreenJuego(4, emailsJugadores);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            return;
        } else if (btnAtras.meTocaste()) {
            AtsUtil.game.setScreen(AtsScreens.screenMultiPlayer);
            return;
        }
        AtsUtil.limpiarP();
        batch.disableBlending();
        batch.begin();
        batch.draw(Load.backgroundnumplayers, 0, 0, 15, 10);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        batch = AtsUtil.batch;
        btndos = new Boton(null, 5.0f, 3.7f, 1.5f, 3f, 30);
        btntres = new Boton(null, 7.5f, 4.4f, 1.5f, 3f, 0);
        btncuatro = new Boton(null, 9.8f, 3.7f, 1.5f, 3f, -30);
        btnAtras = new Boton(null, 13.5f, 0.8f, 1.5f, 1.2f, 0);
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        AtsSound.pause();
    }

    @Override
    public void resume() {
        batch = AtsUtil.batch;
        btndos = new Boton(null, 9.7f, 7.7f, 1.5f, 3f, -25);
        btntres = new Boton(null, 12.0f, 6.062499f, 1.5f, 3f, -25);
        btncuatro = new Boton(null, 9.0f, 4.3124995f, 1.5f, 3f, 25);
        btnAtras = new Boton(null, 13.5f, 0.8f, 1.5f, 1.2f, 0);
    }

    @Override
    public void dispose() {
        Load.atlas.dispose();
        AtsUtil.batch.dispose();
        AtsSound.dispose();
    }

    /**
     * Clase privada se gestiona la gestion del dialogo para ingresar el correo del jugador.
     */

    private class AtsInputListener implements Input.TextInputListener {

        private int numJug = 0;
        private int currentPlayer = 1;

        public AtsInputListener(int numJug) {
            this.numJug = numJug;
            emailsJugadores = new ArrayList<String>();
        }

        /**
         * Metodo para recepcionar el correo del jugador
         */

        @Override
        public void input(String correo) {
            if (!esCorreo(correo)) {
                AtsSound.sonarSound(AtsSound.incorrecto);
                Gdx.input.getTextInput(listener, "Ingresa correo del Jugador " + currentPlayer, "");
            } else {
                currentPlayer++;
                AtsSound.sonarSound(AtsSound.correcto);
                agregaJugador(correo);
                if (numJug == 2) {
                    if (emailsJugadores.size() == 2) {
                        AtsScreens.screenJuego = new ScreenJuego(2, emailsJugadores);
                        AtsUtil.game.setScreen(AtsScreens.screenJuego);
                    } else {
                        ingresaCorreoJugador(2);
                    }
                } else if (numJug == 3) {
                    if (emailsJugadores.size() == 3) {
                        AtsScreens.screenJuego = new ScreenJuego(3, emailsJugadores);
                        AtsUtil.game.setScreen(AtsScreens.screenJuego);
                    } else if (emailsJugadores.size() == 1) {
                        ingresaCorreoJugador(2);
                    } else if (emailsJugadores.size() == 2) {
                        ingresaCorreoJugador(3);
                    }

                } else if (numJug == 4) {
                    if (emailsJugadores.size() == 4) {
                        AtsScreens.screenJuego = new ScreenJuego(4, emailsJugadores);
                        AtsUtil.game.setScreen(AtsScreens.screenJuego);
                    } else if (emailsJugadores.size() == 1) {
                        ingresaCorreoJugador(2);
                    } else if (emailsJugadores.size() == 2) {
                        ingresaCorreoJugador(3);
                    } else if (emailsJugadores.size() == 3) {
                        ingresaCorreoJugador(4);
                    }
                }
            }
        }

        /**
         * Metodo para cancelar el ingreso del correo del jugador.
         */

        @Override
        public void canceled() {
            emailsJugadores.clear();
            AtsUtil.game.setScreen(AtsScreens.screenNumPlayer);
        }

        /**
         * Metodo para agregar jugadores a la lista
         */

        public void agregaJugador(String email) {
            emailsJugadores.add(email);
            //System.out.println(emailsJugadores);
        }

        /**
         * Metodos para llamar el dialogo para ingresar el correo del jugador.
         */

        public void ingresaCorreoJugador(int jugador) {
            if (jugador == 2) {
                Gdx.input.getTextInput(listener, "Ingresa correo del Jugador 2", "");
            } else if (jugador == 3) {
                Gdx.input.getTextInput(listener, "Ingresa correo del Jugador 3", "");
            } else if (jugador == 4) {
                Gdx.input.getTextInput(listener, "Ingresa correo del Jugador 4", "");
            }
        }

        /**
         * Metodos para validar si el correo ingresado es un correo valido
         */

        private final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );

        private boolean esCorreo(String email) {
            return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
        }
    }

}
