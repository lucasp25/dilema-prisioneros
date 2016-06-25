package ar.fi.uba.estrategias;

public class Traicion extends Jugador {

    @Override
    public void resultado(Boolean resultadoContrincante) {
        this.jugada = false;
        this.ultimaJugada = false;
    }
    
    @Override
    public String estrategia() {
        return "Traicion";
    }

}
