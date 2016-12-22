package entertaiment.shurmans.jarno.tomverschueren.hide_henk.database;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public enum UrlRequest {

    SELECT_LEVEL("http://studev.groept.be/api/a16_sd507/selectlevel");


    String url;

    UrlRequest(String url){
        this.url = url;

    }

    public String getUrl() {
        return url;
    }



}
