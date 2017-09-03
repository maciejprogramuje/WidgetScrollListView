package commaciejprogramuje.facebook.widgetscrolllistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5742ZGPC on 2017-09-03.
 */

@SuppressLint("NewApi")
public class WidgetDataPrivider implements RemoteViewsService.RemoteViewsFactory {
    List<String> mCollections = new ArrayList<>();
    Context context = null;

    public WidgetDataPrivider(Context context, Intent intent) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mCollections.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews mView = new RemoteViews(context.getPackageName(), android.R.layout.simple_list_item_1);
        mView.setTextViewText(android.R.id.text1, mCollections.get(i));
        return mView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    private void initData() {
        for (int i = 0; i <10 ; i++) {
            mCollections.add("item: " + i);
        }
    }

    @Override
    public void onDestroy() {

    }
}
