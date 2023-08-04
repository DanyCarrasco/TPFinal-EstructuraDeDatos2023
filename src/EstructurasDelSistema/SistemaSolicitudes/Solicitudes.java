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

    public boolean insertarSolicitud(String origen, String destino) {
        //Crea un nuevo NodoSolicitud de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (!arbol.containsKey(clave)) {
            arbol.put(clave, (new NodoSolicitud(clave)));
            exito = true;
        }
        return exito;
    }

    public boolean agregarCliente(String origen, String destino, Cliente persona){
        //Agrega un NodoCliente en el arbol de NodoSolicitud de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.agregarNodoCliente(new NodoCliente(persona));
        }
        return exito;
    }

    public boolean agregarDescripcion(String origen, String destino, Cliente persona, Descripcion des){
        //Agrega una Descripcion en la Lista de NodoCliente de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
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
        }
        return exito;
    }

    public boolean eliminarSolicitudesCliente(String origen, String destino, Cliente persona){
        //Elimina las solicitudes de un cliente en especifico
        boolean exito = true;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)){
            NodoSolicitud n = arbol.get(clave);
            exito = n.eliminarNodoCliente(new NodoCliente(persona));
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

    public boolean existeCiudadesDeViaje(String origen, String destino){
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        return arbol.containsKey(clave);
    }

    public boolean existeCliente(String origen, String destino, Documento doc){
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        NodoSolicitud nodo = arbol.get(clave);
        return nodo.existeNodoCliente(doc);
    }

    public boolean existeDescripcion(String origen, String destino, Documento doc, String fecha){
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        NodoSolicitud nodo = arbol.get(clave);
        if (nodo.existeNodoCliente(doc)){
            NodoCliente persona = (NodoCliente) nodo.getNodoCliente(doc);
            exito = persona.existeDescripcion(fecha);
        }
        return exito;
    }

    public NodoSolicitud getNodoSolicitud(String origen, String destino){
        return arbol.get(new CiudadesDeViaje(origen, destino));
    }

    public Cliente getCliente(String origen, String destino, Documento doc){
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        return nodo.getNodoCliente(doc).getCliente();
    }

    public Descripcion getDescripcion(String origen, String destino, Documento doc, String fecha){
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        NodoCliente persona = nodo.getNodoCliente(doc);
        return persona.getDescripcion(fecha);
    }


    public Lista listarCiudadesDeViaje(){
        Lista salida = new Lista();
        if (!arbol.isEmpty()) {
            guardarElementos(arbol.keySet(), salida);
        }
        return salida;
    }

    private void guardarElementos(Iterable t, Lista lis) {
        Iterator it = t.iterator();
        while (it.hasNext()) {
            lis.insertar(it.next(), lis.longitud() + 1);
        }
    }


    /*public Descripcion getClienteDescricion(String origen, String destino, Cliente persona, String fecha){
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
    }*/

    public Lista listarClientes(String origen, String destino){
        NodoSolicitud nodo = arbol.get((new CiudadesDeViaje(origen, destino)));
        return nodo.getListaDeClientes();
    }

    public Lista listarDescripcionesCliente(String origen, String destino, Documento doc){
        NodoSolicitud nodo = arbol.get((new CiudadesDeViaje(origen, destino)));
        NodoCliente persona = nodo.getNodoCliente(doc);
        return persona.getListaDescripcion();
    }

    public String mostrarSolicitudes(String origen, String destino){
        return arbol.get((new CiudadesDeViaje(origen, destino))).getListaDePedidos();
    }

    public boolean esCaminoPerfecto(Lista ciudades, int cantMetrosCuadrados){
        String origen, destino;
        int largo = ciudades.longitud();
        boolean existeCamino = false, caminoPerfecto = true;
        int i = 1;
        while (caminoPerfecto && i <= largo){
            int j = i + 1;
            while (!existeCamino && j <= largo){
                origen = (String) ciudades.recuperar(i);
                destino = (String) ciudades.recuperar(j);
                existeCamino = existePedidos(origen,destino);
                if (existeCamino){
                    existeCamino = existeCaminoPerfecto(origen,destino,cantMetrosCuadrados);
                }
                j++;
            }
            if (existeCamino){
                caminoPerfecto = false;
            }
            i++;
        }
        return caminoPerfecto;
    }

    private boolean existeCaminoPerfecto(String origen, String destino, int cantMetrosCuadrados){
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        double cantTotal = nodo.getCantMetrosCuadrados();
        return cantTotal <= cantMetrosCuadrados;
    }

    private boolean existePedidos(String origen, String destino){
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        return nodo.esVacio();
    }
}
