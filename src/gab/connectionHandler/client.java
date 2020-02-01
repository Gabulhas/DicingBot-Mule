package gab.connectionHandler;

import gab.Main;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class client implements Runnable{
    public static Main main;
    public static Socket client;


    @Override
    public void run() {
        client=null;
        try {
            client = new Socket("127.0.0.1", 3500);

        }
        catch (IOException e){
            System.out.println("Couldn't connect to main server");

        }
        assert client != null;
        Receiver r = null;
        try {
            r = new Receiver(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(r).start();


        Scanner teclado = new Scanner(System.in);
        PrintStream saida = null;
        try {
            saida = new PrintStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject();




        obj.put("type","Login");
        obj.put("World", 0);
        obj.put("Username", Main.username);
        obj.put("BotType","Mule");

        assert saida != null;
        saida.println(obj.toJSONString());

        r.run();
        saida.close();
        teclado.close();

    }
}
