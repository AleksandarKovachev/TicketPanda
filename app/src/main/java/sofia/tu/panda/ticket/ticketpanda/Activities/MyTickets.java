package sofia.tu.panda.ticket.ticketpanda.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sofia.tu.panda.ticket.ticketpanda.Adapters.MyTicketsAdapter;
import sofia.tu.panda.ticket.ticketpanda.Objects.Ticket;
import sofia.tu.panda.ticket.ticketpanda.R;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBConstants;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBPref;

public class MyTickets extends AppCompatActivity {

    private List<Ticket> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        data = new ArrayList<>();

        DBPref pref = new DBPref(this);
        Cursor c = pref.getVals(DBConstants.MY_TICKETS);

        if (c.moveToFirst()) {
            do {
                Ticket ticket = new Ticket();
                ticket.setRow(c.getInt(c.getColumnIndex("row")));
                ticket.setColumn(c.getInt(c.getColumnIndex("column")));
                ticket.setBuyDate(c.getString(c.getColumnIndex("buyDate")));
                ticket.setProgramId(c.getLong(c.getColumnIndex("programId")));
                data.add(ticket);
            } while (c.moveToNext());
        }

        c.close();
        pref.close();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_my_tickets);

        MyTicketsAdapter adapter = new MyTicketsAdapter(getApplicationContext(), data);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

}
