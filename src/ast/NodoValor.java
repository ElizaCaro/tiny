package ast;

public class NodoValor extends NodoBase {
    private Integer Vint;
    private Boolean Vbol;

    public NodoValor(Integer Vint, Boolean Vbol) {
        super();
        this.Vint = null;
        this.Vbol = null;
    }

    public NodoValor(Integer Vint) {
        super();
        this.Vint = Vint;
        this.Vbol = null;
    }

    public NodoValor(Boolean Vbol) {
        super();
        this.Vbol = Vbol;
        this.Vint = null;
    }
        
    public Integer getVint() {
        return Vint;
    }

    public void setVint(Integer Vint) {
        this.Vint = Vint;
    }

    public Boolean isVbol() {
        return Vbol;
    }

    public void setVbol(Boolean Vbol) {
        this.Vbol = Vbol;
    }

    
    
}
