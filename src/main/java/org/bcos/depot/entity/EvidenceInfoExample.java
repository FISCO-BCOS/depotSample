package org.bcos.depot.entity;

import java.util.ArrayList;
import java.util.List;

public class EvidenceInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EvidenceInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIsNull() {
            addCriterion("block_height is null");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIsNotNull() {
            addCriterion("block_height is not null");
            return (Criteria) this;
        }

        public Criteria andBlockHeightEqualTo(Integer value) {
            addCriterion("block_height =", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotEqualTo(Integer value) {
            addCriterion("block_height <>", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightGreaterThan(Integer value) {
            addCriterion("block_height >", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("block_height >=", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightLessThan(Integer value) {
            addCriterion("block_height <", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightLessThanOrEqualTo(Integer value) {
            addCriterion("block_height <=", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIn(List<Integer> values) {
            addCriterion("block_height in", values, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotIn(List<Integer> values) {
            addCriterion("block_height not in", values, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightBetween(Integer value1, Integer value2) {
            addCriterion("block_height between", value1, value2, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("block_height not between", value1, value2, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andEventIsNull() {
            addCriterion("event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(String value) {
            addCriterion("event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(String value) {
            addCriterion("event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(String value) {
            addCriterion("event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(String value) {
            addCriterion("event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(String value) {
            addCriterion("event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(String value) {
            addCriterion("event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLike(String value) {
            addCriterion("event like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotLike(String value) {
            addCriterion("event not like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<String> values) {
            addCriterion("event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<String> values) {
            addCriterion("event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(String value1, String value2) {
            addCriterion("event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(String value1, String value2) {
            addCriterion("event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdIsNull() {
            addCriterion("evidence_id is null");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdIsNotNull() {
            addCriterion("evidence_id is not null");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdEqualTo(String value) {
            addCriterion("evidence_id =", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdNotEqualTo(String value) {
            addCriterion("evidence_id <>", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdGreaterThan(String value) {
            addCriterion("evidence_id >", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdGreaterThanOrEqualTo(String value) {
            addCriterion("evidence_id >=", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdLessThan(String value) {
            addCriterion("evidence_id <", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdLessThanOrEqualTo(String value) {
            addCriterion("evidence_id <=", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdLike(String value) {
            addCriterion("evidence_id like", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdNotLike(String value) {
            addCriterion("evidence_id not like", value, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdIn(List<String> values) {
            addCriterion("evidence_id in", values, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdNotIn(List<String> values) {
            addCriterion("evidence_id not in", values, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdBetween(String value1, String value2) {
            addCriterion("evidence_id between", value1, value2, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceIdNotBetween(String value1, String value2) {
            addCriterion("evidence_id not between", value1, value2, "evidenceId");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashIsNull() {
            addCriterion("evidence_hash is null");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashIsNotNull() {
            addCriterion("evidence_hash is not null");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashEqualTo(String value) {
            addCriterion("evidence_hash =", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashNotEqualTo(String value) {
            addCriterion("evidence_hash <>", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashGreaterThan(String value) {
            addCriterion("evidence_hash >", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashGreaterThanOrEqualTo(String value) {
            addCriterion("evidence_hash >=", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashLessThan(String value) {
            addCriterion("evidence_hash <", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashLessThanOrEqualTo(String value) {
            addCriterion("evidence_hash <=", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashLike(String value) {
            addCriterion("evidence_hash like", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashNotLike(String value) {
            addCriterion("evidence_hash not like", value, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashIn(List<String> values) {
            addCriterion("evidence_hash in", values, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashNotIn(List<String> values) {
            addCriterion("evidence_hash not in", values, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashBetween(String value1, String value2) {
            addCriterion("evidence_hash between", value1, value2, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andEvidenceHashNotBetween(String value1, String value2) {
            addCriterion("evidence_hash not between", value1, value2, "evidenceHash");
            return (Criteria) this;
        }

        public Criteria andSignDataIsNull() {
            addCriterion("sign_data is null");
            return (Criteria) this;
        }

        public Criteria andSignDataIsNotNull() {
            addCriterion("sign_data is not null");
            return (Criteria) this;
        }

        public Criteria andSignDataEqualTo(String value) {
            addCriterion("sign_data =", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotEqualTo(String value) {
            addCriterion("sign_data <>", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataGreaterThan(String value) {
            addCriterion("sign_data >", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataGreaterThanOrEqualTo(String value) {
            addCriterion("sign_data >=", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataLessThan(String value) {
            addCriterion("sign_data <", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataLessThanOrEqualTo(String value) {
            addCriterion("sign_data <=", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataLike(String value) {
            addCriterion("sign_data like", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotLike(String value) {
            addCriterion("sign_data not like", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataIn(List<String> values) {
            addCriterion("sign_data in", values, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotIn(List<String> values) {
            addCriterion("sign_data not in", values, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataBetween(String value1, String value2) {
            addCriterion("sign_data between", value1, value2, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotBetween(String value1, String value2) {
            addCriterion("sign_data not between", value1, value2, "signData");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressIsNull() {
            addCriterion("factory_address is null");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressIsNotNull() {
            addCriterion("factory_address is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressEqualTo(String value) {
            addCriterion("factory_address =", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressNotEqualTo(String value) {
            addCriterion("factory_address <>", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressGreaterThan(String value) {
            addCriterion("factory_address >", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressGreaterThanOrEqualTo(String value) {
            addCriterion("factory_address >=", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressLessThan(String value) {
            addCriterion("factory_address <", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressLessThanOrEqualTo(String value) {
            addCriterion("factory_address <=", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressLike(String value) {
            addCriterion("factory_address like", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressNotLike(String value) {
            addCriterion("factory_address not like", value, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressIn(List<String> values) {
            addCriterion("factory_address in", values, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressNotIn(List<String> values) {
            addCriterion("factory_address not in", values, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressBetween(String value1, String value2) {
            addCriterion("factory_address between", value1, value2, "factoryAddress");
            return (Criteria) this;
        }

        public Criteria andFactoryAddressNotBetween(String value1, String value2) {
            addCriterion("factory_address not between", value1, value2, "factoryAddress");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}