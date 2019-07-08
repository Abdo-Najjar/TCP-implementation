package SimpleSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


public class SerSocket {

    static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        System.out.println("Server Start");
        ServerSocket ss = new ServerSocket(9999);
        while(true){
       Handle h= new Handle(ss.accept());
    }}

    private static class Handle implements Runnable {

        PrintWriter out;
        Socket s;
        BufferedReader in;
        String name;

        public Handle(Socket s) {
            this.s = s;
            Thread t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                writers.add(out);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            for (;;) {
                try {
                    String input = in.readLine();
                    if (input == null || input.equals("")) {
                        return;
                    }
                    writers.forEach((PrintWriter t) -> {
                        t.write(input);
                    });
                } catch (IOException ex) {
                    System.out.println(ex);
                } finally {
                    if (out != null) {
                        writers.remove(out);
                    }
                    try {
                        s.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }

    }

}
