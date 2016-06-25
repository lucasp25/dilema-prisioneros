package ar.fi.uba;

import java.util.ArrayList;
import java.util.List;

import ar.fi.uba.estrategias.AlAzar;
import ar.fi.uba.estrategias.Coopera;
import ar.fi.uba.estrategias.Jugador;
import ar.fi.uba.estrategias.NoOjoPorOjo;
import ar.fi.uba.estrategias.OjoPorOjo;
import ar.fi.uba.estrategias.Traicion;

public class Configuracion {
    
    public List<List<Jugador>> cargarEstrategias() {
        
        List<List<Jugador>> jugadores = new ArrayList<>();
        jugadores.add(crearTraicioneros());
        jugadores.add(crearCooperadores());
        jugadores.add(crearOjoPorOjo());
        jugadores.add(crearNoOjoPorOjo());
        jugadores.add(crearAlAzar());
        
        return jugadores;
    }
     
    
    
    private static List<Jugador> crearTraicioneros() {
        List<Jugador> traicioneros = new ArrayList<>();
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.add(new Traicion());
        }
        return traicioneros; 
    }
    
    private static List<Jugador> crearCooperadores() {
        List<Jugador> traicioneros = new ArrayList<>();
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.add(new Coopera());
        }
        return traicioneros; 
    }
    
    private static List<Jugador> crearOjoPorOjo() {
        List<Jugador> traicioneros = new ArrayList<>();
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.add(new OjoPorOjo());
        }
        return traicioneros; 
    } 
    
    private static List<Jugador> crearNoOjoPorOjo() {
        List<Jugador> traicioneros = new ArrayList<>();
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.add(new NoOjoPorOjo());
        }
        return traicioneros; 
    } 
    
    private static List<Jugador> crearAlAzar() {
        List<Jugador> traicioneros = new ArrayList<>();
        
        for ( Integer i = 1; i <= Constante.INDIVIDUOS; i ++ ) {
            traicioneros.add(new AlAzar());
        }
        return traicioneros; 
    } 

}
