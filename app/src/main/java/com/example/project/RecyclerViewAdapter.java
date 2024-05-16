package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RiverViewHolder>  {
    private final ArrayList<River> rivers;

    public RecyclerViewAdapter(ArrayList<River> rivers) {
        this.rivers = rivers;
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

        holder.river.setText(river);
        holder.location.setText(location);
        holder.size.setText(size.toString());
        holder.aux.setText(aux);
    }

    @Override
    public int getItemCount() {
        return rivers.size();
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
