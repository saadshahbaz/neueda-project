package ca.mcgill.ecse321.arms.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.arms.ARMS;
import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import ca.mcgill.ecse321.arms.account_update;
import cz.msebera.android.httpclient.Header;
import android.widget.ArrayAdapter;



public class AccountFragment extends Fragment {
    private String name;
    private String email;
    private String phoneNum;
    private String password;
    private String error;
    private ArrayAdapter arrayAdapter;
    private ListView lv;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPhone;
    private static final String TAG = "Account: ";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_account, container, false);
        name = getCurrentCustomer();
        tvName = (TextView) v.findViewById(R.id.name);
        tvEmail = (TextView) v.findViewById(R.id.email);
        tvPhone = (TextView) v.findViewById(R.id.phoneNum);
        Button btnUpdate = (Button) v.findViewById(R.id.update);
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AccountFragment.this.getActivity(), account_update.class);
                intent.putExtra("USERNAME", name);
                startActivity(intent);
            }
                                     }
                );
        getInfo();
        return v;
    }

    public String getCurrentCustomer() {
        return ARMS.getCurrentuser();
    }

    /**
     * get current user's information
     */
    public void getInfo(){
        RequestParams rp = new RequestParams();
        rp.add("username",name);
        HttpUtils.get("/getCustomer", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                for( int i = 0; i < response.length(); i++){
                    try {
                        email = response.getString("email");
                        phoneNum = response.getString("phoneNumber");
                    }catch (JSONException e) {
                        error += e.getMessage();
                    }}


                refreshErrorMessage();
                TextView tv2 = (TextView) getView().findViewById(R.id.name);
                tv2.setText(name);
                TextView tv3 = (TextView) getView().findViewById(R.id.email);
                tv3.setText(email);
                TextView tv4 = (TextView) getView().findViewById(R.id.phoneNum);
                tv4.setText(phoneNum);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
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

}
