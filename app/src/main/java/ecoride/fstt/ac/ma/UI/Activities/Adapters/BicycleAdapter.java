package ecoride.fstt.ac.ma.UI.Activities.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ecoride.fstt.ac.ma.R;
import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;
import ecoride.fstt.ac.ma.UI.Activities.Others.OnPriceClickListener;
import ecoride.fstt.ac.ma.UI.Activities.ViewHolders.BicycleHolder;

public class BicycleAdapter extends RecyclerView.Adapter<BicycleHolder> {

    private OnPriceClickListener clickListener;
    private List<Bicycle> bicycleList = new ArrayList<>();

    public BicycleAdapter(OnPriceClickListener clickListener, List<Bicycle> bicycleList) {
        this.clickListener = clickListener;
        this.bicycleList = bicycleList;
    }

    public BicycleAdapter(OnPriceClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setBicycleList(List<Bicycle> bicycleList) {
        this.bicycleList = bicycleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BicycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BicycleHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.bicycle_item, parent, false),
                clickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BicycleHolder holder, int position) {
        holder.bindData(bicycleList.get(position));
    }

    @Override
    public int getItemCount() {
        return bicycleList.size();
    }

    public Bicycle getItem(int position) {
        return bicycleList.get(position);
    }
}
