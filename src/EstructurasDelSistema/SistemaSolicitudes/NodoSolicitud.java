package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Documento;

public class NodoSolicitud {
    private CiudadesDeViaje clave;
    private Lista nodoClientes;

    public NodoSolicitud(CiudadesDeViaje clave) {
        this.clave = clave;
        this.nodoClientes = new Lista();
    }

    public NodoSolicitud(String origen, String destino) {
        this.clave = new CiudadesDeViaje(origen, destino);
        this.nodoClientes = new Lista();
    }

    public boolean agregarNodoCliente(NodoCliente n) {
        boolean exito = false;
        if (this.nodoClientes.localizar(n) < 1) {
            exito = this.nodoClientes.insertar(n, nodoClientes.longitud() + 1);
        }
        return exito;
    }

    public boolean agregarNodoCliente(int numTipo, int unNumDni, String nombre, String apellido, int telefono, String email) {
        boolean exito = false;
        Documento doc = new Documento(numTipo, unNumDni);
        if (this.nodoClientes.localizar(doc) < 1) {
            exito = this.nodoClientes.insertar(new NodoCliente((new Cliente(doc, nombre, apellido, telefono, email))), nodoClientes.longitud() + 1);
        }
        return exito;
    }

    public boolean agregarNodoCliente(Cliente persona) {
        boolean exito = false;
        if (this.nodoClientes.localizar(persona) < 1) {
            exito = this.nodoClientes.insertar(new NodoCliente(persona), nodoClientes.longitud() + 1);
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

    public boolean eliminarNodoCliente(Documento doc) {
        boolean exito = false;
        if (!this.nodoClientes.esVacia()) {
            exito = nodoClientes.eliminar(nodoClientes.localizar(doc));
        }
        return exito;
    }

    public boolean eliminarNodoCliente(int tipoDoc, int numDoc) {
        boolean exito = false;
        Documento doc = new Documento(tipoDoc, numDoc);
        if (!this.nodoClientes.esVacia()) {
            exito = nodoClientes.eliminar(nodoClientes.localizar(doc));
        }
        return exito;
    }

    public NodoCliente encontrarNodoCliente(int tipoDoc, int numeroDoc) {
        NodoCliente salida = null;
        Documento doc = new Documento(tipoDoc, numeroDoc);
        if (!this.nodoClientes.esVacia()) {
            salida = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(doc));
        }
        return salida;
    }

    public NodoCliente encontrarNodoCliente(Documento doc) {
        NodoCliente salida = null;
        if (!this.nodoClientes.esVacia()) {
            salida = (NodoCliente) nodoClientes.recuperar(nodoClientes.localizar(doc));
        }
        return salida;
    }

    public boolean existeNodoCliente(int tipoDoc, int numeroDoc) {
        boolean existe = false;
        Documento doc = new Documento(tipoDoc, numeroDoc);
        if (!this.nodoClientes.esVacia()) {
            existe = !(this.nodoClientes.localizar(doc) < 1);
        }
        return existe;
    }

    public boolean existeNodoCliente(Documento doc) {
        boolean existe = false;
        if (!this.nodoClientes.esVacia()) {
            existe = !(this.nodoClientes.localizar(doc) < 1);
        }
        return existe;
    }

    public boolean equals(Object obj){
        CiudadesDeViaje clave = (CiudadesDeViaje) obj;
        return this.clave.equals(clave);
    }
}
