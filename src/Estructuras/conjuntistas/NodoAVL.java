package Estructuras.conjuntistas;

public class NodoAVL {
    private Object elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Object elem, NodoAVL izq, NodoAVL der){
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
        this.altura = 0;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura(){
        if (this.izquierdo != null && this.derecho != null) {
            if (this.izquierdo.getAltura() >= this.derecho.getAltura()){
                this.altura = this.izquierdo.getAltura()+1;
            } else {
                this.altura = this.derecho.getAltura()+1;
            }
        } else {
            if (this.izquierdo != null) {
                this.altura = this.izquierdo.getAltura()+1;
            } else {
                this.altura = this.derecho.getAltura()+1;
            }
        }
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izq) {
        this.izquierdo = izq;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL der) {
        this.derecho = der;
    }

    public boolean equalsEnlaceIzq(NodoAVL enlaceIzq){
        return this.izquierdo == enlaceIzq;
    }

    public boolean equalsEnlaceDer(NodoAVL enlaceDer){
        return this.derecho == enlaceDer;
    }
}
