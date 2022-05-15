package ddss.device.api;

import ddss.device.proto.SensorsData;

public class StorageInteractionController {

    private static final SensorsData protoSensData = SensorsData.newBuilder()
            .setDegreesCelsius(44)
            .setPascals(12)
            .setMetersPerSecond(13).build();
    private static final Data dataSensData = new Data(protoSensData.toByteArray());

}
