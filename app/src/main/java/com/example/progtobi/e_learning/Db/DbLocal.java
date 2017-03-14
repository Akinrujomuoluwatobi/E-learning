package com.example.progtobi.e_learning.Db;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.progtobi.e_learning.model._User;

public class DbLocal {
    private Context c;

    public DbLocal(Context c) {
        this.c = c;
    }

    public boolean add_User(_User _User) {
        try {

            SharedPreferences mydata = c.getSharedPreferences("DATA", 0);
            SharedPreferences.Editor editor = mydata.edit();
            editor.putInt("id", _User.getId());
            editor.putString("name", _User.getName());
            editor.putString("username", _User.getUsername());
            editor.putString("password", _User.getPassword());
            editor.putInt("level", _User.getLevel());
            return editor.commit();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return false;
    }

    public _User fetch_User() {
        _User _User = new _User();
        try {
            SharedPreferences mydata = c.getSharedPreferences("DATA", 0);
            _User.setId(mydata.getInt("id", 0));
            _User.setName(mydata.getString("name", ""));
            _User.setUsername(mydata.getString("username", ""));
            _User.setPassword(mydata.getString("password", ""));
            _User.setLevel(mydata.getInt("level", 0));
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return _User;
    }

    public boolean isRegistered() {
        if (fetch_User().getName().length() > 2) {
            return true;
        }
        return false;
    }

}
