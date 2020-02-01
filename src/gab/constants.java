package gab;



import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class constants {
    private  int profitForMule =0;
    public static  Tile geTile = new Tile(3165,3483);
    private int odds = 55;
    private int bottomLimit = 2000;
    private int upperLimit= 120000;
    private int fakeOdds=55;
    private int alwaysLoseMin=30000;
    private int alwaysWinMax=9999;
    private int alwaysLoseOdds=70;
    public static String mule= "Later Stage";

    public String getMule() {
        return mule;
    }

    public void setMule(String mule) {
        this.mule = mule;
    }

    public int getProfitForMule() {
        return profitForMule;
    }

    public constants() {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("constants.txt")), "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            MethodProvider.log(e.toString());
        }
        String allCs[]=content.split("\\r?\\n");
        ArrayList<String> allCs2=new ArrayList<>();
        for (String m:
             allCs) {
            String temp[]=m.split("=");
            allCs2.add(temp[1]);

        }
        this.geTile = new Tile(Integer.parseInt(allCs2.get(0)),Integer.parseInt(allCs2.get(1)));
        this.odds = Integer.parseInt(allCs2.get(2));
        this.bottomLimit = Integer.parseInt(allCs2.get(3));
        this.upperLimit= Integer.parseInt(allCs2.get(4));
        this.fakeOdds=Integer.parseInt(allCs2.get(5));
        this.alwaysLoseMin=Integer.parseInt(allCs2.get(6));
        this.alwaysWinMax=Integer.parseInt(allCs2.get(7));
        this.alwaysLoseOdds=Integer.parseInt(allCs2.get(8));
        this.mule= allCs2.get(9);
        this.profitForMule=Integer.parseInt(allCs2.get(10));


    }







    public int getAlwaysLoseOdds() {
        return alwaysLoseOdds;
    }

    public void setAlwaysLoseOdds(int alwaysLoseOdds) {
        this.alwaysLoseOdds = alwaysLoseOdds;
    }

    public int getAlwaysLoseMin() {
        return alwaysLoseMin;
    }

    public void setAlwaysLoseMin(int alwaysLoseMin) {
        this.alwaysLoseMin = alwaysLoseMin;
    }

    public int getAlwaysWinMax() {
        return alwaysWinMax;
    }

    public void setAlwaysWinMax(int alwaysWinMax) {
        this.alwaysWinMax = alwaysWinMax;
    }

    public int getFakeOdds() {
        return fakeOdds;
    }

    public void setFakeOdds(int fakeOdds) {
        this.fakeOdds = fakeOdds;
    }

    public int getOdds() {
        return odds;
    }

    public void setOdds(int odds) {
        this.odds = odds;
    }

    public int getBottomLimit() {
        return bottomLimit;
    }

    public void setBottomLimit(int bottomLimit) {
        this.bottomLimit = bottomLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void setGeTile(Tile geTile) {
        this.geTile = geTile;
    }

    public Tile getGeTile() {
        return geTile;
    }


}
