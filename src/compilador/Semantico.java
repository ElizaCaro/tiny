package compilador;
import ast.*;

//<editor-fold defaultstate="collapsed" desc="Variables Globales">
public class Semantico {
    
    private final TablaSimbolos tablaSimbolos;
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
    boolean BandCall = false;
    boolean Correcto = false;
    int ambitoAux = 0;
    int pos = 0;
    int cant = 0;
    int comp = 0;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Semantico(TablaSimbolos tablaSimbolos) {
        super();
        this.tablaSimbolos = tablaSimbolos;
        ambito = 0;
    }
//</editor-fold>

@SuppressWarnings("SuspiciousIndentAfterControlStatement")
public void recorrerArbol(NodoBase raiz){

    while (raiz != null) {

        //<editor-fold defaultstate="collapsed" desc="NodoEstructura">
        if (raiz instanceof NodoEstructura){
            recorrerArbol(((NodoEstructura)raiz).getFuncion());
            ambito++;
            recorrerArbol(((NodoEstructura)raiz).getBloque());
        }
        //</editor-fold> 
        else
        //<editor-fold defaultstate="collapsed" desc="NodoFuncionRetorna">
        if (raiz instanceof NodoFuncionRetorna){
            ambito++;
            String id = ((NodoFuncionRetorna)raiz).getIdentificador();
            String tipo ="";
            
            //verifica que el nombre de la funcion exista en la tabla
            if(tablaSimbolos.BuscarSimbolo(id+" "+ambito)!= null)
                tipo = tablaSimbolos.BuscarSimbolo(id+" "+ambito).getTipo();

            BandRetorno = true;
            // valida la existencia de la expresion del return
            recorrerArbol(((NodoFuncionRetorna)raiz).getExpresion()); 

            if(!tipo.equals(tipoCompara)){
                System.err.println("******* Dato de Retorno Incompatible  ********");
                System.exit(0);
            }           

            bandBoleam = false;
            bandNumero = false;
            BandRetorno = false;
            bandComprobacion = false;
            BandOperadores = false;
            tipoCompara = "";
            
            recorrerArbol(((NodoFuncionRetorna)raiz).getSecuencias());
        }
//</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoFuncionSinRetorno">
        if  (raiz instanceof NodoFuncionSinRetorna){
            ambito++;
            recorrerArbol(((NodoFuncionSinRetorna)raiz).getSecuencias());
        }
//</editor-fold>
        else           
        //<editor-fold defaultstate="collapsed" desc="NodoIdentificador">
        if (raiz instanceof NodoIdentificador){
            String id = ((NodoIdentificador)raiz).getNombre();
            tipoCompara="";
            //verifica la existencia del identificador
            if(tablaSimbolos.BuscarSimbolo(id+" "+ambito) != null){
                tipoCompara = tablaSimbolos.BuscarSimbolo(id+" "+ambito).getTipo();
                
                if (BandCall == true){
                    pos++;
                    cant++;
                    System.out.println("compaaa: "+comp);
                    comp+= tablaSimbolos.getParametros(ambitoAux,tipoCompara,pos);
                }else{
                  
                    pos = 0;
                    BandCall = false;
                    
                    if("BOOLEAN".equals(tipoCompara))
                        bandBoleam = true;
                    else
                        bandNumero = true;
                }
            }
            
            if("".equals(tipoCompara)){
                System.err.println("Identificador no Declarado: "+id);
                System.exit(0);
            }

        }
//</editor-fold>
        else       
        //<editor-fold defaultstate="collapsed" desc="NodoValor">
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

        }
//</editor-fold> 
        else
        //<editor-fold defaultstate="collapsed" desc="NodoAsignacion">
        if (raiz instanceof NodoAsignacion){

            String temporal = ((NodoAsignacion)raiz).getIdentificador();
            System.out.println("temporal: "+temporal);

            if(tablaSimbolos.BuscarSimbolo(temporal+" "+ambito) != null){

                BandAsig = true;
                String t = tablaSimbolos.BuscarSimbolo(temporal+" "+ambito).getTipo();
                recorrerArbol(((NodoAsignacion)raiz).getExpresion());
                
                
                    if(t != tipoCompara){
                        System.err.println("******* Datos no compatible **********");
                        System.err.println("******* Error de Asignación *********");
                        System.exit(0);
                    
                    }
                BandAsig = false;
                bandNumero = false;
                bandBoleam = false;
                tipoCompara = "";

            }else{
                System.err.println("Error de Asignacion, Variable no Declarada: "+temporal);
                System.exit(0);
            }

        }
//</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoIf">
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

        }
        //</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoOperacion">
        if  (raiz instanceof NodoOperacion){

            String t = ""+((NodoOperacion)raiz).getOperacion();
            
            if(BandAsig == true){
                System.out.println("BNI: "+bandNumero+" BBI: "+bandBoleam);
                recorrerArbol(((NodoOperacion)raiz).getOpIzquierdo());
                System.out.println("BNI: "+bandNumero+" BBI: "+bandBoleam);
                System.out.println("");
                System.out.println("BND: "+bandNumero+" BBD: "+bandBoleam);
                recorrerArbol(((NodoOperacion)raiz).getOpDerecho());
                System.out.println("BND: "+bandNumero+" BBD: "+bandBoleam);
                
                
                if(bandNumero == bandBoleam ){
                    System.err.println("Operadores no compatibles");
                    System.exit(0);
                }else{
                   
                    if((bandNumero == true) && (("mas".equals(t) || ("menos".equals(t)) || ("por".equals(t)) || ("entre".equals(t))))){
                        //System.out.println("correcto");
                    }else{    
                        System.err.println("Operador invalido");
                        System.exit(0);
                    }
                 }
                
               
                //asignacion de funciones
            }else{
                
                //operaciones pruebas
              
                if(("mas".equals(t) || "menos".equals(t) || "entre".equals(t) || "por".equals(t)) && (BandOperadores==false) && (BandRetorno == false)){
                    System.err.println("Operador Invalido en Prueba");
                    System.exit(0);
                }else{

                    
                    bandComprobacion = true;
                    BandOperadores = true;

                    recorrerArbol(((NodoOperacion)raiz).getOpIzquierdo());
                    recorrerArbol(((NodoOperacion)raiz).getOpDerecho());
                    
                    if(bandNumero == bandBoleam ){
                        System.err.println("Operadores no compatibles ");
                        System.exit(0);
                    }

                    if(bandBoleam == true && ("menor".equals(t) || "mayor".equals(t) || "menorigual".equals(t) || "mayorigual".equals(t) ))
                    {
                        System.err.println("Operador No Compatible con Booleanos");
                        System.exit(0);
                    }
                }
                
                
            }
        }

//</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoRepeat">
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
        }
        //</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoFor">
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
    }

