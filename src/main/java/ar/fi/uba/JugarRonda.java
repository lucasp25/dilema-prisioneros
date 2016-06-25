package ar.fi.uba;

import ar.fi.uba.estrategias.Jugador;

public class JugarRonda {
    
    private static final Long AMBOS_TRICIONAN = 0L;
    private static final Long AMBOS_COOPERAN = 3L;
    private static final Long TRAICIONA_EL_OTRO_COOPERA = 5L;
    private static final Long COOPERA_EL_OTRO_TRAICIONA = 0L;
    
    
    public void calcular(Jugador jugador1, Jugador jugador2) {
        
        jugador1.resultado(jugador2.getUltimaJugada());
        jugador2.resultado(jugador1.getUltimaJugada());
       
        if (jugador1.jugar() && jugador2.jugar())  {
               jugador1.incrementarPuntuacion(AMBOS_COOPERAN);
               jugador2.incrementarPuntuacion(AMBOS_COOPERAN);
        } else if (!jugador1.jugar() && !jugador2.jugar()) {
            jugador1.incrementarPuntuacion(AMBOS_TRICIONAN);
            jugador2.incrementarPuntuacion(AMBOS_TRICIONAN);
        } else if (jugador1.jugar()) {
            jugador1.incrementarPuntuacion(COOPERA_EL_OTRO_TRAICIONA);
            jugador2.incrementarPuntuacion(TRAICIONA_EL_OTRO_COOPERA);
        } else {
            jugador1.incrementarPuntuacion(TRAICIONA_EL_OTRO_COOPERA);
            jugador2.incrementarPuntuacion(COOPERA_EL_OTRO_TRAICIONA);
        }
    }
    

}
