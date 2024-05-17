package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RiverViewHolder>  {
    private List<River> rivers;
    private final List<River> allRivers;

    public RecyclerViewAdapter(List<River> rivers) {
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
        String aux = rivers.get(position).getAuxdata();
        Integer auxLength = aux.split(",").length;

        holder.river.setText(String.format("Namn: %s", river));
        holder.location.setText(String.format("Anslutning: %s", location));
        holder.size.setText(String.format("Längd: %skm", size.toString()));
        holder.aux.setText(String.format("Passerar (%d): %s", auxLength, aux));
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

        private TextView river, location, size, aux;

        public RiverViewHolder(final View itemView) {
            super(itemView);
            river = itemView.findViewById(R.id.river);
            location = itemView.findViewById(R.id.location);
            size = itemView.findViewById(R.id.size);
            aux = itemView.findViewById(R.id.aux);
        }

    }
}
