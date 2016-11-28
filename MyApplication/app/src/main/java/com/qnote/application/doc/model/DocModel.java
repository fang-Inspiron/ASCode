package com.qnote.application.doc.model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.qnote.application.Iapplication;
import com.qnote.application.doc.bean.NoteListBean;
import com.qnote.application.utils.CallBack;
import com.qnote.application.utils.Urls;
import com.qnote.application.utils.VolleyUtil;

/**
 * Created by silei on 2016/9/10.
 */
public class DocModel implements IdocModel<DocModel> {
    private NoteListBean data;
    private int state;
    private String msg;

    @Override
    public  void syncList(final long updatetime, final CallBack<DocModel> callback) {
        String getUrl = Urls.notes + "?" + "updatetime=" + updatetime+"&token="+ Iapplication.getToken();

        final StringRequest request = new StringRequest(Request.Method.GET, getUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        try {
                            setRequest(gson.fromJson(response, NoteListBean.class));
                        }catch (Exception e){
                            state=IdocModel.SYNC_FAIL;
                            msg="同步失败";
                            callback.getModel(DocModel.this);
                            return;
                        }
                        switch(data.getStatus()){
                            case 0:
                                state=IdocModel.SYNC_OK;
                                msg="同步完成";
                                break;
                            case 2001:
                            case 2002:
                                state=IdocModel.NOTOKEN;
                                msg="登录已过期，请重新登录";
                                break;
                            default:
                                state=IdocModel.SYNC_FAIL;
                                msg="同步失败";
                                break;
                        }
                        callback.getModel(DocModel.this);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        state=IdocModel.SYNC_FAIL;
                        msg="网络错误，同步失败";
                        callback.getModel(DocModel.this);
                    }
                });
        VolleyUtil.getRequestQueue().add(request);
    }

    public NoteListBean getRequest() {
        return data;
    }

    public void setRequest(NoteListBean data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
