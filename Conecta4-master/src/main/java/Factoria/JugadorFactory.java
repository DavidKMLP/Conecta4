package Factoria;

public class JugadorFactory {
    private final HumanoFactory crearHumano;
    private final MaquinaFactory crearMaquina;

    /**
     * CONSTRUCTOR
     */
    public JugadorFactory() {
        crearHumano = new HumanoFactory();
        crearMaquina = new MaquinaFactory();
    }

    /**
     * MÉTODOS
     */

    /**
     * Método que crea un jugador del tipo pasado por parámetro
     * @param tipoJugador enumerado que contiene los posibles tipos de jugadores
     * @return jugador creado
     */
    public IJugador crearJugador(TipoJugador tipoJugador){
        IJugador jugador = null;
        switch (tipoJugador){
            case HUMANO:
                jugador = crearHumano.getHumano();
                break;
            case MAQUINA:
                jugador = crearMaquina.getMaquina();
                break;
        }

        return jugador;
    }



}
