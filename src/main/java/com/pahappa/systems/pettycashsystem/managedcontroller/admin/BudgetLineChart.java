package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.primefaces.model.*;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
public class BudgetLineChart implements Serializable {

    @Autowired
    private AllBudgetLines budgetLines;

    public PieChartModel getPieModel() {
        ChartData cd = new ChartData();
        PieChartDataSet ds = new PieChartDataSet();
        PieChartModel pcm = new PieChartModel();

        List<Number> values = List.of(budgetLines.getPendingBudgetLines().size(),budgetLines.getApprovedBudgetLines().size(),budgetLines.getPaidBudgetLines().size());
        ds.setData(values);

        List<String> colors = List.of("#faa","#aaf","#c7e");
        ds.setBackgroundColor(colors);

        cd.addChartDataSet(ds);
        List<String> labels = List.of("Pending BudgetLines","Approved BudgetLines","Paid BudgetLines");
        cd.setLabels(labels);
        pcm.setData(cd);

        PieChartOptions options = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("BudgetLine Status");
        options.setTitle(title);
        pcm.setOptions(options);

        return pcm;
    }

}