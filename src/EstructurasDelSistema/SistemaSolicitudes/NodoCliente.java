package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

public class NodoCliente {
    private Cliente persona;
    private Lista descripciones;

    public NodoCliente(Cliente persona){
        this.persona = persona;
        descripciones = new Lista();
    }

    public NodoCliente(int numTipo, int unNumDni, String nombre, String apellido, int telefono, String email){
        this.persona = new Cliente(numTipo,unNumDni, nombre, apellido, telefono, email);
        descripciones = new Lista();
    }

    public NodoCliente(Cliente persona,Descripcion des){
        this.persona = persona;
        descripciones = new Lista();
        descripciones.insertar(des, descripciones.longitud()+1);
    }

    public NodoCliente(int numTipo, int unNumDni, String nombre, String apellido, int telefono, String email, Descripcion des){
        this.persona = new Cliente(numTipo,unNumDni, nombre, apellido, telefono, email);
        descripciones = new Lista();
        descripciones.insertar(des, descripciones.longitud()+1);
    }

    public Cliente getCliente(){
        return this.persona;
    }

    public boolean agregarDescripcion(Descripcion des){
        boolean exito = false;
        if (descripciones.localizar(des) < 1 && this.persona != null) {
            exito = descripciones.insertar(des, descripciones.longitud()+1);
        }
        return exito;
    }

    public boolean eliminarDescripcion(Descripcion des){
        boolean exito = false;
        if (this.persona != null && !descripciones.esVacia()) {
            exito = this.descripciones.eliminar(this.descripciones.localizar(des));
        }
        return exito;
    }

    public Descripcion getDescripcion(String fecha){
        Descripcion salida = null;
        if (persona != null && !this.descripciones.esVacia()) {
            salida = (Descripcion) this.descripciones.recuperar(descripciones.localizar(fecha));
        }
        return salida;
    }

    public boolean equals(Object obj){
        Documento doc = (Documento) obj;
        return this.persona.equals(doc);
    }
}
