package com.example.face.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.face.R;
import com.example.face.adapter.PartnerAdapter;
import com.example.face.http.BaseObserver;
import com.example.face.http.HTTP;
import com.example.face.model.act.ActivityDetail;
import com.example.face.model.act.AidReq;
import com.example.face.model.act.ApplyButtonResp;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActDetailActivity extends BaseActivity {

    @BindView(R.id.tv_a_title)
    TextView title;
    @BindView(R.id.tv_a_time)
    TextView time;
    @BindView(R.id.tv_a_address)
    TextView address;
    @BindView(R.id.rcv_partner)
    RecyclerView partnerView;
    @BindView(R.id.btn_apply)
    Button appbtn;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    ActivityDetail d;
    ApplyButtonResp buttonResp;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail);
        ButterKnife.bind(this);
        initView();
        HTTP.activity.detail(getIntent().getExtras().getLong("aid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ActivityDetail>() {
                    @Override
                    public void onNext(ActivityDetail a) {
                        d=a;
                        title.setText(a.getTitle());
                        time.setText(a.getStime());
                        address.setText(a.getAddress());
                    }
                });
        HTTP.apply.isNeedApply(getIntent().getExtras().getLong("aid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean b) {
                        if (b){
                            appbtn.setText("立即申请");
                        }else {
                            appbtn.setText("已申请");
                        }
                    }
                });

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
                Log.d("tt", "right");
            }
        });
    }
    @OnClick({R.id.btn_apply})
    void apply(){
        AidReq r = AidReq.builder().aid(d.getAid()).build();
        HTTP.apply.apply(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Void>() {
                    @Override
                    public void onNext(Void v) {
                    }
                });
    }
    private void initView() {
        PartnerAdapter adapter = new PartnerAdapter(this);

        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this);
        managerHorizontal.setOrientation(RecyclerView.HORIZONTAL);

        partnerView.setLayoutManager(managerHorizontal);
        partnerView.setHasFixedSize(true);
        partnerView.setAdapter(adapter);
        adapter.setHorizontalDataList(getIntent().getExtras().getLong("aid"));
    }
}
