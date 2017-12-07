package commaciejprogramuje.facebook.widgetonaryenglish;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Translator {
    private HashMap<String, String> map = null;

    Translator(Context context, String myKey) {
        try {
            map = new HashMap<>();
            String testLine;
            String firstLetterOfMyKey = String.valueOf(myKey.charAt(0)).toLowerCase();
            String tempFileName = "output_" + firstLetterOfMyKey + ".txt";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(tempFileName)));

            //amadeus : Amadeus : (rzecz.) Amadeusz

            while ((testLine = bufferedReader.readLine()) != null) {
                if (!testLine.isEmpty()) {
                    String word = testLine.substring(0, testLine.indexOf(":") - 1);
                    String meaning = testLine.replace(word + " :", "");
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

    public HashMap<String, String> getMap() {
        return map;
    }
}
