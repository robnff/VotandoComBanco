package com.example.votandocombanco;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	int cont = 0;
	Button button1, button2, button3;
	//adicionando o banco
	MySQLiteHelper db = new MySQLiteHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		
		if (extras != null){
			cont = extras.getInt("contador");
		}
		//adicionando coisas
		db.addPerg(new Pergunta("Question 1", "Clarity", 0));
		db.addPerg(new Pergunta("Question 2", "Clarity", 0));
		db.addPerg(new Pergunta("Question 3", "Clarity", 0));
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final String pergunta = db.getPerg(cont).getQuestion();
		TextView shownQ = (TextView)findViewById(R.id.pergunta);
		shownQ.setText(pergunta);

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				cont++;
				Intent intent;
				if(cont==3){
					intent = new Intent(MainActivity.this, ChartActivity.class);
				}
				else{
					intent = new Intent(MainActivity.this, MainActivity.class);
				}
				intent.putExtra("contador", cont);
				db.addPerg(new Pergunta(pergunta, "Clarity", 1));
				Toast toast = Toast.makeText(getApplicationContext(),"you voted :)", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				finish();
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				cont++;
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				intent.putExtra("contador", cont);
				db.addPerg(new Pergunta(pergunta,"Clarity", 2));
				Toast toast = Toast.makeText(getApplicationContext(),"you voted :|", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				finish();
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				cont++;
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				intent.putExtra("contador", cont);
				db.addPerg(new Pergunta(pergunta,"Clarity", 3));
				Toast toast = Toast.makeText(getApplicationContext(), "you voted :(", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
