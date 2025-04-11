package utils;

import constants.Constant;

public class VersionUtil {

    public static boolean isValidApiVersion(Short apiVersion) {
        return Constant.SUPPORTED_API_VERSIONS.contains(apiVersion);
    }

}
