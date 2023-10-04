package WebScraping;
import java.util.ArrayList;
import java.util.Scanner;

public class bagOfWords {
    int[] value = new int[2];
    ArrayList<String> documents;
    ArrayList<String> bagOfWord = new ArrayList<>();
    TFIDF tfStorage;
    HT ht;
    ArrayList<HT> hashtables=new ArrayList<>();
    double doubletf[][];
    double doubleidf[];
    public bagOfWords(ArrayList<String> documents){
        this.documents=documents;
    }
    public void getBagOfWord(){
        for (String document: documents){
            ht = new HT();
            Scanner scanner = new Scanner(document);
            while (scanner.hasNext()){
                ht.add(scanner.next());
            }
            hashtables.add(ht);
        }
        ht =new HT();
        for (int i=0;i<hashtables.size()-1;i++){
            ht.addAll(hashtables.get(i));
        }

        bagOfWord=ht.printAll();
        doubletf = new double[bagOfWord.size()][hashtables.size()];
        for (int j = 0 ;j < bagOfWord.size(); ++j) {
            for (int i = 0; i < hashtables.size();++i) {
                doubletf[j][i] = TF(bagOfWord.get(j), hashtables.get(i));
            }
        }
        doubleidf = new double[bagOfWord.size()];
        for(int k = 0; k < bagOfWord.size(); ++k){
            doubleidf[k] = IDF(bagOfWord.get(k), hashtables);
        }
        tfStorage = new TFIDF(doubletf,doubleidf);
        tfStorage.calculateTFIDF();
        value = tfStorage.value;

    }
    public double TF(String value, HT hashtable) {
        int size = hashtable.getTotalsize();
        int frequency = hashtable.find(value);
        return (double) frequency / size;
    }

    public double IDF(String value,ArrayList<HT> hts){
        int x = 0;
        for(int l = 0; l< hts.size(); l++) {
            if(hts.get(l).contains(value)){
                x += 1;
            }
        }
        return  (double) (Math.log(hts.size()/x));
    }

}


