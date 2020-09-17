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
   public static float WaterIn = (float)0.00;
   public static float WaterOut = (float)0.00;
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
   float getCurrentWater(){
      float w = (float)0.00;
      for(int i=0;i<dimx-1;i++){
         for(int j=0;j<dimy-1;j++){
            w = w+depth[i][j];
            }
         }
      return w;
      }
   void makeImage(int x, int y){
      this.dimy = y;
      this.dimx = x;  
		img = new BufferedImage(dimy, dimx, BufferedImage.TYPE_INT_ARGB);
      this.depth = new float[dimx][dimy];
      }
   void makeSurface(Terrain t){
      this.surfaceLevel = new float[dimx][dimy];
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
         if (list[i] <= surfaceLevel[r][c] + 0.01){
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
      if(x==0 || x==dimx-1){
         terrainWater.WaterOut = terrainWater.WaterOut + this.depth[x][y];
         this.depth[x][y] = (float)0.00;
         }
      if(y==0 || y==dimy-1){
         terrainWater.WaterOut = terrainWater.WaterOut + this.depth[x][y];
         this.depth[x][y] = (float)0.00;
         }
      if (x!=0 && y!=0 && x!=this.dimx-1 && y!= this.dimy-1 && isMinima(x,y) != true && this.depth[x][y] != 0){
         int r = x;
         int c = y;
         int lowest =0;
         //finding the smallest item
         //creating surfacelevel objects
         surfaceLevel n1 = new surfaceLevel(r-1,c-1,this.surfaceLevel[r-1][c-1]);
         surfaceLevel n2 = new surfaceLevel(r-1,c,this.surfaceLevel[r-1][c]);
         surfaceLevel n3 = new surfaceLevel(r-1,c+1,this.surfaceLevel[r-1][c+1]);
         surfaceLevel n4 = new surfaceLevel(r,c+1,this.surfaceLevel[r][c+1]);
         surfaceLevel n5 = new surfaceLevel(r+1,c+1,this.surfaceLevel[r+1][c+1]);
         surfaceLevel n6 = new surfaceLevel(r+1,c,this.surfaceLevel[r+1][c]);
         surfaceLevel n7 = new surfaceLevel(r+1,c-1,this.surfaceLevel[r+1][c-1]);
         surfaceLevel n8 = new surfaceLevel(r,c-1,this.surfaceLevel[r][c-1]);
         
         surfaceLevel[] levels = {n1,n2,n3,n4,n5,n6,n7,n8};
         for (int a = 1;a<8;a++){
            float hh = (float)0.01;
            float diff = levels[a].level - surfaceLevel[x][y] + hh;
            if (diff < levels[lowest].level - surfaceLevel[x][y]){
               lowest = a;
               }
            }
         waterFlowTo(x,y,levels[lowest].x,levels[lowest].y);
         }
   }
   //method takes a unit of water and makes it flow to the next cell
   public synchronized void waterFlowTo(int x1,int y1,int x2, int y2){
      if(this.depth[x1][y1] > 0){
      this.depth[x1][y1] = this.depth[x1][y1] - terrainWater.WATER_UNIT;
      this.surfaceLevel[x1][y1] = this.surfaceLevel[x1][y1] - terrainWater.WATER_UNIT;
      this.depth[x2][y2] = this.depth[x2][y2] + terrainWater.WATER_UNIT;
      this.surfaceLevel[x2][y2] = this.surfaceLevel[x2][y2] + terrainWater.WATER_UNIT;
      Color col = new Color(0, 0, 153);
	   this.img.setRGB(x2, y2, col.getRGB());
      if(this.depth[x1][y1] == 0.00){
         this.img.setRGB(x1,y1,0);
         }
      }
      }
     
   public void addWater(int x, int y){
      for (int a = (x-3);a<(x+3);a++){
         for(int b = (y-3);b<(y+3);b++){
            Color col = new Color(0, 0, 153);
	         this.img.setRGB(a, b, col.getRGB());
            this.depth[a][b] = this.depth[a][b]+3*terrainWater.WATER_UNIT;
            terrainWater.WaterIn = terrainWater.WaterIn+3*terrainWater.WATER_UNIT;
         }
      }
   }
   
}
      