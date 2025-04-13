package dto.metadata;

import java.util.LinkedList;

public class Log {
    private LinkedList<Batch> batches;

    public Log() {
        this.batches = new LinkedList<>();
    }

    public void addBatch(Batch batch) {
        batches.add(batch);
    }

    public LinkedList<Batch> getBatches() {
        return batches;
    }

    public void setBatches(LinkedList<Batch> batches) {
        this.batches = batches;
    }
}
