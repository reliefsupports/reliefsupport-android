package disaster.com.disastermanagement;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by WAKENSYS on 5/30/2017.
 */

public class CustomNotificationsAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    String[] mainValueList, subValueList1, subValueList2, subValueList3, subValueList4, dateList;

    public CustomNotificationsAdapter(
            Context context,
            Activity activity,

            String[] mainValueList,
            String[] subValueList1,
            String[] subValueList2,
            String[] subValueList3,
            String[] subValueList4,
            String[] dateList){

        this.context = context;
        this.activity = activity;

        this.mainValueList = mainValueList;
        this.subValueList1 = subValueList1;
        this.subValueList2 = subValueList2;
        this.subValueList3 = subValueList3;
        this.subValueList4 = subValueList4;
        this.dateList = dateList;
    }

    @Override
    public int getCount() {
        return mainValueList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gvContent = inflater.inflate(R.layout.notification_item_layout, null);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
        gvContent.startAnimation(animation);

        final TextView mainValueDisplay = (TextView) gvContent.findViewById(R.id.mainValueDisplay);
        final TextView subValue1Display = (TextView) gvContent.findViewById(R.id.subValue1Display);
        final TextView subValue2Display = (TextView) gvContent.findViewById(R.id.subValue2Display);
        final TextView subValue3Display = (TextView) gvContent.findViewById(R.id.subValue3Display);
        final TextView subValue4Display = (TextView) gvContent.findViewById(R.id.subValue4Display);
        final TextView dateTimeDisplay = (TextView) gvContent.findViewById(R.id.dateTimeDisplay);

        mainValueDisplay.setText(mainValueList[position]);
        subValue1Display.setText(subValueList1[position]);
        subValue2Display.setText(subValueList2[position]);
        subValue3Display.setText(subValueList3[position]);
        subValue4Display.setText(subValueList4[position]);
        dateTimeDisplay.setText(dateList[position]);

        return gvContent;
    }
}
