package ar.fi.uba.graficos;

import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ar.fi.uba.Constante;

public class Grafico {
    
    private static Map<String, XYSeries> seriesSegunEstrategiaCantidad = new HashMap<>();
    private static Map<String, XYSeries> seriesSegunEstrategiaPuntaje = new HashMap<>();
    
    public Grafico() {}
    
    public static void limpiarSeries() {
        putSeriesSegunEstrategiaCantidad();
        seriesSegunEstrategiaCantidad = new HashMap<>();
        putSeriesSegunEstrategiaPuntaje();
        seriesSegunEstrategiaPuntaje = new HashMap<>();
    }
    
    public static void agregarDatosCantidad(String estrategia, Integer generacion, Integer cantIndividuos) {
        seriesSegunEstrategiaCantidad.get(estrategia).add(generacion, cantIndividuos);
    }
    
    public static void agregarDatosPuntaje(String estrategia, Integer generacion, Long puntaje) {
        seriesSegunEstrategiaPuntaje.get(estrategia).add(generacion, puntaje);
    }
    
    static {
        putSeriesSegunEstrategiaCantidad();
    }
    
    static {
        putSeriesSegunEstrategiaPuntaje();
    }
    
    
    public static void graficoCantidadIndividuos() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for(XYSeries serie : seriesSegunEstrategiaCantidad.values()) {
            dataset.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Individuos x Generaciones", // Título
                "Numero de Generacion", // Etiqueta Coordenada X
                "Cantidad de Individuos", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        // Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Ejemplo Grafica Lineal", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void graficoPuntajePorEstrategia() {
        
        XYSeriesCollection dataset = new XYSeriesCollection();

        for(XYSeries serie : seriesSegunEstrategiaPuntaje.values()) {
            dataset.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Individuos x Generaciones", // Título
                "Numero de Generacion", // Etiqueta Coordenada X
                "Puntaje por Estrategia", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true, 
                false,
                false
        );

        // Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Ejemplo Grafica Lineal", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    private static void putSeriesSegunEstrategiaCantidad() {
        seriesSegunEstrategiaCantidad.put(Constante.AL_AZAR,new XYSeries(Constante.AL_AZAR));
        seriesSegunEstrategiaCantidad.put(Constante.COOPERA,new XYSeries(Constante.COOPERA));
        seriesSegunEstrategiaCantidad.put(Constante.NO_OJO_POR_OJO,new XYSeries(Constante.NO_OJO_POR_OJO));
        seriesSegunEstrategiaCantidad.put(Constante.OJO_POR_OJO,new XYSeries(Constante.OJO_POR_OJO));
        seriesSegunEstrategiaCantidad.put(Constante.TRAICION,new XYSeries(Constante.TRAICION));
    }
    
    private static void putSeriesSegunEstrategiaPuntaje() {
        seriesSegunEstrategiaPuntaje.put(Constante.AL_AZAR,new XYSeries(Constante.AL_AZAR));
        seriesSegunEstrategiaPuntaje.put(Constante.COOPERA,new XYSeries(Constante.COOPERA));
        seriesSegunEstrategiaPuntaje.put(Constante.NO_OJO_POR_OJO,new XYSeries(Constante.NO_OJO_POR_OJO));
        seriesSegunEstrategiaPuntaje.put(Constante.OJO_POR_OJO,new XYSeries(Constante.OJO_POR_OJO));
        seriesSegunEstrategiaPuntaje.put(Constante.TRAICION,new XYSeries(Constante.TRAICION));
    }
   
}
