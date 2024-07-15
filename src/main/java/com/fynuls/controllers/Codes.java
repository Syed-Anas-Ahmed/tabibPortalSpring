package com.fynuls.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface Codes {
    public static final String ALL_OK = "200";
    public static final String NOT_FOUND = "404";
    public static final String SOMETHING_WENT_WRONG = "502";
    public static final String ALREADY_LOGGED_IN = "504";
    public static final String ALREADY_REGISTERED = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORCED_LOGOUT = "444";
    public static final String INVALID_TOKEN = "300";
    public static final String INVALID_VERSION = "303";
    public static final String DUPLICATE_ENTRY = "212";
    public static final String INCORRECTCREDENTIALS = "503";
    /*
    Login settings
     */
    public static final String LoggedIn = "1";
    public static final String LoggedOut = "2";
    public static final String LicenseExpired = "3";


    public static final String FROMPATIENT = "2";
    public static final String FROMDOCTOR = "3";
    public static final String FROMCLINIC = "4";

    final public static String myFormat = "dd-MMM-yy";;

    public static final String API_GETPATIENTS = "getPatients";
    public static final String API_REGISTERPATIENT = "registerPatient";
    public static final String API_ADDPATIENT = "addPatient";
    public static final String API_SETAPPOINTMENT = "setAppointmentDetail";
    public static final String API_GETDOCTORDETAILS = "getDoctorDetails";
    public static final String API_GETDOCTORAPPOINTMENTS = "getDoctorAppointments";
    public static final String API_ALLBASICDATA = "getAllBasicData";

    public static final String API_GETALLDOCTORDATA = "getAllDoctorData";
    public static final String API_LOGIN = "login";
    public static final String API_LOGINPATIENT = "loginPatient";
    public static final String API_GETPATIENTHISTORY = "getPatientHistory";
    public static final String API_ALLDOCTORBASICDATA = "getAllDoctorBasicData";
    public static final String API_DIRECTAPPOINTMENT = "directAppointment";
    public static final String API_GETAPPOINTMENTSTATUS = "getAppointmentStatus";
    public static final String API_UPDATEFCMTOKEN = "updateFCMToken";
    public static final String API_SENDFEEDBACK = "sendFeedback";
    public static final String API_GETSUMMARY = "getSummary";
    public static final String API_CHANGEPASSWORD = "changePassword";

    public static final SimpleDateFormat sdf = new SimpleDateFormat(Codes.myFormat);

    /*
    Firebase settings
     */
    public static final String DOCTORFILE = "dr-inn-for-doctors-firebase-adminsdk.json";
    public static final String PATIENTFILE = "dr-inn-firebase-adminsdk.json";

    public static final String DoctorAppName = "Dr Inn for Doctors";
    public static final String PatientAppName = "Dr Inn";
}
