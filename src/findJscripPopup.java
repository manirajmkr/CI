import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.DataBufferByte;
import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class findJscripPopup {

	/**
	 * @param args
	 */
	//static WebDriver driver=new FirefoxDriver();
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/Administrator/Desktop/jpopup.htm");
		Thread.sleep(2000);
		
		try
		{
		Alert alert=driver.switchTo().alert();
		System.out.println("POPUP found\n");
		alert.accept();
		
		System.out.println(driver.getTitle());
		String str=driver.findElement(By.xpath("html/body/p")).getText();
		System.out.println(str);
		
		}
		catch (Exception e){
		System.out.println("No POPUP\n");
		}
		
	}
}
	