package com.videojuegos.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsScreens;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.AtsUtil;
import com.videojuegos.asset.Load;

import java.util.ArrayList;

public class InputScreenJuego implements InputProcessor {

    public SpriteBatch spritebatch;
    public String palabra;
    public ArrayList<Character> texto;
    public float x;
    public float y;
    public float ancho;
    public float alto;
    private boolean rotarPosicion;
    private float gradosRotacion;

    public InputScreenJuego(SpriteBatch batch, float x, float y) {
        this.x = x;
        this.y = y;
        this.spritebatch = batch;
        texto = new ArrayList<Character>();
        setRotarPosicion(false);
        setGradosRotacion(0);
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        // System.out.println(keycode);
        if (keycode == Input.Keys.BACK) {
            AtsUtil.game.setScreen(AtsScreens.screenMain);
        }
        if (texto.size() >= 1) {
            if (keycode == 67) {
                texto.remove(texto.size() - 1);
                // System.out.println("despues"+texto.size());
            }
        }
        return true;
    }

    @Override
    public boolean keyTyped(char caracter) {
        if (caracter != ' ') {
            llenar_texto(caracter);
        }
        /*if(caracter==8){

		}
		else{
			if(texto.size()>13){

			}
			else{
				if (caracter != ' ') {
					texto.add(caracter);
				}
			}
		}
		*/
        return false;
    }

    public void llenar_texto(char caracter) {

        texto.add(caracter);
    }

    public void limpiarTexto() {
        texto.clear();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 v = AtsTM.tocasteAqui();
        // System.out.println("x = " + v.x + "\n" + "y = " + v.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    public String getPalabra() {
        if (texto.isEmpty()) return " Vacio";

        palabra = "";
        for (int i = 0; i < texto.size(); i++) {
            palabra += texto.get(i);
        }
        return palabra;
    }

    /**
     * Establece el nombre del jugador, que obtiene cuando lo teclean en pantalla.
     */
    public void setJugador(ArrayList<Character> texto) {
        this.texto = texto;
    }

