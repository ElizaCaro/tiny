package ast;

public class NodoIdentificador extends NodoBase {
	private String nombre;
        private Integer numero;

        public NodoIdentificador(){
            super();
            this.nombre = null;
            this.numero = null;
        }
	public NodoIdentificador(String nombre) {
		super();
		this.nombre = nombre;
                this.numero = null;
	}
        
        public NodoIdentificador(String nombre, Integer numero) {
		super();
		this.nombre = nombre;
                this.numero = numero;
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
        
        

	

}
