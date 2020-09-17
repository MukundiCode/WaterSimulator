package FlowSkeleton;
import java.util.concurrent.atomic.AtomicBoolean;
public class flowThread extends java.lang.Thread{
   public int start;
   public int stop;
   public terrainWater water;
   public Terrain land;
   
   flowThread(int start,int stop,terrainWater water, Terrain land){ 
      this.start = start;
      this.stop = stop;
      this.water = water;
      this.land = land;
      }
      
	public void run() {	
      for(int i = start; i<stop ; i++){
         int[] locations = new int[2];
         land.getPermute(land.permute.get(i), locations);
         this.water.flow(locations[0],locations[1]);
            }
       }
}
