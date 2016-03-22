
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.collections.Sets;

import com.google.common.base.Functions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;


public class pageSoruce {
	static Map<String,Integer> multimap = new HashMap<String,Integer>();
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		WebDriver driver=new FirefoxDriver();
		
	//	Thread.sleep(2000);
		long currentMsec= System.currentTimeMillis();
		long end = currentMsec+15000;
		
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/2249/5c/d6/5cd685097d02ee783116d4c34bea1987.html");	
		
		List<WebElement> atag= driver.findElements(By.tagName("a"));
	//	System.out.println("Empty a size"+atag.size());
		for(WebElement a:atag)
		{
			String linkString=null;
			try{
				
				
				linkString=a.getText();
				if(linkString!=null){
				driver.findElement(By.partialLinkText(linkString)).click();
				
				}else{
					linkString=a.getAttribute("href");
			if(linkString!=null)
			{
			driver.get(linkString);
			System.out.println(linkString);
			}else
			{
				System.out.println("Empty a tag");
			}
			}}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		
		//CallFindDomChange(driver,currentMsec,end);
	}
		
	

	private static void CallFindDomChange(WebDriver driver, long currentMsec, long end) throws IOException, Exception 
	{
/*		int sleep=1;
		while(System.currentTimeMillis() < end) 
		{  		   System.out.println(sleep);
			Boolean keyCheck=false;
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			PrintWriter out = new PrintWriter("C:\\Users\\Administrator\\Desktop\\Pagesource\\Dom.txt");
			out.println(driver.getPageSource());
			out.close();
			FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\Dom.txt"));
			String result = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
			fis.close();
			
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			//System.out.println(driver.getPageSource());
			messageDigest.update(driver.getPageSource().getBytes(Charset.forName("UTF8")));
			PrintWriter out = new PrintWriter("C:\\Users\\Administrator\\Desktop\\Pagesource\\Dom"+sleep+".txt");
			out.println(driver.getPageSource());
			out.close();
			final byte[] resultByte = messageDigest.digest();
			final String result = new String(Hex.encodeHex(resultByte));
	
			if(multimap.isEmpty())
			{
				multimap.put(result,sleep);
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\"+result+".png"));
			}
			else
			{
			Iterator entries = multimap.entrySet().iterator();
			while (entries.hasNext()) 
			{
				Entry thisEntry = (Entry) entries.next();
				Object key = thisEntry.getKey();
				if(key.toString().equals(result))
				{
					keyCheck=true; 
					break;
				}
			}
			
			if(keyCheck==false)
			{
				Thread.sleep(500);
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\"+result+".png"));
				multimap.put(result,sleep);
				currentMsec= System.currentTimeMillis();
				end = end+5000;
				 System.out.println("Extending......");
			}
			}
			sleep++;	
			
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\last.png"));}
		
	//   Ordering<String> Final = Ordering.natural().onResultOf(Functions.forMap(multimap));
	   System.out.println(multimap);*/
		
	
		}





}

