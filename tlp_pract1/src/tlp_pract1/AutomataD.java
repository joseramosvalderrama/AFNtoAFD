package tlp_pract1;

import java.util.ArrayList;
import java.util.Scanner;

public class AutomataD extends Automata{

	private ArrayList<Estado> estados;
	private int [][] delta;
	
	static Scanner sc=new Scanner(System.in);
	
	public AutomataD() {
		super();
		estados= new ArrayList<Estado>();
		estados.add(new Estado(0,"inicial"));
		delta= new int [estados.size()][sigma.length];
		delta[0][0]= Integer.MAX_VALUE;
	}
	
	public AutomataD(ArrayList<Estado> estados, char [] sigma,int [][] delta) {
		super(sigma);
		this.estados= estados;
		this.delta=delta;
	}
	
	
	public void leerAutomata() {
		
		this.estados= leerEstados();
		
		this.sigma= leerSigma().toCharArray();
		
		this.delta = leerDelta(estados, sigma);
		
		
	}
	
	private int [][] leerDelta(Estado [] estados, char [] numInputs){
		int [][] delta = new int [estados.length][numInputs.length];
		
		for(int i=0;i<estados.length;i++) {
			System.out.println("Estado "+estados[i].toString());
			for(int j=0; j<numInputs.length; j++) {
				do {
				System.out.println("Relaciona a través de "+numInputs[j]+" con id:"+i+ "(Numero Negativo si no relaciona) ");
				delta[i][j]= sc.nextInt();
				}while (delta[i][j]>= estados.length);
				if(delta[i][j] < 0)
					delta[i][j]= Integer.MAX_VALUE;
			}
		}
		
		return delta;
	}
	
	private String leerSigma() {
		String sigma;
		System.out.println("Introduzca el alfabeto de inputs");
		System.out.println("Introduzca uno a uno los caracteres del alfabeto");
		sigma= sc.next();
		return sigma;
	}
	
	private Estado [] leerEstados() {
		int numestados;
		Estado []  estados;
		
		System.out.println("Introduzca el numero de estados del automata: ");
		numestados= sc.nextInt();
		
		estados= new Estado [numestados];
		
		estados[0]= new Estado(0, "inicial");
		for(int i=1; i< numestados; i++) {
			char answ;
			System.out.println("Estado n"+i+" es final? (S/N)");
			answ = sc.next().charAt(0);
			if (answ== 'S'  || answ== 's')
				estados[i]=new Estado(i,"final");
			else
				estados[i] = new Estado (i, "comun");
		}
		
		return estados;
		
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
