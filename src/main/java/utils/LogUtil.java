package utils;

import dto.LogContext;
import dto.metadata.Batch;
import dto.metadata.Log;
import dto.metadata.Record;
import dto.metadata.Value;
import enums.FieldType;
import enums.ValueType;
import service.log.LogValueService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class LogUtil {

    public static void updateBatchRecordChecksum(Batch batch) {

    }

    public static Log getLog(LogContext logContext) throws IOException {
        if (Objects.isNull(logContext) || Objects.isNull(logContext.getFilePath()) || logContext.getFilePath().isBlank()) {
            return null;
        }
        String filePath = logContext.getFilePath();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("failed to load cluster metadata log due to file not found");
            return null;
        }
        FileInputStream fileIS = new FileInputStream(file);
        logContext.setIs(fileIS);
        Log log = new Log();
        while (fileIS.available() != 0) {
            Batch batch = getLogBatch(logContext);
            log.addBatch(batch);
        }
        return log;
    }

    private static Batch getLogBatch(LogContext logContext) throws IOException {
        FileInputStream is = logContext.getIs();
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
        for (int i = 0; i < numRecords; i++) {
            Record record = getLogRecord(i, logContext);
            batch.getRecords().add(record);
        }
        return batch;
    }

    private static Record getLogRecord(int index, LogContext logContext) throws IOException {
        FileInputStream is = logContext.getIs();
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
        boolean isPartitionLogRecord = Objects.equals(logContext.getValueType(), ValueType.PARTITION);
        if (isPartitionLogRecord) {
            int valueLength;
            if (index == 0) {
                valueLength = ByteUtil.convertStreamToByte(record.getValueLength().getData());
            } else {
                valueLength = ByteUtil.convertStreamToShort(record.getValueLength().getData());
            }
            record.setValueStream(BrokerUtil.wrapField(is, FieldType.COMPACT_RECORD, valueLength / 2));
        } else {
            record.setValue(getLogValue(logContext));
        }
        record.setHeaderArrayCount(BrokerUtil.wrapField(is, FieldType.BYTE));
        return record;
    }

    private static Value getLogValue(LogContext logContext) throws IOException {
        return LogValueService.getLogValue(logContext);
    }
}
