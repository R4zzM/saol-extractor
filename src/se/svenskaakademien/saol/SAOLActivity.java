package se.svenskaakademien.saol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import se.svenskaakademien.saol.searchengine.NativeLib;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SAOLActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SAOLActivity.java", "onCreate: START");

        NativeLib saoldict = new NativeLib();
        String saolff = "/sdcard/saolff.sox";
        String fonregel = "/sdcard/fonregel.cry";
        String saolarticles = "/sdcard/saolarticles.sox";

        saoldict.open(saolff, fonregel, saolarticles);

        File outputFile = new File("/sdcard/saoldict.txt");
        BufferedWriter writer = null;
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try {
            outputFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (Exception e) {
            Log.d("SAOLActivity.java", "ERROR CREATING FILE!!!");
            e.printStackTrace();
        }

        // The first part...
        int numberOfEntries = saoldict.getNumberOfEntries();
        int chunkSize = 10000;
        int chunks = numberOfEntries / chunkSize;

        int currentChunk;
        for (currentChunk = 0; currentChunk < chunks; currentChunk++) {
            List<Word> wordList = saoldict.getEntriesFromIndex(currentChunk * chunkSize, (currentChunk + 1) * chunkSize);
            try {
                printToFile(writer, wordList);
                wordList = null; // allow gc.
            } catch (Exception e) {
                Log.d("SAOLActivity.java", "ERROR WHEN PRINTING DICT TO FILE!!!");
                e.printStackTrace();
            }
        }

        // Then the rest...
        List<Word> wordList = saoldict.getEntriesFromIndex(currentChunk * chunkSize, numberOfEntries);
        try {
            printToFile(writer, wordList);
        } catch (Exception e) {
            Log.d("SAOLActivity.java", "ERROR WHEN PRINTING DICT TO FILE!!!");
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        saoldict.close();
        setContentView(R.layout.main);

        Log.d("SAOLActivity.java", "onCreate: END");
    }

    private void printToFile(BufferedWriter writer, List<Word> wordlist) throws IOException {
        Log.d("SAOLActivity.java", "printToFile: START");
        Log.d("SAOLActivity.java", "Writing to file...");
        for (int i = 0; i < wordlist.size(); i++) {
            if (i % 1000 == 0) {
                Log.d("SAOLActivity.java", "printToFile: " + i + " out of " + wordlist.size() + " entries written.");
            }
            writer.write(wordlist.get(i).getWord() + "\n");
        }
        Log.d("SAOLActivity.java", "printToFile: END");
    }
}