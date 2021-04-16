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

public class AppointmentFragment extends Fragment {
    private String error = null;
    private ArrayList<String> appointments = new ArrayList<>();
    private ArrayList<String> appointmentIDs = new ArrayList<>();
    private ArrayList<String> serviceNames = new ArrayList<>();
    private ArrayList<String> plateNos = new ArrayList<>();
    private ArrayList<String> startDates = new ArrayList<>();
    private ArrayList<String> startTimes = new ArrayList<>();
    private ArrayList<String> endDates = new ArrayList<>();
    private ArrayList<String> endTimes = new ArrayList<>();
    private ArrayList<String> technicianIDs = new ArrayList<>();
    private ArrayList<String> spaceIDs = new ArrayList<>();

    private ArrayAdapter arrayAdapter;
    private ListView lv;
    private static final String TAG = "Appointments: ";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getAllAppointments();
        View v= inflater.inflate(R.layout.fragment_appointment, container, false);
        lv = (ListView) v.findViewById(R.id.appointmentList);
        arrayAdapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, appointments);
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

    public void getAllAppointments() {

        HttpUtils.get("getAppointments/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call successfully (" + i + ").");
                        JSONObject obj1 = response.getJSONObject(i);
                        appointmentIDs.add(obj1.getString("appointmentID"));
                        serviceNames.add(obj1.getString("serviceName"));
                        plateNos.add(obj1.getString("plateNo"));
                        startDates.add(obj1.getString("startDate"));
                        startTimes.add(obj1.getString("startTime"));
                        endDates.add(obj1.getString("endDate"));
                        endTimes.add(obj1.getString("endTime"));

                        spaceIDs.add(obj1.getString("spaceID"));
                        //technicianIDs.add(obj1.getString("technicianID"));
                        appointments.add("appointment ID: " + appointmentIDs.get(i) +
                                "\nservice name: " + serviceNames.get(i) +
                                "\nplate number: " + plateNos.get(i) +
                                "\nstart date: " + startDates.get(i) +
                                "\nstart time: " + startTimes.get(i) +
                                "\nend date: " + endDates.get(i) +
                                "\nend time: " + endTimes.get(i) +

                                "\nspace ID: " + spaceIDs.get(i)); //+
                                //"\ntechnician ID: " + technicianIDs.get(i));
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