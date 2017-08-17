
##效果图

----------

![](http://i.imgur.com/aMfM0Un.gif)

实现了以下的效果：
1.为每个按钮设置监听
2.按下按钮后改变按钮的颜色和字体的颜色

----------


##使用
这个自定义控件使用起来也非常的简单，只需要我们传入一些必要的参数就可以使用了。

在XML中：
	因为是继承于RadioGroup，所有我们使用RadioGroup的方法就可以了。

	<com.example.olay.downview.View.BottomGroup
        android:id="@+id/bottomGroup"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_below="@id/image"
        android:layout_centerVertical="true"
        android:orientation="horizontal" />
	
在Activity中：

 		bottomGroup.setId(id);//按钮id
        bottomGroup.setText(text);//按钮文本
        bottomGroup.setNormal_img(normal_img);//按钮自然的图片
        bottomGroup.setPress_img(press_img);//按钮被按下时的图片
        bottomGroup.setColor(color);//按钮文本平常和被按下时的颜色
        bottomGroup.setOnButtonListener(new BottomGroup.OnButtonListener() {
            @Override
            public void OnClick(int which) {
                
            }
        });//监听
        bottomGroup.start();//开始初始化

完成以上的操作，就可以得到一个底部按钮组了。

##实现

----------
之前说过是RadioGroup和RadioButton，那么这个自定义控件无非就是重写RadioGroup，在里面动态的添加RadioButton就是了。
我们先自定义一个BottomGroup来继承RadioGroup，并且实现它的构造函数。
	
	public class BottomGroup extends RadioGroup {
	 public BottomGroup(Context context) {
        this(context, null);
    }

    public BottomGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
	}
之后，我们需要使用者传入一些参数，来帮助我们完成初始化。

	//每个按钮的id
    private int[] id;
    //每个按钮的文本
    private String[] text;
    //平常的图片
    private int[] normal_img;
    //按下的图片
    private int[] press_img;
    //文字的平常和按下时的颜色
    private int[] color;
    private Context context;
在直接通过setXXX方法完成初始化后，我们就要通过传入的参数初始化RadioButton了，我们要明白我们需要动态的去完成哪些个功能。

1.设置DrawingTop（文字上方的图片）

2.为每个RadioButton添加权重

3.每个按钮居中处理

4.去掉RadioButton自带的小圆点

那我们一个一个的完成它们
	
	设置DrawingTop
    Drawable drawable = getResources().getDrawable(normal_img[i]);
    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
	//四个参数分别为left,top,right,bottom不同方向的图片
    radioButton.setCompoundDrawables(null, drawable, null, null);
	//设置权重,默认均等分
    LayoutParams layoutParams = new LayoutParams
    (0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
    radioButton.setLayoutParams(layoutParams);
     //居中
    radioButton.setGravity(Gravity.CENTER);
	 //去掉左边的小圆点
    radioButton.setButtonDrawable(new BitmapDrawable());

最后我们需要为每个RadioButton设置监听，使用监听来改变它们本点击时的文本和图标，同时回调我们的自己实现的监听事件。
	
	
	radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //改变字体颜色和图片状态
                        Drawable drawable = getResources().getDrawable(press_img[finalI]);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        radioButton.setCompoundDrawables(null, drawable, null, null);
                        radioButton.setTextColor(color[0]);
                  
                    } else {
                        Drawable drawable = getResources().getDrawable(normal_img[finalI]);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        radioButton.setCompoundDrawables(null, drawable, null, null);
                        radioButton.setTextColor(color[1]);
                    }
                }
            });

按钮被点击后要实现我们自己需要的逻辑，需要一个监听接口来设置，在RadioButton的监听中实现。

	//监听接口
    public interface OnButtonListener {
        void OnClick(int which);
    }

    private OnButtonListener listener;

    public void setOnButtonListener(OnButtonListener listener) {
        this.listener = listener;
    }

这样我们就实现我们所有需要的功能
