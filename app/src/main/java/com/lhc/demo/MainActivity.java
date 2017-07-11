package com.lhc.demo;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public ImageView iv;
    public TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv= (ImageView) findViewById(R.id.iv);
        tv= (TextView) findViewById(R.id.tv);
        iv.setClipToOutline(true);
        Rect rect=new Rect(20,20,10,12);
        iv.setClipBounds(rect);


        SpannableString spannableString=new SpannableString("低速嘎嘎地AV实地");
        spannableString.setSpan(new Clickable(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,((TextView)view).getText(),Toast.LENGTH_SHORT).show();
            }
        }), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new NoUnderlineSpan(),  0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(spannableString);
        // 2.取消文本的点击背景
        tv.setHighlightColor(getResources().getColor(android.R.color.transparent));
        tv.setMovementMethod(LinkMovementMethod.getInstance());

    }
    class Clickable extends ClickableSpan{

        private View.OnClickListener mOnClickListener;

        public Clickable(View.OnClickListener l){
            mOnClickListener=l;
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onClick(view);
        }

        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.colorAccent));
        }
    }

    //1.取消下划线
    public class NoUnderlineSpan extends UnderlineSpan {

        @Override

        public void updateDrawState(TextPaint ds) {

            ds.setColor(getResources().getColor(R.color.colorPrimary));

//设置可点击文本的字体颜色
            ds.setUnderlineText(false);

        }

    }

//设置高亮文本的颜色为透明

}


/*
new ClickableSpan() {
@Override
public void onClick(View view) {
        Toast.makeText(MainActivity.this,((TextView)view).getText(),Toast.LENGTH_SHORT).show();
        }
        }*/
