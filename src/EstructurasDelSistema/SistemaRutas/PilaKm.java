package EstructurasDelSistema.SistemaRutas;

import Estructuras.lineales.dinamicas.Lista;
import Estructuras.lineales.dinamicas.Pila;

public class PilaKm {
    private Pila unaPila;
    private double contador;

    public PilaKm() {
        this.unaPila = new Pila();
        this.contador = 0;
    }

    public boolean apilar(Object elem) {
        double n = (double) elem;
        contador = contador + n;
        return unaPila.apilar(n);
    }

    public boolean desapilar() {
        double tope = (double) unaPila.obtenerTope();
        boolean exito = unaPila.desapilar();
        if (exito){
            contador = contador - tope;
        }
        return unaPila.desapilar();
    }

    public double getContador(){
        return contador;
    }

    public boolean esVacio(){
        return this.unaPila.esVacia() && this.contador == 0;
    }

    public void vaciar(){
        this.unaPila.vaciar();
        this.contador = 0;
    }
}
