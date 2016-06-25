package ar.fi.uba.estrategias;

/***
 * 
 * 
 *
 */
public class Coopera extends Jugador {
     
    @Override
    public void resultado(Boolean resultadoContrincante) {
        this.jugada = true;
        this.ultimaJugada = true;
    }
    @Override
    public String estrategia() {
        return "Coopera";
    }

}
