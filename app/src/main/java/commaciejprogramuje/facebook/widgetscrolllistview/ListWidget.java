package commaciejprogramuje.facebook.widgetscrolllistview;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class ListWidget extends AppWidgetProvider {
    public static final String TEXT_KEY = "textKey";
    public static final String WIDGET_TEXT_KEY = "widgetTextKey";
    String myText = "";

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews mView = new RemoteViews(context.getPackageName(), R.layout.list_widget);
            mView.setTextViewText(R.id.appwidget_text, myText);

            mView.setOnClickPendingIntent(R.id.buttonQ, getMyPendingIntent(context, appWidgetIds, ("Q"), 1));
            mView.setOnClickPendingIntent(R.id.buttonW, getMyPendingIntent(context, appWidgetIds, ("W"), 1));
            mView.setOnClickPendingIntent(R.id.buttonE, getMyPendingIntent(context, appWidgetIds, ("E"), 1));
            mView.setOnClickPendingIntent(R.id.buttonR, getMyPendingIntent(context, appWidgetIds, ("R"), 4));
            mView.setOnClickPendingIntent(R.id.buttonT, getMyPendingIntent(context, appWidgetIds, ("T"), 5));
            mView.setOnClickPendingIntent(R.id.buttonY, getMyPendingIntent(context, appWidgetIds, ("Y"), 6));
            mView.setOnClickPendingIntent(R.id.buttonU, getMyPendingIntent(context, appWidgetIds, ("U"), 7));
            mView.setOnClickPendingIntent(R.id.buttonI, getMyPendingIntent(context, appWidgetIds, ("I"), 8));
            mView.setOnClickPendingIntent(R.id.buttonO, getMyPendingIntent(context, appWidgetIds, ("O"), 9));
            mView.setOnClickPendingIntent(R.id.buttonP, getMyPendingIntent(context, appWidgetIds, ("P"), 10));
            mView.setOnClickPendingIntent(R.id.buttonA, getMyPendingIntent(context, appWidgetIds, ("A"), 11));
            mView.setOnClickPendingIntent(R.id.buttonS, getMyPendingIntent(context, appWidgetIds, ("S"), 12));
            mView.setOnClickPendingIntent(R.id.buttonD, getMyPendingIntent(context, appWidgetIds, ("D"), 13));
            mView.setOnClickPendingIntent(R.id.buttonF, getMyPendingIntent(context, appWidgetIds, ("F"), 14));
            mView.setOnClickPendingIntent(R.id.buttonG, getMyPendingIntent(context, appWidgetIds, ("G"), 15));
            mView.setOnClickPendingIntent(R.id.buttonH, getMyPendingIntent(context, appWidgetIds, ("H"), 16));
            mView.setOnClickPendingIntent(R.id.buttonJ, getMyPendingIntent(context, appWidgetIds, ("J"), 17));
            mView.setOnClickPendingIntent(R.id.buttonK, getMyPendingIntent(context, appWidgetIds, ("K"), 18));
            mView.setOnClickPendingIntent(R.id.buttonL, getMyPendingIntent(context, appWidgetIds, ("L"), 19));
            mView.setOnClickPendingIntent(R.id.buttonZ, getMyPendingIntent(context, appWidgetIds, ("Z"), 20));
            mView.setOnClickPendingIntent(R.id.buttonX, getMyPendingIntent(context, appWidgetIds, ("X"), 21));
            mView.setOnClickPendingIntent(R.id.buttonC, getMyPendingIntent(context, appWidgetIds, ("C"), 22));
            mView.setOnClickPendingIntent(R.id.buttonV, getMyPendingIntent(context, appWidgetIds, ("V"), 23));
            mView.setOnClickPendingIntent(R.id.buttonB, getMyPendingIntent(context, appWidgetIds, ("B"), 24));
            mView.setOnClickPendingIntent(R.id.buttonN, getMyPendingIntent(context, appWidgetIds, ("N"), 25));
            mView.setOnClickPendingIntent(R.id.buttonM, getMyPendingIntent(context, appWidgetIds, ("M"), 26));
            mView.setOnClickPendingIntent(R.id.buttonClear, getMyPendingIntent(context, appWidgetIds, ("clear"), 27));
            mView.setOnClickPendingIntent(R.id.buttonBack, getMyPendingIntent(context, appWidgetIds, ("back"), 28));

            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);

            // tu przekazuję do WidgetDataProvider
            if(myText != null) {
                intent.putExtra(WIDGET_TEXT_KEY, myText);
            }



            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            mView.setRemoteAdapter(appWidgetId, R.id.widgetCollectionList, intent);
            appWidgetManager.updateAppWidget(appWidgetId, mView);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    private PendingIntent getMyPendingIntent(Context context, int[] appWidgetIds, String keyStone, int requestCode) {
        Intent intent = new Intent(context, ListWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        String temp;
        if (keyStone.equals("back")) {
            if (myText == null) {
                temp = null;
            } else if (myText.length() == 1) {
                temp = null;
            } else {
                temp = myText.substring(0, myText.length() - 1);
            }
        } else if (keyStone.equals("clear")) {
            temp = null;
        } else {
            if (myText == null) {
                temp = keyStone;
            } else {
                temp = (myText + keyStone);
            }
        }
        intent.putExtra(TEXT_KEY, temp);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        return PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        myText = intent.getStringExtra(TEXT_KEY);
        super.onReceive(context, intent);
    }
}

