package ar.fi.uba.estrategias;

import java.util.Random;

public class AlAzar extends Jugador {
    
    private Random randomGenerator = new Random();
    
    public AlAzar() {
        this.jugada = randomGenerator.nextBoolean();
        this.ultimaJugada = randomGenerator.nextBoolean();
    }

    @Override
    public void resultado(Boolean ultimaJugadaContrincante) {
        this.jugada = randomGenerator.nextBoolean();
        this.ultimaJugada = this.jugada;
    }
    
    @Override
    public String estrategia() {
        return "AlAzar";
    }

}
