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

import java.util.Iterator;
import java.util.List;

import javolution.context.ObjectFactory;

/**
 * Encapsulates a list of EntityConditions to be used as a single EntityCondition combined as specified
 *
 */
public class EntityConditionList<T extends EntityCondition> extends EntityConditionListBase<T> {
    public static final String module = EntityConditionList.class.getName();

    protected static final ObjectFactory<EntityConditionList> entityConditionListFactory = new ObjectFactory<EntityConditionList>() {
        protected EntityConditionList create() {
            return new EntityConditionList();
        }
    };

    protected EntityConditionList() {
        super();
    }

    /** @deprecated Use EntityCondition.makeCondition() instead */
    public EntityConditionList(EntityJoinOperator operator, T... conditionList) {
        init(operator, conditionList);
    }

    /** @deprecated Use EntityCondition.makeCondition() instead */
    public EntityConditionList(List<T> conditionList, EntityJoinOperator operator) {
        init(conditionList, operator);
    }

    public int getConditionListSize() {
        return super.getConditionListSize();
    }

    public Iterator<T> getConditionIterator() {
        return super.getConditionIterator();
    }

    public void accept(EntityConditionVisitor visitor) {
        visitor.acceptEntityConditionList(this);
    }
}
