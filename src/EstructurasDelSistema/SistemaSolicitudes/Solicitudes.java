package EstructurasDelSistema.SistemaSolicitudes;

import clases.Cliente;
import clases.Descripcion;

import java.util.TreeMap;

public class Solicitudes {
    private TreeMap<CiudadesDeViaje, NodoCliente> arbol;

    public Solicitudes() {
        arbol = new TreeMap<CiudadesDeViaje, NodoCliente>();
    }

    public boolean agregarSolicitud(String origen, String destino, Cliente persona, Descripcion des) {
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (!arbol.containsKey(clave)) {
            arbol.put(clave, new NodoCliente(persona, des));
            exito = true;
        }
        return exito;
    }

    public boolean eliminarSolicitud(String origen, String destino) {
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            arbol.remove(clave);
            exito = true;
        }
        return exito;
    }
}
