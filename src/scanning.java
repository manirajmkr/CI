import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.map.MultiValueMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
public class scanning 
{	static WebDriver driver;
	static Boolean landingPagestate=false;
	static	MultiValueMap  Landingpage_width_and_height = new MultiValueMap();
	public static void main(String[] args) 
	{driver=new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/2307/50/38/503896b4a7294375200fe6e3119dde95.html");
		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		List<WebElement> TotaliFrames = driver.findElements(By.tagName("iframe"));
		System.out.println("iFrame count\t"+TotaliFrames.size());
		if(TotaliFrames.size()>0)
		{
			for(WebElement iframe : TotaliFrames)
			{
				System.out.println("main iframe \t"+iframe.toString());
				System.out.println("main iframe height\t"+iframe.getAttribute("height").toString());
				System.out.println("main iframe width\t"+iframe.getAttribute("width").toString());
				innerframfunction(iframe);		
			}
		}
			System.out.println("No more iframe found switching to normal scan mode");
			List<WebElement> canvas = driver.findElements(By.tagName("canvas"));
			List<WebElement> img = driver.findElements(By.tagName("img"));
			List<WebElement> src = driver.findElements(By.tagName("src")); 
			List<WebElement> div = driver.findElements(By.tagName("div"));
			List<WebElement> anchor = driver.findElements(By.tagName("a")); 
			System.out.println("canvas count in frame t"+ canvas.size());
			if(canvas.size()>0)
			{
				System.out.println("inside canvas");
				
				for(WebElement canvTemp : canvas)
				{
					System.out.println("height\t"+canvTemp.getAttribute("height"));
					System.out.println("width\t"+canvTemp.getAttribute("width"));
					callnormal(canvTemp);
				}
			}
			
			if(img.size()>0)
			{	System.out.println("inside img");
				for(WebElement imge : img)
				{
					System.out.println("==================="+imge.getSize().getHeight());
					System.out.println("==================="+imge.getSize().getWidth());
					System.out.println("height\t"+imge.getAttribute("height"));
					System.out.println("width\t"+imge.getAttribute("width"));
					callnormal(imge);
				}
			}
			if(src.size()>0)
			{System.out.println("inside src");
				for(WebElement srcc : src)
				{	System.out.println("height\t"+srcc.getAttribute("height"));
				System.out.println("width\t"+srcc.getAttribute("width"));
					callnormal(srcc);
				}
			}
			
			
			List<WebElement> atag= driver.findElements(By.tagName("a"));
			System.out.println("Empty a size"+atag.size());
			for(WebElement a:atag)
			{
				try{
				String linkString=a.getAttribute("href");
				if(linkString!=null)
				{
				driver.get(linkString);
				System.out.println(linkString);
				}else
				{
					System.out.println("Empty a tag");
				}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
			
		/*	if(anchor.size()>0)
			{System.out.println("inside anchor");
				for(WebElement anchorc : anchor)
				{System.out.println("height\t"+anchorc.getAttribute("height"));
				System.out.println("width\t"+anchorc.getAttribute("width"));
					callnormal(anchorc);
				}
			}*/
			if(div.size()>0)
			{System.out.println("inside div");
				for(WebElement divc : div)
				{System.out.println("height\t"+divc.getAttribute("height"));
				System.out.println("width\t"+divc.getAttribute("width"));
					callnormal(divc);
				}
			}
			System.out.println("Landingpage_width_and_height"+Landingpage_width_and_height);

	}

	private static void callnormal(WebElement wb) 
	{ String parentwindow=driver.getWindowHandle();
		 try {
			
			 System.out.println("Clicked\t");
			 System.out.println("Element\t"+wb.toString());
			 wb.click();
			// Actions newTab= new Actions(driver);
			  //newTab.contextClick(wb).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			 // 	wb.sendKeys(Keys.CONTROL,"t"); 
			  	String ImageHeight=wb.getAttribute("height");
				String ImageWidth=wb.getAttribute("width");
				String combained=ImageHeight+","+ImageWidth;
				System.out.println("height\t"+ImageHeight);
				System.out.println("width\t"+ImageWidth);
				Set<String> handles = driver.getWindowHandles(); 
				String  subWindowHandler=null;
				if(handles.size()>=2)
				{
				Iterator<String> iterator = handles.iterator();
				while (iterator.hasNext())
					{
						subWindowHandler = iterator.next();
					 }
				driver.switchTo().window(subWindowHandler); // switch to popup window
				String fUrl = driver.getCurrentUrl();
					
				System.out.println("final Url\t"+fUrl);
				Landingpage_width_and_height.put(fUrl, combained);
				System.out.println("Added into the map ");
				driver.close();
				driver.switchTo().window(parentwindow);
			}
		  	}
		  catch(Exception e)
		  {
			  System.out.println("Exception unable to click");
				driver.switchTo().window(parentwindow);
	       }
	}

	private static void innerframfunction(WebElement childiFrame) 
	{
	//	int innerframecount = 0;
		String parentwindow=driver.getWindowHandle(); 
		driver.switchTo().frame(childiFrame);
		List<WebElement> TotalinneriFrames = driver.findElements(By.tagName("iframe"));	
		System.out.println("inner iFrame count\t"+TotalinneriFrames.size());
		if(TotalinneriFrames.size()>0)
		{
			for(WebElement childiFram:TotalinneriFrames)
			{
				System.out.println("calling recursive");
				innerframfunction(childiFram);
			}
		}
		else 
		{
			System.out.println("else block ");
			List<WebElement> innercanvas = driver.findElements(By.tagName("canvas"));
			List<WebElement> innersrc = driver.findElements(By.tagName("src"));
			List<WebElement> innerimg = driver.findElements(By.tagName("img"));
			List<WebElement> inneranchor = driver.findElements(By.tagName("a"));
			List<WebElement> innerdiv = driver.findElements(By.tagName("div"));
			System.out.println("innersrc.size\t"+innersrc.size());
			if(innersrc.size()>0)
			{
			   for(WebElement insrc:innersrc)
			   {
				  // insrc.click();
				   System.out.println("taking inneriFrames"); 
				   swtichwindow(driver,insrc,parentwindow,childiFrame);
				   landingPagestate=true;
			   }
			}
			
			System.out.println("innercanvas.size()\t"+innercanvas.size());
			if(innercanvas.size()>0)
			{
			   for(WebElement incan:innercanvas)
			   {
				  // incan.click();
				   swtichwindow(driver,incan,parentwindow,childiFrame);
				   landingPagestate=true;
			   }
			}
			System.out.println("innerimg.size()\t"+innerimg.size());
			if(innerimg.size()>0)
			{
			   for(WebElement inimg:innerimg)
			   {
				 //  inimg.click();
				   System.out.println("==================="+inimg.getSize().getHeight());
					System.out.println("==================="+inimg.getSize().getWidth());
				   System.out.println("taking innerimage"); 
				   swtichwindow(driver,inimg,parentwindow,childiFrame); 
				   landingPagestate=true;
			   }
			}
			System.out.println("inneranchor.size()\t"+inneranchor.size());
	/*		if(inneranchor.size()>0)
			{
			   for(WebElement ina:inneranchor)
			   {
				 //  ina.click();
				   swtichwindow(driver,ina,parentwindow,childiFrame);
				   
				   landingPagestate=true;
			   }
			}*/
			List<WebElement> atag= driver.findElements(By.tagName("a"));
			System.out.println("Empty a size"+atag.size());
			for(WebElement a:atag)
			{
				try{
				String linkString=a.getAttribute("href");
				if(linkString!=null)
				{
				driver.get(linkString);
				System.out.println(linkString);
				}else
				{
					System.out.println("Empty a tag");
				}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
			System.out.println("innerdiv.size()\t"+innerdiv.size());
			// int big=0;
			 if(innerdiv.size()>0)
			{
				for(WebElement divTemp : innerdiv)
				{
				System.out.println("divtempppppp"+divTemp.toString());
				if(divTemp.isDisplayed()==true)
				{
				System.out.println("divTemp.isDisplayed() "+divTemp.isDisplayed());
				//divTemp.click();
				swtichwindow(driver,divTemp,parentwindow,childiFrame); 
			//	System.out.println("divTemp.isDisplayed() afterrrrrrr");
				landingPagestate=true;
				}
				else
				{
					System.out.println("Skipping div");
				}
				
		
				}
			 
		}
			System.out.println("no further inner iFrame \t");
			
		List <WebElement> TotalinneriFramess = driver.findElements(By.tagName("iframe"));
		System.out.println("TotalinneriFramess.size() \t"+TotalinneriFrames.size());
			
			ListIterator<WebElement> li = TotalinneriFrames.listIterator((TotalinneriFramess.size()));

			// Iterate in reverse.
			while(li.hasPrevious()) 
			{
				System.out.println("inside itrator \t");
				innerframfunction((WebElement) li.previous());
			}
			
			driver.switchTo().window(parentwindow);	
			System.out.println("nparentwindow \t");
		//	driver.switchTo().frame(childiFrame);

			
}
	}

	private static void swtichwindow(WebDriver driver, WebElement wb, String parentwindow, WebElement iframe) 
	{
		  try {
			  
			  wb.click();
			//  Actions newTab= new Actions(driver);
			  //newTab.contextClick(wb).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			  
			//  	wb.sendKeys(Keys.CONTROL,"t"); 
			  	String ImageHeight=wb.getAttribute("height");
				String ImageWidth=wb.getAttribute("width");
				String combained=ImageHeight+","+ImageWidth;
				System.out.println("height\t"+ImageHeight);
				System.out.println("width\t"+ImageWidth);
				  Set<String> handles = driver.getWindowHandles(); 
				  String  subWindowHandler=null;
				  if(handles.size()>=2)
					 {
					 Iterator<String> iterator = handles.iterator();
					 while (iterator.hasNext())
					 {
						subWindowHandler = iterator.next();
					 }
					driver.switchTo().window(subWindowHandler); // switch to popup window
					String fUrl = driver.getCurrentUrl();
					
					System.out.println("final Url\t"+fUrl);
					Landingpage_width_and_height.put(fUrl, combained);
					System.out.println("Added into the map ");
					driver.close();
					driver.switchTo().window(parentwindow);
					driver.switchTo().frame(iframe);
			}
		  	}
		  catch(Exception e)
		  {
			  System.out.println("Exception unable to click");
			
	       }
}
}
