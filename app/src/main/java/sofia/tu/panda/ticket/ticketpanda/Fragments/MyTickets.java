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
                Long id = c.getLong(c.getColumnIndex("programId"));
                Ticket ticket = new Ticket();
                ticket.setRow(c.getInt(c.getColumnIndex("row")));
                ticket.setColumn(c.getInt(c.getColumnIndex("column")));
                ticket.setBuyDate(c.getString(c.getColumnIndex("buyDate")));
                ticket.setProgramId(id);
                ticket.setPlaces(takePlace(ticket));
                Ticket existingTIcket = containsElement(id);
                if (existingTIcket != null) {
                    existingTIcket.setPlaces(existingTIcket.getPlaces() + ", " + ticket.getPlaces());
                } else {
                    data.add(ticket);
                }
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

    private Ticket containsElement(Long id) {
        for (Ticket ticket : data) {
            if (ticket.getProgramId() == id) {
                return ticket;
            }
        }
        return null;
    }

    private String takePlace(Ticket ticket) {
        String row = null;

        switch (ticket.getRow()) {
            case 0:
                row = "А";
                break;
            case 1:
                row = "Б";
                break;
            case 2:
                row = "В";
                break;
            case 3:
                row = "Г";
                break;
            case 4:
                row = "Д";
                break;
            case 5:
                row = "Е";
                break;
        }

        return (row + "-" + (ticket.getColumn() + 1));
    }

}
