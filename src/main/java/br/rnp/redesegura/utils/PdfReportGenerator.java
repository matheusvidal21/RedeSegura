    package br.rnp.redesegura.utils;

    import br.rnp.redesegura.dto.response.VulnerabilityTestResponse;
    import br.rnp.redesegura.exception.NotFoundException;
    import br.rnp.redesegura.models.Vulnerability;
    import br.rnp.redesegura.models.enums.TestStatus;
    import br.rnp.redesegura.repositories.VulnerabilityRepository;
    import com.itextpdf.text.*;
    import com.itextpdf.text.pdf.BaseFont;
    import com.itextpdf.text.pdf.PdfWriter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.time.LocalDateTime;
    import java.util.Locale;

    @Component
    public class PdfReportGenerator {

        private PdfReportGenerator() {
        }

        @Autowired
        private ChartGenerator chartGenerator;

        @Autowired
        private VulnerabilityRepository vulnerabilityRepository;

        @Value("${report.directory}")
        private String reportDirectory;

        @Value("${image_logo.path}")
        private String logoPath;

        public String generatePdfReport(VulnerabilityTestResponse vulnerabilityTestResponse) throws DocumentException, IOException {
            Document document = new Document();
            String fileName = "report_" + vulnerabilityTestResponse.getTypeTest().toUpperCase(Locale.ROOT).replaceAll("\\s", "") + "_" + vulnerabilityTestResponse.getTestedAt().replaceAll("[:\\\\/*?|<>]", "") + ".pdf";
            String filePath = reportDirectory  + "report_" + vulnerabilityTestResponse.getTypeTest().toUpperCase(Locale.ROOT).replaceAll("\\s", "") + "_" + vulnerabilityTestResponse.getTestedAt().replaceAll("[:\\\\/*?|<>]", "") + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED, 16);
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED, 12);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED, 12);

            try {
                Image img = Image.getInstance(logoPath);
                img.setAlignment(Element.ALIGN_CENTER);
                img.scaleToFit(150, 150);
                img.setSpacingAfter(10);
                document.add(img);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao adicionar a imagem ao documento: " + e.getMessage());
            }

            // Título do relatório
            Paragraph title = new Paragraph("Relatório de Teste de Vulnerabilidade", titleFont);
            title.setSpacingAfter(20);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Informações gerais do teste
            Paragraph ipParagraph = new Paragraph();
            ipParagraph.add(new Chunk("IP: ", boldFont));
            ipParagraph.add(new Chunk(vulnerabilityTestResponse.getIp(), contentFont));
            ipParagraph.setLeading(20f);
            ipParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(ipParagraph);

            Paragraph portParagraph = new Paragraph();
            portParagraph.add(new Chunk("Porta: ", boldFont));
            portParagraph.add(new Chunk(String.valueOf(vulnerabilityTestResponse.getPort()), contentFont));
            portParagraph.setLeading(20f);
            portParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(portParagraph);

            Paragraph typeTestParagraph = new Paragraph();
            typeTestParagraph.add(new Chunk("Tipo de Teste: ", boldFont));
            typeTestParagraph.add(new Chunk(vulnerabilityTestResponse.getTypeTest(), contentFont));
            typeTestParagraph.setLeading(20f);
            typeTestParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(typeTestParagraph);

            Paragraph dateParagraph = new Paragraph();
            dateParagraph.add(new Chunk("Data do Teste: ", boldFont));
            dateParagraph.add(new Chunk(DateUtils.formatDateTimeToBrazilianFormat(LocalDateTime.parse(vulnerabilityTestResponse.getTestedAt())), contentFont));
            dateParagraph.setLeading(20f);
            dateParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(dateParagraph);

            Font statusFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
            if (vulnerabilityTestResponse.getTestStatus().equals(TestStatus.NOT_VULNERABLE))
                statusFont.setColor(BaseColor.GREEN);
            else
                statusFont.setColor(BaseColor.RED);

            Paragraph statusParagraph = new Paragraph();
            statusParagraph.add(new Chunk("Status do Teste: ", boldFont));
            statusParagraph.add(new Chunk(vulnerabilityTestResponse.getTestStatus().name(), statusFont));
            statusParagraph.setLeading(20f);
            statusParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(statusParagraph);

            Paragraph resultParagraph = new Paragraph();
            resultParagraph.add(new Chunk("Resultado do Teste: ", boldFont));
            resultParagraph.add(new Chunk(vulnerabilityTestResponse.getTestResultMessage(), contentFont));
            resultParagraph.setLeading(20f);
            resultParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(resultParagraph);

            addTextToDocument(document, vulnerabilityTestResponse, contentFont, boldFont);

            Paragraph charts = new Paragraph();
            charts.setSpacingBefore(20);
            charts.add(new Chunk("Gráficos: ", boldFont));


            if (vulnerabilityTestResponse.getVulnerabilityId() != null) {
                var vulnerability = vulnerabilityRepository.findById(vulnerabilityTestResponse.getVulnerabilityId()).orElseThrow(() -> new NotFoundException("Vulnerability not found"));
                Long institutionId = vulnerability.getServer().getInstitution().getId();

                String chartFilePath = reportDirectory + "vulnerability_by_status_chart.png";
                chartGenerator.generateVulnerabilityStatusByInstitution(chartFilePath, institutionId);
                Image chartImage = Image.getInstance(chartFilePath);
                chartImage.setAlignment(Element.ALIGN_CENTER);
                chartImage.scaleToFit(500, 300);
                chartImage.setSpacingBefore(20);
                chartImage.setSpacingAfter(20);
                document.add(chartImage);


                String barChartPath = reportDirectory + "vulnerabilities_by_severity_chart.png";
                chartGenerator.generateVulnerabilitiesBySeverityChart(barChartPath, institutionId);
                Image barChartImage = Image.getInstance(barChartPath);
                barChartImage.setAlignment(Element.ALIGN_CENTER);
                barChartImage.scaleToFit(500, 300);
                barChartImage.setSpacingBefore(20);
                barChartImage.setSpacingAfter(20);
                document.add(barChartImage);

                String radarChartPath = reportDirectory + "server_health_radar_chart.png";
                chartGenerator.generateServerHealthPieChart(radarChartPath, institutionId);
                Image radarChartImage = Image.getInstance(radarChartPath);
                radarChartImage.setAlignment(Element.ALIGN_CENTER);
                radarChartImage.scaleToFit(500, 300);
                radarChartImage.setSpacingBefore(20);
                document.add(radarChartImage);
            }

            document.close();
            return fileName;
        }

        private void addTextToDocument(Document document, VulnerabilityTestResponse vulnerabilityTestResponse, Font font, Font boldFont) throws DocumentException {
            String reason = "";
            String importance = "";
            String solution = "";

            switch (Vulnerability.VulnerabilityTypeEnum.fromName(vulnerabilityTestResponse.getTypeTest())) {
                case DNS_RECURSION:
                    reason = MessageList.getMessage(MessageList.Message.DNS_RECURSION_REASON);
                    importance = MessageList.getMessage(MessageList.Message.DNS_RECURSION_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.DNS_RECURSION_SOLUTION);
                    break;
                case NTP_DDOS_AMPLIFICATION:
                    reason = MessageList.getMessage(MessageList.Message.NTP_DDOS_AMPLIFICATION_REASON);
                    importance = MessageList.getMessage(MessageList.Message.NTP_DDOS_AMPLIFICATION_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.NTP_DDOS_AMPLIFICATION_SOLUTION);
                    break;
                case NETBIOS_EXPOSURE:
                    reason = MessageList.getMessage(MessageList.Message.NETBIOS_EXPOSURE_REASON);
                    importance = MessageList.getMessage(MessageList.Message.NETBIOS_EXPOSURE_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.NETBIOS_EXPOSURE_SOLUTION);
                    break;
                case SNMP_PUBLIC_COMMUNITY:
                    reason = MessageList.getMessage(MessageList.Message.SNMP_PUBLIC_COMMUNITY_REASON);
                    importance = MessageList.getMessage(MessageList.Message.SNMP_PUBLIC_COMMUNITY_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.SNMP_PUBLIC_COMMUNITY_SOLUTION);
                    break;
                case SAMBA_OUTDATED_VERSION:
                    reason = MessageList.getMessage(MessageList.Message.SAMBA_OUTDATED_VERSION_REASON);
                    importance = MessageList.getMessage(MessageList.Message.SAMBA_OUTDATED_VERSION_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.SAMBA_OUTDATED_VERSION_SOLUTION);
                    break;
                case EXPOSED_MYSQL:
                    reason = MessageList.getMessage(MessageList.Message.EXPOSED_MYSQL_REASON);
                    importance = MessageList.getMessage(MessageList.Message.EXPOSED_MYSQL_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.EXPOSED_MYSQL_SOLUTION);
                    break;
                case REDIS_NO_AUTHENTICATION:
                    reason = MessageList.getMessage(MessageList.Message.REDIS_NO_AUTHENTICATION_REASON);
                    importance = MessageList.getMessage(MessageList.Message.REDIS_NO_AUTHENTICATION_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.REDIS_NO_AUTHENTICATION_SOLUTION);
                    break;
                case EXPOSED_SSDP:
                    reason = MessageList.getMessage(MessageList.Message.EXPOSED_SSDP_REASON);
                    importance = MessageList.getMessage(MessageList.Message.EXPOSED_SSDP_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.EXPOSED_SSDP_SOLUTION);
                    break;
                case EXPOSED_MEMCACHED:
                    reason = MessageList.getMessage(MessageList.Message.EXPOSED_MEMCACHED_REASON);
                    importance = MessageList.getMessage(MessageList.Message.EXPOSED_MEMCACHED_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.EXPOSED_MEMCACHED_SOLUTION);
                    break;
                case EXPOSED_SLP:
                    reason = MessageList.getMessage(MessageList.Message.EXPOSED_SLP_REASON);
                    importance = MessageList.getMessage(MessageList.Message.EXPOSED_SLP_IMPORTANCE);
                    solution = MessageList.getMessage(MessageList.Message.EXPOSED_SLP_SOLUTION);
                    break;
            }

            Paragraph aboutParagraph = new Paragraph();
            aboutParagraph.add(new Chunk("Sobre a vulnerabilidade", boldFont));
            aboutParagraph.setAlignment(Element.ALIGN_CENTER);
            aboutParagraph.setSpacingBefore(20);
            aboutParagraph.setSpacingAfter(20);
            aboutParagraph.setLeading(20f);
            document.add(aboutParagraph);

            Paragraph reasonParagraph = new Paragraph();
            reasonParagraph.add(new Chunk("Motivo: ", boldFont));
            reasonParagraph.add(new Chunk(reason, font));
            reasonParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            reasonParagraph.setLeading(20f);
            document.add(reasonParagraph);

            Paragraph importanceParagraph = new Paragraph();
            importanceParagraph.add(new Chunk("Importância: ", boldFont));
            importanceParagraph.add(new Chunk(importance, font));
            importanceParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            importanceParagraph.setLeading(20f);
            document.add(importanceParagraph);

            Paragraph solutionParagraph = new Paragraph();
            solutionParagraph.add(new Chunk("Possível Solução: ", boldFont));
            solutionParagraph.add(new Chunk(solution, font));
            solutionParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            solutionParagraph.setLeading(20f);
            document.add(solutionParagraph);
        }

    }
