package com.yx.mydesign.bean;

import java.util.ArrayList;
import java.util.List;

public class UserCheckResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserCheckResultExample() {
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

        public Criteria andTemIsNull() {
            addCriterion("tem is null");
            return (Criteria) this;
        }

        public Criteria andTemIsNotNull() {
            addCriterion("tem is not null");
            return (Criteria) this;
        }

        public Criteria andTemEqualTo(String value) {
            addCriterion("tem =", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemNotEqualTo(String value) {
            addCriterion("tem <>", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemGreaterThan(String value) {
            addCriterion("tem >", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemGreaterThanOrEqualTo(String value) {
            addCriterion("tem >=", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemLessThan(String value) {
            addCriterion("tem <", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemLessThanOrEqualTo(String value) {
            addCriterion("tem <=", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemLike(String value) {
            addCriterion("tem like", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemNotLike(String value) {
            addCriterion("tem not like", value, "tem");
            return (Criteria) this;
        }

        public Criteria andTemIn(List<String> values) {
            addCriterion("tem in", values, "tem");
            return (Criteria) this;
        }

        public Criteria andTemNotIn(List<String> values) {
            addCriterion("tem not in", values, "tem");
            return (Criteria) this;
        }

        public Criteria andTemBetween(String value1, String value2) {
            addCriterion("tem between", value1, value2, "tem");
            return (Criteria) this;
        }

        public Criteria andTemNotBetween(String value1, String value2) {
            addCriterion("tem not between", value1, value2, "tem");
            return (Criteria) this;
        }

        public Criteria andHumIsNull() {
            addCriterion("hum is null");
            return (Criteria) this;
        }

        public Criteria andHumIsNotNull() {
            addCriterion("hum is not null");
            return (Criteria) this;
        }

        public Criteria andHumEqualTo(String value) {
            addCriterion("hum =", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumNotEqualTo(String value) {
            addCriterion("hum <>", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumGreaterThan(String value) {
            addCriterion("hum >", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumGreaterThanOrEqualTo(String value) {
            addCriterion("hum >=", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumLessThan(String value) {
            addCriterion("hum <", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumLessThanOrEqualTo(String value) {
            addCriterion("hum <=", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumLike(String value) {
            addCriterion("hum like", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumNotLike(String value) {
            addCriterion("hum not like", value, "hum");
            return (Criteria) this;
        }

        public Criteria andHumIn(List<String> values) {
            addCriterion("hum in", values, "hum");
            return (Criteria) this;
        }

        public Criteria andHumNotIn(List<String> values) {
            addCriterion("hum not in", values, "hum");
            return (Criteria) this;
        }

        public Criteria andHumBetween(String value1, String value2) {
            addCriterion("hum between", value1, value2, "hum");
            return (Criteria) this;
        }

        public Criteria andHumNotBetween(String value1, String value2) {
            addCriterion("hum not between", value1, value2, "hum");
            return (Criteria) this;
        }

        public Criteria andChohIsNull() {
            addCriterion("choh is null");
            return (Criteria) this;
        }

        public Criteria andChohIsNotNull() {
            addCriterion("choh is not null");
            return (Criteria) this;
        }

        public Criteria andChohEqualTo(String value) {
            addCriterion("choh =", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohNotEqualTo(String value) {
            addCriterion("choh <>", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohGreaterThan(String value) {
            addCriterion("choh >", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohGreaterThanOrEqualTo(String value) {
            addCriterion("choh >=", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohLessThan(String value) {
            addCriterion("choh <", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohLessThanOrEqualTo(String value) {
            addCriterion("choh <=", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohLike(String value) {
            addCriterion("choh like", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohNotLike(String value) {
            addCriterion("choh not like", value, "choh");
            return (Criteria) this;
        }

        public Criteria andChohIn(List<String> values) {
            addCriterion("choh in", values, "choh");
            return (Criteria) this;
        }

        public Criteria andChohNotIn(List<String> values) {
            addCriterion("choh not in", values, "choh");
            return (Criteria) this;
        }

        public Criteria andChohBetween(String value1, String value2) {
            addCriterion("choh between", value1, value2, "choh");
            return (Criteria) this;
        }

        public Criteria andChohNotBetween(String value1, String value2) {
            addCriterion("choh not between", value1, value2, "choh");
            return (Criteria) this;
        }

        public Criteria andPm25IsNull() {
            addCriterion("pm25 is null");
            return (Criteria) this;
        }

        public Criteria andPm25IsNotNull() {
            addCriterion("pm25 is not null");
            return (Criteria) this;
        }

        public Criteria andPm25EqualTo(String value) {
            addCriterion("pm25 =", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25NotEqualTo(String value) {
            addCriterion("pm25 <>", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25GreaterThan(String value) {
            addCriterion("pm25 >", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25GreaterThanOrEqualTo(String value) {
            addCriterion("pm25 >=", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25LessThan(String value) {
            addCriterion("pm25 <", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25LessThanOrEqualTo(String value) {
            addCriterion("pm25 <=", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25Like(String value) {
            addCriterion("pm25 like", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25NotLike(String value) {
            addCriterion("pm25 not like", value, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25In(List<String> values) {
            addCriterion("pm25 in", values, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25NotIn(List<String> values) {
            addCriterion("pm25 not in", values, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25Between(String value1, String value2) {
            addCriterion("pm25 between", value1, value2, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm25NotBetween(String value1, String value2) {
            addCriterion("pm25 not between", value1, value2, "pm25");
            return (Criteria) this;
        }

        public Criteria andPm10IsNull() {
            addCriterion("pm10 is null");
            return (Criteria) this;
        }

        public Criteria andPm10IsNotNull() {
            addCriterion("pm10 is not null");
            return (Criteria) this;
        }

        public Criteria andPm10EqualTo(String value) {
            addCriterion("pm10 =", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10NotEqualTo(String value) {
            addCriterion("pm10 <>", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10GreaterThan(String value) {
            addCriterion("pm10 >", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10GreaterThanOrEqualTo(String value) {
            addCriterion("pm10 >=", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10LessThan(String value) {
            addCriterion("pm10 <", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10LessThanOrEqualTo(String value) {
            addCriterion("pm10 <=", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10Like(String value) {
            addCriterion("pm10 like", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10NotLike(String value) {
            addCriterion("pm10 not like", value, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10In(List<String> values) {
            addCriterion("pm10 in", values, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10NotIn(List<String> values) {
            addCriterion("pm10 not in", values, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10Between(String value1, String value2) {
            addCriterion("pm10 between", value1, value2, "pm10");
            return (Criteria) this;
        }

        public Criteria andPm10NotBetween(String value1, String value2) {
            addCriterion("pm10 not between", value1, value2, "pm10");
            return (Criteria) this;
        }

        public Criteria andAirrankIsNull() {
            addCriterion("airrank is null");
            return (Criteria) this;
        }

        public Criteria andAirrankIsNotNull() {
            addCriterion("airrank is not null");
            return (Criteria) this;
        }

        public Criteria andAirrankEqualTo(String value) {
            addCriterion("airrank =", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankNotEqualTo(String value) {
            addCriterion("airrank <>", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankGreaterThan(String value) {
            addCriterion("airrank >", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankGreaterThanOrEqualTo(String value) {
            addCriterion("airrank >=", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankLessThan(String value) {
            addCriterion("airrank <", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankLessThanOrEqualTo(String value) {
            addCriterion("airrank <=", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankLike(String value) {
            addCriterion("airrank like", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankNotLike(String value) {
            addCriterion("airrank not like", value, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankIn(List<String> values) {
            addCriterion("airrank in", values, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankNotIn(List<String> values) {
            addCriterion("airrank not in", values, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankBetween(String value1, String value2) {
            addCriterion("airrank between", value1, value2, "airrank");
            return (Criteria) this;
        }

        public Criteria andAirrankNotBetween(String value1, String value2) {
            addCriterion("airrank not between", value1, value2, "airrank");
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

        public Criteria andGpsinfoIsNull() {
            addCriterion("gpsInfo is null");
            return (Criteria) this;
        }

        public Criteria andGpsinfoIsNotNull() {
            addCriterion("gpsInfo is not null");
            return (Criteria) this;
        }

        public Criteria andGpsinfoEqualTo(String value) {
            addCriterion("gpsInfo =", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoNotEqualTo(String value) {
            addCriterion("gpsInfo <>", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoGreaterThan(String value) {
            addCriterion("gpsInfo >", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoGreaterThanOrEqualTo(String value) {
            addCriterion("gpsInfo >=", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoLessThan(String value) {
            addCriterion("gpsInfo <", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoLessThanOrEqualTo(String value) {
            addCriterion("gpsInfo <=", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoLike(String value) {
            addCriterion("gpsInfo like", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoNotLike(String value) {
            addCriterion("gpsInfo not like", value, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoIn(List<String> values) {
            addCriterion("gpsInfo in", values, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoNotIn(List<String> values) {
            addCriterion("gpsInfo not in", values, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoBetween(String value1, String value2) {
            addCriterion("gpsInfo between", value1, value2, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andGpsinfoNotBetween(String value1, String value2) {
            addCriterion("gpsInfo not between", value1, value2, "gpsinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoIsNull() {
            addCriterion("deviceInfo is null");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoIsNotNull() {
            addCriterion("deviceInfo is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoEqualTo(String value) {
            addCriterion("deviceInfo =", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotEqualTo(String value) {
            addCriterion("deviceInfo <>", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoGreaterThan(String value) {
            addCriterion("deviceInfo >", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoGreaterThanOrEqualTo(String value) {
            addCriterion("deviceInfo >=", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoLessThan(String value) {
            addCriterion("deviceInfo <", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoLessThanOrEqualTo(String value) {
            addCriterion("deviceInfo <=", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoLike(String value) {
            addCriterion("deviceInfo like", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotLike(String value) {
            addCriterion("deviceInfo not like", value, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoIn(List<String> values) {
            addCriterion("deviceInfo in", values, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotIn(List<String> values) {
            addCriterion("deviceInfo not in", values, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoBetween(String value1, String value2) {
            addCriterion("deviceInfo between", value1, value2, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andDeviceinfoNotBetween(String value1, String value2) {
            addCriterion("deviceInfo not between", value1, value2, "deviceinfo");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andRoominfoIsNull() {
            addCriterion("roomInfo is null");
            return (Criteria) this;
        }

        public Criteria andRoominfoIsNotNull() {
            addCriterion("roomInfo is not null");
            return (Criteria) this;
        }

        public Criteria andRoominfoEqualTo(String value) {
            addCriterion("roomInfo =", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoNotEqualTo(String value) {
            addCriterion("roomInfo <>", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoGreaterThan(String value) {
            addCriterion("roomInfo >", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoGreaterThanOrEqualTo(String value) {
            addCriterion("roomInfo >=", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoLessThan(String value) {
            addCriterion("roomInfo <", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoLessThanOrEqualTo(String value) {
            addCriterion("roomInfo <=", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoLike(String value) {
            addCriterion("roomInfo like", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoNotLike(String value) {
            addCriterion("roomInfo not like", value, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoIn(List<String> values) {
            addCriterion("roomInfo in", values, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoNotIn(List<String> values) {
            addCriterion("roomInfo not in", values, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoBetween(String value1, String value2) {
            addCriterion("roomInfo between", value1, value2, "roominfo");
            return (Criteria) this;
        }

        public Criteria andRoominfoNotBetween(String value1, String value2) {
            addCriterion("roomInfo not between", value1, value2, "roominfo");
            return (Criteria) this;
        }

        public Criteria andContentevaluateIsNull() {
            addCriterion("contentEvaluate is null");
            return (Criteria) this;
        }

        public Criteria andContentevaluateIsNotNull() {
            addCriterion("contentEvaluate is not null");
            return (Criteria) this;
        }

        public Criteria andContentevaluateEqualTo(String value) {
            addCriterion("contentEvaluate =", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateNotEqualTo(String value) {
            addCriterion("contentEvaluate <>", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateGreaterThan(String value) {
            addCriterion("contentEvaluate >", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateGreaterThanOrEqualTo(String value) {
            addCriterion("contentEvaluate >=", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateLessThan(String value) {
            addCriterion("contentEvaluate <", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateLessThanOrEqualTo(String value) {
            addCriterion("contentEvaluate <=", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateLike(String value) {
            addCriterion("contentEvaluate like", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateNotLike(String value) {
            addCriterion("contentEvaluate not like", value, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateIn(List<String> values) {
            addCriterion("contentEvaluate in", values, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateNotIn(List<String> values) {
            addCriterion("contentEvaluate not in", values, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateBetween(String value1, String value2) {
            addCriterion("contentEvaluate between", value1, value2, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andContentevaluateNotBetween(String value1, String value2) {
            addCriterion("contentEvaluate not between", value1, value2, "contentevaluate");
            return (Criteria) this;
        }

        public Criteria andImageinfoIsNull() {
            addCriterion("imageInfo is null");
            return (Criteria) this;
        }

        public Criteria andImageinfoIsNotNull() {
            addCriterion("imageInfo is not null");
            return (Criteria) this;
        }

        public Criteria andImageinfoEqualTo(String value) {
            addCriterion("imageInfo =", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoNotEqualTo(String value) {
            addCriterion("imageInfo <>", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoGreaterThan(String value) {
            addCriterion("imageInfo >", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoGreaterThanOrEqualTo(String value) {
            addCriterion("imageInfo >=", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoLessThan(String value) {
            addCriterion("imageInfo <", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoLessThanOrEqualTo(String value) {
            addCriterion("imageInfo <=", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoLike(String value) {
            addCriterion("imageInfo like", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoNotLike(String value) {
            addCriterion("imageInfo not like", value, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoIn(List<String> values) {
            addCriterion("imageInfo in", values, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoNotIn(List<String> values) {
            addCriterion("imageInfo not in", values, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoBetween(String value1, String value2) {
            addCriterion("imageInfo between", value1, value2, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImageinfoNotBetween(String value1, String value2) {
            addCriterion("imageInfo not between", value1, value2, "imageinfo");
            return (Criteria) this;
        }

        public Criteria andImagepathIsNull() {
            addCriterion("imagePath is null");
            return (Criteria) this;
        }

        public Criteria andImagepathIsNotNull() {
            addCriterion("imagePath is not null");
            return (Criteria) this;
        }

        public Criteria andImagepathEqualTo(String value) {
            addCriterion("imagePath =", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathNotEqualTo(String value) {
            addCriterion("imagePath <>", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathGreaterThan(String value) {
            addCriterion("imagePath >", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathGreaterThanOrEqualTo(String value) {
            addCriterion("imagePath >=", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathLessThan(String value) {
            addCriterion("imagePath <", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathLessThanOrEqualTo(String value) {
            addCriterion("imagePath <=", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathLike(String value) {
            addCriterion("imagePath like", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathNotLike(String value) {
            addCriterion("imagePath not like", value, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathIn(List<String> values) {
            addCriterion("imagePath in", values, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathNotIn(List<String> values) {
            addCriterion("imagePath not in", values, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathBetween(String value1, String value2) {
            addCriterion("imagePath between", value1, value2, "imagepath");
            return (Criteria) this;
        }

        public Criteria andImagepathNotBetween(String value1, String value2) {
            addCriterion("imagePath not between", value1, value2, "imagepath");
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