package no.knowit.integration.dto;

public record BegrepDTO(
        String id,
        String subject,
        String prefLabel,
        String altLabel,
        BegrepDefinisjonDTO definition
) {
    public static BegrepDTO.Builder builder() {
        return new BegrepDTO.Builder();
    }

    public static class Builder {
        private String id;
        private String subject;
        private String prefLabel;
        private String altLabel;
        private BegrepDefinisjonDTO definition;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder prefLabel(String prefLabel) {
            this.prefLabel = prefLabel;
            return this;
        }

        public Builder altLabel(String altLabel) {
            this.altLabel = altLabel;
            return this;
        }

        public Builder definition(BegrepDefinisjonDTO definition) {
            this.definition = definition;
            return this;
        }

        public BegrepDTO build() {
            return new BegrepDTO(id, subject, prefLabel, altLabel, definition);
        }
    }
}
