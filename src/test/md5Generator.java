package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class md5Generator {

	//static WebDriver driver =new FirefoxDriver();
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		
	//image hash comparision	
		String image1="C:\\Users\\Administrator\\Desktop\\HashTest\\test001.png";
		String image2="C:\\Users\\Administrator\\Desktop\\HashTest\\test003.png";
		String image1Hash=passImage(image1);
		String image2Hash=passImage(image2);
		
		if(image1Hash.equals(image2Hash))
		{
			System.out.println("Hash matched");
			System.out.println("Hash matched");
		}
		else
		{
			System.out.println("Hash NOT matched");
		}
		
	//pagesorce comparision
		/*
		driver.get("https://www.google.co.in/");
		String PageOne=driver.getPageSource();
		
		PageOne=passPageSrc(PageOne);
		System.out.println("============================>\t"+PageOne);
		driver.close();
		driver =new FirefoxDriver();
		driver.get("https://www.google.co.in/");
		String PageTwo=driver.getPageSource();
	
		PageTwo=passPageSrc(PageTwo);
		System.out.println("============================>\t"+PageTwo);
		
		
		if(PageOne.equals(PageTwo))
		{
			System.out.println("Page Hash matched");
		}
		else
		{
			System.out.println("Page Hash NOT matched");
		}*/
    }     
	
	
	public static String passImage(String image1) throws Exception 
	{
		File input = new File(image1);
        BufferedImage buffImg = ImageIO.read(input);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", outputStream);
        byte[] data = outputStream.toByteArray();

        System.out.println("Start MD5 Digest");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data);
        byte[] hash = md.digest();
        
        System.out.println("========>\t"+returnHex(hash));
		return returnHex(hash);
	}
	
		
	public static String passPageSrc(String pageSRC) throws Exception 
	{
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(pageSRC.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		
		
		return hashtext;
	}
	
	
	// Belongs to main class
   
    // Below method of converting Byte Array to hex

    static String returnHex(byte[] inBytes) throws Exception 
    {
        String hexString = null;
        for (int i=0; i < inBytes.length; i++) { //for loop ID:1
            hexString +=
            Integer.toString( ( inBytes[i] & 0xff ) + 0x100, 16).substring( 1 );
        }                                   // Belongs to for loop ID:1
    return hexString;

	}

}
