package clases;

public class Cliente {
    private Documento doc;
    private String nombre;
    private String apellido;
    private int telefono;
    private String email;

    public Cliente(int numTipo, int unNumDni){
        this.doc = new Documento(numTipo, unNumDni);
    }
    public Cliente(int numTipo, int unNumDni, String nombre, String apellido, int telefono, String email) {
        this.doc = new Documento(numTipo, unNumDni);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public String getTipoDocumento(){
        return this.doc.getTipo();
    }

    public int getNumeroDocumento(){
        return this.doc.getNumero();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
