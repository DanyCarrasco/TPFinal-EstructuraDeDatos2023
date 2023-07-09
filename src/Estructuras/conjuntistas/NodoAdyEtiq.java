package Estructuras.conjuntistas;

public class NodoAdyEtiq {
    private NodoVertEtiq vertice;
    private NodoAdyEtiq sigAdyacente;
    private Object etiqueta;

    public NodoAdyEtiq(NodoVertEtiq vertice, NodoAdyEtiq sigAdyacente, Object etiqueta){
        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
        this.etiqueta = etiqueta;
    }

    public NodoVertEtiq getVertice() {
        return vertice;
    }

    public void setVertice(NodoVertEtiq vertice) {
        this.vertice = vertice;
    }

    public NodoAdyEtiq getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdyEtiq sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public Object getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }
}