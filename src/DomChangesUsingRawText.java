import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DomChangesUsingRawText {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		
		WebDriver driver=new FirefoxDriver();
		driver.get("http://publish.rcc.bps-core.com/creative_preview/rtb/1512/d2/9b/d29b9457522c029fbaf1457ddc95b3ad.html");	
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	
		System.out.println("Main");
		int i=1;
		GetMainDom(driver);
		CallFindDomChanges(driver);
		
	}
		
	private static void GetMainDom(WebDriver driver) throws FileNotFoundException 
	{
		PrintWriter out = new PrintWriter( "C:\\Users\\Administrator\\Desktop\\Pagesource\\DomFile1.txt" );
		System.out.println("updating the Master");
		out.println( driver.getPageSource() );
		out.close();
	}



	private static void CallFindDomChanges(WebDriver driver) throws Exception 
	{
		int NumberofDomFiles=2;
		long currentMsec= System.currentTimeMillis();
		long end = currentMsec+30000;
		while(System.currentTimeMillis() < end) 
		{
			boolean compare =false;
		
			PrintWriter out = new PrintWriter( "C:\\Users\\Administrator\\Desktop\\Pagesource\\DomFile"+NumberofDomFiles+".txt" );
			System.out.println("updating the temp");
			out.println(driver.getPageSource() );
			out.close();
			
			for(int a=1;a<=NumberofDomFiles;a++)
			{
			for(int i=2;i<=NumberofDomFiles;i++)
			{
				if(a!=i)
				{
				System.out.println("Number of Dom "+(NumberofDomFiles));
				System.out.println("Comparing DomFile"+a +"with"+"DomFile"+i);
				
				File MasterDom = new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\DomFile"+a+".txt");
				File ChangeDom = new File("C:\\Users\\Administrator\\Desktop\\Pagesource\\DomFile"+i+".txt");
				compare = FileUtils.contentEquals(MasterDom, ChangeDom);
				System.out.println("compare"+compare);
				if(compare==true)
				{
					System.out.println("Break");
					break;
				}
				}
			}
		}
			
			
		if(compare==false)
		{
			NumberofDomFiles++;
			PrintWriter out1 = new PrintWriter( "C:\\Users\\Administrator\\Desktop\\Pagesource\\DomFile"+NumberofDomFiles+".txt" );
			System.out.println("added new DOM");
			out.println( driver.getPageSource() );
			currentMsec= System.currentTimeMillis();
			System.out.println("Extending");
			end = currentMsec+5000;
			out1.close();
		}else
		{
			System.out.println("No change in the dom");
		}
			
		
			

		}
		
	}



	private static void GetPagesource(WebDriver driver, int i, String state) throws Exception 
	{
		if(i==1)
		{
		
		}
		else if(state=="temp")
		{
			PrintWriter out = new PrintWriter( "C:\\Users\\Administrator\\Desktop\\Pagesource\\temp.txt" );
			System.out.println("updating the temp");
			out.println( driver.getPageSource() );
			out.close();
		}
		else if(state=="change")
		{
			PrintWriter out = new PrintWriter( "C:\\Users\\Administrator\\Desktop\\Pagesource\\ChangedDom.txt" );
			System.out.println("updating the Change");
			out.println( driver.getPageSource() );
			out.close();
		}
	}

}
