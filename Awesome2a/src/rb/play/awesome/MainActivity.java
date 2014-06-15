package rb.play.awesome;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	MediaPlayer player;
	int pausedPos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Pickel","inside onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//player.start();
		//Log.e("testtag", "test message");
	}
	
	protected void onResume() {
		Log.e("Pickel","inside onResume");
		player=MediaPlayer.create(this, R.raw.radionepal);
		player.seekTo(getPausedPos());
		player.start();
		super.onResume();
	};
	
	@Override
	protected void onStop() {
		Log.e("Pickel","inside onStop");
		player.stop();
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		Log.e("Pickel","inside onPause");
		setPausedPos(player.getCurrentPosition());
		player.pause();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void clickFB(View view){
		String url="http://www.onlinekhabar.com/";
		Intent i=new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	
	}

	public int getPausedPos() {
		return pausedPos;
	}

	public void setPausedPos(int pausedPos) {
		this.pausedPos = pausedPos;
	}
}
