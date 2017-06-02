package disaster.com.disastermanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by WAKENSYS on 6/2/2017.
 */

public class CustomEmergencyNumberAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    String[] nameList, numberList;

    public CustomEmergencyNumberAdapter(Context context, Activity activity, String[] nameList, String[] numberList){
        this.context = context;
        this.activity = activity;

        this.nameList = nameList;
        this.numberList = numberList;
    }

    @Override
    public int getCount() {
        return nameList.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gvContent = inflater.inflate(R.layout.emergency_number_item_layout, null);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        gvContent.startAnimation(animation);

        final TextView nameDisplay = (TextView) gvContent.findViewById(R.id.nameDisplay);
        final TextView numberDisplay = (TextView) gvContent.findViewById(R.id.numberDisplay);

        nameDisplay.setText(nameList[position]);
        numberDisplay.setText(numberList[position]);

        return gvContent;
    }
}
