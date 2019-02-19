package tlp_pract1;

import java.util.ArrayList;

public class GrupoEstados {
	private char in1;
	private ArrayList<Estado> out;
	private int id;
	
	public GrupoEstados(int i){
		id=i;
	}
	
	public void setOut(ArrayList<Estado> out) {
		this.out=out;
	}
	
	public ArrayList<Estado> getOut(){
		return this.out;
	}
	
	
	
	public void aniadirEstado(Estado e) {
		out.add(e);
	}
	

}
