package ar.fi.uba.interfazgrafica;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.fi.uba.Configuracion;
import ar.fi.uba.Constante;
import ar.fi.uba.Torneo;
import ar.fi.uba.graficos.Grafico;

public class Menu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Integer LIMITE_CARATERES = 2;
    private JTextField generacionText = new JTextField(4);
    private JTextField rondaText = new JTextField(4);

    public Menu() throws Exception {

        initUI();
    }

    private void initUI() throws Exception {

        createMenuBar();

        setTitle("El Dilema del Prisionero");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() throws Exception {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("descarga.png");

        JPanel panelSoloLabel = new JPanel();
        panelSoloLabel.setSize(200, 300);

        JPanel panel = new JPanel();
        panel.setSize(300, 300);

        JMenuItem eMenuItem = new JMenuItem("Salir", icon);
        JMenuItem eMenuItem2 = new JMenuItem("Nuevo", icon);

        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Salir de la aplicacion");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        eMenuItem2.setMnemonic(KeyEvent.VK_E);
        eMenuItem2.setToolTipText("Nuevo");

        JLabel individuosLabel = new JLabel("Individuos Por Estrategia: " + Constante.INDIVIDUOS);

        JLabel generacionlabel = new JLabel("Nro. Generaciones: ", Label.RIGHT);
        JLabel rondaLabel = new JLabel("Nro. Rondas: ", Label.RIGHT);

        generacionText.addKeyListener(limitarCantidadDeCaraceres(generacionText));
        rondaText.addKeyListener(limitarCantidadDeCaraceres(rondaText));

        JButton jugarBoton = new JButton("Jugar");
        jugarBoton.addActionListener(accionarBotonJugar());

        generacionlabel.addKeyListener(validarSoloNumeros());
        rondaText.addKeyListener(validarSoloNumeros());

        // panelSoloLabel.add(individuosLabel);
        panel.setLayout(new FlowLayout());
        panel.add(generacionlabel);
        panel.add(generacionText);
        panel.add(rondaLabel);
        panel.add(rondaText);
        panel.add(jugarBoton);

        // this.add(panelSoloLabel);
        this.add(panel);
        setJMenuBar(menubar);

    }

    private KeyAdapter validarSoloNumeros() {
        return new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        };
    }

    private KeyListener limitarCantidadDeCaraceres(JTextField textField) {
        return new KeyListener() {

            public void keyTyped(KeyEvent e)

            {
                if (textField.getText().length() == LIMITE_CARATERES)

                    e.consume();
            }

            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }
        };
    }

    private ActionListener accionarBotonJugar() throws Exception {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Configuracion conf = new Configuracion();
                Torneo torneo;
                Grafico grafico = new Grafico();
                try {
                    torneo = new Torneo(conf.cargarEstrategias(), Integer.valueOf(generacionText.getText()),
                            Integer.valueOf(rondaText.getText()),grafico);
                    torneo.jugar();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                grafico.graficoCantidadIndividuos();
                grafico.graficoPuntajePorEstrategia();
            }
        };
    }
}