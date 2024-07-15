package com.fynuls.controllers;

import com.fynuls.dal.Dashboard;
import com.fynuls.dal.DashboardCom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Syed Muhammad Hassan
 * 22nd July,2022
 */

@Controller
public class Test{
    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
    @ResponseBody
    public String getTime(){
        List<String> gamesList = new ArrayList<String>();
        gamesList.add("Football");
        gamesList.add("Cricket");
        gamesList.add("Chess");
        gamesList.add("Hocky");

        Dashboard dashboard = new Dashboard() ;
        dashboard.setId(1);
        dashboard.setHtml("hello");
        Dashboard dashboard2 = new Dashboard() ;
        dashboard2.setId(5);
        dashboard2.setHtml("hello5");
        Dashboard dashboard3 = new Dashboard() ;
        dashboard3.setId(33);
        dashboard3.setHtml("hello33");
        List<Dashboard> dashboards = Arrays.asList(dashboard2,dashboard3,dashboard);

        Collections.sort(dashboards);
        //Collections.sort(dashboards,new DashboardCom());
        System.out.println("------------Iterating by passing lambda expression--------------");
        dashboards.forEach(s -> System.out.println(s.getHtml()));
        List<String> names = Arrays.asList("Alex", "Brian", "Charles");

        Consumer<Dashboard> makeUpperCase = new Consumer<Dashboard>()
        {
            @Override
            public void accept(Dashboard t)
            {
                System.out.println(t.getHtml());
            }
        };
        TestInterface1.getStatic();
        dashboards.forEach(System.out::println);

        return "";
    }

}
interface TestInterface1
{
    int rr = 0;
    static int getStatic(){
        return rr;
    }
    // default method
    default void show()
    {
        System.out.println("Default TestInterface1");
    }
}

interface TestInterface2
{
    static int getStatic(){
        return 0;
    }
    // Default method
    default void show()
    {
        System.out.println("Default TestInterface2");
    }
}
