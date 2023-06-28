package network;

import controller.Controller;
import controller.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class ClientHandler extends Thread {
    private Socket socket;

    public static User user = new User("admin", "admin", "admin", "admin", "admin", "admin", 0);


    ClientHandler(Socket socket) {this.socket = socket;}


    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            StringBuilder request = new StringBuilder();
            int c = dis.read();
            while(c != 0) {
                request.append((char) c);
                c = dis.read();
            }
            Scanner sc = new Scanner(request.toString());
            String command = sc.nextLine();
            String data = sc.nextLine();


            String response = new Controller().start(command, data);
            dos.writeBytes(response);
            dos.flush();
            dos.close();
            dis.close();
            socket.close();


        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
