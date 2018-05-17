package com.smallmulberry.linechangelayout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smallmulberry.linechangelayout.Bean.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzp on 2018/5/16.poon
 */

public class LineChangeLayout extends LinearLayout {
    private LineCallBack lineCallBack;
    private LineLongCallBack lineLongCallBack;
    private Context context;
    private ArrayList<Line> list;
    private ArrayList<TextView> toptextlist=new ArrayList<>();


    //默认背景r文件
    private int drawableR= R.drawable.hui;
    private int drawableStarR= R.drawable.hong;
    //默认左上右下内边距kl
    private int itemLeftPadding=10;
    private int itemTopPadding=10;
    private int itemRightPadding=10;
    private int itemBottomPadding=10;
    public  int width=-1;


    //默认左上右下外边距
    private int itemleftMargin=10;
    private int itemtopMargin=10;
    private int itemrightMargin=10;
    private int itembottomMargin=10;

    //默认文字默认颜色和点亮之后颜色 及文字大小
    private int itemTextColor= R.color.hui;
    private int itemTextStarColor= R.color.hei;
    private int itemtextSize=30;

    //监听最后一个按钮
    private boolean ifMonitorLastItem=false;

    public interface LineCallBack {
        void lineOnclick(int i, boolean ifCheck);
    }
    public interface LineLongCallBack{
        void lineOnLongclick(int i, boolean ifCheck);
    }


    //构造方法 context,数据集合,单击item回调,长按item回调
    public LineChangeLayout(Context context, List<Line> list, LineCallBack lineCallBack, LineLongCallBack lineLongCallBack){
        super(context);
        this.context=context;
        this.list= (ArrayList<Line>) list;
        this.lineCallBack=lineCallBack;
        this.lineLongCallBack=lineLongCallBack;

//        show();
        onWindowFocusChanged(false);

    }
    public LineChangeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LineChangeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    //设置监听最后一个按钮为添加item按钮
    public void setLastItemMonitor(boolean ifMonitorLastItem){
        this.ifMonitorLastItem=ifMonitorLastItem;
    }
    //xml方式加载自定义控件初始化方法参数与构造方法一致
    public void setList(Context context,List<Line> list,LineCallBack lineCallBack,LineLongCallBack lineLongCallBack){
        this.context=context;
        this.list= (ArrayList<Line>) list;
        this.lineCallBack=lineCallBack;
        this.lineLongCallBack=lineLongCallBack;
        //show();

        onWindowFocusChanged(false);

    }
    //设置单个item 背景样式 默认背景样式及item选中之后背景样式
    public void setItemBgStyle(int drawableR,int drawableStarR){
        this.drawableR=drawableR;
        this.drawableStarR=drawableStarR;

    }
    //设置item文字样式 item文字默认颜色 item选中后文字颜色  文字大小 文字 左 上 右 下 padding
    public void setItemTextStyle(int itemTextColor,int itemTextStarColor,int itemtextSize,int itemLeftPadding,int itemTopPadding,int itemRightPadding,int itemBottomPadding){

        this.itemTextColor=itemTextColor;
        this.itemTextStarColor=itemTextStarColor;

        this.itemtextSize=itemtextSize;
        this.itemLeftPadding=itemLeftPadding;
        this.itemTopPadding=itemTopPadding;
        this.itemRightPadding=itemRightPadding;
        this.itemBottomPadding=itemBottomPadding;
    }
    //设置item 左上右下边距
    public void setItemMargin(int itemLeftMargin,int itemTopMargin,int itemRightMargin,int itemBottomMargin){
        this.itemleftMargin=itemLeftMargin;
        this.itemtopMargin=itemTopMargin;
        this.itemrightMargin=itemRightMargin;
        this.itembottomMargin=itemBottomMargin;
    }
    //刷新自定义控件 在动态添加item数据后可调用
    public void refresh(List<Line> list){

        this.list= (ArrayList<Line>) list;
//        show();
        onWindowFocusChanged(false);
    }
    //设置自定义控件宽度 不调用则默认设置自定义控件宽度为屏幕宽度
    public void setLineChangeLayoutWidth(int width){
        this.width=width;

        onWindowFocusChanged(false);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        show();

    }


