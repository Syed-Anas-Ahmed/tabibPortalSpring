package com.fynuls.utils;

import com.fynuls.controllers.Codes;
import com.fynuls.controllers.FCMNotification;
import com.fynuls.entity.AppointmentModel;

import java.util.ArrayList;

public class PushNotificationThread implements Runnable {

    private AppointmentModel appointmentModel;
    private String userType;

    public PushNotificationThread(AppointmentModel appointmentModel, String userType) {
        this.appointmentModel = appointmentModel;
        this.userType = userType;
    }

    public void run() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(userType.equals(Codes.FROMPATIENT)) {
                    String query = "SELECT p.name   patientName, p.cell_number, A.token, D.name  DoctorName, c.name  ClinicName, ls.fcmtoken, A.charges FROM Appointment A\n" +
                            "                INNER JOIN Patient p on p.id =A.patient_Id\n" +
                            "                INNER JOIN Doctor d on d.id = A.doctor_Id\n" +
                            "                INNER JOIN Clinic c on c.id = A.clinic_id\n" +
                            "                INNER JOIN LOGINSTATUS LS ON LS.username = D.username "+
                            "                where ls.fcmtoken is not null and A.id=" + appointmentModel.getId();

                    ArrayList<Object> objs = HibernateUtil.getDBObjectsFromSQLQuery(query);
                    String message = "";
                    String fcmToken = "";
                    if (objs != null && objs.size() > 0) {
                        for(Object obj : objs) {
                            Object[] o = (Object[]) obj;
                            message = "Patient " + o[0].toString() + " - " + o[1].toString() + " has token number " + o[2].toString() + " for DR. " + o[3].toString() + " in clinic " + o[4].toString();
                            fcmToken = o[5].toString();
                            try {
                                new FCMNotification().sendNotification(fcmToken, "Patient has set an Appointment", message, Codes.DOCTORFILE, Codes.DoctorAppName);
                            } catch (Exception e) {
                                int i =9;
                                i=8;
                            }
                        }
                    }
                }else if(userType.equals(Codes.FROMDOCTOR) && appointmentModel.getStatus()==1){
                    String query = "SELECT p.name   patientName, p.cell_number, A.token, D.name  DoctorName, c.name  ClinicName, ls.fcmtoken, A.charges FROM Appointment A\n" +
                            "                INNER JOIN Patient p on p.id =A.patient_Id\n" +
                            "                INNER JOIN Doctor d on d.id = A.doctor_Id\n" +
                            "                INNER JOIN Clinic c on c.id = A.clinic_id\n" +
                            "                INNER JOIN LOGINSTATUS LS ON LS.username = P.cell_number"+
                            "                where A.id=" + appointmentModel.getId();

                    ArrayList<Object> objs = HibernateUtil.getDBObjectsFromSQLQuery(query);
                    String message = "";
                    String fcmToken = "";
                    if (objs != null && objs.size() > 0) {
                        for(Object obj : objs) {
                            Object[] o = (Object[]) obj;
                            message = "Mr. " + o[0].toString() + " - " + o[1].toString() + " your checkup has been completed. You have paid RS" + o[6].toString() + ". (And when I fall ill ,He (Allah) is the one who cures me) Al Quran";
                            fcmToken = o[5].toString();
                            try {
                                new FCMNotification().sendNotification(fcmToken, "Appointment Completed", message, Codes.PATIENTFILE, Codes.PatientAppName);
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }
        });
        t1.start();
    }
}