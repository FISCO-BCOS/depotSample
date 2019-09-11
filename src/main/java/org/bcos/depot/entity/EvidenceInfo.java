package org.bcos.depot.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class EvidenceInfo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 交易所在块高
     */
    private Integer blockHeight;

    /**
     * event
     */
    private String event;

    /**
     * 证据ID
     */
    private String evidenceId;

    /**
     * 证据hash
     */
    private String evidenceHash;

    /**
     * 签名数据
     */
    private String signData;

    /**
     * 工厂合约地址
     */
    private String factoryAddress;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getEvidenceHash() {
        return evidenceHash;
    }

    public void setEvidenceHash(String evidenceHash) {
        this.evidenceHash = evidenceHash;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    public String getFactoryAddress() {
        return factoryAddress;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
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
        EvidenceInfo other = (EvidenceInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBlockHeight() == null ? other.getBlockHeight() == null : this.getBlockHeight().equals(other.getBlockHeight()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getEvidenceId() == null ? other.getEvidenceId() == null : this.getEvidenceId().equals(other.getEvidenceId()))
            && (this.getEvidenceHash() == null ? other.getEvidenceHash() == null : this.getEvidenceHash().equals(other.getEvidenceHash()))
            && (this.getSignData() == null ? other.getSignData() == null : this.getSignData().equals(other.getSignData()))
            && (this.getFactoryAddress() == null ? other.getFactoryAddress() == null : this.getFactoryAddress().equals(other.getFactoryAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBlockHeight() == null) ? 0 : getBlockHeight().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getEvidenceId() == null) ? 0 : getEvidenceId().hashCode());
        result = prime * result + ((getEvidenceHash() == null) ? 0 : getEvidenceHash().hashCode());
        result = prime * result + ((getSignData() == null) ? 0 : getSignData().hashCode());
        result = prime * result + ((getFactoryAddress() == null) ? 0 : getFactoryAddress().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", blockHeight=").append(blockHeight);
        sb.append(", event=").append(event);
        sb.append(", evidenceId=").append(evidenceId);
        sb.append(", evidenceHash=").append(evidenceHash);
        sb.append(", signData=").append(signData);
        sb.append(", factoryAddress=").append(factoryAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}