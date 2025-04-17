package constants;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Constant {
    public static final Set<Short> SUPPORTED_API_VERSIONS = IntStream.range(0, 20).boxed().map(Integer::shortValue).collect(Collectors.toSet());
    public static final Integer THROTTLE_TIME_MS = 0;
    public static final Byte TAGGED_FIELD_SIZE = Integer.valueOf(0).byteValue();
    public static final Integer GRACE_PERIOD_BETWEEN_REQUEST_HANDLING = 100;
    public static final byte[] DEFAULT_TOPIC_ID = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static final Byte DEFAULT_IS_INTERNAL = 0;
    public static final Byte DEFAULT_PARTITION_ARRAY_LENGTH = 1;
    public static final Integer DEFAULT_TOPIC_AUTHORIZED_OPERATIONS = 0;
    public static final Byte DEFAULT_ELIGIBLE_LEADER_REPLICAS_LENGTH = 1;
    public static final Byte DEFAULT_LAST_KNOWN_ELR_LENGTH = 1;
    public static final Byte DEFAULT_OFFLINE_REPLICAS_LENGTH = 1;
    public static final Byte DEFAULT_FETCH_RESPONSE_PARTITION_LENGTH = 2;
    public static final Long DEFAULT_FETCH_RESPONSE_HIGH_WATERMARK = 0L;
    public static final Long DEFAULT_FETCH_RESPONSE_LAST_STABLE_OFFSET = 0L;
    public static final Long DEFAULT_FETCH_RESPONSE_LOG_START_OFFSET = 0L;
    public static final Byte DEFAULT_FETCH_RESPONSE_ABORTED_TRANSACTIONS_LENGTH = 0;
    public static final Integer DEFAULT_FETCH_RESPONSE_PREFERRED_READ_REPLICA = 0;
    public static final Byte DEFAULT_FETCH_RESPONSE_RECORD_LENGTH = 0;
    public static final Integer TOPIC_ID_LENGTH = 16;
    public static final String CONFIG_FILE_NAME = "config-default.properties";
    public static final String LOG_FILE_DEFAULT_PATH = "log.file.default.path";
}
