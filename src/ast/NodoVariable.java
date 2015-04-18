package ast;

public class NodoVariable extends NodoBase{
    private String identificador;
    private Integer numero;

    public NodoVariable(String identificador) {
        this.identificador = identificador;
        this.numero = null;
    }
    
    public NodoVariable(String identificador, Integer numero) {
        this.identificador = identificador;
        this.numero = numero;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    
}
