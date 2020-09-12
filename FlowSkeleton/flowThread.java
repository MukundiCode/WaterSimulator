package FlowSkeleton;
public class flowThread extends java.lang.Thread{
   public int start;
   public int stop;
   public FlowPanel fp;
   
   flowThread(int start,int stop,FlowPanel fp){
      this.start = start;
      this.stop = stop;
      this.fp = fp;
      }
      
	public void run() {	
      //fp.land.genPermute();
      boolean run = true;
      while(run == true){
         if(this.fp.play.get() == true){
         for(int i = start; i<stop ; i++){
            int[] locations = new int[2];
            fp.land.getPermute(fp.land.permute.get(i), locations);
            this.fp.water.flow(locations[0],locations[1]);
            }
            fp.repaint();
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