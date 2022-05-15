package ru.mirea.lukyanchuk.loadermanger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>{
    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234;


    EditText wordGiven;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordGiven = (EditText) findViewById(R.id.editTextTextPersonName);
        textView = (TextView) findViewById(R.id.textView);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle bundle) {
        if (id == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + id, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, bundle);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished");
            Toast.makeText(this, "onLoadFinished:", Toast.LENGTH_SHORT).show();
            textView.setText(data);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    public void onClick(View view){
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, wordGiven.getText().toString());
        getSupportLoaderManager().initLoader(LoaderID, bundle, this);
    }
}