package com.trprojects.okhttp3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import com.itsaky.androidide.logsender.LogSender;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {
	
	private Context ctx;
	OkHttpClient client = new OkHttpClient();

      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		String str = "";
        
    }
    
    private String runme(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

       try (Response response = client.newCall(request).execute()) {
         return response.body().string();
       
    }
  }
  
  @Override
	public void onResume() {
		super.onResume();
        //SIMPLE GET REQUEST
        new Thread(new Runnable(){
                @Override public void run(){
                    try{
                      final String _message = runme("https://www.facebook.com/arnice.candoza");
                      runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            Toast.makeText(getApplicationContext(),_message,Toast.LENGTH_LONG).show();
                            }
                        });
                    }catch(final Exception e){
                       e.printStackTrace();
                       runOnUiThread(new Runnable(){
                       @Override
                       public void run(){
                       	Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                          }
                       });
                    }
                }
           }).start();
	}

}
