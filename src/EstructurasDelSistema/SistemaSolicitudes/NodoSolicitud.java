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

    public NodoSolicitud(CiudadesDeViaje clave) {
        this.clave = clave;
        this.arbolClientes = new TreeMap<Cliente, NodoCliente>();
    }

    public boolean agregarNodoCliente(NodoCliente n) {
        boolean exito = false;
        if (!this.arbolClientes.containsKey(n.getCliente())) {
            arbolClientes.put(n.getCliente(), n);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarNodoCliente(NodoCliente n) {
        boolean exito = false;
        if (this.arbolClientes.containsKey(n.getCliente())) {
            arbolClientes.remove(n.getCliente());
            exito = true;
        }
        return exito;
    }

    public NodoCliente getNodoCliente(Documento doc) {
        NodoCliente salida = null;
        Cliente clave = new Cliente(doc);
        if (this.arbolClientes.containsKey(clave)) {
            salida = this.arbolClientes.get(clave);
        }
        return salida;
    }

    public boolean existeNodoCliente(Documento doc) {
        Cliente clave = new Cliente(doc);
        return this.arbolClientes.containsKey(clave);
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
        }
        return exito;
    }

    public Descripcion getDescripcionCliente(Documento doc, String fecha) {
        Descripcion salida = null;
        Cliente claveCliente = new Cliente(doc);
        if (this.arbolClientes.containsKey(claveCliente)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(claveCliente);
            salida = n.getDescripcion(fecha);
        }
        return salida;
    }

    public boolean existeDescripcionCliente(Documento doc, String fecha) {
        boolean exito = false;
        Cliente claveCliente = new Cliente(doc);
        if (this.arbolClientes.containsKey(claveCliente)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(claveCliente);
            exito = n.existeDescripcion(fecha);
        }
        return exito;
    }

    public Lista getListaDescripcionCliente(Documento doc) {
        Lista salida = new Lista();
        Cliente claveCliente = new Cliente(doc);
        if (this.arbolClientes.containsKey(claveCliente)) {
            NodoCliente n = (NodoCliente) arbolClientes.get(claveCliente);
            salida = n.getListaDescripcion();
        }
        return salida;
    }

    public String getListaDePedidos() {
        String cad = "No hay pedidos de ningun cliente";
        if (!arbolClientes.isEmpty()) {
            cad = "";
            Lista aux = new Lista();
            guardarElementos(arbolClientes.entrySet(), aux);
            for (int i = 0; i <= aux.longitud(); i++) {
                NodoCliente nodo = (NodoCliente) aux.recuperar(i);
                cad = cad + "Cliente: " + nodo.getCliente().toString() + "\n Lista de sus pedidos: \n";
                cad = cad + nodo.getListaDescripcion().toString();
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
                NodoCliente persona = (NodoCliente) aux.recuperar(i);
                salida.insertar(persona.getCliente(), i);
            }
        }
        return salida;
    }

    public double getCantMetrosCuadrados() {
        double cantTotal = 0;
        if (!arbolClientes.isEmpty()) {
            Lista aux = new Lista();
            guardarElementos(arbolClientes.keySet(), aux);
            for (int i = 1; i <= aux.longitud(); i++) {
                NodoCliente persona = (NodoCliente) aux.recuperar(i);
                cantTotal = cantTotal + persona.cantMetrosCuadradosDescripcion();
            }
        }
        return cantTotal;
    }

    public boolean esVacio(){
        return this.arbolClientes.isEmpty();
    }
}
