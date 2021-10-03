package com.anandhuarjunan.developertools.core.controller.tools.sysinfo;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.VirtualMemory;

public class MemoryInfoController implements Initializable {

	 @FXML
	    private LineChart<String, Number> linechart;

	    @FXML
	    private CategoryAxis categoryAxis;

	    @FXML
	    private NumberAxis numberAxis;
	    
	    private ScheduledExecutorService scheduledExecutorService;
	    
	    @FXML
	    private Label availablePhMem;

	    @FXML
	    private PieChart physicalMemChart;

	    @FXML
	    private Label usedVirMem;

	    @FXML
	    private PieChart swapMemChart;

	    @FXML
	    private TextArea otherinfo;
	    
	    private SystemInfo si = new SystemInfo();
	    private GlobalMemory globalMemory = si.getHardware().getMemory();
	    private VirtualMemory virtualMemory = globalMemory.getVirtualMemory();
	    private DecimalFormat dec = new DecimalFormat("#0.00");

	    

	    private static final String USED = "Used";
	    private static final String AVAILABLE = "Available";
	    
	    
	    
	    
	    
	  
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			PieChart.Data usedPhData =	new PieChart.Data(USED, 0);
			PieChart.Data availablePh =	new PieChart.Data(AVAILABLE, 0);

			PieChart.Data usedDataSw =	new PieChart.Data(USED, 0);
			PieChart.Data availableSw =	new PieChart.Data(AVAILABLE, 0);
			
			ObservableList<PieChart.Data> pieChartDataPh = FXCollections.observableArrayList(usedPhData,availablePh);
			ObservableList<PieChart.Data> pieChartDataSw = FXCollections.observableArrayList(usedDataSw,availableSw);
			physicalMemChart.setData(pieChartDataPh);
			swapMemChart.setData(pieChartDataSw);
			  scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
	          // put dummy data onto graph per second
	          scheduledExecutorService.scheduleAtFixedRate(() -> {
	             
	              // Update the chart
	              Platform.runLater(() -> {
	            	  updatePhChart(usedPhData,availablePh);
	            	  updateSwapChart(usedDataSw,availableSw);
	            	  updateMemoryText();
	            	  availablePhMem.setText(updatePhysTitle());
	            	  usedVirMem.setText(updateVirtTitle());
	              });
	          }, 0, 1, TimeUnit.SECONDS);
		}
		
		
		
		void updatePhChart(PieChart.Data usedData,PieChart.Data available){
	        double used = (((double) globalMemory.getTotal() - globalMemory.getAvailable())/ globalMemory.getTotal())*100;
	        double availableV = (((double) globalMemory.getAvailable())/ globalMemory.getTotal())*100;
			usedData.setPieValue(used);
			usedData.setName(USED+" : "+dec.format(used)+" %");
			available.setPieValue(availableV);
			available.setName(AVAILABLE+" : "+dec.format(availableV)+" %");

		}
		
		void updateSwapChart(PieChart.Data usedData,PieChart.Data available){
	        double used = ((double) virtualMemory.getSwapUsed()/ virtualMemory.getSwapTotal())*100;
	        double availableV = (((double)virtualMemory.getSwapTotal()-  virtualMemory.getSwapUsed())/ globalMemory.getTotal())*100;

			usedData.setPieValue(used);
			available.setPieValue(availableV);
			usedData.setName(USED+" : "+dec.format(used)+" %");
			available.setName(AVAILABLE+" : "+dec.format(availableV)+" %");
		}
		
		private  String updatePhysTitle() {
	        return globalMemory.toString();
	    }

	    private  String updateVirtTitle() {
	        return globalMemory.getVirtualMemory().toString();
	    }
		
		 private  void  updateMemoryText() {
		        StringBuilder sb = new StringBuilder();
		        List<PhysicalMemory> pmList = globalMemory.getPhysicalMemory();
		        for (PhysicalMemory pm : pmList) {
		            sb.append('\n').append(pm.toString());
		        }
		        otherinfo.setText(sb.toString());
		    }
		
		
	
}
