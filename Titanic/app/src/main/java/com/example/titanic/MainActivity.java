package com.example.titanic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.romainpiel.titanic.library.Titanic;
import com.romainpiel.titanic.library.TitanicTextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

	    //设置字体
	    tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

	    //开启动画
	    Titanic titanic = new Titanic();
	    titanic.start(tv);
	    //关闭动画
	    //titanic.cancel();
	}
}
