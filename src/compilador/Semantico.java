package compilador;
import ast.*;

public class Semantico {
    private TablaSimbolos tablaSimbolos;
    private int ambito;
    public boolean debug = true;
    public String tipoCompara ="";
    int Numeros;
    boolean booleanos;
    boolean bandNumero = false;
    boolean bandBoleam = false;
    boolean prueba = false;
    boolean bandComprobacion = false;
    boolean BandOperadores = false;
    boolean BandAsig = false;
    boolean BandRetorno = false;
    
    public Semantico(TablaSimbolos tablaSimbolos) {
        super();
        this.tablaSimbolos = tablaSimbolos;
        ambito = 0;
    }

    public void recorrerArbol(NodoBase raiz){
            
        while (raiz != null) {
                
            //recursividad
            if (raiz instanceof NodoEstructura){
                recorrerArbol(((NodoEstructura)raiz).getFuncion());
                ambito++;
                recorrerArbol(((NodoEstructura)raiz).getBloque());
            }else
            
            if (raiz instanceof NodoFuncionRetorna){
                    ambito++;
                    String id = ((NodoFuncionRetorna)raiz).getIdentificador();
                    String tipo ="";
                            
                    if(tablaSimbolos.BuscarSimbolo(id+" "+ambito)!= null)
                        tipo = tablaSimbolos.BuscarSimbolo(id+" "+ambito).getTipo();
                   
                    BandRetorno = true;
                    recorrerArbol(((NodoFuncionRetorna)raiz).getExpresion());
                    
                    //System.out.println("bb: "+bandBoleam+" t. "+tipo+" tc: "+tipoCompara);
                    
                    if(bandBoleam == true && tipo != tipoCompara){
                         System.err.println("******* Tipos Incompatibles ********");
                         System.exit(0);
                    }else
                    
                    if(bandBoleam == false){
                        if(tipoCompara == ""){
                            System.err.println("******* Expresion de Retorno no Declarada ******");
                            System.exit(0);
                        }else if(tipo != tipoCompara){
                            System.err.println("******* Tipos Incompatibles********");
                            System.exit(0);
                        }else
                            tipoCompara="";
                    }
                    
                        bandBoleam = false;
                        BandRetorno = false;
                        tipoCompara = "";
                    //System.out.println("recorrer");
                    recorrerArbol(((NodoFuncionRetorna)raiz).getSecuencias());
            }else
                
            if  (raiz instanceof NodoFuncionSinRetorna){
                ambito++;
                String id = ((NodoFuncionSinRetorna)raiz).getIdentificador();
                String tipo = "";
                if(tablaSimbolos.BuscarSimbolo(id+" "+ambito)!= null)
                    tipo = tablaSimbolos.BuscarSimbolo(id+" "+ambito).getTipo();

                recorrerArbol(((NodoFuncionSinRetorna)raiz).getSecuencias());
            }else
                    
            
                if (raiz instanceof NodoIdentificador){
                String id = ((NodoIdentificador)raiz).getNombre();
                  
                
                tipoCompara=" ";
                if(tablaSimbolos.BuscarSimbolo(id+" "+ambito) != null){
                   tipoCompara = tablaSimbolos.BuscarSimbolo(id+" "+ambito).getTipo();     
         
                   if(tipoCompara == "BOOLEAN")
                       bandBoleam = true;
                   else
                       bandNumero = true;
                }
                    //System.out.println("tipo: "+tipoCompara);
                if("".equals(tipoCompara)){
                    System.err.println("Identificador no Declarado: "+id);
                    System.exit(0);
                } 
                
            }else       
            if (raiz instanceof NodoAsignacion){
                String temporal = ((NodoAsignacion)raiz).getIdentificador();
                
                if(tablaSimbolos.BuscarSimbolo(temporal+" "+ambito) != null){
                    
                    BandAsig = true;
                    String t = tablaSimbolos.BuscarSimbolo(temporal+" "+ambito).getTipo();
                    
                    recorrerArbol(((NodoAsignacion)raiz).getExpresion());                  
                        
                        if("INT".equals(t) && bandNumero == false){
                                System.err.println("******* Datos no compatible **********");
                                System.err.println("******* Error de Asignación *********");
                                System.exit(0);
                        }
                            
                        if("BOOLEAN".equals(t) && bandBoleam == false){
                            System.err.println("******* Datos no compatible **********");
                            System.err.println("******* Error de Asignación *********");
                            System.exit(0);
                        }
                        
                        BandAsig = false;
                        bandNumero = false;
                        bandBoleam = false;
                        tipoCompara = "";
                        
                }else{
                    System.err.println("Error de Asignacion Variable no Declarada: "+temporal);
                    System.exit(0);
                }
            }else
                    
            if  (raiz instanceof NodoValor){
                if( ((NodoValor)raiz).getVint() != null){
                    Numeros= ((NodoValor)raiz).getVint();
                    bandNumero = true;
                    tipoCompara = "INT";
                }else{
                    booleanos = ((NodoValor)raiz).isVbol();
                    bandBoleam = true;
                    tipoCompara = "BOOLEAN";
                }

            }else
                
  //recorridos bloques de codigo
                
            if  (raiz instanceof NodoIf){
                
                recorrerArbol(((NodoIf)raiz).getPrueba());
               
                if(!"BOOLEAN".equals(tipoCompara) && bandBoleam == false && bandComprobacion == false){
                    System.err.println("Prueba IF No Es De Tipo Boolean");
                    System.exit(0);
                } else{
                    tipoCompara ="";
                    bandComprobacion = false;
                    BandOperadores = false;
                    bandBoleam = false;
                }
                 
                    
                recorrerArbol(((NodoIf)raiz).getParteThen());
                
                if(((NodoIf)raiz).getParteThen()!= null)
                    recorrerArbol(((NodoIf)raiz).getParteElse());
                
            }else
            
            if  (raiz instanceof NodoOperacion){
                                
                String t = ""+((NodoOperacion)raiz).getOperacion();
                              
                if(BandAsig == true){
                    recorrerArbol(((NodoOperacion)raiz).getOpIzquierdo());
                    recorrerArbol(((NodoOperacion)raiz).getOpDerecho());
                    
                    if(bandNumero == bandBoleam ){
                        System.err.println("Operadores no compatibles");
                        System.exit(0);
                    }else
                        if(bandNumero == true && ("mas".equals(t) || "menos".equals(t) || "entre".equals(t) || "por".equals(t))){
                           // System.out.println("correcto");
                        }else{
                            System.err.println("Operador invalido");
                            System.exit(0);
                        }
                }else
                
                
                if(("mas".equals(t) || "menos".equals(t) || "entre".equals(t) || "por".equals(t)) && (BandOperadores==false) && (BandRetorno == false)){
                    System.err.println("Operador Invalido en Prueba");
                    System.exit(0);
                }else{
                    
                    bandComprobacion = true;
                    BandOperadores = true;
                    
                    //System.out.println("BN: "+bandNumero+" BB: "+bandBoleam);
                    
                    recorrerArbol(((NodoOperacion)raiz).getOpIzquierdo());
                    recorrerArbol(((NodoOperacion)raiz).getOpDerecho());
                    
                    //System.out.println("BN: "+bandNumero+" BB: "+bandBoleam);
                    
                    if(bandNumero == bandBoleam ){
                        System.err.println("Operadores no compatibles");
                        System.exit(0);
                    }
                    
                    if(bandBoleam == true && ("menor".equals(t) || "mayor".equals(t) || "menorigual".equals(t) || "mayorigual".equals(t) ))
                    {
                        System.err.println("Operador No Compatible con Booleanos");
                        System.exit(0);
                    }
                }}else
                
            if  (raiz instanceof NodoRepeat){
                recorrerArbol(((NodoRepeat)raiz).getCuerpo());
                
                recorrerArbol(((NodoRepeat)raiz).getPrueba());
               
                if(!"BOOLEAN".equals(tipoCompara) && bandBoleam == false && bandComprobacion == false){
                    System.err.println("Prueba REPEAT No Es De Tipo Boolean");
                    System.exit(0);
                } else{
                    tipoCompara ="";
                    bandComprobacion = false;
                    BandOperadores = false;
                    bandBoleam = false;
                }
                
                
             }else
            
            if  (raiz instanceof NodoFor){
           
                
                recorrerArbol(((NodoFor)raiz).getAsignacion());
                recorrerArbol(((NodoFor)raiz).getPrueba());
                
                if(!"BOOLEAN".equals(tipoCompara) && bandBoleam == false && bandComprobacion == false){
                    System.err.println("Prueba For");
                    System.exit(0);
                } else{
                    tipoCompara ="";
                    bandComprobacion = false;
                    BandOperadores = false;
                    bandBoleam = false;
                }
                
                recorrerArbol(((NodoFor)raiz).getAcumulador());
                recorrerArbol(((NodoFor)raiz).getCuerpo());
                
            
            }else
                
            if  (raiz instanceof NodoLeer){
                if(tablaSimbolos.BuscarSimbolo(((NodoLeer)raiz).getIdentificador()+" "+ambito) == null){
                    System.err.println("La Variable No de Lectura no Esta declarada...");
                    System.exit(0);
                }
                
            }else
                
            if  (raiz instanceof NodoEscribir){
                 recorrerArbol(((NodoEscribir)raiz).getExpresion());
            }
            
            raiz = raiz.getHermanoDerecha();
                
        
	}//while raiz
    }
}