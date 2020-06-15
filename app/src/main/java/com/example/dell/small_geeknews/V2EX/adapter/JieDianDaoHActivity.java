package com.example.dell.small_geeknews.V2EX.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.small_geeknews.R;
import com.example.dell.small_geeknews.V2EX.adapter.bean.V2exBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import qdx.stickyheaderdecoration.NormalDecoration;

//石明洋  1808B
public class JieDianDaoHActivity extends AppCompatActivity {
    private static final String TAG = "JieDianDaoHActivity";
    private String Url = "https://www.v2ex.com";
    private ArrayList<V2exBean> list;
    private String title;
    private String text;
    private RecyclerView mRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_dian_dao_h);
        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    list = new ArrayList<>();

                    Document doc = Jsoup.connect(Url).get();
                    Elements elements = doc.select("div.cell");
                    for (Element element : elements) {
                        Elements titles = element.select("table tbody tr td span.fade");
                        String title = titles.text();
                        if(title.length()>0){
                            Log.e(TAG, "title: " + title);
                            Elements texts = element.select("table tbody tr td > a[href]");
                            String text = texts.text();
                            Log.e(TAG, "text: " + text);
                            list.add(new V2exBean(title,text));
                        }

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void setData() {
        mRlv.setLayoutManager(new LinearLayoutManager(JieDianDaoHActivity.this));
        RlvAdapter rlvAdapter = new RlvAdapter(list);
        //返回头布局的内容
        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return list.get(i).title;
            }
        };
        //自定义头布局,可不设置
        decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(final int i) {
                View inflate = LayoutInflater.from(JieDianDaoHActivity.this).inflate(R.layout.item_header,null);
                TextView tv = inflate.findViewById(R.id.tv);
                tv.setText(list.get(i).title);

                return inflate;
            }
        });

        mRlv.addItemDecoration(decoration);
        //头布局的点击事件
        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int i) {
                Toast.makeText(JieDianDaoHActivity.this, list.get(i).title, Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getContext(), FlowActivity.class));
//                startActivity(new Intent(getContext(), MaterialActivity.class));
            }
        });
        mRlv.setAdapter(rlvAdapter);
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);

    }
}
