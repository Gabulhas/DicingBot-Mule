package gab.connectionHandler;

import org.dreambot.api.methods.MethodProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStream;
import java.util.Scanner;

public class Receiver implements Runnable {
    private InputStream server;

    public Receiver(InputStream server){

        this.server=server;
    }



    @Override
    public void run() {
        synchronized (System.out){
        Scanner s = new Scanner(this.server);
        while(s.hasNextLine()){

            String temp=s.nextLine();

            System.out.println("Test "+temp.length());
            System.out.println(temp);

            MethodProvider.log(s.nextLine());

            JSONParser parser = new JSONParser();
            try {

                JSONObject j = (JSONObject) parser.parse(s.next());
                messageTypeHandler.on((String)j.get("type"),j);
            } catch (ParseException e) {
               e.printStackTrace();
            }

        }


}
    }
}
