package ar.fi.uba;

import javax.swing.SwingUtilities;

import ar.fi.uba.interfazgrafica.Menu;

public class Main {
    
    public static void main( String[] args ) {
      SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Menu ex = new Menu();
                ex.setVisible(true);
            }
        });       
    }
   
}
