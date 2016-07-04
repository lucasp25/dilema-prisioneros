package ar.fi.uba.estrategias;

public class OjoPorOjo extends Jugador {
    
    public OjoPorOjo() {
        this.jugada = false;
        this.ultimaJugada = false;
    }
    
    @Override
    public void resultado(Boolean ultimaJugadaContrincante) {
        this.jugada = ultimaJugadaContrincante;
        this.ultimaJugada = jugada;
    }

    @Override
    public String estrategia() {
        return "OjoPorOjo";
    }

}
