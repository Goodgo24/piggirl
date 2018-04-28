package com.joe.pojo;

import com.common.BaseExample;
import com.common.util.PageMySql;
import java.util.ArrayList;
import java.util.List;

public class ExpressExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageMySql page;

    public ExpressExample() {
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

    public void setPage(PageMySql page) {
        this.page=page;
    }

    public PageMySql getPage() {
        return page;
    }

    public void addSql(String conditionSQL) {
        if (oredCriteria.size() != 0) {oredCriteria.get(0).addCriterion(conditionSQL);}
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

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Integer value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Integer value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Integer value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Integer value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Integer> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Integer> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andExpressNumIsNull() {
            addCriterion("express_num is null");
            return (Criteria) this;
        }

        public Criteria andExpressNumIsNotNull() {
            addCriterion("express_num is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNumEqualTo(Integer value) {
            addCriterion("express_num =", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotEqualTo(Integer value) {
            addCriterion("express_num <>", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumGreaterThan(Integer value) {
            addCriterion("express_num >", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_num >=", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumLessThan(Integer value) {
            addCriterion("express_num <", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumLessThanOrEqualTo(Integer value) {
            addCriterion("express_num <=", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumIn(List<Integer> values) {
            addCriterion("express_num in", values, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotIn(List<Integer> values) {
            addCriterion("express_num not in", values, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumBetween(Integer value1, Integer value2) {
            addCriterion("express_num between", value1, value2, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotBetween(Integer value1, Integer value2) {
            addCriterion("express_num not between", value1, value2, "expressNum");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(Integer value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(Integer value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(Integer value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(Integer value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(Integer value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(Integer value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<Integer> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<Integer> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(Integer value1, Integer value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(Integer value1, Integer value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andOtherContactIsNull() {
            addCriterion("other_contact is null");
            return (Criteria) this;
        }

        public Criteria andOtherContactIsNotNull() {
            addCriterion("other_contact is not null");
            return (Criteria) this;
        }

        public Criteria andOtherContactEqualTo(String value) {
            addCriterion("other_contact =", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactNotEqualTo(String value) {
            addCriterion("other_contact <>", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactGreaterThan(String value) {
            addCriterion("other_contact >", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactGreaterThanOrEqualTo(String value) {
            addCriterion("other_contact >=", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactLessThan(String value) {
            addCriterion("other_contact <", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactLessThanOrEqualTo(String value) {
            addCriterion("other_contact <=", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactLike(String value) {
            addCriterion("other_contact like", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactNotLike(String value) {
            addCriterion("other_contact not like", value, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactIn(List<String> values) {
            addCriterion("other_contact in", values, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactNotIn(List<String> values) {
            addCriterion("other_contact not in", values, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactBetween(String value1, String value2) {
            addCriterion("other_contact between", value1, value2, "otherContact");
            return (Criteria) this;
        }

        public Criteria andOtherContactNotBetween(String value1, String value2) {
            addCriterion("other_contact not between", value1, value2, "otherContact");
            return (Criteria) this;
        }

        public Criteria andExpressTrailIsNull() {
            addCriterion("express_trail is null");
            return (Criteria) this;
        }

        public Criteria andExpressTrailIsNotNull() {
            addCriterion("express_trail is not null");
            return (Criteria) this;
        }

        public Criteria andExpressTrailEqualTo(String value) {
            addCriterion("express_trail =", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailNotEqualTo(String value) {
            addCriterion("express_trail <>", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailGreaterThan(String value) {
            addCriterion("express_trail >", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailGreaterThanOrEqualTo(String value) {
            addCriterion("express_trail >=", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailLessThan(String value) {
            addCriterion("express_trail <", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailLessThanOrEqualTo(String value) {
            addCriterion("express_trail <=", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailLike(String value) {
            addCriterion("express_trail like", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailNotLike(String value) {
            addCriterion("express_trail not like", value, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailIn(List<String> values) {
            addCriterion("express_trail in", values, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailNotIn(List<String> values) {
            addCriterion("express_trail not in", values, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailBetween(String value1, String value2) {
            addCriterion("express_trail between", value1, value2, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressTrailNotBetween(String value1, String value2) {
            addCriterion("express_trail not between", value1, value2, "expressTrail");
            return (Criteria) this;
        }

        public Criteria andExpressStateIsNull() {
            addCriterion("express_state is null");
            return (Criteria) this;
        }

        public Criteria andExpressStateIsNotNull() {
            addCriterion("express_state is not null");
            return (Criteria) this;
        }

        public Criteria andExpressStateEqualTo(Short value) {
            addCriterion("express_state =", value, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateNotEqualTo(Short value) {
            addCriterion("express_state <>", value, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateGreaterThan(Short value) {
            addCriterion("express_state >", value, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateGreaterThanOrEqualTo(Short value) {
            addCriterion("express_state >=", value, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateLessThan(Short value) {
            addCriterion("express_state <", value, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateLessThanOrEqualTo(Short value) {
            addCriterion("express_state <=", value, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateIn(List<Short> values) {
            addCriterion("express_state in", values, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateNotIn(List<Short> values) {
            addCriterion("express_state not in", values, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateBetween(Short value1, Short value2) {
            addCriterion("express_state between", value1, value2, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressStateNotBetween(Short value1, Short value2) {
            addCriterion("express_state not between", value1, value2, "expressState");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNull() {
            addCriterion("express_company is null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNotNull() {
            addCriterion("express_company is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyEqualTo(String value) {
            addCriterion("express_company =", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotEqualTo(String value) {
            addCriterion("express_company <>", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThan(String value) {
            addCriterion("express_company >", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("express_company >=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThan(String value) {
            addCriterion("express_company <", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThanOrEqualTo(String value) {
            addCriterion("express_company <=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLike(String value) {
            addCriterion("express_company like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotLike(String value) {
            addCriterion("express_company not like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIn(List<String> values) {
            addCriterion("express_company in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotIn(List<String> values) {
            addCriterion("express_company not in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyBetween(String value1, String value2) {
            addCriterion("express_company between", value1, value2, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotBetween(String value1, String value2) {
            addCriterion("express_company not between", value1, value2, "expressCompany");
            return (Criteria) this;
        }
    }

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