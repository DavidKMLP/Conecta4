package ModosDeJuego;

import Factoria.IJugador;
import Factoria.TipoJugador;


public class HumanoVsMaquina extends PlantillaJuego implements IModalidades {

    /**
     * CONSTRUCTOR
     */
    public HumanoVsMaquina() {

    }

    /**
     * MÉTODOS
     */

    /**
     * Método que implementa los jugadores necesarios para el modo de HumanoVSMaquina
     * @return array que contiene los jugadores creados
     */
    @Override
    protected IJugador[] implementaJugadores() {
        IJugador jugador1;
        IJugador jugador2;
        jugador2 = crearJugador(TipoJugador.MAQUINA);
        do{
            jugador1 = crearJugador(TipoJugador.HUMANO);
        }while(noCoincideMismoNombre(jugador1.getNombre(),jugador2.getNombre()));

        return new IJugador[]{jugador1,jugador2};
    }

    /**
     * Método que permite jugar un partida de HumanoVSMaquina
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
        HumanoVsMaquina partido = new HumanoVsMaquina();
        partido.jugar();
    }


}
