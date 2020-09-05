package FlowSkeleton;
import java.io.File;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
public class terrainWater{
   
   float [][] depth; // regular grid of height values
   float [][] surfaceLevel;
	int dimx, dimy; // data dimensions
	public static BufferedImage img; // greyscale image for displaying the terrain top-down
   public static float WATER_UNIT = (float)0.01;
   // overall number of elements in the height grid
	int dim(){
		return dimx*dimy;
	}
	
	// get x-dimensions (number of columns)
	int getDimX(){
		return dimx;
	}
	
	// get y-dimensions (number of rows)
	int getDimY(){
		return dimy;
	}
   void makeImage(int x, int y){
      this.dimy = y;
      this.dimx = x;  
		img = new BufferedImage(dimy, dimx, BufferedImage.TYPE_INT_ARGB);
      this.depth = new float[dimx][dimy];
      }
   void makeSurface(Terrain t){
      for (int x=0;x<dimx;x++){
         for (int y=0;y<dimy;y++){
            this.surfaceLevel[x][y] = t.height[x][y] + this.depth[x][y];
            }
         }
   }
	
	// get greyscale image
	public BufferedImage getImage() {
		  return img;
	}
	
	// convert linear position into 2D location in grid
	void locate(int pos, int [] ind)
	{
		ind[0] = (int) pos / dimy; // x
		ind[1] = pos % dimy; // y	
	}
   //method to add water to the next square
   public boolean isMinima(int r,int c){
      float[] diff = new float[8];
      boolean minima = true;
      //creating a list of all neighboring items
      float[] list = {surfaceLevel[r-1][c-1], surfaceLevel[r-1][c], surfaceLevel[r-1][c+1], surfaceLevel[r][c+1], surfaceLevel[r+1][c+1], surfaceLevel[r+1][c], surfaceLevel[r+1][c-1], surfaceLevel[r][c-1]};
      //checking if this grid is a minima
      for (int i=0; i<8 ;i++){
         if (list[i] < surfaceLevel[r][c] + 0.01){
            minima = false;
            break;
            }
         else{
            float hh = (float)0.01;
            //diff[i] = list[i].height - g.height + hh;
            continue;
         }
         }
      return minima;
      }
   public void flow(int x, int y){
      if (isMinima(x,y) != true){
         int r = x;
         int c = y;
         float[] list = {surfaceLevel[r-1][c-1], surfaceLevel[r-1][c], surfaceLevel[r-1][c+1], surfaceLevel[r][c+1], surfaceLevel[r+1][c+1], surfaceLevel[r+1][c], surfaceLevel[r+1][c-1], surfaceLevel[r][c-1]};
         int [][] coordinates = {[r-1][c-1],[r-1][c],[r-1][c+1],[r][c+1],[r+1][c+1],[r+1][c],[r+1][c-1],[r][c-1]};
         //finding the smallest item
         for (int a = 0;a<8;a++){
            float hh = (float)0.01;
            float diff = list[a] - surfaceLevel[x][y] + hh;
            if (diff < lowest){
               lowest = a;
               }
            }
         
   public void addWater(int x, int y){
      for (int a = (x-3);a<(x+3);a++){
         for(int b = (y-3);b<(y+3);b++){
            Color col = new Color(0, 0, 153);
	         this.img.setRGB(a, b, col.getRGB());
            this.depth[a][b] = 3*terrainWater.WATER_UNIT;
         }
      }
   }
   
}
      