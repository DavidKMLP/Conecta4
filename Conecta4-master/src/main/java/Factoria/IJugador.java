package Factoria;

import ElementosJuego.Tablero;

import java.awt.*;

public interface IJugador {
     void ponerFicha();
     String getNombre();
     char getLetraFicha();
     void setLetraFicha(int indice);
     void setTablero(Tablero tablero);
     void setColorFicha(Color colorFicha);


}
