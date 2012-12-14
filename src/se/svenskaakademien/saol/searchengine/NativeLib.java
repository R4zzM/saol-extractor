/**
 *---------------------------------------------------------------------
 * Copyright Ericsson AB 2012
 *
 * All rights reserved. No part of this computer programs(s) may be
 * used, reproduced, stored in any retrieval system, or transmitted,
 * in any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise without prior written permission of
 * Ericsson AB.
 *---------------------------------------------------------------------
 */
package se.svenskaakademien.saol.searchengine;

import java.util.ArrayList;
import java.util.List;

import se.svenskaakademien.saol.Word;
import android.util.Log;

/**
 * 
 * Created 20 feb 2012
 * 
 * @author erasmat
 */
public class NativeLib {

    static {
        System.loadLibrary("soaldict");
    }

    private native int getparam_d();

    private native int getparam_homograph();

    private native int getparam_i();

    private native byte[] getparam_p();

    private native byte[] getparam_word();

    private native byte[] getparam_wordclass();

    private native int sm_getnext_result();

    private native int sm_getnext_result2();

    private native int sm_startfullsearch(byte[] paramArrayOfByte);

    private native int sm_startwildcardsearch(byte[] paramArrayOfByte);

    private native void sm_close();

    private native byte[] sm_getarticle(int paramInt);

    private native byte[] sm_getword(int paramInt);

    private native int sm_getwordinfo(int paramInt);

    private native int sm_livesearch(byte[] paramArrayOfByte);

    private native int sm_nentries();

    private native int sm_open(String paramString1, String paramString2, String paramString3);

    public void open(String paramString1, String paramString2, String paramString3) {
        Log.d("NativeLib.java", "open: START");
        int retval = sm_open(paramString1, paramString2, paramString3);
        Log.d("NativeLib.java", "sm_open(...) retval = " + retval);
        Log.d("NativeLib.java", "open: END");
    }

    public void close() {
        Log.d("NativeLib.java", "close: START");
        sm_close();
        Log.d("NativeLib.java", "close: END");
    }

    public int getNumberOfEntries() {
        Log.d("NativeLib.java", "getNumberOfEntries: START");
        int retval = sm_nentries();
        Log.d("NativeLib.java", "sm_nentries(...) retval: " + retval);
        Log.d("NativeLib.java", "getNumberOfEntries: END");
        return retval;
    }

    // returns the entries int the interval [index1, index2)
    // TODO: Finish!!!
    public List<Word> getEntriesFromIndex(int index1, int index2) {
        Log.d("NativeLib.java", "getEntriesFromIndex: START");

        List<Word> words = new ArrayList<Word>();

        for (int i = index1; i < index2; i++) {

            if (i % 1000 == 0) {
                Log.d("NativeLib.java", "getEntriesFromIndex: Reading entries " + i + " to " + index2);
            }

            int retval = sm_getwordinfo(i);
            // Log.d("NativeLib.java", "sm_getwordinfo(...) retval = " + retval);

            if (retval > 0) {

                byte[] wordTmp = getparam_word();
                int homograph = getparam_homograph();
                byte[] wordclassTmp = getparam_wordclass();
                // int paramITmp = getparam_i();
                // byte[] paramPTmp = getparam_p();
                // int paramDTmp = getparam_d();

                String word = null;
                String wordclass = null;
                // String paramI = null;
                // String paramP = null;
                // String paramD = null;

                try {
                    word = new String(wordTmp, "ISO-8859-1");
                    wordclass = new String(wordclassTmp, "ISO-8859-1");
                    // paramI = new String(paramITmp, "ISO-8859-1");
                    // paramP = new String(paramPTmp, "ISO-8859-1");
                    // paramD = new String(paramDTmp, "ISO-8859-1");
                } catch (Exception e) {
                    Log.d("NativeLib.java", "EXCEPTION WHILE DECODING WORD!!!!");
                    e.printStackTrace();
                }
                // Log.d("NativeLib.java", "ENTRY: word = " + word + ", wordclass = " + wordclass + ", homograph = " + homograph);

                Word wordObject = new Word();
                wordObject.setWord(word);
                wordObject.setWordclass(wordclass);
                wordObject.setHomograph(homograph);
                words.add(wordObject);
            }
        }

        Log.d("NativeLib.java", "getEntriesFromIndex: END");
        return words;
    }

    // Not used yet.
    public String getArticle(int index) {
        Log.d("NativeLib.java", "getArticle: START");

        byte[] b = sm_getarticle(index);
        String article = null;

        try {
            article = new String(b, "ISO-8859-1");
        } catch (Exception e) {
            Log.d("NativeLib.java", "EXCEPTION WHILE DECODING WORD!!!!");
            e.printStackTrace();
        }
        Log.d("NativeLib.java", "ARTICLE: " + article);

        Log.d("NativeLib.java", "getArticle: END");
        return article;
    }

}
