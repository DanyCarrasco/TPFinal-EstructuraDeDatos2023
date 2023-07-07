package conjuntistas;

public class NodoAVL {
    private Object clave;
    private Object dato;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Object clave, Object dato, NodoAVL izq, NodoAVL der){
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
