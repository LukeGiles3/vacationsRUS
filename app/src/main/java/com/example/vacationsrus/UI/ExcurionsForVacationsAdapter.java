package com.example.vacationsrus.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationsrus.R;
import com.example.vacationsrus.entities.Excursion;

import java.util.ArrayList;
import java.util.List;

public class ExcurionsForVacationsAdapter extends RecyclerView.Adapter<ExcurionsForVacationsAdapter.ExcurionsForVacationsViewHolder>{
    private final Context context;
    private final LayoutInflater mInflater;
    private List<Excursion> excursionsForVacationsList;

    public void setExcursionsForVacations(List<Excursion> excursions) {
        if (excursions != null) {
            this.excursionsForVacationsList = new ArrayList<>(excursions);
        } else {
            this.excursionsForVacationsList.clear();
        }
        notifyDataSetChanged();
    }
    public ExcurionsForVacationsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.excursionsForVacationsList = new ArrayList<>();
    }
    @NonNull
    @Override
    public ExcurionsForVacationsAdapter.ExcurionsForVacationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.excursion_list_item,parent,false);
        return new ExcurionsForVacationsAdapter.ExcurionsForVacationsViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ExcurionsForVacationsAdapter.ExcurionsForVacationsViewHolder holder, int position) {
        if(excursionsForVacationsList!=null) {
            Excursion current = excursionsForVacationsList.get(position);
            String title = current.getExcursionTitle();
            holder.excursionItemView.setText(title);
        } else {
            holder.excursionItemView.setText("Excursion title is empty");
        }
    }
    @Override
    public int getItemCount() {
        return excursionsForVacationsList.size();
    }
    public class ExcurionsForVacationsViewHolder extends RecyclerView.ViewHolder {
        private final TextView excursionItemView;
        public ExcurionsForVacationsViewHolder(@NonNull View itemView) {
            super(itemView);
            excursionItemView=itemView.findViewById(R.id.excursionItemText);
            itemView.setOnClickListener(view -> {
                int position=getAdapterPosition();
                final Excursion current = excursionsForVacationsList.get(position);
                Intent intent = new Intent(context, ExcursionDetails.class);
                intent.putExtra("id", current.getExcursionID());
                intent.putExtra("title", current.getExcursionTitle());
                intent.putExtra("startDate", current.getExcursionStartDate());
                intent.putExtra("endDate", current.getExcursionEndDate());
                context.startActivity(intent);
            });
        }
    }
}
