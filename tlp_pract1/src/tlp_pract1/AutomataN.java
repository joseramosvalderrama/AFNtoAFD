package tlp_pract1;

import java.util.ArrayList;
import java.util.Scanner;

public class AutomataN extends Automata{
	private int [][][] delta;
	
	static Scanner sc=new Scanner(System.in);
	
	public AutomataN() {
	
		super();
		
		
	}
	
	public AutomataN(Estado [] estados, char [] sigma, int [][][] delta) {
		
		super(sigma, estados);
		this.delta= delta;
		
	}
	
/*	
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
*/	
	
	
	
}
