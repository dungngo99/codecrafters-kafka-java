package dto.metadata.record;

import dto.Field;
import dto.metadata.Value;

import java.io.Serializable;

/**
 * - Name length
 * - Topic Name
 * - Topic UUID
 */

public class TopicRecord extends Value implements Serializable {
    private static final long serialVersionUID = 31292730292L;
    private Field nameLength;
    private Field topicName;
    private Field topicUUID;

    public Field getNameLength() {
        return nameLength;
    }

    public void setNameLength(Field nameLength) {
        this.nameLength = nameLength;
    }

    public Field getTopicName() {
        return topicName;
    }

    public void setTopicName(Field topicName) {
        this.topicName = topicName;
    }

    public Field getTopicUUID() {
        return topicUUID;
    }

    public void setTopicUUID(Field topicUUID) {
        this.topicUUID = topicUUID;
    }

    @Override
    public String toString() {
        return "TopicRecord{" +
                "nameLength=" + nameLength +
                ", topicName=" + topicName +
                ", topicUUID=" + topicUUID +
                '}';
    }
}
