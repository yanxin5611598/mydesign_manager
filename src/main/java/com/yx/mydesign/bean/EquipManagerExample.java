package com.yx.mydesign.bean;

import java.util.ArrayList;
import java.util.List;

public class EquipManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipManagerExample() {
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

        public Criteria andEquipmanagernameIsNull() {
            addCriterion("equipmanagername is null");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameIsNotNull() {
            addCriterion("equipmanagername is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameEqualTo(String value) {
            addCriterion("equipmanagername =", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameNotEqualTo(String value) {
            addCriterion("equipmanagername <>", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameGreaterThan(String value) {
            addCriterion("equipmanagername >", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameGreaterThanOrEqualTo(String value) {
            addCriterion("equipmanagername >=", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameLessThan(String value) {
            addCriterion("equipmanagername <", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameLessThanOrEqualTo(String value) {
            addCriterion("equipmanagername <=", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameLike(String value) {
            addCriterion("equipmanagername like", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameNotLike(String value) {
            addCriterion("equipmanagername not like", value, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameIn(List<String> values) {
            addCriterion("equipmanagername in", values, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameNotIn(List<String> values) {
            addCriterion("equipmanagername not in", values, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameBetween(String value1, String value2) {
            addCriterion("equipmanagername between", value1, value2, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagernameNotBetween(String value1, String value2) {
            addCriterion("equipmanagername not between", value1, value2, "equipmanagername");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordIsNull() {
            addCriterion("equipmanagerpassword is null");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordIsNotNull() {
            addCriterion("equipmanagerpassword is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordEqualTo(String value) {
            addCriterion("equipmanagerpassword =", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordNotEqualTo(String value) {
            addCriterion("equipmanagerpassword <>", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordGreaterThan(String value) {
            addCriterion("equipmanagerpassword >", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("equipmanagerpassword >=", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordLessThan(String value) {
            addCriterion("equipmanagerpassword <", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordLessThanOrEqualTo(String value) {
            addCriterion("equipmanagerpassword <=", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordLike(String value) {
            addCriterion("equipmanagerpassword like", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordNotLike(String value) {
            addCriterion("equipmanagerpassword not like", value, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordIn(List<String> values) {
            addCriterion("equipmanagerpassword in", values, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordNotIn(List<String> values) {
            addCriterion("equipmanagerpassword not in", values, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordBetween(String value1, String value2) {
            addCriterion("equipmanagerpassword between", value1, value2, "equipmanagerpassword");
            return (Criteria) this;
        }

        public Criteria andEquipmanagerpasswordNotBetween(String value1, String value2) {
            addCriterion("equipmanagerpassword not between", value1, value2, "equipmanagerpassword");
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