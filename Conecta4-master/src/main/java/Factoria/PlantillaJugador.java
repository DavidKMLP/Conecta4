package Factoria;

import ElementosJuego.Tablero;
import Excepciones.InputVacioExcepcion;

import java.awt.*;

public abstract class PlantillaJugador {

    protected String nombre;//Nombre del jugador
    protected char letraFicha;//letra de la ficha del jugador
    protected Color colorFicha;//Color de la ficha del jugador
    protected Tablero tablero;

    protected final char AMARILLA = 'Y';
    protected final char ROJA = 'R';


    /**
     * CONSTRUCTOR
     */
    public PlantillaJugador() {
    }

    /**
     * GET
     */

    public String getNombre() {
        return nombre;
    }

    public char getLetraFicha() {
        return letraFicha;
    }

    public Color getColorFicha() {
        return colorFicha;
    }

    public Tablero getTablero() {
        return tablero;
    }



    /**
     * SET
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * MÉTODOS
     */

    public abstract String escogerNombre() throws InputVacioExcepcion;

}
