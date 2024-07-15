package com.fynuls.controllers;

import com.fynuls.dal.ClinicAppointments;
import com.fynuls.dal.SyncObject;
import com.fynuls.entity.Doctor;
import com.fynuls.entity.LoginStatus;
import com.fynuls.utils.ApiResponse;
import com.fynuls.utils.HibernateUtil;
import com.fynuls.utils.TokenUtil;
import com.fynuls.utils.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author Syed Muhammad Hassan
 *  13th May, 2024
 */

@Controller
public class Patients {
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getClinicPatients", method = RequestMethod.GET, params = {"token"})
    @ResponseBody
    public String getClinicPatients(String token){
        ArrayList<ClinicAppointments> clinicAppointments = new ArrayList<>();
        ApiResponse apiResponse = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(apiResponse.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if (Codes.FROMCLINIC.equals(loginStatus.getType())) {
                String clinicIdStr = loginStatus.getUsername();
                String query = "SELECT p.id, p.name, p.gender, a.age, p.cellNumber, " +
                        " d.id,d.name,d.gender,d.address," +
                        " a.id,a.charges,a.followupDate,a.visitDate, a.diagnosis, a.prescription, a.weight " +
                        " FROM AppointmentModel a\n" +
                        " JOIN a.patient p " +
                        " JOIN a.doctor d " +
                        " where a.clinic.id = " + clinicIdStr;

                ClinicAppointments clinicAppointments1 = new ClinicAppointments();
                ArrayList<Object> clinicAppointmentObjs = (ArrayList<Object>) HibernateUtil.getDBObjects(query);
                if (clinicAppointmentObjs != null && !clinicAppointmentObjs.isEmpty()) {
                    for (Object clinicAppointmentObject : clinicAppointmentObjs) {
                        if (clinicAppointmentObject != null && clinicAppointmentObject instanceof Object[]) {
                            Object[] objArr = (Object[]) clinicAppointmentObject;
                            clinicAppointments1 = new ClinicAppointments();
                            if(objArr[0] != null && objArr[0] instanceof Integer) {
                                clinicAppointments1.setPatientId(Integer.valueOf(objArr[0].toString()));
                            }
                            clinicAppointments1.setPatientName(objArr[1]==null?"":objArr[1].toString());
                            clinicAppointments1.setPatientGender(objArr[2]==null?"":objArr[2].toString());
                            clinicAppointments1.setPatientAge(objArr[3]==null?"":objArr[3].toString());
                            clinicAppointments1.setPatientCellNumber(objArr[4]==null?"":objArr[4].toString());
                            if(objArr[5] != null && objArr[5] instanceof Integer) {
                                clinicAppointments1.setDoctorId(Integer.valueOf(objArr[5].toString()));
                            }
                            clinicAppointments1.setDoctorName(objArr[6]==null?"":objArr[6].toString());
                            clinicAppointments1.setDoctorGender(objArr[7]==null?"":objArr[7].toString());
                            clinicAppointments1.setDoctorAddress(objArr[8]==null?"":objArr[8].toString());
                            if(objArr[9] != null && objArr[9] instanceof Integer) {
                                clinicAppointments1.setAppointmentId(Integer.valueOf(objArr[9].toString()));
                            }
                            if(objArr[10] != null && objArr[10] instanceof Integer) {
                                clinicAppointments1.setAppointmentCharges(Integer.valueOf(objArr[10].toString()));
                            }
                            clinicAppointments1.setFollowupDate(objArr[11]==null?"":objArr[11].toString());
                            clinicAppointments1.setVisitDate(objArr[12]==null?"":objArr[12].toString());
                            clinicAppointments1.setDiagnosis(objArr[13]==null?"":objArr[13].toString());
                            clinicAppointments1.setPrescription(objArr[14]==null?"":objArr[14].toString());
                            if(objArr[15] != null && objArr[15] instanceof Integer) {
                                clinicAppointments1.setWeight(Integer.valueOf(objArr[15].toString()));
                            }
                            clinicAppointments.add(clinicAppointments1);

                        }
                    }
                }
            }
        }
        SyncObject syncObject = new SyncObject();
        syncObject.setClinicAppointments(clinicAppointments);
        apiResponse.setData(syncObject);

        return new Gson().toJson(apiResponse);
    }
}
