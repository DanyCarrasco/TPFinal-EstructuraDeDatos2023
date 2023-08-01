package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;
import com.sun.java.accessibility.util.AccessibilityListenerList;

import java.util.TreeMap;

public class Solicitudes {
    private TreeMap<CiudadesDeViaje, NodoSolicitud> arbol;

    public Solicitudes() {
        arbol = new TreeMap<CiudadesDeViaje, NodoSolicitud>();
    }

    public boolean insertarSolicitud(String origen, String destino) {
        //Crea una nueva Lista de NodoClientes de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (!arbol.containsKey(clave)) {
            arbol.put(clave, (new Lista()));
            exito = true;
        }
        return exito;
    }

    public boolean agregarCliente(String origen, String destino, Cliente persona){
        //Agrega un NodoCliente en la Lista de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            exito = lis.insertar((new NodoCliente(persona)), lis.longitud()+1);
        }
        return exito;
    }

    public boolean agregarDescripcion(String origen, String destino, Cliente persona, Descripcion des){
        //Agrega una Descripcion en la Lista de NodoCliente de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            NodoCliente nodo = (NodoCliente) lis.recuperar(lis.localizar((new NodoCliente(persona))));
            exito = nodo.agregarDescripcion(des);
        }
        return exito;
    }

    public boolean eliminarSolicitud(String origen, String destino, Cliente persona, Descripcion des) {
        //Elimina una solicitud de un cliente en especifico
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            Lista lis = (Lista) arbol.get(clave);
            NodoCliente n = (NodoCliente) lis.recuperar(lis.localizar(new NodoCliente(persona)));
            n.eliminarDescripcion(des);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarSolicitudesCliente(String origen, String destino, Cliente persona){
        //Elimina las solicitudes de un cliente en especifico
        boolean exito = true;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            exito = lis.eliminar(lis.localizar(new NodoCliente(persona)));
        }
        return exito;
    }

    public boolean eliminarSolicitudes(String origen, String destino){
        //Elimina las solicitudes de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            arbol.remove(clave);
            exito = true;
        }
        return exito;
    }

    public Descripcion getClienteDescricion(String origen, String destino, Cliente persona, String fecha){
        //Si existe en el arbol y si existe en la lista de NodoCliente, retorna la descripcion de la solicitud de un cliente
        Descripcion salida = null;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            NodoCliente nPers = (NodoCliente) lis.recuperar(lis.localizar((new NodoCliente(persona))));
            salida = nPers.getDescripcion(fecha);
        }
        return salida;
    }

    public Lista getListaDescripcion(String origen, String destino, Cliente persona){
        //Si existe en el arbol y si existe en la lista de NodoCliente,
        // retorna la lista de descripciones de las solicitudes de un cliente en el viaje de ciudad origen y ciudad destino
        Lista salida = null;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            NodoCliente nPers = (NodoCliente) lis.recuperar(lis.localizar((new NodoCliente(persona))));
            salida = nPers.getListaDescripcion();
        }
        return salida;
    }

    public Cliente getCliente(String origen, String destino, Documento doc){
        //Si existe en el arbol, retorna un cliente de la lista de NodoCliente
        Cliente salida = null;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            NodoCliente nPers = (NodoCliente) lis.recuperar(lis.localizar((new NodoCliente(new Cliente(doc)))));
            salida = nPers.getCliente();
        }
        return salida;
    }

    public Lista getListaClientes(String origen, String destino, Documento doc){
        //Si existe en el arbol, retorna una lista de clientes de la lista de NodoCliente
        Lista salida = new Lista();
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            Lista lis = arbol.get(clave);
            int cant = lis.longitud();
            for (int i = 1; i <= cant; i++) {
                NodoCliente nPers = (NodoCliente) lis.recuperar(i);
                salida.insertar(nPers.getCliente(), i);
            }
        }
        return salida;
    }
}
