package Comando;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Pila<T> {
    private List<T> movimientos;

    public Pila(){
        movimientos = new ArrayList<>();
    }

    /**
     * Método que apila un elemento pasado por parametro y lo pone en la cima
     * @param elemento Elemento a apilar
     */
    public void apilar(T elemento){
        movimientos.add(0,elemento);
    }

    /**
     * Método que desapila el primer elemeto de una pila
     * @return
     */
    public T desapilar(){
        if(movimientos.size() > 0){
            return movimientos.remove(0);
        }
        else
            return null;
    }

    public boolean isVacia(){
        return movimientos.size() == 0;
    }

    public void vaciar(){
        movimientos.clear();
    }
}
