/*
 * Copyright (c) EPAM Systems. All Rights Reserved.
 * @author Alexander_Shushunov@epam.com
 */
package org.zabara.collectionstasks.fromshushu.textparser;


class ParseTextException extends Exception {
    
    private static final long serialVersionUID = 4058053491742213955L;
    
    public ParseTextException() {
    }

    public ParseTextException(String message) {
        super(message);
    }

    public ParseTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseTextException(Throwable cause) {
        super(cause);
    }

    public ParseTextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
