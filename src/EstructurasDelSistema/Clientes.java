package EstructurasDelSistema;
import clases.Cliente;
import clases.Documento;
import clases.TipoDocumento;
import java.util.HashMap;
import java.lang.Object;

public class Clientes {
    private HashMap arbol;

    public Clientes(){
        arbol = new HashMap();
    }

    public boolean existeCliente(TipoDocumento tipo){
        return arbol.containsKey(tipo);
    }

    public boolean insertarCliente(int tipoDoc, int numeroDoc, String nombre, String apellido, int telefono, String email){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc, numeroDoc);
        if (arbol.containsKey(clave)){
            Cliente nuevo = new Cliente(tipoDoc, numeroDoc, nombre, apellido, telefono,email);
            exito = arbol.put(clave, nuevo) != null;
        }
        return exito;
    }

    public boolean eliminarCliente(int tipoDoc, int numeroDoc){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            exito = arbol.remove(clave) != null;
        }
        return exito;
    }

    public Cliente getCliente(int tipoDoc, int numeroDoc){
        Cliente salida = null;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            salida = (Cliente) arbol.get(clave);
        }
        return salida;
    }

    public String getNombreCliente(int tipoDoc, int numeroDoc){
        Cliente buscado = null;
        String nombre = "";
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            buscado = (Cliente) arbol.get(clave);
            nombre = buscado.getNombre();
        }
        return nombre;
    }

    public boolean setNombreCliente(int tipoDoc, int numeroDoc, String nombre){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            Cliente original = (Cliente) arbol.get(clave);
            original.setNombre(nombre);
            arbol.remove(clave);
            exito = arbol.put(clave,original) != null;
        }
        return exito;
    }

    public String getApellidoCliente(int tipoDoc, int numeroDoc){
        Cliente buscado = null;
        String apellido = "";
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            buscado = (Cliente) arbol.get(clave);
            apellido = buscado.getApellido();
        }
        return apellido;
    }

    public boolean setApellidoCliente(int tipoDoc, int numeroDoc, String apellido){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            Cliente original = (Cliente) arbol.get(clave);
            original.setApellido(apellido);
            arbol.remove(clave);
            exito = arbol.put(clave,original) != null;
        }
        return exito;
    }

    public int getTelefonoCliente(int tipoDoc, int numeroDoc){
        Cliente buscado = null;
        int telefono = 0;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            buscado = (Cliente) arbol.get(clave);
            telefono = buscado.getTelefono();
        }
        return telefono;
    }

    public boolean setApellidoCliente(int tipoDoc, int numeroDoc, int telefono){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            Cliente original = (Cliente) arbol.get(clave);
            original.setTelefono(telefono);
            arbol.remove(clave);
            exito = arbol.put(clave,original) != null;
        }
        return exito;
    }

    public String getEmailCliente(int tipoDoc, int numeroDoc){
        Cliente buscado = null;
        String email = "";
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            buscado = (Cliente) arbol.get(clave);
            email = buscado.getEmail();
        }
        return email;
    }

    public boolean setEmailCiudad(int tipoDoc, int numeroDoc, int telefono){
        boolean exito = false;
        Documento clave = new Documento(tipoDoc,numeroDoc);
        if (arbol.containsKey(clave)){
            Cliente original = (Cliente) arbol.get(clave);
            original.setTelefono(telefono);
            arbol.remove(clave);
            exito = arbol.put(clave,original) != null;
        }
        return exito;
    }
}