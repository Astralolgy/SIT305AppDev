package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LFAdvertsView extends AppCompatActivity {


    RecyclerView recyclerViewMain;
    VerticalAdapter vAdapter;
    LostFoundItemDB lostFoundItemDB;
    List<LostFoundItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lfadverts_view);
        recyclerViewMain = findViewById(R.id.recyclerView);
        lostFoundItemDB = new LostFoundItemDB(this);
        createItemList();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        createItemList();
    }

    private void createItemList()
    {
        itemList = lostFoundItemDB.getAllItem();
        vAdapter = new VerticalAdapter(itemList, new onItemClickListener()
        {
            @Override
            public void itemClick(LostFoundItem lostfoundItem) {
                Intent intent = new Intent(LFAdvertsView.this, DeleteItem.class);
                intent.putExtra("id", lostfoundItem.getId());
                intent.putExtra("type", lostfoundItem.getType());
                intent.putExtra("itemName", lostfoundItem.getItemName());
                intent.putExtra("contact", lostfoundItem.getContact());
                intent.putExtra("description", lostfoundItem.getDescription());
                intent.putExtra("time", lostfoundItem.getTime());
                intent.putExtra("longitude", lostfoundItem.getLng());
                intent.putExtra("latitude", lostfoundItem.getLat());
                startActivity(intent);
            }
        });
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMain.setAdapter(vAdapter);
    }

    public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

        private List<LostFoundItem> itemList;
        private final onItemClickListener listener;

        public VerticalAdapter(List<LostFoundItem> itemList, onItemClickListener listener) {
            this.itemList = itemList;
            this.listener = listener;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemTypeTextView;
            private TextView itemTextView;
            private TextView timeTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                itemTypeTextView = itemView.findViewById(R.id.itemTypeTV);
                itemTextView = itemView.findViewById(R.id.itemNameTV);
                timeTextView = itemView.findViewById(R.id.LFDateTV);
            }

            public void bind(LostFoundItem lostFoundItem, final onItemClickListener listener) {
                itemTypeTextView.setText(lostFoundItem.getType());
                itemTextView.setText(lostFoundItem.getItemName());
                timeTextView.setText(lostFoundItem.getTime());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.itemClick(lostFoundItem);
                    }
                });
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_found_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            LostFoundItem lostFoundItem = itemList.get(position);
            holder.bind(lostFoundItem, listener);
        }

        @Override
        public int getItemCount() {
            if (this.itemList == null) {
                return 0;
            }
            return this.itemList.size();
        }
    }

    public interface onItemClickListener
    {
        void itemClick(LostFoundItem lostFoundItem);
    }

}