    public void Actualizar() {
        @SuppressWarnings("unused")
        PintarLetra prueba;
        float respaldo = this.x;
        if (texto.size() != 0) {
            for (int i = 0; i < texto.size(); i++) {
                switch (texto.get(i)) {
                    case '0':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);

                        respaldo += AtsPos.anchoNumero;

                        break;
                    case '1':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.uno, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.uno, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '2':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.dos, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.dos, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;

                        break;
                    case '3':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.tres, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.tres, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '4':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.cuatro, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.cuatro, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '5':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.cinco, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.cinco, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '6':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.seis, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.seis, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '7':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.siete, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.siete, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '8':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.ocho, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.ocho, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case '9':
                        if (rotarPosicion)
                            prueba = new PintarLetra(this.spritebatch, Load.nueve, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero, getGradosRotacion());
                        else
                            prueba = new PintarLetra(this.spritebatch, Load.nueve, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                        respaldo += AtsPos.anchoNumero;
                        break;
                    case 'a':
                        prueba = new PintarLetra(this.spritebatch, Load.a, respaldo, this.y, AtsPos.anchoLetraA, AtsPos.altoLetraA);
                        respaldo += AtsPos.anchoLetraA;
                        break;
                    case 'b':
                        prueba = new PintarLetra(this.spritebatch, Load.b, respaldo, this.y, AtsPos.anchoLetraB, AtsPos.altoLetraB);
                        respaldo += AtsPos.anchoLetraB;
                        break;
                    case 'c':
                        prueba = new PintarLetra(this.spritebatch, Load.c, respaldo, this.y, AtsPos.anchoLetraC, AtsPos.altoLetraC);
                        respaldo += AtsPos.anchoLetraC;
                        break;
                    case 'd':
                        prueba = new PintarLetra(this.spritebatch, Load.d, respaldo, this.y, AtsPos.anchoLetraD, AtsPos.altoLetraD);
                        respaldo += AtsPos.anchoLetraD;
                        break;
                    case 'e':
                        prueba = new PintarLetra(this.spritebatch, Load.e, respaldo, this.y, AtsPos.anchoLetraE, AtsPos.altoLetraE);
                        respaldo += AtsPos.anchoLetraE;
                        break;
                    case 'f':
                        prueba = new PintarLetra(this.spritebatch, Load.f, respaldo, this.y, AtsPos.anchoLetraF, AtsPos.altoLetraF);
                        respaldo += AtsPos.anchoLetraF;
                        break;
                    case 'g':
                        prueba = new PintarLetra(this.spritebatch, Load.g, respaldo, this.y, AtsPos.anchoLetraG, AtsPos.altoLetraG);
                        respaldo += AtsPos.anchoLetraG;
                        break;
                    case 'h':
                        prueba = new PintarLetra(this.spritebatch, Load.h, respaldo, this.y, AtsPos.anchoLetraH, AtsPos.altoLetraH);
                        respaldo += AtsPos.anchoLetraH;
                        break;
                    case 'i':
                        prueba = new PintarLetra(this.spritebatch, Load.i, respaldo, this.y, AtsPos.anchoLetraI, AtsPos.altoLetraI);
                        respaldo += AtsPos.anchoLetraI;
                        break;
                    case 'j':
                        prueba = new PintarLetra(this.spritebatch, Load.j, respaldo, this.y, AtsPos.anchoLetraJ, AtsPos.altoLetraJ);
                        respaldo += AtsPos.anchoLetraJ;
                        break;
                    case 'k':
                        prueba = new PintarLetra(this.spritebatch, Load.k, respaldo, this.y, AtsPos.anchoLetraK, AtsPos.altoLetraK);
                        respaldo += AtsPos.anchoLetraK;
                        break;
                    case 'l':
                        prueba = new PintarLetra(this.spritebatch, Load.l, respaldo, this.y, AtsPos.anchoLetraL, AtsPos.altoLetraL);
                        respaldo += AtsPos.anchoLetraL;
                        break;
                    case 'm':
                        prueba = new PintarLetra(this.spritebatch, Load.m, respaldo, this.y, AtsPos.anchoLetraM, AtsPos.altoLetraM);
                        respaldo += AtsPos.anchoLetraM;
                        break;
                    case 'n':
                        prueba = new PintarLetra(this.spritebatch, Load.n, respaldo, this.y, AtsPos.anchoLetraN, AtsPos.altoLetraN);
                        respaldo += AtsPos.anchoLetraN;
                        break;
                    case '�':
                        prueba = new PintarLetra(this.spritebatch, Load.enie, respaldo, this.y, AtsPos.anchoLetraENIE, AtsPos.altoLetraENIE);
                        respaldo += AtsPos.anchoLetraENIE;
                        break;
                    case 'o':
                        prueba = new PintarLetra(this.spritebatch, Load.o, respaldo, this.y, AtsPos.anchoLetraO, AtsPos.altoLetraO);
                        respaldo += AtsPos.anchoLetraO;
                        break;
                    case 'p':
                        prueba = new PintarLetra(this.spritebatch, Load.p, respaldo, this.y, AtsPos.anchoLetraP, AtsPos.altoLetraP);
                        respaldo += AtsPos.anchoLetraP;
                        break;
                    case 'q':
                        prueba = new PintarLetra(this.spritebatch, Load.q, respaldo, this.y, AtsPos.anchoLetraQ, AtsPos.altoLetraQ);
                        respaldo += AtsPos.anchoLetraQ;
                        break;
                    case 'r':
                        prueba = new PintarLetra(this.spritebatch, Load.r, respaldo, this.y, AtsPos.anchoLetraR, AtsPos.altoLetraR);
                        respaldo += AtsPos.anchoLetraR;
                        break;
                    case 's':
                        prueba = new PintarLetra(this.spritebatch, Load.s, respaldo, this.y, AtsPos.anchoLetraS, AtsPos.altoLetraS);
                        respaldo += AtsPos.anchoLetraS;
                        break;
                    case 't':
                        prueba = new PintarLetra(this.spritebatch, Load.t, respaldo, this.y, AtsPos.anchoLetraT, AtsPos.altoLetraT);
                        respaldo += AtsPos.anchoLetraT;
                        break;
                    case 'u':
                        prueba = new PintarLetra(this.spritebatch, Load.u, respaldo, this.y, AtsPos.anchoLetraU, AtsPos.altoLetraU);
                        respaldo += AtsPos.anchoLetraU;
                        break;
                    case 'v':
                        prueba = new PintarLetra(this.spritebatch, Load.v, respaldo, this.y, AtsPos.anchoLetraV, AtsPos.altoLetraV);
                        respaldo += AtsPos.anchoLetraV;
                        break;
                    case 'w':
                        prueba = new PintarLetra(this.spritebatch, Load.w, respaldo, this.y, AtsPos.anchoLetraW, AtsPos.altoLetraW);
                        respaldo += AtsPos.anchoLetraW;
                        break;
                    case 'x':
                        prueba = new PintarLetra(this.spritebatch, Load.x, respaldo, this.y, AtsPos.anchoLetraX, AtsPos.altoLetraX);
                        respaldo += AtsPos.anchoLetraX;
                        break;
                    case 'y':
                        prueba = new PintarLetra(this.spritebatch, Load.y, respaldo, this.y, AtsPos.anchoLetraY, AtsPos.altoLetraY);
                        respaldo += AtsPos.anchoLetraY;
                        break;
                    case 'z':
                        prueba = new PintarLetra(this.spritebatch, Load.z, respaldo, this.y, AtsPos.anchoLetraZ, AtsPos.altoLetraZ);
                        respaldo += AtsPos.anchoLetraZ;
                        break;
                    default:
                        break;

                }
            }
        }
    }

    public boolean isRotarPosicion() {
        return rotarPosicion;
    }

    public void setRotarPosicion(boolean rotarPosicion) {
        this.rotarPosicion = rotarPosicion;
    }

    public float getGradosRotacion() {
        return gradosRotacion;
    }

    public void setGradosRotacion(float gradosRotacion) {
        this.gradosRotacion = gradosRotacion;
    }
}
