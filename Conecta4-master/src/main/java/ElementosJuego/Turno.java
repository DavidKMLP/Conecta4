package ElementosJuego;

import Factoria.IJugador;
import Factoria.Maquina;
import Factoria.PlantillaJugador;

/**
 * TO DO:
 * -Clase limpia
 */
public class Turno {
    private int turno;
    private IJugador[] jugadores;

    /**
     * CONSTRUCTOR
     */
    public Turno(IJugador[] jugadores) {
        this.jugadores = jugadores;
        this.turno = jugadorInicial();
    }

    /**
     * MÉTODOS
     */

    /**
     * Método que devuelve un número aleatorio entre 0 y la longitud de jugadores -1 de forma aleatoria
     */
    private int jugadorInicial(){
        return (int) (Math.random()*jugadores.length);
    }
    /**
     * Método que nos indica el turno actual
     * @return
     */
    public IJugador tieneTurno(){
        return jugadores[turno];
    }

    /**
     * Método que nos devuelve el turno que toca
     * @return
     */
    private int siguienteJugada(){
        return (turno + 1) % jugadores.length;
    }

    /**
     * Método que cambia el turno
     */
    public void cambiarTurno(){
        turno = siguienteJugada();
    }

    /**
     * Método que une el nombre del jugador con un turno
     * @return devuelve
     */
    public String nombreJugadorConTurno(){
        return jugadores[turno].getNombre();
    }

    /**
     * Método que devuelve la letra de la ficha del jugador correspondiente
     * @return
     */
    public char letraJugadorConTurno(){
        return jugadores[turno].getLetraFicha();
    }
}
