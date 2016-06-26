package ar.fi.uba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.fi.uba.estrategias.Jugador;
import ar.fi.uba.estrategias.JugadoresPorEstrategia;
import ar.fi.uba.graficos.Grafico;

public class Torneo {

    List<JugadoresPorEstrategia> jugadores;
    Map<String, Long> puntajesPorEstrategia;
    JugarRonda jugarRonda;
    Long media = 0L;
    Integer generaciones;
    Integer rondas;
    
    public Torneo(List<JugadoresPorEstrategia> jugadores) {
        this.jugadores = jugadores;
        this.puntajesPorEstrategia = new HashMap<>();
        this.jugarRonda = new JugarRonda();
        this.generaciones = Constante.GENERACIONES;
        this.rondas = Constante.RONDAS;
    }

    public Torneo(List<JugadoresPorEstrategia> jugadores, Integer generaciones, Integer rondas) {
        this.jugadores = jugadores;
        this.puntajesPorEstrategia = new HashMap<>();
        this.jugarRonda = new JugarRonda();
        this.generaciones = generaciones;
        this.rondas = rondas;

    }

    private void inicializarMapDePuntajes() {
        puntajesPorEstrategia.put(Constante.COOPERA, new Long(0));
        puntajesPorEstrategia.put(Constante.TRAICION, new Long(0));
        puntajesPorEstrategia.put(Constante.OJO_POR_OJO, new Long(0));
        puntajesPorEstrategia.put(Constante.NO_OJO_POR_OJO, new Long(0));
        puntajesPorEstrategia.put(Constante.AL_AZAR, new Long(0));
    }

    public void jugar() throws Exception {

        Integer generacion = 0;
        inicializarMapDePuntajes();
        calcularPuntosPorEstrategia(generacion);
        mostrarCantidadIndividuo(generacion);
        generacion++;
        while (generacion <= this.generaciones) {
            Integer ronda = 1;
            while (ronda <= this.rondas) {
                for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
                    for (Jugador jugador1 : jugadoresEstrategiaRonda.getJugadores()) {
                        for (JugadoresPorEstrategia jugadoresEstrategia : this.jugadores) {
                            for (Jugador jugador2 : jugadoresEstrategia.getJugadores()) {
                                this.jugarRonda.calcular(jugador1, jugador2);
                            }
                        }
                    }
                }
                ronda++;
            }
            inicializarMapDePuntajes();
            calcularPuntosPorEstrategia(generacion);
            agregarOQuitarIndividuosSegunMedia();
            mostrarCantidadIndividuo(generacion);
            generacion++;
        }
    }

    private void calcularPuntosPorEstrategia(Integer generacion) {

        media = 0L;
        for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
            String estrategia = jugadoresEstrategiaRonda.getEstrategia();
            for (Jugador jugador1 : jugadoresEstrategiaRonda.getJugadores()) {
                puntajesPorEstrategia.put(jugador1.estrategia(),
                        puntajesPorEstrategia.get(jugador1.estrategia()) + jugador1.getPuntos());
                media += jugador1.getPuntos();
            }
            System.out.println("puntaje " + estrategia + " " + puntajesPorEstrategia.get(estrategia));
            Grafico.agregarDatosPuntaje(estrategia, generacion, puntajesPorEstrategia.get(estrategia));
        }
        media = media / 5;
        System.out.println("Media:" + media);
    }

    private void agregarOQuitarIndividuosSegunMedia() throws Exception {

        for (String key : puntajesPorEstrategia.keySet()) {
            if (puntajesPorEstrategia.get(key) >= media) {
                for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
                    if (jugadoresEstrategiaRonda.getEstrategia().equalsIgnoreCase(key)) {
                        for (Integer i = 1; i <= Constante.NACIMIENTOS; i++) {
                            jugadoresEstrategiaRonda
                                    .addJugador(determinarEstrategia(jugadoresEstrategiaRonda.getEstrategia()));
                        }
                    }
                }
            } else {
                for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
                    if (jugadoresEstrategiaRonda.getEstrategia().equalsIgnoreCase(key)) {
                        for (Integer i = 1; i <= Constante.DECESOS; i++) {
                            jugadoresEstrategiaRonda.removeJugador();
                        }
                    }
                }
            }
        }
    }

    private Jugador determinarEstrategia(String estrategia) {
        Class<?> clase = null;
        Jugador jug = null;
        try {
            clase = Class.forName(Constante.PACKAGE_ESTRATEGIAS + estrategia);
            jug = (Jugador) clase.newInstance();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jug;
    }

    private void mostrarCantidadIndividuo(Integer generacion) {
        for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
            String estrategia = jugadoresEstrategiaRonda.getEstrategia();
            System.out.println(
                    "Cantidad de individuos de estrategia " + estrategia + ": " + jugadoresEstrategiaRonda.getJugadores().size());

            Grafico.agregarDatosCantidad(estrategia, generacion, jugadoresEstrategiaRonda.getJugadores().size());
        }
    }
    
}
