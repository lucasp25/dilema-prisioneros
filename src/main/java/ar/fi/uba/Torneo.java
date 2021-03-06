package ar.fi.uba;

import java.util.ArrayList;
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
    Grafico grafico;
    
    public Torneo(List<JugadoresPorEstrategia> jugadores, Grafico grafico) {
        this.jugadores = jugadores;
        this.puntajesPorEstrategia = new HashMap<>();
        this.jugarRonda = new JugarRonda();
        this.generaciones = Constante.GENERACIONES;
        this.rondas = Constante.RONDAS;
        this.grafico = grafico;
    }

    public Torneo(List<JugadoresPorEstrategia> jugadores, Integer generaciones, Integer rondas, Grafico grafico)  {
        this.jugadores = jugadores;
        this.puntajesPorEstrategia = new HashMap<>();
        this.jugarRonda = new JugarRonda();
        this.generaciones = generaciones;
        this.rondas = rondas;
        this.grafico = grafico;

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
            grafico.agregarDatosPuntaje(estrategia, generacion, puntajesPorEstrategia.get(estrategia));
        }
        media = media / 5;
        System.out.println("Media:" + media);
    }

    private void agregarOQuitarIndividuosSegunMedia() throws Exception {

        List<JugadoresPorEstrategia> estrategiasGanadoras = new ArrayList<>();
        List<JugadoresPorEstrategia> estrategiasPerdedoras = new ArrayList<>();
        for (String key : puntajesPorEstrategia.keySet()) {
            if (puntajesPorEstrategia.get(key) >= media) {
                for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
                    if (jugadoresEstrategiaRonda.getEstrategia().equalsIgnoreCase(key)) {
                        estrategiasGanadoras.add(jugadoresEstrategiaRonda);
                        // for (Integer i = 1; i <= Constante.NACIMIENTOS; i++)
                        // {
                        // jugadoresEstrategiaRonda
                        // .addJugador(determinarEstrategia(jugadoresEstrategiaRonda.getEstrategia()));
                        // }
                    }
                }
            } else {
                for (JugadoresPorEstrategia jugadoresEstrategiaRonda : this.jugadores) {
                    if (jugadoresEstrategiaRonda.getEstrategia().equalsIgnoreCase(key)) {
                        estrategiasPerdedoras.add(jugadoresEstrategiaRonda);
//                         for (Integer i = 1; i <= Constante.DECESOS; i++) {
//                         jugadoresEstrategiaRonda.removeJugador();
//                         }
                    }
                }
            }
        }

        estrategiasGanadoras = sortEstrategiasSegunPuntaje(estrategiasGanadoras, puntajesPorEstrategia);
        Integer nacimientosFaltantes = Constante.NACIMIENTOS;
        for (int posicion = 1; posicion < estrategiasGanadoras.size(); posicion++) {
            Integer nacimientosOtorgados = 2*(nacimientosFaltantes/3);
            for (Integer i = 1; i <= nacimientosOtorgados; i++) {
                estrategiasGanadoras.get(posicion-1).addJugador(determinarEstrategia(estrategiasGanadoras.get(posicion-1).getEstrategia()));
                nacimientosFaltantes--;
            }
            if(posicion+1 == estrategiasGanadoras.size()) {
                for (Integer i = 1; i <= nacimientosFaltantes; i++) {
                    estrategiasGanadoras.get(posicion).addJugador(determinarEstrategia(estrategiasGanadoras.get(posicion).getEstrategia()));
                }
            }
        }
        
        estrategiasPerdedoras = sortEstrategiasSegunPuntaje(estrategiasPerdedoras, puntajesPorEstrategia);
        Integer decesosFaltantes = Constante.DECESOS;
        for (int posicion = estrategiasPerdedoras.size(); posicion > 1; posicion--) {
            Integer decesosOtorgados = 2*(decesosFaltantes/3);
            for (Integer i = 1; i <= decesosOtorgados; i++) {
                if(estrategiasPerdedoras.get(posicion-1) != null) estrategiasPerdedoras.get(posicion-1).removeJugador();
                decesosFaltantes--;
            }
            if(posicion+1 == estrategiasPerdedoras.size()) {
                for (Integer i = 1; i <= decesosFaltantes; i++) {
                    estrategiasPerdedoras.get(posicion-2).removeJugador();
                }
            }
        }
    }

    private List<JugadoresPorEstrategia> sortEstrategiasSegunPuntaje(List<JugadoresPorEstrategia> estrategias,
            Map<String, Long> puntajes) {
        List<JugadoresPorEstrategia> resultado = new ArrayList<>();
        List<JugadoresPorEstrategia> aux = new ArrayList<>(estrategias);
        for (int i = 0; i < estrategias.size(); i++) {
            JugadoresPorEstrategia max = null;
            Long puntajeMax = 0L;
            for (JugadoresPorEstrategia estrategia : aux) {
                Long puntaje = puntajes.get(estrategia.getEstrategia());
                if (puntaje > puntajeMax) {
                    puntajeMax = puntaje;
                    max = estrategia;
                }
            }
            resultado.add(max);
            aux.remove(max);
        }
        return resultado;
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
            System.out.println("Cantidad de individuos de estrategia " + estrategia + ": "
                    + jugadoresEstrategiaRonda.getJugadores().size());

            grafico.agregarDatosCantidad(estrategia, generacion, jugadoresEstrategiaRonda.getJugadores().size());
        }
    }

}
