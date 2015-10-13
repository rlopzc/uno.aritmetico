package com.videojuegos.input;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.AtsTM;
import com.videojuegos.asset.Load;

import java.util.ArrayList;

public class PintarLetra {

    private SpriteBatch batch;
    public Sprite texture;
    public float x, y, anh, alt, grados;

    public PintarLetra(SpriteBatch batch, Sprite texture, float x, float y, float anh, float alt) {
        inicializar(batch, texture, x, y, anh, alt, 0);

    }

    public PintarLetra(SpriteBatch batch, Sprite texture, float x, float y, float anh, float alt, float grados) {
        inicializar(batch, texture, x, y, anh, alt, grados);

    }

    private void inicializar(SpriteBatch batch, Sprite texture, float x, float y, float anh, float alt, float grados) {
        this.texture = new Sprite(texture);
        this.anh = anh;
        this.alt = alt;
        this.x = x;
        this.y = y;
        this.batch = batch;

        if (texture != null) {
            setPosicion(x, y, grados);
        }

        this.batch.enableBlending();
        this.batch.begin();
        this.texture.draw(this.batch);
        this.batch.end();

    }

    public void setPosicion(float x, float y, float grados) {
        this.x = x;
        this.y = y;
        AtsTM.rotar(this.texture, anh, alt, grados);
        AtsTM.setPosicion(this.texture, x, y, anh, alt);
    }

    public Sprite get() {
        return this.texture;
    }

    public void blabla(ArrayList<Character> texto, float x) {
        float respaldo = x;
        for (int i = 0; i < texto.size(); i++) {
            switch (texto.get(i)) {
                case '0':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '1':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '2':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '3':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '4':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '5':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '6':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '7':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '8':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '9':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'a':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'b':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'c':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'd':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'e':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'f':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'g':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'h':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'i':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'j':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'k':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'l':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'm':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'n':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case '�':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'o':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'p':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'q':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'r':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 's':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 't':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'u':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'v':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'w':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'x':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'y':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
                case 'z':
                    batch.draw(Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
                    respaldo += AtsPos.anchoNumero;
                    break;
//				case ' ':
//					prueba=new PintarLetra(this.spritebatch,Load.btnsoundoff, respaldo, this.y, AtsPos.anchoLetraZ, AtsPos.altoLetraZ);
//					respaldo+=AtsPos.anchoLetraZ;
//					break;
                default:
                    break;

            }
        }
    }

}
