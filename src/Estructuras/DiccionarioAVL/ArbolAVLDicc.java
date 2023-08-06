package Estructuras.DiccionarioAVL;

import Estructuras.lineales.dinamicas.Lista;

public class ArbolAVLDicc {
    private NodoAVLDicc raiz;

    public ArbolAVLDicc() {
        this.raiz = null;
    }

    public boolean existeClave(Comparable clave) {
        // Devuelve verdadero si el elemento recibido por parámetro está en el árbol y falso en caso contrario.
        boolean exito = false;
        if (this.raiz != null) {
            exito = existeClaveAux(this.raiz, clave);
        }
        return exito;
    }

    private boolean existeClaveAux(NodoAVLDicc n, Comparable clave) {
        boolean exito = false;
        if (clave.compareTo(n.getClave()) == 0) {
            exito = true;
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = existeClaveAux(n.getIzquierdo(), clave);
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = existeClaveAux(n.getDerecho(), clave);
                }
            }
        }
        return exito;
    }

    private NodoAVLDicc rotarIzquierda(NodoAVLDicc r) {
        NodoAVLDicc h = r.getDerecho();
        NodoAVLDicc temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        return h;
    }

    private NodoAVLDicc rotarDerecha(NodoAVLDicc r) {
        NodoAVLDicc h = r.getIzquierdo();
        NodoAVLDicc temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        return h;
    }

    public boolean insertar(Comparable clave, Object elemento) {
        /*Recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento ya se encuentra
        en el árbol no se realiza la inserción. Devuelve verdadero si el elemento se agrega a la estructura y
        falso en caso contrario.*/
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(clave, elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, null, clave, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVLDicc n, NodoAVLDicc padre, Comparable clave, Object elemento) {
        boolean exito = true;
        if (clave.compareTo(n.getClave()) == 0) {
            exito = false;
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = insertarAux(n.getIzquierdo(), n, clave, elemento);
                } else {
                    n.setIzquierdo(new NodoAVLDicc(clave, elemento, null, null));
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = insertarAux(n.getDerecho(), n, clave, elemento);
                } else {
                    n.setDerecho(new NodoAVLDicc(clave, elemento, null, null));
                }
            }
            n.recalcularAltura();
        }
        autobalance(n, padre);
        return exito;
    }

    private void autobalance(NodoAVLDicc n, NodoAVLDicc padre) {
        int balance, balanceHI, balanceHD;
        balance = calcularBalance(n);
        balanceHI = calcularBalance(n.getIzquierdo());
        balanceHD = calcularBalance(n.getDerecho());
        if (balance == 2 || balance == -2) {
            if (balance == 2) {
                if (balanceHI > -1) {
                    //rotar a la derecha
                    n = rotarDerecha(n);
                    n.getDerecho().recalcularAltura();
                } else {
                    //doble izquierda-derecha
                    n.setIzquierdo(rotarIzquierda(n.getIzquierdo()));
                    n.getIzquierdo().getIzquierdo().recalcularAltura();
                    n = rotarDerecha(n);
                    n.getDerecho().recalcularAltura();
                    n.recalcularAltura();
                }
            } else {
                if (balance == -2) {
                    if (balanceHD < 1) {
                        //rotar a la izquierda
                        n = rotarIzquierda(n);
                        n.getIzquierdo().recalcularAltura();
                    } else {
                        //doble derecha-izquierda
                        n.setDerecho(rotarDerecha(n.getDerecho()));
                        n.getDerecho().getDerecho().recalcularAltura();
                        n = rotarIzquierda(n);
                        n.getIzquierdo().recalcularAltura();
                        n.recalcularAltura();
                    }
                }
            }
            if (padre == null) {
                this.raiz = n;
            } else {
                Comparable claveDeN = (Comparable) n.getClave();
                if (claveDeN.compareTo(padre.getClave()) < 0) {
                    //podria hacer (padre.getClave().comparaTo(n.getClave()) < 0), asi me ahorro hacer pasar por parametro
                    //podria pasar por parametro claveComparada y asignarle en los metodos resursivos claveComparada = elem.compareTo(n.getClave())
                    padre.setIzquierdo(n);
                } else {
                    padre.setDerecho(n);
                }
            }
        }
    }

    private int calcularBalance(NodoAVLDicc n) {
        int numBalance = -1;
        if (n != null) {
            int altIzq, altDer;
            NodoAVLDicc hijoIzq = n.getIzquierdo();
            NodoAVLDicc hijoDer = n.getDerecho();
            if (hijoIzq == null) {
                altIzq = -1;
            } else {
                altIzq = hijoIzq.getAltura();
            }

            if (hijoDer == null) {
                altDer = -1;
            } else {
                altDer = hijoDer.getAltura();
            }
            numBalance = altIzq - altDer;
        }

        return numBalance;
    }

    public boolean eliminar(Comparable clave) {
        /*Recibe el elemento que se desea eliminar y se procede a removerlo del árbol. Si no se encuentra
        el elemento no se puede realizar la eliminación. Devuelve verdadero si el elemento se elimina de la
        estructura y falso en caso contrario.*/
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, null, clave);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVLDicc n, NodoAVLDicc padre, Comparable clave) {
        boolean exito = false;
        if (clave.compareTo(n.getClave()) == 0) {
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                caso1(clave, padre);
            } else {
                if (n.getIzquierdo() == null || n.getDerecho() == null) {
                    caso2(clave, n, padre);
                } else {
                    caso3(clave, n, padre);
                }
            }
            //autobalance(n, padre);
            exito = true;
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = eliminarAux(n.getIzquierdo(), n, clave);
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = eliminarAux(n.getDerecho(), n, clave);
                }
            }
            autobalance(n, padre);
        }
        return exito;
    }

    //Caso 1 de eliminar.
    private void caso1(Comparable clave, NodoAVLDicc padre) {
        if (padre == null) {
            //Caso especial al intentar eliminar la raiz
            this.raiz = null;
        } else {
            int temp = clave.compareTo(padre.getClave());
            if (temp < 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    //Caso 2 de eliminar.
    private void caso2(Comparable clave, NodoAVLDicc hijo, NodoAVLDicc padre) {
        //Busco al candidato para reemplazar al nodo
        //almenos 1 sera null.
        NodoAVLDicc der = hijo.getDerecho();
        NodoAVLDicc izq = hijo.getIzquierdo();
        if (padre == null) {
            //Caso especial.
            if (der == null) {
                this.raiz = izq;
            } else {
                this.raiz = der;
            }
        } else {
            //Verifico la rama derecha o izquierda.
            int temp = clave.compareTo(padre.getClave());
            if (temp < 0) {
                if (izq == null) {
                    padre.setIzquierdo(der);
                } else {
                    padre.setIzquierdo(izq);
                }
            } else {
                if (izq == null) {
                    padre.setDerecho(der);
                } else {
                    padre.setDerecho(izq);
                }
            }
        }
    }

    /**
     * Usar el candidato A (El mayor del subarbol izquierdo de N, siendo N el
     * nodo a eliminar).
     *
     * @param actual envia el nodo a eliminar.
     */
    private void caso3(Comparable clave, NodoAVLDicc actual, NodoAVLDicc padre) {
        NodoAVLDicc nodoA = actual.getIzquierdo(), nodoPadreA = actual;
        while (nodoA.getDerecho() != null) {
            nodoPadreA = nodoA;
            nodoA = nodoA.getDerecho();
        }
        actual = new NodoAVLDicc(nodoA.getClave(),nodoA.getDato(),actual.getIzquierdo(),actual.getDerecho());
        NodoAVLDicc hijoDer = nodoA.getDerecho();
        if (actual.getIzquierdo().getDato().equals(nodoA.getDato())) {
            actual.setIzquierdo(hijoDer);
        } else {
            nodoPadreA.setDerecho(hijoDer);
        }
        if (padre == null){
            this.raiz = actual;
        } else {
            int temp = clave.compareTo(padre.getClave());
            if (temp < 0){
                padre.setIzquierdo(actual);
            } else {
                padre.setDerecho(actual);
            }
        }
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un elemento en el árbol y verdadero en caso contrario.
        return this.raiz == null;
    }

    public Object obtenerInformacion(Comparable clave) {
        Object dato = null;
        if (raiz != null) {
            dato = obtenerInformacionAux(this.raiz, clave);
        }
        return dato;
    }

    private Object obtenerInformacionAux(NodoAVLDicc n, Comparable clave) {
        Object dato = false;
        if (clave.compareTo(n.getClave()) == 0) {
            dato = n.getDato();
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                if (n.getIzquierdo() != null) {
                    dato = obtenerInformacionAux(n.getIzquierdo(), clave);
                }
            } else {
                if (n.getDerecho() != null) {
                    dato = obtenerInformacionAux(n.getDerecho(), clave);
                }
            }
        }
        return dato;
    }

    public Lista listarClaves() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarClavesAux(this.raiz, lis);
        }
        return lis;
    }

    private void listarClavesAux(NodoAVLDicc n, Lista lis) {
        if (n.getIzquierdo() != null) {
            listarClavesAux(n.getIzquierdo(), lis);
        }

        lis.insertar(n.getClave(), lis.longitud() + 1);

        if (n.getDerecho() != null) {
            listarClavesAux(n.getDerecho(), lis);
        }
    }

    public Lista listarDatos() {
        /*recorre el árbol completo y devuelve una lista ordenada con los datos que se encuentran
        almacenados en él.*/
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarDatosAux(this.raiz, lis);
        }
        return lis;
    }

    private void listarDatosAux(NodoAVLDicc n, Lista lis) {
        if (n.getIzquierdo() != null) {
            listarDatosAux(n.getIzquierdo(), lis);
        }

        lis.insertar(n.getDato(), lis.longitud() + 1);

        if (n.getDerecho() != null) {
            listarDatosAux(n.getDerecho(), lis);
        }
    }

    //metodo que se usara en el inciso 7 del tpFinal, inciso 2
    public Lista listarRango(Comparable claveMin, Comparable claveMax) {
        /*Recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada con los elementos que
        se encuentran en el intervalo [elemMinimo, elemMaximo].*/
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarRangoAux(this.raiz, lis, claveMin, claveMax);
        }
        return lis;
    }

    private void listarRangoAux(NodoAVLDicc n, Lista lis, Comparable claveMin, Comparable claveMax) {
        if (n.getIzquierdo() != null && (claveMin.compareTo(n.getClave()) < 0)) {
            listarRangoAux(n.getIzquierdo(), lis, claveMin, claveMax);
        }
        if ((claveMin.compareTo(n.getClave()) <= 0) && (claveMax.compareTo(n.getClave()) >= 0)) {
            lis.insertar(n.getDato(), lis.longitud() + 1);
        }

        if (n.getDerecho() != null && (claveMax.compareTo(n.getClave()) > 0)) {
            listarRangoAux(n.getDerecho(), lis, claveMin, claveMax);
        }
    }

    public Object minimoElem() {
        // recorre la rama correspondiente y devuelve el elemento más pequeño almacenado en el árbol.
        Object elem = null;
        if (this.raiz != null) {
            elem = minimoElemAux(this.raiz);
        }
        return elem;
    }

    private Object minimoElemAux(NodoAVLDicc n) {
        Object elem;
        if (n.getIzquierdo() != null) {
            elem = minimoElemAux(n.getIzquierdo());
        } else {
            elem = n.getDato();
        }
        return elem;
    }

    public Object maximoElem() {
        // recorre la rama correspondiente y devuelve el elemento más grande almacenado en el árbol.
        Object elem = null;
        if (this.raiz != null) {
            elem = maximoElemAux(this.raiz);
        }
        return elem;
    }

    private Object maximoElemAux(NodoAVLDicc n) {
        Object elem;
        if (n.getDerecho() != null) {
            elem = maximoElemAux(n.getDerecho());
        } else {
            elem = n.getDato();
        }
        return elem;
    }

    public ArbolAVLDicc clone() {
        ArbolAVLDicc clon = new ArbolAVLDicc();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoAVLDicc cloneAux(NodoAVLDicc n) {
        NodoAVLDicc nuevo = null;
        if (n != null) {
            nuevo = new NodoAVLDicc(n.getClave(), n.getDato(), cloneAux(n.getIzquierdo()), cloneAux(n.getDerecho()));
        }
        return nuevo;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        String cadena = "Arbol vacio";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz);
        }
        return cadena;
    }

    private String toStringAux(NodoAVLDicc nodo) {
        String cadenaNodo = "";
        if (nodo != null) {
            cadenaNodo = "Nodo: "+ nodo.getClave().toString()+", altura: "+nodo.getAltura();
            if (nodo.getIzquierdo() != null) {
                cadenaNodo = cadenaNodo + "; HI: " + nodo.getIzquierdo().getClave().toString();
            } else {
                cadenaNodo = cadenaNodo + "; HI:--";
            }
            if (nodo.getDerecho() != null) {
                cadenaNodo = cadenaNodo + ", HD: " + nodo.getDerecho().getClave().toString() + "\n";
            } else {
                cadenaNodo = cadenaNodo + ", HD:--\n";
            }
            cadenaNodo = cadenaNodo + toStringAux(nodo.getIzquierdo());
            cadenaNodo = cadenaNodo + toStringAux(nodo.getDerecho());
        }
        return cadenaNodo;
    }
}

