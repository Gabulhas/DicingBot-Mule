package gab.connectionHandler;

import gab.Main;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintStream;

public class sendOut implements Runnable {


    private static String messageToSend;
    private static PrintStream saida;
    private static String request="";

    static {
        try {
            saida = new PrintStream(client.client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public sendOut(String req) throws IOException {

        request=req;


    }

    public static void MuleRequest(){




            JSONObject obj = new JSONObject();




            obj.put("type","MuleRequest");
            obj.put("World",0);
            obj.put("Username", Main.username);
            obj.put("Profit",0);

            messageToSend=obj.toJSONString();
            saida.println(messageToSend);




    }


    @Override
    public void run() {
        switch (request){
            case "MuleRequest":
                MuleRequest();


                break;




        }

    }
}
