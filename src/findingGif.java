import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class findingGif {

	static WebDriver Driver;
	static List<WebElement> TotaliinnerFrames=null;
	static String parent,url;
	static List<String> anchorList = new ArrayList<String>();

	static List<String> imageList = new ArrayList<String>();
	static List<String> videoList = new ArrayList<String>();
	static List<String> video1x1List = new ArrayList<String>();
	static List<String> refJsScript = new ArrayList<String>();
	static StringBuffer runJsCounts = new StringBuffer("0");
	static List<String> iFrameList = new ArrayList<String>();
	static List<String> objectList = new ArrayList<String>();
	static List<String> iFrame1x1List = new ArrayList<String>();
	static List<String> image1x1List = new ArrayList<String>();
	static List<String> canvasList = new ArrayList<String>();
	static List<String> canvas1x1List = new ArrayList<String>();
	static List<String> objecttagList = new ArrayList<String>();
	static List<String> objecttag1x1List = new ArrayList<String>();
	static List<String> embed1x1List = new ArrayList<String>();
	static List<String> embedList = new ArrayList<String>();
	static List<String> object1x1List = new ArrayList<String>();
	static List<String> audioList = new ArrayList<String>();
	static List<String> audio1x1List = new ArrayList<String>();
	
	
	static Map<Long, String> imageNameWithLandingPage = new TreeMap<Long, String>();
	static Map<Long, String> videoNameWithLandingPage = new TreeMap<Long, String>();
	static Map<Long, String> embedNameWithLandingPage = new TreeMap<Long, String>();
	static Map<Long, String> objectNameWithLandingPage = new TreeMap<Long, String>();
	static Map<Long, String> audioNameWithLandingPage = new TreeMap<Long, String>();
	
	
	static StringBuffer creativeSize = new StringBuffer("0x0");
	findingGif() 
	{
		Driver=new FirefoxDriver();
		System.out.println("constructor");
	}

	public static void main(String[] args) throws Exception 
	{
		String[] url={"http://publish.rcc.bps-core.com/creative_preview/rtb/1512/b7/e0/b7e0509078c02678c0687fa20d2b60cc.html",
				};
		findingGif obj=new findingGif();
		
		for(int i=0;i<url.length;i++)
		{
		Driver.get(url[i]);
		getAdUrlDetails(Driver, url[i], anchorList,  imageList, refJsScript,  runJsCounts,	 iFrameList,  objectList,  iFrame1x1List,
				 image1x1List,  imageNameWithLandingPage,  creativeSize,  canvasList, canvas1x1List,videoNameWithLandingPage,embedNameWithLandingPage,
				 objectNameWithLandingPage,audioNameWithLandingPage);
		}
		
//Call this method when you want gif delay attribute
		
		callFindGif();
		
		System.out.println("imageNameWithLandingPage \t"+imageNameWithLandingPage);
		System.out.println("imageNameWithLandingPage count\t"+imageNameWithLandingPage.size());
		System.out.println("videoNameWithLandingPage\t"+videoNameWithLandingPage);
		System.out.println("videoNameWithLandingPage count\t"+videoNameWithLandingPage.size());
		System.out.println("embedNameWithLandingPage\t"+embedNameWithLandingPage);
		System.out.println("embedNameWithLandingPage count\t"+embedNameWithLandingPage.size());
		System.out.println("objectNameWithLandingPage \t"+objectNameWithLandingPage);
		System.out.println("objectNameWithLandingPage count\t"+objectNameWithLandingPage.size());
		System.out.println("audioNameWithLandingPage\t"+audioNameWithLandingPage);
		System.out.println("audioNameWithLandingPage count\t"+audioNameWithLandingPage.size());
	}
		
		public static void getAdUrlDetails(WebDriver driver, String url, List<String> anchorList, List<String> imageList,
				List<String> refJsScript, StringBuffer runJsCounts,	List<String> iFrameList, List<String> objectList, List<String> iFrame1x1List,
				List<String> image1x1List, Map<Long, String> imageNameWithLandingPage, StringBuffer creativeSize, List<String> canvasList,
				List<String> canvas1x1List, Map<Long, String> videoNameWithLandingPage, Map<Long, String> embedNameWithLandingPage,
				Map<Long, String> objectNameWithLandingPage, Map<Long, String> audioNameWithLandingPage) throws Exception 
				
		{
			Thread.sleep(5000);
			try{
			//	Driver=driver;

					//reading the page source
					String pageSource = driver.getPageSource();
					//		System.out.println(pageSource);

					//checking for <noscript> tag & extracting the anchor link if available
					if(pageSource.contains("<noscript")){
						for(int i = pageSource.indexOf("<noscript"); i != -1;){
							String noScriptContent = pageSource.substring(i + "<noscript>".length(), 
									pageSource.indexOf("</noscript>", i) - "</noscript>".length());

							if(noScriptContent.contains("<a")){
								//putting in anchor link in anchorjson
								String href = noScriptContent.substring(noScriptContent.indexOf("href=") + 6, 
										noScriptContent.indexOf("\"", noScriptContent.indexOf("href=") + 6)).replace("amp;", "");
								if(href != null){
									if(!(href.toLowerCase().equals("about:blank"))){
										anchorList.add(href);
									}
								}
							}
							i = pageSource.indexOf("<noscript", i+1);
						}
					}

					//reading all script tag
					List<WebElement> scriptTags = driver.findElements(By.xpath("//script[not(contains(@style,'display:none'))]"));
					for(WebElement scriptTag: scriptTags){
						if(scriptTag.getAttribute("src") != null){
							refJsScript.add(scriptTag.getAttribute("src"));
						} else {
							runJsCounts.replace(0, runJsCounts.length(), 
									String.valueOf(Integer.parseInt(runJsCounts.toString().isEmpty() ? String.valueOf(0) : runJsCounts.toString()) + 1));
						}
					}

					//reading all canvas tag
					List<WebElement> canvasTags = driver.findElements(By.xpath("//canvas[not(contains(@style,'display:none'))]"));
					for(WebElement canvasTag: canvasTags){
						String height = null;
						String width = null;
						if(canvasTag.getAttribute("height") != null && canvasTag.getAttribute("width") != null){
							height = canvasTag.getAttribute("height");
							width = canvasTag.getAttribute("width");
							//calling updateImageList method for updating all info related to image
							updateCanvasList(driver, url, canvasTag, height, width, imageNameWithLandingPage, creativeSize);
						} else if(canvasTag.getAttribute("style") != null){
							if(canvasTag.getCssValue("height") != null && canvasTag.getCssValue("width") != null){
								height = canvasTag.getCssValue("height").replace("px", "");
								width = canvasTag.getCssValue("width").replace("px", "");;
								//calling updateImageList method for updating all info related to image
								updateCanvasList(driver, url, canvasTag, height, width, imageNameWithLandingPage, creativeSize);
							} else{
								height = String.valueOf(canvasTag.getSize().height);
								width = String.valueOf(canvasTag.getSize().width);
								//calling updateImageList method for updating all info related to image
								updateCanvasList(driver, url, canvasTag, height, width, imageNameWithLandingPage, creativeSize);
							}
						}
					}
					
					
		//reading all image tag
				List<WebElement> imageTags = driver.findElements(By.xpath("//img"));
				System.out.println("imageTags\t"+imageTags.size());
				for(WebElement imageTag: imageTags)
				{
					String height = null;
					String width = null;
					if(imageTag.getAttribute("src") != null){
						if(imageTag.getAttribute("height") != null && imageTag.getAttribute("width") != null){
							height = imageTag.getAttribute("height");
							width = imageTag.getAttribute("width");
							//calling updateImageList method for updating all info related to image
							updateImageList(driver, url, imageTag, height, width, image1x1List, imageList, imageNameWithLandingPage, creativeSize);
						} else if(imageTag.getAttribute("style") != null){
							if(imageTag.getCssValue("height") != null && imageTag.getCssValue("width") != null){
								height = imageTag.getCssValue("height").replace("px", "");
								width = imageTag.getCssValue("width").replace("px", "");
								//calling updateImageList method for updating all info related to image
								updateImageList(driver, url, imageTag, height, width, image1x1List, imageList, imageNameWithLandingPage, creativeSize);
							} else{
								height = String.valueOf(imageTag.getSize().height);
								width = String.valueOf(imageTag.getSize().width);
								//calling updateImageList method for updating all info related to image
								updateImageList(driver, url, imageTag, height, width, image1x1List, imageList, imageNameWithLandingPage, creativeSize);
							}
						} else{
							height = String.valueOf(imageTag.getSize().height);
							width = String.valueOf(imageTag.getSize().width);
							//calling updateImageList method for updating all info related to image
							updateImageList(driver, url, imageTag, height, width, image1x1List, imageList, imageNameWithLandingPage, creativeSize);
						}
					}
			}
				
	//reading all video tags
				
				List<WebElement> videoTags = driver.findElements(By.xpath("//video"));
				System.out.println("videoTags"+videoTags.size());
				for(WebElement videoTag: videoTags)
				{
					String height = null;
					String width = null;
					Boolean autoplay,loop;
					if(videoTag.getAttribute("src") != null){
						if(videoTag.getAttribute("height") != null && videoTag.getAttribute("width") != null){
							height = videoTag.getAttribute("height");
							width = videoTag.getAttribute("width");	
							if(videoTag.getAttribute("autoplay") != null)
							{
								autoplay =true;
							}
							else
							{
								autoplay =false;
							}
							if(videoTag.getAttribute("loop") != null)
							{
								loop=true;
								
							}else{
								loop=false;
							}
							
							//calling updateVideoList method for updating all info related to video
							updateVideoList(driver, url, videoTag, height, width, autoplay, loop, videoNameWithLandingPage);
						} 
							
					
					}
					System.out.println("Videotag-Mark HTML 5");
				}
							
	//reading all audio tags
				List<WebElement> AudioTags = driver.findElements(By.xpath("//audio"));
				System.out.println("AudioTags"+AudioTags.size());

				for(WebElement AudioTag: AudioTags)
				{
					String height = null;
					String width = null;
					Boolean autoplay,loop;
					if(AudioTag.getAttribute("src") != null){
						if(AudioTag.getAttribute("height") != null && AudioTag.getAttribute("width") != null){
							height = AudioTag.getAttribute("height");
							width = AudioTag.getAttribute("width");	
							if(AudioTag.getAttribute("autoplay") != null)
							{
								autoplay =true;
							}
							else
							{
								autoplay =false;
							}
							if(AudioTag.getAttribute("loop") != null)
							{
								loop=true;
								
							}else{
								loop=false;
							}
							
							//calling updateAudioList method for updating all info related to audio
							updateAudioList(driver, url, AudioTag, height, width, autoplay, loop,audioNameWithLandingPage);
						} 
							
					
					}
					System.out.println("Videotag-Mark HTML 5");
				}	
				
	//reading all object tags		
				List<WebElement> objectTags = driver.findElements(By.xpath("//object"));
				System.out.println("objectTags"+objectTags.size());
				for(WebElement objectTag: objectTags)
				{
					System.out.println("objectTags-Mark HTML 5");
					String height= objectTag.getAttribute("height");
					String width=objectTag.getAttribute("width");
					
					//Checking wether the object attribute has type,data or archive info	
					
					if(objectTag.getAttribute("type") != null &&objectTag.getAttribute("height")!= null &&objectTag.getAttribute("width")!= null )
					{
						if(objectTag.getAttribute("data")!=null)
						{
							String data=objectTag.getAttribute("data");
							//updating updateObjectList method with all object related attributes
						updateObjectList(driver, data,objectTag, height, width,objectNameWithLandingPage);
						}else if(objectTag.getAttribute("archive")!=null)
						{String archive=objectTag.getAttribute("archive");
						
						//updating updateObjectList method with all object related attributes
							updateObjectList(driver, archive,objectTag, height, width,objectNameWithLandingPage);   // ******************* need to implement updateObjectList method
						}
					}
				}
				//reading all div tag
				List<WebElement> divTags = driver.findElements(By.xpath("//div[not(contains(@style,'display:none'))]"));
				for(WebElement divTag: divTags){
					String height = null;
					String width = null;
					if(divTag.getAttribute("height") != null && divTag.getAttribute("width") != null){
						height = divTag.getAttribute("height");
						width = divTag.getAttribute("width");
						//calling updateImageList method for updating all info related to image
						updateDivList(driver, url, divTag, height, width, imageNameWithLandingPage, creativeSize);
					} else if(divTag.getAttribute("style") != null){
						if(divTag.getCssValue("height") != null && divTag.getCssValue("width") != null){
							height = divTag.getCssValue("height").replace("px", "");
							width = divTag.getCssValue("width").replace("px", "");
							//calling updateImageList method for updating all info related to image
							updateDivList(driver, url, divTag, height, width, imageNameWithLandingPage, creativeSize);
						} else{
							height = String.valueOf(divTag.getSize().height);
							width = String.valueOf(divTag.getSize().width);
							//calling updateImageList method for updating all info related to image
							updateDivList(driver, url, divTag, height, width, imageNameWithLandingPage, creativeSize);
						}

						//checking for href
						if(divTag.getAttribute("href") != null){
							if(divTag.getAttribute("href").toLowerCase().equals("about:blank")){
								continue;
							}
							anchorList.add(divTag.getAttribute("href"));
						}

						//checking for data-href
						if(divTag.getAttribute("data-href") != null){
							if(divTag.getAttribute("href").toLowerCase().equals("about:blank")){
								continue;
							}
							anchorList.add(divTag.getAttribute("data-href"));
						}
					}
				}			
					
				//reading all anchor tag
				List<WebElement> anchorTags = driver.findElements(By.xpath("//a[not(contains(@style,'display:none'))]"));
				for(WebElement anchorTag: anchorTags){
					String height = null;
					String width = null;
					if(anchorTag.getAttribute("height") != null && anchorTag.getAttribute("width") != null){
						height = anchorTag.getAttribute("height");
						width = anchorTag.getAttribute("width");
						//calling updateImageList method for updating all info related to image
						updateAnchorList(driver, url, anchorTag, height, width, imageNameWithLandingPage, creativeSize);
					} else if(anchorTag.getAttribute("style") != null){
						if(anchorTag.getCssValue("height") != null && anchorTag.getCssValue("width") != null){
							height = anchorTag.getCssValue("height").replace("px", "");
							width = anchorTag.getCssValue("width").replace("px", "");
							//calling updateImageList method for updating all info related to image
							updateAnchorList(driver, url, anchorTag, height, width, imageNameWithLandingPage, creativeSize);
						} else{
							height = String.valueOf(anchorTag.getSize().height);
							width = String.valueOf(anchorTag.getSize().width);
							//calling updateImageList method for updating all info related to image
							updateAnchorList(driver, url, anchorTag, height, width, imageNameWithLandingPage, creativeSize);
						}
					} else{
						height = String.valueOf(anchorTag.getSize().height);
						width = String.valueOf(anchorTag.getSize().width);
						//calling updateImageList method for updating all info related to image
						updateAnchorList(driver, url, anchorTag, height, width, imageNameWithLandingPage, creativeSize);
					}

					//checking for href
					if(anchorTag.getAttribute("href") != null){
						if(anchorTag.getAttribute("href").toLowerCase().equals("about:blank")){
							continue;
						}
						anchorList.add(anchorTag.getAttribute("href"));
					}

					//checking for data-href
					if(anchorTag.getAttribute("data-href") != null){
						if(anchorTag.getAttribute("data-href").toLowerCase().equals("about:blank")){
							continue;
						}
						anchorList.add(anchorTag.getAttribute("data-href"));
					}
				}
				
		
				
				
	//reading all embed tags		
				List<WebElement> embedTages = driver.findElements(By.xpath("//embed"));
				System.out.println("embedTages"+objectTags.size());
				for(WebElement embedTage: embedTages)
				{
					System.out.println("objectTags-Mark HTML 5");
					String height= embedTage.getAttribute("height");
					String width=embedTage.getAttribute("width");
							
					if(embedTage.getAttribute("src") != null &&embedTage.getAttribute("height")!= null &&embedTage.getAttribute("width")!= null )
					{
						String src=embedTage.getAttribute("src");
						String type=embedTage.getAttribute("type");
						
						//updating updateEmbedList method with all embed related attributes
						
						updateEmbedList(driver, url , src, embedTage, height, width,embedNameWithLandingPage);

					}
					System.out.println("embedTages-Mark HTML 5");
				}				
				


				//reading and loading iframe tag
				List<WebElement> iFrameTags = driver.findElements(By.xpath("//iframe[not(contains(@style,'display:none'))]"));
				for(WebElement iFrameTag: iFrameTags)
				{
					Thread.sleep(1000);
					if(iFrameTag.getAttribute("src") != null){
						if(iFrameList.contains(iFrameTag.getAttribute("src")) || iFrame1x1List.contains(iFrameTag.getAttribute("src"))){
							continue;
						}
					}
					if(iFrameTag.getAttribute("src") != null){
						if(iFrameTag.getAttribute("height") != null && iFrameTag.getAttribute("width") != null){
							if((iFrameTag.getAttribute("height").equals("1") || iFrameTag.getAttribute("height").equals("1px")) &&
									(iFrameTag.getAttribute("width").equals("1") || iFrameTag.getAttribute("width").equals("1px")) ){
								iFrame1x1List.add(iFrameTag.getAttribute("src"));
							} else{
								iFrameList.add(iFrameTag.getAttribute("src"));
							}
						} else if(iFrameTag.getAttribute("style") != null){
							if(iFrameTag.getCssValue("height") != null && iFrameTag.getCssValue("width") != null){
								String height = iFrameTag.getCssValue("height").replace("px", "");
								String width = iFrameTag.getCssValue("width").replace("px", "");
								if(height.equals("1px") && (width.equals("1px"))){
									iFrame1x1List.add(iFrameTag.getAttribute("src"));
								} else{
									iFrameList.add(iFrameTag.getAttribute("src"));
								}
							} else{
								iFrameList.add(iFrameTag.getAttribute("src"));
							}
						} else{
							iFrameList.add(iFrameTag.getAttribute("src"));
						}

						//
						String iFrameSrc = null;
						try{
							//loading the url
							iFrameSrc = iFrameTag.getAttribute("src");
							driver.get(iFrameSrc);
							//						Thread.sleep(2000);
						} catch(Exception e){
							//ignore
						}
						
						getAdUrlDetails(driver, iFrameSrc, anchorList, imageList, refJsScript, runJsCounts, iFrameList, objectList,
								iFrame1x1List, image1x1List, imageNameWithLandingPage, creativeSize, canvasList, canvas1x1List, 
								videoNameWithLandingPage, embedNameWithLandingPage, objectNameWithLandingPage, audioNameWithLandingPage);
					}
				}
			}catch(Exception e)
			{
			System.out.println(e);
			}
		
			
		}

		private static void updateAnchorList(WebDriver driver, String url, WebElement anchoreTag, String height, String width, Map<Long, String> imageNameWithLandingPage, StringBuffer creativeSize) {
			//checking for 1x1 or 0x0 image
			if(!(height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
				//clicking on image for getting actual landing page
				clickImage(driver, url, anchoreTag, height, width, "anchor" + imageNameWithLandingPage.size() + 1, imageNameWithLandingPage);
			} 

			//storing max image height and width for creative size
			if((Integer.parseInt(creativeSize.toString().split("x")[0]) + Integer.parseInt(creativeSize.toString().split("x")[1])) 
					< (Integer.parseInt(height) + Integer.parseInt(width))){
				//storing max image size to creative size
				creativeSize.delete(0, creativeSize.length());
				creativeSize.append(width.trim() + "x" + height.trim());
			}
		}

		private static void updateDivList(WebDriver driver, String url, WebElement divTag, String height, String width, Map<Long, String> imageNameWithLandingPage, StringBuffer creativeSize) {
			//checking for 1x1 or 0x0 image
			if(!(height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
				//clicking on image for getting actual landing page
				clickImage(driver, url, divTag, height, width, "div" + imageNameWithLandingPage.size() + 1, imageNameWithLandingPage);
			} 

			//storing max image height and width for creative size
			if((Integer.parseInt(creativeSize.toString().split("x")[0]) + Integer.parseInt(creativeSize.toString().split("x")[1])) 
					< (Integer.parseInt(height) + Integer.parseInt(width))){
				//storing max image size to creative size
				creativeSize.delete(0, creativeSize.length());
				creativeSize.append(width.trim() + "x" + height.trim());
			}
		}

			private static void updateCanvasList(WebDriver driver,String url, WebElement canvasTag, String height, String width, Map<Long, String> imageNameWithLandingPage, StringBuffer creativeSize) {
				//checking for 1x1 or 0x0 image
				if(!(height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
					System.out.println("canvas============================================");
					//clicking on image for getting actual landing page
					clickImage(driver, url, canvasTag, height, width, "canvas" + imageNameWithLandingPage.size() + 1, imageNameWithLandingPage);
				} 

				//storing max image height and width for creative size
				if((Integer.parseInt(creativeSize.toString().split("x")[0]) + Integer.parseInt(creativeSize.toString().split("x")[1])) 
						< (Integer.parseInt(height) + Integer.parseInt(width))){
					//storing max image size to creative size
					creativeSize.delete(0, creativeSize.length());
					creativeSize.append(width.trim() + "x" + height.trim());
				}
			}

private static void callFindGif() throws Exception 
{
	System.out.println("imageList"+imageList.size());
	
//Remove duplicate src from the imageList
	    
	Set<String> allHttpLinkList = new LinkedHashSet<>(imageList);
	//Set<String> allHttpLinkList = new HashSet<String>();

		allHttpLinkList.addAll(anchorList);
		allHttpLinkList.addAll(imageList);
		allHttpLinkList.addAll(refJsScript);
		allHttpLinkList.addAll(iFrameList);
	//	allHttpLinkList.addAll(iFrame1x1List);
	//	allHttpLinkList.addAll(image1x1List);
		allHttpLinkList.addAll(videoList);
	//	allHttpLinkList.addAll(video1x1List);
		allHttpLinkList.addAll(objectList);
		allHttpLinkList.addAll(canvasList);
		allHttpLinkList.addAll(embedList);
		allHttpLinkList.addAll(audioList);

		
//Find whether the src contains .gif ext
		
	for(String imgSrc: allHttpLinkList)
	{
		
		imgSrc=imgSrc.toLowerCase();
		System.out.println("imgSrc\t============"+imgSrc);
		if(imgSrc.contains(".gif"))
		{	System.out.println("Its an gif image");
			System.out.println("imgSrc=======>\t"+imgSrc);
//Call findDelay 	
			double delay=(Integer.parseInt(GiffTest.findDelay(imgSrc))*0.001);
			System.out.println("Delay====>"+delay+"sec");
			
		}
		else
		{
			System.out.println("No gif found");
		}
	}
			
	}
private static void updateImageList(WebDriver driver,String url, WebElement imageTag, String height, String width, List<String> image1x1List,
		List<String> imageList, Map<Long, String> imageNameWithLandingPage, StringBuffer creativeSize) {
	//checking for 1x1 or 0x0 image
	if((height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
		//adding 1x1 or 0x0 image src to list
		image1x1List.add(imageTag.getAttribute("src"));
		
	
	} else{
		//adding image src to list except 1x1 or 0x0  
		imageList.add(imageTag.getAttribute("src"));
		//clicking on image for getting actual landing page
		System.out.println("Clicked Img\t"+imageTag.getAttribute("src"));
		clickImage(driver, url, imageTag, height, width, imageTag.getAttribute("src"), imageNameWithLandingPage);
		
	}

	//storing max image height and width for creative size
	if((Integer.parseInt(creativeSize.toString().split("x")[0]) + Integer.parseInt(creativeSize.toString().split("x")[1])) 
			< (Integer.parseInt(height) + Integer.parseInt(width))){
		//storing max image size to creative size
		creativeSize.delete(0, creativeSize.length());
		creativeSize.append(width.trim() + "x" + height.trim());
	}
}
private static void updateVideoList(WebDriver driver, String url, WebElement videoTag, String height, String width, Boolean autoplay, Boolean loop, Map<Long, String> videoNameWithLandingPage2) {
	//checking for 1x1 or 0x0 video
	if((height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
		//adding 1x1 or 0x0 image src to list
		video1x1List.add(videoTag.getAttribute("src"));
		
	
	} else{
		//adding video src to list except 1x1 or 0x0  
		videoList.add(videoTag.getAttribute("src"));
		//clicking on video for getting actual landing page
		System.out.println("Clicked video\t"+videoTag.getAttribute("src"));
		clickVideo(driver, url, videoTag, height, width, autoplay,loop, videoNameWithLandingPage);

	}

}

private static void updateEmbedList(WebDriver driver, String url, String src, WebElement embedTage,
		String height, String width, Map<Long, String> embedNameWithLandingPage2)
{

	//checking for 1x1 or 0x0 embed
		if((height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
			//adding 1x1 or 0x0 embed src to list
			embed1x1List.add(src);
			System.out.println("1 x 1 height of embed \t"+height+"Width \t"+width);
		
		} else{
			//adding image src to list except 1x1 or 0x0  
			embedList.add(src);
			//clicking on embed for getting actual landing page
			System.out.println("Clicked embed \t"+src);
			clickEmbed(driver, url, embedTage, height, width, src, embedNameWithLandingPage);			
		}
}
private static void updateObjectList(WebDriver driver,String url, WebElement objectTag, String height, String width, Map<Long, String> objectNameWithLandingPage2) {
	//checking for 1x1 or 0x0 object
	if((height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
		//adding 1x1 or 0x0 object src to list
		objecttag1x1List.add(url);
	} else{
		//adding object url to list except 1x1 or 0x0  
		objecttagList.add(url);
		//clicking on object for getting actual landing page
		System.out.println("Clicked object\t"+objectTag.getAttribute("src"));
		clickObject(driver, url, objectTag, height, width, objectNameWithLandingPage);
		

	}
	
}
private static void updateAudioList(WebDriver driver,String url, WebElement audioTag, String height,
		String width, Boolean autoplay, Boolean loop, Map<Long, String> audioNameWithLandingPage2) 
{
if((height.equals("1") && (width.equals("1"))) || (height.equals("0") && (width.equals("0")))){
//adding 1x1 or 0x0 audio src to list
audio1x1List.add(audioTag.getAttribute("src"));


} else{
//adding audio src to list except 1x1 or 0x0  
audioList.add(audioTag.getAttribute("src"));
//clicking on image for getting actual landing page
System.out.println("Clicked audio\t"+audioTag.getAttribute("src"));
clickAudio(driver, url, audioTag, height, width, autoplay, loop, audioNameWithLandingPage);
}
}
private static void clickAudio(WebDriver driver, String url, WebElement audioTage, String height,
		String width, Boolean autoplay, Boolean loop, Map<Long, String> audioNameWithLandingPage) {
	String parentWindowHandler = null,src=audioTage.getAttribute("src");
	try{
		//keeping reference of window to which driver is pointing now
		parentWindowHandler = driver.getWindowHandle();
		String str=driver.getCurrentUrl();
		//clicking on image element for getting landing page
		System.out.println("Type of video"+audioTage.getAttribute("type"));
		System.out.println("src of video"+src);
		System.out.println("loop state of video"+loop);
		System.out.println("autoplay state of video"+autoplay);

		audioTage.click();
		//checking for url after clicking whether it is same as parent url or different - different means that will landing page which opened in same window
		if(!Driver.getCurrentUrl().equals(url)){
			//storing data in map with size as key, name and landing page url as value
			audioNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
					src + " @split@ " + driver.getCurrentUrl());
			//switching to parent window
			  
			driver.switchTo().window(parentWindowHandler);
		} else{
			//counting how many window opened
			Set<String> handles = driver.getWindowHandles();
			//if it is more than 1 means it is having other window opened that may be landing page 
			if(handles.size() > 1){
				//iterating all window
				for(String subWindowhandler: driver.getWindowHandles()){
					//getting current url 
					String currentUrl = driver.getCurrentUrl();
					//switching to sub window
					driver.switchTo().window(subWindowhandler);
					//checking both are same or different
					if(!currentUrl.equals(driver.getCurrentUrl())){
						//storing data in map with size as key, name and landing page url as value
						audioNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
								src + " @split@ " + driver.getCurrentUrl());
						
					}
				}driver.close();
				//switching to parent window
				driver.switchTo().window(parentWindowHandler);
			}
		}
	} catch(Exception e){
		System.out.println(e);
		driver.switchTo().window(parentWindowHandler);
	}
}
private static void clickObject(WebDriver driver,String url, WebElement objectTag, String height,
		String width, Map<Long, String> objectNameWithLandingPage) {

	
	String parentWindowHandler = null;
	try{
		//keeping reference of window to which driver is pointing now
		parentWindowHandler = driver.getWindowHandle();
		String str=driver.getCurrentUrl();
		String src=objectTag.getAttribute("src");
		//clicking on image element for getting landing page
		System.out.println("Type of video"+objectTag.getAttribute("type"));


		objectTag.click();
		//checking for url after clicking whether it is same as parent url or different - different means that will landing page which opened in same window
		if(!Driver.getCurrentUrl().equals(url)){
			//storing data in map with size as key, name and landing page url as value
			objectNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
					src + " @split@ " + driver.getCurrentUrl());
			//switching to parent window
			  
			driver.switchTo().window(parentWindowHandler);
		} else{
			//counting how many window opened
			Set<String> handles = driver.getWindowHandles();
			//if it is more than 1 means it is having other window opened that may be landing page 
			if(handles.size() > 1){
				//iterating all window
				for(String subWindowhandler: driver.getWindowHandles()){
					//getting current url 
					String currentUrl = driver.getCurrentUrl();
					//switching to sub window
					driver.switchTo().window(subWindowhandler);
					//checking both are same or different
					if(!currentUrl.equals(driver.getCurrentUrl())){
						//storing data in map with size as key, name and landing page url as value
						objectNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
								src + " @split@ " + driver.getCurrentUrl());
						
					}
				
				}	//switching to parent window
				driver.close();
				driver.switchTo().window(parentWindowHandler);
			}
		}
	} catch(Exception e){
		System.out.println(e);
		driver.switchTo().window(parentWindowHandler);
	}
}
private static void clickVideo(WebDriver driver, String url, WebElement videoTage, String height,
		String width, Boolean autoplay, Boolean loop,Map<Long, String> videoNameWithLandingPage) {

	
	String parentWindowHandler = null,src=videoTage.getAttribute("src");
	try{
		//keeping reference of window to which driver is pointing now
		parentWindowHandler = driver.getWindowHandle();
		String str=driver.getCurrentUrl();
		//clicking on image element for getting landing page
		System.out.println("Type of video"+videoTage.getAttribute("type"));
		System.out.println("src of video"+src);
		System.out.println("loop state of video"+loop);
		System.out.println("autoplay state of video"+autoplay);

		videoTage.click();
		//checking for url after clicking whether it is same as parent url or different - different means that will landing page which opened in same window
		if(!Driver.getCurrentUrl().equals(url)){
			//storing data in map with size as key, name and landing page url as value
			videoNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
					src + " @split@ " + driver.getCurrentUrl());
			//switching to parent window
			  
			driver.switchTo().window(parentWindowHandler);
		} else{
			//counting how many window opened
			Set<String> handles = driver.getWindowHandles();
			//if it is more than 1 means it is having other window opened that may be landing page 
			if(handles.size() > 1){
				//iterating all window
				for(String subWindowhandler: driver.getWindowHandles()){
					//getting current url 
					String currentUrl = driver.getCurrentUrl();
					//switching to sub window
					driver.switchTo().window(subWindowhandler);
					//checking both are same or different
					if(!currentUrl.equals(driver.getCurrentUrl())){
						//storing data in map with size as key, name and landing page url as value
						videoNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
								src + " @split@ " + driver.getCurrentUrl());
						
					}
					//switching to parent window
				
				}	//switching to parent window
				driver.close();
				driver.switchTo().window(parentWindowHandler);
			}
		}
	} catch(Exception e){
		System.out.println(e);
		driver.switchTo().window(parentWindowHandler);
	}
}
private static void clickEmbed(WebDriver driver, String url, WebElement embedTage, String height,
		String width, String src, Map<Long, String> embedNameWithLandingPage) {

	
	String parentWindowHandler = null;
	try{
		//keeping reference of window to which driver is pointing now
		parentWindowHandler = driver.getWindowHandle();
		String str=driver.getCurrentUrl();
		
		//clicking on image element for getting landing page
		System.out.println("Type of embed"+embedTage.getAttribute("type"));
		System.out.println("src of embed"+src);
		
		embedTage.click();
		//checking for url after clicking whether it is same as parent url or different - different means that will landing page which opened in same window
		if(!Driver.getCurrentUrl().equals(url)){
			//storing data in map with size as key, name and landing page url as value
			embedNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
					src + " @split@ " + driver.getCurrentUrl());
			//switching to parent window
			  
			driver.switchTo().window(parentWindowHandler);
		} else{
			//counting how many window opened
			Set<String> handles = driver.getWindowHandles();
			//if it is more than 1 means it is having other window opened that may be landing page 
			if(handles.size() > 1){
				//iterating all window
				for(String subWindowhandler: driver.getWindowHandles()){
					//getting current url 
					String currentUrl = driver.getCurrentUrl();
					//switching to sub window
					driver.switchTo().window(subWindowhandler);
					//checking both are same or different
					if(!currentUrl.equals(driver.getCurrentUrl())){
						//storing data in map with size as key, name and landing page url as value
						embedNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
								src + " @split@ " + driver.getCurrentUrl());
				
					}
					//switching to parent window
					
				}	//switching to parent window
				driver.close();
				driver.switchTo().window(parentWindowHandler);
			}
		}
	} catch(Exception e){
		System.out.println(e);
		driver.switchTo().window(parentWindowHandler);
	}
}

