package EstructurasDelSistema.SistemaRutas;

import Estructuras.conjuntistas.NodoAdyEtiq;
import Estructuras.conjuntistas.NodoVertEtiq;
import Estructuras.lineales.dinamicas.Cola;
import Estructuras.lineales.dinamicas.Lista;

public class Rutas {
    private NodoCiudad inicio;

    public Rutas(){
        this.inicio = null;
    }

    private NodoCiudad ubicarVertice(Object buscado) {
        //Busca hasta encontrar el vertice buscado en la lista de vertice
        NodoCiudad aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigCiudad();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        /* Dado un elemento de TipoVertice se lo agrega a la estructura controlando que no se inserten
        vértices repetidos. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.*/
        boolean exito = false;
        NodoCiudad aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoCiudad(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object vertice) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        NodoCiudad aux = this.inicio;
        NodoCiudad auxAnterior = null;
        while (!exito && aux != null) {
            if (aux.getElem().equals(vertice)) {
                eliminarArcos(aux);
                if (auxAnterior == null) {
                    this.inicio = aux.getSigCiudad();
                } else {
                    auxAnterior.setSigCiudad(aux.getSigCiudad());
                }
                exito = true;
            }
            auxAnterior = aux;
            aux = aux.getSigCiudad();
        }
        /*Se puede usar tambien el modulo recursivo eliminarVerticeAux y comentar todo desde NodoVert aux = this.inicio*/
        //exito = eliminarVerticeAux(this.inicio, null, vertice);
        return exito;
    }

    private boolean eliminarVerticeAux(NodoCiudad nVertice, NodoCiudad nVerticeAnterior, Object verticeBuscado) {
        //Metodo recursivo para moverme en la lista de vertice hasta encontrar el verticeBuscado para eliminar
        boolean exito = false;
        if (nVertice != null) {
            if (nVertice.getElem().equals(verticeBuscado)) {
                eliminarArcos(nVertice);
                if (nVerticeAnterior == null) {
                    this.inicio = nVertice.getSigCiudad();
                } else {
                    nVerticeAnterior.setSigCiudad(nVertice.getSigCiudad());
                }
                exito = true;
            } else {
                exito = eliminarVerticeAux(nVertice.getSigCiudad(), nVertice, verticeBuscado);
            }
        }
        return exito;
    }

    private void eliminarArcos(NodoCiudad n) {
        //Modulo para eliminar los arcos del nodo "n"
        NodoRuta nAdyacente = n.getPrimerRuta();
        while (nAdyacente != null) {
            eliminarUnArco(nAdyacente.getVertice(), n.getElem());
            n.setPrimerRuta(nAdyacente.getSigRuta());
            nAdyacente = nAdyacente.getSigRuta();
        }
    }

    private boolean eliminarUnArco(NodoCiudad n, Object buscado) {
        //Confirma la eliminacion del nodo adyacente "buscado" de la lista de adyacentes del nodo "n"
        boolean exito = false;
        if (n != null) {
            if (n.getPrimerRuta() != null) {
                if (n.getPrimerRuta().getVertice().getElem().equals(buscado)) {
                    n.setPrimerRuta(n.getPrimerRuta().getSigRuta());
                    exito = true;
                } else {
                    exito = eliminarUnArcoAux(n.getPrimerRuta().getSigRuta(), n.getPrimerRuta(), buscado);
                }
            }
        }
        return exito;
    }

