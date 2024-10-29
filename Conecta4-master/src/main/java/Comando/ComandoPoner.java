package Comando;

import ElementosJuego.Ficha;
import ElementosJuego.HistorialPosicion;
import ElementosJuego.Tablero;

public class ComandoPoner implements IComando{
    /**
     * Receivers del patrón Comando
     */
    private Ficha ficha;
    private Tablero tablero;
    private HistorialPosicion historialPosicion;

    /**
     * Constructor
     */
    public ComandoPoner(Ficha ficha, Tablero tablero, HistorialPosicion historialPosicion) {
        this.ficha = ficha;
        this.tablero = tablero;
        this.historialPosicion = historialPosicion;
    }

    /**
     * Getters y Setters
     *
     */


    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public HistorialPosicion getPosicion() {
        return historialPosicion;
    }

    public void setPosicion(HistorialPosicion historialPosicion) {
        this.historialPosicion = historialPosicion;
    }

    /**
     * Métodos
     */

    /**
     * Método que recibe la columna a poner y actualiza la posición actual a la columna
     * @return columna escogida
     */
    private int columnaEscoger(){
        int result = tablero.escogerColumnaValida();
        historialPosicion.setColumnaActual(result);
        return result;
    }

    /**
     * Método que comprueba si se han puesto fichas anteriormente
     * @return true si se han puesto fichas antes
     */
    private boolean comprobacionFichaAnteiormente(){
        return historialPosicion.getColumnaActual() != 0;
    }

    /**
     * Método que asigna la posicion actual a las coordenadas de la posicion escogida del tablero
     */
    @Override
    public void execute() {
        if (comprobacionFichaAnteiormente()) {
            historialPosicion.setPosicionAntigua(historialPosicion.getFilaActual(), historialPosicion.getColumnaActual());
        }
        historialPosicion.setColumnaActual(columnaEscoger());
        tablero.ponerFicha(ficha, historialPosicion.getColumnaActual());
    }

    /**
     * Método que asigna la posicion antigua a la actual y borra su posicion antigua del tablero
     */
    @Override
    public void undo() {
        historialPosicion.setFilaAntigua(historialPosicion.getFilaActual());
        historialPosicion.setColumnaAntigua(historialPosicion.getColumnaActual());
        tablero.limpiarCasilla(historialPosicion.getFilaActual(), historialPosicion.getColumnaActual());
    }

    /**
     * Método que pone en el tablero una ficha en la posicion actual
     */
    public void redo(){
        tablero.ponerFicha(ficha, historialPosicion.getColumnaActual());
    }


}