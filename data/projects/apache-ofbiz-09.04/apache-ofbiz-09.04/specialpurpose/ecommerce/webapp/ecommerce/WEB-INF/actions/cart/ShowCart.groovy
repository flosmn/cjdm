/*
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
 */

import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.product.catalog.CatalogWorker;
import org.ofbiz.order.shoppingcart.product.ProductDisplayWorker;
import org.ofbiz.order.shoppingcart.ShoppingCartEvents;
import org.ofbiz.entity.condition.*;

// Get the Cart and Prepare Size
shoppingCart = ShoppingCartEvents.getCartObject(request);
context.shoppingCartSize = shoppingCart?.size() ?: 0;
context.shoppingCart = shoppingCart;

if (parameters.add_product_id) { // check if a parameter is passed
    add_product_id = parameters.add_product_id;
    product = delegator.findByPrimaryKeyCache("Product", [productId : add_product_id]);
    context.product = product;
}

// get all the possible gift wrap options
allgiftWraps = delegator.findByAnd("ProductFeature", [productFeatureTypeId : "GIFT_WRAP"], ["defaultSequenceNum"]);
context.allgiftWraps = allgiftWraps;

// get the shopping lists for the logged in user
if (userLogin) {
    exprList = [EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.partyId),
                EntityCondition.makeCondition("listName", EntityOperator.NOT_EQUAL, "auto-save")];
    condition = EntityCondition.makeCondition(exprList, EntityOperator.AND);
    allShoppingLists = delegator.findList("ShoppingList", condition, null, ["listName"], null, false);
    context.shoppingLists = allShoppingLists;
}

// Get Cart Associated Products Data
associatedProducts = ProductDisplayWorker.getRandomCartProductAssoc(request, true);
context.associatedProducts = associatedProducts;

context.contentPathPrefix = CatalogWorker.getContentPathPrefix(request);
