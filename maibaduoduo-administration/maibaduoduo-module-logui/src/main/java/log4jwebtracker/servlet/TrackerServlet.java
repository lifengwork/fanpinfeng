package log4jwebtracker.servlet;

import java.io.*;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log4jwebtracker.io.StreamUtils;
import log4jwebtracker.logging.LoggingUtils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Tracker servlet.
 *
 * @author Mariano Ruiz
 */
public class TrackerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(TrackerServlet.class);

    private static final int BUFFER_SIZE = 1024 * 16;

    private byte[] jqueryMin = null;
    private byte[] jqueryWordWrap = null;
    private byte[] css = null;
    private byte[] logo = null;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Action URI, eg. /webtracker/tracker/config
        String action = request.getRequestURI();
        // Servlet URI, eg. /webtracker/tracker
        String baseAction = request.getContextPath() + request.getServletPath();
        if (request.getPathInfo() == null || request.getPathInfo().equals("") || request.getPathInfo().equals("/")) {
            response.sendRedirect(response.encodeRedirectURL(baseAction + "/config"));
            // If JS resource
        } else if (request.getPathInfo().startsWith("/js/")) {
            if (request.getPathInfo().equals("/js/jquery-1.6.4.min.js")) {
                doResource(request, response, getJQueryMin(), "application/javascript");
            } else if (request.getPathInfo().equals("/js/jquery.wordWrap.js")) {
                doResource(request, response, getJQueryWordWrap(), "application/javascript");
            } else {
                logger.warn("Request javascript resource " + action + " not found.");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            // If CSS resource
        } else if (request.getPathInfo().startsWith("/css/")) {
            if (request.getPathInfo().equals("/css/tracker.css")) {
                doResource(request, response, getCSS(), "text/css");
            } else {
                logger.warn("Request CSS resource " + action + " not found.");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            // If image resource
        } else if (request.getPathInfo().startsWith("/img/")) {
            if (request.getPathInfo().equals("/img/logo.png")) {
                doResource(request, response, getLogo(), "image/png");
            } else {
                logger.warn("Request image resource " + action + " not found.");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            // If log request
        } else if (request.getPathInfo().startsWith("/taillog")) {
            doTailLog(request, response, action, baseAction);
            // If ajax log request
        } else if (request.getPathInfo().startsWith("/getlog")) {
            doGetLog(request, response, action, baseAction);
            // Page resource
        } else {
            doPage(request, response, action, baseAction);
        }
    }

    private void doResource(
            HttpServletRequest request, HttpServletResponse response,
            byte[] buffer, String contentType)
            throws ServletException, IOException {
        ServletOutputStream output = response.getOutputStream();
        response.setContentType(contentType);
        response.setContentLength(buffer.length);
        output.write(buffer, 0, buffer.length);
        output.flush();
        output.close();
    }

    private void doPage(
            HttpServletRequest request, HttpServletResponse response,
            String action, String baseAction)
            throws ServletException, IOException {
        request.setAttribute("action", action);
        request.setAttribute("baseAction", baseAction);
        if (request.getPathInfo().equals("/log")) {
            doLog(request, response, action, baseAction);
        } else if (request.getPathInfo().equals("/config")) {
            doConfiguration(request, response, action, baseAction);
        } else {
            logger.warn("Request page " + action + " not found.");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void doConfiguration(
            HttpServletRequest request, HttpServletResponse response,
            String action, String baseAction)
            throws ServletException, IOException {
        List loggers = LoggingUtils.getLoggers();
        request.setAttribute("loggers", loggers);
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            if (parameterName.equals("root")) {
                Level level = Level.toLevel(request.getParameter(parameterName));
                Logger root = LogManager.getRootLogger();
                synchronized (root) {
                    root.setLevel(level);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug(parameterName + '=' + level.toString());
                }
            } else {
                if (LoggingUtils.contains(loggers, parameterName)) {
                    Level level = Level.toLevel(request.getParameter(parameterName));
                    Logger logg = LogManager.getLogger(parameterName);
                    synchronized (logg) {
                        logg.setLevel(level);
                    }
                    if (logger.isDebugEnabled()) {
                        logger.debug(parameterName + '=' + level.toString());
                    }
                } else {
                    logger.warn("Logger name " + parameterName + " not exist.");
                }
            }
        }
        doHTML(request, response);
    }

    private void doLog(HttpServletRequest request, HttpServletResponse response, String action, String baseAction) throws ServletException, IOException {
        request.setAttribute("fileAppenders", LoggingUtils.getFileAppenders());
        doHTML(request, response);
    }

    private void doTailLog(HttpServletRequest request,HttpServletResponse response, String action, String baseAction) throws ServletException, IOException {
        String appenderName = request.getParameter("appender");
        if (appenderName != null) {
            int lines = 20;
            if (request.getParameter("lines") != null) {
                try {
                    lines = Integer.parseInt(request.getParameter("lines"));
                } catch (NumberFormatException e) {
                    logger.warn("Number format 'lines' parameter invalid = "
                            + request.getParameter("lines"));
                }
            }
            FileAppender fileAppender = LoggingUtils.getFileAppender(appenderName);
            if (fileAppender != null) {
                OutputStream output = response.getOutputStream();
                try {
                    String contentType = "text/plain";
                    if (fileAppender.getEncoding() != null) {
                        contentType += "; charset=" + fileAppender.getEncoding();
                    }
                    response.setContentType(contentType);
                    response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
                    response.setHeader("Pragma", "no-cache");        // HTTP 1.0
                    response.setDateHeader("Expires", -1);           // prevents caching
                    RandomAccessFile inputFile = new RandomAccessFile(fileAppender.getFile(), "r");
                    StreamUtils.tailFile(inputFile, output, BUFFER_SIZE, lines);
                    inputFile.close();
                } catch (IOException e) {
                    logger.error("Error getting the file appender="
                            + fileAppender.getFile(), e);
                    output.write("TrackerError: Check the log manually.".getBytes());
                }
                output.close();
                output.flush();
                output.close();
            } else {
                logger.error("FileAppender with name=" + appenderName + " not exist.");
            }
        } else {
            logger.error("No appender name parameter specified.");
        }
    }

    private void doGetLog(HttpServletRequest request,
                          HttpServletResponse response, String action, String baseAction)
            throws ServletException, IOException {
        String appenderName = request.getParameter("appender");
        if (appenderName != null) {
            FileAppender fileAppender = LoggingUtils.getFileAppender(appenderName);
            if (fileAppender != null) {
                File file = new File(fileAppender.getFile());
                OutputStream output = response.getOutputStream();
                try {
                    String contentType = "text/plain";
                    if (fileAppender.getEncoding() != null) {
                        contentType += "; charset=" + fileAppender.getEncoding();
                    }
                    response.setContentType(contentType);
                    response.setContentLength((int) file.length());
                    response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
                    response.setHeader("Pragma", "no-cache");        // HTTP 1.0
                    response.setDateHeader("Expires", -1);           // prevents caching
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\""
                                    + file.getName() + "\"");
                    InputStream fileStream = new FileInputStream(fileAppender.getFile());
                    StreamUtils.readStream(fileStream, output, BUFFER_SIZE);
                    fileStream.close();
                } catch (IOException e) {
                    response.setHeader("Content-Disposition", "");
                    logger.error("Error getting the file appender="
                            + fileAppender.getFile(), e);
                    output.write("TrackerError: Check the log manually.".getBytes());
                    output.close();
                }
                output.flush();
                output.close();
            } else {
                logger.error("FileAppender with name=" + appenderName + " not exist.");
            }
        } else {
            logger.error("No appender name parameter specified.");
        }
    }


    public void doHTML(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = null;
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta http-equiv=\"Pragma\" content=\"no-cache\" /><meta http-equiv=\"Expires\" content=\"-1\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"");
            stringBuilder.append(((String) request.getAttribute("baseAction")));
            stringBuilder.append(("/css/tracker.css\" /><script type=\"text/javascript\" src=\""));
            stringBuilder.append(((String) request.getAttribute("baseAction")));
            stringBuilder.append(("/js/jquery-1.6.4.min.js\"></script><script type=\"text/javascript\" src=\""));
            stringBuilder.append(((String) request.getAttribute("baseAction")));
            stringBuilder.append("/js/jquery.wordWrap.js\"></script><script>$(document).ready( function() {");
            if (request.getPathInfo().equals("/config")) {
                stringBuilder.append("$('#filter').keyup(function() {var filterKey = this.value.toLowerCase();$('#loggers tbody tr').filter(function() {return $('select', this)[0].name.toLowerCase().indexOf(filterKey) == -1;}).hide();$('#loggers tbody tr').filter(function() {return $('select', this)[0].name.toLowerCase().indexOf(filterKey) != -1;}).show();});$('select').change(function() {$(this).parent().submit();});");
            }
            stringBuilder.append("");
            if (request.getPathInfo().equals("/log")) {
                stringBuilder.append("$('#wrapCheck').click(function() {var textarea = $('#logText').get();if( ! $(this).attr('checked') ) {$(textarea).wordWrap('on');} else {$(textarea).wordWrap('off');}});var refresh = function() {$('#loading-mask').text('Loading...');$('#loading-mask').removeClass('error');$('#loading-mask').show();var data = {appender: $('#appender').val()};if($('#lines').val()!='') {data.lines = $('#lines').val();}$.ajax({url: '");
                stringBuilder.append((String) request.getAttribute("baseAction"));
                stringBuilder.append("/taillog',data: data,dataType: 'text',cache: false,success: function(data) {if(data.indexOf('TrackerError')==-1) {$('#loading-mask').hide();$('#logText').val(data);$('#logText').get(0).scrollTop = $('#logText').get(0).scrollHeight;} else {$('#loading-mask').addClass('error');$('#loading-mask').text('Error: Check the log manually');}}});};$('#refresh').click(refresh);$('#lines').bind('keypress', function(e) { if(e.keyCode==13) { refresh(); return false; } });$('#download').click(function() {});");
            }
            stringBuilder.append("});</script><!--[if !IE 7]><style type=\"text/css\">#wrap { display:table;height:100% }</style><![endif]--></head><body><div id=\"wrap\"><div id=\"header\"><h1>");
            stringBuilder.append("系统日志查看");
            stringBuilder.append("</h1></div><div id=\"navcontainer\"><ul id=\"navlist\"><li><a href=\"");
            stringBuilder.append((String) request.getAttribute("baseAction"));
            stringBuilder.append("/config\"   ");
            if (request.getPathInfo().equals("/config")) {
                stringBuilder.append("class=\"active\" ");
            }
            stringBuilder.append(">Level配置</a></li><li><a href=\"");
            stringBuilder.append((String) request.getAttribute("baseAction"));
            stringBuilder.append("/log\"   ");
            if (request.getPathInfo().equals("/log")) {
                stringBuilder.append("class=\"active\" ");
            }
            stringBuilder.append(" >Log查看</a></li></ul></div><div class=\"clear\"></div><div id=\"main\">");

            if (request.getPathInfo().equals("/config")) {
                List loggers = (List) request.getAttribute("loggers");

                stringBuilder.append("<div id=\"configuration\"><div id=\"filterContainer\"><div id=\"filterTextContainer\"><p>Filter:</p></div><div id=\"filterInputContainer\"><input type=\"text\" id=\"filter\" name=\"filter\"       placeholder=\"Enter the name or part of it\" spellcheck=\"false\"/></div></div><div class=\"clear\"></div><div id=\"loggersContainer\"><table id=\"loggers\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><thead><tr><th>Logger</th><th>Level</th></tr></thead><tbody>");

                for (int i = 0; i < loggers.size(); i++) {
                    Logger logger = (Logger) loggers.get(i);

                    stringBuilder.append("<tr class=\"logger-");
                    stringBuilder.append(i % 2 == 0 ? "pair" : "odd");
                    stringBuilder.append("\"><td class=\"logger-name\"><label for=\"");
                    stringBuilder.append(logger.getName());
                    stringBuilder.append("\">");
                    stringBuilder.append(logger.getName());
                    stringBuilder.append("</label></td><td class=\"logger-level\"><form action=\"");
                    stringBuilder.append((String) request.getAttribute("baseAction"));
                    stringBuilder.append("/config\" method=\"post\"><select id=\"");
                    stringBuilder.append(logger.getName());
                    stringBuilder.append("\" name=\"");
                    stringBuilder.append(logger.getName());
                    stringBuilder.append("\"><option value=\"TRACE\" ");
                    if (logger.getEffectiveLevel().toString() == "TRACE") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">TRACE</option><option value=\"DEBUG\" ");
                    if (logger.getEffectiveLevel().toString() == "DEBUG") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">DEBUG</option><option value=\"INFO\" ");
                    if (logger.getEffectiveLevel().toString() == "INFO") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">INFO</option><option value=\"WARN\" ");
                    if (logger.getEffectiveLevel().toString() == "WARN") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">WARN</option><option value=\"ERROR\" ");
                    if (logger.getEffectiveLevel().toString() == "ERROR") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">ERROR</option><option value=\"FATAL\" ");
                    if (logger.getEffectiveLevel().toString() == "FATAL") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">FATAL</option><option value=\"OFF\" ");
                    if (logger.getEffectiveLevel().toString() == "OFF") {
                        stringBuilder.append(" selected=\"selected\" ");
                    }
                    stringBuilder.append(">OFF</option>");
                    stringBuilder.append(logger.getEffectiveLevel().toString());
                    stringBuilder.append("</select></form></td></tr>");

                }

                stringBuilder.append("</tbody></table></div></div>");
            }
            stringBuilder.append("");
            if (request.getPathInfo().equals("/log")) {
                stringBuilder.append("<div id=\"log\"><div id=\"options\"><div style=\"float: left;\"><input type=\"checkbox\" id=\"wrapCheck\" />&nbsp;<label for=\"wrapCheck\">No wrap log</label></div><form action=\"");
                stringBuilder.append((String) request.getAttribute("baseAction"));
                stringBuilder.append("/getlog\"><div style=\"float: right;\"><label for=\"appender\">File appender: </label><select id=\"appender\" name=\"appender\">");

                List fileAppenders = (List) request
                        .getAttribute("fileAppenders");
                for (int i = 0; i < fileAppenders.size(); i++) {
                    FileAppender fap = (FileAppender) fileAppenders.get(i);

                    stringBuilder.append("<option value=\"");
                    stringBuilder.append(fap.getName());
                    stringBuilder.append("\">");
                    stringBuilder.append(fap.getName());
                    stringBuilder.append("</option>");
                }
                stringBuilder.append("</select><label for=\"lines\">Lines: </label><input type=\"number\" id=\"lines\" name=\"lines\" value=\"20\" size=\"4\" style=\"margin-right: 5px;\" /><button type=\"button\" id=\"refresh\">Refresh</button><button type=\"submit\" id=\"download\">Download</button></div></form><div style=\"overflow: hidden;\"><div id=\"loading-mask\" style=\"display: none;\"></div></div></div><textarea id=\"logText\" rows=\"20\" spellcheck=\"false\"></textarea></div>");
            }
            stringBuilder.append("</div></div><div id=\"footer\"><div id=\"back-link\"><span><a href=\"");
            stringBuilder.append(request.getContextPath());
            stringBuilder.append("/\">&uarr; Go to the application</a></span></div></div></body></html>");
            printWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    synchronized private byte[] getJQueryMin() throws IOException {
        if (jqueryMin == null) {
            InputStream in = this.getClass().getResourceAsStream("js/jquery-1.6.4.min.js");
            jqueryMin = toByteArray(in);
            in.close();
        }
        return jqueryMin;
    }

    synchronized private byte[] getJQueryWordWrap() throws IOException {
        if (jqueryWordWrap == null) {
            InputStream in = this.getClass().getResourceAsStream("js/jquery.wordWrap.js");
            jqueryWordWrap = toByteArray(in);
            in.close();
        }
        return jqueryWordWrap;
    }

    synchronized private byte[] getCSS() throws IOException {
        if (css == null) {
            InputStream in = this.getClass().getResourceAsStream("css/tracker.css");
            css = toByteArray(in);
            in.close();
        }
        return css;
    }

    synchronized private byte[] getLogo() throws IOException {
        if (logo == null) {
            InputStream in = this.getClass().getResourceAsStream("img/logo.png");
            logo = toByteArray(in);
            in.close();
        }
        return logo;
    }

    /**
     * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     *
     * @param input the <code>InputStream</code> to read from
     * @return the requested byte array
     * @throws NullPointerException if the input is null
     * @throws IOException          if an I/O error occurs
     */
    private byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        StreamUtils.readStream(input, output, BUFFER_SIZE);
        return output.toByteArray();
    }
}
