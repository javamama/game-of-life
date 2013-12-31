/*
 *Koushik Krishnan
 *
 */
/*
 *This class serves as the Graphics class for the Game Of Life project. It carries out several actions:
 *		- draws a board for the game to be played on
 *		- draws ellipses on the board for each cell that is alive
 *		- implements mouse and action listeners for the buttons used in the applet
 */
import java.applet.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
public class MyGraphics extends JApplet{
	/* declare the GameOfLife Object to be used in the class. Methods such as clear() and
	 * next generation will be used on this
	 */
	private GameOfLife game;
	//The Container in which add the listeners, JPanels and buttons will be added to
	private Container cp;
	//The JPanel which will contain the buttons of the applet
	private JPanel p = new JPanel();
	//genCount signifies the number of generations that have gone by
	private int genCount=0;
	//Initialize all the buttons. The number of generations will be recorded
	//on the Button "button"
	private Button button  = new Button("Generation: " + genCount + "  Click for Next");
	private Button button2 = new Button("Reset");
	private Button button3 = new Button("Start");
	private Button button4 = new Button("Stop");
	//This class will recieve a GameOfLife object and set it equal
	//to the private GameOfLife object game.
	public MyGraphics(GameOfLife a){
    	game = a;
    }
	/*
	 * In these next few classes I set up my action and mouse listeners
	 */

	//The action listener that is associated with "button"
	//When the button is pressed, the next generation will be calculated and
	//the genCount will be incremented by 1.
	private class NextGenerationListener implements ActionListener{

			private JApplet f;
			public NextGenerationListener(JApplet t){
				//this constructor will later be used to set JApplet
				//to "this" JApplet
				f=t;
			}
			public void actionPerformed(ActionEvent event){
				game.nextGeneration();
				genCount++;
				button.setLabel("Generation: " + genCount + "  Click for Next");
				f.repaint();
			}
	}
	//The action listener associated with "Reset"
	//When pressed, the board is cleared and the genCount is
	//set back to 0.
	private class ClearBoardListener implements ActionListener{

			private JApplet w;
			public ClearBoardListener(JApplet s){
				w=s;
			}
			public void actionPerformed(ActionEvent event){
				game.clear();
				genCount = 0;
				button.setLabel("Generation: " + genCount + "  Click for Next");
				timer.stop();
				timer.removeActionListener(listener3);
				w.repaint();
			}
	}
	//The action listener associated with "Start"
	//When pressed, the button will start a timer which calculates
	//a new generation every second.
	private class StartButtonTimer implements ActionListener{
		private JApplet app;
		public StartButtonTimer(JApplet app2){
			app = app2;

		}
		public void actionPerformed(ActionEvent event){
			//adds the timer listener and starts it
			timer.addActionListener(listener3);
			timer.start();
			app.repaint();


		}
	}
	//The action listener associated with the Timer(declared on line 122)
	//This will calculate a new generation every second
	private class GenerationTimer implements ActionListener{
		private JApplet app;
		public GenerationTimer(JApplet app2){
			app = app2;

		}
		public void actionPerformed(ActionEvent event){
			game.nextGeneration();
			genCount++;
			button.repaint();
			button.setLabel("Generation: " + genCount + "  Click for Next");
			app.repaint();

		}
	}
	//The action listener associated with "Stop" button
	//When pressed the Timer will be stopped and its action
	//listener is removed
	private class StopButtonTimer implements ActionListener{
		private JApplet app;
		public StopButtonTimer(JApplet app2){
			app = app2;

		}
		public void actionPerformed(ActionEvent event){
			timer.stop();
			timer.removeActionListener(listener3);
			app.repaint();
		}
	}
	//The mouse listener. When the mouse is pressed, it will the respective
	//cell to alive. When the repaint method is called, an ellipse will be drawn there
	private class MyMouseListener implements MouseListener{
		private JApplet l;
		public MyMouseListener(JApplet e){
			l=e;
		}
		public void mousePressed(MouseEvent event){
			int x = event.getX();
			int y = event.getY();
			int i = x/50;
			int j = y/50;
			game.toggleACell(j-1,i-1);
			l.repaint();
		}
		//do nothing methods
		public void mouseReleased(MouseEvent event){}
		public void mouseClicked(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
	}
	/*
	 *Here I construct all of my action and mouse listeners as well as my timer.
	 *	listener5 = Stop button
	 *	listener4 = Start button
	 *	listener3 = timer
	 *	listener = next generation button
	 */
	private ActionListener listener5 = new StopButtonTimer(this);
	private ActionListener listener4 = new StartButtonTimer(this);
	private ActionListener listener3 = new GenerationTimer(this);
	private ActionListener listener2 = new ClearBoardListener(this);
	private ActionListener listener = new NextGenerationListener(this);
	private Timer timer = new Timer(500,listener3);
	private MouseListener mouse = new MyMouseListener(this);
	/*
	 *In the init method:
	 *	- action listeners are added to respective buttons
	 *	- the action listener for the timer is removed
	 *		(it will be added when the start button is clicked)
	 *	- the buttons are added to a JPanel
	 *	- the Container is set to a BorderLayout and the panel is added to the
	 *		bottom
	 *	- the mouse listener is added to the container
	 */
    public void init(){
		button.addActionListener(listener);
		button2.addActionListener(listener2);
		button3.addActionListener(listener4);
		button4.addActionListener(listener5);
		timer.removeActionListener(listener3);
		p.add(button3);
		p.add(button4);
		p.add(button2);
		p.add(button);
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(BorderLayout.SOUTH, p);
    	cp.addMouseListener(mouse);
	}
    public void paint(Graphics g){
		//type cast the Graphics object to a 2D object
    	Graphics2D g2 = (Graphics2D)g;
		//these ints signify the start and end X coordinates for
		//horizontal lines
		int startX;
		int endX;
		//these ints signify the start and end Y coordinates for
		//vertical lines
		int startY;
		int endY;
		//yValue signifies the row number in which to draw horizontal lines
		int yValue;
		//xValue signifies the column number in which to draw vertical lines
		int xValue;
		//makes the lines in which each square is 50px by 50px
		for(int i = 0; i <= game.getBoardLength()*50; i +=50){ // <-
			for(int j = 0; j <= game.getBoardWidth()*50; j +=50){
				//horizontal lines
				startX = 50;
				endX = startX + game.getBoardWidth()*50;
				yValue = 50 +(i*1);
				Line2D.Double line2 = new Line2D.Double(startX, yValue, endX, yValue);
				g2.draw(line2);
				//vertical lines
				startY = 50;
				endY = startY + game.getBoardLength()*50;
				xValue = 50 + (j*1);
				Line2D.Double line3 = new Line2D.Double(xValue, startY, xValue, endY);
				g2.draw(line3);
			}
		}
		//makes the ellipses
		for(int i = 0; i < game.getBoardLength(); i++){
			for(int j = 0; j < game.getBoardWidth(); j++){
				//draws an ellipse for each living cell
				if(game.getCellValue(i,j) == 1){
					Ellipse2D.Double fill = new Ellipse2D.Double(50+(50*j)+5,50+(i*50)+5,40,40);
					g2.setColor(Color.BLACK);
					g2.fill(fill);
					g2.draw(fill);
				}
				//draws a white rectangle for an empty cell on top of cell that has died
				else{
					Rectangle box = new Rectangle(50+(50*j)+3,50+(i*50)+3,43,43);
					g2.setColor(Color.WHITE);
					g2.fill(box);
					g2.draw(box);
				}
			}
		}
    }
}