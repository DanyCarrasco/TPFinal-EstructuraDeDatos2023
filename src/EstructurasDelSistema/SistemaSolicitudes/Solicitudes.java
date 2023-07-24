package EstructurasDelSistema.SistemaSolicitudes;

import clases.Cliente;
import clases.Descripcion;

import java.util.TreeMap;

public class Solicitudes {
    private TreeMap arbol;

    public Solicitudes() {
        arbol = new TreeMap<>();
    }

    public boolean agregarSolicitud(CiudadesDeViaje clave, Cliente persona, Descripcion des) {
        boolean exito = false;
        if (!arbol.containsKey(clave)) {
            arbol.put(clave, new NodoCliente(persona, des));
            exito = true;
        }
        return exito;
    }

    public boolean eliminarSolicitud(CiudadesDeViaje clave) {
        return arbol.get(clave) == arbol.remove(clave);
    }
}
