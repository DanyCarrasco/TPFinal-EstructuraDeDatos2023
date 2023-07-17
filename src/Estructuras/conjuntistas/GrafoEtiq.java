package Estructuras.conjuntistas;

import Estructuras.lineales.dinamicas.Cola;
import Estructuras.lineales.dinamicas.Lista;
import Estructuras.conjuntistas.NodoVertEtiq;
import Estructuras.conjuntistas.NodoAdyEtiq;

public class GrafoEtiq {
    private NodoVertEtiq inicio;

    public GrafoEtiq() {
        this.inicio = null;
    }

    private NodoVertEtiq ubicarVertice(Object buscado) {
        //Busca hasta encontrar el vertice buscado en la lista de vertice
        NodoVertEtiq aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        /* Dado un elemento de TipoVertice se lo agrega a la estructura controlando que no se inserten
        vértices repetidos. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.*/
        boolean exito = false;
        NodoVertEtiq aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVertEtiq(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object vertice) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        NodoVertEtiq aux = this.inicio;
        NodoVertEtiq auxAnterior = null;
        while (!exito && aux != null) {
            if (aux.getElem().equals(vertice)) {
                eliminarArcos(aux);
                if (auxAnterior == null) {
                    this.inicio = aux.getSigVertice();
                } else {
                    auxAnterior.setSigVertice(aux.getSigVertice());
                }
                exito = true;
            }
            auxAnterior = aux;
            aux = aux.getSigVertice();
        }
        /*Se puede usar tambien el modulo recursivo eliminarVerticeAux y comentar todo desde NodoVert aux = this.inicio*/
        //exito = eliminarVerticeAux(this.inicio, null, vertice);
        return exito;
    }

    private boolean eliminarVerticeAux(NodoVertEtiq nVertice, NodoVertEtiq nVerticeAnterior, Object verticeBuscado) {
        //Metodo recursivo para moverme en la lista de vertice hasta encontrar el verticeBuscado para eliminar
        boolean exito = false;
        if (nVertice != null) {
            if (nVertice.getElem().equals(verticeBuscado)) {
                eliminarArcos(nVertice);
                if (nVerticeAnterior == null) {
                    this.inicio = nVertice.getSigVertice();
                } else {
                    nVerticeAnterior.setSigVertice(nVertice.getSigVertice());
                }
                exito = true;
            } else {
                exito = eliminarVerticeAux(nVertice.getSigVertice(), nVertice, verticeBuscado);
            }
        }
        return exito;
    }

    private void eliminarArcos(NodoVertEtiq n) {
        //Modulo para eliminar los arcos del nodo "n"
        NodoAdyEtiq nAdyacente = n.getPrimerAdy();
        while (nAdyacente != null) {
            eliminarUnArco(nAdyacente.getVertice(), n.getElem());
            n.setPrimerAdy(nAdyacente.getSigAdyacente());
            nAdyacente = nAdyacente.getSigAdyacente();
        }
    }

    private boolean eliminarUnArco(NodoVertEtiq n, Object buscado) {
        //Confirma la eliminacion del nodo adyacente "buscado" de la lista de adyacentes del nodo "n"
        boolean exito = false;
        if (n != null) {
            if (n.getPrimerAdy() != null) {
                if (n.getPrimerAdy().getVertice().getElem().equals(buscado)) {
                    n.setPrimerAdy(n.getPrimerAdy().getSigAdyacente());
                    exito = true;
                } else {
                    exito = eliminarUnArcoAux(n.getPrimerAdy().getSigAdyacente(), n.getPrimerAdy(), buscado);
                }
            }
        }
        return exito;
    }

