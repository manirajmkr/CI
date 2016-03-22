import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class logic {

	static WebDriver driver;
	static List<WebElement> TotaliinnerFrames=null;
	static String parent;
	logic() 
	{
		driver=new FirefoxDriver();
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/2132/23/2a/232af75fa5793c75f7a7c5f35fe76883.html");
		
	}
	public static void main(String[] args) 
	{
		logic log=new logic();
		findLandingPage(driver);
		parent=driver.getWindowHandle();

	}




	private static void findLandingPage(WebDriver driver2) 
	{
		
		
		List<WebElement> canvas = driver2.findElements(By.tagName("canvas"));
		List<WebElement> img = driver2.findElements(By.tagName("img"));
		List<WebElement> anchor = driver2.findElements(By.tagName("a")); 
		System.out.println("canvas\t"+canvas.size());
		System.out.println("img\t"+img.size());
		System.out.println("anchor\t"+anchor.size());
		
	
		List <WebElement> iFrameTags = driver2.findElements(By.tagName("iframe"));
	//	System.out.println("TotaliinnerFrames\t"+TotaliinnerFrames.size());
	//	int innerframesize=TotaliinnerFrames.size();
	
		//	System.out.println("TotaliinnerFrames\t"+innerframesize);
			String src;
			for(WebElement inframe:iFrameTags)
				
				
			{	
				
				
				if(canvas.size()>0)
				{
					System.out.println("inside canvas");
					
					for(WebElement canvTemp : canvas)
					{
						callClick(canvTemp, driver2);
						
					}
				}
				if(img.size()>0)
				{	System.out.println("inside img");
					for(WebElement imge : img)
					{
						callClick(imge, driver2);
						
						
					}
				}
				if(anchor.size()>0)
				{
					System.out.println("inside anchor");
					for(WebElement anchorc : anchor)
					{
					callClick(anchorc, driver2);
					}
				}
				
				
				
				
				System.out.println("calling inner recursive\t");
				src=inframe.getAttribute("src");
				driver2.get(src);
				findLandingPage(driver2);
				
			}
			
			
		
		
		
	}
	private static void callClick(WebElement canvTemp, WebDriver driver3) 
	{
		String parent=driver3.getWindowHandle();
		try
		{
		canvTemp.click();
		System.out.println("Element clicked");
		driver.switchTo().window(parent);
		}
		catch(Exception e)
		{
			System.out.println("Exception unable to click");
	//		driver.switchTo().window(parent);
		}
		
	}



}
