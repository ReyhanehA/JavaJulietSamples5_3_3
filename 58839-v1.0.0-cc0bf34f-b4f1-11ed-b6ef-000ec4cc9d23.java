/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE78_OS_Command_Injection__getCookiesServlet_31.java
Label Definition File: CWE78_OS_Command_Injection.label.xml
Template File: sources-sink-31.tmpl.java
*/
/*
 * @description
 * CWE: 78 OS Command Injection
 * BadSource: getCookiesServlet Read data from the first cookie
 * GoodSource: A hardcoded string
 * Sinks: exec
 *    BadSink : dynamic command execution with Runtime.getRuntime().exec()
 * Flow Variant: 31 Data flow: make a copy of data within the same method
 *
 * */

package testcases.CWE78_OS_Command_Injection;

import testcasesupport.*;

import javax.servlet.http.*;

import javax.servlet.http.*;
import java.util.logging.Logger;

public class CWE78_OS_Command_Injection__getCookiesServlet_31 extends AbstractTestCaseServlet
{

    /* uses badsource and badsink */
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data_copy;
        {
            String data;

            Logger log_bad = Logger.getLogger("local-logger");

            /* read parameter from cookie */
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null)
            {
                data = cookieSources[0].getValue();
            }
            else
            {
                data = null;
            }

            data_copy = data;
        }
        {
            String data = data_copy;

            String osCommand;
            if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
            {
                /* running on Windows */
                osCommand = "c:\\WINDOWS\\SYSTEM32\\cmd.exe /c dir ";
            }
            else
            {
                /* running on non-Windows */
                osCommand = "/bin/ls ";
            }

            /* POTENTIAL FLAW: command injection */
            Process p = Runtime.getRuntime().exec(osCommand + data);
            p.waitFor();

        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data_copy;
        {
            String data;

            java.util.logging.Logger log_good = java.util.logging.Logger.getLogger("local-logger");

            /* FIX: Use a hardcoded string */
            data = "foo";

            data_copy = data;
        }
        {
            String data = data_copy;

            String osCommand;
            if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
            {
                /* running on Windows */
                osCommand = "c:\\WINDOWS\\SYSTEM32\\cmd.exe /c dir ";
            }
            else
            {
                /* running on non-Windows */
                osCommand = "/bin/ls ";
            }

            /* POTENTIAL FLAW: command injection */
            Process p = Runtime.getRuntime().exec(osCommand + data);
            p.waitFor();

        }
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}