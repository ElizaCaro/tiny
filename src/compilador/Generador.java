package compilador;

import ast.*;

public class Generador {
	/* Ilustracion de la disposicion de la memoria en
	 * este ambiente de ejecucion para el lenguaje Tiny
	 *
	 * |t1	|<- mp (Maxima posicion de memoria de la TM
	 * |t1	|<- desplazamientoTmp (tope actual)
	 * |free|
	 * |free|
	 * |...	|
	 * |x	|
	 * |y	|<- gp
	 * 
	 * */
	
	
	
	/* desplazamientoTmp es una variable inicializada en 0
	 * y empleada como el desplazamiento de la siguiente localidad
	 * temporal disponible desde la parte superior o tope de la memoria
	 * (la que apunta el registro MP).
	 * 
	 * - Se decrementa (desplazamientoTmp--) despues de cada almacenamiento y
	 * 
	 * - Se incrementa (desplazamientoTmp++) despues de cada eliminacion/carga en 
	 *   otra variable de un valor de la pila.
	 * 
	 * Pudiendose ver como el apuntador hacia el tope de la pila temporal
	 * y las llamadas a la funcion emitirRM corresponden a una inserccion 
	 * y extraccion de esta pila
	 */
	private static int desplazamientoTmp = 0;
	private static TablaSimbolos tablaSimbolos = null;
        private static int ambito = 0;
        private static int ambitoAux = 0;
        private static int main; 
        private static int indice = 0;
        private static int indiceSup = 0;
	private static int []Saltos;
        private static int []Comienzo;
        private static boolean bandReturn = false;
        private static boolean bandCall = false;
        private static boolean bandOperacion = false;
        
        
	public static void setTablaSimbolos(TablaSimbolos tabla,int a){
		tablaSimbolos = tabla;
        }
	
	public static void generarCodigoObjeto(NodoBase raiz){
                Saltos = new int[tablaSimbolos.getAmbito()-1];
                Comienzo = new int[tablaSimbolos.getAmbito()-1];
                System.out.println();
		System.out.println();
		System.out.println("------ CODIGO OBJETO DEL LENGUAJE TINY GENERADO PARA LA TM ------");
		System.out.println();
		System.out.println();
		generarPreludioEstandar();
		generar(raiz);
		/*Genero el codigo de finalizacion de ejecucion del codigo*/   
		UtGen.emitirComentario("Fin de la ejecucion.");
		UtGen.emitirRO("HALT", 0, 0, 0, "");
                UtGen.setInstruccionActual(2);
                UtGen.emitirRM("LDA", UtGen.PC, main, UtGen.GP, "Salto Inicial al Main");
		System.out.println();
		System.out.println();
		System.out.println("------ FIN DEL CODIGO OBJETO DEL LENGUAJE TINY GENERADO PARA LA TM ------");
	}
	
	//Funcion principal de generacion de codigo
	//prerequisito: Fijar la tabla de simbolos antes de generar el codigo objeto 
	private static void generar(NodoBase nodo){
	if(tablaSimbolos!=null){
                if (nodo instanceof NodoEstructura){
                          generarEstructura(nodo);
                }else if(nodo instanceof NodoFuncionRetorna){
                          generarFuncionRetorna(nodo);
                }else if(nodo instanceof NodoFuncionSinRetorna){
                         generarFuncionSinRetorna(nodo);
                }else if (nodo instanceof  NodoIf){
			generarIf(nodo);
		}else if (nodo instanceof  NodoRepeat){
			generarRepeat(nodo);
		}else if (nodo instanceof NodoFor){
                        generarFor(nodo);
                }else if (nodo instanceof  NodoAsignacion){
			generarAsignacion(nodo);
		}else if (nodo instanceof  NodoLeer){
			generarLeer(nodo);
		}else if (nodo instanceof  NodoEscribir){
			generarEscribir(nodo);
		}else if (nodo instanceof NodoValor){
			generarValor(nodo);
		}else if (nodo instanceof NodoIdentificador){
			generarIdentificador(nodo);
		}else if (nodo instanceof NodoOperacion){
			generarOperacion(nodo);
		}else if (nodo instanceof NodoVariable){
			generarVariable(nodo);
		}else if (nodo instanceof NodoDeclaracion){
			generarDeclaracion(nodo);
		}else if (nodo instanceof NodoCall){
                         generarCall(nodo);
                }else if (nodo instanceof NodoParametro)
                        generarParametros(nodo);
                else{
			System.out.println("BUG: Tipo de nodo a generar desconocido");
		}
		/*Si el hijo de extrema izquierda tiene hermano a la derecha lo genero tambien*/
		if(nodo.TieneHermano())
			generar(nodo.getHermanoDerecha());
	}else
		System.out.println("���ERROR: por favor fije la tabla de simbolos a usar antes de generar codigo objeto!!!");
}
        
