package enums;

import java.util.Objects;

public enum ApiKey {
    PRODUCE("Produce", Short.valueOf("0")),
    FETCH("Fetch", Short.valueOf("1")),
    LIST_OFFSETS("ListOffsets", Short.valueOf("2")),
    METADATA("Metadata", Short.valueOf("3")),
    OFFSET_COMMIT("OffsetCommit", Short.valueOf("4")),
    OFFSET_FETCH("OffsetFetch", Short.valueOf("5")),
    FIND_COORDINATOR("FindCoordinator", Short.valueOf("6")),
    JOIN_GROUP("JoinGroup", Short.valueOf("11")),
    HEARTBEAT("Heartbeat", Short.valueOf("12")),
    LEAVE_GROUP("LeaveGroup", Short.valueOf("13")),
    SYNC_GROUP("SyncGroup", Short.valueOf("14")),
    DESCRIBE_GROUPS("DescribeGroups", Short.valueOf("15")),
    LIST_GROUPS("ListGroups", Short.valueOf("16")),
    SASL_HANDSHAKE("SaslHandshake", Short.valueOf("17")),
    API_VERSIONS("ApiVersions", Short.valueOf("18")),
    CREATE_TOPICS("CreateTopics", Short.valueOf("19")),
    DELETE_TOPICS("DeleteTopics", Short.valueOf("20")),
    DELETE_RECORDS("DeleteRecords", Short.valueOf("21")),
    INIT_PRODUCER_ID("InitProducerId", Short.valueOf("22")),
    OFFSET_FOR_LEADER_EPOCH("OffsetForLeaderEpoch", Short.valueOf("23")),
    ADD_PARTITIONS_TO_TXN("AddPartitionsToTxn", Short.valueOf("24")),
    ADD_OFFSETS_TO_TXN("AddOffsetsToTxn", Short.valueOf("25")),
    END_TXN("EndTxn", Short.valueOf("26")),
    WRITE_TXN_MARKERS("WriteTxnMarkers", Short.valueOf("27")),
    TXN_OFFSET_COMMIT("TxnOffsetCommit", Short.valueOf("28")),
    DESCRIBE_ACLS("DescribeAcls", Short.valueOf("29")),
    CREATE_ACLS("CreateAcls", Short.valueOf("30")),
    DELETE_ACLS("DeleteAcls", Short.valueOf("31")),
    DESCRIBE_CONFIGS("DescribeConfigs", Short.valueOf("32")),
    ALTER_CONFIGS("AlterConfigs", Short.valueOf("33")),
    ALTER_REPLICA_LOG_DIRS("AlterReplicaLogDirs", Short.valueOf("34")),
    DESCRIBE_LOG_DIRS("DescribeLogDirs", Short.valueOf("35")),
    SASL_AUTHENTICATE("SaslAuthenticate", Short.valueOf("36")),
    CREATE_PARTITIONS("CreatePartitions", Short.valueOf("37")),
    CREATE_DELEGATION_TOKEN("CreateDelegationToken", Short.valueOf("38")),
    RENEW_DELEGATION_TOKEN("RenewDelegationToken", Short.valueOf("39")),
    EXPIRE_DELEGATION_TOKEN("ExpireDelegationToken", Short.valueOf("40")),
    DESCRIBE_DELEGATION_TOKEN("DescribeDelegationToken", Short.valueOf("41")),
    DELETE_GROUPS("DeleteGroups", Short.valueOf("42")),
    ELECT_LEADERS("ElectLeaders", Short.valueOf("43")),
    INCREMENTAL_ALTER_CONFIGS("IncrementalAlterConfigs", Short.valueOf("44")),
    ALTER_PARTITION_REASSIGNMENTS("AlterPartitionReassignments", Short.valueOf("45")),
    LIST_PARTITION_REASSIGNMENTS("ListPartitionReassignments", Short.valueOf("46")),
    OFFSET_DELETE("OffsetDelete", Short.valueOf("47")),
    DESCRIBE_CLIENT_QUOTAS("DescribeClientQuotas", Short.valueOf("48")),
    ALTER_CLIENT_QUOTAS("AlterClientQuotas", Short.valueOf("49")),
    DESCRIBE_USER_SCRAM_CREDENTIALS("DescribeUserScramCredentials", Short.valueOf("50")),
    ALTER_USER_SCRAM_CREDENTIALS("AlterUserScramCredentials", Short.valueOf("51")),
    DESCRIBE_QUORUM("DescribeQuorum", Short.valueOf("55")),
    UPDATE_FEATURES("UpdateFeatures", Short.valueOf("57")),
    DESCRIBE_CLUSTER("DescribeCluster", Short.valueOf("60")),
    DESCRIBE_PRODUCERS("DescribeProducers", Short.valueOf("61")),
    UNREGISTER_BROKER("UnregisterBroker", Short.valueOf("64")),
    DESCRIBE_TRANSACTIONS("DescribeTransactions", Short.valueOf("65")),
    LIST_TRANSACTIONS("ListTransactions", Short.valueOf("66")),
    CONSUMER_GROUP_HEARTBEAT("ConsumerGroupHeartbeat", Short.valueOf("68")),
    CONSUMER_GROUP_DESCRIBE("ConsumerGroupDescribe", Short.valueOf("69")),
    GET_TELEMETRY_SUBSCRIPTIONS("GetTelemetrySubscriptions", Short.valueOf("71")),
    PUSH_TELEMETRY("PushTelemetry", Short.valueOf("72")),
    LIST_CLIENT_METRICS_RESOURCES("ListClientMetricsResources", Short.valueOf("74")),
    DESCRIBE_TOPIC_PARTITIONS("DescribeTopicPartitions", Short.valueOf("75")),
    ADD_RAFT_VOTER("AddRaftVoter", Short.valueOf("80")),
    REMOVE_RAFT_VOTER("RemoveRaftVoter", Short.valueOf("81"));

    private final String name;
    private final Short key;

    ApiKey(String name, Short key) {
        this.name = name;
        this.key = key;
    }

    public static ApiKey fromKey(Short key) {
        for (ApiKey apiKey: values()) {
            if (Objects.equals(key, apiKey.getKey())) {
                return apiKey;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Short getKey() {
        return key;
    }
}
