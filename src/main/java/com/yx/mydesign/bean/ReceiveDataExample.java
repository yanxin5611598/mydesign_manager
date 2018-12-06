package com.yx.mydesign.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReceiveDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReceiveDataExample() {
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

        public Criteria andReceiveidIsNull() {
            addCriterion("receiveID is null");
            return (Criteria) this;
        }

        public Criteria andReceiveidIsNotNull() {
            addCriterion("receiveID is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveidEqualTo(Integer value) {
            addCriterion("receiveID =", value, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidNotEqualTo(Integer value) {
            addCriterion("receiveID <>", value, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidGreaterThan(Integer value) {
            addCriterion("receiveID >", value, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiveID >=", value, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidLessThan(Integer value) {
            addCriterion("receiveID <", value, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidLessThanOrEqualTo(Integer value) {
            addCriterion("receiveID <=", value, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidIn(List<Integer> values) {
            addCriterion("receiveID in", values, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidNotIn(List<Integer> values) {
            addCriterion("receiveID not in", values, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidBetween(Integer value1, Integer value2) {
            addCriterion("receiveID between", value1, value2, "receiveid");
            return (Criteria) this;
        }

        public Criteria andReceiveidNotBetween(Integer value1, Integer value2) {
            addCriterion("receiveID not between", value1, value2, "receiveid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNull() {
            addCriterion("deviceID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("deviceID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(String value) {
            addCriterion("deviceID =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(String value) {
            addCriterion("deviceID <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(String value) {
            addCriterion("deviceID >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(String value) {
            addCriterion("deviceID >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(String value) {
            addCriterion("deviceID <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(String value) {
            addCriterion("deviceID <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLike(String value) {
            addCriterion("deviceID like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotLike(String value) {
            addCriterion("deviceID not like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<String> values) {
            addCriterion("deviceID in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<String> values) {
            addCriterion("deviceID not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(String value1, String value2) {
            addCriterion("deviceID between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(String value1, String value2) {
            addCriterion("deviceID not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andTempvalueIsNull() {
            addCriterion("tempValue is null");
            return (Criteria) this;
        }

        public Criteria andTempvalueIsNotNull() {
            addCriterion("tempValue is not null");
            return (Criteria) this;
        }

        public Criteria andTempvalueEqualTo(String value) {
            addCriterion("tempValue =", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueNotEqualTo(String value) {
            addCriterion("tempValue <>", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueGreaterThan(String value) {
            addCriterion("tempValue >", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueGreaterThanOrEqualTo(String value) {
            addCriterion("tempValue >=", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueLessThan(String value) {
            addCriterion("tempValue <", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueLessThanOrEqualTo(String value) {
            addCriterion("tempValue <=", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueLike(String value) {
            addCriterion("tempValue like", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueNotLike(String value) {
            addCriterion("tempValue not like", value, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueIn(List<String> values) {
            addCriterion("tempValue in", values, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueNotIn(List<String> values) {
            addCriterion("tempValue not in", values, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueBetween(String value1, String value2) {
            addCriterion("tempValue between", value1, value2, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andTempvalueNotBetween(String value1, String value2) {
            addCriterion("tempValue not between", value1, value2, "tempvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueIsNull() {
            addCriterion("humValue is null");
            return (Criteria) this;
        }

        public Criteria andHumvalueIsNotNull() {
            addCriterion("humValue is not null");
            return (Criteria) this;
        }

        public Criteria andHumvalueEqualTo(String value) {
            addCriterion("humValue =", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueNotEqualTo(String value) {
            addCriterion("humValue <>", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueGreaterThan(String value) {
            addCriterion("humValue >", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueGreaterThanOrEqualTo(String value) {
            addCriterion("humValue >=", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueLessThan(String value) {
            addCriterion("humValue <", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueLessThanOrEqualTo(String value) {
            addCriterion("humValue <=", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueLike(String value) {
            addCriterion("humValue like", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueNotLike(String value) {
            addCriterion("humValue not like", value, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueIn(List<String> values) {
            addCriterion("humValue in", values, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueNotIn(List<String> values) {
            addCriterion("humValue not in", values, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueBetween(String value1, String value2) {
            addCriterion("humValue between", value1, value2, "humvalue");
            return (Criteria) this;
        }

        public Criteria andHumvalueNotBetween(String value1, String value2) {
            addCriterion("humValue not between", value1, value2, "humvalue");
            return (Criteria) this;
        }

        public Criteria andPm10valueIsNull() {
            addCriterion("PM10Value is null");
            return (Criteria) this;
        }

        public Criteria andPm10valueIsNotNull() {
            addCriterion("PM10Value is not null");
            return (Criteria) this;
        }

        public Criteria andPm10valueEqualTo(String value) {
            addCriterion("PM10Value =", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueNotEqualTo(String value) {
            addCriterion("PM10Value <>", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueGreaterThan(String value) {
            addCriterion("PM10Value >", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueGreaterThanOrEqualTo(String value) {
            addCriterion("PM10Value >=", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueLessThan(String value) {
            addCriterion("PM10Value <", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueLessThanOrEqualTo(String value) {
            addCriterion("PM10Value <=", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueLike(String value) {
            addCriterion("PM10Value like", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueNotLike(String value) {
            addCriterion("PM10Value not like", value, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueIn(List<String> values) {
            addCriterion("PM10Value in", values, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueNotIn(List<String> values) {
            addCriterion("PM10Value not in", values, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueBetween(String value1, String value2) {
            addCriterion("PM10Value between", value1, value2, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm10valueNotBetween(String value1, String value2) {
            addCriterion("PM10Value not between", value1, value2, "pm10value");
            return (Criteria) this;
        }

        public Criteria andPm25valueIsNull() {
            addCriterion("PM25Value is null");
            return (Criteria) this;
        }

        public Criteria andPm25valueIsNotNull() {
            addCriterion("PM25Value is not null");
            return (Criteria) this;
        }

        public Criteria andPm25valueEqualTo(String value) {
            addCriterion("PM25Value =", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueNotEqualTo(String value) {
            addCriterion("PM25Value <>", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueGreaterThan(String value) {
            addCriterion("PM25Value >", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueGreaterThanOrEqualTo(String value) {
            addCriterion("PM25Value >=", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueLessThan(String value) {
            addCriterion("PM25Value <", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueLessThanOrEqualTo(String value) {
            addCriterion("PM25Value <=", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueLike(String value) {
            addCriterion("PM25Value like", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueNotLike(String value) {
            addCriterion("PM25Value not like", value, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueIn(List<String> values) {
            addCriterion("PM25Value in", values, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueNotIn(List<String> values) {
            addCriterion("PM25Value not in", values, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueBetween(String value1, String value2) {
            addCriterion("PM25Value between", value1, value2, "pm25value");
            return (Criteria) this;
        }

        public Criteria andPm25valueNotBetween(String value1, String value2) {
            addCriterion("PM25Value not between", value1, value2, "pm25value");
            return (Criteria) this;
        }

        public Criteria andHchovalueIsNull() {
            addCriterion("HCHOValue is null");
            return (Criteria) this;
        }

        public Criteria andHchovalueIsNotNull() {
            addCriterion("HCHOValue is not null");
            return (Criteria) this;
        }

        public Criteria andHchovalueEqualTo(String value) {
            addCriterion("HCHOValue =", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueNotEqualTo(String value) {
            addCriterion("HCHOValue <>", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueGreaterThan(String value) {
            addCriterion("HCHOValue >", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueGreaterThanOrEqualTo(String value) {
            addCriterion("HCHOValue >=", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueLessThan(String value) {
            addCriterion("HCHOValue <", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueLessThanOrEqualTo(String value) {
            addCriterion("HCHOValue <=", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueLike(String value) {
            addCriterion("HCHOValue like", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueNotLike(String value) {
            addCriterion("HCHOValue not like", value, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueIn(List<String> values) {
            addCriterion("HCHOValue in", values, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueNotIn(List<String> values) {
            addCriterion("HCHOValue not in", values, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueBetween(String value1, String value2) {
            addCriterion("HCHOValue between", value1, value2, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andHchovalueNotBetween(String value1, String value2) {
            addCriterion("HCHOValue not between", value1, value2, "hchovalue");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIsNull() {
            addCriterion("receiveTime is null");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIsNotNull() {
            addCriterion("receiveTime is not null");
            return (Criteria) this;
        }

        public Criteria andReceivetimeEqualTo(Date value) {
            addCriterionForJDBCDate("receiveTime =", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("receiveTime <>", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("receiveTime >", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("receiveTime >=", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLessThan(Date value) {
            addCriterionForJDBCDate("receiveTime <", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("receiveTime <=", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIn(List<Date> values) {
            addCriterionForJDBCDate("receiveTime in", values, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("receiveTime not in", values, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("receiveTime between", value1, value2, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("receiveTime not between", value1, value2, "receivetime");
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