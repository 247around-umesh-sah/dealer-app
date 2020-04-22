package helper;

import com.example.dealerapp.MainActivity;
import com.example.dealerapp.SplashActivity;
import com.google.gson.internal.Primitives;

public class MainActivityHelper {
    private static MainActivity applicationObj;
    private static ApplicationHelper applicationHelper;

    public static MainActivity application() {
        return applicationObj;
    }


    public static void setApplicationHelper(ApplicationHelper applicationHelper){
        MainActivityHelper.applicationHelper=applicationHelper;
    }
    public static ApplicationHelper applicationHelper() {
        return MainActivityHelper.applicationHelper;
    }



    public static <T> T application(Class<T> entity) {
        return Primitives.wrap(entity).cast(application());
    }

    public static void setApplicationObj(MainActivity applicationObj) {
        MainActivityHelper.applicationObj = applicationObj;
    }
}
