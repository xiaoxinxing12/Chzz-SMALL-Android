package org.chzz.app.main.engine;

import org.chzz.app.main.model.bean.BaseModel;
import org.chzz.app.main.mvp.presenter.BaseListener;
import org.chzz.app.main.presenter.listener.OnDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by copy on 2016/4/23.
 */
public class DataCallback<T extends BaseModel> implements Callback<T> {
    private OnDataListener mOnDataListener;
    private int code;

    public DataCallback(OnDataListener onDataListener, int code) {
        this.mOnDataListener = onDataListener;
        this.code = code;
    }

    public DataCallback(OnDataListener baseListener) {
        this.mOnDataListener = baseListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.mOnDataListener.result(response.body(), code);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
