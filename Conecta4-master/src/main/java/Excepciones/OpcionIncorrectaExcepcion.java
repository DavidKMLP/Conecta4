package Excepciones;

//Controla la excepcion para el menu a la hora de elegir el modo de juego

public class OpcionIncorrectaExcepcion extends Exception{
    public OpcionIncorrectaExcepcion(String msg) {
        super(msg);
    }
}
