package gab.connectionHandler;

import gab.Main;
import org.json.simple.JSONObject;

public class messageTypeHandler {



    public static  void on(String type, JSONObject msg) {




        switch (type){
            case "MuleRequest":

               // Main.goFind((int)msg.get("World"),(String)msg.get("Username"));





                break;



        }




    }
}
