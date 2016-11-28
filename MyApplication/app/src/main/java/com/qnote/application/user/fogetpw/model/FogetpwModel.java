package com.qnote.application.user.fogetpw.model;

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


public class FogetpwModel implements IFogetpwModel {
    public String msg;
    public int state;

    @Override
    public void resetPW(final String name, final String mail,
                        final CallBack<FogetpwModel> callback) {
        StringRequest request = new StringRequest(Method.POST,
                Urls.resetUrl, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                int status = -1;
                String value = null;
                try {
                    JSONObject o = new JSONObject(response);
                    status = o.getInt("status");
                    value = o.getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                    msg = "服务器错误";
                    state = IFogetpwModel.System_error;
                    callback.getModel(FogetpwModel.this);
                    return;
                }
                switch (status) {
                    case 0:
                        msg = value;
                        state = IFogetpwModel.Operate_ok;
                        break;
                    case 1020:
                        msg = value;
                        state = IFogetpwModel.Input_error;
                        break;
                    default:
                        msg=value;
                        state = IFogetpwModel.Input_error;
                        break;
                }
                callback.getModel(FogetpwModel.this);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                msg = "网络错误";
                state = IFogetpwModel.System_error;
                callback.getModel(FogetpwModel.this);
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("mail", mail);
                return params;
            }

        };

        VolleyUtil.getRequestQueue().add(request);
    }

}
