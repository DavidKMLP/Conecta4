package ModosDeJuego;

import Factoria.*;


public class HumanoVsHumano extends PlantillaJuego implements IModalidades {

    /**
     * CONSTRUCTOR
     */
    public HumanoVsHumano() {

    }

    /**
     * MÉTODOS
     */

    /**
     * Método que implementa los jugadores necesarios para el modo de HumanoVSHumano
     * @return array que contiene los jugadores creados
     */
    @Override
    protected IJugador[] implementaJugadores() {
        IJugador jugador1;
        IJugador jugador2;
        jugador1 = crearJugador(TipoJugador.HUMANO);
        do {

             jugador2 = crearJugador(TipoJugador.HUMANO);
             if(noCoincideMismoNombre(jugador1.getNombre(),jugador2.getNombre())){
                 System.out.println("Mismo nombre de jugadores. Vuelve a introducir los nombres");
             }
        }while(noCoincideMismoNombre(jugador1.getNombre(),jugador2.getNombre()));
        return  new IJugador[]{jugador1,jugador2};
    }

    /**
     * Método que permite jugar un partida de HumanoVSHumano
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
        HumanoVsHumano partido = new HumanoVsHumano();
        partido.jugar();
    }

}
