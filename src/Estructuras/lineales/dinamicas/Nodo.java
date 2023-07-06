/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras.lineales.dinamicas;

/**
 *
 * @author danyc
 */
class Nodo {

    private Object elem;
    private Nodo enlace;

    //constructor
    public Nodo(Object elem, Nodo enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    //modificadores
    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

    //observadores
    public Object getElem() {
        return elem;
    }

    public Nodo getEnlace() {
        return enlace;
    }
}
