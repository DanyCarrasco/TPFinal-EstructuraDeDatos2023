package EstructurasDelSistema;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Documento;
import clases.TipoDocumento;

import java.util.HashMap;
import java.lang.Object;
import java.util.Iterator;
import java.util.Map;

public class Clientes {
    private HashMap<Documento, Cliente> arbol;

    public Clientes() {
        arbol = new HashMap<Documento, Cliente>();
    }

    public boolean existeCliente(int tipoDoc, int numDoc) {
        Documento doc = new Documento(tipoDoc, numDoc);
        return arbol.containsKey(doc);
    }

    public boolean insertarCliente(int tipoDoc, int numeroDoc, String nombre, String apellido, int telefono, String email) {
        boolean exito = false;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (!arbol.containsKey(clave)) {
            Cliente nuevo = new Cliente(tipoDoc, numeroDoc, nombre, apellido, telefono, email);
            arbol.put(clave, nuevo);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarCliente(int tipoDoc, int numeroDoc){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            arbol.remove(clave);
            exito = true;
        }
        return exito;
    }

    public Cliente getCliente(int tipoDoc, int numeroDoc) {
        Cliente salida = null;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            salida = (Cliente) arbol.get(clave);
        }
        return salida;
    }

    public String getNombreCliente(int tipoDoc, int numeroDoc) {
        Cliente buscado = null;
        String nombre = "";
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            buscado = (Cliente) arbol.get(clave);
            nombre = buscado.getNombre();
        }
        return nombre;
    }

    public boolean setNombreCliente(int tipoDoc, int numeroDoc, String nombre) {
        boolean exito = false;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            Cliente original = (Cliente) arbol.get(clave);
            original.setNombre(nombre);
            arbol.remove(clave);
            exito = arbol.put(clave, original) != null;
        }
        return exito;
    }

    public String getApellidoCliente(int tipoDoc, int numeroDoc) {
        Cliente buscado = null;
        String apellido = "";
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            buscado = (Cliente) arbol.get(clave);
            apellido = buscado.getApellido();
        }
        return apellido;
    }

    public boolean setApellidoCliente(int tipoDoc, int numeroDoc, String apellido) {
        boolean exito = false;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            Cliente original = (Cliente) arbol.get(clave);
            original.setApellido(apellido);
            arbol.remove(clave);
            exito = arbol.put(clave, original) != null;
        }
        return exito;
    }

    public int getTelefonoCliente(int tipoDoc, int numeroDoc) {
        Cliente buscado = null;
        int telefono = 0;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            buscado = (Cliente) arbol.get(clave);
            telefono = buscado.getTelefono();
        }
        return telefono;
    }

    public boolean setTelefonoCliente(int tipoDoc, int numeroDoc, int telefono) {
        boolean exito = false;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            Cliente original = (Cliente) arbol.get(clave);
            original.setTelefono(telefono);
            arbol.remove(clave);
            exito = arbol.put(clave, original) != null;
        }
        return exito;
    }

    public String getEmailCliente(int tipoDoc, int numeroDoc) {
        Cliente buscado = null;
        String email = "";
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            buscado = (Cliente) arbol.get(clave);
            email = buscado.getEmail();
        }
        return email;
    }

    public boolean setEmailCliente(int tipoDoc, int numeroDoc, String email) {
        boolean exito = false;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            Cliente original = (Cliente) arbol.get(clave);
            original.setEmail(email);
            arbol.remove(clave);
            exito = arbol.put(clave, original) != null;
        }
        return exito;
    }

    public String mostrarCliente(int tipoDoc, int numeroDoc) {
        String cad = "No se encuentra cliente";
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)) {
            cad = arbol.get(clave).toString();
        }
        return cad;
    }

    private String mostrarElementos(Iterable s) {
        String cad = "";
        Iterator it = s.iterator();
        while (it.hasNext()) {
            cad = cad + " - " + it.next() + "\n";
        }
        return cad;
    }

    public boolean esVacio(){
        return arbol.isEmpty();
    }

    public void vaciar(){
        arbol.clear();
    }

    public String toString() {
        String cad = "HashMap de clientes: \n";
        if (arbol.isEmpty()) {
            cad = cad + "vacio";
        } else {
            cad = cad +  mostrarElementos(arbol.entrySet());;
        }
        return cad;
    }

    public Lista obtenerConjuntoDominio(){
        Lista salida = new Lista();
        if (!arbol.isEmpty()){
            guardarElementos(arbol.keySet(),salida);
        }
        return salida;
    }

    public Lista obtenerConjuntoRango(){
        Lista salida = new Lista();
        if (!arbol.isEmpty()){
            guardarElementos(arbol.values(),salida);
        }
        return salida;
    }

    private void guardarElementos(Iterable t, Lista lis){
        Iterator it= t.iterator();
        while (it.hasNext()){
            lis.insertar(it.next(),lis.longitud()+1);
        }
    }

    public String toStringClave(){
        return "Claves de HashMap de clientes: \n"+ mostrarElementos(arbol.keySet());
    }
}