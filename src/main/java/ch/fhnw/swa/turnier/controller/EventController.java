package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.EventBean;
import ch.fhnw.swa.turnier.domain.Event;
import ch.fhnw.swa.turnier.domain.EventType;
import ch.fhnw.swa.turnier.utils.JsfUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Named
@SessionScoped
public class EventController extends AbstractController<Event> {

    @EJB
    private EventBean bean;

    public EventController() {
        entityClass = Event.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }

    public EnumSet<EventType> getTypes() {
        return EnumSet.of(EventType.GAME, EventType.TRAINING);
    }

    public void download() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        try {
            String template = ec.getRealPath("/events/events.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(template);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), new JREmptyDataSource());

            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

            ec.responseReset();
            ec.setResponseContentType("application/pdf");
            ec.setResponseContentLength(pdf.length);
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"events.pdf\"");

            OutputStream output = ec.getResponseOutputStream();
            output.write(pdf);
            fc.responseComplete();
        } catch (JRException e) {
            JsfUtil.addErrorMessage(e, "Could not generate pdf.");
        }
    }
}
