import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;

public class GiffTest {

    /**
     * @param args the command line arguments
     * @throws Exception 
     * @throws FileNotFoundException 
     */
	public static String findDelay(String src) throws FileNotFoundException, Exception
    {
		InputStream is;
		is = new URL(src).openStream();
        ImageReader reader = ImageIO.getImageReadersBySuffix("gif").next();
        reader.setInput(ImageIO.createImageInputStream(is));
        int i = reader.getMinIndex();
        int numImages = reader.getNumImages(true);
        IIOMetadata imageMetaData =  reader.getImageMetadata(0);
        String metaFormatName = imageMetaData.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode)imageMetaData.getAsTree(metaFormatName);
        IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");
        System.out.println(graphicsControlExtensionNode.getAttribute("delayTime"));
        String delay=graphicsControlExtensionNode.getAttribute("delayTime");
        return(delay);
    }

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) 
        {
            if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName)== 0) {
            return((IIOMetadataNode) rootNode.item(i));
            }
       }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return(node);
  }
}


