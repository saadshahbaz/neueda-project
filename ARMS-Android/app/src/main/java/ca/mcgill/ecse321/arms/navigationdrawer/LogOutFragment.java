package ca.mcgill.ecse321.arms.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ca.mcgill.ecse321.arms.ARMS;
import ca.mcgill.ecse321.arms.R;
import ca.mcgill.ecse321.arms.ui.login.LoginActivity;

public class LogOutFragment extends Fragment {
    private Button logoutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //final Button logoutButton = getView().findViewById(R.id.logoutBtn);
        View v= inflater.inflate(R.layout.fragment_logout, container, false);
        logoutButton = v.findViewById(R.id.logoutBtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARMS.setCurrentuser("");
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return v;
    }

}