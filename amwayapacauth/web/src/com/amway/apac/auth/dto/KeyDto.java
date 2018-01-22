
package com.amway.apac.auth.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
*
* Key DTO specifies private and public key of certificate
*
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "alg",
    "e",
    "n",
    "kid",
    "kty",
    "use"
})
public class KeyDto {

    @JsonProperty("alg")
    private String alg;
    @JsonProperty("e")
    private String e;
    @JsonProperty("n")
    private String n;
    @JsonProperty("kid")
    private String kid;
    @JsonProperty("kty")
    private String kty;
    @JsonProperty("use")
    private String use;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alg")
    public String getAlg() {
        return alg;
    }

    @JsonProperty("alg")
    public void setAlg(String alg) {
        this.alg = alg;
    }

    @JsonProperty("e")
    public String getE() {
        return e;
    }

    @JsonProperty("e")
    public void setE(String e) {
        this.e = e;
    }

    @JsonProperty("n")
    public String getN() {
        return n;
    }

    @JsonProperty("n")
    public void setN(String n) {
        this.n = n;
    }

    @JsonProperty("kid")
    public String getKid() {
        return kid;
    }

    @JsonProperty("kid")
    public void setKid(String kid) {
        this.kid = kid;
    }

    @JsonProperty("kty")
    public String getKty() {
        return kty;
    }

    @JsonProperty("kty")
    public void setKty(String kty) {
        this.kty = kty;
    }

    @JsonProperty("use")
    public String getUse() {
        return use;
    }

    @JsonProperty("use")
    public void setUse(String use) {
        this.use = use;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(alg).append(e).append(additionalProperties).append(kty).append(n).append(use).append(kid).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof KeyDto) == false) {
            return false;
        }
        KeyDto rhs = ((KeyDto) other);
        return new EqualsBuilder().append(alg, rhs.alg).append(e, rhs.e).append(additionalProperties, rhs.additionalProperties).append(kty, rhs.kty).append(n, rhs.n).append(use, rhs.use).append(kid, rhs.kid).isEquals();
    }

}
