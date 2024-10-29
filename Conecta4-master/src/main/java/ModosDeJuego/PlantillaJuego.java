package ModosDeJuego;

import ElementosJuego.Reglas;
import ElementosJuego.Tablero;
import ElementosJuego.Turno;
import Factoria.IJugador;
import Factoria.JugadorFactory;
import Factoria.TipoJugador;

import java.awt.*;
import java.util.Objects;

public abstract class PlantillaJuego {
    protected Turno turno;
    protected Tablero tablero;
    protected Reglas reglas;
    protected IJugador[] jugadores;
    protected JugadorFactory jugadorFactory;
    protected int INICIO = 0;
    protected final int NUM_FILAS = 6;
    protected final int NUM_COLUMNAS = 7;
    protected final int NUM_JUGADORES = 2;

    /**
     * CONSTRUCTOR
     */
    public PlantillaJuego() {
        this.jugadorFactory = new JugadorFactory();
        this.tablero = new Tablero(NUM_FILAS,NUM_COLUMNAS);
        this.reglas = new Reglas(tablero, jugadores);
    }

    /**
     * MÉTODOS
     */

    /**
     * Crea un jugador de cualquier tipo usando el JugadorFactory
     * @return Devuelve el jugador creado
     */
    protected IJugador crearJugador(TipoJugador tipoJugador) {
        return jugadorFactory.crearJugador(tipoJugador);
    }

    /**
     * Mete a los jugadores según la modalidad del juego en un array para poder manejarlo en las condiciones del juego
     * @return Arreglo de jugadores
     */
    protected abstract IJugador[] implementaJugadores();

    /**
     * Comprueba que dos jugadores no tengan el mismo nombre
     * @param nombre1 Nombre del jugador1
     * @param nombre2 Nombre del jugador2
     * @return True si son iguales
     */
    protected boolean noCoincideMismoNombre(String nombre1, String nombre2) {
        return Objects.equals(nombre1, nombre2);
    }

    /**
     * Método que cambia el Turno de los jugadores
     * @return El jugador correspondiente al turno
     */
    protected IJugador cambioDeTurno(){
        turno.cambiarTurno();
        return turno.tieneTurno();
    }

    /**
     * Muestra el resultado de la partida si se cumple alguna de las condiciones
     * @param jugadorActual Jugador del turno actual
     */
    protected void mostrarMensajesResultadoPartida(IJugador jugadorActual){
        if(reglas.victoria()){
            System.out.println("Ha ganado "+jugadorActual.getNombre()+" ("+jugadorActual.getLetraFicha()+")");
        }else if(tablero.estaLlenoTablero()){
            System.out.println("Empate. El tablero está lleno");
        }
    }

    protected void mensajeJugadorActual(IJugador jugadorActual){
        System.out.println("Pone ficha " +jugadorActual.getNombre()+" ("+jugadorActual.getLetraFicha()+")");
    }

    /**
     * Método que asigna a los jugadores una letraFicha según el indice: 0->Y 1->R
     * @param jugadores Array de jugadores de la partida
     */
    private void asignarLetraFicha(IJugador[] jugadores) {
        for (int indice = INICIO; indice < NUM_JUGADORES; indice++) {
            jugadores[indice].setLetraFicha(indice);
        }
    }

    /**
     * Método que asigna a los jugadores una Color de ficha según su letra: Y->"yellow" R->"red"
     * @param jugadores Array de jugadores de la partida
     */
    private void asignarColorFicha(IJugador[] jugadores) {
        for (int indice = INICIO; indice < NUM_JUGADORES; indice++) {
            if(jugadores[indice].getLetraFicha() == 'Y'){
                jugadores[indice].setColorFicha(Color.yellow);
            }else if(jugadores[indice].getLetraFicha() == 'R'){
                jugadores[indice].setColorFicha(Color.RED);
            }
        }
    }

    /**
     * Método que asigna a los jugadores el mismo tablero
     * @param jugadores Array de jugadores de la partida
     */
    private void asignarTablero(IJugador[] jugadores) {
        for (int indice = INICIO; indice < NUM_JUGADORES; indice++) {
            jugadores[indice].setTablero(tablero);
        }
    }

    /**
     * Método que asigna todos los atributos necesarios del Jugador para poder jugar
     */
    protected void asignacionJugadoresPartida(){
        this.jugadores = implementaJugadores();
        this.turno = new Turno(jugadores);
        this.reglas = new Reglas(tablero, jugadores);
        asignarLetraFicha(jugadores);
        asignarColorFicha(jugadores);
        asignarTablero(jugadores);
    }


}
