package com.mlog.comm.type;

/**
 * APS 인증 타입 *
 */
public enum CertType {

    GUEST("0"),
    IDPW("1"),
    CTN("2"),
    OTHER_CARRIERS("3"),
    ONEID("4"),
    REALMAC("5");

    private final String name;

    CertType(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
