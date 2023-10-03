package WebScraping;

import WebScraping.JSoup;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Handler;

public class Main {
    private String url;
    public static JSoup jSoup;
    public static ArrayList<String> jSoups = new ArrayList<>();
    private static String value = JOptionPane.showInputDialog(null, "Please Enter the url:");

    public static void main(String[] args) throws IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            int[] index = new int[2];
            InputStream input = new FileInputStream("C:\\Users\\DELL\\IdeaProjects\\CSC\\src\\main\\java\\WebScraper\\WebScraper\\URL.xml");
            //Creates a SAX parser factory and parser
            SAXParser SaxParser = spf.newSAXParser();
            //parse the file
            URL sxp = new URL();
            SaxParser.parse(input, sxp);
            jSoups.add(value);
            for (String url : sxp.getUrls()) {
                if(url!=null){
                    jSoups.add(url);
                }
            }
            jSoup = new JSoup(jSoups);
            jSoup.getDocument();
            index = jSoup.value;
            for(int i=0; i < index.length; i++){
                System.out.println(sxp.getUrls()[index[i]]);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}



