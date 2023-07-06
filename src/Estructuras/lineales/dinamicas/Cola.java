/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras.lineales.dinamicas;

/**
 *
 * @author danyc
 */

/**
 * *********** Autores ***********
 * - Daniel Carrasco, FAI-2840
 * - Agustin Caceres FAI-2993
 * - Jonathan maximiliano cabrera, 108665
 */

public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        // Crea y devuelve una cola vacía
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        // Pone el elemento al final de la cola. 
        // Devuelve siempre verdadero porque se pudo agregar el elemento.
        Nodo nuevo = new Nodo(elem, null);
        if (this.frente == null) {
            //Si cola esta vacia, frente y fin se les asigna "nuevo"
            this.frente = nuevo;
        }
        if (this.fin == null) {
            //Si fin es null, le asigna nuevo
            this.fin = nuevo;
        } else {
            //Se enlaza con el nodo que indica this.fin y this.fin se le asigna "nuevo"
            this.fin.setEnlace(nuevo);
            this.fin = nuevo;
        }
        return true;
    }

    public boolean sacar() {
        // Saca el elemento que está en el frente de la cola. 
        // Devuelve verdadero si el elemento se pudo sacar (la estructura no estaba vacía) y falso en caso contrario.
        boolean exito = true;
        if (this.frente == null) {
            // la cola esta vacia, reporta error
            exito = false;
        } else {
            // al menos hay un elemento:
            // quita el primer elemento y actualiza frente (y fin si queda vacia)
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        // Devuelve el elemento que está en el frente. Precondición: la cola no está vacía.
        Object elem = null;
        if (this.frente != null) {
            elem = this.frente.getElem();
        }
        return elem;
    }

    public boolean esVacia() {
        // Devuelve verdadero si la cola no tiene elementos y falso en caso contrario
        return this.frente == null;
    }

    public void vaciar() {
        // Saca todos los elementos de la estructura.
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        // Devuelve una copia exacta de los datos en la estructura original, 
        // y respetando el orden de los mismos, en otra estructura del mismo tipo
        Cola clonCola = new Cola();
        if (this.frente == null) {
            clonCola.frente = null;
            clonCola.fin = null;
        } else {
            clonCola.frente = crearNodo(this.frente, clonCola.fin);
        }
        return clonCola;
    }

    private Nodo crearNodo(Nodo aux, Nodo clonFin) {
        //Metodo recursivo que devuelve un nodo utilizado en clone()
        //Parametros: nodo aux y nodo clonFin (referencia del clonCola.fin)
        Nodo nuevo;
        if (aux.getEnlace() != null) {
            //Crea un nodo con elemento de aux y enlaza con nodo de crearNodo
            nuevo = new Nodo(aux.getElem(), crearNodo(aux.getEnlace(), clonFin));
        } else {
            //Asigna al ultimo nodo clonFin
            nuevo = new Nodo(aux.getElem(), null);
            clonFin = nuevo;
        }
        return nuevo;
    }

    @Override
    public String toString() {
        // Crea y devuelve una cadena de caracteres formada por todos los elementos
        // de la cola para poder mostrarla por pantalla.
        String cadena = "Cola vacia";
        if (this.frente != null) {
            cadena = "[";
            Nodo aux = this.frente;
            while (aux != null) {
                cadena = cadena + aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    cadena = cadena + " ;";
                }
            }
            cadena = cadena + "]";
        }
        return cadena;
    }
}
