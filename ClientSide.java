package SimpleSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String ip = "localhost";
        int port = 9999;
        Socket s = new Socket(ip, port);
        System.out.print("Me :");
        String a = in.nextLine();
        PrintWriter pr = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        pr.println(a);
        pr.flush();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String message = br.readLine();
        System.out.println("S: "+message);

    }

}
