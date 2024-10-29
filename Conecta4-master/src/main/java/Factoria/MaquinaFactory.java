package Factoria;

public class MaquinaFactory {
    /**
     * Método que "fabrica" una nueva maquina
     * @return maquina creada
     */
    public Maquina getMaquina(){
        return new Maquina();
    }
}
