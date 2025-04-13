package dto.metadata.record;

import dto.Field;
import dto.metadata.Value;

/**
 * - Name length
 * - Name
 * - Feature Level
 */

public class FeatureLevelRecord extends Value {
    private static final long serialVersionUID = 10947049379L;
    private Field nameLength;
    private Field name;
    private Field featureLevel;

    public Field getNameLength() {
        return nameLength;
    }

    public void setNameLength(Field nameLength) {
        this.nameLength = nameLength;
    }

    public Field getName() {
        return name;
    }

    public void setName(Field name) {
        this.name = name;
    }

    public Field getFeatureLevel() {
        return featureLevel;
    }

    public void setFeatureLevel(Field featureLevel) {
        this.featureLevel = featureLevel;
    }
}