private static void clickImage(WebDriver driver, String url, WebElement element, String height, String width, String src, Map<Long, String> imageNameWithLandingPage)
{

	String parentWindowHandler = null;
	try{
		//keeping reference of window to which driver is pointing now
		parentWindowHandler = driver.getWindowHandle();
		String BeforeClick=driver.getCurrentUrl();
		
		
		//clicking on image element for getting landing page
			element.click();
			String AfterClick=driver.getCurrentUrl();
		
			
		//checking for url after clicking whether it is same as parent url or different - different means that will landing page which opened in same window
		if(!BeforeClick.equals(AfterClick)){
			//storing data in map with size as key, name and landing page url as value
			imageNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
					src + " @split@ " + driver.getCurrentUrl());
			
			System.out.println("If block src " + src +"::" + driver.getCurrentUrl());
	
			//switching to parent window
			  
			
			driver.switchTo().window(parentWindowHandler);
		} else{
			//counting how many window opened
			Set<String> handles = driver.getWindowHandles();
			//if it is more than 1 means it is having other window opened that may be landing page 
			if(handles.size() > 1){
				//iterating all window
				for(String subWindowhandler: driver.getWindowHandles()){
					//getting current url 
					String currentUrl = driver.getCurrentUrl();
					//switching to sub window
					driver.switchTo().window(subWindowhandler);
					//checking both are same or different
					if(!currentUrl.equals(driver.getCurrentUrl())){
						//storing data in map with size as key, name and landing page url as value
						imageNameWithLandingPage.put(Long.parseLong(height) + Long.parseLong(width),
								src + " @split@ " + driver.getCurrentUrl());
						System.out.println("else block=== src " + src +"::" + driver.getCurrentUrl());
					
					}
					
				}	//switching to parent window
				driver.close();
				driver.switchTo().window(parentWindowHandler);
			}
		}
	}
	catch(WebDriverException e){
		System.out.println("image here"+e);
		//driver.switchTo().window(parentWindowHandler);
	} catch(Exception e){
		System.out.println("image here"+e);
		driver.switchTo().window(parentWindowHandler);
	}
}

}
