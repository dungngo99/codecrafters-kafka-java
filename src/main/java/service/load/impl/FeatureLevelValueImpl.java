package service.load.impl;

import dto.metadata.record.FeatureLevelRecord;
import enums.FieldType;
import enums.RecordType;
import service.load.ClusterMetadataLoadService;
import utils.BrokerUtil;
import utils.ByteUtil;

import java.io.FileInputStream;
import java.io.IOException;

public class FeatureLevelValueImpl extends ClusterMetadataLoadService<FeatureLevelRecord> {
    @Override
    protected FeatureLevelRecord createValue() {
        return new FeatureLevelRecord();
    }

    @Override
    protected void load(FileInputStream is, FeatureLevelRecord value) throws IOException {
        value.setNameLength(BrokerUtil.wrapField(is, FieldType.BYTE));
        int nameLength = ByteUtil.convertStreamToByte(value.getNameLength().getData()) - FieldType.BYTE.getByteSize();
        if (nameLength == -1) {
            value.setName(null);
        } else {
            value.setName(BrokerUtil.wrapField(is, FieldType.STRING, nameLength));
        }
        value.setFeatureLevel(BrokerUtil.wrapField(is, FieldType.SHORT));
    }

    @Override
    protected void map(FeatureLevelRecord value) {
    }

    @Override
    public void register() {
        ClusterMetadataLoadService.STORE.put(RecordType.FEATURE_LEVEL, this);
    }
}
