package org.iflab.wecentermobileandroidrestructure.tools;

import com.orhanobut.hawk.Hawk;

import org.iflab.wecentermobileandroidrestructure.model.personal.UserPersonal;

/**
 * Created by hcjcch on 15/6/15.
 */
public class HawkControl {
    public static void saveUserCount(UserPersonal userPersonal) {
        Hawk.put(userPersonal.getUid() + "", userPersonal);
    }

    public static UserPersonal loadUserCount(int uid) {
        return Hawk.get(uid + "");
    }


}