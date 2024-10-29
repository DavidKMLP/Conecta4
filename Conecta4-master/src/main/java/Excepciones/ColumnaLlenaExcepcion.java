package Excepciones;

//Controla la excepcion para cuando una columna del tablero esta llena

public class ColumnaLlenaExcepcion extends Exception {
    public ColumnaLlenaExcepcion(String msg) {
        super(msg);
    }
}
