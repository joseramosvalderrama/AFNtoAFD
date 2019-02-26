package tlp_pract1;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;
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
	
	public void mostrarDelta() {
		for(int i=0;i<delta.length;i++) {
			System.out.println("Estado "+i);
			for(int j=0;j<delta[0].length;j++)
				System.out.println("\tA traves de "+sigma[j]+": "+delta[i][j]);
		}	
	}
	
	public AutomataD transformar(AutomataN aut) {
		ArrayList<GrupoEstados> act= new ArrayList<GrupoEstados>();
		HashMap<Integer, GrupoEstados> auxDelta= new HashMap<>();
		int it=0;
		int estadoMax=0;
		
		super.sigma= aut.sigma;
		super.estados.add(new Estado(it,"inicial"));
		act.add(new GrupoEstados(it));
		act.get(it).aniadirEstado(aut.getEstados().get(it));	//Linea de Victor.
		
		
		do {
			GrupoEstados it1 = act.get(it);		//Cogemos el grupo de estados de esta iteracion.
			for(Estado it2 : it1.getOut()) 		//Recorremos cada uno de los estados de it1 para ver sus transiciones.
				for(int i=0;i<aut.getSigma().length;i++) {
					ArrayList<Estado> aux = aut.getDelta()[it2.getId()][i].getOut();
					if(noestaAuxenAct(aux,act)) {
						estadoMax++;
						act.add(new GrupoEstados(estadoMax,it,i));	//Rellenar s1,s2...
						act.get(estadoMax).setOut(aux);
						if(auxhasFinal(aux))
							super.estados.add(new Estado(estadoMax,"Final"));
						else super.estados.add (new Estado(estadoMax,"Comun"));
									
						}	
				}
			it++;
		}while(it <= estadoMax);
		
		rellenarDelta(act);
		
		return this;
	}
	
	private void rellenarDelta(ArrayList<GrupoEstados> grupos) {
		delta= new int [grupos.size()][sigma.length];
		
		int i=0;
		for(GrupoEstados g : grupos) {
			System.out.println(i);
			delta[g.getIn1()][g.getIn2()]=g.getId();
			System.out.println(i);
			i++;
		}
			
				
	}
	
	private boolean auxhasFinal(ArrayList<Estado> estados) {
		for(Estado es : estados)
			if(es.getTipo().equals("Final"))
				return true;
		return false;
	}
	
	private boolean noestaAuxenAct(ArrayList<Estado> estados, ArrayList<GrupoEstados> g) {
		for(GrupoEstados i : g)
			if(i.getOut().equals(estados))
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
		Iterator<Estado> it=estados.iterator();
		for(int i=0; i< ac.length;i++)
		{
			if(!ac[i] || !coac[i]) {
				//estados[i]=null;
				it.remove();
				eliminarTransiciones(i);
			}
			it.next();
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
		}while(!esigual(resul,it));
	}
	
	private void rellenarCoAccesibles(boolean [] resul) {
		ArrayList<Integer> ant = new ArrayList<Integer>();
		ArrayList<Integer> act = new ArrayList<Integer>();
		boolean [] it =resul;
		//Iterator<Estado> it2=estados.iterator();
		//Estado a=it2.next();
		
		/*for (int i=0; i<estados.length; i++)
			if(estados[i].getTipo().equals("final")) {
				act.add(i);
				it[i]=true;
			}
		while(it2.hasNext())
		{
			if(a.getTipo().equals("final"))
				act.
			a=it2.next();
			it2.add
		}
		*/
		
		//iteracion 1
		for (Estado e : super.estados)
			if(e.getTipo().equals("final")) {
				act.add(e.getId());
				it[e.getId()]=true;
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
		}while(!esigual(resul,it));
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
		boolean [] resul= new boolean [estados.size()];
		
		/*
		for(int i=0; i< estados.length; i++)
			resul[i]=false;
			
		*/
		for(Estado e : super.estados)
			resul[e.getId()]=false;
		
		return resul;
	}
}
