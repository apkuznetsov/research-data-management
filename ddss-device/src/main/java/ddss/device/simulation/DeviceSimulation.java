package ddss.device.simulation;

import com.google.protobuf.InvalidProtocolBufferException;
import ddss.device.domain.Data;
import ddss.device.proto.SensorsData;

public class DeviceSimulation {

    private static final int DEFAULT_DEGREES_CELSIUS = 44;
    private static final int DEFAULT_PASCALS = 12;
    private static final int DEFAULT_METERS_PER_SECOND = 13;
    public static final SensorsData PROTO_SENSORS_DATA = SensorsData.newBuilder()
            .setDegreesCelsius(DEFAULT_DEGREES_CELSIUS)
            .setPascals(DEFAULT_PASCALS)
            .setMetersPerSecond(DEFAULT_METERS_PER_SECOND).build();
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

    private static int generateRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