        private static void generarEstructura(NodoBase nodo){
            NodoEstructura n = (NodoEstructura)nodo;
            
            if(UtGen.debug) UtGen.emitirComentario("--> Estructura");
                generar(n.getFuncion());
                
                ambito++;
                main = UtGen.getInstruccionActual();
                //System.out.println("Main: "+main+" ambito: "+ambito);
                generar(n.getBloque());   
            if(UtGen.debug) UtGen.emitirComentario("<--Fin Estructura");
            
            
        }
        
        private static void generarFuncionRetorna(NodoBase nodo){
            NodoFuncionRetorna n = (NodoFuncionRetorna)nodo;
            int RegFun = UtGen.getInstruccionActual(); 
            Comienzo[ambito] = RegFun;
            
            ambito++;
           // System.out.println("Inicio de "+n.getIdentificador()+": "+RegFun);
            int direccion = tablaSimbolos.getDireccion(n.getIdentificador()+" "+ambito);
            //UtGen.emitirRM("LDC",UtGen.AC, RegFun+2,UtGen.GP ,"Constante de inicio de funcion");
            //UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Almaceno Direccion de Inicio de: "+n.getIdentificador());
            
            if(UtGen.debug) UtGen.emitirComentario("--> Funcion "+n.getIdentificador());
            
            if(n.getParametros() != null){
                if(UtGen.debug) UtGen.emitirComentario("--->Parametros de: "+n.getIdentificador());
                indice = 0;
                indiceSup = 0;
                generar(n.getParametros());
                
            }
            
            if(UtGen.debug) UtGen.emitirComentario("===>Secuencia");
                generar(n.getSecuencias());
            
            if(UtGen.debug){ 
                UtGen.emitirComentario("===>RETURN");
                bandReturn = true;
                generar(n.getExpresion());
            }
                
            
            Saltos[ambito-1]= UtGen.getInstruccionActual()-1;
            //System.out.println("salida: "+Saltos[0]);
            if(UtGen.debug) UtGen.emitirComentario("<-- Funcion "+n.getIdentificador());
            
            UtGen.emitirRM("LDA", UtGen.PC, 0, UtGen.AC3 ,"Salto donde lo llamo");
            
        }
        
        private static void generarFuncionSinRetorna(NodoBase nodo){
            NodoFuncionSinRetorna n = (NodoFuncionSinRetorna)nodo;
            int RegFun = UtGen.getInstruccionActual();
            Comienzo[ambito] = RegFun;
            ambito++;
                   // System.out.println("Inicio de "+n.getIdentificador()+": "+RegFun);
            
            //int direccion = tablaSimbolos.getDireccion(n.getIdentificador()+" "+ambito);
            //UtGen.emitirRM("LDC",UtGen.AC, RegFun+2,UtGen.GP ,"Constante de inicio de funcion");
            //UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Almaceno Direccion de Inicio de: "+n.getIdentificador());
                
            
            if(UtGen.debug) UtGen.emitirComentario("--> Funcion "+n.getIdentificador());
            
            if(n.getParametros() != null){
                if(UtGen.debug) UtGen.emitirComentario("--->Parametros de: "+n.getIdentificador());
                indice = 0;
                indiceSup = 0;
                generar(n.getParametros());
            }
            
            if(UtGen.debug) UtGen.emitirComentario("===>Secuencia");
            generar(n.getSecuencias());
            
            Saltos[ambito-1]= UtGen.getInstruccionActual()-1;
            //System.out.println("salida: "+Saltos[1]);
            UtGen.emitirRM("LDA", UtGen.PC, 0, UtGen.AC3 ,"Salto donde lo llamo");
        }
        
