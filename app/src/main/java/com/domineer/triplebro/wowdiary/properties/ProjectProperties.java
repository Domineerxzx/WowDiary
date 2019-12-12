package com.domineer.triplebro.wowdiary.properties;

public class ProjectProperties {

    public static final String ADDRESS_GET_REQUEST_CODE = "http://120.25.96.141:8080/login/request";

    public static final String ADDRESS_REGISTER = "http://120.25.96.141:8080/login/registernumber";

    public static final String ADDRESS_UPDATE_REGISTER_INFO = "http://120.25.96.141:8080/user/update_info";

    public static final String ADDRESS_LOGIN = "http://120.25.96.141:8080/login/login";

    public static final int LOGIN_SUCCESS = 0;

    public static final int LOGIN_ADMIN_SUCCESS = 2;

    public static final int LOGIN_ADMIN_FAILED = 3;

    public static final int LOGIN_FAILED = 1;

    public static final int REGISTER_SUCCESS = 0;

    public static final int REGISTER_FAILED = 1;

    public static final int GET_REQUEST_CODE_SUCCESS = 2;

    public static final int GET_REQUEST_CODE_FAILED = 3;

    public static final int FROM_GALLERY = 1;

    public static final int FROM_CAMERA = 2;

    public static final int ADMIN = 1;

    public static final int USER = 2;

    public static final int REGISTER_ADMIN_SUCCESS = 4;

    public static final int DATA_INSERT_SUCCESS = 1;

    public static final int DATA_INSERT_FAILED = 2;

    //获取token令牌的地址
    public static final String TOKEN_ADDRESS = "https://thethreestooges.cn:5210/identity/oss/token.php";
    //oss服务器地址
    public static final String ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com";
    //oss服务器上的存储空间名
    public static final String BUCKET_NAME = "thethreestooges";
    //massage的4种情况
    public static final int WHAT_SUCCESS_DOWNLOAD = 3;

    public static final int WHAT_SUCCESS_UPLOAD = 1;

    public static final int WHAT_FAILED_UPLOAD = 2;

    public static final int WHAT_FAILED_DOWNLOAD = 4;

    public static final String AD_PICTURE_PATH = "http://guolin.tech/api/bing_pic";
    public static final int AD_PICTURE = 1;
    public static final int SKIP = 2;
    public static final int ADD_COMMENT = 1;
    public static final int ADD_COMMENT_IN_COMMENT = 2;
    public static final String[] classNumber = {"1班","2班","3班","4班","5班","6班","7班","8班","9班","10班","11班","12班","13班","14班","15班","16班","17班","18班","19班","20班"};
    public static final String[] gradeNumber = {"1年级","2年级","3年级","4年级","5年级","6年级","7年级","8年级","9年级","大一","大二","大三","大四","研一","研二","研三","博一","博二"};
}