    //显示数据的方法
    private void show() {
        removeAllViews();


        //设置 LinearLayout 方向
        setLinearLayoutDirection();

        //判断如果用户没有设置自定义控件的宽度就默认屏幕宽度为自定义控件的宽度
        if(width==-1){
            if( getLayoutParams()==null ){

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(((Activity)context).getWindowManager().getDefaultDisplay().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                setLayoutParams(lp);

            }

            if(getLayoutParams().width<=0){
                if(getLayoutParams() instanceof RelativeLayout.LayoutParams){
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(((Activity)context).getWindowManager().getDefaultDisplay().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                    setLayoutParams(lp);
                }else  if(getLayoutParams() instanceof LayoutParams){
                    LayoutParams lp = new LayoutParams(((Activity)context).getWindowManager().getDefaultDisplay().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                    setLayoutParams(lp);
                }else if(getLayoutParams() instanceof ScrollView.LayoutParams){
                    ViewGroup.LayoutParams lp = new ScrollView.LayoutParams(((Activity)context).getWindowManager().getDefaultDisplay().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                    setLayoutParams(lp);
                }
            }
            width = getLayoutParams().width;
        }


        //记录LinearLayout 子元素个数
        int LinearLayoutchildsize = 0;


        LinearLayout goodsevaluate_top_smallll = new LinearLayout(context);
        int allwidth = 0;
        for (int i = 0; i < list.size(); i++) {

            //  ifreturn=true;
            //每一小条中的textview
            LinearLayout goodsevaluate_top_smallll_text = (LinearLayout) View.inflate(context, R.layout.goodsevaluate_top_smallll_text, null);
            TextView goodsevaluate_top_smallll_text_tv = (TextView) goodsevaluate_top_smallll_text.findViewById(R.id.goodsevaluate_top_smallll_text_tv);
            goodsevaluate_top_smallll_text_tv.setTag(i);

            goodsevaluate_top_smallll_text_tv.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Integer tag = (Integer) view.getTag();

                    lineLongCallBack.lineOnLongclick(tag, list.get(tag).isLight());

                    return true;
                }
            });


            goodsevaluate_top_smallll_text_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    Integer tag = (Integer) view.getTag();

                    boolean b;

                    if(tag==list.size()-1){
                        if(ifMonitorLastItem){
                            b=false;
                        }else {
                            b = !list.get(tag).isLight();
                        }
                    }else {
                        b = !list.get(tag).isLight();
                    }

                    list.get(tag).setLight(b);

                    onWindowFocusChanged(false);
                    lineCallBack.lineOnclick(tag,b);




                }
            });

            LayoutParams layoutParams = (LayoutParams) goodsevaluate_top_smallll_text_tv.getLayoutParams();

            layoutParams.setMargins(itemleftMargin,itemtopMargin,itemrightMargin,itembottomMargin);
            goodsevaluate_top_smallll_text_tv.setLayoutParams(layoutParams);


            goodsevaluate_top_smallll_text_tv.setTextSize(itemtextSize);

            goodsevaluate_top_smallll_text_tv.setPadding(itemLeftPadding,itemTopPadding,itemRightPadding,itemBottomPadding);

            goodsevaluate_top_smallll_text_tv.setText(list.get(i).getName());
            goodsevaluate_top_smallll_text_tv.setTextColor(itemTextColor);
            goodsevaluate_top_smallll_text_tv.setTextColor(context.getResources().getColor(itemTextColor));
            goodsevaluate_top_smallll_text_tv.setBackgroundResource(drawableR);

            if (list.get(i).isLight() == true) {  //点亮所以选择的颜色

                goodsevaluate_top_smallll_text_tv.setTextColor(context.getResources().getColor(itemTextStarColor));
                goodsevaluate_top_smallll_text_tv.setBackgroundResource(drawableStarR);
            }

            goodsevaluate_top_smallll_text.measure(0, 0);
            int measuredWidth2 = goodsevaluate_top_smallll_text.getMeasuredWidth();

            if(measuredWidth2>width){  //单个item宽度超过全部宽度 width

                //判断当前横向布局有集合子元素
                if(goodsevaluate_top_smallll.getChildCount()==0){

                    int lsitemtextSize=itemtextSize;
                    for (int j = 0; j < lsitemtextSize; j++) {
                        lsitemtextSize--;
                        goodsevaluate_top_smallll_text_tv.setTextSize(lsitemtextSize);
                        goodsevaluate_top_smallll_text.measure(0, 0);
                        int measuredWidth = goodsevaluate_top_smallll_text.getMeasuredWidth();

                        if(measuredWidth>width){

                        }else {
                            toptextlist.add(goodsevaluate_top_smallll_text_tv);
                            goodsevaluate_top_smallll.addView(goodsevaluate_top_smallll_text);

                            addView(goodsevaluate_top_smallll);

                            goodsevaluate_top_smallll = new LinearLayout(context);

                            break;
                        }

                    }

                }else {
                    LinearLayoutchildsize++;

                    addView(goodsevaluate_top_smallll);
                    i--;
                    allwidth = 0;
                    goodsevaluate_top_smallll = new LinearLayout(context);
                }


            }else {
                allwidth += measuredWidth2;


                if (allwidth > width) {
                    LinearLayoutchildsize++;
                    addView(goodsevaluate_top_smallll);
                    i--;
                    allwidth = 0;
                    goodsevaluate_top_smallll = new LinearLayout(context);

                } else {
                    toptextlist.add(goodsevaluate_top_smallll_text_tv);
                    goodsevaluate_top_smallll.addView(goodsevaluate_top_smallll_text);

                    if (i == list.size() - 1) {
                        LinearLayoutchildsize++;
                        addView(goodsevaluate_top_smallll);
                    }
                }
            }


        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.e("zzp", "onMeasure: " );
        Log.e("zzp", "onMeasure: " );
        Log.e("zzp", "onMeasure: " );
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


        Log.e("zzp", "onMeasure: " );
        Log.e("zzp", "onMeasure: " );
        Log.e("zzp", "onMeasure: " );
    }

    private void setLineLongCallBack(LineLongCallBack lineLongCallBack){
        this.lineLongCallBack=lineLongCallBack;
    }
    private void setLineCallBack(LineCallBack lineCallBack) {
        this.lineCallBack = lineCallBack;
    }
    //设置方向
    private void setLinearLayoutDirection(){
        this.setOrientation(this.VERTICAL); //HORIZONTAL = 0 VERTICAL = 1
    }

}