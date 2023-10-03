package WebScraping;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class JSoup {
    int[] value = new int[2];
    bagOfWords ar;
    ArrayList<String> urls= new ArrayList<>();
    ArrayList<String> documents = new ArrayList<>();
    HT Ht;

    public JSoup(ArrayList<String> urls) {
        this.urls=urls;
    }
    public void getDocument() throws IOException {
        for (String url: urls){
            Document doc =  Jsoup.connect(url).get();
            String pageText = doc.text();
            pageText = pageText.trim().toLowerCase();
            documents.add(pageText);}
        ar = new bagOfWords(documents);
        ar.getBagOfWord();
        value = ar.value;
    }

    public static void main(String[] args) throws IOException {
        // WebScraper.JSoup jSoup = new WebScraper.JSoup("https://stackoverflow.com/questions/9261492/dynamically-adding-more-values-to-an-arraylist-in-java");
        //jSoup.getDocument();
    }

//            List<String> notUsed = new ArrayList<>(Arrays.asList("this","that","is","not","for","has","are","=","to","a","you","the","of","into"));
//            List<String> value = new ArrayList<>();
//            List<String> store = new ArrayList<>();



//               if (!notUsed.contains(st)) {
//                   value.add(st);
//               }
//           }
//            Map<String, Integer> words = new HashMap<>();
//            for (String str : value) {
//                if (words.containsKey(str)) {
//                    words.put(str, 1 + words.get(str));
//                } else {
//                    words.put(str, 1);
//                }
//            }

}








