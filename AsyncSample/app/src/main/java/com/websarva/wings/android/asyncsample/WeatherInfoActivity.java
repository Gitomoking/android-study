package com.websarva.wings.android.asyncsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        ListView lvCityList = findViewById(R.id.lvCityList);
        List<Map<String, String>> cityList = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("name", "大阪");
        city.put("id", "27000");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "神戸");
        city.put("id", "280010");
        cityList.add(city);

        String[] from = {"name"};
        int[] to = {android.R.id.text1};

        SimpleAdapter adapter = new SimpleAdapter(WeatherInfoActivity.this, cityList, android.R.layout.simple_expandable_list_item_1, from, to);
        lvCityList.setAdapter(adapter);
        lvCityList.setOnItemClickListener(new ListItemClickListener());
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            String cityName = item.get("name");
            String cityId = item.get("id");
            TextView tvCityName = findViewById(R.id.tvCityName);
            tvCityName.setText(cityName + "の天気： ");

            // Get TextView for Weather info
            TextView tvWeatherTelop = findViewById(R.id.tvWeatherTelop);
            TextView tvWeatherDesc = findViewById(R.id.tvWeatherDesc);
            // Create WeatherInfoReceiver
            WeatherInfoReceiver receiver = new WeatherInfoReceiver(tvWeatherTelop, tvWeatherDesc);
            receiver.execute(cityId);
        }
    }

    private class WeatherInfoReceiver extends AsyncTask<String, String, String> {

        // _tvWeatherTelop shows current weather
        private TextView _tvWeatherTelop;
        // _tvWeatherDesc shows current weather details
        private TextView _tvWeatherDesc;

        public WeatherInfoReceiver(TextView tvWeatherTelop, TextView tvWeatherDesc) {
            _tvWeatherTelop = tvWeatherTelop;
            _tvWeatherDesc = tvWeatherDesc;
        }

        @Override
        public String doInBackground(String... params) {
            // Get first argument (City id)
            String id = params[0];
            // Make URL to connection
            String urlStr = "http://weather.livedoor.com/forecast/webservice/json/v1?city=" + id;
            // JSON response
            String result = "";

            // Connect URL and Get JSON response
            // Declare HttpURLConnection object
            HttpURLConnection con = null;
            // Declare response data
            InputStream is = null;
            try {
                // Create URL object
                URL url = new URL(urlStr);
                // Get HttpURLConnection object from URL object
                con = (HttpURLConnection) url.openConnection();
                // Set HTTP request method
                con.setRequestMethod("GET");
                // HTTP request
                con.connect();
                // Get response data
                is = con.getInputStream();
                // Convert InputStream object to string
                result = is2String(is);
            }
            catch(MalformedURLException ex) {

            }
            catch(IOException ex) {

            }
            finally {
                if(con != null) {
                    con.disconnect();
                }
                if(is != null) {
                    try {
                        is.close();
                    }
                    catch (IOException ex) {

                    }
                }
            }

            return result;
        }

        private String is2String(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            int line;
            while(0 <= (line = reader.read(b))) {
                sb.append(b, 0, line);
            }
            return sb.toString();
        }

        @Override
        public void onPostExecute(String result) {
            String telop = "";
            String desc = "";

            // Parse JSON
            try {
                // Create JSONObject object
                JSONObject rootJSON = new JSONObject(result);
                // Get :description
                JSONObject descriptionJSON = rootJSON.getJSONObject("description");
                // Get :description:text
                desc = descriptionJSON.getString("text");
                // Get :forecasts
                JSONArray forecasts = rootJSON.getJSONArray("forecasts");
                // Get first json object
                JSONObject forecastNow = forecasts.getJSONObject(0);
                // Get :forecasts:telop
                telop = forecastNow.getString("telop");
            }
            catch (JSONException ex) {

            }

            // Set weather info to TextView
            _tvWeatherTelop.setText(telop);
            _tvWeatherDesc.setText(desc);
        }
    }
}
