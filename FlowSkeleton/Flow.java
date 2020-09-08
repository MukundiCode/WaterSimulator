package FlowSkeleton;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Flow {
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static FlowPanel fp;

	// start timer
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	public static void setupGUI(int frameX,int frameY,Terrain landdata,terrainWater water) {
		
		Dimension fsize = new Dimension(800, 800);
    	JFrame frame = new JFrame("Waterflow"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().setLayout(new BorderLayout());
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
   
		fp = new FlowPanel(landdata, water);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		g.add(fp);
	    
		// to do: add a MouseListener, buttons and ActionListeners on those buttons
      fp.addMouseListener(new CustomMouseListener(fp));//{ 
	   	
		JPanel b = new JPanel();
	    b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
		JButton endB = new JButton("End");;
		// add the listener to the jbutton to handle the "pressed" event
		endB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// to do ask threads to stop
				frame.dispose();
            System.exit(0);
			}
		});
      
      JButton resetB = new JButton("Reset");
      resetB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            fp.clear();
            fp.stop();
            fp.repaint();
            }
         });
      JButton pauseB = new JButton("Pause");
      pauseB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            fp.stop();
            }
         });
      JButton playB = new JButton("Play");
      playB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            fp.play();
            }
        });
		//adding the buttons to the item
      
      b.add(resetB);
      b.add(pauseB);
      b.add(playB);
		b.add(endB);
		g.add(b);
    	
		frame.setSize(frameX, frameY+50);	// a little extra space at the bottom for buttons
      	frame.setLocationRelativeTo(null);  // center window on screen
      	frame.add(g); //add contents to window
        frame.setContentPane(g);
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();
	}
	
		
	public static void main(String[] args) {
		Terrain landdata = new Terrain();
      terrainWater water = new terrainWater();
      
		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			System.out.println("Incorrect number of command line arguments. Should have form: java -jar flow.java intputfilename");
			System.exit(0);
		}
				
		// landscape information from file supplied as argument
		// 
		landdata.readData(args[0]);
      water.makeImage(landdata.getDimX(),landdata.getDimY());
      water.makeSurface(landdata);
		
		frameX = landdata.getDimX();
		frameY = landdata.getDimY();
		SwingUtilities.invokeLater(()->setupGUI(frameX, frameY, landdata, water));
		
		// to do: initialise and start simulation
	}
}
class CustomMouseListener implements MouseListener {
      public FlowPanel f;
      CustomMouseListener(FlowPanel f){
         this.f = f;
         }
      public void mouseClicked(MouseEvent e) {
         System.out.println("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
         f.water.addWater(e.getX(),e.getY());
         this.f.addWater();
         
      }
      public void mousePressed(MouseEvent e) {
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
   }
