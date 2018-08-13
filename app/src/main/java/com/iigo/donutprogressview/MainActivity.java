package com.iigo.donutprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iigo.library.DonutProgressView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private DonutProgressView donutProgressView;
    private Disposable updateProgressDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donutProgressView = findViewById(R.id.dpv_loading);
        updateProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (updateProgressDisposable != null){
            updateProgressDisposable.dispose();
        }
    }

    private void updateProgress(){
        updateProgressDisposable = Observable.interval(0, 50, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int progress = (donutProgressView.getProgress() + 1) % 100;
                        donutProgressView.setProgress(progress);
                    }
                });
    }
}
