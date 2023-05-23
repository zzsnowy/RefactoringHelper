package com.zz.edrt.eddetect.domain;

import java.io.Serializable;

public class SmellDetectRule implements Serializable {
    private String name;

    private String url;

    private Integer supportCount;

    private Double confidence;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(Integer supportCount) {
        this.supportCount = supportCount;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SmellDetectRule other = (SmellDetectRule) that;
        return (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getSupportCount() == null ? other.getSupportCount() == null : this.getSupportCount().equals(other.getSupportCount()))
            && (this.getConfidence() == null ? other.getConfidence() == null : this.getConfidence().equals(other.getConfidence()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getSupportCount() == null) ? 0 : getSupportCount().hashCode());
        result = prime * result + ((getConfidence() == null) ? 0 : getConfidence().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", supportCount=").append(supportCount);
        sb.append(", confidence=").append(confidence);
        sb.append("]");
        return sb.toString();
    }
}