    private boolean eliminarUnArcoAux(NodoRuta n, NodoRuta nAnterior, Object buscado) {
        //Modulo recursivo para moverse en la lista de adyacentes del nodo "n" hasta encontrar
        // y confirmar la eliminacion el nodo "buscado"
        boolean exito = false;
        if (n != null) {
            if (n.getVertice().getElem().equals(buscado)) {
                nAnterior.setSigRuta(n.getSigRuta());
                exito = true;
            } else {
                exito = eliminarUnArcoAux(n.getSigRuta(), n, buscado);
            }
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, double etiqueta) {
        /* Dados dos elementos de TipoVertice (origen y destino) agrega el arco en la estructura, sólo si
        ambos vértices ya existen en el grafo. Si puede realizar la inserción devuelve verdadero, en caso
        contrario devuelve falso.*/
        //Forma mas eficiente
        boolean exito = false;
        NodoCiudad aux = this.inicio;
        NodoCiudad nOrigen = null;
        NodoCiudad nDestino = null;
        while (((nOrigen == null) || (nDestino == null)) && (aux != null)) {
            if (aux.getElem().equals(origen)) {
                nOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nDestino = aux;
            }
            aux = aux.getSigCiudad();
        }
        if (nOrigen != null && nDestino != null) {
            insertarAdyacente(nOrigen, nDestino, etiqueta);
            insertarAdyacente(nDestino, nOrigen, etiqueta);
            exito = true;
        }
        //exito = insertarArcoAux(this.inicio, origen, destino, etiqueta)
        return exito;
    }

    private boolean insertarArcoAux(NodoCiudad n, Object origen, Object destino, double etiqueta) {
        //Modulo recursivo que busca hasta encontrar el nodo vertice origen en lista de vertices del grafo
        //No es la mas eficiente porque busca de forma recursiva dos veces para buscar origen y destino
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(origen)) {
                NodoCiudad nDestino = ubicarVertice(destino);
                if (nDestino != null) {
                    //si no es encontrado el nodo vertice destino, termina y retorna false
                    insertarAdyacente(n, nDestino, etiqueta);
                    insertarAdyacente(nDestino, n, etiqueta);
                    exito = true;
                }
            } else {
                exito = insertarArcoAux(n.getSigCiudad(), origen, destino, etiqueta);
            }
        }
        return exito;
    }

    private void insertarAdyacente(NodoCiudad n, NodoCiudad nEnlace, double etiq) {
        //Inserta el nodo nVertice en la lista de adyacentes del nodo n
        if (n != null) {
            if (n.getPrimerRuta() == null) {
                n.setPrimerRuta(new NodoRuta(nEnlace, null, etiq));
            } else {
                insertarAdyacenteAux(n.getPrimerRuta(), nEnlace, etiq);
            }
        }
    }

    private void insertarAdyacenteAux(NodoRuta nAdyacente, NodoCiudad nEnlace, double etiq) {
        //Modulo recursivo para insertar el nodo nVertice en la lista de adyacentes del nodo n
        if (nAdyacente != null) {
            if (nAdyacente.getSigRuta() == null) {
                nAdyacente.setSigRuta(new NodoRuta(nEnlace, null, etiq));
            } else {
                insertarAdyacenteAux(nAdyacente.getSigRuta(), nEnlace, etiq);
            }
        }
    }

    public boolean eliminarArco(Object origen, Object destino) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        NodoCiudad aux = this.inicio;
        NodoCiudad nOrigen = null;
        NodoCiudad nDestino = null;
        while (((nOrigen == null) || (nDestino == null)) && (aux != null)) {
            if (aux.getElem().equals(origen)) {
                nOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nDestino = aux;
            }
            aux = aux.getSigCiudad();
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

    private NodoRuta ubicarVerticeAdyacente(NodoCiudad n, Object buscado) {
        //Busca y retorna el nodo vertice buscado en la lista de adyacentes de n
        NodoRuta aux = null;
        if (n != null) {
            aux = n.getPrimerRuta();
            while (aux != null && !aux.getVertice().getElem().equals(buscado)) {
                aux = aux.getSigRuta();
            }
        }
        return aux;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        NodoCiudad aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                //si el vertice no fue visitados aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigCiudad();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoCiudad n, Lista vis) {
        if (n != null) {
            //marca al vertice n como visitados
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoRuta ady = n.getPrimerRuta();
            while (ady != null) {
                // visita en profundidad los adyacentes de n aun no visitados
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigRuta();
            }
        }
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoCiudad auxO = null;
        NodoCiudad auxD = null;
        NodoCiudad aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigCiudad();
        }
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoCiudad n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            exito = true;
        } else {
            //si no es el destino verifica si hay camino entre n y destino
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoRuta ady = n.getPrimerRuta();
            while (!exito && ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    exito = existeCaminoAux(ady.getVertice(), dest, vis);
                }
                ady = ady.getSigRuta();
            }
        }
        return exito;
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un vértice cargado en el grafo y verdadero en caso contrario.
        return this.inicio != null;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista salida = new Lista();
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoCiudad auxO = null;
        NodoCiudad auxD = null;
        NodoCiudad aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigCiudad();
        }
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca el camino mas corto entre ambos
            caminoMasCortoAux(auxO, destino, salida);
        }
        return salida;
    }

    private void caminoMasCortoAux(NodoCiudad n, Object dest, Lista salida) {
        //Busca el camino mas corto en la lista de adyacentes del nodo n hacia el vertice dest
        Lista visitados = new Lista();
        NodoRuta nAdyacente = n.getPrimerRuta();
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
            nAdyacente = nAdyacente.getSigRuta();
        }
    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        Lista salida = new Lista();
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoCiudad auxO = null;
        NodoCiudad auxD = null;
        NodoCiudad aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigCiudad();
        }
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca el camino mas corto entre ambos
            caminoMasLargoAux(auxO, destino, salida);
        }
        return salida;
    }

    private void caminoMasLargoAux(NodoCiudad n, Object dest, Lista salida) {
        //Busca el camino mas largo en la lista de adyacentes del nodo n hacia el vertice dest
        Lista visitados = new Lista();
        NodoRuta nAdyacente = n.getPrimerRuta();
        boolean exito = false;
        while (nAdyacente != null) {
            exito = existeCaminoAux(n, dest, visitados);
            if (exito) {
                if (salida.longitud() < visitados.longitud()) {
                    salida = visitados;
                }
            }
            nAdyacente = nAdyacente.getSigRuta();
        }
    }

    public Lista listarEnAnchura() {
        /* Devuelve una lista con los vértices del grafo visitados según el recorrido en anchura explicado en
        la sección anterior.*/
        Lista visitados = new Lista();
        NodoCiudad u = this.inicio;
        while (u != null) {
            if (visitados.localizar(u.getElem()) < 0) {
                AnchuraDesde(u, visitados);
            }
            u = u.getSigCiudad();
        }
        return visitados;
    }

    private void AnchuraDesde(NodoCiudad v, Lista visitados) {
        Cola q = new Cola();
        visitados.insertar(v, visitados.longitud() + 1);
        q.poner(v);
        while (!q.esVacia()) {
            NodoCiudad u = (NodoCiudad) q.obtenerFrente();
            q.sacar();
            NodoRuta vAdyacente = u.getPrimerRuta();
            while (vAdyacente != null) {
                v = vAdyacente.getVertice();
                if (visitados.localizar(v) < 0) {
                    visitados.insertar(v, visitados.longitud() + 1);
                    q.poner(v);
                }
                vAdyacente = vAdyacente.getSigRuta();
            }
        }
    }

    public Rutas clone() {
        // Genera y devuelve un grafo que es equivalente (igual estructura y contenido de los nodos) al original.
        Rutas clon = new Rutas();
        clon.inicio = cloneAux(this.inicio);
        cloneAdy(clon.inicio, this.inicio, clon.inicio);
        return clon;
    }

    private NodoCiudad cloneAux(NodoCiudad n) {
        //Clona la lista de vertices del grafo original
        NodoCiudad nuevo = null;
        if (n != null) {
            nuevo = new NodoCiudad(n.getElem(), cloneAux(n.getSigCiudad()));
        }
        return nuevo;
    }

    private void cloneAdy(NodoCiudad nClon, NodoCiudad n, NodoCiudad clonInicio) {
        //Clona la lista de adyacentes de cada nodo del grafo clon
        if (nClon != null) {
            nClon.setPrimerRuta(cloneAdyAux(nClon, n.getPrimerRuta(), clonInicio));
            cloneAdy(nClon.getSigCiudad(), n.getSigCiudad(), clonInicio);
        }
    }

    private NodoRuta cloneAdyAux(NodoCiudad nOrigen, NodoRuta nAdy, NodoCiudad clonInicio) {
        //Clona cada adyacente del grafo original en el grafo clon
        NodoRuta nuevo = null;
        if (nOrigen != null) {
            NodoCiudad aux = clonInicio;
            Object etiq = null;
            while (!nAdy.getVertice().getElem().equals(aux.getElem()) && aux != null) {
                aux = aux.getSigCiudad();
            }
            if (aux != null) {
                nuevo = new NodoRuta(aux, cloneAdyAux(nOrigen, nAdy.getSigRuta(), clonInicio), nAdy.getEtiqueta());
            }
        }
        return nuevo;
    }

    public String toString() {
        // Con fines de debugging, este método genera y devuelve una cadena String que muestra los
        //vértices almacenados en el grafo y qué adyacentes tiene cada uno de ellos.
        return toStringAux(this.inicio);
    }

    private String toStringAux(NodoCiudad n) {
        //Modulo recursivo: crea una cadena y registra el nodo con sus nodos adyacentes
        String cad = "";
        if (n != null) {
            NodoRuta nAdy = n.getPrimerRuta();
            String cadAdyacentes = "";
            if (nAdy == null) {
                cadAdyacentes = "-";
            } else {
                while (nAdy != null) {
                    cadAdyacentes = cadAdyacentes + nAdy.getVertice().getElem().toString()+", etiqueta: "+ nAdy.getEtiqueta();
                    nAdy = nAdy.getSigRuta();
                    if (nAdy != null) {
                        cadAdyacentes = cadAdyacentes + ";";
                    }
                }
            }
            cad = "Nodo: " + n.getElem() + ", nodos adyacentes: " + cadAdyacentes + "\n";
            cad = cad + toStringAux(n.getSigCiudad());
        }
        return cad;
    }
}
