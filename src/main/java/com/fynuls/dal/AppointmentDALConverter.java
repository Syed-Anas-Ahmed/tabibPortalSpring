package com.fynuls.dal;

import com.fynuls.entity.AppointmentModel;
import com.fynuls.entity.Treatment;
import com.fynuls.utils.HibernateUtil;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDALConverter {
    public static AppointmentDAL convertToAppointmentDAL(AppointmentModel appointment) {
        AppointmentDAL appointmentDAL = new AppointmentDAL();
        appointmentDAL.setId(appointment.getId());
        appointmentDAL.setPatientName(appointment.getPatient().getName());
        appointmentDAL.setClinicName(appointment.getClinic().getName());
        appointmentDAL.setDoctorName(appointment.getDoctor().getName());
        appointmentDAL.setVisitDate(appointment.getVisitDate());
        appointmentDAL.setTokenNumber(appointment.getToken());
        appointmentDAL.setStatus(appointment.getStatus());
        appointmentDAL.setCharges(appointment.getCharges());
        appointmentDAL.setPrescription(appointment.getPrescription());
        appointmentDAL.setDiagnosis(appointment.getDiagnosis());
        appointmentDAL.setWeight(appointment.getWeight());
        appointmentDAL.setBloodPressure(appointment.getBloodPressure());
        appointmentDAL.setFollowupDate(appointment.getFollowupDate());
        appointmentDAL.setPatientId(appointment.getPatient().getId());
        appointmentDAL.setClinicId(appointment.getClinic().getId());
        appointmentDAL.setDoctorId(appointment.getDoctor().getId());
        appointmentDAL.setAge(appointment.getAge());
        List<Treatment> treatments = (List<Treatment>) HibernateUtil.getDBObjects("from Treatment t where t.appointmentModel.id="+appointment.getId());
        if (treatments!=null && !treatments.isEmpty() && Hibernate.isInitialized(treatments)) {
            appointmentDAL.setTreatments(convertToTreatmentDAL(treatments));
        }

        return appointmentDAL;
    }

    private static List<TreatmentDAL> convertToTreatmentDAL(List<Treatment> treatments) {
        List<TreatmentDAL> treatmentDALS = new ArrayList<>();
        TreatmentDAL treatmentDAL = new TreatmentDAL();
        if (treatments!=null && !treatments.isEmpty()){
            for(Treatment treatment : treatments){
                treatmentDAL = new TreatmentDAL();
                treatmentDAL.setName(treatment.getName());
                treatmentDAL.setDetail(treatment.getDetail());
                treatmentDAL.setId(treatment.getId());
                treatmentDALS.add(treatmentDAL);
            }

        }
        return treatmentDALS;

    }

    public static List<AppointmentDAL> convertToAppointmentDALList(List<AppointmentModel> appointments) {
        List<AppointmentDAL> appointmentDALS = new ArrayList<>();
        if (appointments != null) {
            for (AppointmentModel appointment : appointments) {
                appointmentDALS.add(convertToAppointmentDAL(appointment));
            }
        }
        return appointmentDALS;
    }
}
