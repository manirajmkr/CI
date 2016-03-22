import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImgDiffPercent
{
	static long currentMsec;
	static long end,MaxTolerant;
	static List<String> Initiallist = new ArrayList<String>();
	static List<String> AllImgList = new ArrayList<String>();
	static List<String> Finallist = new ArrayList<String>();
	static List<String> FinalListWithOutBlank = new ArrayList<String>();
	static List<String> Templist = new ArrayList<String>();
	static int scrCount=1;
	static int part=1;
	static int	maxlimt=0;
	 public static void main(String str[]) throws IOException, InterruptedException
	 {
		WebDriver driver=new FirefoxDriver();
	//Load the url	
		driver.get("https://cas.ny.us.criteo.com/delivery/rtb/appnexus/display.aspx?creative=6128408");	
		getScreenshot(driver, 0, 0);
	//Get Master image
		long startTime = System.nanoTime();
	//Adding the master screentshot directly into the FinallList
		Finallist.add("C:\\Users\\Administrator\\Desktop\\Pagesource\\Part0Screenshot1.png");
		FinalListWithOutBlank.add("C:\\Users\\Administrator\\Desktop\\Pagesource\\Part0Screenshot1.png");
		currentMsec= System.currentTimeMillis();
		end = currentMsec+5000;
		MaxTolerant=currentMsec+300000;
	//Getting the next image imediate after the Master image
		getScreenshot(driver, 1, 10);
	//Looping for 10sec for change in ads
		while(System.currentTimeMillis() < end && System.currentTimeMillis() < MaxTolerant) 
		{
			if(scrCount>1)
			{
			getPicDiff(driver);	
		//	System.out.println("Main call");
			
			}		
		}
   //Looping for deleting the unwanted image files
		for(int StrartPart=1;StrartPart<part;StrartPart++)
		{
			int totalPart=0;
			double PictureIndex=0.0;
	    	for(int Start=0;Start<(Initiallist.size()-1);Start++)
	    	{
	    		String srt;
	    		String str1;
	    		if(part>9)
	    		{
	    			srt=Initiallist.get(Start);
	    			str1="Part"+StrartPart+"Screen";
	    		}
	    		else
	    		{
	    			 srt=Initiallist.get(Start);
		    		 str1="Part"+StrartPart;
	    		}
	    		
	    		if(srt.contains(str1))
	    		{
	    		//	System.out.println(Initiallist.get(Start));	
	    			Templist.add(Initiallist.get(Start));
	    			totalPart++;
	    		}
	    	}
	    									//	System.out.println("So the total is ======> "+totalPart);									
	    	PictureIndex=totalPart/1.3;
	    	if(Math.round(PictureIndex)>0 && Templist.size()>1)
	    	{
	    		if(Math.round(PictureIndex) > (Templist.size()-1))
	    		{
	    			PictureIndex=totalPart/2;
	    		}
	    	Finallist.add(Templist.get((int) Math.round(PictureIndex)));
	    	Templist = new ArrayList<String>();
	    	}
	    	

	    //	System.out.println(Templist.size());
	    	if(Templist.size()==1)
	    	{
	    		Finallist.add(Templist.get(0));
	    		Templist = new ArrayList<String>();
	    	}
	
		}
		
		
	//Removing the unwanted pictures from the local storage
		
		AllImgList.removeAll(Finallist);		
   for (String path : AllImgList) 
   {
		   try
		   {
		//   System.out.println(path);
			File f = new File(path);
			f.delete();
		   }
		   catch(Exception e)
		   {
			   System.out.println("Exception"+e);
		   }
	}
	 
	 
	 //RemovING blank images from the finallist

 for (String pic : Finallist) 
 {
		 BufferedImage image = ImageIO.read(new File(pic));
		 
		 		  final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			      final int width = image.getWidth();
			      final int height = image.getHeight();
			      final boolean hasAlphaChannel = image.getAlphaRaster() != null;
			      boolean blankImage=true;
			      int[][] result = new int[height][width];
			      if (hasAlphaChannel) {
			         final int pixelLength = 4;
			         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			            int argb = 0;
			            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
			            argb += ((int) pixels[pixel + 1] & 0xff); // blue
			            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
			            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
			            result[row][col] = argb;
			            if(result[row][col]!=result[0][0])
			            {
			            	blankImage=false;
			            }
			            col++;
			           
			            if (col == width) {
			               col = 0;
			               row++;
			            }
			            }
			      } else {
			         final int pixelLength = 3;
			         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			            int argb = 0;
			            argb += -16777216; // 255 alpha
			            argb += ((int) pixels[pixel] & 0xff); // blue
			            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
			            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
			            result[row][col] = argb;
			            if(result[row][col]!=result[0][0])
			            {
			            	blankImage=false;
			            }
			            col++;
			            
			            if (col == width) {
			               col = 0;
			               row++;
			            }	         
			         }
			      }
			      
			      if(blankImage==true)
			      {
			    	  try
					   {
			    
					   System.out.println("Blank image found and its deleted");
						File f = new File(pic);
						f.delete();
					   }
					   catch(Exception e)
					   {
						   System.out.println("Exception"+e);
					   }
			      }else
			      {
			    	  FinalListWithOutBlank.add(pic);
			      }
			   }
 
	//FinalListWithOutBlank list will have the final screenshot list
 		 
 System.out.println("FinalListWithOutBlank size"+FinalListWithOutBlank.size());
		 if(FinalListWithOutBlank.size()==2)
		 {
			 System.out.println("Its an static display creative ");
		//	 File f = new File(FinalListWithOutBlank.get(1));
		//	 f.delete();
			 FinalListWithOutBlank.remove(1);
		 }
		 else{
	if(FinalListWithOutBlank.size()==0)
	 {
		 System.out.println("Its an Blank creative ");
	 }else{
		 System.out.println("Its an Animated or dynamic display creative ");}  }
	 long endTime = System.nanoTime();
	 System.out.println("total execution time==>"+(double)(endTime - startTime) / 1000000000.0+"seconds");
	 
	 System.out.println("FinalListWithOutBlank "+FinalListWithOutBlank);
	 
	 }
	 
	
