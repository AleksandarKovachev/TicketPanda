package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import sofia.tu.panda.ticket.ticketpanda.Constants.SharedPreferenceConstants;
import sofia.tu.panda.ticket.ticketpanda.Fragments.MapTab;
import sofia.tu.panda.ticket.ticketpanda.Fragments.MyTickets;
import sofia.tu.panda.ticket.ticketpanda.Fragments.ProgramFragment;
import sofia.tu.panda.ticket.ticketpanda.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
//    private Button program, myTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogin();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                String title = null;

                switch (tabId) {
                    case R.id.program_tab:
                        fragment = new ProgramFragment();
                        title = "Програма";
                        break;
                    case R.id.my_tickets_tab:
                        fragment = new MyTickets();
                        title = "Моите билети";
                        break;
                    case R.id.map_tab:
                        fragment = new MapTab();
                        title = "Карта";
                        break;
                }

                if (fragment != null)

                {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    getFragmentManager().popBackStackImmediate();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentManager.popBackStack();
                    fragmentTransaction.replace(R.id.contentContainer, fragment);
                    getSupportActionBar().setTitle(title);
                    fragmentTransaction.commit();
                }
            }
        });

    }

    @Override
    protected void onRestart() {
        checkLogin();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        checkLogin();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            SharedPreferences pref = getSharedPreferences(SharedPreferenceConstants.LOGIN, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        return false;
    }

    private void checkLogin() {
        sharedPreferences = getApplicationContext().getSharedPreferences(SharedPreferenceConstants.LOGIN, Context.MODE_PRIVATE);
        boolean isUserLoggedIn = sharedPreferences.getBoolean(SharedPreferenceConstants.LOGIN, false);
        String userName = sharedPreferences.getString("userName", null);
        String userEgn = sharedPreferences.getString("userEgn", null);

        if (!isUserLoggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
