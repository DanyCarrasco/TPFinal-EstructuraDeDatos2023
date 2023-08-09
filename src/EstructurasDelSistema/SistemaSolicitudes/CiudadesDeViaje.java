package EstructurasDelSistema.SistemaSolicitudes;

import clases.Ciudad;

import java.util.Objects;

public class CiudadesDeViaje implements Comparable {
    private int origen;
    private int destino;

    public CiudadesDeViaje(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Ciudad origen: " + this.origen + ", ciudad destino: " + this.destino;
    }

    public boolean equals(Object obj) {
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        return this.origen == clave.origen && this.destino == clave.destino;
    }

    @Override
    public int compareTo(Object obj) {
        int salida;
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        if (this.origen < clave.origen) {
            salida = -1;
        } else {
            if (this.origen > clave.origen) {
                salida = 1;
            } else {
                if (this.destino < clave.destino) {
                    salida = -2;
                } else {
                    if (this.destino > clave.destino) {
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
