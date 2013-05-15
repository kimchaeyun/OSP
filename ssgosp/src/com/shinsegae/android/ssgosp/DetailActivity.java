package com.shinsegae.android.ssgosp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailview);
		
		setTitle("일정 상세등록");

		/*TextView textView = (TextView) findViewById(R.id.title);
		TextView textView1 = (TextView) findViewById(R.id.description);
		TextView textView2 = (TextView) findViewById(R.id.thumbnail);
		TextView textView3 = (TextView) findViewById(R.id.date);

		Bundle extras = getIntent().getExtras();
		textView.setText("[제목] : \n" + extras.get("title").toString());
		textView1.setText("[링크] : \n" + extras.get("description").toString());
		textView2.setText("[이미지 링크] : \n" + extras.get("thumbnail").toString());
		textView3.setText("[날짜] : \n" + extras.get("date").toString());*/
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
