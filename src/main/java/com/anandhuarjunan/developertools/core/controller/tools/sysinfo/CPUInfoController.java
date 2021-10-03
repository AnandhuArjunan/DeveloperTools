package com.anandhuarjunan.developertools.core.controller.tools.sysinfo;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;


public class CPUInfoController  implements Initializable  {
	  @FXML
	    private LineChart<String, Number> sysCpuChart;

	    @FXML
	    private LineChart<String, Number> processorCpuChart;
	    
	    @FXML
	    private CategoryAxis cpuX;

	    @FXML
	    private NumberAxis cpuY;

	    @FXML
	    private CategoryAxis processorX;

	    @FXML
	    private NumberAxis processorY;
	    
	    ScheduledExecutorService scheduledExecutorService;
	    private SystemInfo si = new SystemInfo();

	    private CentralProcessor cpu = si.getHardware().getProcessor();
	    private long[] oldTicks;
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			 oldTicks = new long[TickType.values().length];
			 final int WINDOW_SIZE = 20;

				
			 cpuX.setLabel("Time/s");
			 cpuY.setAnimated(false); // axis animations are removed
	          cpuY.setLabel("% CPU");
	          cpuX.setAnimated(false); // axis animations are removed

	          //defining a series to display data
	          XYChart.Series<String, Number> series = new XYChart.Series<>();
	          series.setName("CPU Usage");

	          // add series to chart
	          sysCpuChart.getData().add(series);

	     

	          // this is used to display time in HH:mm:ss format
	          final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

	          // setup a scheduled executor to periodically put data into the chart
	          scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	          // put dummy data onto graph per second
	          scheduledExecutorService.scheduleAtFixedRate(() -> {
	             
	              // Update the chart
	              Platform.runLater(() -> {
	                  // get current time
	                  Date now = new Date();
	                  // put random number with current time
	                  
	                  
	                  series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now),cpuData()));

	                  if (series.getData().size() > WINDOW_SIZE)
	                      series.getData().remove(0);
	              });
	          }, 0, 1, TimeUnit.SECONDS);
						
		}
		
		
		
	 
		private double cpuData() {
	        double d = cpu.getSystemCpuLoadBetweenTicks(oldTicks);
	        oldTicks = cpu.getSystemCpuLoadTicks();
	        return d;
	    }
		
		
		
		
		
		
		
		
		
}
