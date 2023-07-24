package EstructurasDelSistema.SistemaSolicitudes;

import clases.Ciudad;

public class CiudadesDeViaje {
    private String origen;
    private String destino;

    public CiudadesDeViaje(String origen, String destino){
        this.origen = origen;
        this.destino = destino;
    }

    public String getOrigen(){
        return origen;
    }
    public String getDestino(){
        return destino;
    }

    public boolean equals(String origen, String destino){
        return this.origen.equals(origen) && this.destino.equals(destino);
    }

    public boolean equals(CiudadesDeViaje clave){
        return this.origen.equals(clave.getOrigen()) && this.destino.equals(clave.getDestino());
    }
}
