package com.yx.mydesign.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andPlacefromIsNull() {
            addCriterion("placefrom is null");
            return (Criteria) this;
        }

        public Criteria andPlacefromIsNotNull() {
            addCriterion("placefrom is not null");
            return (Criteria) this;
        }

        public Criteria andPlacefromEqualTo(String value) {
            addCriterion("placefrom =", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromNotEqualTo(String value) {
            addCriterion("placefrom <>", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromGreaterThan(String value) {
            addCriterion("placefrom >", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromGreaterThanOrEqualTo(String value) {
            addCriterion("placefrom >=", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromLessThan(String value) {
            addCriterion("placefrom <", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromLessThanOrEqualTo(String value) {
            addCriterion("placefrom <=", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromLike(String value) {
            addCriterion("placefrom like", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromNotLike(String value) {
            addCriterion("placefrom not like", value, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromIn(List<String> values) {
            addCriterion("placefrom in", values, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromNotIn(List<String> values) {
            addCriterion("placefrom not in", values, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromBetween(String value1, String value2) {
            addCriterion("placefrom between", value1, value2, "placefrom");
            return (Criteria) this;
        }

        public Criteria andPlacefromNotBetween(String value1, String value2) {
            addCriterion("placefrom not between", value1, value2, "placefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromIsNull() {
            addCriterion("timefrom is null");
            return (Criteria) this;
        }

        public Criteria andTimefromIsNotNull() {
            addCriterion("timefrom is not null");
            return (Criteria) this;
        }

        public Criteria andTimefromEqualTo(Date value) {
            addCriterion("timefrom =", value, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromNotEqualTo(Date value) {
            addCriterion("timefrom <>", value, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromGreaterThan(Date value) {
            addCriterion("timefrom >", value, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromGreaterThanOrEqualTo(Date value) {
            addCriterion("timefrom >=", value, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromLessThan(Date value) {
            addCriterion("timefrom <", value, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromLessThanOrEqualTo(Date value) {
            addCriterion("timefrom <=", value, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromIn(List<Date> values) {
            addCriterion("timefrom in", values, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromNotIn(List<Date> values) {
            addCriterion("timefrom not in", values, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromBetween(Date value1, Date value2) {
            addCriterion("timefrom between", value1, value2, "timefrom");
            return (Criteria) this;
        }

        public Criteria andTimefromNotBetween(Date value1, Date value2) {
            addCriterion("timefrom not between", value1, value2, "timefrom");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentIsNull() {
            addCriterion("placecurrent is null");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentIsNotNull() {
            addCriterion("placecurrent is not null");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentEqualTo(String value) {
            addCriterion("placecurrent =", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentNotEqualTo(String value) {
            addCriterion("placecurrent <>", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentGreaterThan(String value) {
            addCriterion("placecurrent >", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentGreaterThanOrEqualTo(String value) {
            addCriterion("placecurrent >=", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentLessThan(String value) {
            addCriterion("placecurrent <", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentLessThanOrEqualTo(String value) {
            addCriterion("placecurrent <=", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentLike(String value) {
            addCriterion("placecurrent like", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentNotLike(String value) {
            addCriterion("placecurrent not like", value, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentIn(List<String> values) {
            addCriterion("placecurrent in", values, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentNotIn(List<String> values) {
            addCriterion("placecurrent not in", values, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentBetween(String value1, String value2) {
            addCriterion("placecurrent between", value1, value2, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andPlacecurrentNotBetween(String value1, String value2) {
            addCriterion("placecurrent not between", value1, value2, "placecurrent");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andTimecurrentIsNull() {
            addCriterion("timecurrent is null");
            return (Criteria) this;
        }

        public Criteria andTimecurrentIsNotNull() {
            addCriterion("timecurrent is not null");
            return (Criteria) this;
        }

        public Criteria andTimecurrentEqualTo(Date value) {
            addCriterion("timecurrent =", value, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentNotEqualTo(Date value) {
            addCriterion("timecurrent <>", value, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentGreaterThan(Date value) {
            addCriterion("timecurrent >", value, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentGreaterThanOrEqualTo(Date value) {
            addCriterion("timecurrent >=", value, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentLessThan(Date value) {
            addCriterion("timecurrent <", value, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentLessThanOrEqualTo(Date value) {
            addCriterion("timecurrent <=", value, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentIn(List<Date> values) {
            addCriterion("timecurrent in", values, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentNotIn(List<Date> values) {
            addCriterion("timecurrent not in", values, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentBetween(Date value1, Date value2) {
            addCriterion("timecurrent between", value1, value2, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andTimecurrentNotBetween(Date value1, Date value2) {
            addCriterion("timecurrent not between", value1, value2, "timecurrent");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
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