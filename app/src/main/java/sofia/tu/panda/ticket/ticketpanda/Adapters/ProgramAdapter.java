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

import sofia.tu.panda.ticket.ticketpanda.Activities.ProgramItemActivity;
import sofia.tu.panda.ticket.ticketpanda.Objects.Program;
import sofia.tu.panda.ticket.ticketpanda.R;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {

    private Context context;

    private static List<Program> data;

    public ProgramAdapter(Context context, List<Program> objects) {
        this.context = context;
        data = objects;
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_program, parent, false);
        return new ProgramViewHolder(itemView);
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView img, menu;
        public CardView cardView;

        public ProgramViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.program_title);
            description = (TextView) view.findViewById(R.id.program_description);
            img = (ImageView) view.findViewById(R.id.program_image);

            cardView = (CardView) view.findViewById(R.id.program_card);

            menu = (ImageView) view.findViewById(R.id.program_menu);
        }
    }

    @Override
    public void onBindViewHolder(final ProgramViewHolder holder, final int position) {
        Program program = data.get(position);
        holder.title.setText(program.getTitle());
        holder.description.setText(program.getSmallDescription());
        holder.img.setImageResource(data.get(position).getPrimaryImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProgramItemActivity.class);
                intent.putExtra("PROGRAM", data.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
