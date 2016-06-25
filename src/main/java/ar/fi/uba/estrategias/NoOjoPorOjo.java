package ar.fi.uba.estrategias;

public class NoOjoPorOjo extends Jugador {
    
    @Override
    public void resultado(Boolean ultimaJugadaContrincante) {
        this.jugada = !ultimaJugadaContrincante;
        this.ultimaJugada = jugada;
    }
    
    @Override
    public String estrategia() {
        return "NoOjoPorOjo";
    }

}
