package entertaiment.shurmans.jarno.tomverschueren.hide_henk.Util;

/**
 * Created by TomVerschueren on 6/01/2017.
 */

public enum StatType {
    GAMES_PLAYED("gamesplayed", "Games Played",1),
    GAMES_WON("gameswon", "Games Won",2),
    GAMES_FAILED("gamesfailed", "Games Failed",3),
    OBJECT_PLACED("ojbectsplaced", "Objects Placed",4),
    GAMES_MADE("gamesmade","Games Made",5);

    private String databaseName;
    private String showName;
    private int columnNumber;

    StatType(String databaseName, String showName, int columNumber){
        this.databaseName = databaseName;
        this.showName = showName;
        this.columnNumber = columNumber;
    }


    public int getColumnNumber(){
        return columnNumber;
    }

    public String toDatabaseString(){
        return databaseName;
    }

    public String toShowString(){
        return showName;
    }

}
