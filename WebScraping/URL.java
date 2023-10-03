package WebScraping;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URL extends DefaultHandler {
    String urls[]= new String[10];
    String name;
    int i =0;
    public void startElement(String uri, String localName, String qName, Attributes att){
        if(qName.equals("URL")){
            name = att.getValue("url");
        }
    }
    public void endElement(String uri, String localName, String qName){
        if(qName.equals("URL")){
            if(name!=null) {
                urls[i] = name;
                i++;
            }
        }
    }
    public String[] getUrls(){
        return urls;
    }

//    public static void main(String[] args) {
//        URL urls = new URL();
//        for(String url:urls.getUrls()) {
//
//        }
//    }
}

