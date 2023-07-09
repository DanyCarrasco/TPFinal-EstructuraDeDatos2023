package Estructuras.conjuntistas;

public class NodoVertEtiq {
    private Object elem;
    private NodoVertEtiq sigVertice;
    private NodoAdyEtiq primerAdy;

    public NodoVertEtiq(Object elem, NodoVertEtiq sigVertice) {
        this.elem = elem;
        this.sigVertice = sigVertice;
        this.primerAdy = null;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoVertEtiq getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVertEtiq sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdyEtiq getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdyEtiq primerAdy) {
        this.primerAdy = primerAdy;
    }
}
