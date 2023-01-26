package io.sui.bcsgen;


public abstract class ObjectInfoRequestKind {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static ObjectInfoRequestKind deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return LatestObjectInfo.load(deserializer);
            case 1: return PastObjectInfo.load(deserializer);
            case 2: return PastObjectInfoDebug.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for ObjectInfoRequestKind: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ObjectInfoRequestKind bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ObjectInfoRequestKind value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class LatestObjectInfo extends ObjectInfoRequestKind {
        public final java.util.Optional<ObjectFormatOptions> value;

        public LatestObjectInfo(java.util.Optional<ObjectFormatOptions> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            TraitHelpers.serialize_option_ObjectFormatOptions(value, serializer);
            serializer.decrease_container_depth();
        }

        static LatestObjectInfo load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_option_ObjectFormatOptions(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            LatestObjectInfo other = (LatestObjectInfo) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.Optional<ObjectFormatOptions> value;

            public LatestObjectInfo build() {
                return new LatestObjectInfo(
                    value
                );
            }
        }
    }

    public static final class PastObjectInfo extends ObjectInfoRequestKind {
        public final SequenceNumber value;

        public PastObjectInfo(SequenceNumber value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static PastObjectInfo load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = SequenceNumber.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PastObjectInfo other = (PastObjectInfo) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public SequenceNumber value;

            public PastObjectInfo build() {
                return new PastObjectInfo(
                    value
                );
            }
        }
    }

    public static final class PastObjectInfoDebug extends ObjectInfoRequestKind {
        public final SequenceNumber field0;
        public final java.util.Optional<ObjectFormatOptions> field1;

        public PastObjectInfoDebug(SequenceNumber field0, java.util.Optional<ObjectFormatOptions> field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            field0.serialize(serializer);
            TraitHelpers.serialize_option_ObjectFormatOptions(field1, serializer);
            serializer.decrease_container_depth();
        }

        static PastObjectInfoDebug load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = SequenceNumber.deserialize(deserializer);
            builder.field1 = TraitHelpers.deserialize_option_ObjectFormatOptions(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PastObjectInfoDebug other = (PastObjectInfoDebug) obj;
            if (!java.util.Objects.equals(this.field0, other.field0)) { return false; }
            if (!java.util.Objects.equals(this.field1, other.field1)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.field0 != null ? this.field0.hashCode() : 0);
            value = 31 * value + (this.field1 != null ? this.field1.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public SequenceNumber field0;
            public java.util.Optional<ObjectFormatOptions> field1;

            public PastObjectInfoDebug build() {
                return new PastObjectInfoDebug(
                    field0,
                    field1
                );
            }
        }
    }
}

