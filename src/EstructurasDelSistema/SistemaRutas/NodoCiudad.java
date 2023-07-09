package EstructurasDelSistema.SistemaRutas;

import Estructuras.conjuntistas.NodoAdyEtiq;
import Estructuras.conjuntistas.NodoVertEtiq;

public class NodoCiudad {
    private Object elem;
    private NodoCiudad sigVertice;
    private NodoRuta primerRuta;

    public NodoCiudad(Object elem, NodoCiudad sigVertice) {
        this.elem = elem;
        this.sigVertice = sigVertice;
        this.primerRuta = null;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoCiudad getSigCiudad() {
        return sigVertice;
    }

    public void setSigCiudad(NodoCiudad sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoRuta getPrimerRuta() {
        return primerRuta;
    }

    public void setPrimerRuta(NodoRuta primerRuta) {
        this.primerRuta = primerRuta;
    }
}