        private static void generarParametros(NodoBase nodo){
            NodoParametro n = (NodoParametro)nodo;
           
            if(UtGen.debug) UtGen.emitirComentario("--->Identificador: "+n.getIdentificador());
            int direccion = tablaSimbolos.getDireccion(n.getIdentificador()+" "+ambito);
            UtGen.emitirRM("LD", UtGen.AC, indiceSup--, UtGen.MP, "Extrae el valor pasado por parametros para: "+n.getIdentificador());
            UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Almaceno Valor de Constante "+n.getIdentificador());
            indice++;
             
        }
        
        private static void generarDeclaracion(NodoBase nodo){
            NodoDeclaracion n = (NodoDeclaracion)nodo;
            if(UtGen.debug) UtGen.emitirComentario("--->Declarada: ");
            generar(n.getLis_asig());
        }
        
        private static void generarVariable (NodoBase nodo){
            NodoVariable n = (NodoVariable)nodo;
            if(UtGen.debug) UtGen.emitirComentario("Variables: "+n.getIdentificador());
        }
        
        
	private static void generarIf(NodoBase nodo){
    	NodoIf n = (NodoIf)nodo;
		int localidadSaltoElse,localidadSaltoEnd,localidadActual;
		if(UtGen.debug)	UtGen.emitirComentario("-> if");
		/*Genero el codigo para la parte de prueba del IF*/
		generar(n.getPrueba());
		localidadSaltoElse = UtGen.emitirSalto(1);
		UtGen.emitirComentario("If: el salto hacia el else debe estar aqui");
		/*Genero la parte THEN*/
		generar(n.getParteThen());
		localidadSaltoEnd = UtGen.emitirSalto(1);
		UtGen.emitirComentario("If: el salto hacia el final debe estar aqui");
		localidadActual = UtGen.emitirSalto(0);
		UtGen.cargarRespaldo(localidadSaltoElse);
		UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadActual, "if: jmp hacia else");
		UtGen.restaurarRespaldo();
		/*Genero la parte ELSE*/
		if(n.getParteElse()!=null){
			generar(n.getParteElse());
                }
		//igualmente debo generar la sentencia que reserve en
		//localidadSaltoEnd al finalizar la ejecucion de un true
		localidadActual = UtGen.emitirSalto(0);
		UtGen.cargarRespaldo(localidadSaltoEnd);
		UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadActual, "if: jmp hacia el final");
		UtGen.restaurarRespaldo();			
		if(UtGen.debug)	UtGen.emitirComentario("<- if");
	}
	
        private static void generarFor(NodoBase nodo){
            NodoFor n = (NodoFor)nodo;
                   
		if(UtGen.debug)	UtGen.emitirComentario("-> for");
                    int localidadSaltoInicio, localidadSaltoFinal, localidadActual;
                    generar(n.getAsignacion());
                    localidadSaltoInicio = UtGen.emitirSalto(0);
                    generar(n.getPrueba());
                    localidadSaltoFinal = UtGen.emitirSalto(1);
                    generar(n.getCuerpo());
                    generar(n.getAcumulador());
                    UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadSaltoInicio, "Inicio For");
                    localidadActual = UtGen.emitirSalto(0);
                    UtGen.cargarRespaldo(localidadSaltoFinal);
                    UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadActual, "Salto al Inicio del For");
                    UtGen.restaurarRespaldo();
		if(UtGen.debug)	UtGen.emitirComentario("<- for");
        }
        
        private static void generarCall(NodoBase nodo){
            NodoCall n = (NodoCall)nodo;
            
            for (int i = 0; i < tablaSimbolos.getAmbito(); i++) {
                if(tablaSimbolos.BuscarSimbolo(n.getIdentificador()+" "+i)!=null){
                   int direccion = tablaSimbolos.BuscarSimbolo(n.getIdentificador()+" "+i).getDireccionMemoria();
                   
                   UtGen.emitirRM("LDC", UtGen.AC3, UtGen.getInstruccionActual()+(tablaSimbolos.tamano(i)*2)+2, UtGen.GP, "Linea de Retorno");
                        
                        if(n.getArgumentos()!=null){
                            bandCall = true;
                            ambitoAux = i;
                            indice = 0;
                            
                           
                            generar(n.getArgumentos());
                            bandCall = false;
                            bandOperacion = false;
                            
                        }
                   
                   UtGen.emitirRM("LDA", UtGen.PC, Comienzo[i-1], UtGen.GP,"Salto a la funcion");
                   break;
                }
            }
            
        }
        
	private static void generarRepeat(NodoBase nodo){
    	NodoRepeat n = (NodoRepeat)nodo;
		int localidadSaltoInicio;
		if(UtGen.debug)	UtGen.emitirComentario("-> repeat");
			localidadSaltoInicio = UtGen.emitirSalto(0);
			UtGen.emitirComentario("repeat: el salto hacia el final (luego del cuerpo) del repeat debe estar aqui");
			/* Genero el cuerpo del repeat */
			generar(n.getCuerpo());
			/* Genero el codigo de la prueba del repeat */
			generar(n.getPrueba());
			UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadSaltoInicio, "repeat: jmp hacia el inicio del cuerpo");
		if(UtGen.debug)	UtGen.emitirComentario("<- repeat");
	}		
	
	private static void generarAsignacion(NodoBase nodo){
		NodoAsignacion n = (NodoAsignacion)nodo;
		int direccion;
		if(UtGen.debug)	UtGen.emitirComentario("-> asignacion");		
		/* Genero el codigo para la expresion a la derecha de la asignacion */
		generar(n.getExpresion());
		/* Ahora almaceno el valor resultante */
		direccion = tablaSimbolos.getDireccion(n.getIdentificador()+" "+ambito);
		UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "asignacion: almaceno el valor para el id "+n.getIdentificador());
		if(UtGen.debug)	UtGen.emitirComentario("<- asignacion");
	}
	
	private static void generarLeer(NodoBase nodo){
		NodoLeer n = (NodoLeer)nodo;
		int direccion;
		if(UtGen.debug)	UtGen.emitirComentario("-> leer");
		UtGen.emitirRO("IN", UtGen.AC, 0, 0, "leer: lee un valor entero ");
		direccion = tablaSimbolos.getDireccion(n.getIdentificador()+" "+ambito);
		UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "leer: almaceno el valor entero leido en el id "+n.getIdentificador());
		if(UtGen.debug)	UtGen.emitirComentario("<- leer");
	}
	
	private static void generarEscribir(NodoBase nodo){
		NodoEscribir n = (NodoEscribir)nodo;
		if(UtGen.debug)	UtGen.emitirComentario("-> escribir");
		/* Genero el codigo de la expresion que va a ser escrita en pantalla */
		generar(n.getExpresion());
		/* Ahora genero la salida */
		UtGen.emitirRO("OUT", UtGen.AC, 0, 0, "escribir: genero la salida de la expresion");
		if(UtGen.debug)	UtGen.emitirComentario("<- escribir");
	}
	
	private static void generarValor(NodoBase nodo){
    	NodoValor n = (NodoValor)nodo;
    	if(UtGen.debug)	UtGen.emitirComentario("-> constante");
    	UtGen.emitirRM("LDC", UtGen.AC, n.getVint(), 0, "cargar constante: "+n.getVint().toString());
    	if(UtGen.debug)	UtGen.emitirComentario("<- constante");
	}
	
	private static void generarIdentificador(NodoBase nodo){
		NodoIdentificador n = (NodoIdentificador)nodo;
		if(UtGen.debug)	UtGen.emitirComentario("-> identificador");
		int direccion = tablaSimbolos.getDireccion(n.getNombre()+" "+ambito);
               
                if(bandCall == true){
                   // System.out.println("tamaño: "+tablaSimbolos.tamano(ambito));
                    UtGen.emitirRM("LD", UtGen.AC, direccion, UtGen.GP, "cargar valor de identificador: "+n.getNombre());
                   //System.out.println("desp: "+desplazamientoTmp);
                   UtGen.emitirRM("ST", UtGen.AC,indiceSup--, UtGen.MP, "cargar valor de identificador: "+n.getNombre());
                    indice++;   
                    
                }else
                    UtGen.emitirRM("LD", UtGen.AC, direccion, UtGen.GP, "cargar valor de identificador: "+n.getNombre());
                
                
                if(UtGen.debug)	UtGen.emitirComentario("-> identificador");
	}

	private static void generarOperacion(NodoBase nodo){
		NodoOperacion n = (NodoOperacion) nodo;
		if(UtGen.debug)	UtGen.emitirComentario("-> Operacion: " + n.getOperacion());
		
                bandOperacion = true;
                indiceSup = desplazamientoTmp;
                
		generar(n.getOpIzquierdo());
		/* Almaceno en la pseudo pila de valor temporales el valor de la operacion izquierda */
                
		UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "op: push en la pila tmp el resultado expresion izquierda");
		/* Genero la expresion derecha de la operacion */
		generar(n.getOpDerecho());
		/* Ahora cargo/saco de la pila el valor izquierdo */
		UtGen.emitirRM("LD", UtGen.AC1, ++desplazamientoTmp, UtGen.MP, "op: pop o cargo de la pila el valor izquierdo en AC1");
                
		switch(n.getOperacion()){
                    case or:
                        case	mas:	UtGen.emitirRO("ADD", UtGen.AC, UtGen.AC1, UtGen.AC, "op: +");		
							break;
			case	menos:	UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: -");
                                                        break;
                    case and:				
			case	por:	UtGen.emitirRO("MUL", UtGen.AC, UtGen.AC1, UtGen.AC, "op: *");
							break;
			case	entre:	UtGen.emitirRO("DIV", UtGen.AC, UtGen.AC1, UtGen.AC, "op: /");
							break;		
			case	menor:	UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: <");
							UtGen.emitirRM("JLT", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC<0)");
							UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
							UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
							UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                                                        break;
                        case	menorigual:	UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: <=");
							UtGen.emitirRM("JLE", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC<0)");
							UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
							UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
							UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
							break;
                            
                        case	mayorigual:	UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: >=");
							UtGen.emitirRM("JGE", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC<0)");
							UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
							UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
							UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                                                        break;
                        case    mayor:  UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: >");
                                                        UtGen.emitirRM("JGT", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla");
                                                        UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
							UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
							UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                                                        break;
			case	igual:	UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: ==");
							UtGen.emitirRM("JEQ", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC==0)");
							UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
							UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
							UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
							break;	
                        case	diferente:  UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: !=");
							UtGen.emitirRM("JNE", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC==0)");
							UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
							UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
							UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
							break;	
                            
                        default:
							UtGen.emitirComentario("BUG: tipo de operacion desconocida");
		}
		if(UtGen.debug)	UtGen.emitirComentario("<- Operacion: " + n.getOperacion());
	}
	
	//TODO: enviar preludio a archivo de salida, obtener antes su nombre
	private static void generarPreludioEstandar(){
		UtGen.emitirComentario("Compilacion TINY para el codigo objeto TM");
		UtGen.emitirComentario("Archivo: "+ "NOMBRE_ARREGLAR");
		/*Genero inicializaciones del preludio estandar*/
		/*Todos los registros en tiny comienzan en cero*/
		UtGen.emitirComentario("Preludio estandar:");
		UtGen.emitirRM("LD", UtGen.MP, 0, UtGen.AC, "cargar la maxima direccion desde la localidad 0");
		UtGen.emitirRM("ST", UtGen.AC, 0, UtGen.AC, "limpio el registro de la localidad 0");
                
                UtGen.setInstruccionActual(3);
                
	}

}
