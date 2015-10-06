package com.videojuegos.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsSound;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.DataCarta;
import com.videojuegos.asset.Load;
import com.videojuegos.cartas.Boton;
import com.videojuegos.cartas.Carta;
import com.videojuegos.jugador.Juego;

public class ScreenEligeColor implements Screen {

    private Carta cc;//Suponemos que significa "Carta del Centro"

    private Boton btnvefu, btnamfu, btnazfu, btnroj, btnmo;
    //Estos botones, ya no son necesarios: btnveba, btnamba, btnazba, btnna, btnros

    private SpriteBatch batch;

    /**
     * Aqui se pinta la pantalla de elegir color
     * cuando se selecciona y se pone al mazo del centro
     * la carta comodin +2
     */

    public ScreenEligeColor(Carta cc) {
        this.cc = cc;
    }

    /**
     * este metodo pinta botones encima de las cartas
     * asignandoles el color en que se haya puesto, luego
     * pinta encima del mazo del centro la carta de color
     * seleccionada.
     */
    @Override
    public void render(float delta) {
        if (btnamfu.meTocaste()) {
            cc.setColorComodin(DataCarta.amfu);
            AtsUtil.game.setScreen(AtsScreens.screenJuego); //NO SACAR ESTA LINEA DE LOS IFS, YA QUE AUNQUE SE REPITA, ES NECESARIO QUE PRIMERO SE HAGA
            //LA VERIFICACION DE SI ALGUN BOTON FUE PRESIONADO. RECORDAR QUE RENDER ES LLAMADO CADA "fps" (Frame Per Second).
            Juego.addMazo(Load.cartasBase[0]);
        } else if (btnazfu.meTocaste()) {
            cc.setColorComodin(DataCarta.azfu);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            Juego.addMazo(Load.cartasBase[1]);
        } else if (btnmo.meTocaste()) {
            cc.setColorComodin(DataCarta.mo);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            Juego.addMazo(Load.cartasBase[2]);
        } else if (btnroj.meTocaste()) {
            cc.setColorComodin(DataCarta.roj);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            Juego.addMazo(Load.cartasBase[3]);
        } else if (btnvefu.meTocaste()) {
            cc.setColorComodin(DataCarta.vefu);
            AtsUtil.game.setScreen(AtsScreens.screenJuego);
            Juego.addMazo(Load.cartasBase[4]);
        }


        AtsUtil.limpiarP();
        batch.disableBlending();
        batch.begin();
        batch.draw(Load.backgroundeligecolor, 0, 0, 15, 10);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    /**
     * Inicializa los botones y sus posiciones *
     */
    @Override
    public void show() {
        acomodar_botones_para_elegir_color();
        // Gdx.input.setInputProcessor(new InputScreenJuego());
    }

    /**
     * Inicializa los botones y sus posiciones nuevamente
     * cuando la aplicaci�n ha sido interrumpida
     */
    @Override
    public void resume() {
        acomodar_botones_para_elegir_color();
    }

    private void acomodar_botones_para_elegir_color() {
        batch = AtsUtil.batch;
        // ES LA NUMERO 0, DE ACUERDO AL ARREGLO DECLARADO EN EL METODO cargarCartasBase() DE LA CLASE MAZO.
        btnamfu = new Boton(null,
                AtsPos.carta_amarilla_comodin_posx,
                AtsPos.carta_amarilla_comodin_posy,
                AtsPos.anchoCarta, AtsPos.altoCarta,
                0);

        // ES LA NUMERO 1, DE ACUERDO AL ARREGLO DECLARADO EN EL METODO cargarCartasBase() DE LA CLASE MAZO.
        btnazfu = new Boton(null,
                AtsPos.carta_azul_comodin_posx,
                AtsPos.carta_azul_comodin_posy,
                AtsPos.anchoCarta,
                AtsPos.altoCarta,
                0);

        // ES LA NUMERO 2, DE ACUERDO AL ARREGLO DECLARADO EN EL METODO cargarCartasBase() DE LA CLASE MAZO.
        btnmo = new Boton(null,
                AtsPos.carta_morado_comodin_posx,
                AtsPos.carta_morado_comodin_posy,
                AtsPos.anchoCarta,
                AtsPos.altoCarta,
                0);

        // ES LA NUMERO 3, DE ACUERDO AL ARREGLO DECLARADO EN EL METODO cargarCartasBase() DE LA CLASE MAZO.
        btnroj = new Boton(null,
                AtsPos.carta_rojo_comodin_posx,
                AtsPos.carta_rojo_comodin_posy,
                AtsPos.anchoCarta,
                AtsPos.altoCarta,
                0);

        // ES LA NUMERO 4, DE ACUERDO AL ARREGLO DECLARADO EN EL METODO cargarCartasBase() DE LA CLASE MAZO.
        btnvefu = new Boton(null,
                AtsPos.carta_verde_comodin_posx,
                AtsPos.carta_verde_comodin_posy,
                AtsPos.anchoCarta,
                AtsPos.altoCarta,
                0);
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
    public void dispose() {
        Load.atlas.dispose();
        AtsUtil.batch.dispose();
        AtsSound.dispose();
    }

}
