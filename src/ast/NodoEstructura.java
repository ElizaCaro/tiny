
package ast;


public class NodoEstructura extends NodoBase{
    private NodoBase funcion;
    private NodoBase bloque;

    public NodoEstructura(NodoBase funcion, NodoBase bloque) {
        super();
        this.funcion = funcion;
        this.bloque = bloque;
    }
    
    public NodoEstructura() {
        super();
        this.funcion = null;
        this.bloque = null;
    }    

    public NodoBase getFuncion() {
        return funcion;
    }

    public void setFuncion(NodoBase funcion) {
        this.funcion = funcion;
    }

    public NodoBase getBloque() {
        return bloque;
    }

    public void setBloque(NodoBase bloque) {
        this.bloque = bloque;
    }
    
    
    
}
