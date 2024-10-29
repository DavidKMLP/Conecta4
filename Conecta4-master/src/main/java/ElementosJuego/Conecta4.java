package ElementosJuego;

import Excepciones.OpcionIncorrectaExcepcion;
import ModosDeJuego.InicializarPartida;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Conecta4 {

    private final int INICIO = 0 ;
    public static Scanner scanner;
    private final int NUM_OPCIONES=3;
    private InicializarPartida partida;

    /**
     * CONSTRUCTOR que crea todos los elementos necesarios para el funcionamiento del programa
     */

    public Conecta4() {
        scanner = new Scanner(System.in);
        partida = InicializarPartida.getInstance();
    }

    /**
     * MÉTODOS
     */

    private void dibujarGuiones() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
    }
    private String mensajeEleccionUsuario(){return "Por favor, elija el modo a jugar:";}
    private String mensajeComienzo(){
       return "Bienvenido a Conecta4.";
    }
    private String mensajeMenu(){return "1. Modo Básico(Humano vs Humano)\n"+"2. Modo entrenamiento(Humano Vs Maquina)\n"+"3. Modo demostracion(Maquina VS Maquina)\n"+"0. Salir del programa";}
    private String mensajeVolverIntroducir(){
        return "Introduzca un dato numérico en el rango pedido";
    }

    /**
     * Método que guarda la eleccion introducida por el usuario
     * @return opcion escogida
     * @throws OpcionIncorrectaExcepcion si no es una opcion válida lo introducido por teclado
     */
    private int inputUsuario() throws OpcionIncorrectaExcepcion{
        int numero = scanner.nextInt();
        scanner.nextLine();
        if(numero < INICIO || numero > NUM_OPCIONES){
            throw new OpcionIncorrectaExcepcion("Escribe numero en el rango de " + INICIO + " a " + (NUM_OPCIONES));
        }
        return numero;
    }

    /**
     * Método que pide al usuario por teclado la opcion a escoger del menu
     * @return Eleccion del usuario
     */
    private int pedirEleccionUsuario(){
        int numero;
        do {
            try {
                numero = inputUsuario();

            }catch (OpcionIncorrectaExcepcion e){
                System.out.println(e.getMessage());
                System.out.println(mensajeVolverIntroducir());
                numero = pedirEleccionUsuario();
            }catch (InputMismatchException e){
                System.out.println(e);
                System.out.println(mensajeVolverIntroducir());
                scanner.next();//Limpieza del buffer del scanner
                numero = pedirEleccionUsuario();
            }

        }while(numero < INICIO || numero > NUM_OPCIONES);
        return numero;
    }

    /**
     * Método que incia el menu para escoger todas las opciones posibles:
     * 1.HvH
     * 2.HvM
     * 3.MvM
     * 0.Salir del programa
     */
    public void inicio(){
        System.out.println(mensajeComienzo());
        int eleccion;
        do{
            dibujarGuiones();
            System.out.println(mensajeMenu());
            System.out.println(mensajeEleccionUsuario());
            eleccion = pedirEleccionUsuario();
            switch (eleccion){
                case 1:
                    partida.jugarHumanoVsHumano();
                    break;
                case 2:
                    partida.jugarHumanoVsMaquina();
                    break;
                case 3:
                    partida.jugarMaquinaVsMaquina();
                    break;
            }

        }while(eleccion != INICIO);
        System.out.println("***CERRANDO EL PROGRAMA***");
    }

    /**
     * PRUEBAS
     */
    public static void main( String[] args ) {
        Conecta4 juego = new Conecta4();
        juego.inicio();
    }
}
