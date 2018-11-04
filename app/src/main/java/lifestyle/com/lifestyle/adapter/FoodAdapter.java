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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private Context context;
    private List<Food> list;

    public FoodAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_calory)
        TextView tvCalory;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.tvType.setTextColor(context.getResources().getColor(R.color.light_blue));
            holder.tvName.setTextColor(context.getResources().getColor(R.color.light_blue));
            holder.tvCount.setTextColor(context.getResources().getColor(R.color.light_blue));
            holder.tvCalory.setTextColor(context.getResources().getColor(R.color.light_blue));
        } else {
            holder.tvType.setTextColor(context.getResources().getColor(R.color.dark_blue));
            holder.tvName.setTextColor(context.getResources().getColor(R.color.dark_blue));
            holder.tvCount.setTextColor(context.getResources().getColor(R.color.dark_blue));
            holder.tvCalory.setTextColor(context.getResources().getColor(R.color.dark_blue));
        }
        Food item = list.get(position);
        holder.tvType.setText(item.getFoodType());
        holder.tvName.setText(item.getName());
        holder.tvCount.setText(item.getQuantity());
        holder.tvCalory.setText(item.getCalories());
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }
    public void setList(List<Food> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
