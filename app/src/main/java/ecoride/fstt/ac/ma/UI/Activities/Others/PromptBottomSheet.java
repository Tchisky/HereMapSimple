package ecoride.fstt.ac.ma.UI.Activities.Others;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import ecoride.fstt.ac.ma.R;
import ecoride.fstt.ac.ma.Repository.Dao.BicycleDao;
import ecoride.fstt.ac.ma.Repository.Dao.StationsDao;
import ecoride.fstt.ac.ma.Repository.Database.BicycleDatabase;
import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;
import ecoride.fstt.ac.ma.Repository.Modals.Station;
import ecoride.fstt.ac.ma.UI.Activities.Adapters.BicycleAdapter;

public class PromptBottomSheet extends BottomSheetDialogFragment implements OnPriceClickListener {

    private RecyclerView recycler;
    private BicycleAdapter adapter;
    private BicycleDao bicycleDao;
    private StationsDao stationsDao;
    private TextView bottomSheetTitle;
    private int stationId;
    private OnBicycleClick onBicycleClick;

    public PromptBottomSheet(OnBicycleClick onBicycleClick) {
        this.onBicycleClick = onBicycleClick;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                ecoride.fstt.ac.ma.R.layout.bottom_sheet_prompt,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.recycler_availableBicycles);
        bottomSheetTitle = view.findViewById(R.id.bottom_sheet_title);
    }

    @Override
    public void onStart() {
        super.onStart();

        // init dao
        bicycleDao = BicycleDatabase.getInstance(getContext()).bicycleDao();
        stationsDao = BicycleDatabase.getInstance(getContext()).stationsDao();
        Station current = stationsDao.search(stationId);

        // set title
        String titleText = current.getAvailableBicyclesCount() + " bicycles are available in " + current.getName();
        bottomSheetTitle.setText(titleText);

        // init adapter
        adapter = new BicycleAdapter(this);

        // init recycler
        recycler.setHasFixedSize(false);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new DividerItemDecoration(
                getContext(),
                DividerItemDecoration.VERTICAL
        ));

        // attach adapter to recycler
        recycler.setAdapter(adapter);


    }

    @Override
    public void onResume() {
        super.onResume();

        bicycleDao.watchAvailableBicycles(stationId).observe(this, new Observer<List<Bicycle>>() {
            @Override
            public void onChanged(List<Bicycle> bicycles) {
                adapter.setBicycleList(bicycles);
            }
        });

    }

    /**
     * on price click listener send the captured bicycle object and send it back to
     * the host activity
     *
     * @param position
     */
    @Override
    public void onPriceClick(int position) {
        Bicycle clickedItem = adapter.getItem(position);
        onBicycleClick.onBicycleClick(clickedItem);
    }
}
