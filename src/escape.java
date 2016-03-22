import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;


public class escape {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws AWTException 
	 */
	public static void main(String[] args) throws InterruptedException, AWTException 
	
	{
		
		WebDriver driver=new FirefoxDriver();
	//	driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/2271/3f/4e/3f4e988c059a21507b37fbeeb864fbe3.html");
		 List<WebElement> img = driver.findElements(By.xpath("//img"));
		 String Before_click_url=driver.getWindowHandle();
		for(WebElement im:img)
		{
			im.click();
			 Set<String> After_click_url = driver.getWindowHandles();
			 
			 for(String window:After_click_url)
			 {
				 if(!Before_click_url.equalsIgnoreCase(window))
				 {
					 WebDriver driver1=driver.switchTo().window(window);
					 System.out.println("Switched");
					// Thread.sleep(5000);
					// Robot robot = new Robot();
					 //robot.keyPress(KeyEvent.VK_ESCAPE); 
					 String Lp=driver1.getCurrentUrl();
					 System.out.println("LP"+Lp);
					 driver1.close();
				 } WebDriver driver1=driver.switchTo().window(Before_click_url);
			 }
		}
		
		
	
		
		
		 
		//Load the url	
	

	}

}
