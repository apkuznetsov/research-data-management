package ddss.device;

public class DdssDeviceProps {

    public static final String CAT_URL = "http://localhost:8080";
    public static final String CAT_REG_URL = CAT_URL + "/cat/register";
    public static final String CAT_CREATE_URL = CAT_URL + "/cat/create";

    public static final String USERNAME = "example";
    public static final String PASSWORD = "qwerty";
    public static final String IP_ADDRESS = "127.0.0.1";
    public static final short PORT = 2048;

    public static final String PROTO_SCHEME = "message SensorData { int32 data = 1; }";
}
