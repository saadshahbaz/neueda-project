package ca.mcgill.ecse321.arms.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.arms.ARMS;
import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import ca.mcgill.ecse321.arms.navigationdrawer.MainActivity;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private String error;
    private static final String TAG = "Current User: ";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginCustomer(v);

            }
        });
        refreshErrorMessage();

    }


    /**
     * display error if there is any
     */
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /**
     * login the customer to the system
     * @param v
     */
    public void loginCustomer(View v) {
        error = "";
        final TextView tvUsername = (TextView) findViewById(R.id.username);
        String username = tvUsername.getText().toString();
        final TextView tvPassword = (TextView) findViewById(R.id.password);
        String password = tvPassword.getText().toString();
        HttpUtils.put("loginCustomer/?username=" + username + "&password=" + password, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "Restful GET call successfully");
                //refreshErrorMessage();
                try {
                    System.out.println(response.get("email").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ARMS.setCurrentuser(username);
                tvUsername.setText("");
                tvPassword.setText("");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable){
//                error += responseString;
//                //no matter what is done, we will need to refresh the error messages.
//                refreshErrorMessage();
//            }
        });
    }
}