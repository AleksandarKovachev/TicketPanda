package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import sofia.tu.panda.ticket.ticketpanda.R;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBConstants;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBPref;

public class MainActivity extends AppCompatActivity {

    private EditText username, egn;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.input_username);
        egn = (EditText) findViewById(R.id.input_egn);

        login = (Button) findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("Въведи име!");
                } else if (egn.getText().toString().isEmpty()) {
                    egn.setError("Въведи егн!");
                } else {
                    DBPref pref = new DBPref(getApplicationContext());
                    pref.addRecord(DBConstants.LOGIN_TABLE, username.getText().toString(), egn.getText().toString(), new Date().toString());
                    pref.close();
                }
            }
        });
    }
}
