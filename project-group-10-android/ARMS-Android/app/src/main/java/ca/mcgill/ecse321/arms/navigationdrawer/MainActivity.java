package ca.mcgill.ecse321.arms.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import ca.mcgill.ecse321.arms.R;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AccountFragment()).commit();
            navigationView.setCheckedItem(R.id.my_account);
        }
    }

    /**
     * link fragments to the navigation bar
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragment()).commit();
                break;
            case R.id.services:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ServiceFragment()).commit();
                break;
            case R.id.my_car:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CarFragment()).commit();
                break;
            case R.id.appointment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AppointmentFragment()).commit();
                break;
            case R.id.payment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BillFragment()).commit();
                break;

            case R.id.logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogOutFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
