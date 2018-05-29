package example.fastec.diabin.com.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by cguyu on 2018/5/13.
 */

public enum EcIcons implements Icon {

    icon_scan('\ue602'),
    icon_ali_pay('\ue606');
//      icon_scon('\ue602');
//     icon_ali_pay('\ue602');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
