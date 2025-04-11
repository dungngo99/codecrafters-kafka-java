package constants;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Constant {
    public static final Set<Short> SUPPORTED_API_VERSIONS = IntStream.of(0,1,2,3,4).boxed().map(Integer::shortValue).collect(Collectors.toSet());
    public static final Byte API_KEY_COUNTS = Integer.valueOf(3).byteValue(); // note: offset=1
    public static final Short API_MIN_VERSION = Short.valueOf("0");
    public static final Short API_MAX_VERSION = Short.valueOf("4");
    public static final Integer THROTTLE_TIME_MS = 0;
    public static final Byte TAGGED_FIELD_SIZE = Integer.valueOf(0).byteValue();
    public static final Integer GRACE_PERIOD_BETWEEN_REQUEST_HANDLING = 100;
    public static final byte[] DEFAULT_TOPIC_ID = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static final Byte DEFAULT_IS_INTERNAL = 0;
    public static final Byte DEFAULT_PARTITION_ARRAY_LENGTH = 0;
    public static final Integer DEFAULT_TOPIC_AUTHORIZED_OPERATIONS = 0;
    public static final Integer TOPIC_ID_LENGTH = 16;
}
