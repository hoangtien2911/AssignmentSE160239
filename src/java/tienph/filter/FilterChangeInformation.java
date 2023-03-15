/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tienph.dto.AccountDTO;
import tienph.dto.AccountInsertError;
import tienph.utils.SecurityUtils;

/**
 *
 * @author Hp
 */
public class FilterChangeInformation implements Filter {

    private static final boolean debug = true;
    private final String CHANGE_PROFILE_PAGE = "./user/changeProfile.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterChangeInformation() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterChangeInformation:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterChangeInformation:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("FilterChangeInformation:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            String url = CHANGE_PROFILE_PAGE;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT_USER");
            String txtNewFullname = request.getParameter("txtNewFullName");
            String txtNewPhone = request.getParameter("txtNewPhone");
            String txtOldPassword = request.getParameter("txtOldPassword");
            String txtNewPassword = request.getParameter("txtNewPassword");
            String txtConfirmPassword = request.getParameter("txtConfirmPassword");
            AccountInsertError errors = new AccountInsertError();
            boolean foundError = false;
            //1. Check all user errors
            if (txtNewFullname.trim().length() < 8 || txtNewFullname.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthErr("Fullname is required from 8 to 50 chars."
                        + " Please try again.");
            }
            if (txtNewPhone.trim().length() < 10 || txtNewPhone.trim().length() > 12) {
                foundError = true;
                errors.setPhoneLengthErr("Phone is required from 10 to 12 chars."
                        + " Please try again.");
            }

            if (txtNewPassword.length() < 8 || txtNewPassword.length() > 30) {
                foundError = true;
                errors.setPasswordLengthErr("Password is required from 8 to 30 chars."
                        + " Please try again.");
            } else if (!txtConfirmPassword.equals(txtNewPassword)) {
                foundError = true;
                errors.setConfirmNotMatch("Confirm did not match password."
                        + " Please try again.");
            } else if (!SecurityUtils.getSecurePassword(txtOldPassword).equals(account.getPassword())) {
                foundError = true;
                errors.setPasswordOldNotMatch("Old password did not match."
                        + " Please try again.");
            }
            if (foundError) {
                request.setAttribute("INSERT_ERRORS", errors);
                httpRequest.getRequestDispatcher(url).forward(request, response);
            } else {
                chain.doFilter(request, response);
            }

        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterChangeInformation:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterChangeInformation()");
        }
        StringBuffer sb = new StringBuffer("FilterChangeInformation(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
