package ast;

public class NodoDeclaracion extends NodoBase{
   private NodoBase tipo;
   private NodoBase lis_asig;

   
    public NodoDeclaracion() {
        super();
        this.tipo = null;
        this.lis_asig = null;
    }
    
   public NodoDeclaracion(NodoBase tipo, NodoBase lis_asig) {
        super();
        this.tipo = tipo;
        this.lis_asig = lis_asig;
    }

    public NodoBase getTipo() {
        return tipo;
    }

    public void setTipo(NodoBase tipo) {
        this.tipo = tipo;
    }

    public NodoBase getLis_asig() {
        return lis_asig;
    }

    public void setLis_asig(NodoBase lis_asig) {
        this.lis_asig = lis_asig;
    }
   
   
    
}
