package ar.fi.uba.estrategias;

import java.util.ArrayList;
import java.util.List;

public class JugadoresPorEstrategia {

    private List<Jugador> jugadores;
    private String estrategia;

    public JugadoresPorEstrategia(String estrategia) {
        this.estrategia = estrategia;
        this.jugadores = new ArrayList<>();
    }

    public void addJugador(Jugador jugador) throws Exception {
        if (jugador.estrategia().equalsIgnoreCase(estrategia)) {
            jugadores.add(jugador);
        } else {
            throw new Exception("Intentando agregar individuo a grupo de estrategias erroneo");
        }
    }

    public void removeJugador() {
        if (jugadores.size() > 0) {
            jugadores.remove(0);
        }
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public String getEstrategia() {
        return this.estrategia;
    }

}
