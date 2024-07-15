package com.fynuls.utils;

import com.fynuls.controllers.Codes;
import com.fynuls.entity.LoginStatus;

import java.util.List;

public class TokenUtil {

    public static <T> ApiResponse<T> validateTokenAndRespond(String token) {
        ApiResponse<T> response = new ApiResponse<>();
        String status = validateToken(token);

        if (status.equals(Codes.LoggedIn)) {
            response.setStatus(Codes.ALL_OK);
        } else {
            response.setStatus(Codes.INVALID_TOKEN);
        }

        return response;
    }

    private static String validateToken(String token) {
        // Your token validation logic goes here
        // For demonstration purposes, assume a simple check for the token being non-null
        List<LoginStatus> loginStatuses = (List<LoginStatus>) HibernateUtil.getDBObjects("from LoginStatus where token='"+token+"'");
        if (loginStatuses != null && loginStatuses.size() > 0) {
            return Codes.LoggedIn;
        } else {
            return Codes.INVALID_TOKEN;
        }
    }

    public static String getPatientCellNumberFromToken(String token) {
        // Your token validation logic goes here
        // For demonstration purposes, assume a simple check for the token being non-null
        List<LoginStatus> loginStatuses = (List<LoginStatus>) HibernateUtil.getDBObjects("from LoginStatus where token='"+token+"'");
        if (loginStatuses != null && loginStatuses.size() > 0) {
            for (LoginStatus loginStatus : loginStatuses) {
                return loginStatus.getUsername();
            }
        }
        return Codes.INVALID_TOKEN;
    }

    public static LoginStatus getLoginStatusFromToken(String token) {
        // Your token validation logic goes here
        // For demonstration purposes, assume a simple check for the token being non-null
        List<LoginStatus> loginStatuses = (List<LoginStatus>) HibernateUtil.getDBObjects("from LoginStatus where token='"+token+"'");
        if (loginStatuses != null && loginStatuses.size() > 0) {
            for (LoginStatus loginStatus : loginStatuses) {
                return loginStatus;
            }
        }
        return null;
    }
}
