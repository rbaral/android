package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CommunityFragment extends Fragment {
	
	public CommunityFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_community, container,false);
        EditText weatherTextField=(EditText)rootView.findViewById(R.id.weatherText);
        EditText placeTextField=(EditText)rootView.findViewById(R.id.placeText);
        String weatherText=getArguments().getString("weatherResult");
        Log.d("Saw","Bundle has result as: "+weatherText.length());
        Log.d("Saw","Text field value was: "+weatherTextField.getText());
        weatherTextField.setText(weatherText);
        String placeText=getArguments().getString("placeResult");
        placeTextField.setText(placeText); 
        return rootView;
    }

}
