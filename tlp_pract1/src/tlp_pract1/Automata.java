package tlp_pract1;

import java.util.Scanner;
import java.util.ArrayList;

public abstract class Automata {

	protected char[] sigma;
	protected ArrayList<Estado> estados;
	
	static Scanner sc=new Scanner(System.in);
	
	public Automata() {
		
		sigma= new char [1];
		sigma[0]= '1';
		estados= new ArrayList<Estado>();
		estados.add(new Estado(0,"inicial"));
	}
	
	public Automata(char [] sigma,Estado [] estados) {
	
		this.sigma=sigma;
		for(int i=0;i<estados.length;i++)
		this.estados.add(estados[i]);
		
	}

	public char[] getSigma() {
		return sigma;
	}

	public void setSigma(char[] sigma) {
		this.sigma = sigma;
	}

	public ArrayList<Estado> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}
	
	public Estado getEstado(int i) {
		return estados.get(i);
	}
	
	
}

