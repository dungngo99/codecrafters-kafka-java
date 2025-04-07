package constants;

import java.util.Set;

public class Constant {
    public static final Integer ARBITRARY_MESSAGE_SIZE = 0;
    public static final Integer ARBITRARY_CORRELATION_ID = 7;
    public static final Integer INT_BYTE_SIZE = 4;
    public static final Integer SHORT_BYTE_SIZE = 2;
    public static final Integer MESSAGE_SIZE_BYTE_SIZE = 4;
    public static final Integer REQUEST_API_KEY_BYTE_SIZE = 2;
    public static final Integer REQUEST_API_VERSION_BYTE_SIZE = 2;
    public static final Integer CORRELATION_ID_BYTE_SIZE = 4;
    public static final Integer ERROR_CODE_BYTE_SIZE = 2;
    public static final String EMPTY_STRING = "";
    public static final int[] EMPTY_ARRAY = new int[0];
    public static final Set<Integer> SUPPORTED_API_VERSIONS = Set.of(4);

    /* ErrorCode - begin */
    public static final Short UNSUPPORTED_VERSION_ERROR_CODE = 35;
    /* ErrorCode - end */
}
