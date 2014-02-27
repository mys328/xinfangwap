package com.bwzy.company.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 自定义异常类<br>
 * 这个<code>ExceptionManager</code>类继承自<code>RuntimeException</code>，属于unchecked类型异常。<br>
 * 此类用于统一打包所有的<code>Throwable</code>实例和对象。<br>
 * 此类的StackTraces和Messages是可利用的。<br>
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
     * 无参构造函数<br>
     * 初始化Cause为null
*/
    public ExceptionManager() {
        initCause(null);
    }

    /**
     * 带参构造函数，传入String参数
     * @param msg String
*/
    public ExceptionManager(String msg) {
        super(msg);
        initCause(null);
        this.message=msg;
    }

    /**
     * 带参构造函数，传入Throwable参数
     * @param thrown Throwable
*/
    public ExceptionManager(Throwable thrown) {
        initCause(null);
        exception = thrown;
    }

    /**
     * 带参构造函数，传入String和Throwable参数
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
     * 打印错误堆栈
*/
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * 打印错误堆栈
     * (字节打印流)
*/
    public void printStackTrace(PrintStream outStream) {
        printStackTrace(new PrintWriter(outStream));
    }

    /**
     * 打印错误堆栈
     * (字符打印流)
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