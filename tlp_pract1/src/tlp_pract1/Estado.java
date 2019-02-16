package tlp_pract1;

public class Estado {
	private int id;
	private String tipo;
	
	public Estado() {
		
	}
	
	public Estado(int id,String tipo) {
		this.id=id;
		this.tipo=tipo;
		
	}
	
	public String toString() {
		return ("id: "+id+" tipo: "+tipo);
	}
	
	public int getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	
}
