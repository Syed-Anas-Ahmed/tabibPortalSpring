package com.fynuls.controllers;

import com.fynuls.dal.*;
import com.fynuls.entity.*;
import com.fynuls.utils.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * @author Syed Muhammad Hassan
 * 12th May,2022
 */

@Controller
public class Appointment {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getFamily", method = RequestMethod.GET, params = {"token"})
    @ResponseBody
    public String getFamily(String token){
        List<Patient> patients = new ArrayList<>();
        SyncObject syncObject = new SyncObject();
        String cellNumber = TokenUtil.getPatientCellNumberFromToken(token);
        ApiResponse response = new ApiResponse();
        if(cellNumber != null && !cellNumber.equals("")) {
            patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where status=1 and cellNumber='"+cellNumber+"'");
            if(patients!=null && patients.size()>0){
                List<PatientDAL> patientDAL = PatientDALConverter.convertToPatientDALList(patients);
                syncObject.setPatients(patientDAL);
                response.setStatus(Codes.ALL_OK);
                response.setData(syncObject);
            }else{
                response.setStatus(Codes.NOT_FOUND);
            }
        } else {
            response.setStatus(Codes.INVALID_TOKEN);
        }
        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/removeFamilyMember", method = RequestMethod.GET, params = {"token", "patientId"})
    @ResponseBody
    public String removeFamilyMember(String token, int patientId){
        List<Patient> patients = new ArrayList<>();
        boolean isSuccessful = false;
        ApiResponse response = TokenUtil.validateTokenAndRespond(token);
        if(Codes.ALL_OK.equals(response.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where id = "+ patientId + " and cellNumber='"+loginStatus.getUsername()+"'");
            if(patients != null && !patients.isEmpty()) {
                Patient patient = patients.get(0);
                patient.setStatus(2);
                isSuccessful = HibernateUtil.saveOrUpdate(patient);
            }
            if (!isSuccessful) {
                response.setStatus(Codes.SOMETHING_WENT_WRONG);
            }
        }
        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/setAppointment", method = RequestMethod.GET, params = {"token", "appointment"})
    @ResponseBody
    public String setAppointment(String token, String appointment){
        int age = 0;
        SyncObject syncObject = new SyncObject();
        ApiResponse apiResponse = TokenUtil.validateTokenAndRespond(token);
        AppointmentDAL appointmentDAL = new Gson().fromJson(appointment, AppointmentDAL.class);

        if(Codes.ALL_OK.equals(apiResponse.getStatus()) && appointmentDAL != null && !"".equals(appointmentDAL)) {
            apiResponse = setAppointment(apiResponse, appointmentDAL, token);
        }

        return new Gson().toJson(apiResponse);
    }

    public ApiResponse setAppointment(ApiResponse apiResponse, AppointmentDAL appointmentDAL, String token){
        List<Doctor> doctors = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor where id="+appointmentDAL.getDoctorId());
        List<Clinic> clinics = (List<Clinic>) HibernateUtil.getDBObjects("from Clinic where id="+appointmentDAL.getClinicId());
        List<Patient> patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where status=1 and id="+appointmentDAL.getPatientId());

        if (doctors == null || doctors.isEmpty()
                || clinics == null || clinics.isEmpty()
                || patients == null || patients.isEmpty()) {
            apiResponse.setStatus(Codes.NOT_FOUND);
        } else {
            int newToken = 0;
            int age = 0;
            SyncObject syncObject = new SyncObject();

            Patient patient = patients.get(0);
            Doctor doctor = doctors.get(0);
            Clinic clinic = clinics.get(0);
            List<AppointmentModel> appointmentModels = (List<AppointmentModel>) HibernateUtil.getDBObjects("from AppointmentModel a where visitDate='" + appointmentDAL.getVisitDate() + "' and a.doctor.id=" + appointmentDAL.getDoctorId() + " and a.clinic.id=" + appointmentDAL.getClinicId() + " order by token desc");
            if (appointmentModels != null && !appointmentModels.isEmpty()) {
                newToken = appointmentModels.get(0).getToken() + 1;
            } else {
                newToken++;
            }
            AppointmentModel appointmentModel = new AppointmentModel();
            if (appointmentDAL.getId() != 0) {
                appointmentModel = (AppointmentModel) HibernateUtil.getObjectById(AppointmentModel.class, appointmentDAL.getId());
            }
            appointmentModel.setVisitDate(appointmentDAL.getVisitDate());
            appointmentModel.setToken(newToken);
            appointmentModel.setDoctor(doctor);
            appointmentModel.setPatient(patient);
            appointmentModel.setClinic(clinic);

            if (patient.getDob() != null && !patient.getDob().equals("")) {
                age = getAgeFromDob(patient.getDob());

            }
            appointmentModel.setAge(age);
            appointmentModel.setDiagnosis(appointmentDAL.getDiagnosis());
            appointmentModel.setCharges(appointmentDAL.getCharges());
            appointmentModel.setPrescription(appointmentDAL.getPrescription());
            appointmentModel.setStatus(appointmentDAL.getStatus());
            appointmentModel.setFollowupDate(appointmentDAL.getFollowupDate());
            appointmentModel.setBloodPressure(appointmentDAL.getBloodPressure());
            appointmentModel.setWeight(appointmentDAL.getWeight());

            if (appointmentDAL.getTreatments() != null && !appointmentDAL.getTreatments().isEmpty()) {
                appointmentModel.setTreatments(getTreatments(appointmentModel, appointmentDAL.getTreatments()));
            }

            int id = 0;

            if (appointmentDAL.getId() != 0) {
                HibernateUtil.updateRecord(appointmentModel);
                id = appointmentModel.getId();
            } else {
                id = HibernateUtil.saveAndGetIdOld(appointmentModel);
            }

            if (id != 0) {
                if (appointmentDAL.getDoctorId() != 0 && appointmentDAL.getPatientId() != 0) {
                    LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
                    if (loginStatus.getType().equals(Codes.FROMDOCTOR) && appointmentDAL.getStatus() == 1) {
                        Patient patient1 = (Patient) HibernateUtil.getObjectById(Patient.class, appointmentDAL.getPatientId());
                        if (patient1 != null) {
                            List<User> users = (List<User>) HibernateUtil.getDBObjects("from User username='" + patient1.getCellNumber() + "'");
                            if (users != null && !users.isEmpty()) {
                                User user = users.get(0);
                                if (user != null && user.getFcmToken() != null && !"".equals(user.getFcmToken())) {
                                    NotificationSender.sendNotification(user.getFcmToken(), "Your appointment with Dr. " + appointmentDAL.getDoctorName() + " has completed.", "Your appointment with Dr. " + appointmentDAL.getDoctorName() + " has completed. and you have paid RS " + appointmentDAL.getCharges());
                                }
                            }
                        }
                    } else if (loginStatus.getType().equals(Codes.FROMPATIENT)) {
                        Patient patient1 = (Patient) HibernateUtil.getObjectById(Patient.class, appointmentDAL.getPatientId());
                        if (patient1 != null) {
                            List<Doctor> doctors1 = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor where id=" + appointmentDAL.getDoctorId());
                            if (doctors1 != null && !doctors1.isEmpty()) {
                                List<User> users = (List<User>) HibernateUtil.getDBObjects("from User username='" + doctors1.get(0).getUsername() + "'");
                                if (users != null && !users.isEmpty()) {
                                    User user = users.get(0);
                                    if (user != null && user.getFcmToken() != null && !"".equals(user.getFcmToken())) {
                                        NotificationSender.sendNotification(user.getFcmToken(), "You got an appointment from " + appointmentDAL.getPatientName() + " at clinic " + appointmentDAL.getClinicName(), "You got an appointment from " + appointmentDAL.getPatientName() + " at clinic " + appointmentDAL.getClinicName());
                                    }
                                }
                            }

                        }
                    }
                }
                appointmentDAL.setId(id);
                appointmentDAL.setAge(age);
                List<AppointmentDAL> appointmentDALS = new ArrayList<>();
                appointmentDALS.add(appointmentDAL);
                syncObject.setAppointments(appointmentDALS);
                apiResponse.setData(syncObject);
            } else {
                apiResponse.setStatus(Codes.SOMETHING_WENT_WRONG);
            }
        }

        return apiResponse;
    }

    private List<Treatment> getTreatments(AppointmentModel appointmentModel, List<TreatmentDAL> treatmentDALSs) {
        List<Treatment> treatments = new ArrayList<>();
        Treatment treatment = new Treatment();

        for (TreatmentDAL treatmentDAL : treatmentDALSs) {
            treatment = new Treatment();
            treatment.setAppointmentModel(appointmentModel);
            treatment.setDetail(treatmentDAL.getDetail());
            treatment.setName(treatmentDAL.getName());
            treatment.setId(treatmentDAL.getId());
            treatments.add(treatment);
        }
        return treatments;
    }

    private int getAgeFromDob(String dob) {
        // Parse the date of birth string to a LocalDate object
        LocalDate birthDate = LocalDate.parse(dob);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the age
        Period period = Period.between(birthDate, currentDate);

        // Return the age
        return period.getYears();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/viewAppointments", method = RequestMethod.GET, params = {"token", "visitDate", "clinicId", "patientId", "doctorId", "appointmentId", "followupDate"})
    @ResponseBody
    public String viewAppointments(String token, String visitDate, int clinicId, int patientId, int doctorId, int appointmentId, String followupDate){
        ApiResponse response = new ApiResponse();
        response = TokenUtil.validateTokenAndRespond(token);
        List<AppointmentModel> appointmentModels = new ArrayList<>();

        if (Codes.ALL_OK.equals(response.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            String query = "";
            if (loginStatus!=null) {
                if (loginStatus.getType().equals(Codes.FROMDOCTOR)) {
                    List<Doctor> doctors = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor where username ='" + loginStatus.getUsername() + "'");

                    if (doctors != null && !doctors.isEmpty()) {
                        query = "from AppointmentModel a where ";
                        boolean hasCondition = false;

                        if (visitDate != null && !visitDate.isEmpty()) {
                            query += "a.visitDate = '" + visitDate + "'";
                            hasCondition = true;
                        }

                        if (followupDate != null && !followupDate.isEmpty()) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.followupDate = '" + followupDate + "'";
                            hasCondition = true;
                        }

                        if (appointmentId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.id = '" + appointmentId + "'";
                            hasCondition = true;
                        }

                        if (clinicId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.clinic.id = " + clinicId;
                            hasCondition = true;
                        }

                        if (doctors.get(0).getId() != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.doctor.id = " + doctors.get(0).getId();
                        }
                    }

                    query += " order by a.visitDate DESC";

                } else if (loginStatus.getType().equals(Codes.FROMPATIENT)) {
                    List<Patient> patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where status=1 and cellNumber ='" + loginStatus.getUsername() + "'");

                    if (patientId != 0) {
                        // Use the provided patientId if available
                        query = "from AppointmentModel a where ";
                        boolean hasCondition = false;

                        query += "a.patient.id = " + patientId;
                        hasCondition = true;

                        if (visitDate != null && !visitDate.isEmpty()) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.visitDate = '" + visitDate + "'";
                            hasCondition = true;
                        }

                        if (clinicId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.clinic.id = " + clinicId;
                            hasCondition = true;
                        }

                        if (appointmentId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.id = " + appointmentId;
                            hasCondition = true;
                        }

                        if (followupDate != null && !followupDate.isEmpty()) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.followupDate = " + followupDate;
                            hasCondition = true;
                        }

                        if (doctorId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.doctor.id = " + doctorId;
                            hasCondition = true;
                        }

                        query += " order by a.visitDate DESC";


                    } else if (patients != null && !patients.isEmpty()) {
                        // Use patient IDs from the patients list if no specific patientId provided
                        List<Integer> patientIds = new ArrayList<>();
                        for (Patient patient : patients) {
                            if (patient.getId() != 0) {
                                patientIds.add(patient.getId());
                            }
                        }

                        if (!patientIds.isEmpty()) {
                            query = "from AppointmentModel a where ";
                            boolean hasCondition = false;

                            String patientIdList = StringUtils.join(patientIds, ",");
                            query += "a.patient.id IN (" + patientIdList + ")";
                            hasCondition = true;

                            if (visitDate != null && !visitDate.isEmpty()) {
                                if (hasCondition) {
                                    query += " and ";
                                }
                                query += "a.visitDate = '" + visitDate + "'";
                                hasCondition = true;
                            }

                            if (clinicId != 0) {
                                if (hasCondition) {
                                    query += " and ";
                                }
                                query += "a.clinic.id = " + clinicId;
                                hasCondition = true;
                            }
                            if (appointmentId != 0) {
                                if (hasCondition) {
                                    query += " and ";
                                }
                                query += "a.id = " + appointmentId;
                                hasCondition = true;
                            }

                            if (followupDate != null && !followupDate.isEmpty()) {
                                if (hasCondition) {
                                    query += " and ";
                                }
                                query += "a.followupDate = " + followupDate;
                                hasCondition = true;
                            }

                            if (doctorId != 0) {
                                if (hasCondition) {
                                    query += " and ";
                                }
                                query += "a.doctor.id = " + doctorId;
                                hasCondition = true;
                            }

                            query += " order by a.visitDate DESC";

                        }
                    }
                } else if (loginStatus.getType().equals(Codes.FROMCLINIC)) {
                    List<Clinic> clinics = (List<Clinic>) HibernateUtil.getDBObjects("from Clinic where id ='" + loginStatus.getUsername() + "'");

                    if (clinics != null && !clinics.isEmpty()) {
                        Set<DoctorClinic> doctorClinics = clinics.get(0).getDoctorClinics();

                        query = "from AppointmentModel a where ";
                        boolean hasCondition = false;

                        if (visitDate != null && !visitDate.isEmpty()) {
                            query += "a.visitDate = '" + visitDate + "'";
                            hasCondition = true;
                        }

                        if (followupDate != null && !followupDate.isEmpty()) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.followupDate = '" + followupDate + "'";
                            hasCondition = true;
                        }

                        if (appointmentId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.id = '" + appointmentId + "'";
                            hasCondition = true;
                        }

                        if (clinicId != 0) {
                            if (hasCondition) {
                                query += " and ";
                            }
                            query += "a.clinic.id = " + loginStatus.getId();
                            hasCondition = true;
                        }
                        if (doctorClinics != null && !doctorClinics.isEmpty()) {
                            // If a condition already exists in the query, add 'and'
                            if (hasCondition) {
                                query += " AND ";
                            }

                            // Start constructing the query to select doctor IDs
                            query += "a.doctor.id IN (";

                            // Iterate through doctorClinics set
                            Iterator<DoctorClinic> iterator = doctorClinics.iterator();
                            while (iterator.hasNext()) {
                                DoctorClinic doctorClinic = iterator.next();
                                // Append the doctor ID to the query
                                query += doctorClinic.getDoctor().getId();

                                // Add comma separator if not the last element
                                if (iterator.hasNext()) {
                                    query += ", ";
                                }
                            }

                            // Close the IN clause
                            query += ")";
                        }

                    }

                    query += " order by a.visitDate DESC";

                }
            }
                SyncObject syncObject = new SyncObject();
                appointmentModels = (List<AppointmentModel>) HibernateUtil.getDBObjects(query);

                List<AppointmentDAL> appointmentConverted = AppointmentDALConverter.convertToAppointmentDALList(appointmentModels);
                syncObject.setAppointments(appointmentConverted);
                response.setData(syncObject);
            }


        if( response.getStatus()==null || response.getStatus().isEmpty()){
            response.setStatus(Codes.NOT_FOUND);
        }

        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_GETDOCTORAPPOINTMENTS, method = RequestMethod.GET, params = {"token", "doctorId", "clinicId"})
    @ResponseBody
    public String getDoctorAppointments(String token, int doctorId, int clinicId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse = TokenUtil.validateTokenAndRespond(token);
        if(Codes.ALL_OK.equals(apiResponse.getStatus())){
            String todayDate = Codes.sdf.format(Calendar.getInstance().getTime());
            List<AppointmentModel> appointmentModels = (List<AppointmentModel>) HibernateUtil.getDBObjects("from AppointmentModel where visitDate='"+todayDate+"' and doctorId = "+doctorId+" and clinicId="+clinicId);
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<Integer> appointmentIds = new ArrayList<>();
            List<Patient> patients = new ArrayList<>();
            List<Treatment> treatments = new ArrayList<>();

            if(appointmentModels!=null && appointmentModels.size()>0){
                for(AppointmentModel appointmentModel : appointmentModels){
                   // ids.add(appointmentModel.getPatientId());
                    appointmentIds.add(appointmentModel.getId());
                }
                patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where status=1 and  id in ("+ Util.getWhereClause(ids)+")");
                treatments = (List<Treatment>) HibernateUtil.getDBObjects("from Treatment where appointmentId in ("+Util.getWhereClause(appointmentIds)+")");
            }

            SyncObject syncObject = new SyncObject();
            syncObject.setTreatments(treatments);

            apiResponse.setData(syncObject);
        }


        return new Gson().toJson(apiResponse, ApiResponse.class);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_GETPATIENTHISTORY, method = RequestMethod.GET, params = {"token", "doctorId", "patientId"})
    @ResponseBody
    public String getPatientHistory(String token, int doctorId, int patientId){
        ApiResponse response = new ApiResponse();
        response = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(response.getStatus())) {
            List<AppointmentModel> appointmentModels = new ArrayList<>();
            List<Patient> patients = (List<Patient>) HibernateUtil.getDBObjects("from Patient where id =" + patientId);
            SyncObject syncObject = new SyncObject();
            if(patients!=null && patients.size()>0) {
                Set<AppointmentModel> appointments = patients.get(0).getAppointments();
                if (doctorId != 0) {
                    appointmentModels = (List<AppointmentModel>) HibernateUtil.getDBObjects("from AppointmentModel a where status=1 and a.patient.id=" + patientId + " and a.doctor.id = " + doctorId + " order by visitDate DESC");
                } else {
                    appointmentModels = (List<AppointmentModel>) HibernateUtil.getDBObjects("from AppointmentModel a where status=1 and a.patient.id=" + patientId + " order by visitDate DESC");
                }
                List<AppointmentDAL> appointmentConverted = AppointmentDALConverter.convertToAppointmentDALList(appointmentModels);

                syncObject.setAppointments(appointmentConverted);
            }

            response.setData(syncObject);
        }

        return new Gson().toJson(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/" + Codes.API_DIRECTAPPOINTMENT, method = RequestMethod.GET, params = {"token", "appointment", "patient"})
    @ResponseBody
    public String directAppointment(String token, String appointment, String patient){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse = TokenUtil.validateTokenAndRespond(token);
    AppointmentDAL appointmentDAL = new Gson().fromJson(appointment, AppointmentDAL.class);
        if (Codes.ALL_OK.equals(apiResponse.getStatus())) {
            Patient patientObj = new Gson().fromJson(patient, Patient.class);
            patientObj.setStatus(1);
            RegisterPatient registerPatient = new RegisterPatient();
            int id = registerPatient.savePatient(patientObj);

            if (id != 0) {
                LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
                if (loginStatus.getType().equals(Codes.FROMCLINIC)) {
                    int clinicId = Integer.valueOf(loginStatus.getUsername());
                    appointmentDAL.setClinicId(clinicId);
                }
                appointmentDAL.setPatientId(id);
                apiResponse = setAppointment(apiResponse, appointmentDAL, token);
            }
        }

        return new Gson().toJson(apiResponse);
    }
}