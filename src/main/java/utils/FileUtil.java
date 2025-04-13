package utils;

import constants.Constant;
import dto.metadata.Batch;
import dto.metadata.Record;
import dto.metadata.Value;
import enums.FieldType;
import service.broker.BrokerService;
import service.load.ClusterMetadataLoadService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public class FileUtil {

    public static void loadConfigs() {
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constant.CONFIG_FILE_NAME);
        try {
            props.load(inputStream);
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                PropertyUtil.saveProperty((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("failed to load properties file, error=" + e.getMessage());
        }
    }

    public static void loadClusterMetadataLog() {
        String logFileDefaultPath = PropertyUtil.getProperty(Constant.LOG_FILE_DEFAULT_PATH);
        try {
            File file = new File(logFileDefaultPath);
            if (!file.exists()) {
                System.out.println("failed to load cluster metadata log due to file not found");
                return;
            }
            FileInputStream fileIS = new FileInputStream(file);
            while (fileIS.available() != 0) {
                loadClusterMetadataBatches(fileIS);
            }
        } catch (IOException e) {
            System.out.println("failed to load cluster metadata log due to error=" + e.getMessage());
        }
    }

    private static void loadClusterMetadataBatches(FileInputStream is) throws IOException {
        Batch batch = new Batch();
        batch.setBaseOffset(BrokerUtil.wrapField(is, FieldType.BIG_INTEGER));
        batch.setBatchLength(BrokerUtil.wrapField(is, FieldType.INTEGER));
        batch.setPartitionLeaderEpoch(BrokerUtil.wrapField(is, FieldType.INTEGER));
        batch.setMagicByte(BrokerUtil.wrapField(is, FieldType.BYTE));
        batch.setCrc(BrokerUtil.wrapField(is, FieldType.INTEGER));
        batch.setAttributes(BrokerUtil.wrapField(is, FieldType.SHORT));
        batch.setLastOffsetDelta(BrokerUtil.wrapField(is, FieldType.INTEGER));
        batch.setBaseTimestamp(BrokerUtil.wrapField(is, FieldType.BIG_INTEGER));
        batch.setMaxTimestamp(BrokerUtil.wrapField(is, FieldType.BIG_INTEGER));
        batch.setProducerId(BrokerUtil.wrapField(is, FieldType.BIG_INTEGER));
        batch.setProducerEpoch(BrokerUtil.wrapField(is, FieldType.SHORT));
        batch.setBaseSequence(BrokerUtil.wrapField(is, FieldType.INTEGER));
        batch.setRecordLength(BrokerUtil.wrapField(is, FieldType.INTEGER));
        batch.setRecords(new LinkedList<>());
        int numRecords = ByteUtil.convertStreamToInt(batch.getRecordLength().getData());
        for (int i=0; i<numRecords; i++) {
            Record record = loadClusterMetadataRecords(is, i);
            batch.getRecords().add(record);
        }
        BrokerService.CLUSTER_METADATA_LOG.addBatch(batch);
    }

    private static Record loadClusterMetadataRecords(FileInputStream is, int index) throws IOException {
        Record record = new Record();
        if (index == 0) {
            record.setLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        } else {
            record.setLength(BrokerUtil.wrapField(is, FieldType.SHORT));
        }
        record.setAttributes(BrokerUtil.wrapField(is, FieldType.BYTE));
        record.setTimestampDelta(BrokerUtil.wrapField(is, FieldType.BYTE));
        record.setOffsetDelta(BrokerUtil.wrapField(is, FieldType.BYTE));
        record.setKeyLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        record.setKey(null);
        if (index == 0) {
            record.setValueLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        } else {
            record.setValueLength(BrokerUtil.wrapField(is, FieldType.SHORT));
        }
        record.setValue(getClusterMetadataValue(is));
        record.setHeaderArrayCount(BrokerUtil.wrapField(is, FieldType.BYTE));
        return record;
    }

    private static Value getClusterMetadataValue(FileInputStream is) throws IOException {
        return ClusterMetadataLoadService.getClusterMetadataValue(is);
    }

}