//Getting screenshot 
	 
	  public static void getScreenshot(WebDriver driver, int partCount, int sleep) throws InterruptedException
	  {
		  Thread.sleep(sleep);
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\Part"+partCount+"Screenshot"+scrCount+".png"));
				} catch (IOException e) {
				e.printStackTrace();
			}
			Initiallist.add("C:\\Users\\Administrator\\Desktop\\Pagesource\\Part"+partCount+"Screenshot"+scrCount+".png");
			AllImgList.add("C:\\Users\\Administrator\\Desktop\\Pagesource\\Part"+partCount+"Screenshot"+scrCount+".png");
			scrCount++;
	  }
	

//Finding the diffreence between the images
  public static void getPicDiff(WebDriver driver) throws IOException, InterruptedException
  {
    Boolean CheckNewImage=true;
    Boolean CheckSameImage=true;
    BufferedImage img = null;
    BufferedImage img1 = null;
    double changePercent = 0.40;
  
    if(Initiallist.size()==1)
    {
         CheckNewImage=false;
         CheckSameImage=false;
    }
    
   
    	for(int j=0;j<(Initiallist.size()-1);j++)
    	{
    			 try 
    			 {
    			 img = ImageIO.read(new File(Initiallist.get(Initiallist.size()-1)));
    	    	 img1 =ImageIO.read(new File(Initiallist.get(j)));
    	    	 
    	 //   	 System.out.println("Comparing last element  "+(Initiallist.get(Initiallist.size()-1))+"\t with mylist\t"+ Initiallist.get(j));	
    			 }
    			 catch (IOException e) 
    			 {
    			      e.printStackTrace();
    			 }
    			 
    			    int width1 = img.getWidth(null);
    			//    int width2 = bimg1.getWidth(null);
    			    int height1 = img.getHeight(null);
    			//    int height2 = bimg1.getHeight(null);
    			//    if ((width1 != width2) || (height1 != height2)) 
    			 //   {
    			    //  System.err.println("Error: Images dimensions mismatch");
    			       
    			//    }
    			    long diff = 0;
    			    for (int y = 0; y < height1; y++) {
    			     for (int x = 0; x < width1; x++) {
    			        int rgb1 = img.getRGB(x, y);
    			        int rgb2 = img1.getRGB(x, y);
    			        int r1 = (rgb1 >> 16) & 0xff;
    			        int g1 = (rgb1 >>  8) & 0xff;
    			        int b1 = (rgb1      ) & 0xff;
    			        int r2 = (rgb2 >> 16) & 0xff;
    			        int g2 = (rgb2 >>  8) & 0xff;
    			        int b2 = (rgb2      ) & 0xff;
    			        diff += Math.abs(r1 - r2);
    			        diff += Math.abs(g1 - g2);
    			        diff += Math.abs(b1 - b2);
    			      }
    			    }
    			    double n = width1 * height1 * 3;
    			    double p = diff / n / 255.0;
    			//    System.out.println("diff percent: " + (p * 100.0));
    			    
    			    
 //Considering changePercent for determine the new image 
    			   
    			  if((p * 100.0)<=changePercent)
    			  {	CheckNewImage=false;
    			 if((p * 100.0) == 0.0)
    			  { CheckSameImage=false;

    				 Initiallist.remove(Initiallist.size()-1);
    
    			  }
    			    
    			  }
    			  
    	}
  
 	
    	
    if(CheckNewImage==true || CheckSameImage==true)
    {
    
        	getScreenshot(driver, part,0);
        	part++;
    		currentMsec= System.currentTimeMillis();
    		end = currentMsec+5000;
        	
   }else
    	{
        	getScreenshot(driver, part,0);
    	}
    

  }
}

