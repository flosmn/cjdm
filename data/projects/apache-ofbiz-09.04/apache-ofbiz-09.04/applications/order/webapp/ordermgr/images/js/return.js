/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/

Event.observe(window, 'load', function() {
    Event.observe($('returnHeaderTypeId'), 'change', function() {
        changeStatusCorrespondingToHeaderType();
    });
});

function changeStatusCorrespondingToHeaderType() {
    var listOptions = [];
    new Ajax.Request('/ordermgr/control/getStatusItemsForReturn', {
        asynchronous: false,
        onSuccess: function(transport) {
            var data = transport.responseText.evalJSON(true);
            var statusItems = data.statusItems;
            statusItems.each( function(statusItem) {
                listOptions.push("<option value = " + statusItem.statusId + " > " + statusItem.description + " </option>");
            });
            $('statusId').update(listOptions);
        }, parameters: {returnHeaderTypeId: $F('returnHeaderTypeId')}, requestHeaders: {Accept: 'application/json'}
    });
}