package com.bwzy.company.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * �Զ����쳣��<br>
 * ���<code>ExceptionManager</code>��̳���<code>RuntimeException</code>������unchecked�����쳣��<br>
 * ��������ͳһ������е�<code>Throwable</code>ʵ���Ͷ���<br>
 * �����StackTraces��Messages�ǿ����õġ�<br>
 * 
 * @author oldjiang
 * @version 2012-01-17
 * @see java.lang.Exception
 * @since JDK 1.5
 */

public class ExceptionManager extends RuntimeException {

    private static final long serialVersionUID = 6785236870166144787L;
    private Throwable exception;
    private String message;

    /**
     * �޲ι��캯��<br>
     * ��ʼ��CauseΪnull
*/
    public ExceptionManager() {
        initCause(null);
    }

    /**
     * ���ι��캯��������String����
     * @param msg String
*/
    public ExceptionManager(String msg) {
        super(msg);
        initCause(null);
        this.message=msg;
    }

    /**
     * ���ι��캯��������Throwable����
     * @param thrown Throwable
*/
    public ExceptionManager(Throwable thrown) {
        initCause(null);
        exception = thrown;
    }

    /**
     * ���ι��캯��������String��Throwable����
     * @param msg String
     * @param thrown Throwable
*/
    public ExceptionManager(String msg, Throwable thrown) {
        super(msg);
        initCause(null);
        this.message=msg;
        this.exception = thrown;
    }
    
    /**
     * ��ӡ�����ջ
*/
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * ��ӡ�����ջ
     * (�ֽڴ�ӡ��)
*/
    public void printStackTrace(PrintStream outStream) {
        printStackTrace(new PrintWriter(outStream));
    }

    /**
     * ��ӡ�����ջ
     * (�ַ���ӡ��)
*/
    public void printStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
        if (getException() != null) {
            getException().printStackTrace(writer);
        }
        writer.flush();
    }

    /**
     * exception's getter
     * @return reception
*/
    public Throwable getException() {
        return exception;
    }

    /**
     * @return exception
*/
    public Throwable getCause() {
        return exception;
    }
    
    /**
     * message's getter
     * @return message
*/
    public String getMessage() {
        return message;
    }

    /**
     * message's setter
     * @param message
*/
    public void setMessage(String message) {
        this.message = message;
    }
}