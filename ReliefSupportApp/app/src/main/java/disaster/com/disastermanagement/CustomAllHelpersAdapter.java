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

public class CustomAllHelpersAdapter extends BaseAdapter {
    Context context;
    Activity activity;

    String[]
            donationIDList,
            donationNameList,
            donationTelephoneList,
            donationAddressList,
            donationCityList,
            donationTextList,
            donationInformationList,
            donationCreatedAtList,
            donationUpdatedAtList,
            donationIdentifierList;

    public CustomAllHelpersAdapter(
            Context context,
            Activity activity,

            String[] donationIDList,
            String[] donationNameList,
            String[] donationTelephoneList,
            String[] donationAddressList,
            String[] donationCityList,
            String[] donationTextList,
            String[] donationInformationList,
            String[] donationCreatedAtList,
            String[] donationUpdatedAtList,
            String[] donationIdentifierList){

        this.context = context;
        this.activity = activity;

        this.donationIDList = donationIDList;
        this.donationNameList = donationNameList;
        this.donationTelephoneList = donationTelephoneList;
        this.donationAddressList = donationAddressList;
        this.donationCityList = donationCityList;
        this.donationTextList = donationTextList;
        this.donationInformationList = donationInformationList;
        this.donationCreatedAtList = donationCreatedAtList;
        this.donationUpdatedAtList = donationUpdatedAtList;
        this.donationIdentifierList = donationIdentifierList;
    }

    @Override
    public int getCount() {
        return donationIDList.length;
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
        View gvContent = inflater.inflate(R.layout.helper_item_layout, null);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        gvContent.startAnimation(animation);

        final TextView textDisplay = (TextView) gvContent.findViewById(R.id.textDisplay);
        final TextView infoDisplay = (TextView) gvContent.findViewById(R.id.infoDisplay);
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

        userNameDisplay.setText(donationNameList[position]);
        telNoDisplay.setText(donationTelephoneList[position]);
        addressDisplay.setText(donationAddressList[position]);
        cityDisplay.setText(donationCityList[position]);
        textDisplay.setText(donationTextList[position]);
        infoDisplay.setText(donationInformationList[position]);
        dateDisplay.setText(donationUpdatedAtList[position]);
        identifierDisplay.setText(donationIdentifierList[position]);
        return gvContent;
    }
}
