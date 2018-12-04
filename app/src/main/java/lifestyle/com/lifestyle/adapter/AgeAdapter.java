package lifestyle.com.lifestyle.adapter;

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

public class AgeAdapter extends RecyclerView.Adapter<AgeAdapter.MyViewHolder> {

    private List<Integer> list;
    private Context context;
    private int selectedItem = -1;
    private IClickAdapter<Integer> clickAdapter;

    public AgeAdapter(Context context, List<Integer> list,IClickAdapter<Integer> clickAdapter) {
        this.list = list;
        this.context = context;
        this.clickAdapter = clickAdapter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number)
        TextView tvNumber;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickAdapter != null) {
                        selectedItem = getAdapterPosition();
                        notifyDataSetChanged();
                        clickAdapter.click(list.get(selectedItem), selectedItem);
                    }
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_age, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNumber.setText(String.valueOf(list.get(position)));
        if (selectedItem > -1 && selectedItem == position) {
            holder.tvNumber.setBackground(context.getDrawable(R.drawable.my_circle));
            holder.tvNumber.setTextSize(20);
            holder.tvNumber.setTextColor(context.getResources().getColor(R.color.light_blue));
        }
        else {
            holder.tvNumber.setBackground(null);
            holder.tvNumber.setTextSize(18);
            holder.tvNumber.setTextColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public void setAge(int age) {
        int size = list.size();
        for (int i = 0; i < size; i++ ){
            if(list.get(i) == age){
                selectedItem = i;
                notifyDataSetChanged();
                clickAdapter.click(list.get(selectedItem), selectedItem);
                break;
            }
        }
    }
}
