package ar.fi.uba.estrategias;

import java.util.Random;

public class AlAzar extends Jugador {
    
    private Random randomGenerator = new Random();

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
