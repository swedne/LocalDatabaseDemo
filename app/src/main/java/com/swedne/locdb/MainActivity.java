package com.swedne.locdb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.swedne.locdb.bean.Game;
import com.swedne.locdb.dao.GameDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private DBManager dbHelper;
    private ListView lv;
    private List<Game> list = new ArrayList<>();
    private MyAdapter adapter;
    private JSONArray jsonArray;
    private GameDao gameDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //首次执行导入.db文件
//        dbHelper = new DBManager(this);
//        dbHelper.openDatabase();
//        dbHelper.closeDatabase();
        lv = findViewById(R.id.lv);
        gameDao = new GameDao(new Game());
        initView();
        try {
            initJsonData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.clear();
        list.addAll(gameDao.queryAll());
        adapter.notifyDataSetChanged();
    }

    private void initJsonData() throws JSONException {
        String city = getAssets(this, "game.json");
        jsonArray = new JSONArray(city);
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Game game = gson.fromJson(jsonObject.toString(), Game.class);
            gameDao.insert(game);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameDao.clearAll();
    }

    public static String getAssets(Activity mActivity, String fileName) {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = mActivity.getAssets().open(fileName);
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "UTF-8"));
            }
            is.close();
            return sb.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    private void initView() {
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.item_game, null);
            TextView tvName = inflate.findViewById(R.id.tv_name);
            TextView tvRemark = inflate.findViewById(R.id.tv_remark);
            tvName.setText(list.get(position).getNav());
            tvRemark.setText(list.get(position).getRemark());
            return inflate;
        }
    }

}