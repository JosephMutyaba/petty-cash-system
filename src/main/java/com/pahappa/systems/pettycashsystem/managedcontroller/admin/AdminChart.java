package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
public class AdminChart implements Serializable {

    @Autowired
    private AllRequisitions allRequisitions;

    public PieChartModel getPieChart() {
        ChartData cd = new ChartData();
        PieChartDataSet ds = new PieChartDataSet();
        PieChartModel pcm = new PieChartModel();

        List<Number> values = List.of(allRequisitions.getPendingRequisitions().size(),allRequisitions.getApprovedRequisitions().size(),allRequisitions.getPaidRequisitions().size(), allRequisitions.getRejectedRequisitions().size());
        ds.setData(values);

        List<String> colors = List.of("#faa","#aaf","#c7e", "#00FFFF");
        ds.setBackgroundColor(colors);

        cd.addChartDataSet(ds);
        List<String> labels = List.of("Pending Requisitions","Approved Requisitions","Paid Out Requisitions", "Rejected Requisitions");
        cd.setLabels(labels);
        pcm.setData(cd);

        PieChartOptions options = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Requisitions Status");
        options.setTitle(title);
        pcm.setOptions(options);

        return pcm;
    }

}

