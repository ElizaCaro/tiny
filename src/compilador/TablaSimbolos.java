package compilador;

import ast.*;
import java.util.*;

public class TablaSimbolos {
	private HashMap<String, RegistroSimbolo> tabla;
	private int direccion;  //Contador de las localidades de memoria asignadas a la tabla
	private int ambito;
        private String tipo;
        private String Clasificacion;
        private int pos;
        
	public TablaSimbolos() {
		super();
		tabla = new HashMap<String, RegistroSimbolo>();
		direccion=0;
                ambito = 0;
                tipo = "";
                Clasificacion = "";
                pos = 0;
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
                pos = 0;
                tipo = BuscarTipo(((NodoFuncionRetorna)raiz).getTipo());
                Clasificacion = "FUN";
                InsertarSimbolo(((NodoFuncionRetorna)raiz).getIdentificador(), direccion);
                
                if(((NodoFuncionRetorna)raiz).getParametros()!=null){
                    cargarTabla(((NodoFuncionRetorna)raiz).getParametros());
                }
                
                Clasificacion = "VAR";
                pos = 0;
                cargarTabla(((NodoFuncionRetorna)raiz).getSecuencias());
                cargarTabla(((NodoFuncionRetorna)raiz).getExpresion());
                
            }else
            
            if  (raiz instanceof NodoFuncionSinRetorna){
                ambito++;
                pos = 0;
                tipo = "VOID";
                Clasificacion = "FUN";
                InsertarSimbolo(((NodoFuncionSinRetorna)raiz).getIdentificador(), direccion);
                tipo = "";
                
                
               if(((NodoFuncionSinRetorna)raiz).getParametros()!=null){
                   cargarTabla(((NodoFuncionSinRetorna)raiz).getParametros());
                }
                Clasificacion = "VAR";
                pos = 0;
                cargarTabla(((NodoFuncionSinRetorna)raiz).getSecuencias());
               
            }else
            
            if  (raiz instanceof NodoParametro){
                tipo = BuscarTipo(((NodoParametro)raiz).getTipo());
                Clasificacion = "PFUN";
                pos++;
                
                InsertarSimbolo(((NodoParametro)raiz).getIdentificador(), direccion);
            }else
            
            if (raiz instanceof NodoCall){
                InsertarSimbolo(((NodoCall)raiz).getIdentificador(), direccion);
                cargarTabla(((NodoCall)raiz).getArgumentos());
            }else
            
            if  (raiz instanceof NodoDeclaracion){
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
            tipo = "";
	}
	
	//true es nuevo no existe se insertara, false ya existe NO se vuelve a insertar 
	public boolean InsertarSimbolo(String ident, int numLinea){
		RegistroSimbolo simbolo;
                
                String identificador = new String(ident+" "+ambito);
                
                if(tipo != ""){
                    if(tabla.containsKey(identificador) && BuscarSimbolo(identificador).getAmbito() == ambito){
		        return false;
                    }else{
                    
                        simbolo= new RegistroSimbolo(ident,numLinea,direccion++,ambito,tipo, Clasificacion,pos);
			tabla.put(identificador,simbolo);
                        
                        System.out.print("\t"+tipo);
                        System.out.print("\t\t"+ident);
                        System.out.print("\t\t\t"+ambito);
                        System.out.print("\t\t"+direccion);
                        System.out.print("\t\t"+Clasificacion);
                        System.out.println("\t\t"+pos);
                        return true;		
                     }
                }else
                    return false;
        }
	
	public RegistroSimbolo BuscarSimbolo(String identificador){
		RegistroSimbolo simbolo=(RegistroSimbolo)tabla.get(identificador);
		return simbolo;
	}
	
	public void ImprimirClaves(){
		System.out.println("*** Tabla de Simbolos ***");
		for( Iterator <String>it = tabla.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
	    System.out.print("Tipo: "+BuscarSimbolo(s).getTipo());
            System.out.print("\t Identificador: "+BuscarSimbolo(s).getIdentificador());
            System.out.println("\t\t Ambito: "+BuscarSimbolo(s).getAmbito());
		}
	}

	public int getDireccion(String Clave){
		return BuscarSimbolo(Clave).getDireccionMemoria();
	}
        
        
	
	/*
	 * TODO:
	 * 1. Crear lista con las lineas de codigo donde la variable es usada.
	 * */

    public boolean getParametros(int ambito,String tipoCompara, int p) {
        String aux = "";
        System.out.println("Ambito: "+ambito+" Com: "+tipoCompara);
        
        
        for (Iterator <String>it = tabla.keySet().iterator(); it.hasNext();){
            String s = (String)it.next();
            
            if( BuscarSimbolo(s).getAmbito() == ambito && "PFUN".equals(BuscarSimbolo(s).getClasificacion()) && BuscarSimbolo(s).getTipo() == tipoCompara && BuscarSimbolo(s).getPos_Parametro() == p){
                System.out.println(BuscarSimbolo(s).getIdentificador()+" pos: "+BuscarSimbolo(s).getPos_Parametro());
                return true;
            }
         }
        return false;
    }
}
