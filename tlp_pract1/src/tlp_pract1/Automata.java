package tlp_pract1;

import java.util.Scanner;
import java.util.ArrayList;

public abstract class Automata {

	protected char[] sigma;
	protected Estado[] estados;
	
	static Scanner sc=new Scanner(System.in);
	
	public Automata() {
		
		sigma= new char [1];
		sigma[0]= '1';
		estados = new Estado[1];
		estados[0]= new Estado(0, "inicial");
		
	}
	
	public Automata(char [] sigma, Estado[] estados) {
	
		this.sigma=sigma;
		this.estados=estados;
		
	}
	
}

