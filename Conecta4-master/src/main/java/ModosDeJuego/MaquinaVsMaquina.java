package ModosDeJuego;

import Factoria.IJugador;
import Factoria.TipoJugador;

public class MaquinaVsMaquina extends PlantillaJuego implements IModalidades {

    /**
     * CONSTRUCTOR
     */
    public MaquinaVsMaquina() {

    }

    /**
     * MÉTODOS
     */

    /**
     * Método que implementa los jugadores necesarios para el modo de MaquinaVSMaquina
     * @return array que contiene los jugadores creados
     */
    @Override
    public IJugador[] implementaJugadores() {
        IJugador jugador1;
        IJugador jugador2;
        do{
            jugador2 = crearJugador(TipoJugador.MAQUINA);
            jugador1 = crearJugador(TipoJugador.MAQUINA);
        }while(noCoincideMismoNombre(jugador1.getNombre(),jugador2.getNombre()));

        return new IJugador[]{jugador1,jugador2};
    }

    /**
     * Método que permite jugar un partida de MaquinaVSMaquina
     */
    @Override
    public void jugar() {
        asignacionJugadoresPartida();
        IJugador jugadorActual = turno.tieneTurno();
        do{
            mensajeJugadorActual(jugadorActual);
            tablero.dibujar();
            jugadorActual.ponerFicha();
            mostrarMensajesResultadoPartida(jugadorActual);
            jugadorActual = cambioDeTurno();
        }while(!reglas.empate() && !reglas.victoria());
        tablero.dibujar();
        System.out.println();
        tablero.limpiarTablero();
    }


    public static void main(String[] args) {
        MaquinaVsMaquina partido = new MaquinaVsMaquina();
        partido.jugar();
    }


}
