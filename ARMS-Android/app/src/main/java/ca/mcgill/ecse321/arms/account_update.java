package ca.mcgill.ecse321.arms;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.arms.HttpUtils;
import ca.mcgill.ecse321.arms.R;
import ca.mcgill.ecse321.arms.navigationdrawer.AccountFragment;
import ca.mcgill.ecse321.arms.navigationdrawer.MainActivity;
import ca.mcgill.ecse321.arms.ui.login.LoginViewModel;
import ca.mcgill.ecse321.arms.ui.login.LoginViewModelFactory;
import cz.msebera.android.httpclient.Header;

public class account_update extends AppCompatActivity {

    private String name;
    private String newEmail;
    private String newPhoneNum;
    private String newPassword;
    private String oldPassword;
    private String error;
    private static final String TAG = "update: ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_account);
        refreshErrorMessage();
        name = getIntent().getStringExtra("USERNAME");
        Bundle bundle = getIntent().getExtras();
        TextView tv2 = (TextView) findViewById(R.id.updname);
        tv2.setText(name);

        refreshErrorMessage();
        Button btn = (Button)findViewById(R.id.buttonGoAccount);
        Button btn1 = (Button)findViewById(R.id.buttonUpdate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this.finish();
//                Log.d(TAG, "clicked");
                Fragment fragment = new AccountFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack(null);
                fragmentTransaction.commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new AccountFragment()).commit();
            }
        });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateInfo(v);
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            this.finish();
//        } else {
//            super.onBackPressed(); //replaced
//        }
//    }
    public void getInfo(){
        HttpUtils.get("/getCurrentCustomer", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try{
                    name = response.get("username").toString();
                    oldPassword= response.get("password").toString();

                }catch (Exception e){
                    error += e.getMessage();
                }
                refreshErrorMessage();

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

//
//    public void goAccount(View v){
//        Intent intent = new Intent(account_update.this, my_account.class);
//        intent.putExtra("USERNAME", name);
//        startActivity(intent);
//    }


//    private void updateUiWithUser(LoggedInUserView model) {
//        String welcome = getString(R.string.welcome) + model.getDisplayName();
//        // TODO : initiate successful logged in experience
//        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
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

    public void updateInfo(View v) {
        error = "";
        TextView tvOldP = (TextView) findViewById(R.id.oldPassword);
        TextView tvNewP = (TextView) findViewById(R.id.newPassword);
        TextView tvEmail = (TextView) findViewById(R.id.updateEmail);
        TextView tvPhone = (TextView) findViewById(R.id.updatePhone);
        String oldP = tvOldP.getText().toString();
        String newP = tvNewP.getText().toString();
        String newEmail = tvEmail.getText().toString();
        String newPhone = tvPhone.getText().toString();
        if(!oldP.equals(oldPassword)){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("Wrong password!")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }else if(newP.equals("")||newEmail.equals("")||newPhone.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("Incomplete information.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .show();

        }else {

            RequestParams rp = new RequestParams();
            rp.add("username",name);
            rp.add("password",newP);
            rp.add("email",newEmail);
            rp.add("phonenumber",newPhone);

            HttpUtils.put("/updateCustomer", rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    AlertDialog alertDialog = new AlertDialog.Builder(account_update.this)
                            .setMessage("Update success!")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setNegativeButton("go back to profile", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(account_update.this, AccountFragment.class);
                                    //intent.putExtra("USERNAME", user);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("Update again", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                    refreshErrorMessage();
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
    }
}