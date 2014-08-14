package com.example.votandocombanco;

import java.util.ArrayList;
import java.util.Iterator;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChartActivity extends ActionBarActivity {
	MySQLiteHelper db = new MySQLiteHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		int total=1;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		
		//getting all the answers
		ArrayList<Pergunta> todas = db.getAllPerg();
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			total = extras.getInt("contador");
		}
		
		int col1=0, col2=0, col3=0;
		for (Iterator<Pergunta> iterator = todas.iterator(); iterator.hasNext();) {
			Pergunta pergunta = (Pergunta) iterator.next();
			if(pergunta.getResposta()==1){
				col1++;
			}else if(pergunta.getResposta()==2){
				col2++;
			}else if(pergunta.getResposta()==3){
				col3++;
			}
			
		}

		GraphViewData[] data = new GraphViewData[]{
				new GraphViewData(0,col1*100/total),
				new GraphViewData(1,col2*100/total),
				new GraphViewData(2,col3*100/total),	
		};

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

		if(data.length>0){
			GraphViewSeries input = new GraphViewSeries(data);
			//handling the graph
			GraphView exemplo = new BarGraphView(this,"answers to question 1");		
			exemplo.setVerticalLabels(new String[]{"100%", "80%", "60%", "40%", "20%", "0%"});
			exemplo.setHorizontalLabels(new String[]{"happy","+-", "sad"});
			exemplo.addSeries(input);

			layout.addView(exemplo);
		} else{
			final TextView errorMessage = new TextView(this);
			errorMessage.setText("No student evaluated this criteria");
			layout.addView(errorMessage);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
