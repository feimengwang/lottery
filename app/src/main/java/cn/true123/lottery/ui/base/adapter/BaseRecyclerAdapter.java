package cn.true123.lottery.ui.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.ui.fragment.listener.OnItemClickListener;
import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 2/11/2016.
 */

public abstract class BaseRecyclerAdapter<M, VH extends BaseRecyclerAdapter.BaseViewHoler> extends RecyclerView.Adapter<VH> {
    public List<M> list;
    public Context context;
    public int FOOTER_TYPE = 1;
    public int COMMON_TYPE = 2;

    public boolean hasFooter = false;
    public boolean isEnd = false;
    public boolean isFlushing = false;

    public void setFlushing(boolean isFlushing) {
        this.isFlushing = isFlushing;
    }

    public void setFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public void onEnd(boolean isEnd) {
        this.isEnd = isEnd;
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public List getData() {
        return list == null ? Collections.emptyList() : list;
    }

    public BaseRecyclerAdapter(List<M> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return createVH(parent, viewType);
    }


    public int getFooterLayoutResId() {
        return R.layout.adapter_footer;
    }

    public VH createVH(ViewGroup parent, final int viewType) {
        View v;
        if (FOOTER_TYPE == viewType) {
            v = LayoutInflater.from(context).inflate(getFooterLayoutResId(), parent, false);
        } else {
            v = LayoutInflater.from(context).inflate(getLayoutResId(), parent, false);
        }
        final VH vh = createVH(v, viewType);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null && FOOTER_TYPE != viewType) {
                    mOnItemClickListener.onItemClick(v, vh.getLayoutPosition());
                } else if (mOnItemClickListener != null && FOOTER_TYPE == viewType) {
                    mOnItemClickListener.onFooterClick(v, vh.getLayoutPosition());
                    isFlushing = true;
                }
            }
        });

        v.setClickable(!isFlushing);

        return vh;
    }

    public abstract VH createVH(View v, int ViewType);

    public abstract int getLayoutResId();

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOTER_TYPE;
        } else {
            return COMMON_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        // bindVH(holder, position);
        MLog.i("position=" + position + ";" + getItemCount());
        if ((position < getItemCount() - 1 && hasFooter) || (!hasFooter)) {
            bindVH(holder, position);
        } else if (hasFooter && position == getItemCount() - 1) {
            bindFooterVH(holder,position);
        }
    }

    public abstract void bindVH(VH holder, int position);

    public abstract void bindFooterVH(VH holder, int position);

    @Override
    public int getItemCount() {
        if (hasFooter) {
            return list.size() + 1;
        }
        return list.size();
    }

    public class BaseViewHoler extends RecyclerView.ViewHolder {

        public BaseViewHoler(View itemView) {
            super(itemView);
        }
    }


}
