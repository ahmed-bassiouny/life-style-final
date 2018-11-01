package lifestyle.com.lifestyle.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lifestyle.com.lifestyle.R;
import lifestyle.com.lifestyle.helper.Constants;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     WorkTypeListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link WorkTypeListDialogFragment.Listener}.</p>
 */
public class WorkTypeListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    public Listener mListener;
    // TODO: Customize parameters

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worktype_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getArguments().getString(Constants.TYPE).equals(Constants.WORK_TYPE)) {
            recyclerView.setAdapter(new WorkTypeAdapter());
        } else {
            recyclerView.setAdapter(new GoalAdapter());
        }
    }


    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onSelectItem(int position);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDesc;
        ImageView img;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_worktype_list_dialog_item, parent, false));
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            img = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onSelectItem(getAdapterPosition());
                    }
                }
            });
        }

    }

    private class WorkTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final String[] list = getResources().getStringArray(R.array.work_type);
        private final String[] listDesc = getResources().getStringArray(R.array.work_type_desc);
        private final int[] listImg = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvTitle.setText(list[position]);
            holder.img.setImageDrawable(getActivity().getDrawable(listImg[position]));
            if (listDesc[position].isEmpty()) {
                holder.tvDesc.setVisibility(View.GONE);
            } else {
                holder.tvDesc.setVisibility(View.VISIBLE);
                holder.tvDesc.setText(listDesc[position]);
            }
        }

        @Override
        public int getItemCount() {
            return list.length;
        }

    }

    private class GoalAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final String[] list = getResources().getStringArray(R.array.goal);
        private final int[] listImg = {R.drawable.f,R.drawable.g,R.drawable.h,};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvTitle.setText(list[position]);
            holder.img.setImageDrawable(getActivity().getDrawable(listImg[position]));
            holder.tvDesc.setVisibility(View.GONE);

        }

        @Override
        public int getItemCount() {
            return list.length;
        }

    }

}
