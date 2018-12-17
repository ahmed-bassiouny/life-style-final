package lifestyle.com.lifestyle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.MyGlide;
import lifestyle.com.lifestyle.model.Food;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {

    private List<Food> list;

    public MealAdapter() {
        this.list = new ArrayList<>();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_food)
        TextView tvFood;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_own_meal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Food item = list.get(position);
        holder.tvFood.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public List<Food> getList() {
        return list;
    }

    public void addFood(Food food) {
        int size = list.size();
        for(int i=0;i<size;i++){
            if(list.get(i).getFoodType() == food.getFoodType()){
                this.list.set(i,food);
                notifyItemChanged(i);
                return;
            }
        }
        this.list.add(0,food);
        notifyItemInserted(0);
    }
}
