package com.videojuegos.input;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.videojuegos.asset.AtsPos;
import com.videojuegos.asset.Load;

public class ListaDevices {
	public SpriteBatch spritebatch;
	public String letra;
	public float x;
	public float y;
	public float ancho;
	public float alto;
	public float cambiolinea;
	public int NumMax;
	
	
	public ListaDevices(SpriteBatch batch,float x,float y,float ancho,float alto,int nummax){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.spritebatch=batch;
		cambiolinea=y;
		NumMax=nummax;}
	
	public void Asignar(ArrayList<String> valores){
		
		if(valores.size()>0){
		if(valores.size()>0 && valores.size()<=NumMax){
		for(int i=0;i<valores.size();i++){
			y=cambiolinea;
			Actualizar(valores.get(i));
			cambiolinea+=-.6;}
		}
		else{
			for(int i=0;i<NumMax;i++){
				y=cambiolinea;
				Actualizar(valores.get(i));
				cambiolinea+=-.6;}	
		}
		}
	}
	
	public void Actualizar(String  texto){
		@SuppressWarnings("unused")
		PintarLetra prueba;
		float respaldo=this.x;
		if(texto.length()==0){
			
		}else{
			for(int i=0;i<texto.length();i++){
				switch(texto.charAt(i)){
				
				case '0':
					prueba=new PintarLetra(this.spritebatch,Load.cero, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '1':
					prueba=new PintarLetra(this.spritebatch,Load.uno, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '2':
					prueba=new PintarLetra(this.spritebatch,Load.dos, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '3':
					prueba=new PintarLetra(this.spritebatch,Load.tres, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '4':
					prueba=new PintarLetra(this.spritebatch,Load.cuatro, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '5':
					prueba=new PintarLetra(this.spritebatch,Load.cinco, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '6':
					prueba=new PintarLetra(this.spritebatch,Load.seis, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '7':
					prueba=new PintarLetra(this.spritebatch,Load.siete, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '8':
					prueba=new PintarLetra(this.spritebatch,Load.ocho, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case '9':
					prueba=new PintarLetra(this.spritebatch,Load.nueve, respaldo, this.y, AtsPos.anchoNumero, AtsPos.altoNumero);
					respaldo+=AtsPos.anchoNumero;
					break;
				case 'a':
					prueba=new PintarLetra(this.spritebatch,Load.a, respaldo, this.y, AtsPos.anchoLetraA, AtsPos.altoLetraA);
					respaldo+=AtsPos.anchoLetraA;
					break;	
				case 'b':
					prueba=new PintarLetra(this.spritebatch,Load.b, respaldo, this.y, AtsPos.anchoLetraB, AtsPos.altoLetraB);
					respaldo+=AtsPos.anchoLetraB;
					break;
				case 'c':
					prueba=new PintarLetra(this.spritebatch,Load.c, respaldo, this.y, AtsPos.anchoLetraC, AtsPos.altoLetraC);
					respaldo+=AtsPos.anchoLetraC;
					break;
				case 'd':
					prueba=new PintarLetra(this.spritebatch,Load.d, respaldo, this.y, AtsPos.anchoLetraD, AtsPos.altoLetraD);
					respaldo+=AtsPos.anchoLetraD;
					break;
				case 'e':
					prueba=new PintarLetra(this.spritebatch,Load.e, respaldo, this.y, AtsPos.anchoLetraE, AtsPos.altoLetraE);
					respaldo+=AtsPos.anchoLetraE;
					break;
				case 'f':
					prueba=new PintarLetra(this.spritebatch,Load.f, respaldo, this.y, AtsPos.anchoLetraF, AtsPos.altoLetraF);
					respaldo+=AtsPos.anchoLetraF;
					break;
				case 'g':
					prueba=new PintarLetra(this.spritebatch,Load.g, respaldo, this.y, AtsPos.anchoLetraG, AtsPos.altoLetraG);
					respaldo+=AtsPos.anchoLetraG;
					break;
				case 'h':
					prueba=new PintarLetra(this.spritebatch,Load.h, respaldo, this.y, AtsPos.anchoLetraH, AtsPos.altoLetraH);
					respaldo+=AtsPos.anchoLetraH;
					break;	
				case 'i':
					prueba=new PintarLetra(this.spritebatch,Load.i, respaldo, this.y, AtsPos.anchoLetraI, AtsPos.altoLetraI);
					respaldo+=AtsPos.anchoLetraI;
					break;	
				case 'j':
					prueba=new PintarLetra(this.spritebatch,Load.j, respaldo, this.y, AtsPos.anchoLetraJ, AtsPos.altoLetraJ);
					respaldo+=AtsPos.anchoLetraJ;
					break;
				case 'k':
					prueba=new PintarLetra(this.spritebatch,Load.k, respaldo, this.y, AtsPos.anchoLetraK, AtsPos.altoLetraK);
					respaldo+=AtsPos.anchoLetraK;
					break;
				case 'l':
					prueba=new PintarLetra(this.spritebatch,Load.l, respaldo, this.y, AtsPos.anchoLetraL, AtsPos.altoLetraL);
					respaldo+=AtsPos.anchoLetraL;
					break;
				case 'm':
					prueba=new PintarLetra(this.spritebatch,Load.m, respaldo, this.y, AtsPos.anchoLetraM, AtsPos.altoLetraM);
					respaldo+=AtsPos.anchoLetraM;
					break;
				case 'n':
					prueba=new PintarLetra(this.spritebatch,Load.n, respaldo, this.y, AtsPos.anchoLetraN, AtsPos.altoLetraN);
					respaldo+=AtsPos.anchoLetraN;
					break;
				case 'ñ':
					prueba=new PintarLetra(this.spritebatch,Load.enie, respaldo, this.y, AtsPos.anchoLetraENIE, AtsPos.altoLetraENIE);
					respaldo+=AtsPos.anchoLetraENIE;
					break;
				case 'o':
					prueba=new PintarLetra(this.spritebatch,Load.o, respaldo, this.y, AtsPos.anchoLetraO, AtsPos.altoLetraO);
					respaldo+=AtsPos.anchoLetraO;
					break;
				case 'p':
					prueba=new PintarLetra(this.spritebatch,Load.p, respaldo, this.y, AtsPos.anchoLetraP, AtsPos.altoLetraP);
					respaldo+=AtsPos.anchoLetraP;
					break;	
				case 'q':
					prueba=new PintarLetra(this.spritebatch,Load.q, respaldo, this.y, AtsPos.anchoLetraQ, AtsPos.altoLetraQ);
					respaldo+=AtsPos.anchoLetraQ;
					break;
				case 'r':
					prueba=new PintarLetra(this.spritebatch,Load.r, respaldo, this.y, AtsPos.anchoLetraR, AtsPos.altoLetraR);
					respaldo+=AtsPos.anchoLetraR;
					break;
				case 's':
					prueba=new PintarLetra(this.spritebatch,Load.s, respaldo, this.y, AtsPos.anchoLetraS, AtsPos.altoLetraS);
					respaldo+=AtsPos.anchoLetraS;
					break;
				case 't':
					prueba=new PintarLetra(this.spritebatch,Load.t, respaldo, this.y, AtsPos.anchoLetraT, AtsPos.altoLetraT);
					respaldo+=AtsPos.anchoLetraT;
					break;
				case 'u':
					prueba=new PintarLetra(this.spritebatch,Load.u, respaldo, this.y, AtsPos.anchoLetraU, AtsPos.altoLetraU);
					respaldo+=AtsPos.anchoLetraU;
					break;
				case 'v':
					prueba=new PintarLetra(this.spritebatch,Load.v, respaldo, this.y, AtsPos.anchoLetraV, AtsPos.altoLetraV);
					respaldo+=AtsPos.anchoLetraV;
					break;
				case 'w':
					prueba=new PintarLetra(this.spritebatch,Load.w, respaldo, this.y, AtsPos.anchoLetraW, AtsPos.altoLetraW);
					respaldo+=AtsPos.anchoLetraW;
					break;
				case 'x':
					prueba=new PintarLetra(this.spritebatch,Load.x, respaldo, this.y, AtsPos.anchoLetraX, AtsPos.altoLetraX);
					respaldo+=AtsPos.anchoLetraX;
					break;
				case 'y':
					prueba=new PintarLetra(this.spritebatch,Load.y, respaldo, this.y, AtsPos.anchoLetraY, AtsPos.altoLetraY);
					respaldo+=AtsPos.anchoLetraY;
					break;	
				case 'z':
					prueba=new PintarLetra(this.spritebatch,Load.z, respaldo, this.y, AtsPos.anchoLetraZ, AtsPos.altoLetraZ);
					respaldo+=AtsPos.anchoLetraZ;
					break;
				default:
					break;	
				}
			}
		}
	}
}
