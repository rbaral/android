package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.model.CityResult;
import info.androidhive.slidingmenu.provider.YahooClient;

import java.util.List;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	
	private TableLayout questionContainer;
    static int pos = 0;
    private String[] titles = {"The first title ", "hallo1","hallo2", "hallo3",
            "hallo4", "hallo5","hallo6", "hallo7","hallo8", "hallo9"};
	
	List<CityResult> cityResultList;
    public HomeFragment(){}
	

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        return rootView;
        
		/*super.onCreate(savedInstanceState);
        TableLayout table = new TableLayout(getActivity().getApplicationContext());

        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(getActivity().getApplicationContext());
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        TableRow rowDayLabels = new TableRow(getActivity().getApplicationContext());
        TableRow rowHighs = new TableRow(getActivity().getApplicationContext());
        TableRow rowLows = new TableRow(getActivity().getApplicationContext());
        TableRow rowConditions = new TableRow(getActivity().getApplicationContext());
        rowConditions.setGravity(Gravity.CENTER);

        TextView empty = new TextView(getActivity().getApplicationContext());

        // title column/row
        TextView title = new TextView(getActivity().getApplicationContext());
        title.setText("Java Weather Table"+(getCityResultList().size()>0?getCityResultList().get(0).getCityName():" default"));

        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 6;

        rowTitle.addView(title, params);

        // labels column
        TextView highsLabel = new TextView(getActivity().getApplicationContext());
        highsLabel.setText("Day High");
        highsLabel.setTypeface(Typeface.DEFAULT_BOLD);

        TextView lowsLabel = new TextView(getActivity().getApplicationContext());
        lowsLabel.setText("Day Low");
        lowsLabel.setTypeface(Typeface.DEFAULT_BOLD);

        TextView conditionsLabel = new TextView(getActivity().getApplicationContext());
        conditionsLabel.setText("Conditions");
        conditionsLabel.setTypeface(Typeface.DEFAULT_BOLD);

        rowDayLabels.addView(empty);
        rowHighs.addView(highsLabel);
        rowLows.addView(lowsLabel);
        rowConditions.addView(conditionsLabel);

        // day 1 column
        TextView day1Label = new TextView(getActivity().getApplicationContext());
        day1Label.setText("Feb- 7");
        day1Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day1High = new TextView(getActivity().getApplicationContext());
        day1High.setText("28°F");
        day1High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day1Low = new TextView(getActivity().getApplicationContext());
        day1Low.setText("15°F");
        day1Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day1Conditions = new ImageView(getActivity().getApplicationContext());
        day1Conditions.setImageResource(R.drawable.ic_whats_hot);

        rowDayLabels.addView(day1Label);
        rowHighs.addView(day1High);
        rowLows.addView(day1Low);
        rowConditions.addView(day1Conditions);

        // day2 column
        TextView day2Label = new TextView(getActivity().getApplicationContext());
        day2Label.setText("Feb 8");
        day2Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day2High = new TextView(getActivity().getApplicationContext());
        day2High.setText("26°F");
        day2High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day2Low = new TextView(getActivity().getApplicationContext());
        day2Low.setText("14°F");
        day2Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day2Conditions = new ImageView(getActivity().getApplicationContext());
        day2Conditions.setImageResource(R.drawable.ic_pages);

        rowDayLabels.addView(day2Label);
        rowHighs.addView(day2High);
        rowLows.addView(day2Low);
        rowConditions.addView(day2Conditions);

        // day3 column
        TextView day3Label = new TextView(getActivity().getApplicationContext());
        day3Label.setText("Feb 9");
        day3Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day3High = new TextView(getActivity().getApplicationContext());
        day3High.setText("23°F");
        day3High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day3Low = new TextView(getActivity().getApplicationContext());
        day3Low.setText("3°F");
        day3Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day3Conditions = new ImageView(getActivity().getApplicationContext());
        day3Conditions.setImageResource(R.drawable.ic_communities);

        rowDayLabels.addView(day3Label);
        rowHighs.addView(day3High);
        rowLows.addView(day3Low);
        rowConditions.addView(day3Conditions);

        // day4 column
        TextView day4Label = new TextView(getActivity().getApplicationContext());
        day4Label.setText("Feb 10");
        day4Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day4High = new TextView(getActivity().getApplicationContext());
        day4High.setText("17°F");
        day4High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day4Low = new TextView(getActivity().getApplicationContext());
        day4Low.setText("5°F");
        day4Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day4Conditions = new ImageView(getActivity().getApplicationContext());
        day4Conditions.setImageResource(R.drawable.counter_bg);

        rowDayLabels.addView(day4Label);
        rowHighs.addView(day4High);
        rowLows.addView(day4Low);
        rowConditions.addView(day4Conditions);

        // day5 column
        TextView day5Label = new TextView(getActivity().getApplicationContext());
        day5Label.setText("Feb 11");
        day5Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day5High = new TextView(getActivity().getApplicationContext());
        day5High.setText("19°F");
        day5High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day5Low = new TextView(getActivity().getApplicationContext());
        day5Low.setText("6°F");
        day5Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day5Conditions = new ImageView(getActivity().getApplicationContext());
        day5Conditions.setImageResource(R.drawable.ic_communities);

        rowDayLabels.addView(day5Label);
        rowHighs.addView(day5High);
        rowLows.addView(day5Low);
        rowConditions.addView(day5Conditions);

        table.addView(rowTitle);
        table.addView(rowDayLabels);
        table.addView(rowHighs);
        table.addView(rowLows);
        table.addView(rowConditions);

        //setContentView(table);
        return table.getRootView();
    }

	public List<CityResult> getCityResultList() {
		List<CityResult> cityList=new YahooClient().getCityList("ca");
		Log.d("Swa", "City list size:"+cityList.size());
		return cityList;
	}

	public void setCityResultList(List<CityResult> cityResultList) {
		this.cityResultList = cityResultList;
	}*/
	
	}	
}