//</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoLeer">
        if  (raiz instanceof NodoLeer){
            if(tablaSimbolos.BuscarSimbolo(((NodoLeer)raiz).getIdentificador()+" "+ambito) == null){
                System.err.println("La Variable No de Lectura no Esta declarada...");
                System.exit(0);
            }

        }

//</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoEscribir">
        if  (raiz instanceof NodoEscribir){
            recorrerArbol(((NodoEscribir)raiz).getExpresion());
        }

//</editor-fold>
        else
        //<editor-fold defaultstate="collapsed" desc="NodoCall">
        if  (raiz instanceof NodoCall){
            
            //<editor-fold defaultstate="collapsed" desc="BandCall false">
            if(BandAsig == false){
                // Si BandCall false llamo procedimiento
                comp = 0;
                cant = 0;
                boolean band = false;
                String id = ((NodoCall)raiz).getIdentificador();
                for (int i = 1; i < ambito+1; i++) {
                    if (tablaSimbolos.BuscarSimbolo(id+" "+i) != null){
                        
                        if(tablaSimbolos.BuscarSimbolo(id+" "+i).getTipo() == "VOID"){
                            //verifica parametros
                            int tam = tablaSimbolos.tamano(i);
                            if(((NodoCall)raiz).getArgumentos() != null){
                                BandCall = true;
                                ambitoAux = i;
                                recorrerArbol(((NodoCall)raiz).getArgumentos());
                                
                                System.out.println("comp: "+comp+" tam: "+tam);
                                if(comp != tam){
                                    System.err.println("Parametros de la Funcion Mal Definidos");
                                    System.exit(0);
                                }else{
                                    tipoCompara = "";
                                    BandCall = false;
                                }
                            }
                            
                            System.out.println("tam: "+tam+" cant: "+cant);
                            if(tam != cant){
                                System.err.println("Numero de parametros distintos");
                                System.exit(0);
                            }
                            
                             
                        }else{
                            System.err.println("Mal Uso de Funcion");
                            System.exit(0);
                        }
                       
                        band = true;
                        break;
                    }
                }
                
                if(band == false){
                    System.err.println("Funcion No Declarada");
                    System.exit(0);
                }
                
                
            }
         //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="BandCall True">
            else{
             // Si BandCall es true llamo asignacion   
                boolean band = false;
                pos = 0;
                comp = 0;
                cant = 0;
                
                String id = ((NodoCall)raiz).getIdentificador();
                System.out.println("IDENTIFICADOR: "+id);
                
                for(int i = 0; i < ambito; i++){
                    
                    if(tablaSimbolos.BuscarSimbolo(id+" "+i)!=null){
                        
                        if(tablaSimbolos.BuscarSimbolo(id+" "+i).getTipo()=="INT" || tablaSimbolos.BuscarSimbolo(id+" "+i).getTipo()=="BOOLEAN"){
                       
                        int tam = tablaSimbolos.tamano(i);
                       
                            if(((NodoCall)raiz).getArgumentos() != null){
                                BandCall = true;
                                ambitoAux = i;
                                
                                System.out.println("comparador: "+comp+" tam: "+tam);
                                
                                recorrerArbol(((NodoCall)raiz).getArgumentos());
                                
                                System.out.println("comp: "+comp+" tam: "+tam);
                                
                                if(comp != tam){
                                    System.err.println("Parametros de la Funcion Mal Definidos");
                                    System.exit(0);
                                }else{
                                    tipoCompara = "";
                                    BandCall = false;
                                }
                            }
                        
                            System.out.println("tam: "+tam+" cant: "+cant);
                        if(tam != cant){
                                System.err.println("Numero de parametros distintos");
                                System.exit(0);
                        }
                            
                            
                        }else{
                            System.err.println("Mal Uso de Funcion O Tipos Incompatibles");
                            System.exit(0);
                        } 
                        
                         tipoCompara = tablaSimbolos.BuscarSimbolo(id+" "+i).getTipo();

                         if("INT".equals(tipoCompara))
                             bandNumero = true;
                         else
                             bandBoleam = true;
                         
                         band = true;   
                         break;
                    }
                    
                    
                }//for
                
                if(band == false){
                    System.err.println("Funcion No Declarada");
                    System.exit(0);
                }
                              }
        //</editor-fold>
            
        }

//</editor-fold>
        raiz = raiz.getHermanoDerecha();

    }//while raiz
}
}