
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;


public class Output extends DefaultMetalTheme{
	final static String THEME = "Test";
	final static String LOOKANDFEEL = "Metal";

    public static void makeFrame(GameOfLife d){
    	JFrame f = new JFrame("Game Of Life");
		f.setSize(new Dimension((d.getBoardWidth()*50)+150,(d.getBoardLength()*50)+150));
   		JApplet a = new MyGraphics(d);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
	    JLabel genLbl = new JLabel("Generation Number");
	    genLbl.setSize(10,50);
	    p.add(genLbl);
	    f.add(p);

		f.getContentPane().add("Center",a);
		a.init();
		f.repaint();
		try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //SwingUtilities.updateComponentTreeUI(this);
    	}
    	catch (UnsupportedLookAndFeelException e) {
    	   // handle exception
    	}
    	catch (ClassNotFoundException e) {
       // handle exception
    	}
    	catch (InstantiationException e) {
       // handle exception
    	}
    	catch (IllegalAccessException e) {
       // handle exception
    	}
		JFrame.setDefaultLookAndFeelDecorated(true);


		f.setResizable(false);
    	f.setVisible(true);
    	
    }


}