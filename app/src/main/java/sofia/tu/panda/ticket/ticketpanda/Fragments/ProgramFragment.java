package sofia.tu.panda.ticket.ticketpanda.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sofia.tu.panda.ticket.ticketpanda.Adapters.ProgramAdapter;
import sofia.tu.panda.ticket.ticketpanda.Objects.Program;
import sofia.tu.panda.ticket.ticketpanda.R;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBConstants;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBPref;

public class ProgramFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgramAdapter adapter;
    private List<Program> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_program, container, false);

        data = new ArrayList<>();

        DBPref pref = new DBPref(getContext());
        Cursor c = pref.getVals(DBConstants.PROGRAM_TABLE);

        if (c.moveToFirst()) {
            do {
                Program program = new Program();
                program.setId(c.getInt(c.getColumnIndex("_id")));
                program.setTitle(c.getString(c.getColumnIndex("title")));
                program.setSmallDescription((c.getString(c.getColumnIndex("smallDescription"))));
                program.setDescription(c.getString(c.getColumnIndex("fullDescription")));
                program.setPrice(c.getInt(c.getColumnIndex("price")));
                program.setAuthor(c.getString(c.getColumnIndex("author")));
                program.setActors(c.getString(c.getColumnIndex("actors")));
                program.setDirector(c.getString(c.getColumnIndex("director")));
                program.setProduction(c.getString(c.getColumnIndex("production")));
                program.setPrimaryImage(c.getInt(c.getColumnIndex("primaryImage")));
                program.setSecondaryImage(c.getInt(c.getColumnIndex("secondaryImage")));
                program.setThirdImage(c.getInt(c.getColumnIndex("thirdImage")));
                program.setBought(c.getInt(c.getColumnIndex("bought")));
                data.add(program);
            } while (c.moveToNext());
        }

        c.close();
        pref.close();

        recyclerView = (RecyclerView) root.findViewById(R.id.program_recycler_view);
        adapter = new ProgramAdapter(getContext(), data);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return root;
    }
}
