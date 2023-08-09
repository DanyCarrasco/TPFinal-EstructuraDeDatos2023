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

    public boolean insertarSolicitud(int origen, int destino, Cliente persona, Descripcion des) {
        //Crea un nuevo NodoSolicitud de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (!arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.put(clave, (new NodoSolicitud(persona, des)));
            exito = true;
        }
        return exito;
    }

    public boolean agregarCliente(int origen, int destino, Cliente persona, Descripcion des) {
        //Agrega un NodoCliente en el arbol de NodoSolicitud de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.agregarNodoCliente(persona, des);
        }
        return exito;
    }

    public boolean agregarDescripcion(int origen, int destino, Cliente persona, Descripcion des) {
        //Agrega una Descripcion en la Lista de NodoCliente de una ciudad origen y una ciudad destino
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.agregarDescripcionCliente(persona, des);
        }
        return exito;
    }

    public boolean eliminarSolicitud(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        //Elimina una solicitud de un cliente en especifico
        boolean exito = false;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.eliminarDescripcionCliente(persona, fecha,domRetiro);
            this.actualizar();
        }
        return exito;
    }

    public boolean eliminarSolicitudesCliente(int origen, int destino, Cliente persona) {
        //Elimina las solicitudes de un cliente en especifico
        boolean exito = true;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (arbol.containsKey(clave)) {
            NodoSolicitud n = arbol.get(clave);
            exito = n.eliminarNodoCliente(persona);
            if (exito) {
                this.actualizar();
            }
        }
        return exito;
    }

    public boolean eliminarSolicitudes(int origen, int destino) {
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

    private void actualizar() {
        if (!this.arbol.isEmpty()) {
            Lista aux = new Lista();
            guardarElementos(this.arbol.keySet(), aux);
            int largo = aux.longitud();
            for (int i = 1; i <= largo; i++) {
                CiudadesDeViaje nodo = (CiudadesDeViaje) aux.recuperar(i);
                if (this.arbol.get(nodo).esVacio()) {
                    this.arbol.remove(nodo);
                }
            }
        }
    }

    public boolean existeCiudadesDeViaje(int origen, int destino) {
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        return arbol.containsKey(clave);
    }

    public boolean existeCliente(int origen, int destino, int tipoDni, int numDni) {
        boolean exito = false;
        if (!this.arbol.isEmpty()) {
            CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
            NodoSolicitud nodo = arbol.get(clave);
            exito = nodo.existeNodoCliente(tipoDni,numDni);
        }
        return exito;
    }

    public boolean existeDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        boolean exito = false;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            exito = nodo.existeDescripcionCliente(persona, fecha, domRetiro);
        }
        return exito;
    }

    public int getCantMetrosDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        int salida = 0;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.getCantMetrosDescripcionCliente(persona, fecha, domRetiro);
            }
        }
        return salida;
    }

    public boolean setCantMetrosDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro, int cantMetros) {
        boolean salida = false;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.setCantMetrosDescripcionCliente(persona, fecha, domRetiro, cantMetros);
            }
        }
        return salida;
    }

    public int getCantBultosDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        int salida = 0;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.getCantBultosDescripcionCliente(persona, fecha, domRetiro);
            }
        }
        return salida;
    }

    public boolean setCantBultosDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro, int cantBultos) {
        boolean salida = false;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.setCantBultosDescripcionCliente(persona, fecha, domRetiro, cantBultos);
            }
        }
        return salida;
    }

    public String getDomicilioEntregaDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        String salida = "";
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.getDomicilioEntregaDescripcionCliente(persona, fecha, domRetiro);
            }
        }
        return salida;
    }

    public boolean setDomicilioEntregaDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro, String domEntrega) {
        boolean salida = false;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.setDomicilioEntregaDescripcionCliente(persona, fecha, domRetiro, domEntrega);
            }
        }
        return salida;
    }

    public boolean getEnvioPagadoDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        boolean salida = false;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.getEnvioPagadoDescripcionCliente(persona, fecha, domRetiro);
            }
        }
        return salida;
    }

    public boolean setEnvioPagadoDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro, boolean pago) {
        boolean salida = false;
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.setEnvioPagadoDescripcionCliente(persona, fecha, domRetiro, pago);
            }
        }
        return salida;
    }

    public String mostrarDescripcion(int origen, int destino, Cliente persona, String fecha, String domRetiro) {
        String salida = "";
        if (arbol.containsKey(new CiudadesDeViaje(origen, destino))) {
            NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
            if (nodo.existeDescripcionCliente(persona, fecha, domRetiro)) {
                salida = nodo.mostrarDescripcionCliente(persona, fecha, domRetiro);
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

    private void guardarElementos(Iterable t, Lista lis) {
        Iterator it = t.iterator();
        while (it.hasNext()) {
            lis.insertar(it.next(), lis.longitud() + 1);
        }
    }

    public Lista listarClientes(int origen, int destino) {
        Lista salida = new Lista();
        NodoSolicitud nodo = arbol.get((new CiudadesDeViaje(origen, destino)));
        if (nodo != null) {
            salida = nodo.getListaDeClientes();
        }
        return salida;
    }

    public Lista listarDescripcionesCliente(int origen, int destino, Cliente persona) {
        Lista salida = new Lista();
        if (arbol.containsKey((new CiudadesDeViaje(origen, destino)))) {
            NodoSolicitud nodo = arbol.get((new CiudadesDeViaje(origen, destino)));
            salida = nodo.getListaDescripcionCliente(persona);
        }
        return salida;
    }

    public String mostrarSolicitudes(int origen, int destino){
        String cad = "No hay solicitudes de ningun cliente desde " + origen + " a " + destino;
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (this.arbol.containsKey(clave)) {
            NodoSolicitud nodo = arbol.get(clave);
            cad = nodo.getListaDePedidos() + "\n La cantidad total de metros cuadrados que necesita el camion es: " + nodo.getCantMetrosCuadrados() + "\n\n";
        }
        return cad;
    }

    public boolean esCaminoPerfecto(Lista ciudades, double cantMetrosCuadrados) {
        boolean existeCamino = false, caminoPerfecto = true;
        double cantTotal = 0;
        int origen, destino;
        int largo = ciudades.longitud();
        int i = 1;
        while (caminoPerfecto && i < largo) {
            int j = i + 1;
            origen = (int) ciudades.recuperar(i);
            while (!existeCamino && j <= largo) {
                destino = (int) ciudades.recuperar(j);
                existeCamino = existePedidos(origen, destino);
                if (existeCamino) {
                    existeCamino = existeCaminoPerfecto(origen, destino, cantMetrosCuadrados);
                    if (existeCamino) {
                        cantTotal = contador(origen, destino, cantTotal);
                    }
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

    private boolean existeCaminoPerfecto(int origen, int destino, double cantMetrosCuadrados) {
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        double cantTotal = nodo.getCantMetrosCuadrados();
        return cantTotal <= cantMetrosCuadrados;
    }

    private boolean existePedidos(int origen, int destino) {
        return this.arbol.containsKey(new CiudadesDeViaje(origen, destino));
    }

    private double contador(int origen, int destino, double cantTotal) {
        NodoSolicitud nodo = arbol.get(new CiudadesDeViaje(origen, destino));
        cantTotal = cantTotal + nodo.getCantMetrosCuadrados();
        return cantTotal;
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

    public void vaciarSolicitudes(int origen, int destino) {
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (this.arbol.containsKey(clave)) {
            this.arbol.get(clave).vaciar();
            this.actualizar();
        }
    }

    public void vaciarSolicitudesCiudad(int codigo) {
        Lista lis = new Lista();
        guardarElementos(arbol.keySet(), lis);
        for (int i = 1; i <= lis.longitud(); i++) {
            CiudadesDeViaje clave = (CiudadesDeViaje) lis.recuperar(i);
            if (clave.getOrigen() == codigo || clave.getDestino() == codigo) {
                arbol.remove(clave);
            }
        }
    }

    public void vaciarSolicitudesCliente(int tipoDoc, int numTipo) {
        Lista lis = new Lista();
        guardarElementos(arbol.values(), lis);
        for (int i = 1; i <= lis.longitud(); i++) {
            NodoSolicitud nodo = (NodoSolicitud) lis.recuperar(i);
            if (nodo.existeNodoCliente(tipoDoc, numTipo)) {
                nodo.vaciarSolicitudesCliente(tipoDoc, numTipo);
            }
        }
    }


    public void vaciarSolicitudCliente(int origen, int destino, int tipoDoc, int numDoc) {
        CiudadesDeViaje clave = new CiudadesDeViaje(origen, destino);
        if (this.arbol.containsKey(clave)) {
            this.arbol.get(clave).vaciarSolicitudesCliente(tipoDoc, numDoc);
        }
    }

    public Solicitudes clone() {
        Solicitudes clon = new Solicitudes();
        clon.arbol.putAll(this.arbol);
        return clon;
    }
}
