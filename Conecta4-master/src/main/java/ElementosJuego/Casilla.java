package ElementosJuego;

import java.awt.*;

/**
 * TO DO:
 * -Clase ya acabada.
 */
public class Casilla {
    private Ficha ficha;
    private final String CASILLAVACIA ="   ";

    /**
     * CONSTRUCTORES
     */
    public Casilla() {
    }

    /**
     * GET
     */
    public Ficha getFicha() {
        return ficha;
    }

    public char getLetraFicha(){
        return getFicha().getContenido();
    }

    /**
     * SET
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    /**
     * Métodos
     */
    public boolean isVacia(){
        return ficha == null;
    }
    public String dibujar(){
        String resultado = CASILLAVACIA;
        if(!isVacia()){
            resultado = ficha.dibujar();
        }
        return resultado;
    }

    /**
     * PRUEBAS
     */
    public static void main(String[] args){
        System.out.println("Pruebas clase Casilla: ");
        Ficha ficha = new Ficha('X',Color.white);
        /*
        Casilla casilla = new Casilla(2,2);
        Casilla casilla1 = new Casilla(ficha,3,3);
        Casilla casilla2 = new Casilla(null,1,2);
        System.out.println(casilla.isVacia());
        System.out.println(casilla1.isVacia());
        System.out.println(casilla2.isVacia());


        System.out.println(casilla1.dibujar());
        System.out.println(casilla2.dibujar());
        */
    }
}
