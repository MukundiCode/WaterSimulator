package FlowSkeleton;
import java.util.concurrent.atomic.AtomicBoolean;
public class flowThread extends java.lang.Thread{
   public int start;
   public int stop;
   public FlowPanel fp;
   static AtomicBoolean sync = new AtomicBoolean();
   static int syncCount = 0;
   public terrainWater water;
   Terrain land;
   
   flowThread(int start,int stop,terrainWater water, Terrain land){ //FlowPanel fp){
      this.start = start;
      this.stop = stop;
      this.fp = fp;
      this.water = water;
      this.land = land;
      }
      
	public void run() {	
   /*
      boolean run = true;
      while(run == true){
         flowThread.sync.set(false);
         if(this.fp.play.get() == true){
         */
         for(int i = start; i<stop ; i++){
            int[] locations = new int[2];
            land.getPermute(land.permute.get(i), locations);
            this.water.flow(locations[0],locations[1]);
            }
            //fp.repaint();
            }
            /*
         else{
            try {
               Thread.sleep(1000);
            } catch (InterruptedException ex) {
                 // Stop immediately and go home
            }
         } */
       }
       /*
	}
}*/