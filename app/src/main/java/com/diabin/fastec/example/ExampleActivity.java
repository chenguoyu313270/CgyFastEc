package com.diabin.fastec.example;

import android.widget.TextView;
import android.widget.Toast;

import com.flj.latte.activities.ProxyActivity;
import com.flj.latte.delegates.latteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.ISuccess;

public class ExampleActivity extends ProxyActivity {
    private TextView mTextview;

    @Override
    public latteDelegate setRootDelegare() {


        return new ExampleDelagate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextview=findViewById(R.id.tv);
        testRestClient();
    }

    private void testRestClient(){//ExampleDelagate 2499
        RestClient.builder().url("http://news.baidu.com/")
//                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mTextview.setText(response);
                        Toast.makeText(getApplication(),response,Toast.LENGTH_LONG).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).builder()
                .get();
    }
}
