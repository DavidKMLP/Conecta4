package Comando;

//Invoker


public class GestorComandos {
    private static GestorComandos gestor;
    private Pila <IComando> pilaUndo;
    private Pila <IComando> pilaRedo;

    /**
     * Constructor
     */

    public GestorComandos() {
        pilaUndo = new Pila<>();
        pilaRedo = new Pila<>();
    }

    /**
     * Singleton del GestorComandos
     */

    public static GestorComandos getInstance(){
        if(gestor != null){
            return gestor;
        }else{
            return new GestorComandos();
        }

    }

    /**
     * Métodos
     */

    /**
     * Método que ejecuta un comando concreto pasado por parametro, el cuál se apila en la pila de movimientos a deshacer
     * @param comando Comando a ejecutar
     */
    public void execute(IComando comando){
        comando.execute();
        pilaUndo.apilar(comando);

    }

    /**
     * Método que desapila el comando de la cima de la pila de movimientos a deshacer y lo apila en la pila de movimientos a rehacer
     */
    public void undo(){
        if (!pilaUndo.isVacia()) {
            IComando comando = pilaUndo.desapilar();//Desapilo el primer elemento de Command History
            comando.undo();//Ejecutamos su undo
            pilaRedo.apilar(comando);//Lo apilamos en la pila del undo
        }
    }

    /**
     * Método que desapila el comando de la cima de la pila de movimientos a rehacer y vuelva a apilar en la pila de movimientos a deshacer
     */
    public void redo(){
        if (!pilaRedo.isVacia()) {
            IComando comando = pilaRedo.desapilar();
            comando.redo();
            pilaUndo.apilar(comando);
        }
    }

    /**
     * PRUEBAS
     */
    public static void main(String[] args) {
        /*
        GestorComandos gestorComandos = getInstance();
        Tablero tablero = new Tablero(6,7);
        JugadorFactory factory = new JugadorFactory();
        IJugador jugador = factory.crearJugador(TipoJugador.HUMANO);
        Ficha ficha = new Ficha(jugador.getLetraFicha(), jugador.getColorFicha()); //Ficha va a ser color y letra null porque no se puede desde fuera inicializar letra y color al ser un metodo protected
        Posicion posicion = new Posicion();

        System.out.println("Pongo una ficha");

        ComandoPoner cmd1 =  new ComandoPoner(ficha, tablero, posicion);
        gestorComandos.execute(cmd1);
        tablero.dibujar();
        tablero.limpiarTablero();


        System.out.println("Pongo otra ficha");
        ComandoPoner cmd2 =  new ComandoPoner(ficha, tablero, posicion);
        gestorComandos.execute(cmd2);
        tablero.dibujar();
        tablero.limpiarTablero();

        System.out.println("Despues del undo");
        gestorComandos.undo();
        //vaciar la posicion anterior
        tablero.dibujar();
        tablero.limpiarTablero();

        System.out.println("Despues del redo");
        gestorComandos.redo();
        //vaciar la posicion anterior
        tablero.dibujar();
        tablero.limpiarTablero();


         */
    }
}