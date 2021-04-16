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

import ca.mcgill.ecse321.arms.ARMS;
import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import cz.msebera.android.httpclient.Header;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BillFragment extends Fragment {
    private static final String TAG = "Current User: ";
    private String currentCustomer = "";
    private String error = "";
    private ArrayList<String> amounts = new ArrayList<>();
    private ArrayList<String> isPaids = new ArrayList<>();
    private ArrayList<String> billNos = new ArrayList<>();
    private ArrayList<String> bills = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentCustomer=getCurrentCustomer();
        getBills(currentCustomer);
        View v= inflater.inflate(R.layout.fragment_payment, container, false);
        lv = (ListView) v.findViewById(R.id.billList);
        arrayAdapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, bills);
        lv.setAdapter(arrayAdapter);
        return v;
    }

    public String getCurrentCustomer() {
        return ARMS.getCurrentuser();
    }
    public void getBills(String username){

        HttpUtils.get("/getBillsByCustomer"+"?username="+username, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call succesfull (" + i + ").");
                        JSONObject obj1 = response.getJSONObject(i);
                        billNos.add(obj1.getString("billNo"));
                        if(obj1.getBoolean("paid")){
                            isPaids.add("paid");
                        }else{
                            isPaids.add("not paid");
                        }
                        amounts.add(obj1.getString("amount"));
                        bills.add("bill No.: " + billNos.get(i) + "\nstatus: " + isPaids.get(i) + "\namount: " + amounts.get(i));
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
            }
        });
    }

}
