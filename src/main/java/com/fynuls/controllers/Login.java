package com.fynuls.controllers;

import com.fynuls.dal.DoctorDetail;
import com.fynuls.dal.Response;
import com.fynuls.dal.SyncObject;
import com.fynuls.entity.*;
import com.fynuls.utils.ApiResponse;
import com.fynuls.utils.HibernateUtil;
import com.fynuls.utils.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Syed Muhammad Hassan
 * 16th May,2022
 */
@Controller
public class Login {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_LOGIN, method = RequestMethod.GET, params = {"username","password","UUID", "type"})
    @ResponseBody
    public String login(String username, String password, String UUID, String type){
        boolean isSuccessFull= false;
        SyncObject syncObject = new SyncObject();
        ApiResponse apiResponse = new ApiResponse();
        List<User> users = (List<User>) HibernateUtil.getDBObjects("from User where type='"+type+"' and username='"+username+"' and password='"+password+"'");
        if(users!=null && users.size()>0){
            LoginStatus loginStatus = new LoginStatus();
            loginStatus.setUsername(username);
            loginStatus.setType(type);
            String token = HibernateUtil.generateToken(UUID);
            loginStatus.setToken(token);
            loginStatus.setStatus(Codes.LoggedIn);

            isSuccessFull = HibernateUtil.save(loginStatus);
            if(isSuccessFull){
                apiResponse.setStatus(Codes.ALL_OK);
            }else{
                apiResponse.setStatus(Codes.SOMETHING_WENT_WRONG);
            }

            syncObject.setToken(token);
            apiResponse.setData(syncObject);
        }else{
            apiResponse.setStatus(Codes.INCORRECTCREDENTIALS);
        }

        return new Gson().toJson(apiResponse);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_LOGINPATIENT, method = RequestMethod.GET, params = {"username", "password", "UUID"})
    @ResponseBody
    public String loginPatient(String username, String password, String UUID)
    {
        boolean isSuccessFull= false;
        LoginStatus loginStatus = new LoginStatus();

        String token = HibernateUtil.generateToken(UUID);
        loginStatus.setToken(token);
        loginStatus.setType(Codes.FROMPATIENT);
        loginStatus.setStatus(Codes.LoggedIn);
        loginStatus.setUsername(username);
        isSuccessFull = HibernateUtil.save(loginStatus);
        ApiResponse response = new ApiResponse();
        if(isSuccessFull){
            SyncObject syncObject = new SyncObject();
            syncObject.setToken(token);
            response.setStatus(Codes.ALL_OK);
            response.setData(syncObject);
        }else{
            response.setStatus(Codes.SOMETHING_WENT_WRONG);
        }
        return new Gson().toJson(response);
    }
}
