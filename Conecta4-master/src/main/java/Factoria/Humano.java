package Factoria;

import Comando.ComandoPoner;
import Comando.GestorComandos;
import ElementosJuego.Ficha;
import ElementosJuego.HistorialPosicion;
import Excepciones.InputVacioExcepcion;
import Excepciones.OpcionIncorrectaExcepcion;

import java.awt.*;
import java.util.InputMismatchException;

import static ElementosJuego.Conecta4.scanner;


public class Humano  extends PlantillaJugador implements IJugador {
    protected char letraFicha;
    protected Color colorFicha;
    private GestorComandos comandos;
    private HistorialPosicion historialPosicion;


    /**
     * CONSTRUCTOR
     */
    public Humano(){
        this.nombre = escogerNombre();
        this.comandos = GestorComandos.getInstance();
        this.historialPosicion = HistorialPosicion.getInstance();
    }

    /**
     * GET
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public char getLetraFicha() {
        return letraFicha;
    }
    @Override
    public Color getColorFicha() {
        return colorFicha;
    }


    /**
     * SET
     */
    @Override
    public void setLetraFicha(int indice) {
        if(indice == 0){
            this.letraFicha = AMARILLA;
        }else if(indice == 1){
            this.letraFicha = ROJA;
        }
    }

    @Override
    public void setColorFicha(Color colorFicha) {
        this.colorFicha = colorFicha;
    }


    /**
     * MÉTODOS
     */

    /**
     * Método que imprime un mensaje pidiendo un nombre
     */
    private String printMensajeIntroducirNombre(){
        return "Introduzca a continuación el nombre del jugador";
    }

    /**
     * Método que devuelve por pantalla el nombre introducido por teclado
     */
    private String introducirNombre() throws InputVacioExcepcion {
        String result = scanner.nextLine();
        if(result == null || result.trim().equals("")){
            throw new InputVacioExcepcion("Nombre no puede estar vacio");
        }else{
            return result;
        }
    }
    /**
     * Método que pide por pantalla un nombre y se asegura de que no esté vacío
     * @return el nombre introducido
     */
    @Override
    public String escogerNombre(){
        String result ="";
        do {
            System.out.println(printMensajeIntroducirNombre());
            try {
                result = introducirNombre();
            } catch (InputVacioExcepcion e) {
                System.out.println(e.getMessage());
            }
        }while( result.trim().equals(""));
        return result;
    }

    /**
     *  Método que maneja que se ponga una poscion fuera de lo posible
     * @return respuesta escogida
     * @throws OpcionIncorrectaExcepcion si la opcion está fuera del rango permitido
     */
    private int inputPedirUndoRedo()throws OpcionIncorrectaExcepcion{
        int respuesta = scanner.nextInt();
        scanner.nextLine();
        if(respuesta != 1 && respuesta != 2){
            throw new OpcionIncorrectaExcepcion("Escoge una opción válida");
        }
        else
            return respuesta;
    }

    /**
     * Método que pide al Usuario si desea hacer un redo mientras la opción sea la adecuada, donde maneja los errores posibles del usuario por teclado
     * @return eleccion del Usuario
     */
    private int inputPedirUndo(){
        int respuesta=0;
        do {
            System.out.println("Deshacer el ultimo movimiento?  1.SI 2.NO");
            try {
                respuesta = inputPedirUndoRedo();
            } catch (OpcionIncorrectaExcepcion e) {
                System.out.println(e + "Introduzca unicamente 1 o 2");
            } catch (InputMismatchException e) {
                System.out.println(e);
                scanner.nextLine();
            }
        }while (respuesta != 1 && respuesta != 2);
        return respuesta;
    }

    /**
     * Método que pide al Usuario si desea hacer un redo mientras la opcion sea adecuada, donde maneja los errores posibles del usuario por teclado
     * @return eleccion del Usuario
     */
    private int inputPedirRedo(){
        int respuesta=0;
        do {
            System.out.println("Rehacer el movimiento deshecho?  1.SI 2.NO");
            try {
                respuesta = inputPedirUndoRedo();
            } catch (OpcionIncorrectaExcepcion e) {
                System.out.println(e + "Introduzca unicamente 1 o 2");
            } catch (InputMismatchException e) {
                System.out.println(e);
                scanner.nextLine();
            }
        }while (respuesta != 1 && respuesta != 2);
        return respuesta;
    }


    /**
     * Método que pide al jugador si desea realizar la acción de deshacer su ultimo movimiento, y consecuentemente, si desea rehacerlo
     * @param cmd Comando a rehacer
     */
    private void pedirUndoRedo(ComandoPoner cmd){
        int respuesta;
        respuesta = inputPedirUndo();
        if(respuesta == 1){
            comandos.undo();
            tablero.dibujar();
            respuesta = inputPedirRedo();
            if(respuesta == 1){
                comandos.redo();
            } else if(respuesta == 2){
                comandos.execute(cmd);
            }
        }

    }


    /**
     * Método que permite al jugador poner una ficha en el tablero, y le da la eleccion de rehacer o deshacer su ultimo movimiento
     */
    @Override
    public void ponerFicha() {
        Ficha ficha = new Ficha(this.letraFicha, this.colorFicha);
        ComandoPoner cmd = new ComandoPoner(ficha, getTablero(), historialPosicion);
        comandos.execute(cmd);
        pedirUndoRedo(cmd);
    }
    }