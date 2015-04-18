package ast;

public class NodoCall extends NodoBase{
    private String identificador;
    private NodoBase argumentos;

    
    public NodoCall(String identificador) {
        super();
        this.identificador = identificador;
        this.argumentos = null;
    }
    
    public NodoCall(String identificador, NodoBase argumentos) {
        super();
        this.identificador = identificador;
        this.argumentos = argumentos;
    }

    public NodoCall() {
        this.identificador = null;
        this.argumentos = null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public NodoBase getArgumentos() {
        return argumentos;
    }

    public void setArgumentos(NodoBase argumentos) {
        this.argumentos = argumentos;
    }
    

    
}
