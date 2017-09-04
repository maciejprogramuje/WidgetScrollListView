package commaciejprogramuje.facebook.widgetscrolllistview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {
    BufferedReader bufferedReader = null;
    String testLine;
    private HashMap<String, String> map = new HashMap<>();
    String outputFileName = "output.txt";

    public Translator(Context context) {
        Log.w("UWAGA", "tworzę translator");
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(outputFileName)));

            while ((testLine = bufferedReader.readLine()) != null) {
                if (!testLine.isEmpty()) {
                    String word = testLine.substring(0, testLine.indexOf(":") - 1);
                    String meaning = testLine.substring(testLine.indexOf("("));
                    if (meaning.isEmpty()) {
                        meaning = " ";
                    }

                    if (!map.containsKey(word)) {
                        map.put(word, meaning);
                    } else {
                        map.put(word, map.get(word) + " | " + meaning);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Translator(Parcel in) {
        testLine = in.readString();
        outputFileName = in.readString();
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
