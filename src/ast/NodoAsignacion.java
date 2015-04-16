package ast;

public class NodoAsignacion extends NodoBase {
	private String identificador;
	private NodoBase expresion;
        private Integer posicion;
	
        public NodoAsignacion(){
            super();
            this.expresion = null;
            this.identificador = null;
            this.posicion = null;
        }
        
	public NodoAsignacion(String identificador) {
		super();
		this.identificador = identificador;
		this.expresion = null;
	}
	
        public NodoAsignacion(String identificador, Integer posicion)
        {
            super();
            this.identificador = identificador;
            this.posicion = posicion;
        }
        
	public NodoAsignacion(String identificador, NodoBase expresion) {
		super();
		this.identificador = identificador;
		this.expresion = expresion;
	}
        
        public NodoAsignacion(String identificador,Integer posicion, NodoBase expresion) {
		super();
		this.identificador = identificador;
                this.posicion = posicion;
		this.expresion = expresion;
	}

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }
        

        
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public NodoBase getExpresion() {
		return expresion;
	}

	public void setExpresion(NodoBase expresion) {
		this.expresion = expresion;
	}
	
	
	
}
