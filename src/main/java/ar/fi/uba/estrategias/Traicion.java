package ar.fi.uba.estrategias;

public class Traicion extends Jugador {
    
    public Traicion() {
        this.jugada = false;
        this.ultimaJugada = false; 
    }

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
