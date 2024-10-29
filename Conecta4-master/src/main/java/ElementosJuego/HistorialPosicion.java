package ElementosJuego;

public class HistorialPosicion {
    private int filaActual, filaAntigua;
    private int columnaActual, columnaAntigua;
    private static HistorialPosicion instancia;
    /**
     * Constructor
     */
    public HistorialPosicion(){
        this.filaActual = 0;
        this.filaAntigua = 0;
        this.columnaActual = 0;
        this.columnaAntigua = 0;
    }

    /**
     * Singleton Posición
     */
    public static HistorialPosicion getInstance(){
        if(instancia == null){
            instancia = new HistorialPosicion();
        }
        return instancia;
    }

    /**
     * GETTER Y SETTER
     */
    public int getFilaActual() {
        return filaActual;
    }

    public void setFilaActual(int filaActual) {
        this.filaActual = filaActual;
    }

    public int getFilaAntigua() {
        return filaAntigua;
    }

    public void setFilaAntigua(int filaAntigua) {
        this.filaAntigua = filaAntigua;
    }

    public int getColumnaActual() {
        return columnaActual;
    }

    public void setColumnaActual(int columnaActual) {
        this.columnaActual = columnaActual;
    }

    public int getColumnaAntigua() {
        return columnaAntigua;
    }

    public void setColumnaAntigua(int columnaAntigua) {
        this.columnaAntigua = columnaAntigua;
    }

    public void setPosicionAntigua(int fila, int columna){
        setFilaAntigua(fila);
        setColumnaAntigua(columna);
    }

    public void setPosicionActual(int fila, int columna){
        setFilaActual(fila);
        setColumnaActual(columna);
    }

}