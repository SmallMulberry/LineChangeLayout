package com.smallmulberry.linechangelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smallmulberry.linechangelayout.Bean.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public MainActivity mMainActivity;

    private List<Line> list=new ArrayList<>();
    private LineChangeLayout main_lcl;
    private LinearLayout main_ll;
    private LineChangeLayout lineChangeLayout;

    private String[] adddata ={"小绵羊","电话机","糖糖","帷飘白玉堂，簟卷碧牙床。楚女当时意，萧萧发彩凉。","panda","龙"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mMainActivity = this;


        //填充假数据
        setData();

        //xml方式加载 LineChangeLayout
        xmlgetLineChangeLayout();

        //代码方式加载 LineChangeLayout
        newgetLineChangeLayout();


    }


    //xml方式加载自定义控件
    private void xmlgetLineChangeLayout(){
        //LineChangeLayout
        main_lcl = (LineChangeLayout) findViewById(R.id.main_lcl);
        main_lcl.setItemBgStyle(R.drawable.hui,R.drawable.hong);  //item默认背景样式及点亮之后样式
        //默认及点亮之后文字颜色,文字大小，左上右下Padding
        main_lcl.setItemTextStyle(R.color.colorAccent,R.color.colorPrimary,25,20,5,20,5);
        main_lcl.setItemMargin(20,20,20,20);  //设置 LineChangeLayout 单个item的 Margin

        main_lcl.setLastItemMonitor(true); //设置最后一个按钮为添加item的监听按钮

        main_lcl.setList(mMainActivity, list, new LineChangeLayout.LineCallBack() {
            @Override
            public void lineOnclick(int i, boolean ifCheck) {  //单击item
                Toast.makeText(mMainActivity, i+""+ifCheck, Toast.LENGTH_SHORT).show();

                //判断点击最后一个item
                if(i==list.size()-1){

                    //插入数据
                    Line line=new Line();
                    line.setName(adddata[new Random().nextInt(adddata.length)]);
                    //插入的数据如果需要默认被选中就设置为true
                    line.setLight(false);
                    list.add(list.size()-1,line);

                    //刷新列表
                    main_lcl.refresh(list);


                }


            }
        }, new LineChangeLayout.LineLongCallBack() {  //长按item
            @Override
            public void lineOnLongclick(int i, boolean ifCheck) {
                Toast.makeText(mMainActivity, i+""+ifCheck, Toast.LENGTH_SHORT).show();

                list.remove(i);

                main_lcl.refresh(list);


            }
        });

    }

    //代码方式加载自定义控件
    private void newgetLineChangeLayout(){

        //Linearlayout
        main_ll = (LinearLayout) findViewById(R.id.main_ll);

        lineChangeLayout=new LineChangeLayout(mMainActivity, list, new LineChangeLayout.LineCallBack() {
            @Override
            public void lineOnclick(int i, boolean ifCheck) {
                Toast.makeText(mMainActivity, i+""+ifCheck, Toast.LENGTH_SHORT).show();
            }
        }, new LineChangeLayout.LineLongCallBack() {
            @Override
            public void lineOnLongclick(int i, boolean ifCheck) {
                Toast.makeText(mMainActivity, i+""+ifCheck, Toast.LENGTH_SHORT).show();
            }
        });

        //设置LineChangeLayout 宽度
        lineChangeLayout.setLineChangeLayoutWidth(500);


        // main_ll.addView(lineChangeLayout);
    }

    //假数据
    private void setData(){
        Line line=new Line();
        line.setName("小虎");
        line.setLight(false);

        list.add(line);

        Line line1=new Line();
        line1.setName("小帆反反复复付付船");
        line1.setLight(false);

        list.add(line1);

        Line line2=new Line();
        line2.setName("落叶聚还散，寒鸦栖复惊，相思相见知何");
        line2.setLight(false);

        list.add(line2);

        Line line3=new Line();
        line3.setName("曾经沧海难为水,除却巫山不是云.取次花丛懒回顾，半缘修道半缘君");
        line3.setLight(false);

        list.add(line3);

        Line line4=new Line();
        line4.setName("小虎家咯ioijilk");
        line4.setLight(false);
        list.add(line4);

        Line line5=new Line();
        line5.setName(" + ");
        line5.setLight(false);

        list.add(line5);
    }
}
