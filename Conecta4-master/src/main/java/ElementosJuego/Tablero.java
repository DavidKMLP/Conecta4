package ElementosJuego;

import Excepciones.ColumnaLlenaExcepcion;
import Excepciones.PosicionFueraDelTableroExcepcion;
import java.awt.*;
import java.util.InputMismatchException;
import static ElementosJuego.Conecta4.scanner;

public class Tablero {

    private int NUM_FILAS;
    private int NUM_COLUMNAS;
    private Casilla[][] casillas;
    private final int INICIO = 0;
    private HistorialPosicion historialPosicion;


    /**
     * Constructor que crea el tablero a partir del numero de filas y columnas pasados como parámetros
     * Además, inicializa el tablero a vacío
     */

    public Tablero(int NUM_FILAS, int NUM_COLUMNAS) {
        this.NUM_FILAS = NUM_FILAS;
        this.NUM_COLUMNAS = NUM_COLUMNAS;
        this.casillas = new Casilla[NUM_FILAS][NUM_COLUMNAS];
        this.historialPosicion = HistorialPosicion.getInstance();
        inicializarTablero();
    }


    /**
     * GET
     */

    public int getNUM_FILAS() {
        return NUM_FILAS;
    }

    public int getNUM_COLUMNAS() {
        return NUM_COLUMNAS;
    }


    /**
     * MÉTODOS
     */

    /**
     * Inicializa el Tablero
     */
    public void inicializarTablero() {
        for (int fila = INICIO; fila < NUM_FILAS; fila++) {
            for (int columna = INICIO; columna < NUM_COLUMNAS; columna++) {
                casillas[fila][columna] = new Casilla();
            }
        }
    }

    /**
     * Métodos que dibujan el Tablero elemento a elemento y otros
     */
    private void dibujarSeparador() {
        System.out.print("|");
    }

    private void dibujarGuiones() {
        System.out.println("-----------------------------");
    }

    private void dibujarSaltoLinea() {
        System.out.println();
    }

    private void dibujarContenidoCasilla(int fila, int columna) {
        System.out.print(casillas[fila][columna].dibujar());
    }

    public void dibujar() {
        dibujarGuiones();
        for (int fila = INICIO; fila < NUM_FILAS; fila++) {
            dibujarSeparador();
            for (int columna = INICIO; columna < NUM_COLUMNAS; columna++) {
                dibujarContenidoCasilla(fila, columna);
                dibujarSeparador();
            }
            dibujarSaltoLinea();
        }
        dibujarGuiones();
    }

    /**
     * Método que comprueba que la fila y la columna pasadas por parametro estén vacios
     * @param fila    param
     * @param columna param
     * @return true si esta libre, false ocupado
     */
    public boolean estaLibre(int fila, int columna) {
        return casillas[fila][columna].isVacia();
    }

