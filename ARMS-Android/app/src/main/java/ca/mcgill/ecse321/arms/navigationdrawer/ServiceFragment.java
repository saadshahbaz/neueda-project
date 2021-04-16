package ca.mcgill.ecse321.arms.navigationdrawer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import cz.msebera.android.httpclient.Header;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ServiceFragment extends Fragment {
    private String error = null;
    private ArrayList<String> services = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> durations = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ListView lv;
    private static final String TAG = "Services: ";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getAllServices();
        View v= inflater.inflate(R.layout.fragment_service, container, false);
        lv = (ListView) v.findViewById(R.id.serviceList);
        arrayAdapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, services);
        lv.setAdapter(arrayAdapter);
        return v;
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) getView().findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }

    /**
     * method that retrieve all available service and display them to the user
     */
    public void getAllServices() {

        HttpUtils.get("services/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call successfully (" + i + ").");
                        JSONObject obj1 = response.getJSONObject(i);
                        names.add(obj1.getString("name"));
                        prices.add(obj1.getString("price"));
                        durations.add(obj1.getString("duration"));
                        services.add("name: " + names.get(i) + "\nduration: " + durations.get(i) + "\nprice: " + prices.get(i));
                    }catch (JSONException e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    super.onFailure(statusCode, headers, errorResponse.getString(""), throwable);
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });



    }
}