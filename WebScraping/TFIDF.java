package WebScraping;


public class TFIDF {
    int[] value = new int[2];
    double[][] doubletf ;
    double[] doubleidf;
    int m = 0;
    double[] cosine;
    public TFIDF(double[][] doubletf,double[] doubleidf){
        this.doubletf = doubletf;
        this.doubleidf = doubleidf;
        cosine = new double[doubletf[0].length-1];
    }
    public void calculateTFIDF(){
        for (int i = 0; i < doubletf.length; ++i) {
            for(int j = 0; j < doubletf[0].length; ++j){
                doubletf[i][j] = doubletf[i][j] * doubleidf[i];
            }
        }
        int l =1;
        while(l < doubletf[1].length) {
            double a= 0 , b = 0 , c= 0;
            for (int k = 0; k < doubletf.length; ++k) {
                a += (calculateA(doubletf[k][0], doubletf[k][l]));
                b += calculateB(doubletf[k][0]);
                c += calculateB(doubletf[k][l]);
            }
            b = Math.sqrt(b);
            c = Math.sqrt(c);
            if(a == 0) {
                cosine[m] = 0;
                m += 1;
                l+=1;
            }else {
                cosine[m] = (double) ((a) / (double) (b * c));
                m += 1;
                l += 1;
            }
        }
        double a =cosine[0];
        int b = 0;
        for(int i = 1; i < cosine.length; ++i){
            if(a < cosine[i]){
                a = cosine[i];
                b = i;
            }
        }
        value[0] = b;
        cosine[b]=0;
        int c = 0;
        a = cosine[0];
        for (int j = 0; j < cosine.length; j++) {
            if (a < cosine[j]) {
                a = cosine[j];
                c = j;
            }
        }
        value[1] = c;
    }

    public double calculateA(double A ,double B){
        return A * B;
    }
    public double calculateB(double A){
        return A;
    }
}
