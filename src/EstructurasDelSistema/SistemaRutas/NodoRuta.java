package EstructurasDelSistema.SistemaRutas;

import Estructuras.conjuntistas.NodoAdyEtiq;
import Estructuras.conjuntistas.NodoVertEtiq;

public class NodoRuta {
    private NodoCiudad vertice;
    private NodoRuta sigRuta;
    private double etiqueta;

    public NodoRuta(NodoCiudad vertice, NodoRuta sigRuta, double etiqueta){
        this.vertice = vertice;
        this.sigRuta = sigRuta;
        this.etiqueta = etiqueta;
    }

    public NodoCiudad getVertice() {
        return vertice;
    }

    public void setVertice(NodoCiudad vertice) {
        this.vertice = vertice;
    }

    public NodoRuta getSigRuta() {
        return sigRuta;
    }

    public void setSigRuta(NodoRuta sigRuta) {
        this.sigRuta = sigRuta;
    }

    public double getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(double etiqueta) {
        this.etiqueta = etiqueta;
    }
}
