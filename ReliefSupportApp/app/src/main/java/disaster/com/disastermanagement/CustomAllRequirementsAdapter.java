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
 * Created by WAKENSYS on 5/29/2017.
 */

public class CustomAllRequirementsAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    String[]
            needIDList,
            needNameList,
            needTelephoneList,
            needAddressList,
            needCityList,
            needTextList,
            needHeadCountList,
            needCreatedAtList,
            needUpdatedAtList,
            needIdentifierList;

    public CustomAllRequirementsAdapter(
            Context context,
            Activity activity,

            String[] needIDList,
            String[] needNameList,
            String[] needTelephoneList,
            String[] needAddressList,
            String[] needCityList,
            String[] needTextList,
            String[] needHeadCountList,
            String[] needCreatedAtList,
            String[] needUpdatedAtList,
            String[] needIdentifierList){

        this.context = context;
        this.activity = activity;

        this.needIDList = needIDList;
        this.needNameList = needNameList;
        this.needTelephoneList = needTelephoneList;
        this.needAddressList =needAddressList;
        this.needCityList = needCityList;
        this.needTextList = needTextList;
        this.needHeadCountList = needHeadCountList;
        this.needCreatedAtList = needCreatedAtList;
        this.needUpdatedAtList = needUpdatedAtList;
        this.needIdentifierList = needIdentifierList;
    }

    @Override
    public int getCount() {
        return needIDList.length;
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
        View gvContent = inflater.inflate(R.layout.need_item_layout, null);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
        gvContent.startAnimation(animation);

        final TextView textDisplay = (TextView) gvContent.findViewById(R.id.textDisplay);
        final TextView countDisplay = (TextView) gvContent.findViewById(R.id.countDisplay);
        final TextView userNameDisplay = (TextView) gvContent.findViewById(R.id.userNameDisplay);
        final TextView addressDisplay = (TextView) gvContent.findViewById(R.id.addressDisplay);
        final TextView cityDisplay = (TextView) gvContent.findViewById(R.id.cityDisplay);
        final TextView telNoDisplay = (TextView) gvContent.findViewById(R.id.telNoDisplay);

        final TextView identifierDisplay = (TextView) gvContent.findViewById(R.id.identifierDisplay);
        final TextView dateDisplay = (TextView) gvContent.findViewById(R.id.dateDisplay);

        final CardView itemLayout = (CardView) gvContent.findViewById(R.id.itemLayout);
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        userNameDisplay.setText(needNameList[position]);
        telNoDisplay.setText(needTelephoneList[position]);
        addressDisplay.setText(needAddressList[position]);
        cityDisplay.setText(needCityList[position]);
        textDisplay.setText(needTextList[position]);
        countDisplay.setText(needHeadCountList[position]);
        dateDisplay.setText(needUpdatedAtList[position]);
        identifierDisplay.setText(needIdentifierList[position]);
        return gvContent;
    }
}
