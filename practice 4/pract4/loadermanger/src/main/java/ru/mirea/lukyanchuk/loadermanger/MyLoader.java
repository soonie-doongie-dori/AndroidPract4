package ru.mirea.lukyanchuk.loadermanger;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.concurrent.ThreadLocalRandom;

public class MyLoader extends AsyncTaskLoader<String> {
    private String wordGiven;
    public static final String ARG_WORD = "word";


    public StringBuffer word;
    public String result;
    public int count;
    public int max;

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            wordGiven = args.getString(ARG_WORD);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        result = "";

        word = new StringBuffer(wordGiven);
        max = word.length();

        for (int i = 0; i < max; i++) {
            count = ThreadLocalRandom.current().nextInt(word.length());
            result += word.charAt(count);
            word.deleteCharAt(count);
        }

        return result;
    }

}
