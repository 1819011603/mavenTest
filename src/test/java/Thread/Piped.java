package Thread;

import sun.print.PrintJob2D;

import java.io.*;
import java.util.Scanner;

public class Piped {
    public static void main(String[] args) throws IOException {
        PipedReader in  = new PipedReader();
        PipedWriter out  = new PipedWriter();
        out.connect(in);
        Thread myThread = new Thread(new Print(in));
        myThread.start();
        int w = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((w = reader.read())!=-1){
            out.write(w);
        }
        out.close();
    }
    private static class Print implements Runnable{
        private PipedReader in;
        public Print(PipedReader in){
            this.in = in;
        }
        @Override
        public void run() {
            int w = 0;

            while (true){
                try {
                    if ((w = in.read())!=-1) {
                        System.out.print((char) w);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
