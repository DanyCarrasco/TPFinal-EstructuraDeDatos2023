package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

import java.util.Iterator;
import java.util.TreeMap;

public class NodoSolicitud {
    private TreeMap<Cliente, NodoCliente> arbolClientes;

    public NodoSolicitud(Cliente persona, Descripcion des) {
        this.arbolClientes = new TreeMap<Cliente, NodoCliente>();
        this.arbolClientes.put(persona, (new NodoCliente(persona, des)));
    }

    public boolean agregarNodoCliente(Cliente persona, Descripcion des) {
        boolean exito = false;
        if (!this.arbolClientes.containsKey(persona)) {
            arbolClientes.put(persona, new NodoCliente(persona, des));
            exito = true;
        }
        return exito;
    }

    public boolean eliminarNodoCliente(Cliente persona) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            arbolClientes.remove(persona);
            exito = true;
            this.actualizar();
        }
        return exito;
    }

    public boolean existeNodoCliente(int tipoDoc, int numDoc) {
        return this.arbolClientes.containsKey(new Cliente(new Documento(tipoDoc,numDoc)));
    }

    public boolean agregarDescripcionCliente(Cliente persona, Descripcion des) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            exito = n.agregarDescripcion(des);
        }
        return exito;
    }

    public boolean eliminarDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            exito = n.eliminarDescripcion(fecha, domRetiro);
            if (exito) {
                this.actualizar();
            }
        }
        return exito;
    }

    public int getCantMetrosDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        int salida = 0;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.getCantMetrosDescripcion(fecha,domRetiro);
            }
        }
        return salida;
    }

    public boolean setCantMetrosDescripcionCliente(Cliente persona, String fecha, String domRetiro, int cantMetros) {
        boolean  salida = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.setCantMetrosDescripcion(fecha,domRetiro,cantMetros);
            }
        }
        return salida;
    }

    public int getCantBultosDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        int salida = 0;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.getCantBultosDescripcion(fecha,domRetiro);
            }
        }
        return salida;
    }

    public boolean setCantBultosDescripcionCliente(Cliente persona, String fecha, String domRetiro, int cantMetros) {
        boolean  salida = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.setCantBultosDescripcion(fecha,domRetiro,cantMetros);
            }
        }
        return salida;
    }

    public String getDomicilioEntregaDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        String salida = "";
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.getDomicilioEntregaDescripcion(fecha,domRetiro);
            }
        }
        return salida;
    }

    public boolean setDomicilioEntregaDescripcionCliente(Cliente persona, String fecha, String domRetiro, String domEntrega) {
        boolean salida = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.setDomicilioEntregaDescripcion(fecha,domRetiro,domEntrega);
            }
        }
        return salida;
    }

    public boolean getEnvioPagadoDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        boolean salida = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.getPagoEnvioDescripcion(fecha,domRetiro);
            }
        }
        return salida;
    }

    public boolean setEnvioPagadoDescripcionCliente(Cliente persona, String fecha, String domRetiro, boolean pago) {
        boolean salida = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.setPagoEnvioDescripcion(fecha,domRetiro,pago);
            }
        }
        return salida;
    }

    public boolean existeDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        boolean salida = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = arbolClientes.get(persona);
            salida = n.existeDescripcion(fecha,domRetiro);
        }
        return salida;
    }

    public String mostrarDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        String salida = "";
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            if (n.existeDescripcion(fecha, domRetiro)){
                salida = n.mostrarDescripcion(fecha,domRetiro);
            }
        }
        return salida;
    }



    public Lista getListaDescripcionCliente(Cliente persona) {
        Lista salida = new Lista();
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            salida = n.getListaDescripcion();
        }
        return salida;
    }

    public String getListaDePedidos() {
        String cad = "No hay pedidos de ningun cliente";
        if (!arbolClientes.isEmpty()) {
            cad = "";
            Lista aux = new Lista();
            guardarElementos(arbolClientes.values(), aux);
            for (int i = 1; i <= aux.longitud(); i++) {
                NodoCliente nodo = (NodoCliente) aux.recuperar(i);
                cad = cad + nodo.toString();
            }
        }
        return cad;
    }

    private void guardarElementos(Iterable t, Lista lis) {
        Iterator it = t.iterator();
        while (it.hasNext()) {
            lis.insertar(it.next(), lis.longitud() + 1);
        }
    }

    public Lista getListaDeClientes() {
        Lista salida = new Lista();
        if (!arbolClientes.isEmpty()) {
            Lista aux = new Lista();
            guardarElementos(arbolClientes.keySet(), aux);
            for (int i = 1; i <= aux.longitud(); i++) {
                Cliente persona = (Cliente) aux.recuperar(i);
                salida.insertar(persona, i);
            }
        }
        return salida;
    }

    public double getCantMetrosCuadrados() {
        double cantTotal = 0;
        if (!arbolClientes.isEmpty()) {
            Lista aux = new Lista();
            guardarElementos(arbolClientes.values(), aux);
            for (int i = 1; i <= aux.longitud(); i++) {
                NodoCliente persona = (NodoCliente) aux.recuperar(i);
                cantTotal = cantTotal + persona.cantMetrosCuadradosDescripcion();
            }
        }
        return cantTotal;
    }

    public boolean esVacio() {
        return this.arbolClientes.isEmpty();
    }

    public boolean esVacioCliente(Cliente persona) {
        boolean vacio = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente nodo = this.arbolClientes.get(persona);
            vacio = nodo.esVacio();
        }
        return vacio;
    }

    private void actualizar() {
        if (!this.arbolClientes.isEmpty()) {
            Lista aux = new Lista();
            guardarElementos(this.arbolClientes.keySet(), aux);
            int largo = aux.longitud();
            for (int i = 1; i <= largo; i++) {
                Cliente persona = (Cliente) aux.recuperar(i);
                if (this.arbolClientes.get(persona).esVacio()) {
                    this.arbolClientes.remove(persona);
                }
            }
        }
    }

    public void vaciar() {
        this.arbolClientes.clear();
    }

    public void vaciarSolicitudesCliente(int tipoDoc, int numDoc) {
        if (this.arbolClientes.containsKey(new Cliente(new Documento(tipoDoc, numDoc)))) {
            this.arbolClientes.get(new Cliente(new Documento(tipoDoc, numDoc))).vaciar();
            this.actualizar();
        }
    }

    public String toString() {
        String cad = "Lista de clientes con sus pedidos:\n";
        Lista aux = new Lista();
        if (arbolClientes.isEmpty()) {
            cad = cad + "No hay pedidos de ningun cliente";
        } else {
            guardarElementos(arbolClientes.values(), aux);
            for (int i = 1; i <= aux.longitud(); i++) {
                NodoCliente persona = (NodoCliente) aux.recuperar(i);
                if (persona != null) {
                    cad = cad + persona.toString() + "\n";
                }
            }
        }
        return cad;
    }
}
