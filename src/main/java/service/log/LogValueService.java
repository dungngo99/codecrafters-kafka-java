package service.log;

import dto.metadata.Value;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface LogValueService<T extends Value> {
    T createValue();

    void load(ByteArrayInputStream is, T value) throws IOException;

    void map(T value);

    void register();
}
