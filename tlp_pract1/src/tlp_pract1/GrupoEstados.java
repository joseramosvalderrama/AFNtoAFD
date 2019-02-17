package tlp_pract1;

import java.util.ArrayList;

public class GrupoEstados {
	private char in1;
	private Estado in2;
	private ArrayList<Estado> out;
	
	public GrupoEstados(Estado e, char c){
		in1=c;
		in2=e;
		out= new ArrayList<Estado>();
	}

}
