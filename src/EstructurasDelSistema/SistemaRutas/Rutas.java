package EstructurasDelSistema.SistemaRutas;

import Estructuras.conjuntistas.NodoAdyEtiq;
import Estructuras.conjuntistas.NodoVertEtiq;
import Estructuras.lineales.dinamicas.Cola;
import Estructuras.lineales.dinamicas.Lista;
import Estructuras.lineales.dinamicas.Pila;
import EstructurasDelSistema.Ciudades;
import clases.Ciudad;

public class Rutas {
    private NodoCiudad inicio;

    public Rutas() {
        this.inicio = null;
    }

    private NodoCiudad ubicarCiudad(Object buscado) {
        //Busca hasta encontrar el vertice buscado en la lista de vertice
        NodoCiudad aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigCiudad();
        }
        return aux;
    }

    public boolean insertarCiudad(Object nuevaCiudad) {
        /* Dado un elemento de TipoVertice se lo agrega a la estructura controlando que no se inserten
        vértices repetidos. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.*/
        boolean exito = false;
        NodoCiudad aux = this.ubicarCiudad(nuevaCiudad);
        if (aux == null) {
            this.inicio = new NodoCiudad(nuevaCiudad, this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarCiudad(Object ciudad) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        NodoCiudad aux = this.inicio;
        NodoCiudad auxAnterior = null;
        while (!exito && aux != null) {
            if (aux.getElem().equals(ciudad)) {
                eliminarRutas(aux);
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
        return exito;
    }

    private void eliminarRutas(NodoCiudad n) {
        //Modulo para eliminar los arcos del nodo "n"
        NodoRuta nRuta = n.getPrimerRuta();
        while (nRuta != null) {
            eliminarUnaRuta(nRuta.getVertice(), n.getElem());
            n.setPrimerRuta(nRuta.getSigRuta());
            nRuta = nRuta.getSigRuta();
        }
    }

    private boolean eliminarUnaRuta(NodoCiudad n, Object buscado) {
        //Confirma la eliminacion del nodo adyacente "buscado" de la lista de adyacentes del nodo "n"
        boolean exito = false;
        if (n != null) {
            if (n.getPrimerRuta() != null) {
                if (n.getPrimerRuta().getVertice().getElem().equals(buscado)) {
                    n.setPrimerRuta(n.getPrimerRuta().getSigRuta());
                    exito = true;
                } else {
                    exito = eliminarUnaRutaAux(n.getPrimerRuta().getSigRuta(), n.getPrimerRuta(), buscado);
                }
            }
        }
        return exito;
    }

    private boolean eliminarUnaRutaAux(NodoRuta n, NodoRuta nAnterior, Object buscado) {
        //Modulo recursivo para moverse en la lista de adyacentes del nodo "n" hasta encontrar
        // y confirmar la eliminacion el nodo "buscado"
        boolean exito = false;
        if (n != null) {
            if (n.getVertice().getElem().equals(buscado)) {
                nAnterior.setSigRuta(n.getSigRuta());
                exito = true;
            } else {
                exito = eliminarUnaRutaAux(n.getSigRuta(), n, buscado);
            }
        }
        return exito;
    }

    public boolean insertarRuta(Object nOrigen, Object nDestino, double etiqueta) {
        /* Dados dos elementos de TipoVertice (origen y destino) agrega el arco en la estructura, sólo si
        ambos vértices ya existen en el grafo. Si puede realizar la inserción devuelve verdadero, en caso
        contrario devuelve falso.*/
        //Forma mas eficiente
        boolean exito = false;
        if (this.inicio != null) {
            if (nOrigen != null && nDestino != null) {
                NodoCiudad nodoOrigen = ubicarCiudad(nOrigen);
                NodoCiudad nodoDestino = ubicarCiudad(nDestino);
                insertarRuta(nodoOrigen, nodoDestino, etiqueta);
                insertarRuta(nodoDestino, nodoDestino, etiqueta);
                exito = true;
            }
        }
        return exito;
    }

    private void insertarRuta(NodoCiudad n, NodoCiudad nEnlace, double etiq) {
        //Inserta el nodo nVertice en la lista de adyacentes del nodo n
        if (n != null) {
            if (n.getPrimerRuta() == null) {
                n.setPrimerRuta(new NodoRuta(nEnlace, null, etiq));
            } else {
                insertarRutaAux(n.getPrimerRuta(), nEnlace, etiq);
            }
        }
    }

    private void insertarRutaAux(NodoRuta nRuta, NodoCiudad nEnlace, double etiq) {
        //Modulo recursivo para insertar el nodo nVertice en la lista de adyacentes del nodo n
        if (nRuta != null) {
            if (nRuta.getSigRuta() == null) {
                nRuta.setSigRuta(new NodoRuta(nEnlace, null, etiq));
            } else {
                insertarRutaAux(nRuta.getSigRuta(), nEnlace, etiq);
            }
        }
    }

    public boolean eliminarRuta(Object origen, Object destino) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        if (this.inicio != null) {
            if (origen != null && destino != null) {
                NodoCiudad nOrigen = ubicarCiudad(origen);
                NodoCiudad nDestino = ubicarCiudad(destino);
                exito = eliminarUnaRuta(nOrigen, destino);
                if (exito) {
                    exito = eliminarUnaRuta(nDestino, origen);
                }
            }
        }
        return exito;
    }


    public boolean existeCiudad(Object ciudad) {
        // Dado un elemento, devuelve verdadero si está en la estructura y falso en caso contrario.
        return ubicarCiudad(ciudad) != null;
    }

    public boolean existeRuta(Object origen, Object destino) {
        /* Dados dos elementos de TipoVertice (origen y destino), devuelve verdadero si existe un arco en
        la estructura que los une y falso en caso contrario.*/
        return ubicarCiudadRuta(ubicarCiudad(origen), destino) != null;
    }

    private NodoRuta ubicarCiudadRuta(NodoCiudad n, Object buscado) {
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
        /*Devuelve una lista con los vértices del grafo visitados según el recorrido en profundidad explicado
        en la sección anterior.*/
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        NodoCiudad aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 1) {
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
            NodoRuta ruta = n.getPrimerRuta();
            while (ruta != null) {
                // visita en profundidad los adyacentes de n aun no visitados
                if (vis.localizar(ruta.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ruta.getVertice(), vis);
                }
                ruta = ruta.getSigRuta();
            }
        }
    }

    public boolean existeCamino(Object origen, Object destino) {
        /*Dados dos elementos de TipoVertice (origen y destino), devuelve verdadero si existe al menos
        un camino que permite llegar del vértice origen al vértice destino y falso en caso contrario.*/
        boolean exito = false;
        if (this.inicio != null) {
            //verifica si ambos vertices existen
            if (origen != null && destino != null) {
                //si ambos vertices existen busca si existe camino entre ambos
                NodoCiudad auxO = ubicarCiudad(origen);
                Lista visitados = new Lista();
                exito = existeCaminoAux(auxO, destino, visitados);
            }
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoCiudad n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                //si no es el destino verifica si hay camino entre n y destino
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoRuta ruta = n.getPrimerRuta();
                while (!exito && ruta != null) {
                    if (vis.localizar(ruta.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ruta.getVertice(), dest, vis);
                    }
                    ruta = ruta.getSigRuta();
                }
            }
        }
        return exito;
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un vértice cargado en el grafo y verdadero en caso contrario.
        return this.inicio == null;
    }

    public Lista caminoCortoCiudades(Object origen, Object destino) {
        //Dados dos elementos de TipoVertice (origen y destino), devuelve un camino (lista de vértices)
        //que indique el camino que pasa por menos vértices (sin ciclos) que permite llegar del vértice origen
        //al vértice destino. Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de
        //ellos. Si alguno de los vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
        Lista salida = new Lista();
        boolean exito = false;
        if (this.inicio != null) {
            if (origen != null && destino != null) {
                //si ambos vertices existen busca el camino mas corto entre ambos
                NodoCiudad auxO = ubicarCiudad(origen);
                NodoCiudad auxD = ubicarCiudad(destino);
                caminoCortoCiudadesAux(auxO, auxD, salida);
            }
        }
        return salida;
    }

    private void caminoCortoCiudadesAux(NodoCiudad n, Object dest, Lista salida) {
        //Busca el camino mas corto en la lista de adyacentes del nodo n hacia el vertice dest
        Lista visitados = new Lista();
        NodoRuta nAdyacente = n.getPrimerRuta();
        visitados.insertar(n.getElem(), visitados.longitud() + 1);
        while (nAdyacente != null) {
            caminoCortoCiudadesAdy(nAdyacente.getVertice(), dest, visitados, salida);
            nAdyacente = nAdyacente.getSigRuta();
        }
    }

    private void caminoCortoCiudadesAdy(NodoCiudad n, Object dest, Lista vis, Lista salida) {
        //Busca el camino mas largo en la lista de adyacentes del nodo n hacia el vertice dest
        NodoRuta nAdyacente = n.getPrimerRuta();
        if (n.getElem().equals(dest)) {
            if (salida.esVacia() || (vis.longitud() < salida.longitud())) {
                salida.duplicar(vis);
            }
        } else {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            while (nAdyacente != null) {
                if (vis.localizar(nAdyacente.getVertice().getElem()) < 1) {
                    caminoCortoCiudadesAdy(nAdyacente.getVertice(), dest, vis, salida);
                }
                nAdyacente = nAdyacente.getSigRuta();
                if (nAdyacente == null) {
                    vis.eliminar(vis.longitud());
                }
            }
        }
    }

    public Lista caminoCortoDistancias(Object origen, Object destino) {
        Lista salida = new Lista();
        boolean exito = false;
        if (this.inicio != null) {
            //verifica si ambos vertices existen
            if (origen != null && destino != null) {
                //si ambos vertices existen busca el camino mas corto entre ambos
                NodoCiudad auxO = ubicarCiudad(origen);
                caminoCortoDistanciasAux(auxO, destino, salida);
            }
        }
        return salida;
    }

    private void caminoCortoDistanciasAux(NodoCiudad n, Object dest, Lista salida) {
        //Busca el camino mas corto en la lista de adyacentes del nodo n hacia el vertice dest
        Lista visitados = new Lista();
        NodoRuta nAdyacente = n.getPrimerRuta();
        double distanciaTotal, distanciaMenor, distSalida = 0;
        visitados.insertar(n.getElem(), visitados.longitud() + 1);
        while (nAdyacente != null) {
            distanciaTotal = nAdyacente.getEtiqueta();
            distanciaMenor = nAdyacente.getEtiqueta();
            distSalida = caminoCortoDistanciasAdy(nAdyacente.getVertice(), dest, visitados, salida, distanciaTotal, distanciaMenor, distSalida);
            nAdyacente = nAdyacente.getSigRuta();
        }
    }

    private double caminoCortoDistanciasAdy(NodoCiudad n, Object dest, Lista vis, Lista salida, double distTotal, double distParcial, double distSalida) {
        //Busca el camino mas largo en la lista de adyacentes del nodo n hacia el vertice dest y retorna el double distSalida
        NodoRuta nAdyacente = n.getPrimerRuta();
        if (n.getElem().equals(dest)) {
            if ((distSalida == 0) || (distTotal < distSalida)) {
                salida.duplicar(vis);
                distSalida = distTotal;
            }
        } else {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            while (nAdyacente != null) {
                if (vis.localizar(nAdyacente.getVertice().getElem()) < 1) {
                    if ((distSalida == 0) || (nAdyacente.getEtiqueta() + distTotal < distSalida)) {
                        distTotal = distTotal + nAdyacente.getEtiqueta();
                        distSalida = caminoCortoDistanciasAdy(nAdyacente.getVertice(), dest, vis, salida, distTotal, nAdyacente.getEtiqueta(), distSalida);
                        distTotal = distTotal - nAdyacente.getEtiqueta();
                    }
                }
                nAdyacente = nAdyacente.getSigRuta();
                if (nAdyacente == null) {
                    vis.eliminar(vis.longitud());
                }
            }
        }
        return distSalida;
    }

    public Lista listarEnAnchura() {
        /* Devuelve una lista con los vértices del grafo visitados según el recorrido en anchura explicado en
        la sección anterior.*/
        Lista visitados = new Lista();
        NodoCiudad u = this.inicio;
        while (u != null) {
            if (visitados.localizar(u) < 1) {
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
            NodoRuta vRuta = u.getPrimerRuta();
            while (vRuta != null) {
                v = vRuta.getVertice();
                if (visitados.localizar(v) < 0) {
                    visitados.insertar(v, visitados.longitud() + 1);
                    q.poner(v);
                }
                vRuta = vRuta.getSigRuta();
            }
        }
    }

    public Rutas clone() {
        // Genera y devuelve un grafo que es equivalente (igual estructura y contenido de los nodos) al original.
        Rutas clon = new Rutas();
        clon.inicio = cloneAux(this.inicio);
        cloneRuta(clon.inicio, this.inicio, clon.inicio);
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

    private void cloneRuta(NodoCiudad nClon, NodoCiudad n, NodoCiudad clonInicio) {
        //Clona la lista de adyacentes de cada nodo del grafo clon
        if (nClon != null) {
            nClon.setPrimerRuta(cloneRutaAux(nClon, n.getPrimerRuta(), clonInicio));
            cloneRuta(nClon.getSigCiudad(), n.getSigCiudad(), clonInicio);
        }
    }

    private NodoRuta cloneRutaAux(NodoCiudad nOrigen, NodoRuta nAdy, NodoCiudad clonInicio) {
        //Clona cada adyacente del grafo original en el grafo clon
        NodoRuta nuevo = null;
        if (nAdy != null) {
            NodoCiudad aux = clonInicio;
            Object etiq = null;
            while (!(nAdy.getVertice().getElem().equals(aux.getElem())) && aux != null) {
                aux = aux.getSigCiudad();
            }
            if (aux != null) {
                nuevo = new NodoRuta(aux, cloneRutaAux(nOrigen, nAdy.getSigRuta(), clonInicio), nAdy.getEtiqueta());
            }
        }
        return nuevo;
    }

    public String toString() {
        // Con fines de debugging, este método genera y devuelve una cadena String que muestra los
        //vértices almacenados en el grafo y qué adyacentes tiene cada uno de ellos.
        String cad = "No hay ciudades agregados";
        if (this.inicio != null) {
            cad = toStringAux(this.inicio);
        }
        return cad;
    }

    private String toStringAux(NodoCiudad n) {
        //Modulo recursivo: crea una cadena y registra el nodo con sus nodos adyacentes
        String cad = "";
        if (n != null) {
            NodoRuta nRuta = n.getPrimerRuta();
            String cadAdyacentes = "";
            if (nRuta == null) {
                cadAdyacentes = "-";
            } else {
                while (nRuta != null) {
                    Ciudad c = (Ciudad) nRuta.getVertice().getElem();
                    cadAdyacentes = cadAdyacentes + c.getCodigoPostal() + ", etiqueta: " + nRuta.getEtiqueta();
                    nRuta = nRuta.getSigRuta();
                    if (nRuta != null) {
                        cadAdyacentes = cadAdyacentes + "; ";
                    }
                }
            }
            cad = "Nodo: " + n.getElem() + ", nodos adyacentes: " + cadAdyacentes + "\n";
            cad = toStringAux(n.getSigCiudad()) + cad;
        }
        return cad;
    }
}
