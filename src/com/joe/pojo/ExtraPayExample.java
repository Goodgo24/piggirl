package com.joe.pojo;

import com.common.BaseExample;
import com.common.util.PageMySql;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExtraPayExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PageMySql page;

    public ExtraPayExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andExtraPayIdIsNull() {
            addCriterion("extra_pay_id is null");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdIsNotNull() {
            addCriterion("extra_pay_id is not null");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdEqualTo(Integer value) {
            addCriterion("extra_pay_id =", value, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdNotEqualTo(Integer value) {
            addCriterion("extra_pay_id <>", value, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdGreaterThan(Integer value) {
            addCriterion("extra_pay_id >", value, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("extra_pay_id >=", value, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdLessThan(Integer value) {
            addCriterion("extra_pay_id <", value, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdLessThanOrEqualTo(Integer value) {
            addCriterion("extra_pay_id <=", value, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdIn(List<Integer> values) {
            addCriterion("extra_pay_id in", values, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdNotIn(List<Integer> values) {
            addCriterion("extra_pay_id not in", values, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdBetween(Integer value1, Integer value2) {
            addCriterion("extra_pay_id between", value1, value2, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andExtraPayIdNotBetween(Integer value1, Integer value2) {
            addCriterion("extra_pay_id not between", value1, value2, "extraPayId");
            return (Criteria) this;
        }

        public Criteria andPayForIsNull() {
            addCriterion("pay_for is null");
            return (Criteria) this;
        }

        public Criteria andPayForIsNotNull() {
            addCriterion("pay_for is not null");
            return (Criteria) this;
        }

        public Criteria andPayForEqualTo(String value) {
            addCriterion("pay_for =", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForNotEqualTo(String value) {
            addCriterion("pay_for <>", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForGreaterThan(String value) {
            addCriterion("pay_for >", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForGreaterThanOrEqualTo(String value) {
            addCriterion("pay_for >=", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForLessThan(String value) {
            addCriterion("pay_for <", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForLessThanOrEqualTo(String value) {
            addCriterion("pay_for <=", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForLike(String value) {
            addCriterion("pay_for like", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForNotLike(String value) {
            addCriterion("pay_for not like", value, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForIn(List<String> values) {
            addCriterion("pay_for in", values, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForNotIn(List<String> values) {
            addCriterion("pay_for not in", values, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForBetween(String value1, String value2) {
            addCriterion("pay_for between", value1, value2, "payFor");
            return (Criteria) this;
        }

        public Criteria andPayForNotBetween(String value1, String value2) {
            addCriterion("pay_for not between", value1, value2, "payFor");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andPayPepopleIsNull() {
            addCriterion("pay_pepople is null");
            return (Criteria) this;
        }

        public Criteria andPayPepopleIsNotNull() {
            addCriterion("pay_pepople is not null");
            return (Criteria) this;
        }

        public Criteria andPayPepopleEqualTo(Integer value) {
            addCriterion("pay_pepople =", value, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleNotEqualTo(Integer value) {
            addCriterion("pay_pepople <>", value, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleGreaterThan(Integer value) {
            addCriterion("pay_pepople >", value, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_pepople >=", value, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleLessThan(Integer value) {
            addCriterion("pay_pepople <", value, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleLessThanOrEqualTo(Integer value) {
            addCriterion("pay_pepople <=", value, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleIn(List<Integer> values) {
            addCriterion("pay_pepople in", values, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleNotIn(List<Integer> values) {
            addCriterion("pay_pepople not in", values, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleBetween(Integer value1, Integer value2) {
            addCriterion("pay_pepople between", value1, value2, "payPepople");
            return (Criteria) this;
        }

        public Criteria andPayPepopleNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_pepople not between", value1, value2, "payPepople");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
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