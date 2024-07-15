package com.fynuls.utils;

import com.fynuls.controllers.Codes;
import com.fynuls.entity.Doctor;
import org.threeten.bp.format.DateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static String getWhereClause(ArrayList<Integer> ids) {
        String where = "";

        if(ids!=null){
            for(int id : ids){
                where += id+",";
            }
        }
        if(!where.equals("")){
            where = where.substring(0, where.length() - 1);
        }

        return where;
    }
    public static Doctor getDoctorByUsername(String username) {
        Doctor doctor = new Doctor();
        if (!"".equals(username)) {
            List<Doctor> doctors = (List<Doctor>) HibernateUtil.getDBObjects("from Doctor where username = '"+username+"'");
            if (doctors!=null && !doctors.isEmpty()) {
                doctor = doctors.get(0);
            }
        }
        return doctor;
    }

    public static String formatDate(String inputDate) {
        // Check if inputDate is null or empty
        if (inputDate == null || inputDate.isEmpty()) {
            return "Invalid Date";
        }

        // Define input and output date formats
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            // Parse input date string
            LocalDate date = LocalDate.parse(inputDate, inputFormatter);

            // Format the date in the desired output format
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            // Handle incorrect date format
            return "Invalid Date Format";
        }
    }
}
