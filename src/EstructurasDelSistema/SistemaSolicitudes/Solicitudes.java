package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;
import com.sun.java.accessibility.util.AccessibilityListenerList;

import java.util.Iterator;
import java.util.TreeMap;

public class Solicitudes {
    private TreeMap<CiudadesDeViaje, NodoSolicitud> arbol;

    public Solicitudes() {
        arbol = new TreeMap<CiudadesDeViaje, NodoSolicitud>();
    }

    public boolean insertarSolicitud(String origen, String destino, Cliente persona, Descripcion des) {
        //Crea un nuevo NodoSolicitud de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (!arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.put(clave, (new NodoSolicitud(clave,persona,des)));
            exito = true;
        }
        return exito;
    }

    public boolean agregarCliente(String origen, String destino, Cliente persona, Descripcion des) {
        //Agrega un NodoCliente en el arbol de NodoSolicitud de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.agregarNodoCliente(persona, des);
        }
        return exito;
    }

    public boolean agregarDescripcion(String origen, String destino, Cliente persona, Descripcion des) {
        //Agrega una Descripcion en la Lista de NodoCliente de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.agregarDescripcionCliente(persona, des);
        }
        return exito;
    }

    public boolean eliminarSolicitud(String origen, String destino, Cliente persona, Descripcion des) {
        //Elimina una solicitud de un cliente en especifico
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.eliminarDescripcionCliente(persona, des);
            this.actualizar();
        }
        return exito;
    }

    public boolean eliminarSolicitudesCliente(String origen, String destino, Cliente persona) {
        //Elimina las solicitudes de un cliente en especifico
        boolean exito = true;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud n = arbol.get(clave);
            exito = n.eliminarNodoCliente(persona);
            if (exito){
                this.actualizar();
            }
        }
        return exito;
    }

    public boolean eliminarSolicitudes(String origen, String destino) {
        //Elimina las solicitudes de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            arbol.remove(clave);
            exito = true;
            this.actualizar();
        }
        return exito;
    }

    private void actualizar(){
        if (!this.arbol.isEmpty()){
            Lista aux = new Lista();
            guardarElementos(this.arbol.values(),aux);
            int largo = aux.longitud();
            for (int i = 1; i <= largo; i++) {
                NodoSolicitud nodo = (NodoSolicitud) aux.recuperar(i);
                if (this.arbol.get(nodo.getCiudadesDeViaje()).esVacio()){
                    this.arbol.remove(nodo.getCiudadesDeViaje());
                }
            }
        }
    }

    public boolean existeCiudadesDeViaje(String origen, String destino) {
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        return arbol.containsKey(clave);
    }

    public boolean existeCliente(String origen, String destino, Cliente persona) {
        boolean exito = false;
        if (!this.arbol.isEmpty()) {
            CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.existeNodoCliente(persona);
        }
        return exito;
    }

    public boolean existeDescripcion(String origen, String destino, Cliente persona, Descripcion des) {
        boolean exito = false;
        if (!this.arbol.isEmpty()) {
            CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
            NodoSolicitud nodo = arbol.get(clave);
            if (nodo.existeNodoCliente(persona)) {
                NodoCliente nodoPersona = (NodoCliente) nodo.getNodoCliente(persona);
                exito = nodoPersona.existeDescripcion(des);
            }
        }
        return exito;
    }

    public Descripcion getDescripcion(String origen, String destino, Cliente persona, String fecha, String domRetiro) {
        Descripcion salida = null;
        if (!this.arbol.isEmpty()) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo != null) {
                NodoCliente nodoPersona = nodo.getNodoCliente(persona);
                if (nodoPersona != null) {
                    salida = nodoPersona.getDescripcion(fecha, domRetiro);
                }
            }
        }
        return salida;
    }


    public Lista listarCiudadesDeViaje() {
        Lista salida = new Lista();
        if (!arbol.isEmpty()) {
            guardarElementos(arbol.keySet(), salida);
        }
        return salida;
    }

    public Lista listarNodoSolicitud() {
        Lista salida = new Lista();
        if (!arbol.isEmpty()) {
            guardarElementos(arbol.values(), salida);
        }
        return salida;
    }

    private void guardarElementos(Iterable t, Lista lis) {
        Iterator it = t.iterator();
        while (it.hasNext()) {
            lis.insertar(it.next(), lis.longitud() + 1);
        }
    }

    public Lista listarClientes(String origen, String destino) {
        Lista salida = new Lista();
        NodoSolicitud nodo = arbol.get((new CiudadesDeViaje(origen, destino)));
        if (nodo != null) {
            salida = nodo.getListaDeClientes();
        }
        return salida;
    }

    public Lista listarDescripcionesCliente(String origen, String destino, Cliente persona) {
        Lista salida = new Lista();
        NodoSolicitud nodo = arbol.get((new CiudadesDeViaje(origen, destino)));
        if (nodo != null) {
            NodoCliente nodoPersona = nodo.getNodoCliente(persona);
            if (nodoPersona != null) {
                salida = nodoPersona.getListaDescripcion();
            }
        }
        return salida;
    }

    public String mostrarSolicitudes(String origen, String destino) {
        String cad = "No hay solicitudes de ningun cliente desde "+ origen + " a "+destino;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (this.arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            cad =  nodo.getListaDePedidos() + "\n La cantidad total de metros cuadrados que necesita el camion es: " + nodo.getCantMetrosCuadrados() + "\n\n";
        }
        return cad;
    }

    public boolean esCaminoPerfecto(Lista ciudades, int cantMetrosCuadrados) {
        boolean existeCamino = false, caminoPerfecto = true;
        String origen, destino;
        int largo = ciudades.longitud();
        int i = 1;
        while (caminoPerfecto && i < largo) {
            int j = i + 1;
            origen = (String) ciudades.recuperar(i);
            while (!existeCamino && j <= largo) {
                destino = (String) ciudades.recuperar(j);
                existeCamino = existePedidos(origen, destino);
                if (existeCamino) {
                    existeCamino = existeCaminoPerfecto(origen, destino, cantMetrosCuadrados);
                }
                j++;
            }
            if (!existeCamino) {
                caminoPerfecto = false;
            }
            i++;
            existeCamino = false;
        }
        return caminoPerfecto;
    }

    private boolean existeCaminoPerfecto(String origen, String destino, int cantMetrosCuadrados) {
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        double cantTotal = nodo.getCantMetrosCuadrados();
        return cantTotal <= cantMetrosCuadrados;
    }

    private boolean existePedidos(String origen, String destino) {
        return this.arbol.containsKey(new CiudadesDeViaje(origen, destino));
    }

    public String toString() {
        String cad = "Sistema Solicitudes\n";
        if (arbol.isEmpty()) {
            cad = cad + "No hay solicitudes cargadas";
        } else {
            cad = cad + escribirElementos(arbol.entrySet());
        }
        return cad;
    }

    private String escribirElementos(Iterable t) {
        String cad = "";
        Iterator it = t.iterator();
        while (it.hasNext()) {
            cad = cad + it.next() + "\n";
        }
        return cad;
    }

    public boolean esVacio() {
        return this.arbol.isEmpty();
    }

    public void vaciar() {
        this.arbol.clear();
    }

    public void vaciarSolicitudes(String origen, String destino){
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (this.arbol.containsKey(clave)){
            this.arbol.get(clave).vaciar();
            this.actualizar();
        }
    }

    public void vaciarSolicitudesCliente(String origen, String destino, Cliente persona){
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (this.arbol.containsKey(clave)){
            this.arbol.get(clave).vaciarSolicitudesCliente(persona);
        }
    }

    public Solicitudes clone() {
        Solicitudes clon = new Solicitudes();
        clon.arbol.putAll(this.arbol);
        return clon;
    }
}