    /**
     * Método que recorre uno a uno los elementos del tablero y comprueba si está ocupado
     * @return true si esta ocupado
     */
    public boolean estaLlenoTablero() {
        boolean resultado = true;
        for (int fila = INICIO; fila < NUM_FILAS; fila++) {
            for (int columnas = INICIO; columnas < NUM_COLUMNAS; columnas++) {
                if (estaLibre(fila, columnas)) {
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    /**
     * Devuelve la ultima fila
     * @param fila fila a poner
     * @return true si es la última fila
     */
    private boolean ultimaFila(int fila) {
        return fila == NUM_FILAS;
    }

    /**
     * Método que asume que la columna posada por parametro esté llena y lo recorre para comprobar que sea cierto
     * @param columna elegida
     * @return resultado que devuelve true si está llena, y falso si lo contrario
     */
    private boolean columnaLlena(int columna) {
        boolean lleno = true;
        int filaActual = INICIO;
        while (lleno && !ultimaFila(filaActual)) {
            if (estaLibre(filaActual, columna)) {
                lleno = false;
            } else {//Se mete aqui cuando esta ocupado
                filaActual++;
            }
        }
        return lleno;
    }

    /**
     * Metodo que comprueba que el numero pasado por parametro esta fuera del tablero
     * @param numero a comprobar
     * @return true si está fuera
     */
    private boolean columnaDentroLimites(int numero){
        return numero < INICIO || numero >= NUM_COLUMNAS;
    }

    /**
     * Método que maneja los errores que puede tener el usuario al introducir la columna por teclado
     * @return la eleccion si no hay error
     * @throws PosicionFueraDelTableroExcepcion si la eleccion no es válida
     */
    private int inputColumna() throws PosicionFueraDelTableroExcepcion {
        int eleccion = scanner.nextInt();
        if(columnaDentroLimites(eleccion)){
            throw new PosicionFueraDelTableroExcepcion("Escribe numero en el rango de " + INICIO + " a " + (NUM_COLUMNAS-1));
        }
        return eleccion;
    }

    private String mensajeErrorTablero(){
        return " Solo datos numericos dentro del intervalo pedido"+". Introduce el dato de nuevo";
    }
    private String mensajePedirColumna(){
        return "Introduzca una columna: ("+INICIO+"-"+(NUM_COLUMNAS-1)+")";
    }

    /**
     * Método que maneja la columna introducida por teclado y contempla los posibles errores que pueda tener el usuario
     * al introducir su columna
     * @return columna escogida
     * @throws ColumnaLlenaExcepcion si la columna introducida está llena
     */
    private int pedirColumna() throws ColumnaLlenaExcepcion {
        int numero;
        do {
            try {
                System.out.println(mensajePedirColumna());
                numero = inputColumna();
                if(columnaLlena(numero)){
                    throw new ColumnaLlenaExcepcion("La columna escogida está llena. Introduzca una columna que no esté llena");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e +mensajeErrorTablero());
                scanner.next(); //Limpieza del buffer del scanner
                numero = pedirColumna();
            } catch(PosicionFueraDelTableroExcepcion ex){
                System.out.println("Error: "+ex+"."+mensajeErrorTablero());
                numero = pedirColumna();
            }
        }while(columnaDentroLimites(numero));
        return numero;

    }



    /**
     * Método que obliga al usuario que el valor pasado en pedirColumna() sea válido
     * @return devuelve la columna si es válida
     * Estos métodos serán cambiados en las siguientes entregas contemplando excepciones
     */
    public int escogerColumnaValida() {
        int columna;
        do {
            try {
                columna = pedirColumna();
            } catch (ColumnaLlenaExcepcion e) {
                System.out.println(e.getMessage());
                columna = escogerColumnaValida();
            }
        } while (columnaLlena(columna));

        return columna;
    }

    /**
     * Método que escoge una columna de manera aleatoria y sea válido en el tablero
     * @return columna aleatoria
     */
    public int escogerColumnaValidaAleatoria() {
        int columna;
        do {
            columna = (int) (Math.random()*NUM_COLUMNAS);
        } while (columnaLlena(columna));
        return columna;
    }

    /**
     * Método que devuelve la fila que corresponde a la columna escogida, suponiendo que la columna NO ESTÁ LLENA
     * @param columna a comprobar
     * @return la primera fila no ocupada
     */
    public int filaAsignada(int columna) {
        int fila = NUM_FILAS - 1;
        boolean comprobacionFilaAsignada = false;
        while (!comprobacionFilaAsignada) {
            if (casillas[fila][columna].isVacia())
                comprobacionFilaAsignada = true;
            else fila--;
        }
        return fila;
    }

    /**
     * Método que pone una ficha en el tablero usando el método filaAsignada()
     * Hacemos un set con la ficha pasada por parámetro
     * @param ficha Ficha a poner
     * @param columna columna donde ponemos la ficha
     */
    public void ponerFicha(Ficha ficha,int columna) {
        int fila = filaAsignada(columna); //Nos dice la fila correspondiente a poner
        historialPosicion.setPosicionActual(fila, columna);
        casillas[fila][columna].setFicha(ficha);
    }



    /**
     * Método que comprueba si la letra de una casilla es igual a la letra pasada como parámetro
     * @param filaActual fila a poner
     * @param columna columna a poner
     * @param letra letra a poner
     * @return Devuelve true si es igual
     */
    public boolean coincidideMismaLetra(int filaActual, int columna, char letra) {
        return casillas[filaActual][columna].getLetraFicha() == letra;
    }

    /**
     * Método que borra la ficha de una posicion
     */
    public void limpiarCasilla (int fila, int columna){
        casillas[fila][columna].setFicha(null);
    }

    /**
     * Método que borra el tablero actual y lo vuelve a poner vacío
     */
    public void limpiarTablero(){
        for (int fila = INICIO; fila < NUM_FILAS; fila++) {
            for (int columna = INICIO; columna < NUM_COLUMNAS; columna++) {
                if (!casillas[fila][columna].isVacia())
                    limpiarCasilla(fila, columna);
            }
        }
    }


    /**
     * PRUEBAS
     */

    public static void main(String[] args) {
        Ficha ficha = new Ficha('R', Color.red);
        Tablero tablero = new Tablero(6, 7);
        do {
            tablero.ponerFicha(ficha,1);
            tablero.dibujar();
        } while (!tablero.estaLlenoTablero());
    }
}