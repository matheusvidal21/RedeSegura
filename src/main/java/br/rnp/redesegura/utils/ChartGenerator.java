package br.rnp.redesegura.utils;

import br.rnp.redesegura.models.enums.Severity;
import br.rnp.redesegura.services.DashboardService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ChartGenerator {

    @Autowired
    private DashboardService dashboardService;

    public void generateVulnerabilityStatusByInstitution(String filePath, Long institutionId) throws IOException {
        int resolvedCount = dashboardService.countResolvedVulnerabilitiesByInstitution(institutionId);
        int notResolvedCount = dashboardService.countNotResolvedVulnerabilitiesByInstitution(institutionId);
        int openCount = dashboardService.countOpenVulnerabilitiesByInstitution(institutionId);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(resolvedCount, "Vulnerabilidades", "Resolvido");
        dataset.addValue(notResolvedCount, "Vulnerabilidades", "Não resolvido");
        dataset.addValue(openCount, "Vulnerabilidades", "Aberto");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Vulnerabilidades da Instituição",
                "Status",
                "Quantidade",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartUtils.saveChartAsPNG(new File(filePath), barChart, 800, 600);
    }

    public void generateVulnerabilitiesBySeverityChart(String filePath, Long institutionId) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Severity[] severities = Severity.values();
        for (Severity severity : severities) {
            int openCount = dashboardService.countOpenVulnerabilitiesBySeverity(severity, institutionId);
            int resolvedCount = dashboardService.countResolvedVulnerabilitiesBySeverity(severity, institutionId);
            int notResolvedCount = dashboardService.countNotResolvedVulnerabilitiesBySeverity(severity, institutionId);

            dataset.addValue(openCount, "Aberto", severity);
            dataset.addValue(resolvedCount, "Resolvido", severity);
            dataset.addValue(notResolvedCount, "Não resolvido", severity);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Vulnerabilidades por Severidade",
                "Severidade",
                "Quantidade",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartUtils.saveChartAsPNG(new File(filePath), barChart, 800, 600);
    }

    public void generateServerHealthRadarChart(String filePath, Long institutionId) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int operationalCount = dashboardService.countOperationalServers(institutionId);
        int partiallyOperationalCount = dashboardService.countPartiallyOperationalServers(institutionId);
        int degradedCount = dashboardService.countDegradedServers(institutionId);

        dataset.addValue(operationalCount, "Operacional", "Servidores");
        dataset.addValue(partiallyOperationalCount, "Parcialmente Operacional", "Servidores");
        dataset.addValue(degradedCount, "Degradado", "Servidores");

        SpiderWebPlot plot = new SpiderWebPlot(dataset);
        JFreeChart radarChart = new JFreeChart("Comparação de Saúde dos Servidores", JFreeChart.DEFAULT_TITLE_FONT, plot, false);

        ChartUtils.saveChartAsPNG(new File(filePath), radarChart, 800, 600);
    }

}
