package no.knowit.integration.dto;

import java.util.Date;

public record BegrepDefinisjonDTO(String tekst, Date lastUpdated) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String tekst;

        private Date lastUpdated;

        public Builder tekst(String tekst) {
            this.tekst = tekst;
            return this;
        }

        public Builder lastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        public BegrepDefinisjonDTO build() {
            return new BegrepDefinisjonDTO(tekst, lastUpdated);
        }

    }
}
