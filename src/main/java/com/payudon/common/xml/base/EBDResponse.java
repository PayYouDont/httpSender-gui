package com.payudon.common.xml.base;

public interface EBDResponse extends EBD {
    EBD createFullResponse();
    EBD createIncrementalResponse();
}
