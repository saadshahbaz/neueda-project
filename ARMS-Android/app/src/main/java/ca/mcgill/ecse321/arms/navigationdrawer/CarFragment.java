package ca.mcgill.ecse321.arms.navigationdrawer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ca.mcgill.ecse321.arms.ARMS;
import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import cz.msebera.android.httpclient.Header;

public class CarFragment extends Fragment {

    private String error;
    private static final String TAG = "Cars: ";
    private ArrayList<String> cars = new ArrayList<>();
    private ArrayList<String> manufacturers = new ArrayList<>();
    private ArrayList<String> models = new ArrayList<>();
    private ArrayList<String> years = new ArrayList<>();
    private ArrayList<String> plateNos = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ListView lv;
    private String customerName;
    private Button addCarButton;

    /**
     * initialize the Fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * initialize the view of the car fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        customerName = getCurrentCustomer();
        Log.d(TAG, customerName);
        getAllCars(customerName);

        View v = inflater.inflate(R.layout.fragment_car, container, false);
        lv = (ListView) v.findViewById(R.id.carList);
        arrayAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, cars);
        lv.setAdapter(arrayAdapter);
        addCarButton = (Button) v.findViewById(R.id.btnAddCar);

        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerName = getCurrentCustomer();
                createCar(customerName);
            }
        });
        return v;
    }

    /**
     * get the current user from ARMS
     * @return
     */
    public String getCurrentCustomer() {
        return ARMS.getCurrentuser();
    }

    /**
     * get error message and display it if there is
     */
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) getView().findViewById(R.id.tvCarError);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /**
     * get all the cars of the current customer
     * @param username
     */
    public void getAllCars(String username) {
        Log.d(TAG, "getAllCars");
        HttpUtils.get("/getCarsByCustomer?username=" + username, new RequestParams(), new JsonHttpResponseHandler() {
            //System.out.println("in get");
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d(TAG, "success");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.d(TAG, "Restful GET call successfully (" + i + ").");
                        JSONObject obj1 = response.getJSONObject(i);
                        manufacturers.add(obj1.getString("manufacturer"));
                        models.add(obj1.getString("model"));
                        years.add(obj1.getString("year"));
                        plateNos.add(obj1.getString("plateNo"));
                        cars.add("manufacturer: " + manufacturers.get(i) + "\nmodel: " + models.get(i) + "\nyear: " + years.get(i) + "\nplate number: " + plateNos.get(i));
                    } catch (JSONException e) {
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


    /**
     * create a car in the system using information provided by the customer
     * @param username
     */
    public void createCar(String username) {
        error = "";

        //get the text view from xml text inputs
        final TextView tvManufacturer = (TextView) getView().findViewById(R.id.etManufacturer);
        String manufacturer = tvManufacturer.getText().toString();
        final TextView tvModel = (TextView) getView().findViewById(R.id.etModel);
        String model = tvModel.getText().toString();
        final TextView tvYear = (TextView) getView().findViewById(R.id.etYear);
        String year = tvYear.getText().toString();
        final TextView tvPlateNo = (TextView) getView().findViewById(R.id.etplateNo);
        String plateNo = tvPlateNo.getText().toString();

        //connect to backend via HttpUtils
        HttpUtils.post("/car?customer=" + username + "&manufactuer=" + manufacturer + "&model=" + model + "&year=" + year + "&plateN=" + plateNo
                , new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d(TAG, "Restful GET call successfully");
                        //refreshErrorMessage();
                        System.out.println(plateNo + "111111");
                        tvManufacturer.setText("");
                        tvModel.setText("");
                        tvYear.setText("");
                        tvPlateNo.setText("");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            System.out.println(errorResponse.get("message").toString());
                            error += errorResponse.get("message").toString();
                        } catch (JSONException e) {
                            error += e.getMessage();
                        }
                        refreshErrorMessage();
                    }
                });
    }
}