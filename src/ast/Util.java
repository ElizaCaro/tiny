package ast;

public class Util {
	
	static int sangria = 0;
	
	//Imprimo en modo texto con sangrias el AST
	public static void imprimirAST(NodoBase raiz){
		  sangria+=2;
		  while (raiz != null) {
		    printSpaces();
		    if (raiz instanceof  NodoIf)
		    	System.out.println("If");
		    else if (raiz instanceof  NodoRepeat)
		    	System.out.println("Repeat");
                    else if (raiz instanceof NodoFor)
                        System.out.println("For");
                    else if (raiz instanceof NodoDeclaracion)
                        System.out.println("Declaracion");
		    else if (raiz instanceof NodoLeer)  
		    	System.out.println("Lectura: "+((NodoLeer)raiz).getIdentificador());
		    else if (raiz instanceof  NodoEscribir)
		    	System.out.println("Escribir");
                    else if (raiz instanceof NodoEstructura)
                          System.out.println("Estructura");
                    else if (raiz instanceof NodoFuncionRetorna){
                          System.out.println("Funcion con Retorno");                        }
                    else if (raiz instanceof NodoFuncionSinRetorna)
                          System.out.println("Funcion sin Retorno");
                    else if (raiz instanceof NodoParametro)
                          System.out.println("Parametro");
		    else 
                        if (raiz instanceof  NodoAsignacion)
                        {
                            if(((NodoAsignacion)raiz).getPosicion()!=null) //vector
                                System.out.println("Asignacion a: "+((NodoAsignacion)raiz).getIdentificador()+"["+((NodoAsignacion)raiz).getPosicion()+"]");            
                            else
                                System.out.println("Asignacion a: "+((NodoAsignacion)raiz).getIdentificador());
                        }
                    else if (raiz instanceof NodoCall)
                        {
                             if(((NodoCall)raiz).getArgumentos()!=null) //vector
                                System.out.println("Llamada a la funcion: "+((NodoCall)raiz).getIdentificador());            
                            else
                                System.out.println("Llamada a la funcion: "+((NodoCall)raiz).getIdentificador()+"("+")");
                        }
                   else if (raiz instanceof NodoOperacion
		    		|| raiz instanceof NodoValor
		    		|| raiz instanceof NodoIdentificador )
		    	imprimirNodo(raiz);
                   
                   else if (raiz instanceof NodoVariable)
                   {
                       if(((NodoVariable)raiz).getNumero()!=null)
                        System.out.println(((NodoVariable)raiz).getIdentificador()+"["+((NodoVariable)raiz).getNumero()+"]");
                       else
                           System.out.println(((NodoVariable)raiz).getIdentificador());
                   }
		    else System.out.println("Tipo de nodo desconocido");
		    
		    
                    /* Hago el recorrido recursivo */
                    
                    if  (raiz instanceof NodoEstructura){
                        printSpaces();
                        System.out.println("**Funcion**");
                        imprimirAST(((NodoEstructura)raiz).getFuncion());
                        printSpaces();
                        System.out.println("**Main**");
                        imprimirAST(((NodoEstructura)raiz).getBloque());
                    }
                    
                    if (raiz instanceof NodoFuncionRetorna){
                        printSpaces();
                        System.out.println("**Funcion con Retorno**");
                        imprimirAST(((NodoFuncionRetorna)raiz).getTipo());
                        
                        printSpaces();
                        System.out.println("  ID, "+((NodoFuncionRetorna)raiz).getIdentificador());
                       
                        if(((NodoFuncionRetorna)raiz).getParametros()!=null){
                            printSpaces();
                            System.out.println("parametros");
                            imprimirAST(((NodoFuncionRetorna)raiz).getParametros());
                        }
                        printSpaces();
                        System.out.println("cuerpo");
                        imprimirAST(((NodoFuncionRetorna)raiz).getSecuencias());
                        
                        printSpaces();
                        System.out.println("retorno");
                        imprimirAST(((NodoFuncionRetorna)raiz).getExpresion());
                    }
                    
                    if (raiz instanceof NodoFuncionSinRetorna){
                        printSpaces();
                        System.out.println("**Funcion sin Retorno**");
                        System.out.println("VOID");
                        
                        printSpaces();
                        System.out.println("  ID, "+((NodoFuncionSinRetorna)raiz).getIdentificador());
                        
                        if(((NodoFuncionSinRetorna)raiz).getParametros()!=null){
                            printSpaces();
                            System.out.println("parametros");
                            imprimirAST(((NodoFuncionSinRetorna)raiz).getParametros());
                        }
                        printSpaces();
                        System.out.println("cuerpo");
                        imprimirAST(((NodoFuncionSinRetorna)raiz).getSecuencias());
                        
                    }
                    
                    if (raiz instanceof NodoParametro){
                        printSpaces();
                        System.out.println("**Parametros**");
                        imprimirAST(((NodoParametro)raiz).getTipo());
                        
                        System.out.println("\tID, "+((NodoParametro)raiz).getIdentificador());
                    }
                    
                    if (raiz instanceof NodoCall){
                        printSpaces();
                        if(((NodoCall)raiz).getArgumentos()!=null)
                        System.out.println("**argumentos**");
                        imprimirAST(((NodoCall)raiz).getArgumentos());
                    }
                    
                    if  (raiz instanceof NodoDeclaracion){
                        printSpaces();
                        System.out.println("**Declaracion**");
                        imprimirAST(((NodoDeclaracion)raiz).getTipo());
                        imprimirAST(((NodoDeclaracion)raiz).getLis_asig());
                    }
                    
                    if (raiz instanceof  NodoIf){
		    	printSpaces();
		    	System.out.println("**Prueba IF**");
		    	imprimirAST(((NodoIf)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Then IF**");
		    	imprimirAST(((NodoIf)raiz).getParteThen());
		    	if(((NodoIf)raiz).getParteElse()!=null){
		    		printSpaces();
		    		System.out.println("**Else IF**");
		    		imprimirAST(((NodoIf)raiz).getParteElse());
		    	}
		    }
		    else if (raiz instanceof  NodoRepeat){
		    	printSpaces();
		    	System.out.println("**Cuerpo REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getCuerpo());
		    	printSpaces();
		    	System.out.println("**Prueba REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getPrueba());
		    }
                    else if (raiz instanceof NodoFor){
                        printSpaces();
                        System.out.println("**Asignacion For**");
                        imprimirAST(((NodoFor)raiz).getAsignacion());
                        printSpaces();
                        System.out.println("**Comprobacion FOR**");
                        imprimirAST(((NodoFor)raiz).getPrueba());
                        printSpaces();
                        System.out.println("**Acumulador FOR**");
                        imprimirAST(((NodoFor)raiz).getAcumulador());
                        printSpaces();
                        System.out.println("**Cuerpo FOR**");
                        imprimirAST(((NodoFor)raiz).getCuerpo());
                    
                    }
		    else 
                        
                    if (raiz instanceof  NodoAsignacion)
		    	imprimirAST(((NodoAsignacion)raiz).getExpresion());
		    else 
                        
                    if (raiz instanceof  NodoEscribir)
		    	imprimirAST(((NodoEscribir)raiz).getExpresion());
		    else 
                        
                    
                    if (raiz instanceof NodoOperacion){
		    	printSpaces();
		    	System.out.println("**Expr Izquierda Operacion**");
		    	imprimirAST(((NodoOperacion)raiz).getOpIzquierdo());
		    	printSpaces();
		    	System.out.println("**Expr Derecha Operacion**");		    	
		    	imprimirAST(((NodoOperacion)raiz).getOpDerecho());
		    }
		    raiz = raiz.getHermanoDerecha();
		  }
		  sangria-=2;
		}

/* Imprime espacios con sangria */
static void printSpaces()
{ int i;
  for (i=0;i<sangria;i++)
	  System.out.print(" ");
}

/* Imprime informacion de los nodos */
static void imprimirNodo( NodoBase raiz )
{
	if(	raiz instanceof NodoRepeat
		||	raiz instanceof NodoLeer
		||	raiz instanceof NodoEscribir  ){
		System.out.println("palabra reservada: "+ raiz.getClass().getName());
	}
	
	if(	raiz instanceof NodoAsignacion )
		System.out.println(":=");
	
	if(	raiz instanceof NodoOperacion ){
		tipoOp sel=((NodoOperacion) raiz).getOperacion();
		if(sel==tipoOp.menor)
			System.out.println("<"); 
		if(sel==tipoOp.igual)
			System.out.println("=");
		if(sel==tipoOp.mas)
			System.out.println("+");
		if(sel==tipoOp.menos)
			System.out.println("-");
		if(sel==tipoOp.por)
			System.out.println("*");
		if(sel==tipoOp.entre)
			System.out.println("/");
                if(sel==tipoOp.mayor)
			System.out.println(">");
                if(sel==tipoOp.mayorigual)
			System.out.println(">=");
                if(sel==tipoOp.menorigual)
			System.out.println("<=");
                if(sel==tipoOp.diferente)
			System.out.println("!=");
                if(sel==tipoOp.and)
			System.out.println("AND");
                if(sel==tipoOp.or)
			System.out.println("OR");
                
	}

	if(	raiz instanceof NodoValor ){
            if(((NodoValor)raiz).getVint() != null)
                System.out.println("NUM, val= "+ ((NodoValor)raiz).getVint());
            if(((NodoValor)raiz).isVbol()!= null)
                System.out.println("BOL, val= "+ ((NodoValor)raiz).isVbol());
	}

	if(	raiz instanceof NodoIdentificador ){
		System.out.println("ID, nombre= "+ ((NodoIdentificador)raiz).getNombre());
	}

}


}
