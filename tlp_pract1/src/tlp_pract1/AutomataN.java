package tlp_pract1;

import java.util.ArrayList;
import java.util.Scanner;

public class AutomataN extends Automata{
	
	private GrupoEstados [][] delta;
	
	static Scanner sc=new Scanner(System.in);
	
	public AutomataN() {
		super();
		
	}
	
	public AutomataN(Estado [] estados, char [] sigma, GrupoEstados [][] delta) {
		
		super(sigma, estados);
		this.delta= delta;
		
	}
	
	public void mostrarDelta() {
		for(int i=0;i<delta.length;i++) {
			System.out.println("Estado "+i);
			for(int j=0;j<delta[0].length;j++)
				System.out.println("\tA traves de "+sigma[j]+": "+delta[i][j]);
		}	
	}
	
	
	public GrupoEstados [][] getDelta() {
		return delta;
	}
	

	public void leerAutomata() {
		
		this.estados= leerEstados();
		
		this.sigma= leerSigma().toCharArray();
		
		this.delta = leerDelta(estados, sigma);
		
		
	}
	
	private GrupoEstados [][] leerDelta(ArrayList<Estado> estados, char [] numInputs){
		GrupoEstados [][] delta = new GrupoEstados [estados.size()][numInputs.length];
		int it=0;
		
		for(Estado e : estados) {
			System.out.println("Estado "+e.toString());
			for(int j=0; j<numInputs.length; j++) {
				int answ;
				delta[it][j]= new GrupoEstados(it);
				System.out.println("Relaciona a través de "+numInputs[j]+"(Numero Negativo para terminar)");
				do{
					answ= sc.nextInt();
					if(answ >= 0)
						delta[it][j].aniadirEstado(estados.get(answ));
				} while(answ >= 0);
			}
			it++;
		}
		
		return delta;
	}
	
	private ArrayList<Estado> leerEstados() {
		int it=0;
		char answ;
		ArrayList<Estado>  estados = new ArrayList<Estado>();
		
		System.out.println("Introduzca los estados terminando con un .");

		estados.add(new Estado(0, "inicial"));
		
		
		do {
			it++;
			System.out.println("Estado n"+it+" es final? (S/N) O . si no quiere aniadirlo.");
			answ = sc.next().charAt(0);
			if(answ == '.')
				System.out.println("Numero de estados = "+ it);
			else if (answ== 'S'  || answ== 's')
				estados.add(new Estado(it,"final"));
			else
				estados.add(new Estado(it,"normal"));
		}while(answ != '.');
		
		return estados;
		
	}
	
	
	
	private String leerSigma() {
		String sigma;
		System.out.println("Introduzca el alfabeto de inputs");
		System.out.println("Introduzca uno a uno los caracteres del alfabeto");
		sigma= sc.next();
		return sigma;
	}
	
	/*
	
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
	
	
	*/
	
}
