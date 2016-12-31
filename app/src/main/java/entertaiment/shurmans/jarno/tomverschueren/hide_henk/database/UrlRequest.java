package entertaiment.shurmans.jarno.tomverschueren.hide_henk.database;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public class UrlRequest {



    String url ="";

    UrlRequest(String url){
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public String createTables(){
        return "http://api.a16_sd507.studev.groept.be/createtables";

    }

    public String getID(String name){
        return "http://api.a16_sd507.studev.groept.be/getid/" + name;
    }

    public String insertLevel(String name, String stringID){
        return "http://api.a16_sd507.studev.groept.be/insertonlinegame/"+name+ "/"+ stringID;
    }



}
