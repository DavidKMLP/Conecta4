package ElementosJuego;

import Factoria.IJugador;
import Factoria.PlantillaJugador;

public class Reglas {
    private Tablero tablero;
    private IJugador[] jugadores;
    private final int INICIO = 0 ;
    private final int FICHASVICTORIA=4;

    /**
     * CONSTRUCTORES
     */
    public Reglas(Tablero tablero, IJugador[] jugadores) {
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    /**
     * MÉTODOS
     */

    /**
     * Método que nos comprueba la condición de victoria vertical en una columna para un jugador
     * @param columna
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    private boolean comprobarVictoriaVerticalUnaColumna(int columna, char letra){
        boolean victoriaVertical = false;
        int filaActual = tablero.getNUM_FILAS()-1;
        int cuatroFichasSeguidasIguales = 0;

        while(!victoriaVertical && filaActual>=INICIO){
            if (!tablero.estaLibre(filaActual,columna)) {
                if (tablero.coincidideMismaLetra(filaActual,columna,letra)) {
                    cuatroFichasSeguidasIguales++;
                } else {
                    cuatroFichasSeguidasIguales = 0;
                }
            }
            if (cuatroFichasSeguidasIguales == FICHASVICTORIA)
                victoriaVertical = true;
            filaActual--;
        }
        return victoriaVertical;
    }

    /**
     * Método que nos comprueba la condición de victoria vertical en todo el tablero para un jugador
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    private boolean comprobarVictoriaVerticalEnTablero (char letra){
        boolean victoria = false;
        for (int i = INICIO; i< tablero.getNUM_COLUMNAS(); i++){
            if (!victoria) {
                victoria = comprobarVictoriaVerticalUnaColumna(i, letra);
            }
        }
        return victoria;
    }

    /**
     * Método que nos comprueba la condición de victoria vertical en todo el tablero para cualquier jugador
     * @return Devuelve true si algun jugador ha ganado
     */
    public boolean victoriaVertical(){
        boolean victoria = false;
        for (int i =INICIO; i< jugadores.length; i++){
            if (!victoria) {
                victoria = comprobarVictoriaVerticalEnTablero(jugadores[i].getLetraFicha());
            }
        }
        return victoria;
    }

    /**
     * Método que nos comprueba la condición de victoria vertical en una columna para un jugador
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    private boolean comprobarVictoriaHorizontalUnaColumna(int fila, char letra){
        boolean victoriaHorizontal = false;
        int columnaActual = tablero.getNUM_COLUMNAS()-1;
        int cuatroFichasSeguidasIguales = 0;

        while(!victoriaHorizontal && columnaActual>=INICIO){
            if (!tablero.estaLibre(fila,columnaActual)) {
                if (tablero.coincidideMismaLetra(fila,columnaActual,letra)) {
                    cuatroFichasSeguidasIguales++;
                } else {
                    cuatroFichasSeguidasIguales = 0;
                }
            }
            else {
                cuatroFichasSeguidasIguales = 0; //no hay una ficha -> reseteo el contador
            }
            if (cuatroFichasSeguidasIguales == FICHASVICTORIA)
                victoriaHorizontal = true;
            columnaActual--;
        }
        return victoriaHorizontal;
    }

    /**
     * Método que nos comprueba la condición de victoria horizontal en todo el tablero para un jugador en concreto
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    private boolean comprobarVictoriaHorizontalEnTablero (char letra){
        boolean victoria = false;
        for (int i = INICIO; i< tablero.getNUM_FILAS(); i++){
            if (!victoria) {
                victoria = comprobarVictoriaHorizontalUnaColumna(i, letra);
            }
        }
        return victoria;
    }

    /**
     * Método que nos comprueba la condición de victoria horizontal en todo el tablero para cualquier jugador
     * @return Devuelve true si algun jugador ha ganado
     */
    public boolean victoriaHorizontal(){
        boolean victoria = false;
        for (int i = INICIO; i< jugadores.length; i++){
            if (!victoria){
                victoria = comprobarVictoriaHorizontalEnTablero(jugadores[i].getLetraFicha());
            }
        }
        return victoria;
    }

    /**
     * Metodo que comprueba que la fila y columna pasadas por parametro estan dentro del tabler
     *
     * @return true si se encuentra en el tablero
     */
    private boolean casillaEnTablero(int fila, int columna){
        return fila >=INICIO && fila< tablero.getNUM_FILAS() && columna>=INICIO && columna < tablero.getNUM_COLUMNAS();
    }

    /**
     * Método que nos comprueba la condición de victoria en diagonal para un jugador a partir de una posicion
     * (mira la diagonal de izquierda a derecha)
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    public boolean comprobacionDiagonalMayor(int fila, int columna,char letra){
        boolean victoriaDiagonal= false;
        int columnaActual = columna;
        int filaActual = fila;
        int cuatroFichasSeguidasIguales = 0;

        while(!victoriaDiagonal && casillaEnTablero(filaActual, columnaActual)){
            if (!tablero.estaLibre(filaActual,columnaActual)) {
                if (tablero.coincidideMismaLetra(filaActual,columnaActual,letra)) {
                    cuatroFichasSeguidasIguales++;
                }
                else {
                    cuatroFichasSeguidasIguales = 0; //hay una ficha pero no coincide -> reseteo el contador
                }
            }
            else {
                cuatroFichasSeguidasIguales = 0; //no hay una ficha -> reseteo el contador
            }
            if (cuatroFichasSeguidasIguales == FICHASVICTORIA)
                victoriaDiagonal = true;
            columnaActual++;
            filaActual++;
        }
        return victoriaDiagonal;
    }

    /**
     * Método que nos comprueba la condición de victoria de la diagonal mayor en todo el tablero para un jugador en concreto
     * Entendemos por diagonal mayor o principal a la que va de izquierda a derechas
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    private boolean victoriaDiagonalMayor(char letra){
        boolean victoria = false;
        for(int fila = INICIO; fila < tablero.getNUM_FILAS() ; fila++ ){
            for (int columna = INICIO; columna < tablero.getNUM_COLUMNAS(); columna++) {
                if(!victoria){
                    victoria = comprobacionDiagonalMayor(fila,columna,letra);
                }
            }

        }
        return victoria;
    }

    /**
     * Método que nos comprueba la condición de victoria en diagonal para un jugador a partir de una posicion
     * (mira la diagonal de derecha a izquierda)
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    public boolean comprobacionDiagonalMenor(int fila, int columna,char letra){
        boolean victoriaDiagonal= false;
        int columnaActual = columna;
        int filaActual = fila;
        int cuatroFichasSeguidasIguales = 0;

        while(!victoriaDiagonal && casillaEnTablero(filaActual, columnaActual)){
            if (!tablero.estaLibre(filaActual,columnaActual)) {
                if (tablero.coincidideMismaLetra(filaActual,columnaActual,letra)) {
                    cuatroFichasSeguidasIguales++;
                } else {
                    cuatroFichasSeguidasIguales = 0;
                }
            }
            else {
                cuatroFichasSeguidasIguales = 0; //no hay una ficha -> reseteo el contador
            }
            if (cuatroFichasSeguidasIguales == FICHASVICTORIA)
                victoriaDiagonal = true;
            columnaActual--;
            filaActual++;
        }
        return victoriaDiagonal;
    }

    /**
     * Método que nos comprueba la condición de victoria de la diagonal menor en todo el tablero para un jugador en concreto
     * Entendemos por diagonal menor a la inversa de la mayor
     * @param letra Letra que corresponde al jugador
     * @return Devuelve true si ha ganado
     */
    private boolean victoriaDiagonalMenor(char letra){
        boolean victoria = false;
        for (int fila = tablero.getNUM_FILAS()-1; fila > INICIO; fila--) {
            for (int columna = tablero.getNUM_COLUMNAS()-1; columna > INICIO; columna--) {
                if(!victoria){
                    victoria = comprobacionDiagonalMenor(fila,columna,letra);
                }
            }
        }
        return victoria;
    }

    /**
     * Método que nos comprueba la condición de victoria diagonal en todo el tablero para cualquier jugador
     * @return Devuelve true si algun jugador ha ganado
     */

    public boolean victoriaDiagonal(){
        boolean victoria = false;

        for (int indice = INICIO ; indice < jugadores.length; indice++){
            if(!victoria) {
                victoria = victoriaDiagonalMayor(jugadores[indice].getLetraFicha());
            }
            if(!victoria) {
                victoria = victoriaDiagonalMenor(jugadores[indice].getLetraFicha());
            }
        }
        return victoria;
    }

    /**
     * Método que comprueba todas las victorias posibles (diagonal, horixontal overtical
     * @return true si hay cuatro seguidas
     */
    public boolean victoria(){
        return victoriaDiagonal()||victoriaHorizontal()||victoriaVertical();
    }

    /**
     * Método que comprueba si se ha empatado ,es decir, si el tablero está lleno
     * @return true si hay empate
     */
    public boolean empate(){return tablero.estaLlenoTablero();}
}
