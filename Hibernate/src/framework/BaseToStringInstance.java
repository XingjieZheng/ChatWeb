package framework;

import com.google.gson.Gson;

/**
 * Created by XingjieZheng
 * on 2016/8/16.
 */
public abstract class BaseToStringInstance {

    @Override
    public String toString() {
        Gson gson = new Gson();
        return this.getClass().getSimpleName() + " " + gson.toJson(this);
    }
}
