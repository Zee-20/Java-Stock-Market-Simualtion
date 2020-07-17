package rts;

//start program
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

class Stocks implements Runnable {

    public String name;
    public double initialize;
    public int High = 20;
    public int Low = 10;
       List<Stocks> Slist = new ArrayList<>();

    Stocks() throws IOException {
        Stocks S1 = new Stocks("aap", 15.4);
        Stocks S2 = new Stocks("mcc", 12.76);
        Stocks S3 = new Stocks("orc", 17.3);
        Stocks S4 = new Stocks("thr", 11.89);
        Stocks S5 = new Stocks("amz", 16.23);
        Stocks S6 = new Stocks("eby", 14.42);
        Stocks S7 = new Stocks("onz", 18.43);
        Stocks S8 = new Stocks("ira", 19.56);
        Stocks S9 = new Stocks("ipo", 17.12);
        Stocks S10 = new Stocks("etf", 10.65);
        Stocks S11 = new Stocks("apr", 13.4);
        Stocks S12 = new Stocks("tsa", 15.21);
        Stocks S13 = new Stocks("psp", 18.3);
        Stocks S14 = new Stocks("nav", 10.89);
        Stocks S15 = new Stocks("loi", 11.23);
        Stocks S16 = new Stocks("gnp", 17.42);
        Stocks S17 = new Stocks("frb", 13.43);
        Stocks S18 = new Stocks("eft", 13.56);
        Stocks S19 = new Stocks("arm", 17.58);
        Stocks S20 = new Stocks("fdi", 15.34);

        
        Slist.add(S1);
        Slist.add(S2);
        Slist.add(S3);
        Slist.add(S4);
        Slist.add(S5);
        Slist.add(S6);
        Slist.add(S7);
        Slist.add(S8);
        Slist.add(S9);
        Slist.add(S10);
        Slist.add(S11);
        Slist.add(S12);
        Slist.add(S13);
        Slist.add(S14);
        Slist.add(S15);
        Slist.add(S16);
        Slist.add(S17);
        Slist.add(S18);
        Slist.add(S19);
        Slist.add(S20);

        

    }

    Stocks(String n, double ini) {
        name = n;
        initialize = ini;
    }
    
    
    
    

    @Override
    public synchronized void run() {
        Random R = new Random();
        List<Stocks> S2 = new ArrayList<>();
        S2.addAll(Slist);
        
     for(Stocks s: S2)
        {       
        int c = R.nextInt(2);
        if(c == 0)                
        {
        s.initialize++;
        System.out.println(s.name+ "  "+s.initialize);
        }
        else{
        s.initialize--;        
        System.out.println(s.name+ "  "+s.initialize);
        }   
        
        if(s.initialize>=High)
        {    
            System.out.println("Bought! "+s.name +" for "+s.initialize);
            try {
            BufferedWriter out = new BufferedWriter( 
            new FileWriter("Stocks.txt", true)); 
            out.write("Bought! "+s.name +" for "+s.initialize+"\n"); 
            out.close(); 
                
             } catch (IOException ex) {
                Logger.getLogger(Stocks.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i=0;
        }
        else if(s.initialize<=Low)  
        {
            System.out.println("Sold! "+s.name+" for "+s.initialize );
            try {
                
                 BufferedWriter out = new BufferedWriter( 
            new FileWriter("Stocks.txt", true)); 
            out.write("Sold! "+s.name +" for "+s.initialize+"\n"); 
            out.close(); 
                
            } catch (IOException ex) {
                Logger.getLogger(Stocks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        }
       
    }
}





//create class
public class StockPrg{

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        Stocks S = new Stocks();

        while (!S.Slist.isEmpty()) {
            
            executor.schedule(S, 500, TimeUnit.MILLISECONDS);

            try {
                executor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           }

    }

}
