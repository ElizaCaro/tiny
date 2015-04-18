package ast;

public class NodoFuncionSinRetorna extends NodoBase{
    private NodoBase parametros;
    private NodoBase secuencias;
     private String identificador;

    public NodoFuncionSinRetorna(NodoBase secuencias, String identificador) {
        super();
        this.parametros = null;
        this.secuencias = secuencias;
        this.identificador = identificador;
    }
    public NodoFuncionSinRetorna(NodoBase parametros, NodoBase secuencias, String identificador) {
        super();
        this.parametros = parametros;
        this.secuencias = secuencias;
        this.identificador = identificador;
    }
    
    public NodoFuncionSinRetorna(){
        super();
        this.parametros = null;
        this.secuencias = null;
        this.identificador = null;
    }

    public NodoBase getParametros() {
        return parametros;
    }

    public void setParametros(NodoBase parametros) {
        this.parametros = parametros;
    }

    public NodoBase getSecuencias() {
        return secuencias;
    }

    public void setSecuencias(NodoBase secuencias) {
        this.secuencias = secuencias;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
}
