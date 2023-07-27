package EstructurasDelSistema.SistemaSolicitudes;

import clases.Ciudad;

import java.util.Objects;

public class CiudadesDeViaje implements Comparable {
    private String origen;
    private String destino;

    public CiudadesDeViaje(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public boolean equals(Object obj) {
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        return this.origen.equals(clave.getOrigen()) && this.destino.equals(clave.getDestino());
    }

    @Override
    public int compareTo(Object obj) {
        int salida;
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        if (this.origen.compareTo(clave.getOrigen()) < 0) {
            salida = -1;
        } else {
            if (this.origen.compareTo(clave.getOrigen()) > 0) {
                salida = 1;
            } else {
                if (this.destino.compareTo(clave.getDestino()) < 0) {
                    salida = -2;
                } else {
                    if (this.destino.compareTo(clave.getDestino()) > 0) {
                        salida = 2;
                    } else {
                        salida = 0;
                    }
                }
            }
        }
        return salida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.origen);
        hash = 13 * hash + Objects.hashCode(this.destino);
        return hash;
    }
}
