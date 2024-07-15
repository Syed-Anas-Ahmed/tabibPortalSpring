package com.fynuls.controllers;

import com.fynuls.dal.BarChart;
import com.fynuls.dal.LineGraph;
import com.fynuls.dal.SyncObject;
import com.fynuls.entity.AppointmentModel;
import com.fynuls.entity.Clinic;
import com.fynuls.entity.DoctorClinic;
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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Syed Muhammad Hassan
 * 30th April,2024
 */

@Controller
public class graphs {
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getLineGraphOfClinic", method = RequestMethod.GET, params = {"token", "startDate", "endDate"})
    @ResponseBody
    public String getLineGraphOfClinic(String token, String startDate, String endDate){
        SyncObject syncObject = new SyncObject();

        ApiResponse apiResponse = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(apiResponse.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if (loginStatus != null) {
                if (Codes.FROMCLINIC.equals(loginStatus.getType())) {
                    List<Clinic> clinics = (List<Clinic>) HibernateUtil.getDBObjects("from Clinic where id = " + loginStatus.getUsername());
                    Set<DoctorClinic> doctorClinics = clinics.get(0).getDoctorClinics();
                    String query = "Select a.visitDate, a.doctor.name, COUNT(*) from AppointmentModel a where visitDate >= '"+ startDate + "' and visitDate <= '"+endDate+"' and ";
                    if (doctorClinics != null && !doctorClinics.isEmpty()) {
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
                    query += " group by visit_date, a.doctor";
                    List<Object> appointmentSummary = (List<Object>) HibernateUtil.getDBObjects(query);
                    List<LineGraph> lineGraphs = new ArrayList<>();
                    LineGraph lineGraph = new LineGraph();
                    if (appointmentSummary != null && !appointmentSummary.isEmpty()) {
                        for (Object appintmentSum : appointmentSummary) {
                            if (appintmentSum != null && appintmentSum instanceof Object[]) {
                                Object[] summaryRecord = (Object[]) appintmentSum;
                                if (summaryRecord != null && summaryRecord.length == 3) {
                                    lineGraph = new LineGraph();
                                    lineGraph.setVisitDate(summaryRecord[0].toString());
                                    lineGraph.setDoctorName(summaryRecord[1].toString());
                                    if (summaryRecord[2] != null) {
                                        lineGraph.setCount(Integer.valueOf(summaryRecord[2].toString()));
                                    }

                                    lineGraphs.add(lineGraph);
                                }
                            }
                        }
                        syncObject.setLineGraphs(lineGraphs);
                    }
                }

            }
        }

        apiResponse.setData(syncObject);

        return new Gson().toJson(apiResponse);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getBarChartOfClinic", method = RequestMethod.GET, params = {"token", "numberOfMonths"})
    @ResponseBody
    public String getBarChartOfClinic(String token, int numberOfMonths){
        SyncObject syncObject = new SyncObject();

        ApiResponse apiResponse = TokenUtil.validateTokenAndRespond(token);
        if (Codes.ALL_OK.equals(apiResponse.getStatus())) {
            LoginStatus loginStatus = TokenUtil.getLoginStatusFromToken(token);
            if (loginStatus != null) {
                if (Codes.FROMCLINIC.equals(loginStatus.getType())) {

                    // Get the current date
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, -numberOfMonths);
                    Date fourMonthsAgo = calendar.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(fourMonthsAgo);

                    String query = "SELECT DATE_FORMAT(a.visitDate, '%b,%y') AS month_year,\n" +
                            "       d.name,\n" +
                            "       COUNT(*) AS total_entries\n" +
                            "FROM AppointmentModel a\n" +
                            "JOIN a.doctor d\n" +
                            "WHERE a.visitDate >= '" + formattedDate + "'\n" +
                            "GROUP BY DATE_FORMAT(a.visitDate, '%b,%y'), d.name\n" +
                            "ORDER BY DATE_FORMAT(a.visitDate, '%b,%y'), d.name";

                    List<Object> appointmentSummary = (List<Object>) HibernateUtil.getDBObjects(query);
                    List<BarChart> barCharts = new ArrayList<>();
                    BarChart barChart = new BarChart();

                    if (appointmentSummary != null && !appointmentSummary.isEmpty()) {
                        for (Object appintmentSum : appointmentSummary) {
                            if (appintmentSum != null && appintmentSum instanceof Object[]) {
                                Object[] summaryRecord = (Object[]) appintmentSum;
                                if (summaryRecord != null && summaryRecord.length == 3) {
                                    barChart = new BarChart();
                                    barChart.setMonthYear(summaryRecord[0].toString());
                                    barChart.setDoctorName(summaryRecord[1].toString());
                                    if (summaryRecord[2] != null) {
                                        barChart.setCount(Integer.valueOf(summaryRecord[2].toString()));
                                    }

                                    barCharts.add(barChart);
                                }
                            }
                        }
                        syncObject.setBarCharts(barCharts);
                    }
                }

            }
        }

        apiResponse.setData(syncObject);

        return new Gson().toJson(apiResponse);
    }
}
