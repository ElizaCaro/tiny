package ast;

public class NodoParametro extends NodoBase{
    private String identificador;
    private NodoBase tipo;

    public NodoParametro(String identificador, NodoBase tipo) {
        super();
        this.identificador = identificador;
        this.tipo = tipo;
    }
    
    public NodoParametro() {
        super();
        this.identificador = null;
        this.tipo = null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public NodoBase getTipo() {
        return tipo;
    }

    public void setTipo(NodoBase tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
