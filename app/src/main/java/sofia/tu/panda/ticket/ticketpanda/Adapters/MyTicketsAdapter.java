package sofia.tu.panda.ticket.ticketpanda.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sofia.tu.panda.ticket.ticketpanda.Activities.BarCodeActivity;
import sofia.tu.panda.ticket.ticketpanda.Objects.Program;
import sofia.tu.panda.ticket.ticketpanda.Objects.Ticket;
import sofia.tu.panda.ticket.ticketpanda.R;
import sofia.tu.panda.ticket.ticketpanda.SQLite.DBPref;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.MyTicketsViewHolder> {

    private Context context;

    private static List<Ticket> data;

    public MyTicketsAdapter(Context context, List<Ticket> objects) {
        this.context = context;
        this.data = objects;
    }

    @Override
    public MyTicketsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_tickets, parent, false);
        return new MyTicketsViewHolder(itemView);
    }

    public class MyTicketsViewHolder extends RecyclerView.ViewHolder {
        public TextView title, buyDate, projectionDate, location, description;
        public ImageView img, menu;
        public CardView cardView;

        public MyTicketsViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.my_tickets_title);
            buyDate = (TextView) view.findViewById(R.id.my_tickets_buy_date);
            projectionDate = (TextView) view.findViewById(R.id.my_tickets_projection_date);
            location = (TextView) view.findViewById(R.id.my_tickets_location);
            description = (TextView) view.findViewById(R.id.my_tickets_description);
            img = (ImageView) view.findViewById(R.id.my_tickets_image_view);

            cardView = (CardView) view.findViewById(R.id.my_tickets_card_view);

            menu = (ImageView) view.findViewById(R.id.my_tickets_menu);
        }
    }

    @Override
    public void onBindViewHolder(final MyTicketsViewHolder holder, final int position) {
        DBPref pref = new DBPref(context);

        Ticket ticket = data.get(position);
        Program program = pref.getProgramById(ticket.getProgramId());
        pref.close();

        holder.title.setText(program.getTitle());
        holder.description.setText(program.getSmallDescription());
        holder.buyDate.setText(ticket.getBuyDate());
        holder.projectionDate.setText(program.getProduction());
        holder.img.setImageResource(program.getPrimaryImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BarCodeActivity.class);
                context.startActivity(intent);
            }
        });

        holder.location.setText(ticket.getPlaces());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
