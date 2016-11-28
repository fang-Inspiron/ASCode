package com.qnote.application.user.register.mode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qnote.application.user.register.presenter.RegisterPre;
import com.qnote.application.utils.MD5Util;
import com.qnote.application.utils.Urls;
import com.qnote.application.utils.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterMode implements IRegisterMode {
    RegisterPre registerPre;

    public RegisterMode(RegisterPre registerPre) {
        this.registerPre = registerPre;
    }


    @Override
    public void register(final String userName, final String mailAddress,
                         final String password) {
        StringRequest request = new StringRequest(Method.POST,
                Urls.regUrl, new Listener<String>() {

            @Override
            public void onResponse(String response) {

                JSONObject o = null;
                int status = -1;
                String state = null;
                try {
                    o = new JSONObject(response);
                    status = o.getInt("status");
                    state = o.getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                    state="服务器错误";
                    registerPre.showMsg("Error");
                    registerPre.regist_fail();
                }

                switch (status) {
                    case 0:
                        state = "注册成功";
                        registerPre.regist_Success();
                        break;
                    default:
                        registerPre.regist_fail();
                        break;
                }
                registerPre.showMsg(state);
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                registerPre.showMsg("网络错误");
                registerPre.regist_fail();

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", userName);
                params.put("password", MD5Util.MD5(password));
                params.put("mail", mailAddress);
                return params;
            }

        };

        VolleyUtil.getRequestQueue().add(request);
    }
}
