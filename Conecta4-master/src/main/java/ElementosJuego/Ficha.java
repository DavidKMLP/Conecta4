package ElementosJuego;
import java.awt.*;

/**
 * TO DO :
 * -Clase ya acabada.
 */
public class Ficha{
    private char contenido;//Guarda el caracter de a
    private Color color;
    private String yellow="\033[33m";
    private String red="\033[31m";
    private String reset="\u001B[0m";
    /**
     * CONSTRUCTORES
     */
    public Ficha() {

    }

    public Ficha(char contenido,Color color) {
        this.contenido = contenido;
        this.color = color;
    }

    /**
     * GET
     */
    public char getContenido() {
        return contenido;
    }

    public Color getColor() {
        return color;
    }

    /**
     * MÉTODOS
     */
    private String colorEleccion(){
        if(color == Color.red){
            return red;
        }
        if(color == Color.yellow){
            return yellow;
        }
        return null;
    }

    public String dibujar(){
        return  " "+colorEleccion()+contenido+reset+" ";
    }

    /**
     * PRUEBAS
     */

    public static void main(String[] args){
        System.out.println("Pruebas clase Ficha: ");

        Ficha ficha = new Ficha('X',Color.white);

        Ficha ficha2 = new Ficha();
        ficha.getColor();
        char aux=  ficha.getContenido();
        System.out.println(aux);

        System.out.println(ficha.dibujar());
    }
}
