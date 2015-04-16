package ast;

public class NodoFuncionRetorna extends NodoBase{
    private NodoBase tipo;
    private String identificador;
    private NodoBase secuencias; //cuerpo
    private NodoBase expresion; //retorno
    private NodoBase parametros;

    public NodoFuncionRetorna() {
        super();
        this.tipo = null;
        this.secuencias = null;
        this.expresion = null;
        this.parametros = null;
        this.identificador = null;
    }
    
    public NodoFuncionRetorna(NodoBase tipo, NodoBase secuencias, NodoBase expresion, String identificador) {
        super();
        this.tipo = tipo;
        this.secuencias = secuencias;
        this.expresion = expresion;
        this.identificador = identificador;
    }
    
     public NodoFuncionRetorna(NodoBase tipo, NodoBase parametros,NodoBase secuencias, NodoBase expresion, String identificador ) {
        super();
        this.tipo = tipo;
        this.secuencias = secuencias;
        this.expresion = expresion;
        this.parametros = parametros;
        this.identificador = identificador;
    }

    public NodoBase getTipo() {
        return tipo;
    }

    public void setTipo(NodoBase tipo) {
        this.tipo = tipo;
    }

    public NodoBase getSecuencias() {
        return secuencias;
    }

    public void setSecuencias(NodoBase secuencias) {
        this.secuencias = secuencias;
    }

    public NodoBase getExpresion() {
        return expresion;
    }

    public void setExpresion(NodoBase expresion) {
        this.expresion = expresion;
    }

    public NodoBase getParametros() {
        return parametros;
    }

    public void setParametros(NodoBase parametros) {
        this.parametros = parametros;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
    
}
