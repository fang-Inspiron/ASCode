package com.qnote.application.user.revise.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qnote.application.Iapplication;
import com.qnote.application.utils.CallBack;
import com.qnote.application.utils.MD5Util;
import com.qnote.application.utils.Urls;
import com.qnote.application.utils.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReviseModel implements IReviseModel {
    public String msg;
    public int state;

    @Override
    public void changePW(final String oldPW, final String newPW,
                         final CallBack<ReviseModel> callBack) {
        StringRequest request = new StringRequest(Method.POST,
                Urls.userUrl, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                int status;
                String value;
                try {
                    JSONObject o = new JSONObject(response);
                    status = o.getInt("status");
                    value = o.getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                    msg = "服务器错误";
                    state = IReviseModel.FALSE;
                    callBack.getModel(ReviseModel.this);
                    return;
                }
                switch (status) {
                    case 0:
                        msg = value;
                        state = IReviseModel.TRUE;
                        break;
                    case 1021:
                        msg = "密码错误";
                        state = IReviseModel.FALSE;
                        break;
                    case 2001:
                        msg="token错误，请重新登录";
                        state = IReviseModel.NOTOKEN;
                        break;
                    case 2002:
                        msg="token错误，请重新登录";
                        state = IReviseModel.NOTOKEN;
                        break;

                    default:
                        state = IReviseModel.FALSE;
                        msg=value;
                        break;
                }

                callBack.getModel(ReviseModel.this);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                msg = "网络错误";
                state = IReviseModel.FALSE;
                callBack.getModel(ReviseModel.this);
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", MD5Util.MD5(oldPW));
                params.put("npassword", MD5Util.MD5(newPW));
                params.put("token", Iapplication.getToken());
                return params;
            }

        };

        VolleyUtil.getRequestQueue().add(request);
    }

}
