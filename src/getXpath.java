import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class getXpath {

/*	public static String GetWebElementXpaths(WebElement El) throws AssertionError
	{
        if ((El instanceof WebElement)){
            Object o = El;
            String text = o.toString();
         text is smth like this
        [[FirefoxDriver: firefox on WINDOWS (9170d4a5-1554-4018-adac-f3f6385370c0)] -> xpath: //div[contains(@class,'forum-topic-preview')]//div[contains(@class,'small-human')]]
        
            text = text.substring( text.indexOf("xpath: ")+7,text.length()-1);
            return text;
        }else   {   Assert.fail("Argument is not an WebElement, his actual class is:"+El.getClass());       }
        return "";
    }*/
	
	
	
	public static String GetWebElementXpaths(WebDriver driver, WebElement element) 
	{
	    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]);", element);
	}

}
