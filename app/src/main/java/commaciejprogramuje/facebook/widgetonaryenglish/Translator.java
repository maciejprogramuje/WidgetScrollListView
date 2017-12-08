package commaciejprogramuje.facebook.widgetonaryenglish;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static commaciejprogramuje.facebook.widgetonaryenglish.MainActivity.FIRST_LANGUAGE;
import static commaciejprogramuje.facebook.widgetonaryenglish.MainActivity.SECOND_LANGUAGE;

class Translator {
    private HashMap<String, String> mapA = null;
    private HashMap<String, String> mapB = null;

    Translator(Context context, String myKey) {
        mapA = fillMap(FIRST_LANGUAGE, context, myKey);
        mapB = fillMap(SECOND_LANGUAGE, context, myKey);
    }

    private HashMap<String, String> fillMap(String prefix, Context context, String myKey) {
        HashMap<String, String> map = new HashMap<>();

        try {
            String testLine;
            String firstLetterOfMyKey = String.valueOf(myKey.charAt(0)).toLowerCase();
            String tempFileName = "output_" + prefix + "_" + firstLetterOfMyKey + ".txt";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(tempFileName)));

            while ((testLine = bufferedReader.readLine()) != null) {
                if (!testLine.isEmpty()) {
                    String word = testLine.substring(1, testLine.indexOf("]"));
                    String meaning = testLine.substring(testLine.indexOf("]") + 1);

                    if (meaning.isEmpty()) {
                        meaning = " ";
                    }

                    if (!map.containsKey(word)) {
                        map.put(word, meaning);
                        System.out.println(word + "="+meaning);
                    } else {
                        map.put(word, map.get(word) + "; " + meaning);
                        System.out.println(word + "="+map.get(word) + "; " + meaning);
                    }
                }
            }
            bufferedReader.close();
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String> getMapA() {
        return mapA;
    }

    public HashMap<String, String> getMapB() {
        return mapB;
    }
}
