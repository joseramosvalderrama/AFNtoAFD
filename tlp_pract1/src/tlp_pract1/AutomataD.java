package tlp_pract1;

import java.util.ArrayList;
import java.util.Scanner;

public class AutomataD extends Automata{

	
	private int [][] delta;
	
	static Scanner sc=new Scanner(System.in);
	
	public AutomataD() {
		super();
		delta= new int [estados.size()][sigma.length];
		delta[0][0]= Integer.MAX_VALUE;
	}
	
	public AutomataD(Estado [] estados, char [] sigma,int [][] delta) {
		super(sigma, estados);
		this.delta=delta;
	}
	
	public AutomataD transformar(AutomataN aut) {
		ArrayList<GrupoEstados> act= new ArrayList<GrupoEstados>();
		int it=0;
		int estadoMax=0;
		
		estados.add(new Estado(it,"inicial"));
		act.add(new GrupoEstados(it));
		act.get(it).aniadirEstado(aut.getEstados().get(it));	//Linea de Victor.
		
		
		do {
			it++;
			GrupoEstados it1 = act.get(it);
			for(Estado it2 : it1.getOut()) 
				for(int i=0;i<aut.getSigma().length;i++) {
					ArrayList<Estado> aux = aut.getDelta()[it2.getId()][i].getOut();
					if(noestaAuxenAct(aux,act)) {
						estadoMax++;
						act.add(new GrupoEstados(estadoMax));
						act.get(estadoMax).setOut(aux);
						if(auxhasFinal(aux))
							estados.add(new Estado(estadoMax,"Final"));
						else estados.add (new Estado(estadoMax,"Comun"));
									
						}	
				}
		}while(it < estadoMax);
		
		return this;
	}
	
	private boolean auxhasFinal(ArrayList<Estado> estados) {
		for(Estado es : estados)
			if(es.getTipo().equals("Final"))
				return true;
		return false;
	}
	
	private boolean noestaAuxenAct(ArrayList<Estado> estados, ArrayList<GrupoEstados> g) {
		for(GrupoEstados i : g)
			if(g.equals(estados))
				return false;
		return true;
	}
	
	
	
	public void reducirAutomata() {
		boolean [] accesibles= inicializarafalse();
		boolean [] coaccesibles= inicializarafalse();
		
		rellenarAccesibles(accesibles);
		rellenarCoAccesibles(coaccesibles);
		buscaryeliminar(accesibles,coaccesibles);
	}
	
	private void buscaryeliminar(boolean [] ac, boolean [] coac) {
		for(int i=0; i< ac.length;i++)
			if(!ac[i] || !coac[i]) {
				estados[i]=null;
				eliminarTransiciones(i);
			}
	}
	
	private void eliminarTransiciones(int id) {
		for(int i=0;i<delta[0].length; i++)
			delta[id][i]=Integer.MAX_VALUE;
		
		for(int i=0;i<delta.length;i++)
			for(int j=0; j< delta[0].length; j++)
				if(delta[i][j] == id)
					delta[i][j]=Integer.MAX_VALUE;
	}
	
	private void rellenarAccesibles(boolean [] resul) {
		ArrayList<Integer> ant = new ArrayList<Integer>();
		ArrayList<Integer> act = new ArrayList<Integer>();
		boolean [] it =resul;
		//iteracion 1
		resul[0]=true;
		act.add(0);
		
		do {
			resul=it;
			ant=act;
			act.clear();
			for(int i : ant)
				for(int j=0; j< delta[0].length; j++)
					if(delta[i][j]!=Integer.MAX_VALUE) {
						it[delta[i][j]]=true;
						act.add(delta[i][j]);
					}
		}while(esigual(resul,it));
	}
	
	private void rellenarCoAccesibles(boolean [] resul) {
		ArrayList<Integer> ant = new ArrayList<Integer>();
		ArrayList<Integer> act = new ArrayList<Integer>();
		boolean [] it =resul;
		//iteracion 1
		for (int i=0; i<estados.length; i++)
			if(estados[i].getTipo().equals("final")) {
				act.add(i);
				it[i]=true;
			}
		
		do {
			resul=it;
			ant=act;
			act.clear();
			for(int i : ant)
				for(int j=0; j< delta.length; j++)
					for(int k=0; k< delta[0].length;k++)
					 if(delta[j][k]== i){
						act.add(j);
						it[j]=true;
					 }
		}while(esigual(resul,it));
	}
	
	private boolean esigual(boolean [] v1, boolean [] v2) {
		if(v1.length != v2.length)
			return false;
		for(int i=0; i< v1.length; i++)
			if(v1[i] != v2[i])
				return false;
		return true;
	}
	
	
	
	private boolean [] inicializarafalse() {
		boolean [] resul= new boolean [estados.length];
		for(int i=0; i< estados.length; i++)
			resul[i]=false;
		return resul;
	}
}
