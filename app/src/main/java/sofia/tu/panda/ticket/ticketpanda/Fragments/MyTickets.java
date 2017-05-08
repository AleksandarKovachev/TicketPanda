package sofia.tu.panda.ticket.ticketpanda.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sofia.tu.panda.ticket.ticketpanda.Adapters.MyTicketsAdapter;
import sofia.tu.panda.ticket.ticketpanda.Objects.Ticket;
import sofia.tu.panda.ticket.ticketpanda.R;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBConstants;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBPref;

public class MyTickets extends Fragment {

    private List<Ticket> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_tickets, container, false);

        data = new ArrayList<>();

        DBPref pref = new DBPref(getContext());
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

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_my_tickets);

        MyTicketsAdapter adapter = new MyTicketsAdapter(getContext(), data);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return root;
    }

}
