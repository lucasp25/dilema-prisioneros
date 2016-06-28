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
    
    public Grafico() {
        putSeriesSegunEstrategiaCantidad();
        putSeriesSegunEstrategiaPuntaje();
    }
    
    public void limpiarSeries() {
        putSeriesSegunEstrategiaCantidad();
        seriesSegunEstrategiaCantidad = new HashMap<>();
        putSeriesSegunEstrategiaPuntaje();
        seriesSegunEstrategiaPuntaje = new HashMap<>();
    }
    
    public void agregarDatosCantidad(String estrategia, Integer generacion, Integer cantIndividuos) {
        seriesSegunEstrategiaCantidad.get(estrategia).add(generacion, cantIndividuos);
    }
    
    public void agregarDatosPuntaje(String estrategia, Integer generacion, Double puntaje) {
        seriesSegunEstrategiaPuntaje.get(estrategia).add(generacion, puntaje);
    }
    
    public  void graficoCantidadIndividuos() {
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
        ChartFrame frame = new ChartFrame("", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public void graficoPuntajePorEstrategia() {
        
        XYSeriesCollection dataset = new XYSeriesCollection();

        for(XYSeries serie : seriesSegunEstrategiaPuntaje.values()) {
            dataset.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Puntaje x Generaciones", // Título
                "Numero de Generacion", // Etiqueta Coordenada X
                "Puntaje Obtenido", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true, 
                false,
                false
        );

        // Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void putSeriesSegunEstrategiaCantidad() {
        seriesSegunEstrategiaCantidad.put(Constante.AL_AZAR,new XYSeries(Constante.AL_AZAR));
        seriesSegunEstrategiaCantidad.put(Constante.COOPERA,new XYSeries(Constante.COOPERA));
        seriesSegunEstrategiaCantidad.put(Constante.NO_OJO_POR_OJO,new XYSeries(Constante.NO_OJO_POR_OJO));
        seriesSegunEstrategiaCantidad.put(Constante.OJO_POR_OJO,new XYSeries(Constante.OJO_POR_OJO));
        seriesSegunEstrategiaCantidad.put(Constante.TRAICION,new XYSeries(Constante.TRAICION));
    }
    
    private void putSeriesSegunEstrategiaPuntaje() {
        seriesSegunEstrategiaPuntaje.put(Constante.AL_AZAR,new XYSeries(Constante.AL_AZAR));
        seriesSegunEstrategiaPuntaje.put(Constante.COOPERA,new XYSeries(Constante.COOPERA));
        seriesSegunEstrategiaPuntaje.put(Constante.NO_OJO_POR_OJO,new XYSeries(Constante.NO_OJO_POR_OJO));
        seriesSegunEstrategiaPuntaje.put(Constante.OJO_POR_OJO,new XYSeries(Constante.OJO_POR_OJO));
        seriesSegunEstrategiaPuntaje.put(Constante.TRAICION,new XYSeries(Constante.TRAICION));
    }
   
}
