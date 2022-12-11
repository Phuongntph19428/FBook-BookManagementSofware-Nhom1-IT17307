/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author quanc
 */
public class DoTaskSend implements Job {
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        SystemServiceImpl s = new SystemServiceImpl();
        s.sendMethod(s.getListAll());
    }

}
