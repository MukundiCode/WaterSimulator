package FlowSkeleton;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.Graphics;
import javax.swing.JPanel;

public class FlowPanel extends JPanel implements Runnable {
	Terrain land;
   terrainWater water;
   AtomicBoolean play;
	
	FlowPanel(Terrain terrain, terrainWater water) {
		land=terrain;
      this.water = water;
      this.play = new AtomicBoolean(false);
	}
		
	// responsible for painting the terrain and water
	// as images
	@Override
    protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		  
		super.paintComponent(g);
		// draw the landscape in greyscale as an image
		if (land.getImage() != null){
			g.drawImage(land.getImage(), 0, 0, null);
         g.drawImage(water.getImage(),0,0,null);
		} 
	}
	public void addWater(){
      repaint();
      }
      
   public void stop(){
      this.play.set(false); 
      }
      
   public void play(){
      this.play.set(true);
      }
      
   public void clear(){
      for (int x = 3;x<land.dimx-2;x++){
         for (int y = 3;y<land.dimy-2;y++){
            this.water.depth[x][y] = (float)0.00;
            this.water.img.setRGB(x,y,0);
            }
         } 
    } 
   
	public void run() {	
		// display loop here
		// to do: this should be controlled by the GUI
		// to allow stopping and starting
      //generating the permuted list
      land.genPermute();
      boolean run = true;
      while(run == true){
         if(this.play.get() == true){
         for(int i = 0; i<land.dimx*land.dimy ; i++){
            int[] locations = new int[2];
            land.getPermute(land.permute.get(i), locations);
            this.water.flow(locations[0],locations[1]);
            }
            repaint();
            }
         /*
            for (int x = 1;x<land.dimx-1;x++){
               for (int y = 1;y<land.dimy-1;y++){
                  this.water.flow(x,y);
                  }
               } 
	          repaint();
           }
           */
         else{
            try {
               Thread.sleep(1000);
            } catch (InterruptedException ex) {
                 // Stop immediately and go home
            }
         }
       }
	}
}