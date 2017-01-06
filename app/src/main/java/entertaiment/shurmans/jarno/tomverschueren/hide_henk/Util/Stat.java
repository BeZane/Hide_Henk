package entertaiment.shurmans.jarno.tomverschueren.hide_henk.Util;

import java.util.UUID;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.options.Preferences;

/**
 * Created by TomVerschueren on 6/01/2017.
 */

public class Stat {

    private StatType type;
    private int value;
    private UUID uuid;

    public Stat(StatType type, int value) {
        ;
        this.type = type;
        this.value = value;
        uuid = Preferences.uuid;
    }


    public StatType getType() {
        return type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getValue() {
        return value;
    }
}
