/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras.lineales.dinamicas;


/**
 * @author danyc
 */

/**
 * *********** Autores ***********
 * - Daniel Carrasco, FAI-2840
 * - Agustin Caceres FAI-2993
 * - Jonathan maximiliano cabrera, 108665
 */

public class Lista {

    private Nodo cabecera;
    private int longi;

    public Lista() {
        this.cabecera = null;
        this.longi = 0;
    }

    public boolean insertar(Object elem, int pos) {
        // inserta el elemento nuevo en la posicion pos
        // detecta y reporta error posicion invalida
        boolean exito = true;
        if (pos < 1 || pos > this.longi + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(elem, cabecera);
            } else {
                Nodo aux = buscarNodo(1, this.cabecera, pos - 1);
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longi++;
        }
        return exito;
    }

    private Nodo buscarNodo(int i, Nodo aux, int pos) {
        // metodo para buscar el nodo i-1 y que lo retorne
        // podria funcionar con this.cabecera ??
        Nodo encontrado;
        if (i == pos) {
            encontrado = aux;
        } else {
            encontrado = buscarNodo(i + 1, aux.getEnlace(), pos);
        }
        return encontrado;
    }

    public boolean eliminar(int pos) {
        // casos especiales: pos esta fuera del rango, lista es vacia y pos = 1
        boolean exito = false;
        if (this.cabecera != null) {
            if (1 <= pos && pos <= this.longi) {
                if (pos == 1) {
                    this.cabecera = this.cabecera.getEnlace();
                } else {
                    Nodo posAnterior = buscarNodo(1, this.cabecera, (pos - 1));
                    posAnterior.setEnlace(posAnterior.getEnlace().getEnlace());
                }
                this.longi = this.longi - 1;
                exito = true;
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        // si la lista es vacia, retorna null
        Object elem = null;
        if (this.cabecera != null) {
            if (1 <= pos && pos <= this.longi) {
                if (pos == 1) {
                    elem = this.cabecera.getElem();
                } else {
                    elem = buscarNodo(1, this.cabecera, pos).getElem();
                }
            }
        }
        return elem;
    }

    public int localizar(Object elem) {
        // caso especial: cuando no lo encuentra y cuando es lista vacia
        int pos;
        if (this.cabecera == null) {
            // si es lista vacia, retorna 0
            pos = 0;
        } else {
            pos = buscarElem(elem, this.cabecera, 1);
        }
        return pos;
        /*int rta = 0;
        Nodo aux = this.cabecera;
        int i = 1;
        if (aux.getElem().equals(elem)==true) {
            rta = 0;
            } else {
            while (aux.getElem()!= null && aux.getElem().equals(elem) == false){
                aux = aux.getEnlace();
                i++;
            }
        }
        if (i >= longitud()) {
            rta = -1;
        } else {
            rta=i;
        }
        return rta;*/
    }

    private int buscarElem(Object elem, Nodo aux, int num) {
        // metodo para verificar la ubicacion de nodo con elemento y retorna su ubicacion
        int encontrado;
        if (aux.getElem().equals(elem)) {
            encontrado = num;
        } else {
            if (aux.getEnlace() == null) {
                // Si llega al ultimo nodo de lista y no lo encuentra
                encontrado = -1;
            } else {
                encontrado = buscarElem(elem, aux.getEnlace(), num + 1);
            }
        }
        return encontrado;
    }

    public void vaciar() {
        //Quita todos los elementos de la lista
        this.cabecera = null;
    }

    public boolean esVacia() {
        // Devuelve verdadero si la lista no tiene elementos y falso en caso contrario
        return this.cabecera == null;
    }

    public int longitud() {
        // Devuelve la cantidad de elementos de la lista.
        // si no tengo this.longi, tendria que recorrerlo con while o repetitiva
        return this.longi;
    }

    public Lista clone() {
        /* Devuelve una copia exacta de los datos en la estructura original, y respetando el orden de los mismos,
        en otra estructura del mismo tipo*/
        Lista clon = new Lista();
        if (this.cabecera != null) {
            clon.cabecera = clonarNodo(this.cabecera, clon.cabecera);
            clon.longi = this.longi;
        }
        return clon;
    }

    private Nodo clonarNodo(Nodo aux, Nodo clon) {
        // si no tenemos longi, significa que tendremos que poner un parametro int
        // para calcular la longitud de la lista original
        Nodo nuevo = new Nodo(aux.getElem(), null);
        if (aux.getEnlace() != null) {
            nuevo.setEnlace(clonarNodo(aux.getEnlace(), clon));
        }
        clon = nuevo;
        return clon;
    }

    public String toString() {
        /* Crea y devuelve una cadena de caracteres formada por todos los elementos de la lista para poder
        mostrarla por pantalla*/
        String cadena;
        if (this.cabecera != null) {
            cadena = "[";
            cadena = cadena + cadenaRecursiva("", this.cabecera);
            cadena = cadena + "]";
        } else {
            cadena = "Lista vacia";
        }
        return cadena;
    }

    private String cadenaRecursiva(String cad, Nodo aux) {
        // cad debe ser "" y aux debe ser this.cabecera cuando se llame desde afuera
        if (aux != null) {
            cad = cad + aux.getElem().toString();
            if (aux.getEnlace() != null) {
                cad = cad + ";";
            }
            cad = cadenaRecursiva(cad, aux.getEnlace());
        }
        return cad;
    }

    private String cadenaConWhile(String cad, Nodo aux) {
        // cad debe ser "" y aux debe ser this.cabecera cuando se llame desde afuera
        while (aux != null) {
            cad = cad + aux.getElem();
            if (aux.getEnlace() != null) {
                cad = cad + ",";
            }
            aux = aux.getEnlace();
        }
        return cad;
    }

    public void invertir() {
        /*modifica la lista original para que los elementos aparezcan en orden invertido, haciendo un
        único recorrido de la estructura y sin usar estructuras auxiliares ni otras operaciones del TDA.*/
        //Ej: Lista [1,2,3,4] ---> Lista invertida [4,3,2,1]
        if (this.cabecera != null) {
            invertirAux(this.cabecera, null);
        }
    }

    private void invertirAux(Nodo n, Nodo ant) {
        if (n.getEnlace() != null) {
            invertirAux(n.getEnlace(), n);
        } else {
            this.cabecera = n;
        }
        n.setEnlace(ant);
    }

    public void eliminarApariciones(Object elem) {
        /*elimina todas las apariciones de elementos iguales a "elem",
        haciendo un único recorrido de la estructura y sin usar otras operaciones del TDA.*/
        //Ej: lista [1,2,1,3,4] y elem = 1 ---> lista modificada [2,3,4]
        if (this.cabecera != null) {
            eliminarAparicionesAux(this.cabecera, null, elem);
        }
    }

    private void eliminarAparicionesAux(Nodo n, Nodo ant, Object elem) {
        if (n != null) {
            if (n.getElem().equals(elem)) {
                if (ant == null) {
                    this.cabecera = this.cabecera.getEnlace();
                    eliminarAparicionesAux(n.getEnlace(), null, elem);
                } else {
                    ant.setEnlace(n.getEnlace());
                    eliminarAparicionesAux(n.getEnlace(), ant, elem);
                }
                n.setEnlace(null);
            } else {
                eliminarAparicionesAux(n.getEnlace(), n, elem);
            }
        }
    }
}
