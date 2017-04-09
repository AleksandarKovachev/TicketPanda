package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import sofia.tu.panda.ticket.ticketpanda.Constants.SharedPreferenceConstants;
import sofia.tu.panda.ticket.ticketpanda.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Button program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogin();

        program = (Button) findViewById(R.id.button_program);
        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramActivity.class);
                getApplicationContext().startActivity(intent);
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
