package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

import java.util.Iterator;
import java.util.TreeMap;

public class NodoSolicitud {
    private CiudadesDeViaje clave;
    private TreeMap<Cliente, NodoCliente> arbolClientes;

    public NodoSolicitud(CiudadesDeViaje clave, Cliente persona, Descripcion des) {
        this.clave = clave;
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

    public CiudadesDeViaje getCiudadesDeViaje() {
        return this.clave;
    }

    public NodoCliente getNodoCliente(Cliente persona) {
        NodoCliente salida = null;
        if (this.arbolClientes.containsKey(persona)) {
            salida = this.arbolClientes.get(persona);
        }
        return salida;
    }

    public boolean existeNodoCliente(Cliente persona) {
        return this.arbolClientes.containsKey(persona);
    }

    public boolean equals(Object obj) {
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        return this.clave.equals(clave);
    }

    public boolean agregarDescripcionCliente(Cliente persona, Descripcion des) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            exito = n.agregarDescripcion(des);
        }
        return exito;
    }

    public boolean eliminarDescripcionCliente(Cliente persona, Descripcion des) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            exito = n.eliminarDescripcion(des);
            if (exito) {
                this.actualizar();
            }
        }
        return exito;
    }

    public Descripcion getDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        Descripcion salida = null;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            salida = n.getDescripcion(fecha, domRetiro);
        }
        return salida;
    }

    public boolean pagoEnvioDescripcionCliente(Cliente persona, String fecha, String domRetiro) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            exito = n.pagoEnvioDescripcion(fecha, domRetiro);
        }
        return exito;
    }

    public boolean existeDescripcionCliente(Cliente persona, Descripcion des) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(persona)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(persona);
            exito = n.existeDescripcion(des);
        }
        return exito;
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

    public void vaciarSolicitudesCliente(Cliente persona) {
        if (this.arbolClientes.containsKey(persona)) {
            this.arbolClientes.get(persona).vaciar();
            this.actualizar();
        }
    }

    public String toString() {
        String cad = this.clave.toString() + "\n Lista de clientes con sus pedidos:\n";
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
