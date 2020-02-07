package ecoride.fstt.ac.ma.UI.Activities.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import ecoride.fstt.ac.ma.R;
import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;
import ecoride.fstt.ac.ma.UI.Activities.Others.OnPriceClickListener;

public class BicycleHolder extends RecyclerView.ViewHolder {

    private ImageView icon;
    private TextView title;
    private TextView availabilityTimeRange;
    private TextView availability;
    private MaterialButton price;

    public BicycleHolder(@NonNull View itemView, final OnPriceClickListener clickListener) {
        super(itemView);

        icon = itemView.findViewById(R.id.bicycle_icon);
        title = itemView.findViewById(R.id.bicycle_item_title);
        availabilityTimeRange = itemView.findViewById(R.id.bicycle_item_timeRange);
        price = itemView.findViewById(R.id.bicycle_item_price);
        availability = itemView.findViewById(R.id.bicycle_item_availability);

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onPriceClick(getAdapterPosition());
            }
        });

    }

    public void bindData(Bicycle bicycle) {

        title.setText(bicycle.getTitle());
        price.setText(bicycle.getPrice());
        availabilityTimeRange.setText(bicycle.getAvailabilityTimeRange());
        String availabilityMessage = bicycle.isAvailable() ? "Available" : "Unavailable";
        availability.setText(availabilityMessage);

    }
}
