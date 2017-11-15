package com.example.xy.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xy.greendaodemo.db.DBHelper;
import com.example.xy.greendaodemo.entity.User;
import com.example.xy.greendaodemo.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd, mDelete, mUpdate, mFind;
    private TextView mTextView;

    private User mUser;
    private UserDao mUserDao;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mAdd = (Button) findViewById(R.id.button);
        mDelete = (Button) findViewById(R.id.button2);
        mUpdate = (Button) findViewById(R.id.button3);
        mFind = (Button) findViewById(R.id.button4);

        initEvent();

//        mUserDao = DBHelper.getInstance().getDaoSession(this).getUserDao();
        mUserDao = MyApplication.getInstance().getDBHelper().getDaoSession(this).getUserDao();
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                add();
                break;
            case R.id.button2:
                delete();
                break;
            case R.id.button3:
                update();
                break;
            case R.id.button4:
                find();
                break;
        }
    }

    private void add() {
        mUser = new User((long) i++, "xy" + i);
        mUserDao.insert(mUser);
        mTextView.setText(mUser.getName());
    }

    private void delete() {
        /*
        * 查询id小于等于10的数据
        * */
        List<User> userList = mUserDao.queryBuilder().where(UserDao.Properties.Id.le(10)).build().list();
        for (User user : userList) {
            mUserDao.delete(user);
        }
//        mUserDao.deleteByKey((long) 2);
    }

    /*
    * 将表中所有数据一次删除
    * */
    private void deleteAll(){
        mUserDao.deleteAll();
    }

    private void update() {
        mUser = new User((long) 5, "xy");
        mUserDao.update(mUser);
    }

    private void find() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ",";
        }
        mTextView.setText("查询全部数据==>" + userName);

//        List<User> list = mUserDao.queryBuilder()
//                .where(UserDao.Properties.Id.between(2, 13)).limit(5).build().list();
//        for (int i = 0; i < list.size(); i++) {
//            Log.d("google_lenve", "search: " + list.get(i).toString());
//        }
    }

    private void findById(int id){
        User user = mUserDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
        if (user == null) {
            Toast.makeText(MainActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
        }else{
            mTextView.setText("查询全部数据==>"+ user.getName());
        }
    }
}
