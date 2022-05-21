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

    private static final int DEFAULT_DEGREES_DEVIATION = 2;
    private static final int DEFAULT_PASCALS_DEVIATION = 100;
    private static final int DEFAULT_METERS_PER_SECOND_DEVIATION = 4;

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

    public static SensorsData generateSensorsData() {
        int randomDegreesCelsius =
                DEFAULT_DEGREES_CELSIUS + generateRandomInt(-DEFAULT_DEGREES_DEVIATION, DEFAULT_DEGREES_DEVIATION);
        int randomPascals =
                DEFAULT_PASCALS + generateRandomInt(-DEFAULT_PASCALS_DEVIATION, DEFAULT_PASCALS_DEVIATION);
        int randomMetersPerSecondDeviation =
                DEFAULT_PASCALS + generateRandomInt(-DEFAULT_METERS_PER_SECOND_DEVIATION, DEFAULT_METERS_PER_SECOND_DEVIATION);

        return SensorsData.newBuilder()
                .setDegreesCelsius(randomDegreesCelsius)
                .setPascals(randomPascals)
                .setMetersPerSecond(randomMetersPerSecondDeviation).build();
    }

    private static int generateRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
