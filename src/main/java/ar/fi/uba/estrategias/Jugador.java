package ar.fi.uba.estrategias;

public abstract class Jugador implements Estrategia {

    private Long puntos = 0L;

    protected Boolean jugada; // true si coopera, false si traiciona

    protected Boolean ultimaJugada;
    
    
    
    public void incrementarPuntuacion(Long puntos) {
        this.puntos += puntos;
    }
    public void decrementarPuntuacion(Long puntos) {
        this.puntos -= puntos;
    }
    
    @Override
    public Boolean jugar() {
        return this.jugada;
    }

    @Override
    public abstract void resultado(Boolean ultimaJugadaContrincante);
    
    public Boolean getJugada() {
        return jugada;
    }
    public Boolean getUltimaJugada() {
        return ultimaJugada;
    }
    
    public abstract String estrategia();
    
    public Long getPuntos() {
        return puntos;
    }
    
    public void reiniciarPuntos() {
        this.puntos = 0L;
    }

}
