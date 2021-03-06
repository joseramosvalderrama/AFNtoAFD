package tlp_pract1;

import java.util.ArrayList;

public class GrupoEstados {
	
	private ArrayList<Estado> out;
	private int id;
	
	
	public GrupoEstados(int id) {
		this.id=id;
		out= new ArrayList<Estado>();
	}
	

	public String toString() {
		String resul="{";
		for(Estado e : out) 
			resul+=e.getId()+",";
		resul = resul.substring(0, resul.length() - 1);
		resul +="}";
		return resul;
	}
	

	public int getId() {
		return id;
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
