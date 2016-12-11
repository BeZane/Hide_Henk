package entertaiment.shurmans.jarno.tomverschueren.hide_henk.database;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public enum UrlRequest {

    SELECT_LEVEL("http://api.a16_sd507.studev.groept.be/selectlevel");


    String url;

    UrlRequest(String url){
        this.url = url;

    }

    public String getUrl() {
        return url;
    }



}
