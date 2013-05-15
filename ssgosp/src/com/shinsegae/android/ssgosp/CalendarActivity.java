package com.shinsegae.android.ssgosp;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.shinsegae.android.common.CTCalendarView;
import com.shinsegae.android.common.Oneday;

public class CalendarActivity extends CTCalendarView {

	private Oneday basisDay;
	private int during;
	Button mText;
	LinearLayout mScrollViewInner;
	int mId;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Open Schedual Planner");
		initialize();

		basisDay = new Oneday(this);

		Intent intent = getIntent();
		int[] b = intent.getIntArrayExtra("basisDay");
		during = intent.getIntExtra("during", 0);
		if (b != null) {
			basisDay.setYear(b[0]);
			basisDay.setMonth(b[1]);
			basisDay.setDay(b[2]);
		} else {
			Calendar cal = Calendar.getInstance();
			basisDay.setYear(cal.get(Calendar.YEAR));
			basisDay.setMonth(cal.get(Calendar.MONTH));
			basisDay.setDay(cal.get(Calendar.DAY_OF_MONTH));
		}

		//mText = (Button) findViewById(R.id.add_text);
		mScrollViewInner = (LinearLayout) findViewById(R.id.scrollview_inner);

		
	}

	OnClickListener clickResult = new OnClickListener() {
		public void onClick(View v) {
			
			showDeatilInfo();
			/*Toast noti = Toast.makeText(getApplicationContext(), v.getId()
					+ " is Clicked", Toast.LENGTH_SHORT);
			noti.show();*/
		}
	};

	@Override
	protected void onTouched(Oneday touchedDay) {

		if (isInside(touchedDay, basisDay, during)) {
			Calendar cal = Calendar.getInstance();
			cal.set(basisDay.getYear(), basisDay.getMonth(), basisDay.getDay());
			cal.add(Calendar.DAY_OF_MONTH, during);
			Toast.makeText(
					this,
					(cal.get(Calendar.MONTH) + 1) + "월"
							+ cal.get(Calendar.DAY_OF_MONTH) + "일 이후 선택 가능",
					Toast.LENGTH_SHORT).show();
			return;
		}

		final String year = String.valueOf(touchedDay.getYear());
		final String month = doubleString(touchedDay.getMonth() + 1);
		final String date = doubleString(touchedDay.getDay());

		Log.d("cliek date", year + "." + month + "." + date);

		// 날짜 클릭시 해당 날짜의 일정 리스트 보여주기
        TextView newText = new TextView(getApplicationContext());
		newText.setText("새로운 일정[" + mId + "] 추가");
		newText.setId(mId++);
		newText.setOnClickListener(clickResult);
		mScrollViewInner.addView(newText, new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		
		/*
		 * AlertDialog.Builder builder = new
		 * AlertDialog.Builder(CalendarActivity.this);
		 * builder.setTitle("다음 날짜로 설정하시겠습니까?"); builder.setMessage(year + "." +
		 * month + "." + date + "(" + touchedDay.getDayOfWeekKorean()+")");
		 * builder.setPositiveButton("예", new DialogInterface.OnClickListener()
		 * {
		 * 
		 * @Override public void onClick(DialogInterface arg0, int arg1) { //
		 * Intent intent = new Intent(); // intent.putExtra("date", year + "." +
		 * month + "." + date); // setResult(RESULT_OK, intent); // finish();
		 * Toast.makeText(getApplicationContext(), year + "." + month + "." +
		 * date, Toast.LENGTH_SHORT) .show(); } });
		 * builder.setNegativeButton("아니오", null); builder.show();
		 */
	}

	/**
	 * 일정 상세 정보 화면 이동
	 */
	private void showDeatilInfo() {
		Intent detailIntent = new Intent(this, DetailActivity.class);

		/*
		 * String title = selectedFeed.getTitle(); String description =
		 * selectedFeed.getDetails(); String link = selectedFeed.getLink();
		 * String thumbnail = selectedFeed.getThumbnail(); String date =
		 * selectedFeed.getDate().toString();
		 * 
		 * System.out.println("====================== : " + title);
		 * 
		 * intent.putExtra("title", title); intent.putExtra("description",
		 * description); intent.putExtra("link", link);
		 * intent.putExtra("thumbnail", thumbnail); intent.putExtra("date",
		 * date);
		 */

		startActivity(detailIntent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.calendar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		// 오늘
		case R.id.menuitem_calendar_0:
			gotoToday();
			return true;
		}

		return false;
	}
}
