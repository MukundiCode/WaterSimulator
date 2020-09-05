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
	int dimx, dimy; // data dimensions
	public static BufferedImage img; // greyscale image for displaying the terrain top-down
   public static double WATER_UNIT = 0.01;
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
      /*
      for(int a=0; a < dimx/2; a++)
			for(int b=0; b < (dimy/2)-1; b++) {
				 // find normalized height value in range
				 Color col = new Color(0, 0, 153);
				 terrainWater.img.setRGB(a, b, col.getRGB());
             }
         */
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
   
   public static void addWater(int x, int y){
      for (int a = (x-3);a<(x+3);a++){
         for(int b = (y-3);b<(y+3);b++){
            Color col = new Color(0, 0, 153);
	         terrainWater.img.setRGB(a, b, col.getRGB());
         }
      }
   }
   
}
      