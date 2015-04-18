package ast;

public class NodoFor extends NodoBase{
    private NodoBase asignacion;
    private NodoBase prueba;
    private NodoBase acumulador;
    private NodoBase cuerpo;

    public NodoFor(NodoBase asignacion, NodoBase prueba, NodoBase acumulador, NodoBase cuerpo) {
        this.asignacion = asignacion;
        this.prueba = prueba;
        this.acumulador = acumulador;
        this.cuerpo = cuerpo;
    }

    public NodoFor() {
        super();
        this.asignacion = null;
        this.prueba = null;
        this.acumulador = null;
        this.cuerpo = null;
    }

    public NodoBase getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(NodoBase asignacion) {
        this.asignacion = asignacion;
    }

    public NodoBase getPrueba() {
        return prueba;
    }

    public void setPrueba(NodoBase prueba) {
        this.prueba = prueba;
    }

    public NodoBase getAcumulador() {
        return acumulador;
    }

    public void setAcumulador(NodoBase acumulador) {
        this.acumulador = acumulador;
    }

    public NodoBase getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(NodoBase cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
    
    
    
}
