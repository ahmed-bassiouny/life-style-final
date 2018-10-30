package lifestyle.com.lifestyle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lifestyle.com.lifestyle.R;

public class HeightAdapter extends RecyclerView.Adapter<HeightAdapter.MyViewHolder> {

    private List<Integer> list;
    private Context context;
    private int selectedItem = -1;

    public HeightAdapter(Context context, List<Integer> list) {
        this.list = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number)
        TextView tvNumber;
        @BindView(R.id.arrow)
        ImageView arrow;
        @BindView(R.id.linear)
        LinearLayout linear;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_height, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNumber.setText(String.valueOf(list.get(position)));
        if (selectedItem > -1 && selectedItem == position) {
            holder.linear.setBackgroundColor(context.getResources().getColor(R.color.light_blue));
            holder.tvNumber.setTextColor(context.getResources().getColor(R.color.white));
            holder.arrow.setVisibility(View.VISIBLE);
        }
        else {
            holder.linear.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.tvNumber.setTextColor(context.getResources().getColor(R.color.light_blue));
            holder.arrow.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

}
