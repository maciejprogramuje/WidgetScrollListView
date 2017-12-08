package commaciejprogramuje.facebook.widgetonaryenglish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@SuppressLint("NewApi")
class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private List<String> mCollections = new ArrayList<>();
    private Context context = null;
    private String myText = "";

    WidgetDataProvider(Context context, Intent intent) {
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
        RemoteViews mView = new RemoteViews(context.getPackageName(), R.layout.my_layout_list_item);
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
        mCollections.clear();
        mCollections = findKey(myText);
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    private List<String> findKey(String myKey) {
        List<String> resultsList = new ArrayList<>();
        if (myKey.length() > 0) {
            myKey = myKey.toLowerCase();
            for (HashMap.Entry<String, String> e : new Translator(context, myText).getMap().entrySet()) {
                if (e.getKey().startsWith(myKey)) {
                    //resultsList.add(e.getKey() + " " + e.getValue());
                    resultsList.add(e.getValue());
                }
            }
            if(resultsList.isEmpty()) {
                resultsList.add(context.getString(R.string.no_matches_found));
            }
        } else {
            resultsList.add(context.getString(R.string.enter_longer_word));
        }
        Collections.sort(resultsList, String.CASE_INSENSITIVE_ORDER);

        return resultsList;
    }
}
