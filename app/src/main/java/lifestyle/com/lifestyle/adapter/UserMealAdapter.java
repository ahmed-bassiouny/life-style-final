package lifestyle.com.lifestyle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.model.Food;
import lifestyle.com.lifestyle.model.UserMeal;

public class UserMealAdapter extends RecyclerView.Adapter<UserMealAdapter.MyViewHolder> {

    private List<UserMeal> list;

    public UserMealAdapter(List<UserMeal> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_quantity)
        TextView tvQuantity;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_meal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserMeal item = list.get(position);
        holder.tvType.setText(item.getFoodType()+" : ");
        holder.tvName.setText(item.getFoodQuantity());
        holder.tvQuantity.setText(item.getQuantity());
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }
}
