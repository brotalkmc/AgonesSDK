package fun.reallyisnt.oms.models.meta;

import agones.dev.sdk.Sdk;

import java.util.Map;
import java.util.Objects;

public class ObjectMeta {

    private final String name;
    private final String namespace;
    private final String uid;

    private final String resourceVersion;
    private final long generation;

    private final long creationTimestamp;
    private final long deletionTimestamp;

    private final Map<String, String> annotations;
    private final Map<String, String> labels;

    public ObjectMeta(Sdk.GameServer gameServer) {
        Sdk.GameServer.ObjectMeta meta = gameServer.getObjectMeta();

        this.name = meta.getName();
        this.namespace = meta.getNamespace();
        this.uid = meta.getUid();

        this.resourceVersion = meta.getResourceVersion();
        this.generation = meta.getGeneration();

        this.creationTimestamp = meta.getCreationTimestamp();
        this.deletionTimestamp = meta.getDeletionTimestamp();

        this.annotations = meta.getAnnotationsMap();
        this.labels = meta.getLabelsMap();
    }

    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getUid() {
        return uid;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public long getGeneration() {
        return generation;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public long getDeletionTimestamp() {
        return deletionTimestamp;
    }

    public Map<String, String> getAnnotations() {
        return annotations;
    }

    public Map<String, String> getLabels() {
        return labels;
    }
}
