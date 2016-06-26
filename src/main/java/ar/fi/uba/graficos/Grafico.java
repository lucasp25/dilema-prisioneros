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
    
    private static Map<String, XYSeries> seriesSegunEstrategia = new HashMap<>();
    
    public static void agregarDatos(String estrategia, Integer generacion, Integer cantIndividuos) {
        seriesSegunEstrategia.get(estrategia).add(generacion, cantIndividuos);
    }
    
    static {
        seriesSegunEstrategia.put(Constante.AL_AZAR,new XYSeries(Constante.AL_AZAR));
        seriesSegunEstrategia.put(Constante.COOPERA,new XYSeries(Constante.COOPERA));
        seriesSegunEstrategia.put(Constante.NO_OJO_POR_OJO,new XYSeries(Constante.NO_OJO_POR_OJO));
        seriesSegunEstrategia.put(Constante.OJO_POR_OJO,new XYSeries(Constante.OJO_POR_OJO));
        seriesSegunEstrategia.put(Constante.TRAICION,new XYSeries(Constante.TRAICION));
    }
    
    public Grafico() {

        XYSeriesCollection dataset = new XYSeriesCollection();

        for(XYSeries serie : seriesSegunEstrategia.values()) {
            dataset.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Individuos x Generaciones", // Título
                "Numero de Generacion", // Etiqueta Coordenada X
                "Cantidad de Individuos", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true, // Muestra la leyenda de los productos (Producto A)
                false,
                false
        );

        // Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Ejemplo Grafica Lineal", chart);
        frame.pack();
        frame.setVisible(true);

    }
   
}
