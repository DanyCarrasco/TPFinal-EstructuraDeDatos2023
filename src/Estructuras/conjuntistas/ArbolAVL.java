package Estructuras.conjuntistas;

import Estructuras.lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elemento) {
        // Devuelve verdadero si el elemento recibido por parámetro está en el árbol y falso en caso contrario.
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean perteneceAux(NodoAVL n, Comparable elem) {
        boolean exito = false;
        if (elem.compareTo(n.getElem()) == 0) {
            exito = true;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = perteneceAux(n.getIzquierdo(), elem);
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = perteneceAux(n.getDerecho(), elem);
                }
            }
        }
        return exito;
    }

    private NodoAVL rotarIzquierda(NodoAVL r) {
        NodoAVL h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        return h;
    }

    private NodoAVL rotarDerecha(NodoAVL r) {
        NodoAVL h = r.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        return h;
    }

    public boolean insertar(Comparable elemento) {
        /*Recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento ya se encuentra
        en el árbol no se realiza la inserción. Devuelve verdadero si el elemento se agrega a la estructura y
        falso en caso contrario.*/
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, null, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, NodoAVL padre, Comparable elemento) {
        boolean exito = true;
        if (elemento.compareTo(n.getElem()) == 0) {
            exito = false;
        } else {
            if (elemento.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = insertarAux(n.getIzquierdo(), n, elemento);
                } else {
                    n.setIzquierdo(new NodoAVL(elemento, null, null));
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = insertarAux(n.getDerecho(), n, elemento);
                } else {
                    n.setDerecho(new NodoAVL(elemento, null, null));
                }
            }
            n.recalcularAltura();
        }
        autobalance(n, padre);
        return exito;
    }

    private void autobalance(NodoAVL n, NodoAVL padre) {
        int balance, balanceHI, balanceHD;
        balance = calcularBalance(n);
        balanceHI = calcularBalance(n.getIzquierdo());
        balanceHD = calcularBalance(n.getDerecho());
        if (balance == 2 || balance == -2) {
            if (balance == 2) {
                if (balanceHI > -1) {
                    //rotar a la derecha
                    n = rotarDerecha(n);
                } else {
                    //doble izquierda-derecha
                    n.setIzquierdo(rotarIzquierda(n.getIzquierdo()));
                    n = rotarDerecha(n);
                }
            } else {
                if (balance == -2){
                    if (balanceHD < 1) {
                        //rotar a la izquierda
                        n = rotarIzquierda(n);
                    } else {
                        //doble derecha-izquierda
                        n.setDerecho(rotarDerecha(n.getDerecho()));
                        n = rotarIzquierda(n);
                    }
                }
            }
            if (padre == null) {
                this.raiz = n;
            } else {
                Comparable elemDeN = (Comparable) n.getElem();
                if (elemDeN.compareTo(padre.getElem()) < 0) {
                    //podria hacer (padre.getElem().comparaTo(n.getElem()) < 0), asi me ahorro hacer pasar por parametro
                    //podria pasar por parametro elemComparado y asignarle en los metodos resursivos elemComparado = elem.compareTo(n.getElem())
                    padre.setIzquierdo(n);
                } else {
                    padre.setDerecho(n);
                }
            }
        }
    }

    private int calcularBalance(NodoAVL n) {
        int numBalance = -1;
        if (n != null) {
            int altIzq, altDer;
            NodoAVL hijoIzq = n.getIzquierdo();
            NodoAVL hijoDer = n.getDerecho();
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

    public boolean eliminar(Comparable elem) {
        /*Recibe el elemento que se desea eliminar y se procede a removerlo del árbol. Si no se encuentra
        el elemento no se puede realizar la eliminación. Devuelve verdadero si el elemento se elimina de la
        estructura y falso en caso contrario.*/
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, null, elem);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n, NodoAVL padre, Comparable elem) {
        boolean exito = false;
        if (elem.compareTo(n.getElem()) == 0) {
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                caso1(elem, padre);
            } else {
                if (n.getIzquierdo() == null || n.getDerecho() == null) {
                    caso2(elem, n, padre);
                } else {
                    caso3(n);
                }
            }
            autobalance(n, padre);
            exito = true;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = eliminarAux(n.getIzquierdo(), n, elem);
                }
            } else {
                if (n.getDerecho() != null) {
                    exito = eliminarAux(n.getDerecho(), n, elem);
                }
            }
        }
        return exito;
    }

    //Caso 1 de eliminar.
    private void caso1(Comparable elemento, NodoAVL padre) {
        if (padre == null) {
            //Caso especial al intentar eliminar la raiz
            this.raiz = null;
        } else {
            int temp = elemento.compareTo(padre.getElem());
            if (temp < 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    //Caso 2 de eliminar.
    private void caso2(Comparable elem, NodoAVL hijo, NodoAVL padre) {
        //Busco al candidato para reemplazar al nodo
        //almenos 1 sera null.
        NodoAVL der = hijo.getDerecho();
        NodoAVL izq = hijo.getIzquierdo();
        if (padre == null) {
            //Caso especial.
            if (der == null) {
                this.raiz = izq;
            } else {
                this.raiz = der;
            }
        } else {
            //Verifico la rama derecha o izquierda.
            int temp = elem.compareTo(padre.getElem());
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
    private void caso3(NodoAVL actual) {
        NodoAVL nodoA = actual.getIzquierdo(), nodoPadreA = actual;
        while (nodoA.getDerecho() != null) {
            nodoPadreA = nodoA;
            nodoA = nodoA.getDerecho();
        }
        actual.setElem(nodoA.getElem());
        NodoAVL hijoDer = nodoA.getDerecho();
        if (actual.getIzquierdo() == nodoA) {
            actual.setIzquierdo(hijoDer);
        } else {
            nodoPadreA.setIzquierdo(hijoDer);
        }
    }

    public boolean esVacio() {
        // Devuelve falso si hay al menos un elemento en el árbol y verdadero en caso contrario.
        return this.raiz == null;
    }

    public Lista listar() {
        /*recorre el árbol completo y devuelve una lista ordenada con los elementos que se encuentran
        almacenados en él.*/
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lis);
        }
        return lis;
    }

    private void listarAux(NodoAVL n, Lista lis) {
        if (n.getIzquierdo() != null) {
            listarAux(n.getIzquierdo(), lis);
        }

        lis.insertar(n.getElem(), lis.longitud() + 1);

        if (n.getDerecho() != null) {
            listarAux(n.getDerecho(), lis);
        }
    }

    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        /*Recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada con los elementos que
        se encuentran en el intervalo [elemMinimo, elemMaximo].*/
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarRangoAux(this.raiz, lis, elemMin, elemMax);
        }
        return lis;
    }

    private void listarRangoAux(NodoAVL n, Lista lis, Comparable elemMin, Comparable elemMax) {
        if (n.getIzquierdo() != null && (elemMin.compareTo(n.getElem()) < 0)) {
            listarRangoAux(n.getIzquierdo(), lis, elemMin, elemMax);
        }
        if ((elemMin.compareTo(n.getElem()) <= 0) && (elemMax.compareTo(n.getElem()) >= 0)) {
            lis.insertar(n.getElem(), lis.longitud() + 1);
        }

        if (n.getDerecho() != null && (elemMax.compareTo(n.getElem()) > 0)) {
            listarRangoAux(n.getDerecho(), lis, elemMin, elemMax);
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

    private Object minimoElemAux(NodoAVL n) {
        Object elem = n.getElem();
        if (n.getIzquierdo() != null) {
            elem = minimoElemAux(n.getIzquierdo());
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

    private Object maximoElemAux(NodoAVL n) {
        Object elem = n.getElem();
        if (n.getDerecho() != null) {
            elem = maximoElemAux(n.getDerecho());
        }
        return elem;
    }

    public ArbolAVL clone() {
        ArbolAVL clon = new ArbolAVL();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoAVL cloneAux(NodoAVL n) {
        NodoAVL nuevo = null;
        if (n != null) {
            nuevo = new NodoAVL(n.getElem(), cloneAux(n.getIzquierdo()), cloneAux(n.getDerecho()));
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

    private String toStringAux(NodoAVL nodo) {
        String cadenaNodo = "";
        if (nodo != null) {
            cadenaNodo = nodo.getElem().toString();
            if (nodo.getIzquierdo() != null) {
                cadenaNodo = cadenaNodo + ": HI: " + nodo.getIzquierdo().getElem().toString();
            } else {
                cadenaNodo = cadenaNodo + ": HI:--";
            }
            if (nodo.getDerecho() != null) {
                cadenaNodo = cadenaNodo + ", HD: " + nodo.getDerecho().getElem().toString() + "\n";
            } else {
                cadenaNodo = cadenaNodo + ", HD:--\n";
            }
            cadenaNodo = cadenaNodo + toStringAux(nodo.getIzquierdo());
            cadenaNodo = cadenaNodo + toStringAux(nodo.getDerecho());
        }
        return cadenaNodo;
    }
}

