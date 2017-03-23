package gr.uoa.elearning.android.asynctaskactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressView = (TextView)findViewById(R.id.progressView);
        new WorkerThread().execute(10);
    }

    public class WorkerThread extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            //we're going to introduce some artificial heavy lifting here
            for(int i=0; i<params[0];i++){
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressView.setText(10*values[0]+"%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"Starting worker",Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"Job's done!",Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}
