package helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.RetailApp.SplashActivity;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by abhay on 28/12/17.
 */
@SuppressWarnings("DefaultFileTemplate")
public class GetToken {

    public Context context;
    //SharedPreferences sharedPrefs;
    private Boolean isEmulator = new DeviceInfo(this.context).isEmulator();
    private Boolean isRooted = new DeviceInfo(this.context).isRooted();
    private String osName = new DeviceInfo(this.context).getOs();
    private String version = new DeviceInfo(this.context).getVersion();
    private String model = new DeviceInfo(this.context).getModel();
    private String device = new DeviceInfo(this.context).getDevice();

    public GetToken(Context context) {
        this.context = context;
//        sharedPrefs = MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO);//context.getSharedPreferences(SplashActivity.MyPREFERENCES,
               // Context.MODE_PRIVATE);
    }

    public String getAuthToken(String[] params) throws Exception {
        Log.d("aaaaa","getuthToken");
        String subUrl = params[0];

        Map<String, String> urlParameters = new HashMap<>();
        String deviceInfo = getDeviceInfo();
        urlParameters.put("deviceInfo", deviceInfo);
        urlParameters.put("method", "post");
        urlParameters.put("apiPath", "api");

        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
        }

        assert pInfo != null;
        String appVersion = pInfo.versionName;
        urlParameters.put("app_version", appVersion);

        switch (subUrl) {

            case "dealerLogin":
                dealerLogin(urlParameters, params);
                break;
            case "dealerHomeScreen":
                engineerHomeScreen(urlParameters, params);
                break;
            case "searchData":
                searchData(urlParameters,params);
                break;
            case "getSpareTabData":
                getSpareTabData(urlParameters,params);
                break;
            case "submitEscalation":
                submitEscalation(urlParameters,params);
                break;
            case "getEscalationReason":
                getEscalationReason(urlParameters,params);
                break;
            case "getTrackingData":
                getTrackingData(urlParameters,params);
                break;











        }

        String token = generateToken(urlParameters, subUrl);
        Map<String, String> jsonData = new HashMap<>();
        DeviceInfo deviceInfo1 = new DeviceInfo(this.context);
        String deviceId=null;
        if(deviceInfo1!=null &&  !(this.context instanceof SplashActivity)) {
            deviceId = deviceInfo1.bindIds();
        }
        Log.d("aaaaa","print url JSON = "+BMAGson.store().toJson(urlParameters));

        String requestId = UUID.randomUUID().toString();

        jsonData.put("requestId", requestId);
        jsonData.put("token", token);
        jsonData.put("requestUrl", subUrl);
        jsonData.put("deviceId", deviceId);
        jsonData.put("app_version", appVersion);


        return new Gson().toJson(jsonData);
    }



    public String generateToken(Map<String, String> urlParameters, String subUrl) {
        String jwtToken = "";
        try {
            String json = new Gson().toJson(urlParameters);

            String key = "boloaaka-mobile-application";
            String sharedSecret = JwtBuilder.signHmac256("username", "boloaaka-signup-request");

            jwtToken = JwtBuilder.generateJWTToken(subUrl, json, key, sharedSecret);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return jwtToken;
    }


    public Map<String, String> dealerLogin(Map<String, String> urlParameters, String[] params) {
        try {

            String mobile = params[1];
            String password = params[2];
          //  String deviceToken = params[3];
         //   Log.d("zzzzz", " request deviceToken is = " + deviceToken);
            urlParameters.put("mobile", mobile);
            urlParameters.put("password", password);
          //  urlParameters.put("device_firebase_token", deviceToken);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }
    public Map<String, String> searchData(Map<String, String> urlParameters,
                                          String[] params) {
        try {

            urlParameters.put("entity_id", params[1]);
            urlParameters.put("entity_type", params[2]);
            urlParameters.put("search_value", params[3]);
            urlParameters.put("dealer_pincode", params[4]);




        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }
    public Map<String, String> getSpareTabData(Map<String, String> urlParameters,
                                          String[] params) {
        try {

            urlParameters.put("booking_id", params[1]);





        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }


    public Map<String, String> engineerHomeScreen(Map<String, String> urlParameters, String[] params) {
        try {

            String engineerID = params[1];
            String serviceCenetrID = params[2];
            String fireBaseToken = params[4];

            urlParameters.put("service_center_id", serviceCenetrID);
            urlParameters.put("engineer_id", engineerID);
            urlParameters.put("engineer_pincode", params[3]);

                urlParameters.put("device_firebase_token", fireBaseToken);


         //   urlParameters.put("deviceBattery", params[4]);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }
    public Map<String, String> submitEscalation(Map<String, String> urlParameters,
                                               String[] params) {
        try {
            urlParameters.put("entity_type", params[1]);
            urlParameters.put("booking_id", params[2]);
            urlParameters.put("escalation_reason_id", params[3]);
            urlParameters.put("escalation_remarks", params[4]);






        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }
    public Map<String, String> getEscalationReason(Map<String, String> urlParameters,
                                                String[] params) {
        try {
            //urlParameters.put("appliance_id", params[2]);
            urlParameters.put("entity_type", params[1]);
           // urlParameters.put("is_repeat ", params[3]);
            //urlParameters.put("show_all_capacity  ", params[4]);






        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }
    public Map<String, String> getTrackingData(Map<String, String> urlParameters,
                                                   String[] params) {
        try {


            //urlParameters.put("appliance_id", params[2]);
            urlParameters.put("carrier_code", params[1]);
            urlParameters.put("awb_number", params[2]);
            // urlParameters.put("is_repeat ", params[3]);
            //urlParameters.put("show_all_capacity  ", params[4]);






        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlParameters;
    }





















    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    public String getDeviceInfo() {

        HashMap<String, String> dInfo = new HashMap<>();

        dInfo.put("os", osName);
        dInfo.put("platformVersion", version);
        dInfo.put("model", model);
        dInfo.put("modelVersion", device);
        dInfo.put("isRooted", isRooted.toString());
        dInfo.put("isEmulator", isEmulator.toString());

        return new Gson().toJson(dInfo);
    }

}
