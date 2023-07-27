package Estructuras.DiccionarioAVL;

public class NodoAVLDicc {
    private Object clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc izquierdo;
    private NodoAVLDicc derecho;

    public NodoAVLDicc(Object clave, Object dato, NodoAVLDicc izq, NodoAVLDicc der){
        this.clave = clave;
        this.dato = dato;
        this.izquierdo = izq;
        this.derecho = der;
        this.altura = 0;
    }

    public Object getClave(){
        return clave;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
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
                if (this.derecho != null){
                    this.altura = this.derecho.getAltura()+1;
                } else {
                    this.altura = 0;
                }
            }
        }
    }

    public NodoAVLDicc getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVLDicc izq) {
        this.izquierdo = izq;
    }

    public NodoAVLDicc getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVLDicc der) {
        this.derecho = der;
    }

    public boolean equalsEnlaceIzq(NodoAVLDicc enlaceIzq){
        return this.izquierdo == enlaceIzq;
    }

    public boolean equalsEnlaceDer(NodoAVLDicc enlaceDer){
        return this.derecho == enlaceDer;
    }
}
