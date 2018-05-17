    # LineChangeLayout
    文字列表集合自动换行控件，可自适应文字长度。

    ![](https://github.com/SmallMulberry/LineChangeLayout/blob/master/LineChangeLayout/src/main/res/drawable/yanshi.png)

    LineChangeLayout 目前支持的父布局有 RelativeLayout,ScrollView,LinearLayout

    //构造方法 context,数据集合,单击item回调,长按item回调
    public LineChangeLayout(Context context, List<Line> list, LineCallBack lineCallBack, LineLongCallBack lineLongCallBack);
    
    //xml方式加载自定义控件初始化方法参数与构造方法一致
    public void setList(Context context,List<Line> list,LineCallBack lineCallBack,LineLongCallBack lineLongCallBack);
    
    //设置监听最后一个按钮为添加item按钮 true:监听 false 不监听
    public void setLastItemMonitor(boolean ifMonitorLastItem);
    
    //设置单个item 背景样式 默认背景样式及item选中之后背景样式
    public void setItemBgStyle(int drawableR,int drawableStarR);
    
    //设置item文字样式 item文字默认颜色 item选中后文字颜色  文字大小 文字 左 上 右 下 padding
    public void setItemTextStyle(int itemTextColor,int itemTextStarColor,int itemtextSize,int itemLeftPadding,int itemTopPadding,int itemRightPadding,int itemBottomPadding);

    //设置item 左上右下边距
    public void setItemMargin(int itemLeftMargin,int itemTopMargin,int itemRightMargin,int itemBottomMargin);
    
    //刷新自定义控件 在动态添加item数据后可调用
    public void refresh(List<Line> list);
    
    //设置自定义控件宽度 不调用则默认设置自定义控件宽度为屏幕宽度
    public void setLineChangeLayoutWidth(int width);
