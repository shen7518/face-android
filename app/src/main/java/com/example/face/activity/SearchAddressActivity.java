package com.example.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.face.R;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class SearchAddressActivity extends AppCompatActivity {

    @BindView(R.id.tv_a_search)
    SearchView searchView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_address_search);
        ButterKnife.bind(this);
//        searchView.setText("title");
//        ah.myInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<Account>() {
//                    @Override
//                    public void onComplete() {
//
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException){
//                            HttpException he = (HttpException)e;
//                            if (he.code()==600){
//                                try {
//                                    Log.d("biz error {}",he.response().errorBody().string());
//                                } catch (IOException e1) {
//                                    e1.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                    @Override
//                    public void onNext(Account a) {
//                        title.setText("title");
//                        time.setText("time");
//                        address.setText("address");
//                    }
//                });
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
Log.d("tt","right");
            }
        });
    }

}
