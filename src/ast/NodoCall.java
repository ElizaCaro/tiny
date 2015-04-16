package ast;

public class NodoCall extends NodoBase{
    private String identificador;
    private NodoBase parametros;

    
    public NodoCall(String identificador) {
        super();
        this.identificador = identificador;
        this.parametros = null;
    }
    
    public NodoCall(String identificador, NodoBase parametros) {
        super();
        this.identificador = identificador;
        this.parametros = parametros;
    }

    public NodoCall() {
        this.identificador = null;
        this.parametros = null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public NodoBase getParametros() {
        return parametros;
    }

    public void setParametros(NodoBase parametros) {
        this.parametros = parametros;
    }
    

    
}
