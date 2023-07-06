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
public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        // Pone el elemento nuevoElem en el tope de la pila. 
        // Devuelve verdadero si el elemento se pudo apilar y falso en caso contrario.
        //crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);

        //actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;

        //nunca hay error de pila llena, entonces devuelve true
        return true;
    }

    public boolean desapilar() {
        // Saca el elemento del tope de la pila. 
        // Devuelve verdadero si la pila no estaba vacía al momento de desapilar (es decir que se pudo desapilar) y falso en caso contrario.
        boolean exito = false;
        if (this.tope != null) {
            //Actualiza tope apuntando al siguiente nodo
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        // Devuelve el elemento en el tope de la pila. 
        // Precondición: la pila no está vacía.
        Object elem = null; //Si esta vacio, retorna null
        if (this.tope != null) {
            elem = this.tope.getElem();
        }
        return elem;
    }

    public boolean esVacia() {
        // Devuelve verdadero si la pila no tiene elementos y falso en caso contrario.
        return this.tope == null;
    }

    public void vaciar() {
        // Saca todos los elementos de la pila.
        this.tope = null;
    }

    public Pila clone() {
        // Devuelve una copia exacta de los datos en la estructura original, 
        // y respetando el orden de los mismos, en otra estructura del mismo tipo
        Pila clonPila = new Pila();
        if (this.tope == null) {
            clonPila.tope = null;
        } else {
            clonPila.tope = crearNodo(this.tope);
        }
        return clonPila;
    }

    private Nodo crearNodo(Nodo aux) {
        //Metodo recursivo utilizado en clone() que retorna nodo "nuevo"
        //Parametro: nodo aux (tope de pila original)
        Nodo nuevo;
        if (aux.getEnlace() != null) {
            //Crea nodo "nuevo" con elemento de aux y enlaza con metodo recursivo
            nuevo = new Nodo(aux.getElem(), crearNodo(aux.getEnlace()));
        } else {
            //Es el ultimo nodo de pila, lo enlaza con null
            nuevo = new Nodo(aux.getElem(), null);
        }
        return nuevo;
    }

    @Override
    public String toString() {
        // Devuelve una cadena de caracteres formada por todos los elementos de 
        // la pila para poder mostrarla por pantalla.
        String s = "";
        if (this.tope == null) {
            s = "Pila vacia";
        } else {
            //se ubica para recorrer la pila
            Nodo aux = this.tope;
            s = "[";
            while (aux != null) {
                //agrega el textp del elem y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ";";
                }
            }
            s += "]";
        }
        return s;
    }
}
