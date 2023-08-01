package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

public class NodoSolicitud {
    private CiudadesDeViaje clave;
    private Lista nodoClientes;

    public NodoSolicitud(CiudadesDeViaje clave) {
        this.clave = clave;
        this.nodoClientes = new Lista();
    }

    public boolean agregarNodoCliente(NodoCliente n) {
        boolean exito = false;
        if (this.nodoClientes.localizar(n) < 1) {
            exito = this.nodoClientes.insertar(n, nodoClientes.longitud() + 1);
        }
        return exito;
    }

    public boolean eliminarNodoCliente(NodoCliente n) {
        boolean exito = false;
        if (!this.nodoClientes.esVacia()) {
            exito = nodoClientes.eliminar(nodoClientes.localizar(n));
        }
        return exito;
    }

    public NodoCliente getNodoCliente(Documento doc) {
        NodoCliente salida = null;
        if (!this.nodoClientes.esVacia()) {
            salida = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(new NodoCliente(new Cliente(doc))));
        }
        return salida;
    }

    public boolean existeNodoCliente(Documento doc) {
        boolean existe = false;
        if (!this.nodoClientes.esVacia()) {
            existe = !(this.nodoClientes.localizar(new NodoCliente(new Cliente(doc))) < 1);
        }
        return existe;
    }

    public boolean equals(Object obj){
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        return this.clave.equals(clave);
    }

    public boolean agregarDescripcionCliente(Documento doc, String fecha){
        boolean exito = false;
        if (!nodoClientes.esVacia()){
            NodoCliente n = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(new NodoCliente(new Cliente(doc))));
            exito = n.agregarDescripcion(new Descripcion(fecha));
        }
        return exito;
    }

    public boolean eliminarDescripcionCliente(Documento doc, String fecha){
        boolean exito = false;
        if (!nodoClientes.esVacia()){
            NodoCliente n = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(new NodoCliente(new Cliente(doc))));
            exito = n.eliminarDescripcion(new Descripcion(fecha));
        }
        return exito;
    }

    public Descripcion getDescripcionCliente(Documento doc, String fecha){
        Descripcion salida = null;
        if (!nodoClientes.esVacia()){
            NodoCliente n = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(new NodoCliente(new Cliente(doc))));
            salida = n.getDescripcion(fecha);
        }
        return salida;
    }

    public boolean existeDescripcionCliente(Documento doc, String fecha){
        boolean exito = false;
        if (!nodoClientes.esVacia()){
            NodoCliente n = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(new NodoCliente(new Cliente(doc))));
            exito = n.existeDescripcion(fecha);
        }
        return exito;
    }

    public Lista getListaDescripcionCliente(Documento doc){
        Lista salida = new Lista();
        if (!nodoClientes.esVacia()){
            NodoCliente n = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(new NodoCliente(new Cliente(doc))));
            salida = n.getListaDescripcion();
        }
        return salida;
    }
}
