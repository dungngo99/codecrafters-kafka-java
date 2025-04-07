package constants;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Constant {
    public static final Integer ARBITRARY_MESSAGE_SIZE = 0;
    public static final Integer ARBITRARY_CORRELATION_ID = 7;
    public static final String EMPTY_STRING = "";
    public static final int[] EMPTY_ARRAY = new int[0];
    public static final Set<Short> SUPPORTED_API_VERSIONS = IntStream.of(0,1,2,3,4).boxed().map(Integer::shortValue).collect(Collectors.toSet());
    public static final Byte API_KEY_COUNTS = Integer.valueOf(2).byteValue(); // note: offset=1
    public static final Short API_MIN_VERSION = Short.valueOf("0");
    public static final Short API_MAX_VERSION = Short.valueOf("4");
    public static final Integer THROTTLE_TIME_MS = 0;
    public static final Byte TAGGED_FIELD_SIZE = Integer.valueOf(0).byteValue();

    /* BYTE SIZE - begin */
    public static final Integer CLIENT_ID_BYTE_SIZE = 9;
    /* BYTE SIZE - end */

    /* ErrorCode - begin */
    public static final Short NO_ERROR_CODE = 0;
    public static final Short UNSUPPORTED_VERSION_ERROR_CODE = 35;
    /* ErrorCode - end */
}
