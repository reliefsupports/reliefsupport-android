package disaster.com.disastermanagement;

import android.graphics.Color;

/**
 * Created by Sumudu on 5/29/2017.
 */

public class Data {

    private String SERVER_URL_PATH = "http://192.168.1.7/disaster/php/"; // change the URL to php file location

    private int[] SWIPER_COLOR_LIST = new int[]{Color.parseColor("#000000"), Color.parseColor("#007700"), Color.parseColor("#ff0000")};

    private int[] NAVIGATION_ICON_LIST = new int[]{R.drawable.home_navigation_icon, R.drawable.view_notifications_icon_tr, R.drawable.close};
    private String[] NAVIGATION_NAME_LIST = new String[]{"Dashboard", "Notifications", "Exit"};

    public String getSERVER_URL_PATH() {
        return SERVER_URL_PATH;
    }

    public int[] getSWIPER_COLOR_LIST() {
        return SWIPER_COLOR_LIST;
    }

    public int[] getNAVIGATION_ICON_LIST() {
        return NAVIGATION_ICON_LIST;
    }

    public String[] getNAVIGATION_NAME_LIST() {
        return NAVIGATION_NAME_LIST;
    }
}
