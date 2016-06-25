package ar.fi.uba;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.fi.uba.estrategias.Jugador;

public class Torneo {
    
    List<List<Jugador>> jugadores;
    Map<String, Long> puntajesPorEstrategia;
    JugarRonda jugarRonda;
    Long media = 0L;
    Integer generaciones;
    Integer rondas;
    
    public Torneo(List<List<Jugador>> jugadores) {
        this.jugadores = jugadores;
        this.puntajesPorEstrategia = new HashMap<>();
        this.jugarRonda = new JugarRonda();
        this.generaciones = Constante.GENERACIONES;
        this.rondas = Constante.RONDAS;
        
    }
    
    public Torneo(List<List<Jugador>> jugadores, Integer generaciones, Integer rondas) {
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
    
    public void jugar() {
        
        Integer generacion = 1;
        
        inicializarMapDePuntajes();
        
        while (generacion <= this.generaciones ) {
            media = 0L;
            Integer ronda = 1;
            while (ronda <= this.rondas) {
                for (List<Jugador> jugadoresEstrategiaRonda : this.jugadores) {
                    for (Jugador jugador1 : jugadoresEstrategiaRonda) {
                        for (List<Jugador> jugadoresStrategia : this.jugadores) {
                            for (Jugador jugador2 : jugadoresStrategia) {
                                this.jugarRonda.calcular(jugador1, jugador2);
                            }
                        }
                    } 
                }
                ronda++;
                calcularPuntosPorEstrategia();
                agregarOQuitarIndividuosSegunMedia();
                mostrarCantidadIndividuo();
            }
            generacion++;
            //TODO calcular puntos de estrategias por generacion y guardar;
            //agregar y sacar individuos en estrategias.
        }
    }
    
    private Integer calcularPuntosPorEstrategia() {
        
        
        for (List<Jugador> jugadoresEstrategiaRonda : this.jugadores) {
            for (Jugador jugador1 : jugadoresEstrategiaRonda) {
                puntajesPorEstrategia.put(jugador1.estrategia(),puntajesPorEstrategia.get(jugador1.estrategia()) + jugador1.getPuntos());
                media += jugador1.getPuntos();
            }
            System.out.println("puntaje "+ jugadoresEstrategiaRonda.get(0).estrategia()+" "+ puntajesPorEstrategia.get(jugadoresEstrategiaRonda.get(0).estrategia()));
        }
        media =  media / 5;
        System.out.println("Media:"+ media);
        
        return null;
    }
    
    private void agregarOQuitarIndividuosSegunMedia() {
        
        for (String key : puntajesPorEstrategia.keySet()) {
            if (puntajesPorEstrategia.get(key) >= media) {
                for (List<Jugador> jugadoresEstrategiaRonda : this.jugadores) {
                    if(jugadoresEstrategiaRonda.get(0).estrategia().equalsIgnoreCase(key)) {
                        for (Integer i = 1; i <= Constante.NACIMIENTOS; i++) {
                            jugadoresEstrategiaRonda.add(determinarEstrategia(jugadoresEstrategiaRonda.get(0)));
                        }
                    }
                }   
            }else {
                for (List<Jugador> jugadoresEstrategiaRonda : this.jugadores) {
                    if(jugadoresEstrategiaRonda.get(0).estrategia().equalsIgnoreCase(key)) {
                        for (Integer i = 1; i <= Constante.DECESOS; i++) {
                            jugadoresEstrategiaRonda.remove(jugadoresEstrategiaRonda.get(0));
                        }
                    }
                }     
            }
        }
    }
    
    private Jugador determinarEstrategia(Jugador jugador) {
        Class clase = null;
        Jugador jug = null;
        try {
            clase = Class.forName(Constante.PACKAGE_ESTRATEGIAS + jugador.estrategia());
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
    
    private void mostrarCantidadIndividuo() {
        for (List<Jugador> jugadoresEstrategiaRonda : this.jugadores) {
            System.out.println("Cantidad:" + jugadoresEstrategiaRonda.size());
        }
    }
}
