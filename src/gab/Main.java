package gab;

import gab.connectionHandler.client;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.Player;

import java.awt.*;
import java.util.concurrent.TimeUnit;


@ScriptManifest(
        author = "gabulhas",
        name="molto dicing bot mule",
        description = "Just muling lmao",
        category = Category.MONEYMAKING,
        version = 0.01



)

public class Main extends AbstractScript {

    private long timeRan;
    public static int botWorld=0;
    public static String botToGet;
    public static String username;
    public long timeBegan= System.currentTimeMillis();
    public int StartingGP=0;
    private int NowGP;
    public gameStates.CSTATES currentState= gameStates.CSTATES.FINDING_BOT;
    public boolean onStartFlag = true;


    @Override
    public void onStart(String... strings) {
        botToGet="";
        botWorld=0;

        try{
            StartingGP = getInventory().get("Coins").getAmount();


        }
        catch (NullPointerException e){

            StartingGP=0;
        }



        super.onStart(strings);
    }


    public synchronized static void goFind(int i, String user){

        botWorld=i;
        botToGet=user;



    }


    @Override
    public int onLoop() {
        if(onStartFlag&&currentState.equals(gameStates.CSTATES.THINKING)){
            onStartFlag=false;
            username=getLocalPlayer().getName();

            log("Trying to connect to server");

            client c = new client();


          new Thread(c).start();



        }
        if(!getLocalPlayer().getTile().equals(constants.geTile)){


            getWalking().walkExact(constants.geTile);
        }
        switch (currentState){


            case FINDING_BOT:
                if(getWorlds().getMyWorld().getWorld()!=botWorld&&botWorld!=0){
                    getWorldHopper().hopWorld(botWorld);


                }

                boolean flagTemp= false;
                Player tempPlayer;
                for(Player d : getPlayers().all()){

                    if(d.getName().equals(botToGet)){
                        flagTemp=true;

                    }
                }
                if(flagTemp){
                    getTrade().tradeWithPlayer(botToGet);
                    currentState= gameStates.CSTATES.TAKING;


                }


                break;
            case TAKING:
                break;
            case THINKING:

                Condition c = new Condition() {
                    @Override
                    public boolean verify() {
                        return botWorld!=0;
                    }
                };
                MethodProvider.sleepUntil(c,21470000);
                break;
            case WAITING:
                break;
        }


        return 1000;
    }

    @Override
    public void onPaint(Graphics g) {


        timeRan = System.currentTimeMillis() - timeBegan;

        try {
            NowGP = getInventory().get("Coins").getAmount();

        }
        catch (NullPointerException e){
            NowGP=0;
        }
        int profit = (NowGP - StartingGP);
        g.drawString("Status: " + currentState + "      Time Ran: " + ft(timeRan) + "     Profit: " + profit, 8, 325);
        g.drawString("Looking for: " + botToGet + "     Bot World: "+botWorld, 8, 340);
        // g.drawString(ft(timeRan), 20, 450)
        super.onPaint(g);
    }
    private String ft(long duration)

    {

        String res = "";

        long days = TimeUnit.MILLISECONDS.toDays(duration);

        long hours = TimeUnit.MILLISECONDS.toHours(duration)

                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));

        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)

                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS

                .toHours(duration));

        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)

                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS

                .toMinutes(duration));

        if (days == 0) {

            res = (hours + ":" + minutes + ":" + seconds);

        } else {

            res = (days + ":" + hours + ":" + minutes + ":" + seconds);

        }

        return res;

    }
}
