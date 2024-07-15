package com.fynuls.controllers;

import com.fynuls.dal.*;
import com.fynuls.entity.*;
import com.fynuls.utils.ApiResponse;
import com.fynuls.utils.HibernateUtil;
import com.fynuls.utils.TokenUtil;
import com.fynuls.utils.Util;
import com.google.gson.Gson;
import com.google.rpc.Code;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 16th December,2021
 */

@Controller
public class RegisterPatient {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/" + Codes.API_REGISTERPATIENT, method = RequestMethod.GET, params = {"patient", "uuid"})
    @ResponseBody
    public String apiRegisterPatient(String patient, String uuid) {
        String token = HibernateUtil.generateToken(uuid);

        SyncObject syncObject = new SyncObject();

        ApiResponse response = new ApiResponse();
        String status = registerPatient(patient);
        if (status.equals(Codes.ALL_OK)) {
            syncObject.setToken(token);

        }
        response.setStatus(status);
        response.setData(syncObject);

        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/" + Codes.API_ADDPATIENT, method = RequestMethod.GET, params = {"patient", "token"})
    @ResponseBody
    public String addPatient(String patient, String token) {
        ApiResponse response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            List<LoginStatus> loginstatus = (List<LoginStatus>) HibernateUtil.getDBObjects("from LoginStatus where token='" + token + "'");
            String cellnum = loginstatus.get(0).getUsername();
            if (Codes.ALL_OK.equals(response.getStatus())) {
                Patient patientObj = new Gson().fromJson(patient, Patient.class);
                patientObj.setCellNumber(cellnum);
                int id = savePatient(patientObj);
                if (id != 0) {
                    patientObj.setId(id);
                    response.setData(patientObj);
                    response.setStatus(Codes.ALL_OK);
                } else {
                    response.setStatus(Codes.SOMETHING_WENT_WRONG);
                }

            }
        }

        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addFeedback", method = RequestMethod.GET, params = {"token", "feedback"})
    @ResponseBody
    public String addFeedback(String token,String feedback) {
        ApiResponse response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            List<LoginStatus> loginstatus = (List<LoginStatus>) HibernateUtil.getDBObjects("from LoginStatus where token='" + token + "'");
            String cellnum = loginstatus.get(0).getUsername();
            if (Codes.ALL_OK.equals(response.getStatus())) {
                FeedbackDAL feedbackDal = new Gson().fromJson(feedback, FeedbackDAL.class);
                Feedback feedbackObj = new Feedback();
                feedbackObj.setRating(feedbackDal.getRating());
                feedbackObj.setDetail(feedbackDal.getDetail());
                feedbackObj.setToken(token);
                Patient patient = (Patient) HibernateUtil.getObjectById(Patient.class, feedbackDal.getPatientId());
                Doctor doctor = (Doctor) HibernateUtil.getObjectById(Doctor.class, feedbackDal.getDoctorId());
                boolean isSuccessful = false;
                if (patient != null && doctor != null) {
                    feedbackObj.setDoctor(doctor);
                    feedbackObj.setPatient(patient);
                    isSuccessful = HibernateUtil.save(feedbackObj);
                }
                if (!isSuccessful) {
                    response.setStatus(Codes.SOMETHING_WENT_WRONG);
                }
            }
        }

        return new Gson().toJson(response);
    }

    public String registerPatient(String patient) {
        boolean isSuccessFull = false;

        Patient patientObj = new Gson().fromJson(patient, Patient.class);

        List<User> users = new ArrayList<>();

        users = (List<User>) HibernateUtil.getDBObjects("from User where username='" + patientObj.getCellNumber() + "'");
        if (users != null && users.size() > 0) {
            return Codes.ALREADY_REGISTERED;

        } else {
            int id = savePatient(patientObj);

            User user = new User();
            user.setUsername(patientObj.getCellNumber());
            user.setName(patientObj.getName());
            user.setCellNumber(patientObj.getCellNumber());
            user.setPassword(patientObj.getPassword());
            user.setType(Codes.FROMPATIENT);
            user.setUserId(id);
            if (id != 0) {
                isSuccessFull = HibernateUtil.save(user);
            }

            if (isSuccessFull) {
                return Codes.ALL_OK;
            } else {
                return Codes.SOMETHING_WENT_WRONG;
            }
        }
    }

    public int savePatient(Patient patient) {
        return HibernateUtil.saveAndGetIdOld(patient);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getDummyFeedback", method = RequestMethod.GET)
    @ResponseBody
    public String getDummyFeedback(){
        FeedbackDAL feedbackDAL = new FeedbackDAL();
        feedbackDAL.setDetail("Feedback detail");
        feedbackDAL.setRating(5);
        feedbackDAL.setDoctorId(1);
        feedbackDAL.setPatientId(2);
        return new Gson().toJson(feedbackDAL);
    }
}