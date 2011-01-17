/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.ofbiz.entity.condition;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javolution.context.ObjectFactory;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.ObjectType;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.EntityCryptoException;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntity;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericModelException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.config.DatasourceInfo;
import org.ofbiz.entity.model.ModelEntity;
import org.ofbiz.entity.model.ModelField;
import org.ofbiz.entity.model.ModelFieldType;
import org.ofbiz.entity.model.ModelFieldTypeReader;

/**
 * Encapsulates simple expressions used for specifying queries
 *
 */
public class EntityExpr extends EntityCondition {
    public static final String module = EntityExpr.class.getName();

    protected static final ObjectFactory<EntityExpr> entityExprFactory = new ObjectFactory<EntityExpr>() {
        protected EntityExpr create() {
            return new EntityExpr();
        }
    };

    private Object lhs = null;
    private EntityOperator<?> operator = null;
    private Object rhs = null;

    protected EntityExpr() {}

    /** @deprecated Use EntityCondition.makeCondition() instead */
    public EntityExpr(Object lhs, EntityComparisonOperator operator, Object rhs) {
        this.init(lhs, operator, rhs);
    }

    /** @deprecated Use EntityCondition.makeCondition() instead */
    public EntityExpr(String lhs, EntityComparisonOperator operator, Object rhs) {
        this.init(lhs, operator, rhs);
    }

    /** @deprecated Use EntityCondition.makeCondition() instead */
    public EntityExpr(String lhs, boolean leftUpper, EntityComparisonOperator operator, Object rhs, boolean rightUpper) {
        this.init(leftUpper ? EntityFunction.UPPER_FIELD(lhs) : lhs, operator, rightUpper ? EntityFunction.UPPER(rhs) : rhs);
    }

    /** @deprecated Use EntityCondition.makeCondition() instead */
    public EntityExpr(EntityCondition lhs, EntityJoinOperator operator, EntityCondition rhs) {
        this.init(lhs, operator, rhs);
    }

    public void init(Object lhs, EntityComparisonOperator operator, Object rhs) {
        if (lhs == null) {
            throw new IllegalArgumentException("The field name/value cannot be null");
        }
        if (operator == null) {
            throw new IllegalArgumentException("The operator argument cannot be null");
        }

        if (rhs == null || rhs == GenericEntity.NULL_FIELD) {
            if (!EntityOperator.NOT_EQUAL.equals(operator) && !EntityOperator.EQUALS.equals(operator)) {
                throw new IllegalArgumentException("Operator must be EQUALS or NOT_EQUAL when right/rhs argument is NULL ");
            }
        }

        if (EntityOperator.BETWEEN.equals(operator)) {
            if (!(rhs instanceof Collection) || (((Collection) rhs).size() != 2)) {
                throw new IllegalArgumentException("BETWEEN Operator requires a Collection with 2 elements for the right/rhs argument");
            }
        }

        if (lhs instanceof String) {
            this.lhs = EntityFieldValue.makeFieldValue((String) lhs);
        } else {
            this.lhs = lhs;
        }
        this.operator = operator;
        this.rhs = rhs;

        //Debug.logInfo("new EntityExpr internal field=" + lhs + ", value=" + rhs + ", value type=" + (rhs == null ? "null object" : rhs.getClass().getName()), module);
    }

