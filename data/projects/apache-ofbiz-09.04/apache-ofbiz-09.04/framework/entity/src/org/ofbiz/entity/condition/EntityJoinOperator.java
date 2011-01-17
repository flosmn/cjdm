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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntity;
import org.ofbiz.entity.GenericModelException;
import org.ofbiz.entity.config.DatasourceInfo;
import org.ofbiz.entity.model.ModelEntity;

/**
 * Encapsulates operations between entities and entity fields. This is a immutable class.
 *
 */
public class EntityJoinOperator extends EntityOperator<Boolean> {

    protected boolean shortCircuitValue;

    protected EntityJoinOperator(int id, String code, boolean shortCircuitValue) {
        super(id, code);
        this.shortCircuitValue = shortCircuitValue;
    }

    public void addSqlValue(StringBuilder sql, ModelEntity modelEntity, List<EntityConditionParam> entityConditionParams, boolean compat, Object lhs, Object rhs, DatasourceInfo datasourceInfo) {
        sql.append('(');
        sql.append(((EntityCondition) lhs).makeWhereString(modelEntity, entityConditionParams, datasourceInfo));
        sql.append(' ');
        sql.append(getCode());
        sql.append(' ');
        if (rhs instanceof EntityCondition) {
            sql.append(((EntityCondition) rhs).makeWhereString(modelEntity, entityConditionParams, datasourceInfo));
        } else {
            addValue(sql, null, rhs, entityConditionParams);
        }
        sql.append(')');
    }

    public void addSqlValue(StringBuilder sql, ModelEntity modelEntity, List<EntityConditionParam> entityConditionParams, List<? extends EntityCondition> conditionList, DatasourceInfo datasourceInfo) {
        if (UtilValidate.isNotEmpty(conditionList)) {
            sql.append('(');
            Iterator<? extends EntityCondition> conditionIter = conditionList.iterator();
            while (conditionIter.hasNext()) {
                EntityCondition condition = conditionIter.next();
                sql.append(condition.makeWhereString(modelEntity, entityConditionParams, datasourceInfo));
                if (conditionIter.hasNext()) {
                    sql.append(' ');
                    sql.append(getCode());
                    sql.append(' ');
                }
            }
            sql.append(')');
        }
    }

    protected EntityCondition freeze(Object item) {
        return ((EntityCondition) item).freeze();
    }

    public EntityCondition freeze(Object lhs, Object rhs) {
        return EntityCondition.makeCondition(freeze(lhs), this, freeze(rhs));
    }

    public EntityCondition freeze(List<? extends EntityCondition> conditionList) {
        List<EntityCondition> newList = new ArrayList<EntityCondition>(conditionList.size());
        for (EntityCondition condition: conditionList) {
            newList.add(condition.freeze());
        }
        return EntityCondition.makeCondition(newList, this);
    }

    public void visit(EntityConditionVisitor visitor, List<? extends EntityCondition> conditionList) {
        if (UtilValidate.isNotEmpty(conditionList)) {
            for (EntityCondition condition: conditionList) {
                visitor.visit(condition);
            }
        }
    }

    public void visit(EntityConditionVisitor visitor, Object lhs, Object rhs) {
        ((EntityCondition) lhs).visit(visitor);
        visitor.visit(rhs);
    }

    public boolean entityMatches(GenericEntity entity, Object lhs, Object rhs) {
        return entityMatches(entity, (EntityCondition) lhs, (EntityCondition) rhs);
    }

    public Boolean eval(GenericEntity entity, EntityCondition lhs, EntityCondition rhs) {
        return entityMatches(entity, lhs, rhs) ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean entityMatches(GenericEntity entity, EntityCondition lhs, EntityCondition rhs) {
        if (lhs.entityMatches(entity) == shortCircuitValue) return shortCircuitValue;
        if (rhs.entityMatches(entity) == shortCircuitValue) return shortCircuitValue;
        return !shortCircuitValue;
    }

    public boolean entityMatches(GenericEntity entity, List<? extends EntityCondition> conditionList) {
        return mapMatches(entity.getDelegator(), entity, conditionList);
    }

    public Boolean eval(GenericDelegator delegator, Map<String, ? extends Object> map, Object lhs, Object rhs) {
        return castBoolean(mapMatches(delegator, map, lhs, rhs));
    }

    public boolean mapMatches(GenericDelegator delegator, Map<String, ? extends Object> map, Object lhs, Object rhs) {
        if (((EntityCondition) lhs).mapMatches(delegator, map) == shortCircuitValue) return shortCircuitValue;
        if (((EntityCondition) rhs).mapMatches(delegator, map) == shortCircuitValue) return shortCircuitValue;
        return !shortCircuitValue;
    }

    public Boolean eval(GenericDelegator delegator, Map<String, ? extends Object> map, List<? extends EntityCondition> conditionList) {
        return castBoolean(mapMatches(delegator, map, conditionList));
    }

    public boolean mapMatches(GenericDelegator delegator, Map<String, ? extends Object> map, List<? extends EntityCondition> conditionList) {
        if (UtilValidate.isNotEmpty(conditionList)) {
            for (EntityCondition condition: conditionList) {
                if (condition.mapMatches(delegator, map) == shortCircuitValue) return shortCircuitValue;
            }
        }
        return !shortCircuitValue;
    }

    public void validateSql(ModelEntity modelEntity, Object lhs, Object rhs) throws GenericModelException {
        validateSql(modelEntity, (EntityCondition) lhs, (EntityCondition) rhs);
    }

    public void validateSql(ModelEntity modelEntity, EntityCondition lhs, EntityCondition rhs) throws GenericModelException {
        lhs.checkCondition(modelEntity);
        rhs.checkCondition(modelEntity);
    }

    public void validateSql(ModelEntity modelEntity, List<? extends EntityCondition> conditionList) throws GenericModelException {
        if (conditionList == null) {
            throw new GenericModelException("Condition list is null");
        }
        for (EntityCondition condition: conditionList) {
            condition.checkCondition(modelEntity);
        }
    }
}
