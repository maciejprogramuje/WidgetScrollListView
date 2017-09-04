package commaciejprogramuje.facebook.widgetscrolllistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5742ZGPC on 2017-09-03.
 */

@SuppressLint("NewApi")
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    List<String> mCollections = new ArrayList<>();
    Context context = null;
    String myText = "";
    Translator translator;

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        if(intent.getStringExtra(ListWidget.WIDGET_TEXT_KEY) != null) {
            this.myText = intent.getStringExtra(ListWidget.WIDGET_TEXT_KEY);
        }
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
        Log.w("UWAGA", myText);
        translator = new Translator(context);
        mCollections.clear();
        mCollections = translator.findKey(myText);
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }
}
