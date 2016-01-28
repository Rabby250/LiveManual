package com.rabby250.livemanual.provider;

public class LiveManualContract {

    private static final String DEFAULT_AUTHORITY
            = "com.rabby250.livemanual";
    private static final String AUTHORITY_CLASS_NAME
            = DEFAULT_AUTHORITY + ".provider.LiveManualAuthority";
    private static final String AUTHORITY_FIELD_NAME = "AUTHORITY";

    public static final String AUTHORITY = initAuthority();

    private static String initAuthority() {
        try {
            final Class<?> authorityClass = LiveManualContract.class
                    .getClassLoader()
                    .loadClass(AUTHORITY_CLASS_NAME);
            final Object authority = authorityClass
                    .getDeclaredField(AUTHORITY_FIELD_NAME).get(null);
            if (authority != null) {
                return authority.toString();
            } else {
                return DEFAULT_AUTHORITY;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return DEFAULT_AUTHORITY;
        }
    }
}
