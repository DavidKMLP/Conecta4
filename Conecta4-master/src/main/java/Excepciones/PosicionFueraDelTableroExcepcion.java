package Excepciones;

//Controla la excepcion para cuando se introduce una fila que no esta en el tablero

public class PosicionFueraDelTableroExcepcion extends Exception {
    public PosicionFueraDelTableroExcepcion(String msg) {
        super(msg);
    }
}
