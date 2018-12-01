package lifestyle.com.lifestyle.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.base.ui.IAdapter;
import lifestyle.com.lifestyle.model.Food;

public class FoodNameAdapter extends RecyclerView.Adapter<FoodNameAdapter.MyViewHolder> {

    private List<Food> list;
    private IAdapter<Food> adapter;
    public FoodNameAdapter(IAdapter<Food> adapter, List<Food> list) {
        this.list = list;
        this.adapter = adapter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.onClick(list.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_name, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Food item = list.get(position);
        holder.tvName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }
}