    private boolean eliminarUnArcoAux(NodoAdyEtiq n, NodoAdyEtiq nAnterior, Object buscado) {
        //Modulo recursivo para moverse en la lista de adyacentes del nodo "n" hasta encontrar
        // y confirmar la eliminacion el nodo "buscado"
        boolean exito = false;
        if (n != null) {
            if (n.getVertice().getElem().equals(buscado)) {
                nAnterior.setSigAdyacente(n.getSigAdyacente());
                exito = true;
            } else {
                exito = eliminarUnArcoAux(n.getSigAdyacente(), n, buscado);
            }
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        /* Dados dos elementos de TipoVertice (origen y destino) agrega el arco en la estructura, sólo si
        ambos vértices ya existen en el grafo. Si puede realizar la inserción devuelve verdadero, en caso
        contrario devuelve falso.*/
        //Forma mas eficiente
        boolean exito = false;
        NodoVertEtiq aux = this.inicio;
        NodoVertEtiq nOrigen = null;
        NodoVertEtiq nDestino = null;
        while (((nOrigen == null) || (nDestino == null)) && (aux != null)) {
            if (aux.getElem().equals(origen)) {
                nOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nDestino = aux;
            }
            aux = aux.getSigVertice();
        }
        if (nOrigen != null && nDestino != null) {
            insertarAdyacente(nOrigen, nDestino, etiqueta);
            insertarAdyacente(nDestino, nOrigen, etiqueta);
            exito = true;
        }
        //exito = insertarArcoAux(this.inicio, origen, destino, etiqueta)
        return exito;
    }

    private boolean insertarArcoAux(NodoVertEtiq n, Object origen, Object destino, Object etiqueta) {
        //Modulo recursivo que busca hasta encontrar el nodo vertice origen en lista de vertices del grafo
        //No es la mas eficiente porque busca de forma recursiva dos veces para buscar origen y destino
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(origen)) {
                NodoVertEtiq nDestino = ubicarVertice(destino);
                if (nDestino != null) {
                    //si no es encontrado el nodo vertice destino, termina y retorna false
                    insertarAdyacente(n, nDestino, etiqueta);
                    insertarAdyacente(nDestino, n, etiqueta);
                    exito = true;
                }
            } else {
                exito = insertarArcoAux(n.getSigVertice(), origen, destino, etiqueta);
            }
        }
        return exito;
    }

    private void insertarAdyacente(NodoVertEtiq n, NodoVertEtiq nEnlace, Object etiq) {
        //Inserta el nodo nVertice en la lista de adyacentes del nodo n
        if (n != null) {
            if (n.getPrimerAdy() == null) {
                n.setPrimerAdy(new NodoAdyEtiq(nEnlace, null, etiq));
            } else {
                insertarAdyacenteAux(n.getPrimerAdy(), nEnlace, etiq);
            }
        }
    }

    private void insertarAdyacenteAux(NodoAdyEtiq nAdyacente, NodoVertEtiq nEnlace, Object etiq) {
        //Modulo recursivo para insertar el nodo nVertice en la lista de adyacentes del nodo n
        if (nAdyacente != null) {
            if (nAdyacente.getSigAdyacente() == null) {
                nAdyacente.setSigAdyacente(new NodoAdyEtiq(nEnlace, null, etiq));
            } else {
                insertarAdyacenteAux(nAdyacente.getSigAdyacente(), nEnlace, etiq);
            }
        }
    }

    public boolean eliminarArco(Object origen, Object destino) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        NodoVertEtiq aux = this.inicio;
        NodoVertEtiq nOrigen = null;
        NodoVertEtiq nDestino = null;
        while (((nOrigen == null) || (nDestino == null)) && (aux != null)) {
            if (aux.getElem().equals(origen)) {
                nOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nDestino = aux;
            }
            aux = aux.getSigVertice();
        }
        if (nOrigen != null && nDestino != null) {
            exito = eliminarUnArco(nOrigen, destino);
            if (exito) {
                exito = eliminarUnArco(nDestino, origen);
            }
        }
        return exito;
    }


    public boolean existeVertice(Object vertice) {
        // Dado un elemento, devuelve verdadero si está en la estructura y falso en caso contrario.
        return ubicarVertice(vertice) != null;
    }

    public boolean existeArco(Object origen, Object destino) {
        /* Dados dos elementos de TipoVertice (origen y destino), devuelve verdadero si existe un arco en
        la estructura que los une y falso en caso contrario.*/
        return ubicarVerticeAdyacente(ubicarVertice(origen), destino) != null;
    }

    private NodoAdyEtiq ubicarVerticeAdyacente(NodoVertEtiq n, Object buscado) {
        //Busca y retorna el nodo vertice buscado en la lista de adyacentes de n
        NodoAdyEtiq aux = null;
        if (n != null) {
            aux = n.getPrimerAdy();
            while (aux != null && !aux.getVertice().getElem().equals(buscado)) {
                aux = aux.getSigAdyacente();
            }
        }
        return aux;
    }

    public Lista listarEnProfundidad() {
        /*Devuelve una lista con los vértices del grafo visitados según el recorrido en profundidad explicado
        en la sección anterior.*/
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        NodoVertEtiq aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 1) {
                //si el vertice no fue visitados aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVertEtiq n, Lista vis) {
        if (n != null) {
            //marca al vertice n como visitados
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdyEtiq ady = n.getPrimerAdy();
            while (ady != null) {
                // visita en profundidad los adyacentes de n aun no visitados
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public boolean existeCamino(Object origen, Object destino) {
        /*Dados dos elementos de TipoVertice (origen y destino), devuelve verdadero si existe al menos
        un camino que permite llegar del vértice origen al vértice destino y falso en caso contrario.*/
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoVertEtiq auxO = null;
        NodoVertEtiq auxD = null;
        NodoVertEtiq aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVertEtiq n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            exito = true;
        } else {
            //si no es el destino verifica si hay camino entre n y destino
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdyEtiq ady = n.getPrimerAdy();
            while (!exito && ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    exito = existeCaminoAux(ady.getVertice(), dest, vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
        return exito;
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un vértice cargado en el grafo y verdadero en caso contrario.
        return this.inicio == null;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        /*Dados dos elementos de TipoVertice (origen y destino), devuelve un camino (lista de vértices)
        que indique el camino que pasa por menos vértices que permite llegar del vértice origen al vértice
        destino. Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de ellos. Si
        alguno de los vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.*/
        Lista salida = new Lista();
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoVertEtiq auxO = null;
        NodoVertEtiq auxD = null;
        NodoVertEtiq aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca el camino mas corto entre ambos
            caminoMasCortoAux(auxO, destino, salida);
        }
        return salida;
    }

    private void caminoMasCortoAux(NodoVertEtiq n, Object dest, Lista salida) {
        //Busca el camino mas corto en la lista de adyacentes del nodo n hacia el vertice dest
        Lista visitados = new Lista();
        NodoAdyEtiq nAdyacente = n.getPrimerAdy();
        boolean exito = false;
        while (nAdyacente != null) {
            exito = existeCaminoAux(n, dest, visitados);
            if (exito) {
                if (salida.longitud() == 0) {
                    salida = visitados;
                } else {
                    if (visitados.longitud() < salida.longitud()) {
                        salida = visitados;
                    }
                }
            }
            nAdyacente = nAdyacente.getSigAdyacente();
        }
    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        /*Dados dos elementos de TipoVertice (origen y destino), devuelve un camino (lista de vértices)
        que indique el camino que pasa por más vértices (sin ciclos) que permite llegar del vértice origen
        al vértice destino. Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de
        ellos. Si alguno de los vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.*/
        Lista salida = new Lista();
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoVertEtiq auxO = null;
        NodoVertEtiq auxD = null;
        NodoVertEtiq aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca el camino mas corto entre ambos
            caminoMasLargoAux(auxO, destino, salida);
        }
        return salida;
    }

    private void caminoMasLargoAux(NodoVertEtiq n, Object dest, Lista salida) {
        //Busca el camino mas largo en la lista de adyacentes del nodo n hacia el vertice dest
        Lista visitados = new Lista();
        NodoAdyEtiq nAdyacente = n.getPrimerAdy();
        boolean exito = false;
        while (nAdyacente != null) {
            exito = existeCaminoAux(n, dest, visitados);
            if (exito) {
                if (salida.longitud() < visitados.longitud()) {
                    salida = visitados;
                }
            }
            nAdyacente = nAdyacente.getSigAdyacente();
        }
    }

    public Lista listarEnAnchura() {
        /* Devuelve una lista con los vértices del grafo visitados según el recorrido en anchura explicado en
        la sección anterior.*/
        Lista visitados = new Lista();
        NodoVertEtiq u = this.inicio;
        while (u != null) {
            if (visitados.localizar(u.getElem()) < 0) {
                AnchuraDesde(u, visitados);
            }
            u = u.getSigVertice();
        }
        return visitados;
    }

    private void AnchuraDesde(NodoVertEtiq v, Lista visitados) {
        Cola q = new Cola();
        visitados.insertar(v, visitados.longitud() + 1);
        q.poner(v);
        while (!q.esVacia()) {
            NodoVertEtiq u = (NodoVertEtiq) q.obtenerFrente();
            q.sacar();
            NodoAdyEtiq vAdyacente = u.getPrimerAdy();
            while (vAdyacente != null) {
                v = vAdyacente.getVertice();
                if (visitados.localizar(v) < 0) {
                    visitados.insertar(v, visitados.longitud() + 1);
                    q.poner(v);
                }
                vAdyacente = vAdyacente.getSigAdyacente();
            }
        }
    }

    public GrafoEtiq clone() {
        // Genera y devuelve un grafo que es equivalente (igual estructura y contenido de los nodos) al original.
        GrafoEtiq clon = new GrafoEtiq();
        clon.inicio = cloneAux(this.inicio);
        cloneAdy(clon.inicio, this.inicio, clon.inicio);
        return clon;
    }

    private NodoVertEtiq cloneAux(NodoVertEtiq n) {
        //Clona la lista de vertices del grafo original
        NodoVertEtiq nuevo = null;
        if (n != null) {
            nuevo = new NodoVertEtiq(n.getElem(), cloneAux(n.getSigVertice()));
        }
        return nuevo;
    }

    private void cloneAdy(NodoVertEtiq nClon, NodoVertEtiq n, NodoVertEtiq clonInicio) {
        //Clona la lista de adyacentes de cada nodo del grafo clon
        if (nClon != null) {
            nClon.setPrimerAdy(cloneAdyAux(nClon, n.getPrimerAdy(), clonInicio));
            cloneAdy(nClon.getSigVertice(), n.getSigVertice(), clonInicio);
        }
    }

    private NodoAdyEtiq cloneAdyAux(NodoVertEtiq nOrigen, NodoAdyEtiq nAdy, NodoVertEtiq clonInicio) {
        //Clona cada adyacente del grafo original en el grafo clon
        NodoAdyEtiq nuevo = null;
        if (nOrigen != null) {
            NodoVertEtiq aux = clonInicio;
            Object etiq = null;
            while (!nAdy.getVertice().getElem().equals(aux.getElem()) && aux != null) {
                aux = aux.getSigVertice();
            }
            if (aux != null) {
                nuevo = new NodoAdyEtiq(aux, cloneAdyAux(nOrigen, nAdy.getSigAdyacente(), clonInicio), nAdy.getEtiqueta());
            }
        }
        return nuevo;
    }

    public String toString() {
        // Con fines de debugging, este método genera y devuelve una cadena String que muestra los
        //vértices almacenados en el grafo y qué adyacentes tiene cada uno de ellos.
        return toStringAux(this.inicio);
    }

    private String toStringAux(NodoVertEtiq n) {
        //Modulo recursivo: crea una cadena y registra el nodo con sus nodos adyacentes
        String cad = "";
        if (n != null) {
            NodoAdyEtiq nAdy = n.getPrimerAdy();
            String cadAdyacentes = "";
            if (nAdy == null) {
                cadAdyacentes = "-";
            } else {
                while (nAdy != null) {
                    cadAdyacentes = cadAdyacentes + nAdy.getVertice().getElem().toString() + ", etiqueta: " + nAdy.getEtiqueta().toString();
                    nAdy = nAdy.getSigAdyacente();
                    if (nAdy != null) {
                        cadAdyacentes = cadAdyacentes + ";";
                    }
                }
            }
            cad = "Nodo: " + n.getElem() + ", nodos adyacentes: " + cadAdyacentes + "\n";
            cad = toStringAux(n.getSigVertice()) + cad;
        }
        return cad;
    }
}
