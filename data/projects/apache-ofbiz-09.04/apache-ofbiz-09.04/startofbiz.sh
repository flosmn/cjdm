#!/bin/sh
#####################################################################
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#####################################################################

# shutdown settings
ADMIN_PORT=10523
ADMIN_KEY=so3du5kasd5dn

# console log file
OFBIZ_LOG=runtime/logs/console.log

# delete the last log
rm -f $OFBIZ_LOG

# VM args
ADMIN="-Dofbiz.admin.port=$ADMIN_PORT -Dofbiz.admin.key=$ADMIN_KEY"
#DEBUG="-Dsun.rmi.server.exceptionTrace=true"
#automatic IP address for linux
#IPADDR=`/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}'`
#RMIIF="-Djava.rmi.server.hostname=$IPADDR"
MEMIF="-Xms128M -Xmx512M"
#MISC="-Duser.language=en"
VMARGS="$MEMIF $MISC $DEBUG $RMIIF $ADMIN"

# Worldpay Config
#VMARGS="-Xbootclasspath/p:applications/accounting/lib/cryptix.jar $VMARGS"

# location of java executable
if [ -f "$JAVA_HOME/bin/java" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  JAVA=java
fi

# start ofbiz
#$JAVA $VMARGS -jar ofbiz.jar $* >>$OFBIZ_LOG 2>>$OFBIZ_LOG&
exec "$JAVA" $VMARGS -jar ofbiz.jar "$@"

