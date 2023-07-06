package clases;

public class Documento {
    private tipoDocumento tiposDoc;
    private String tipo;
    private int numero;

    private void asignarTipo(int num) {
        tiposDoc = new tipoDocumento();
        tipo = tiposDoc.getTipoDocumento(num);
    }

    public Documento(int numTipo, int numero){
        asignarTipo(numTipo);
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
