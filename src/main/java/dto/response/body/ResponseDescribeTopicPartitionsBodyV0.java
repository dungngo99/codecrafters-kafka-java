package dto.response.body;

import dto.Field;

import java.util.List;

public class ResponseDescribeTopicPartitionsBodyV0 extends ResponseBaseBody {
    public static class Item {
        private Field errorCode;
        private Field topicNameLength;
        private Field topicName;
        private Field topicId;
        private Field isInternal;
        private Field partitionArrayLength;
        private Field topicAuthorizedOperations;
        private Field taggedFieldSize;

        public Field getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Field errorCode) {
            this.errorCode = errorCode;
        }

        public Field getTopicNameLength() {
            return topicNameLength;
        }

        public void setTopicNameLength(Field topicNameLength) {
            this.topicNameLength = topicNameLength;
        }

        public Field getTopicName() {
            return topicName;
        }

        public void setTopicName(Field topicName) {
            this.topicName = topicName;
        }

        public Field getTopicId() {
            return topicId;
        }

        public void setTopicId(Field topicId) {
            this.topicId = topicId;
        }

        public Field getIsInternal() {
            return isInternal;
        }

        public void setIsInternal(Field isInternal) {
            this.isInternal = isInternal;
        }

        public Field getPartitionArrayLength() {
            return partitionArrayLength;
        }

        public void setPartitionArrayLength(Field partitionArrayLength) {
            this.partitionArrayLength = partitionArrayLength;
        }

        public Field getTopicAuthorizedOperations() {
            return topicAuthorizedOperations;
        }

        public void setTopicAuthorizedOperations(Field topicAuthorizedOperations) {
            this.topicAuthorizedOperations = topicAuthorizedOperations;
        }

        public Field getTaggedFieldSize() {
            return taggedFieldSize;
        }

        public void setTaggedFieldSize(Field taggedFieldSize) {
            this.taggedFieldSize = taggedFieldSize;
        }
    }

    private Field throttleTimeMS;
    private Field topicArrayLength;
    private List<Item> topicList;
    private Field nextCursor;
    private Field taggedFieldSize;

    public Field getThrottleTimeMS() {
        return throttleTimeMS;
    }

    public void setThrottleTimeMS(Field throttleTimeMS) {
        this.throttleTimeMS = throttleTimeMS;
    }

    public Field getTopicArrayLength() {
        return topicArrayLength;
    }

    public void setTopicArrayLength(Field topicArrayLength) {
        this.topicArrayLength = topicArrayLength;
    }

    public List<Item> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Item> topicList) {
        this.topicList = topicList;
    }

    public Field getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Field nextCursor) {
        this.nextCursor = nextCursor;
    }

    public Field getTaggedFieldSize() {
        return taggedFieldSize;
    }

    public void setTaggedFieldSize(Field taggedFieldSize) {
        this.taggedFieldSize = taggedFieldSize;
    }
}
