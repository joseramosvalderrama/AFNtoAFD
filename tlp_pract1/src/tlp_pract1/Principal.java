package tlp_pract1;

import java.util.Scanner;

public class Principal {
	
	static Scanner sc= new Scanner(System.in);

	public static void main(String[] args) {
		//crearAutomata();
		AutomataN an= new AutomataN();
		AutomataD ad= new AutomataD();
		an.leerAutomata();
		an.mostrarDelta();
		ad.transformar(an);
		ad.mostrarDelta();
		ad.reducirAutomata();
		ad.mostrarDelta();
	}
	
}
