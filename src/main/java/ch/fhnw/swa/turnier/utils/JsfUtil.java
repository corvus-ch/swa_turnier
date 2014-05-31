package ch.fhnw.swa.turnier.utils;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Collection of JSF related helpers.
 */
public class JsfUtil {

    /**
     * Adds an error message.
     *
     * The message will be printed on the next page load.
     * If ever possible, a meaningfull message will be retrieved from the
     * exception. If the exception does not contain a message,  the method falls
     * back to the default message provided.
     *
     * @param ex
     *   The exeption caused this error.
     * @param defaultMsg
     *   The default message.
     */
    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    /**
     * Adds a bunch of error messages.
     *
     * The messages will be shown on the next page load.
     *
     * @param messages
     *   List of messages.
     */
    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    /**
     * Adds an error message.
     *
     * The message will be printed on the next page load.
     *
     * @param msg
     *   The message.
     */
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * Adds an success message.
     *
     * The message will be printed on the next page load.
     *
     * @param msg
     *   The message.
     */
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
}