    public void init(EntityCondition lhs, EntityJoinOperator operator, EntityCondition rhs) {
        if (lhs == null) {
            throw new IllegalArgumentException("The left EntityCondition argument cannot be null");
        }
        if (rhs == null) {
            throw new IllegalArgumentException("The right EntityCondition argument cannot be null");
        }
        if (operator == null) {
            throw new IllegalArgumentException("The operator argument cannot be null");
        }

        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    public void reset() {
        this.lhs = null;
        this.operator = null;
        this.rhs = null;
    }

    /** @deprecated */
    public void setLUpper(boolean upper) {
    }

    /** @deprecated */
    public boolean isLUpper() {
        return lhs instanceof EntityFunction.UPPER;
    }

    /** @deprecated */
    public boolean isRUpper() {
        return rhs instanceof EntityFunction.UPPER;
    }

    /** @deprecated */
    public void setRUpper(boolean upper) {
    }

    public Object getLhs() {
        return lhs;
    }

    public EntityOperator getOperator() {
        return operator;
    }

    public Object getRhs() {
        return rhs;
    }

    public String makeWhereString(ModelEntity modelEntity, List<EntityConditionParam> entityConditionParams, DatasourceInfo datasourceInfo) {
        // if (Debug.verboseOn()) Debug.logVerbose("makeWhereString for entity " + modelEntity.getEntityName(), module);

        this.checkRhsType(modelEntity, null);

        StringBuilder sql = new StringBuilder();
        operator.addSqlValue(sql, modelEntity, entityConditionParams, true, lhs, rhs, datasourceInfo);
        return sql.toString();
    }

    public boolean mapMatches(GenericDelegator delegator, Map<String, ? extends Object> map) {
        return operator.mapMatches(delegator, map, lhs, rhs);
    }

    public void checkCondition(ModelEntity modelEntity) throws GenericModelException {
        // if (Debug.verboseOn()) Debug.logVerbose("checkCondition for entity " + modelEntity.getEntityName(), module);
        if (lhs instanceof EntityCondition) {
            ((EntityCondition) lhs).checkCondition(modelEntity);
            ((EntityCondition) rhs).checkCondition(modelEntity);
        }
    }

    protected void addValue(StringBuilder buffer, ModelField field, Object value, List<EntityConditionParam> params) {
        if (rhs instanceof EntityFunction.UPPER) {
            if (value instanceof String) {
                value = ((String) value).toUpperCase();
            }
        }
        super.addValue(buffer, field, value, params);
    }

    public EntityCondition freeze() {
        return operator.freeze(lhs, rhs);
    }

    public void encryptConditionFields(ModelEntity modelEntity, GenericDelegator delegator) {
        if (this.lhs instanceof String) {
            ModelField modelField = modelEntity.getField((String) this.lhs);
            if (modelField != null && modelField.getEncrypt()) {
                if (!(rhs instanceof EntityConditionValue)) {
                    try {
                        this.rhs = delegator.encryptFieldValue(modelEntity.getEntityName(), this.rhs);
                    } catch (EntityCryptoException e) {
                        Debug.logWarning(e, "Error encrypting field [" + modelEntity.getEntityName() + "." + modelField.getName() + "] with value: " + this.rhs, module);
                    }
                }
            }
        }
    }

    public void visit(EntityConditionVisitor visitor) {
        visitor.acceptEntityOperator(operator, lhs, rhs);
    }

    public void accept(EntityConditionVisitor visitor) {
        visitor.acceptEntityExpr(this);
    }

    public void checkRhsType(ModelEntity modelEntity, GenericDelegator delegator) {
        if (this.rhs == null || this.rhs == GenericEntity.NULL_FIELD || modelEntity == null) return;

        Object value = this.rhs;
        if (this.rhs instanceof EntityFunction) {
            value = ((EntityFunction) this.rhs).getOriginalValue();
        }

        if (value instanceof Collection) {
            Collection valueCol = (Collection) value;
            if (valueCol.size() > 0) {
                value = valueCol.iterator().next();
            }
        }

        if (delegator == null) {
            // this will be the common case for now as the delegator isn't available where we want to do this
            // we'll cheat a little here and assume the default delegator
            delegator = GenericDelegator.getGenericDelegator("default");
        }

        String fieldName = null;
        if (this.lhs instanceof EntityFieldValue) {
            EntityFieldValue efv = (EntityFieldValue) this.lhs;
            fieldName = efv.getFieldName();
        } else {
            // nothing to check
            return;
        }

        ModelField curField = modelEntity.getField(fieldName);
        if (UtilValidate.isEmpty(curField)) {
            throw new IllegalArgumentException("FieldName " + fieldName + " not found for entity: " + modelEntity.getEntityName());
        }
        ModelFieldType type = null;
        try {
            type = delegator.getEntityFieldType(modelEntity, curField.getType());
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }
        if (type == null) {
            throw new IllegalArgumentException("Type " + curField.getType() + " not found for entity [" + modelEntity.getEntityName() + "]; probably because there is no datasource (helper) setup for the entity group that this entity is in: [" + delegator.getEntityGroupName(modelEntity.getEntityName()) + "]");
        }

        // make sure the type matches the field Java type
        if (!ObjectType.instanceOf(value, type.getJavaType())) {
            String errMsg = "In entity field [" + modelEntity.getEntityName() + "." + curField.getName() + "] set the value passed in [" + value.getClass().getName() + "] is not compatible with the Java type of the field [" + type.getJavaType() + "]";
            // eventually we should do this, but for now we'll do a "soft" failure: throw new IllegalArgumentException(errMsg);
            Debug.logWarning(new Exception("Location of database type warning"), "=-=-=-=-=-=-=-=-= Database type warning in EntityExpr =-=-=-=-=-=-=-=-= " + errMsg, module);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EntityExpr)) return false;
        EntityExpr other = (EntityExpr) obj;
        boolean isEqual = equals(lhs, other.lhs) &&
               equals(operator, other.operator) &&
               equals(rhs, other.rhs);
        //if (!isEqual) {
        //    Debug.logWarning("EntityExpr.equals is false for: \n-this.lhs=" + this.lhs + "; other.lhs=" + other.lhs +
        //            "\nthis.operator=" + this.operator + "; other.operator=" + other.operator +
        //            "\nthis.rhs=" + this.rhs + "other.rhs=" + other.rhs, module);
        //}
        return isEqual;
    }

    public int hashCode() {
        return hashCode(lhs) +
               hashCode(operator) +
               hashCode(rhs);
    }
}
