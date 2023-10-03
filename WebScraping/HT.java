package WebScraping;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HT implements java.io.Serializable {
    String document;
    int totalsize = 0;
    ArrayList<String> arr=new ArrayList<>();
    //                Scanner sc = new Scanner(document);
//           while(sc.hasNext()) {
//               String st = sc.next();
    static final class Node {
        String key;
        Node next;
        int frequency;
        // Object value;
        Node(String k, Node n) {
            key = k;
            next = n;
            frequency = 0;
        }
    }
    Node[] table = new Node[8]; // always a power of 2
    int size = 0;

    public int find(String key) {
        int h = key.hashCode();
        int i = h & (table.length - 1);
        for (Node e = table[i]; e != null; e = e.next) {
            if (key.equals(e.key))
                return e.frequency;
        }
        return 0;
    }

    public boolean contains(String key){
        int h = key.hashCode();
        int i = h & (table.length - 1);
        for(Node e = table[i]; e != null; e = e.next){
            if(key.equals(e.key))
                return true;
        }
        return false;
    }

    void add(String key) {
        totalsize++;
        int h = key.hashCode();
        int i = h & (table.length - 1);
        for (Node e = table[i]; e != null; e = e.next) {
            if (key.equals(e.key)){
                e.frequency ++ ;
                return;}
        }
        table[i] = new Node(key, table[i]);
        table[i].frequency=1;
        ++size;
        if ((float)size/table.length >= 0.75f)
            resize();
    }
    void add1(String key, int freq) {
        totalsize+= freq;
        int h = key.hashCode();
        int i = h & (table.length - 1);
        for (Node e = table[i]; e != null; e = e.next) {
            if (key.equals(e.key)){
                e.frequency += freq ;
                return;}
        }
        table[i] = new Node(key, table[i]);
        table[i].frequency=1;
        ++size;
        if ((float)size/table.length >= 0.75f)
            resize();
    }

    void resize() {
        Node[] oldTable = table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity << 1;
        Node[] newTable = new Node[newCapacity];
        for (int i = 0; i < oldCapacity; ++i) {
            for (Node e = oldTable[i]; e != null; e = e.next) {
                int h = e.key.hashCode();
                int j = h & (newTable.length - 1);
                newTable[j] = new Node(e.key, newTable[j]);
                newTable[j].frequency = e.frequency;
            }
        }
        table = newTable;
    }
    void resizeV2() { // avoids unnecessary creation
        Node[] oldTable = table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity << 1;
        Node[] newTable = new Node[newCapacity];
        for (int i = 0; i < oldCapacity; ++i) {
            Node e = oldTable[i];
            while (e != null) {
                Node next = e.next;
                int h = e.key.hashCode();
                int j = h & (newTable.length - 1);
                e.next = newTable[j];
                newTable[j] = e;
                e = next;
            }
        }
        table = newTable;
    }
    void remove(String key) {
        int h = key.hashCode();
        int i = h & (table.length - 1);
        Node e = table[i], p = null;
        while (e != null) {
            if (key.equals(e.key)) {
                if (p == null)
                    table[i] = e.next;
                else
                    p.next = e.next;
                break;
            }
            p = e;
            e = e.next;
        }
    }
    public ArrayList<String> printAll() {
        for (int i = 0; i < table.length; ++i)
            for (Node e = table[i]; e != null; e = e.next){
                arr.add(e.key);}
        return arr;
    }
    private void writeObject(ObjectOutputStream s) throws Exception {
        s.defaultWriteObject();
        s.writeInt(size);
        for (int i = 0; i < table.length; ++i) {
            for (Node e = table[i]; e != null; e = e.next) {
                s.writeObject(e.key);
            }
        }
    }
//    private void readObject(String s) throws Exception {
//        s.defaultReadObject();
//        int n = s.readInt();
//        for (int i = 0; i < n; ++i)
//            add(s.readObject());
//    }

    void addAll(HT b) {
        for(int i=0; i< b.table.length; i++){
            for(Node e = b.table[i]; e != null; e = e.next ){
                this.add1(e.key, e.frequency);
            }
        }
    }

    public int getTotalsize(){
        return totalsize;
    }

    public static void main(String[] args) {

    }
}


