import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class exceptions {
	static WebDriver driver;
	static Boolean landingPagestate=false;
	

	public static void main(String[] args) throws InterruptedException, Exception, Exception 
	{
	
		driver=new FirefoxDriver();
	
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/2307/29/09/2909cf0ed9d5262442876c835808efd5.html");
	

// Handing the canvas,src attributes inside the iFrame		
		
		List<WebElement> iFrames = driver.findElements(By.tagName("iframe"));
		System.out.println("iFrame count\t"+iFrames.size());
		int i=1;
					for(WebElement iframe : iFrames)
					{
						if(iFrames.size()>0)
						{
							System.out.println("iframe \t"+iframe.toString());
							innerframfunction(iframe);
							driver.switchTo().defaultContent();
						}
					}

								List<WebElement> canvas = driver.findElements(By.tagName("canvas"));
								List<WebElement> img = driver.findElements(By.tagName("img"));
								List<WebElement> src = driver.findElements(By.tagName("src")); 
								List<WebElement> div = driver.findElements(By.tagName("div"));
								List<WebElement> anchor = driver.findElements(By.tagName("a")); 
								System.out.println("canvas count in frame [" +i+"]\t"+ canvas.size());
								if(canvas.size()>0)
								{
									for(WebElement canvTemp : canvas)
									{
										//canvTemp.click();
										swtichwindow(driver,canvTemp);
										
										landingPagestate=true;
										break;
									}
								}
								System.out.println("src count in frame [" +i+"]\t"+ src.size());
								if(src.size()>0)
								{
									for(WebElement srcTemp : src)
									{
										//srcTemp.click();
										swtichwindow(driver, srcTemp);
										String ImageHeight=srcTemp.getAttribute("height");
										String ImageWidth=srcTemp.getAttribute("width");
									
										
										System.out.println("height\t"+ImageHeight);
										System.out.println("width\t"+ImageWidth);
										landingPagestate=true;
										break;
									}	
								}
								
								System.out.println("img count in frame [" +i+"]\t"+ img.size());
								if(img.size()>0)
								{
									for(WebElement imgTemp : img)
									{
										//imgTemp.click();
										swtichwindow(driver,imgTemp);
										String ImageHeight=imgTemp.getAttribute("height");
										String ImageWidth=imgTemp.getAttribute("width");
										
										System.out.println("height\t"+ImageHeight);
										System.out.println("width\t"+ImageWidth);
										landingPagestate=true;
										break;
									}	
								}
								System.out.println("anchor count in frame [" +i+"]\t"+ anchor.size());
								if(anchor.size()>0)
								{
									for(WebElement aTemp : anchor)
									{
										//aTemp.click();
										swtichwindow(driver,aTemp);
										String ImageHeight=aTemp.getAttribute("height");
										String ImageWidth=aTemp.getAttribute("width");
								
										System.out.println("height\t"+ImageHeight);
										System.out.println("width\t"+ImageWidth);
										landingPagestate=true;
										break;
									}	
								}
								System.out.println("div size \t"+ div.size());
								 if(div.size()>0)
									{
										for(WebElement divTemp : div)
										{
										System.out.println("divtempppppp"+divTemp.toString());
										if(divTemp.isDisplayed()==true)
										{
										System.out.println("divTemp.isDisplayed() "+divTemp.isDisplayed());
										//divTemp.click();
										swtichwindow(driver,divTemp);
										System.out.println("divTemp.isDisplayed() afterrrrrrr");
										landingPagestate=true;
										}
										else
										{
											System.out.println("Skipping div");
										}
										
								
										}
									 
								}
								
							
					
				
				}
				
			
			
			
		


		

	private static void innerframfunction(WebElement iframe) throws InterruptedException, MalformedURLException, IOException 
	{
		System.out.println("innerframfunction entres");
		driver.switchTo().frame(iframe);
		System.out.println("after innerframfunction entres");
		int i=0,j=0;
		List<WebElement> inneriFrames = driver.findElements(By.tagName("iframe"));
		
		
		WebElement finalDiv = null;
		System.out.println("inneriFrames \t"+inneriFrames.size());
		
		if(inneriFrames.size()>=0)
		{	
			
			
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
							   swtichwindow(driver,insrc);
							   String innerLpage= driver.getCurrentUrl();
							   System.out.println("innerLpage"+innerLpage); i++;
							   System.out.println("done inneriFrames"); 
							   landingPagestate=true;
						   }
						}
						
						System.out.println("innercanvas.size()\t"+innercanvas.size());
						if(innercanvas.size()>0)
						{
						   for(WebElement incan:innercanvas)
						   {
							  // incan.click();
							   swtichwindow(driver,incan);
							   String innerLpage= driver.getCurrentUrl();
							   System.out.println("innerLpage"+innerLpage); i++;
							   landingPagestate=true;
						   }
						}
						System.out.println("innerimg.size()\t"+innerimg.size());
						if(innerimg.size()>0)
						{
						   for(WebElement inimg:innerimg)
						   {
							 //  inimg.click();
							   System.out.println("taking innerimage"); 
							   swtichwindow(driver,inimg);
							   System.out.println("done innerimage"); 
							   String innerLpage= driver.getCurrentUrl();
							   System.out.println("innerLpage"+innerLpage); i++;
							   landingPagestate=true;
						   }
						}
						System.out.println("inneranchor.size()\t"+inneranchor.size());
						if(inneranchor.size()>0)
						{
						   for(WebElement ina:inneranchor)
						   {
							 //  ina.click();
							   swtichwindow(driver,ina);
							   String innerLpage= driver.getCurrentUrl();
							   System.out.println("innerLpage"+innerLpage); i++;
							   landingPagestate=true;
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
							swtichwindow(driver,divTemp);
							System.out.println("divTemp.isDisplayed() afterrrrrrr");
							landingPagestate=true;
							}
							else
							{
								System.out.println("Skipping div");
							}
							
					
							}
						 
					}
						
						 
						 for(WebElement innerframe : inneriFrames)
							{
											
									System.out.println("calling recursive with "+innerframe.toString());
									
									innerframfunction(innerframe);
									driver.switchTo().defaultContent();
							}
							
						
			
				}
				
	
				
			
		driver.switchTo().defaultContent();

			// driver.switchTo().frame(iframe);
			 
			}
			
	
	
	
	public static boolean isClickable(WebElement webe, WebDriver Driver)      
	{
	try
	{
	   WebDriverWait wait = new WebDriverWait(Driver, 5);
	   wait.until(ExpectedConditions.elementToBeClickable(webe));
	   return true;
	}
	catch (Exception e)
	{
	  return false;
	}
	}
	
	
	public static int getResponseCode(String urlString) throws MalformedURLException, IOException{
	    URL url = new URL(urlString);
	    HttpURLConnection huc = (HttpURLConnection)url.openConnection();
	    huc.setRequestMethod("GET");
	    huc.connect();
	    return huc.getResponseCode();
	}
	
	
	public static void swtichwindow(WebDriver driver, WebElement wb) throws InterruptedException, MalformedURLException, IOException
	{
		
		System.out.println("swtichwindowswtichwindowswtichwindowswtichwindowswtichwindowswtichwindowswtichwindow");
		//Boolean IsClickable =isClickable(wb,driver);
	//	if(IsClickable==true )
	//	{
			
			  try {
				  wb.click();
			  	}
			  catch(Exception e){
				  System.out.println("Exception unable to click");
		       }
			
		//}
		String ImageHeight=wb.getAttribute("height");
		String ImageWidth=wb.getAttribute("width");
		System.out.println("height\t"+ImageHeight);
		System.out.println("width\t"+ImageWidth);
	
		//System.out.println("wb.IsClickable()\t"+IsClickable);
		String parentwindow=driver.getWindowHandle(); 
		String src=wb.getAttribute("src").toString();
		int statusCode = getResponseCode(src);
		System.out.println("statusCode"+statusCode);
		System.out.println("src"+src);
		
		
	
		Thread.sleep(5000);
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
		driver.close();
		driver.switchTo().window(parentwindow); // switch to parent window
	//	driver.switchTo().defaultContent();
	//	
	//	System.exit(0);
	 }
		Object[] array = handles.toArray();
		String str=(String) array[0];
	
		if(handles.size()==1 && parentwindow!=str)
		 {
			Thread.sleep(2000);
			String fUrl = driver.getCurrentUrl();
			System.out.println("final11111111111 Url\t"+fUrl);
			//driver.switchTo().defaultContent();
			//driver.switchTo().window(parentwindow);
		 }
		System.out.println("exit ");	
				
	}
}




		


	



