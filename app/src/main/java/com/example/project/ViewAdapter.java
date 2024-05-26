package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
    /* ================== VARIABLES ================== */
    private List<InventoryItem> items;
    private LayoutInflater layoutInflater;
    /* =============================================== */

    /* ================== CLASS ================== */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
        }
    }
    /* =========================================== */

    public ViewAdapter(Context context, List<InventoryItem> inventoryItems) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = inventoryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.text_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryItem item = items.get(position);
        holder.textView.setText(item.GetBasicString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
