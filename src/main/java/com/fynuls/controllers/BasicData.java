package com.fynuls.controllers;

import com.fynuls.dal.*;
import com.fynuls.entity.*;
import com.fynuls.utils.ApiResponse;
import com.fynuls.utils.HibernateUtil;
import com.fynuls.utils.TokenUtil;
import com.fynuls.utils.Util;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Syed Muhammad Hassan
 * 24th May,2022
 */

@RestController
public class BasicData {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_ALLBASICDATA, method = RequestMethod.GET,params = {"token"})
    @ResponseBody
    public String getAllBasicData(@RequestParam String token){

        ApiResponse response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if(loginStatus.getType().equals(Codes.FROMPATIENT)) {
                List<Doctor> doctorsFromQuery = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor");
                List<DoctorDAL> doctors = new ArrayList<>();

                for (Doctor doctor : doctorsFromQuery) {
                    doctors.add(DoctorDALConverter.convertToDoctorDAL(doctor));
                }
                SyncObject syncObject = new SyncObject();
                syncObject.setDoctors(doctors);

                response.setStatus(Codes.ALL_OK);
                response.setData(syncObject);
            } else {
                response.setStatus(Codes.SOMETHING_WENT_WRONG);
            }
        }

        return new Gson().toJson(response);

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getProfile", method = RequestMethod.GET,params = {"token"})
    @ResponseBody
    public String getProfile(@RequestParam String token){

        ApiResponse response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if(loginStatus.getType().equals(Codes.FROMDOCTOR)) {

                List<Doctor> doctorsFromQuery = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor where username ='"+loginStatus.getUsername()+"'");
                List<DoctorDAL> doctors = new ArrayList<>();

                for (Doctor doctor : doctorsFromQuery) {
                    doctors.add(DoctorDALConverter.convertToDoctorDAL(doctor));
                }
                SyncObject syncObject = new SyncObject();
                syncObject.setDoctors(doctors);

                response.setStatus(Codes.ALL_OK);
                response.setData(syncObject);
            } else if (loginStatus.getType().equals(Codes.FROMPATIENT)) {

                List<Patient> patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where status=1 and cellNumber='"+loginStatus.getUsername()+"'");
                List<PatientDAL> patientDALS = new ArrayList<>();
                if (patients != null) {
                    for (Patient patient : patients) {
                        patientDALS.add(PatientDALConverter.convertToPatientDAL(patient));
                    }
                }

                SyncObject syncObject = new SyncObject();
                syncObject.setPatients(patientDALS);

                response.setStatus(Codes.ALL_OK);
                response.setData(syncObject);
            } else {
                response.setStatus(Codes.SOMETHING_WENT_WRONG);
            }
        }

        return new Gson().toJson(response);

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_SENDFEEDBACK, method = RequestMethod.GET,params = {"token", "syncObject"})
    @ResponseBody
    public String sendFeedback(String token, String syncObject){
        boolean isSuccessful =false;
        Response response = new Response();

        SyncObject syncObjectObj = new Gson().fromJson(syncObject,SyncObject.class);
        if(syncObjectObj!=null && syncObjectObj.getFeedback()!=null){
            Feedback feedback = syncObjectObj.getFeedback();
            feedback.setToken(token);

            isSuccessful = HibernateUtil.save(feedback);
        }

        if(isSuccessful){
            response.setStatus(Codes.ALL_OK);
        }else{
            response.setStatus(Codes.SOMETHING_WENT_WRONG);
        }
        response.setSyncObject(syncObjectObj);
        return new Gson().toJson(response, Response.class);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_GETSUMMARY, method = RequestMethod.GET,params = {"token"})
    @ResponseBody
    public String getSummary(String token){
        boolean isSuccessful =false;
        ApiResponse apiResponse = TokenUtil.validateTokenAndRespond(token);

        if (apiResponse.getStatus().equals(Codes.ALL_OK)) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);

            if (loginStatus.getType().equals(Codes.FROMDOCTOR)) {
                Doctor doctor = Util.getDoctorByUsername(loginStatus.getUsername());

                String query="select a.visitDate, count(*), sum(a.charges) from AppointmentModel a where a.doctor.id = " + doctor.getId() + " group by visitDate order by visitDate desc";

                ArrayList<AppointmentModel> objs = (ArrayList<AppointmentModel>) HibernateUtil.getDBObjects(query);
                Summary summary = new Summary();
                List<Summary> summaries = new ArrayList<>();
                String html = "";
                if(objs !=null){
                    for(Object obj : objs){
                        if(obj!=null){
                            Object[] objArr = (Object[]) obj;
                            if(objArr!=null && objArr.length==3){
                                summary = new Summary();
                                String visitDate = objArr[0].toString();
                                int count = Integer.valueOf(objArr[1].toString());
                                int charges = Integer.valueOf(objArr[2].toString());
                                summary.setCharges(charges);
                                summary.setCount(count);
                                summary.setVisitDate(Util.formatDate(visitDate));
                                summaries.add(summary);
                            }
                        }
                    }
                }

                if(summaries!=null && summaries.size()>0){
                    html = getSummaryHTML(summaries);
                }else{
                    html = "<html><p>No data found...</p></html>";
                }
                apiResponse.setData(html);
            }

        }


        return new Gson().toJson(apiResponse);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_CHANGEPASSWORD, method = RequestMethod.GET,params = {"token","oldPassword","newPassword"})
    @ResponseBody
    public String changePassword(String token, String oldPassword, String newPassword){
        boolean isSuccessful =false;
        ApiResponse response = new ApiResponse();
        response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if(loginStatus!=null && loginStatus.getUsername()!=null && !loginStatus.getUsername().equals("")) {
                List<User> users = (List<User>) HibernateUtil.getDBObjects("from User where password='"+oldPassword+"' and username='"+loginStatus.getUsername()+"'");
                if (users!=null && !users.isEmpty()) {
                    User user = users.get(0);
                    user.setPassword(newPassword);
                    if(HibernateUtil.saveOrUpdate(user)){
                        response.setStatus(Codes.ALL_OK);
                    }else {
                        response.setStatus(Codes.SOMETHING_WENT_WRONG);
                    }
                }else{
                    response.setStatus(Codes.UNAUTHORIZED);
                }
            }else{
                response.setStatus(Codes.INVALID_TOKEN);
            }
        }


        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/updateFCMToken", method = RequestMethod.GET,params = {"token","fcm"})
    @ResponseBody
    public String updateFCMToken(String token, String fcm){
        boolean isSuccessful =false;
        ApiResponse response = new ApiResponse();
        response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if(loginStatus!=null && loginStatus.getUsername()!=null && !loginStatus.getUsername().equals("")) {
                List<User> users = (List<User>) HibernateUtil.getDBObjects("from User where username='"+loginStatus.getUsername()+"'");
                if (users!=null && !users.isEmpty()) {
                    User user = users.get(0);
                    user.setFcmToken(fcm);
                    if(HibernateUtil.saveOrUpdate(user)){
                        response.setStatus(Codes.ALL_OK);
                    }else {
                        response.setStatus(Codes.SOMETHING_WENT_WRONG);
                    }
                }else{
                    response.setStatus(Codes.UNAUTHORIZED);
                }
            }else{
                response.setStatus(Codes.INVALID_TOKEN);
            }
        }


        return new Gson().toJson(response);
    }

    private String getSummaryHTML(List<Summary> summaries) {
        String html = "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "#customers {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #015C92;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table id=\"customers\">\n" +
                "  <tr>\n" +
                "    <th>Visit Date</th>\n" +
                "    <th>No. of Patients</th>\n" +
                "\t<th>Total charges</th>\n" +
                "  </tr>";

        for(Summary summary : summaries){
            html += "<tr>\n" +
                    "    <td>"+summary.getVisitDate()+"</td>\n" +
                    "    <td>"+summary.getCount()+"</td>\n" +
                    "    <td>"+summary.getCharges()+"</td>\n" +
                    "  </tr>";
        }
        html+="</table>\n" +
                "</body>\n" +
                "</html>";

        return html;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/testApi", method = RequestMethod.GET)
    @ResponseBody
    public String testApi(){
        List<Patient> patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient");

        Patient patient = new Patient();
        patient.setStatus(1);
        patient.setName("Muhammad");
        List<PatientDAL> pat = PatientDALConverter.convertToPatientDALList(patients);

        return new Gson().toJson(pat);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/testApiss", method = RequestMethod.GET)
    @ResponseBody
    public String testApiss(){
        List<Patient> patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient");

        Patient patient = new Patient();
        patient.setStatus(1);
        patient.setName("Muhammad");
        List<PatientDAL> pat = PatientDALConverter.convertToPatientDALList(patients);

        return new Gson().toJson(pat);
    }

}
