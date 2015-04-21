package compilador;

public class RegistroSimbolo {
	private String identificador;
	private int NumLinea;
	private int DireccionMemoria;
	private int ambito;
        private String tipo;
        private String Clasificacion;
        private int Pos_Parametro;
        
	public RegistroSimbolo(String identificador, int numLinea,int direccionMemoria,int ambito,String tipo, String Clasificacion,int pos) {
		super();
		this.identificador = identificador;
		NumLinea = numLinea;
		DireccionMemoria = direccionMemoria;
                this.ambito = ambito;
                this.tipo = tipo;
                this.Clasificacion = Clasificacion;
                this.Pos_Parametro = pos;
	}

    public int getPos_Parametro() {
        return Pos_Parametro;
    }

    public void setPos_Parametro(int Pos_Parametro) {
        this.Pos_Parametro = Pos_Parametro;
    }

	public String getIdentificador() {
		return identificador;
	}

	public int getNumLinea() {
		return NumLinea;
	}

	public int getDireccionMemoria() {
		return DireccionMemoria;
	}

	public void setDireccionMemoria(int direccionMemoria) {
		DireccionMemoria = direccionMemoria;
	}

        public int getAmbito() {
            return ambito;
        }

        public void setAmbito(int ambito) {
            this.ambito = ambito;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getClasificacion() {
            return Clasificacion;
        }

        public void setClasificacion(String Clasificacion) {
            this.Clasificacion = Clasificacion;
        }
}
