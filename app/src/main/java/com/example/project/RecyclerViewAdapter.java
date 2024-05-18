package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RiverViewHolder>  {
    private ArrayList<River> rivers;
    private final ArrayList<River> allRivers;

    private final Context context;

    public RecyclerViewAdapter(Context context, ArrayList<River> rivers) {
        this.context = context;
        this.rivers = rivers;
        this.allRivers = new ArrayList<>(rivers);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RiverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.river_item, parent, false);
        return new RiverViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.RiverViewHolder holder, int position) {
        String river = rivers.get(position).getName();
        String location = rivers.get(position).getLocation();
        Integer size = rivers.get(position).getSize();

        holder.river.setText(String.format("Namn: %s", river));
        holder.location.setText(String.format("Anslutning: %s", location));
        holder.size.setText(String.format("Längd: %skm", size.toString()));
    }

    @Override
    public int getItemCount() {
        return rivers.size();
    }

    public void filter(Filter filter, String query) {
        rivers = filter.apply(allRivers, query);
        notifyDataSetChanged();
    }

    public class RiverViewHolder extends RecyclerView.ViewHolder {

        private TextView river, location, size;

        public RiverViewHolder(final View itemView) {
            super(itemView);
            river = itemView.findViewById(R.id.river);
            location = itemView.findViewById(R.id.location);
            size = itemView.findViewById(R.id.size);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    River river = rivers.get(getAdapterPosition());
                    Log.d("==>", String.format("Navigated to %s", river.getID()));
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("name", river.getName());
                    intent.putExtra("location", river.getLocation());
                    intent.putExtra("size", river.getSize().toString());
                    intent.putExtra("aux", river.getAuxdata());
                    context.startActivity(intent);
                }
            });
        }

    }
}
