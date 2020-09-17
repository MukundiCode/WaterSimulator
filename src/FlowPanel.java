
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
      land.genPermute();
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
      System.out.println("Water in was: "+terrainWater.WaterIn);
      System.out.println("Water out was: "+terrainWater.WaterOut);
      System.out.println("Water remaining is :"+water.getCurrentWater());
      System.out.println("Total Water after sim is : "+(terrainWater.WaterOut + water.getCurrentWater()));
      System.out.println("Total difference is : "+(terrainWater.WaterIn - (terrainWater.WaterOut + water.getCurrentWater())));
      
      }
      
   public void play(){
      this.play.set(true);
      }
      
   public void clear(){
      for (int x = 0;x<land.dimx-1;x++){
         for (int y = 3;y<land.dimy-2;y++){
            this.water.depth[x][y] = (float)0.00;
            this.water.img.setRGB(x,y,0);
            }
         }
  //    Flow.time = 0; 
    //  Flow.timer.setText(Integer.toString(Flow.time));
    } 
   
	public void run() {	
      boolean run = true;
      int numOfThreads = 4;
      flowThread [] threads = new flowThread[numOfThreads];
      while(run == true){
         if(this.play.get() == true){
            for(int i =0;i<numOfThreads;i++){
               threads[i] = new flowThread((i*(this.land.dimx*this.land.dimy))/numOfThreads,((i+1)*(this.land.dimx*this.land.dimy))/numOfThreads,this.water,this.land);
               threads[i].start();
               }
            for(int x=0;x<numOfThreads;x++){
               try{
               threads[x].join();
               repaint();
               Flow.timer.setText(Integer.toString(Flow.time++));
               }catch(InterruptedException e){
               
               }
               }
            }
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