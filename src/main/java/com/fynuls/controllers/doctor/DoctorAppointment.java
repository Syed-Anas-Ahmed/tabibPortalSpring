package com.fynuls.controllers.doctor;

import com.fynuls.controllers.Codes;
import com.fynuls.dal.DoctorDAL;
import com.fynuls.dal.DoctorDALConverter;
import com.fynuls.entity.Doctor;
import com.fynuls.entity.LoginStatus;
import com.fynuls.utils.ApiResponse;
import com.fynuls.utils.HibernateUtil;
import com.fynuls.utils.TokenUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 29th Feb,2024
 */
@Controller
public class DoctorAppointment {
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getClinics", method = RequestMethod.GET, params = {"token"})
    @ResponseBody
    public String getClinics(String token) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(apiResponse.getStatus())) {
            DoctorDAL doctor = getDoctorUsername(token);
            apiResponse.setData(doctor);
        } else {
            apiResponse.setStatus(Codes.INVALID_TOKEN);
        }
        return new Gson().toJson(apiResponse);
    }

    private DoctorDAL getDoctorUsername(String token) {
        List<LoginStatus> loginStatuses = (List<LoginStatus>) HibernateUtil.getDBObjects("from LoginStatus where token ='"+token+"'");
        if (loginStatuses != null && loginStatuses.size() > 0) {
            LoginStatus loginStatus = loginStatuses.get(0);
            if (loginStatus != null) {
                String username = loginStatus.getUsername();
                List<Doctor> doctors = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor where username='"+username+"'");
                if (doctors!=null && doctors.size()>0) {
                    Doctor doctor = doctors.get(0);
                    return DoctorDALConverter.convertToDoctorDAL(doctor);
                }
            }
        }
        return null;
    }
}
