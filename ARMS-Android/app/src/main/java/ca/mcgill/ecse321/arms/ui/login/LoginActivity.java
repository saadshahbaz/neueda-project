package ca.mcgill.ecse321.arms.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import ca.mcgill.ecse321.arms.navigationdrawer.MainActivity;
import ca.mcgill.ecse321.arms.ui.login.LoginViewModel;
import ca.mcgill.ecse321.arms.ui.login.LoginViewModelFactory;
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
                System.out.println(username+"111111");
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