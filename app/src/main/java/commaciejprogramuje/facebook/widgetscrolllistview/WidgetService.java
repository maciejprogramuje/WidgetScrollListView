package commaciejprogramuje.facebook.widgetscrolllistview;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by 5742ZGPC on 2017-09-03.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        WidgetDataProvider dataPrivider = new WidgetDataProvider(getApplicationContext(), intent);
        return dataPrivider;
    }
}
