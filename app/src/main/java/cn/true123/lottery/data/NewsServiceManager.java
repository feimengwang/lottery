package cn.true123.lottery.data;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.true123.lottery.App;
import cn.true123.lottery.model.News;
import cn.true123.lottery.utils.Constants;
import cn.true123.lottery.utils.NetUtil;
import mlog.true123.cn.lib.MLog;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by junbo on 30/9/2016.
 */

public class NewsServiceManager {
    private static volatile NewsServiceManager instance;
    OkHttpClient client;
    private Retrofit retrofit;

    static Map<Integer, NewsService> allService = new HashMap<>();


    public NewsServiceManager() {
        client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .cache(new Cache(new File(App.getAppContext().getCacheDir(), "news-news"), 1024 * 1024 * 100))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (!NetUtil.isNetAvailable()) {
                            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                        }
                        Response response = chain.proceed(request);

                        if (NetUtil.isNetAvailable()) {
                            return response.newBuilder()
                                    .addHeader("Cache-Control", "max-age=0")
                                    .removeHeader("Pragma")
                                    .build();
                        } else {
                            return response.newBuilder()
                                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=60*60*24*365")
                                    .removeHeader("pragma")
                                    .build();
                        }

                    }
                })
                .build();
    }

    public NewsService getService(int type) {
        NewsService newsService = allService.get(type);
        if (newsService == null) {
            retrofit = new Retrofit.Builder().baseUrl(NetUtil.getBaseUrl(type))
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
            allService.put(type, newsService);
        }
        return newsService;
    }

    public static NewsServiceManager getInstance() {
        if (instance == null) {
            synchronized (NewsServiceManager.class) {
                if (instance == null) {
                    instance = new NewsServiceManager();
                }
            }
        }

        return instance;
    }

    public Observable<Map<String, List<News>>> getNews(String cacheControl, String type, String id, int startPage) {
        return getService(Constants.NEWS_TYPE).getNewsList(cacheControl, type, id, startPage).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
