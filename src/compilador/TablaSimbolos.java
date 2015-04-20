package compilador;

import ast.NodoAsignacion;
import ast.NodoBase;
import ast.NodoCall;
import ast.NodoDeclaracion;
import ast.NodoEscribir;
import ast.NodoEstructura;
import ast.NodoFor;
import ast.NodoFuncionRetorna;
import ast.NodoFuncionSinRetorna;
import ast.NodoIdentificador;
import ast.NodoIf;
import ast.NodoLeer;
import ast.NodoOperacion;
import ast.NodoParametro;
import ast.NodoRepeat;
import ast.NodoVariable;
import java.util.*;

public class TablaSimbolos {
	private HashMap<String, RegistroSimbolo> tabla;
	private int direccion;  //Contador de las localidades de memoria asignadas a la tabla
	private int ambito;
        private String tipo;
        
	public TablaSimbolos() {
		super();
		tabla = new HashMap<String, RegistroSimbolo>();
		direccion=0;
                ambito = 0;
                tipo = "";
	}
        
        public String BuscarTipo(NodoBase raiz){
            String tipo ="";
                    if(raiz instanceof NodoIdentificador)
                        tipo = ((NodoIdentificador)raiz).getNombre();
            return tipo;
        }
       
	public void cargarTabla(NodoBase raiz){
		while (raiz != null) {
	    if (raiz instanceof NodoIdentificador){
                InsertarSimbolo(((NodoIdentificador)raiz).getNombre(),-1);
	    	//TODO: A�adir el numero de linea y localidad de memoria correcta
	    }

	    /* Hago el recorrido recursivo */
            
            if  (raiz instanceof NodoEstructura){
                cargarTabla(((NodoEstructura)raiz).getFuncion());
                
                ambito++;
                cargarTabla(((NodoEstructura)raiz).getBloque());
            }else
            
            if  (raiz instanceof NodoFuncionRetorna){
                ambito++;
                
                //cargarTabla(((NodoFuncionRetorna)raiz).getTipo());
                tipo = BuscarTipo(((NodoFuncionRetorna)raiz).getTipo());
                InsertarSimbolo(((NodoFuncionRetorna)raiz).getIdentificador(), direccion);
                
                if(((NodoFuncionRetorna)raiz).getParametros()!=null)
                    cargarTabla(((NodoFuncionRetorna)raiz).getParametros());
                
                cargarTabla(((NodoFuncionRetorna)raiz).getSecuencias());
                cargarTabla(((NodoFuncionRetorna)raiz).getExpresion());
            }else
            
            if  (raiz instanceof NodoFuncionSinRetorna){
                ambito++;
                tipo = "VOID";
                InsertarSimbolo(((NodoFuncionSinRetorna)raiz).getIdentificador(), direccion);
                
               if(((NodoFuncionSinRetorna)raiz).getParametros()!=null)
                    cargarTabla(((NodoFuncionSinRetorna)raiz).getParametros());
                
                cargarTabla(((NodoFuncionSinRetorna)raiz).getSecuencias());
            }else
            
            if  (raiz instanceof NodoParametro){
                InsertarSimbolo(((NodoParametro)raiz).getIdentificador(), direccion);
            }else
            
            if (raiz instanceof NodoCall){
                InsertarSimbolo(((NodoCall)raiz).getIdentificador(), direccion);
                cargarTabla(((NodoCall)raiz).getArgumentos());
            }else
            
            if  (raiz instanceof NodoDeclaracion){
               // cargarTabla(((NodoDeclaracion)raiz).getTipo());
                tipo = BuscarTipo(((NodoDeclaracion)raiz).getTipo());
                cargarTabla(((NodoDeclaracion)raiz).getLis_asig());
            }else
                
            if  (raiz instanceof NodoVariable){
                 InsertarSimbolo(((NodoVariable)raiz).getIdentificador(), direccion);
            }else
            
            if (raiz instanceof  NodoIf){
	    	cargarTabla(((NodoIf)raiz).getPrueba());
	    	cargarTabla(((NodoIf)raiz).getParteThen());
	    	if(((NodoIf)raiz).getParteElse()!=null){
	    		cargarTabla(((NodoIf)raiz).getParteElse());
	    	}
	    }else 
                
            if (raiz instanceof  NodoRepeat){
	    	cargarTabla(((NodoRepeat)raiz).getCuerpo());
	    	cargarTabla(((NodoRepeat)raiz).getPrueba());
	    }else 
            
            if  (raiz instanceof NodoFor){
                cargarTabla(((NodoFor)raiz).getAsignacion());
                cargarTabla(((NodoFor)raiz).getPrueba());
                cargarTabla(((NodoFor)raiz).getAcumulador());
                cargarTabla(((NodoFor)raiz).getCuerpo());
            }else 
                
            if (raiz instanceof  NodoAsignacion){
                InsertarSimbolo(((NodoAsignacion)raiz).getIdentificador(), direccion);
	    	cargarTabla(((NodoAsignacion)raiz).getExpresion());
            }else 
                
            if (raiz instanceof  NodoEscribir)
	    	cargarTabla(((NodoEscribir)raiz).getExpresion());
	    else 
                
            if (raiz instanceof  NodoLeer)
	    	InsertarSimbolo(((NodoLeer)raiz).getIdentificador(),direccion);
	    else 
                
            if (raiz instanceof NodoOperacion){
	    	cargarTabla(((NodoOperacion)raiz).getOpIzquierdo());
	    	cargarTabla(((NodoOperacion)raiz).getOpDerecho());
	    }
	    raiz = raiz.getHermanoDerecha();
	  }
	}
	
	//true es nuevo no existe se insertara, false ya existe NO se vuelve a insertar 
	public boolean InsertarSimbolo(String identificador, int numLinea){
		RegistroSimbolo simbolo;
		if(tabla.containsKey(identificador)){
			return false;
		}else{
			simbolo= new RegistroSimbolo(identificador,numLinea,direccion++,ambito,tipo);
			tabla.put(identificador,simbolo);
			return true;			
		}
	}
	
	public RegistroSimbolo BuscarSimbolo(String identificador){
		RegistroSimbolo simbolo=(RegistroSimbolo)tabla.get(identificador);
		return simbolo;
	}
	
	public void ImprimirClaves(){
		System.out.println("*** Tabla de Simbolos ***");
		for( Iterator <String>it = tabla.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
	    System.out.println("Consegui Key: "+s+" con direccion: " + BuscarSimbolo(s).getDireccionMemoria()+" ambito: "+BuscarSimbolo(s).getAmbito()+" tipo: "+BuscarSimbolo(s).getTipo());
		}
	}

	public int getDireccion(String Clave){
		return BuscarSimbolo(Clave).getDireccionMemoria();
	}
	
	/*
	 * TODO:
	 * 1. Crear lista con las lineas de codigo donde la variable es usada.
	 * */
}
