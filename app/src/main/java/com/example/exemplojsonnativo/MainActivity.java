package com.example.exemplojsonnativo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView l1;

    AdapterDivisoes myAdapter;
    ArrayList<DivisoesModel> divisoesModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.divisoes);

        l1 = findViewById(R.id.lista_divisoes);

        JsonWork jsonWork = new JsonWork();
        jsonWork.execute();
    }


    public class JsonWork extends AsyncTask<String, String, ArrayList<DivisoesModel>> {


        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String FullJsonData;

        @Override
        protected ArrayList<DivisoesModel> doInBackground(String... strings) {

            try {
                URL url = new URL("http://aplicacoesmoveis.000webhostapp.com/domotica/listar_divisoes.php");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {

                    stringBuffer.append(line);

                }
                FullJsonData = stringBuffer.toString();
                divisoesModelArrayList = new ArrayList<>();

                //JSONObject jsonStartingObject = new JSONObject(FullJsonData);
                JSONArray jsonStartingArray = new JSONArray(FullJsonData);
                //JSONArray jsonArray = jsonStartingObject.getJSONArray("result");


                for (int i = 0; i < jsonStartingArray.length(); i++) {

                    JSONObject jsonUnderArrayObject = jsonStartingArray.getJSONObject(i);

                    DivisoesModel jsonModel = new DivisoesModel();
                    jsonModel.setId_divisao(jsonUnderArrayObject.getString("id"));
                    jsonModel.setDescricao(jsonUnderArrayObject.getString("descricao"));
                    divisoesModelArrayList.add(jsonModel);
                }

                return divisoesModelArrayList;


            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {

                httpURLConnection.disconnect();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        public void onPostExecute(ArrayList<DivisoesModel> divisoesModelArrayList) {
            super.onPostExecute(divisoesModelArrayList);
            myAdapter = new AdapterDivisoes(MainActivity.this, divisoesModelArrayList);
            l1.setAdapter(myAdapter);


        }
    }

}