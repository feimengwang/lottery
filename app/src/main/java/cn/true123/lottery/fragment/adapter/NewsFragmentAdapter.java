package cn.true123.lottery.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.true123.lottery.R;
import cn.true123.lottery.model.News;

/**
 * Created by junbo on 2/11/2016.
 */

public class NewsFragmentAdapter extends BaseRecyclerAdapter<News, NewsFragmentAdapter.NewsFragmentViewHolder> {


    public NewsFragmentAdapter(List<News> list, Context context) {
        super(list, context);
    }


    @Override
    protected NewsFragmentViewHolder createVH(View v, int viewType) {
        if (FOOTER_TYPE == viewType) {
            return new NewsFragmentFooterViewHolder(v);
        } else {
            return new NewsFragmentItemViewHolder(v);
        }
    }

    @Override
    public int getFooterLayoutResId() {
        return super.getFooterLayoutResId();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.news_item;
    }

    @Override
    public void bindVH(NewsFragmentViewHolder holder, int position) {
        NewsFragmentItemViewHolder vh = (NewsFragmentItemViewHolder) holder;
        News news = list.get(position);
        vh.time.setText(news.getPtime());
        vh.title.setText(news.getTitle());
        Picasso.with(context).load(news.getImgsrc()).resize(200, 200).into(vh.image);


    }

    @Override
    public void bindFooterVH(NewsFragmentViewHolder holder, int position) {
        NewsFragmentFooterViewHolder historyHolder = (NewsFragmentFooterViewHolder) holder;
        if (isEnd) {
            historyHolder.progressBar.setVisibility(View.GONE);
            historyHolder.footerText.setVisibility(View.VISIBLE);
            historyHolder.footerText.setText("没有更多。。。");


        } else if (isFlushing) {
            historyHolder.progressBar.setVisibility(View.VISIBLE);
            historyHolder.footerText.setVisibility(View.GONE);
        } else {
            historyHolder.progressBar.setVisibility(View.GONE);
            historyHolder.footerText.setVisibility(View.VISIBLE);
            historyHolder.footerText.setText("加载更多");
        }
    }

    class NewsFragmentViewHolder extends BaseRecyclerAdapter.BaseViewHoler {

        public NewsFragmentViewHolder(View itemView) {
            super(itemView);
        }
    }

    class NewsFragmentItemViewHolder extends NewsFragmentViewHolder {
        @BindView(R.id.imageView)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.publishTime)
        TextView time;


        public NewsFragmentItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class NewsFragmentFooterViewHolder extends NewsFragmentViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.footer_text)
        TextView footerText;

        public NewsFragmentFooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
