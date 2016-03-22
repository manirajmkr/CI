import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import mx4j.tools.config.DefaultConfigurationBuilder.New;


public class clickBasedonPosition {
	
	static WebDriver driver;


	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		
		driver=new FirefoxDriver();
		
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/1986/b6/55/b655a10ab47ebe09713640f5b29ba373.html");
		
		WebElement e = driver.findElement(By.xpath("//img"));
		Dimension size = e.getSize();
		System.out.println(size);
		Actions action = new Actions(driver);
		action.moveByOffset(size.width/2, size.height/2);
		
		System.out.println("\n\n\n"+size.width/2+size.height/2);
		Thread.sleep(2000);
		action.click().perform();


	}

}
