package com.nsia.officems._util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HTMLToPDFCreator {
    @Value("${print.templates.uploads}")
    private String printTemplateUploads;

    @Value("${wkhtmltopdf}")
    private String wkhtmltopdf;

    // temp directory of Operation System
    private String _tempDirectory = System.getProperty("java.io.tmpdir");

    public File generatePdf(String templateString, String fileName) throws IOException, InterruptedException {
        WrapperConfig wrapperConfig = new WrapperConfig(wkhtmltopdf);

        Pdf pdf = new Pdf(wrapperConfig);
        pdf.addPageFromString(templateString);

        // add parameters
        pdf.addParam(new Param("--allow", this.printTemplateUploads), new Param("--margin-top", "20"),
                new Param("--margin-bottom", "20"), new Param("--margin-left", "20"), new Param("--margin-right", "20"),
                new Param("--enable-local-file-access"), new Param("--viewport-size", "1920×1080"));

        // Save the PDF
        return this.savePdf(pdf, fileName);
    };

    public File savePdf(Pdf pdf, String fileName) throws IOException, InterruptedException {
        Calendar calendar = Calendar.getInstance();
        String FileName = fileName + "_" + calendar.getInstance().getTimeInMillis() + ".pdf";

        File savedPdf = pdf.saveAsDirect(_tempDirectory + "/" + FileName);

        return savedPdf;
    }
}
