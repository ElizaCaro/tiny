package ast;

public class NodoFor extends NodoBase{

	private NodoBase asignacion;
	private NodoBase condicion;
	private NodoBase acumulador;
	private NodoBase cuerpo;

	public NodoFor(NodoBase asignacion, NodoBase condicion, NodoBase acumulador, NodoBase cuerpo){
		super();
		this.asignacion = asignacion;
		this.condicion = condicion;
		this.acumulador = acumulador;
		this.cuerpo = cuerpo;
	}


	public NodoFor(){
		super();
		this.asignacion = null;
		this.condicion = null;
		this.acumulador = null;
		this.cuerpo = null;
	}

	public NodoBase getAsignacion(){
		return asignacion;
	}

	public void setAsignacion(NodoBase asignacion){
		this.asignacion = asignacion;
	}

	public NodoBase getCondicion(){
		return condicion;
	}

	public void setCondicion(NodoBase condicion){
		this.condicion = condicion;
	}

	public NodoBase getAcumulador(){
		return acumulador;
	}

	public void setAcumulador(NodoBase acumulador){
		this.acumulador = acumulador;
	}

	public NodoBase getCuerpo(){
		return cuerpo;
	}

	public void setCuerpo(NodoBase cuerpo){
		this.cuerpo = cuerpo;
	}
}