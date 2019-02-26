package tlp_pract1;

import java.util.ArrayList;

public class GrupoEstados {
	private int in1;
	private int in2;
	private ArrayList<Estado> out;
	private int id;
	
	
	public GrupoEstados(int id) {
		this.id=id;
		in1=Integer.MAX_VALUE;
		in2='.';
		out= new ArrayList<Estado>();
	}
	
	public GrupoEstados(int id, int in1, int in2){
		this.id=id;
		this.in1=in1;
		this.in2=in2;
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
	
	public int getIn1() {
		return in1;
	}
	
	public int getIn2() {
		return in2;
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
