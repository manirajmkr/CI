import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class iframesearch {


	private static final int MAX_DEPTH = 1000;
	static WebDriver driver;
	public static void main(String[] args) 
	{
		driver=new FirefoxDriver();
		searchFrames(null);
		
	}
	private static void searchFrames(List<Integer> route)
	{
	  //  doWhateverYouWannaDoHere();
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/2249/04/1e/041ef1623f4c76c03a02b9d372ce9c0d.html");
	//	System.out.println("newRoute"+route.size());
	  //  if (route.size() < MAX_DEPTH)
	//    {
	        int numOfFrames = driver.findElements(By.tagName("iframe")).size();
	        System.out.println("numOfFrames"+numOfFrames);
	        for (int i=0; i<numOfFrames; i++)
	        {
	            try
	            {  System.out.println("try");
	            	driver.switchTo().frame(i);
	            	 System.out.println("switchTo");
	                List<Integer> newRoute = new ArrayList<Integer>(route);
	                newRoute.add(i);
	             	 System.out.println("add"+ newRoute.add(i));
	                System.out.println("newRoute"+newRoute);
	                System.out.println("calling searchFrames");
	                searchFrames(newRoute);
	                driver.switchTo().defaultContent();
	                System.out.println("switching default");
	                for (int j : route)
	                	driver.switchTo().frame(j);
	            }
	            catch (NoSuchFrameException error)
	            {
	                break;
	            }
	            catch (Exception error)
	            {
	            }
	        }
	    }
	}
//}
