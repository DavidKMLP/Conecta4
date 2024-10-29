package ModosDeJuego;



/**
 * Clase que implementa el patron Fachada
 */

public final class InicializarPartida {
    private static InicializarPartida instancia;
    private HumanoVsHumano humanoVsHumano;
    private HumanoVsMaquina humanoVsMaquina;
    private MaquinaVsMaquina maquinaVsMaquina;


    /**
     * CONSTRUCTOR
     */
    public InicializarPartida() {
        this.humanoVsHumano = new HumanoVsHumano();
        this.humanoVsMaquina = new HumanoVsMaquina();
        this.maquinaVsMaquina = new MaquinaVsMaquina();
    }

    /**
     * Implentacion del Singleton
     */
    public static InicializarPartida getInstance(){
        if(instancia == null){
            instancia = new InicializarPartida();
        }
        return instancia;   
    }

    /**
     * Métodos que permiten jugar a los distintos modos de juego
     */
    public void jugarHumanoVsHumano(){
        humanoVsHumano.jugar();
    }
    public void jugarHumanoVsMaquina(){
        humanoVsMaquina.jugar();
    }
    public void jugarMaquinaVsMaquina(){
        maquinaVsMaquina.jugar();
    }


}
