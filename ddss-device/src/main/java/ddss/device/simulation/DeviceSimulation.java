package ddss.device.simulation;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.device.domain.Data;
import ddss.device.proto.SensorsData;

public class DeviceSimulation {

    public static final SensorsData PROTO_SENSORS_DATA = SensorsData.newBuilder()
            .setDegreesCelsius(44)
            .setPascals(12)
            .setMetersPerSecond(13).build();
    public static final Data DATA = new Data(PROTO_SENSORS_DATA.toByteArray());

    public static String toString(Data data) {
        String stringDataBytes = data.getBytes();

        SensorsData parsedSensorsData = null;
        try {
            parsedSensorsData = SensorsData.parseFrom(stringDataBytes.getBytes());
        } catch (InvalidProtocolBufferException ignored) {
        }

        if (parsedSensorsData == null) return "";
        return "Температура (градусы Цельсия) ...... " + parsedSensorsData.getDegreesCelsius() + "\n" +
                "Давление (паскали) ................. " + parsedSensorsData.getPascals() + "\n" +
                "Скорость ветра (метры в секунду) ... " + parsedSensorsData.getMetersPerSecond();
    }
}
