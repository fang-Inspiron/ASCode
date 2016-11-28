package com.qnote.application.user.login.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qnote.application.utils.CallBack;
import com.qnote.application.utils.Urls;
import com.qnote.application.utils.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginModel implements ILoginModel {
    public int state;
    public String msg;
    private String token;

    @Override
    public void login(final String userName, final String password,
                      final CallBack<LoginModel> callback) {
        StringRequest request = new StringRequest(Method.POST,
                Urls.loginUrl, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject o = new JSONObject(response);
                    int status = o.getInt("status");
                    String value = o.getString("value");

                    switch (status) {
                        case 0:
                            msg="登录成功";
                            token=value;
                            state=ILoginModel.Login_ok;
                            break;
                        default:
                            msg=value;
                            state=ILoginModel.Input_error;
                            break;
                    }
                    callback.getModel(LoginModel.this);


                } catch (JSONException e) {
                    msg="服务器错误";
                    state = System_error;
                    callback.getModel(LoginModel.this);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                msg="网络错误";
                state = System_error;
                callback.getModel(LoginModel.this);
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName);
                params.put("password", password);
                return params;
            }

        };

        VolleyUtil.getRequestQueue().add(request);

    }

    public String getToken() {
        return token;
    }

}
