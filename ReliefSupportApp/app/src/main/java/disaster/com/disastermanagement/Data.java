package disaster.com.disastermanagement;

import android.graphics.Color;

/**
 * Created by Sumudu on 5/29/2017.
 */

public class Data {

    private String ENGLISH = "1";
    private String SINHALA = "2";
    private String TAMIL = "3";

    private String SERVER_URL_PATH = "https://roseless-seat.000webhostapp.com/reliefsupport/php/"; // change the URL to php file location

    private int[] SWIPER_COLOR_LIST = new int[]{Color.parseColor("#000000"), Color.parseColor("#007700"), Color.parseColor("#ff0000")};

    private int[] NAVIGATION_ICON_LIST_ENGLISH = new int[]{R.drawable.home_navigation_icon, R.drawable.phone_classic, R.drawable.view_notifications_icon_tr, R.drawable.language_icon, R.drawable.close};
    private String[] NAVIGATION_NAME_LIST_ENGLISH = new String[]{"Main Menu", "Essential Phone Numbers", "Notifications", "Change Language", "Exit"};

    private int[] NAVIGATION_ICON_LIST_SINHALA = new int[]{R.drawable.home_navigation_icon, R.drawable.phone_classic, R.drawable.view_notifications_icon_tr, R.drawable.language_icon, R.drawable.close};
    private String[] NAVIGATION_NAME_LIST_SINHALA = new String[]{"ප්\u200Dරධාන මෙනුව", "අත්\u200Dයවශ්\u200Dය දුරකථන  අංක", "දැනුම්දීම්", "භාෂාව වෙනස් කිරීම", "ඉවත්වීම"};

    private int[] NAVIGATION_ICON_LIST_TAMIL = new int[]{R.drawable.home_navigation_icon, R.drawable.phone_classic, R.drawable.view_notifications_icon_tr, R.drawable.language_icon, R.drawable.close};
    private String[] NAVIGATION_NAME_LIST_TAMIL = new String[]{"முதன்மை பட்டி", "அத்தியாவசிய தொலைபேசி எண்கள்", "அறிவிப்புகளை", "மொழி மாற்றம்", "வெளியேறுதல்"};


    public String getSERVER_URL_PATH() {
        return SERVER_URL_PATH;
    }

    public int[] getSWIPER_COLOR_LIST() {
        return SWIPER_COLOR_LIST;
    }

    public int[] getNAVIGATION_ICON_LIST_ENGLISH() {
        return NAVIGATION_ICON_LIST_ENGLISH;
    }

    public String[] getNAVIGATION_NAME_LIST_ENGLISH() {
        return NAVIGATION_NAME_LIST_ENGLISH;
    }

    public int[] getNAVIGATION_ICON_LIST_SINHALA() {
        return NAVIGATION_ICON_LIST_SINHALA;
    }

    public String[] getNAVIGATION_NAME_LIST_SINHALA() {
        return NAVIGATION_NAME_LIST_SINHALA;
    }

    public int[] getNAVIGATION_ICON_LIST_TAMIL() {
        return NAVIGATION_ICON_LIST_TAMIL;
    }

    public String[] getNAVIGATION_NAME_LIST_TAMIL() {
        return NAVIGATION_NAME_LIST_TAMIL;
    }
}
