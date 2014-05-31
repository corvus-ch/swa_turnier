package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.EventBean;
import ch.fhnw.swa.turnier.domain.Event;
import ch.fhnw.swa.turnier.domain.EventType;
import ch.fhnw.swa.turnier.utils.EventJRDataSource;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * The controller for events.
 */
@Named
@SessionScoped
public class EventController extends AbstractController<Event> {

    /**
     * The CRUD bean for events.
     */
    @EJB
    private EventBean bean;

    /**
     * The report.
     *
     * Jasper rports object containing the compiled report template ready to be
     * used for creating rports.
     */
    private final JasperReport report;

    /**
     * Constructor.
     *
     * @throws JRException
     *   When failing to compile the report template.
     */
    public EventController() throws JRException {
        entityClass = Event.class;

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        String template = ec.getRealPath("/events/events.jrxml");
        report = JasperCompileManager.compileReport(template);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }

    /**
     * List of event types.
     *
     * @return
     *   The list of all awaillable event types.
     */
    public EnumSet<EventType> getTypes() {
        return EnumSet.of(EventType.GAME, EventType.TRAINING);
    }

    /**
     * Gets the events PDF.
     * 
     * Gets a PDF containing a list of events.
     *
     * @return
     *   The PDF as byte array.
     *
     * @throws JRException
     *   When failing to create the report.
     */
    private byte[] getPdf() throws JRException {
        JRDataSource data = new EventJRDataSource(getItems().iterator());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap(), data);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    /**
     * Offers the evetns pdf for download.
     *
     * @throws IOException
     *   When failing to write the PDF to the output stream.
     */
    public void download() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        try {
            byte[] pdf = getPdf();

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
