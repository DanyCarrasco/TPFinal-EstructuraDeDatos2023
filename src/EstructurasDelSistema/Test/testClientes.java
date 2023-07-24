package EstructurasDelSistema.Test;

import EstructurasDelSistema.Clientes;

import java.math.BigInteger;

public class testClientes {
    public static void main(String[] args) {
        //testToString();
        testInsertarYEliminar();
    }

    public static void testToString() {
        Clientes c1 = new Clientes();
        System.out.println("Clientes c1 vacio: " + c1.toString());
        System.out.println("Se inserta un cliente: " + c1.insertarCliente(1, 43552859, "DANIEL", "CARRASCO", 155462482, "danycarrasco077@gmail.com"));
        System.out.println("Clientes c1: " + c1.toString());
    }

    public static void testInsertarYEliminar() {
        Clientes c2 = new Clientes();
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se inserta un cliente: " + c2.insertarCliente(1, 43562859, "DANIEL", "CARRASCO", 155467482, "danycarrasco893@gmail.com"));
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se inserta otro cliente: " + c2.insertarCliente(1, 87623786, "ALBERTO", "LOPEZ", 299528282, "albLopez092@gmail.com"));
        System.out.println("Clientes c2: \n" + c2.toString());
        c2.insertarCliente(1, 45367372, "PERSONA", "UNO", 299848773, "persona1@gmail.com");
        c2.insertarCliente(2, 43526261, "PERSONA", "DOS", 546376722, "persona2@gmail.com");
        c2.insertarCliente(1, 46372729, "PERSONA", "TRES", 299383872, "persona3@gmail.com");
        System.out.println("Se insertaron los clientes");
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se quita PERSONA DOS: " + c2.eliminarCliente(1, 45367372));
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se quita DANIEL CARRASCO: " + c2.eliminarCliente(1, 43552859));
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Clientes c2: \n" + c2.toStringClave());
        System.out.println("Se verifica si existe DANIEL CARRASCO: "+ c2.existeCliente(1,43552859));
        System.out.println("Se verifica si existe PERSONA DOS: "+ c2.existeCliente(2,43526261));
        c2.vaciar();
        System.out.println("Clientes c2: \n" + c2.toString());
    }
}
