package ar.fi.uba;

import java.util.ArrayList;
import java.util.List;

import ar.fi.uba.estrategias.AlAzar;
import ar.fi.uba.estrategias.Coopera;
import ar.fi.uba.estrategias.JugadoresPorEstrategia;
import ar.fi.uba.estrategias.NoOjoPorOjo;
import ar.fi.uba.estrategias.OjoPorOjo;
import ar.fi.uba.estrategias.Traicion;

public class Configuracion {
    
    public List<JugadoresPorEstrategia> cargarEstrategias(Boolean traicion, Boolean coopera, Boolean ojoPorOjo, Boolean noOjoPor, Boolean alAzar) throws Exception {
        
        List<JugadoresPorEstrategia> jugadores = new ArrayList<>();
        if(traicion) jugadores.add(crearTraicioneros());
        if(coopera) jugadores.add(crearCooperadores());
        if(ojoPorOjo) jugadores.add(crearOjoPorOjo());
        if(noOjoPor) jugadores.add(crearNoOjoPorOjo());
        if(alAzar) jugadores.add(crearAlAzar());
        
        return jugadores;
    }
     
    
    
    private static JugadoresPorEstrategia crearTraicioneros() throws Exception {
        JugadoresPorEstrategia traicioneros = new JugadoresPorEstrategia(Constante.TRAICION);
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.addJugador(new Traicion());
        }
        return traicioneros; 
    }
    
    private static JugadoresPorEstrategia crearCooperadores() throws Exception {
        JugadoresPorEstrategia traicioneros = new JugadoresPorEstrategia(Constante.COOPERA);
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.addJugador(new Coopera());
        }
        return traicioneros; 
    }
    
    private static JugadoresPorEstrategia crearOjoPorOjo() throws Exception {
        JugadoresPorEstrategia traicioneros = new JugadoresPorEstrategia(Constante.OJO_POR_OJO);
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.addJugador(new OjoPorOjo());
        }
        return traicioneros; 
    } 
    
    private static JugadoresPorEstrategia crearNoOjoPorOjo() throws Exception {
        JugadoresPorEstrategia traicioneros = new JugadoresPorEstrategia(Constante.NO_OJO_POR_OJO);
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.addJugador(new NoOjoPorOjo());
        }
        return traicioneros; 
    } 
    
    private static JugadoresPorEstrategia crearAlAzar() throws Exception {
        JugadoresPorEstrategia traicioneros = new JugadoresPorEstrategia(Constante.AL_AZAR);
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.addJugador(new AlAzar());
        }
        return traicioneros; 
    } 

}
