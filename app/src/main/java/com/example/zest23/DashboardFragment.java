package com.example.zest23;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

//import com.jabirdeveloper.tinderswipefragment.CardStackAdapter;
//import com.jabirdeveloper.tinderswipefragment.CardStackCallback;
//import com.jabirdeveloper.tinderswipefragment.ItemModel;
//import com.jabirdeveloper.tinderswipefragment.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {


    private static final String TAG = DashboardFragment.class.getSimpleName();
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init(root);
        return root;
    }

    private void init(View root) {
        CardStackView cardStackView = root.findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample1, "Cyclothon", "19", "JM ROAD"));
        items.add(new ItemModel(R.drawable.sample2, "Mascot", "20", "COEP Ground"));
        items.add(new ItemModel(R.drawable.sample3, "Kabaddi", "20", "Hostel Ground"));
        items.add(new ItemModel(R.drawable.sample4, "Marathon", "19", "FC Road"));
        items.add(new ItemModel(R.drawable.sample5, "Cricket", "19", "COEP Ground"));

        return items;
    }

}