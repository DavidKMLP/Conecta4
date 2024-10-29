package Factoria;

import ElementosJuego.Ficha;

import java.awt.*;

public class Maquina extends PlantillaJugador implements IJugador {

    private final int NUMERO_NOMBRES_IA = NombresMaquina.values().length;

    /**
     * CONSTRUCTOR
     */
    public Maquina() {
        this.nombre =escogerNombre();
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

    public Color getColorFicha() {
        return colorFicha;
    }

    /**
     * SET
     */
    @Override
    public void setLetraFicha(int indice) {
        if (indice == 0) {
            this.letraFicha = AMARILLA;
        } else if (indice == 1) {
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
     * Método que establece el tiempo de espera para que la Maquina ponga una ficha
     */
    private void delayIA(){
        int ms=1500;
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Método que pone una ficha en el tablero
     */
    @Override
    public void ponerFicha() {
        delayIA();
        Ficha ficha = new Ficha(this.letraFicha,this.colorFicha);
        getTablero().ponerFicha(ficha,getTablero().escogerColumnaValidaAleatoria());
    }

    /**
     * Método que genera un número aleatorio entre 0 y 4 ambos incluidos
     */
    private int numeroAleatorio(){
        return (int) (Math.random()*NUMERO_NOMBRES_IA);
    }

    /**
     * Método que según el numero aleatorio generado asigna a la IA un nombre
     */
    private String asignarNombreMaquina(){
        String nombre ="";
        switch (numeroAleatorio()){
            case 0:
                nombre = NombresMaquina.AlfaIA.name();
                break;
            case 1:
                nombre = NombresMaquina.BravoIA.name();
                break;
            case 2:
                nombre = NombresMaquina.CharlieIA.name();
                break;
            case 3:
                nombre = NombresMaquina.DeltaIA.name();
                break;
            case 4:
                nombre = NombresMaquina.TangoIA.name();
                break;
            default:
                nombre = "defaultIA";
                break;
        }
        return nombre;
    }

    /**
     * Método que invoca al método asignarNombresIA()
     */
    @Override
    public String escogerNombre() {
        return asignarNombreMaquina();
    }
}
