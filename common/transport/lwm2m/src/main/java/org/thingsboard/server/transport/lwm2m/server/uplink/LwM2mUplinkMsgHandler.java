/**
 * Copyright © 2016-2021 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.transport.lwm2m.server.uplink;

import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.server.registration.Registration;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceProfile;
import org.thingsboard.server.gen.transport.TransportProtos;
import org.thingsboard.server.transport.lwm2m.config.LwM2MTransportServerConfig;
import org.thingsboard.server.transport.lwm2m.server.client.LwM2mClient;
import org.thingsboard.server.transport.lwm2m.server.rpc.LwM2mClientRpcRequest;

import java.util.Collection;
import java.util.Optional;

public interface LwM2mUplinkMsgHandler {

    void onRegistered(Registration registration, Collection<Observation> previousObsersations);

    void updatedReg(Registration registration);

    void unReg(Registration registration, Collection<Observation> observations);

    void onSleepingDev(Registration registration);

    void onUpdateValueAfterReadResponse(Registration registration, String path, ReadResponse response, LwM2mClientRpcRequest rpcRequest);

    void onAttributeUpdate(TransportProtos.AttributeUpdateNotificationMsg msg, TransportProtos.SessionInfoProto sessionInfo);

    void onDeviceProfileUpdate(TransportProtos.SessionInfoProto sessionInfo, DeviceProfile deviceProfile);

    void onDeviceUpdate(TransportProtos.SessionInfoProto sessionInfo, Device device, Optional<DeviceProfile> deviceProfileOpt);

    void onResourceUpdate (Optional<TransportProtos.ResourceUpdateMsg> resourceUpdateMsgOpt);

    void onResourceDelete(Optional<TransportProtos.ResourceDeleteMsg> resourceDeleteMsgOpt);

    void doDisconnect(TransportProtos.SessionInfoProto sessionInfo);

    void onAwakeDev(Registration registration);

    void sendLogsToThingsboard(LwM2mClient client, String msg);

    void sendLogsToThingsboard(String registrationId, String msg);

    void onToTransportUpdateCredentials(TransportProtos.ToTransportUpdateCredentialsProto updateCredentials);

    String getObjectIdByKeyNameFromProfile(TransportProtos.SessionInfoProto sessionInfo, String name);

    String getObjectIdByKeyNameFromProfile(LwM2mClient lwM2mClient, String keyName);

    void onGetAttributesResponse(TransportProtos.GetAttributeResponseMsg attributesResponse, TransportProtos.SessionInfoProto sessionInfo);

    LwM2MTransportServerConfig getConfig();
}