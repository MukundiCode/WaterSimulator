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
	BufferedImage img; // greyscale image for displaying the terrain top-down
   public static double WATER_UNIT = 0.01;
   public terrainWater(){   
		img = new BufferedImage(dimy, dimx, BufferedImage.TYPE_INT_ARGB);
   }
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
   
   public void addWater(int x, int y){
      Color col = new Color(0, 0, 153, 1.0f);
	   img.setRGB(x, y, col.getRGB());
      }
   
}
      