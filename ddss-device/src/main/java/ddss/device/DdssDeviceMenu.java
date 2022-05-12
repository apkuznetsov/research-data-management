package ddss.device;

import java.util.Scanner;

import static ddss.device.DdssDeviceProps.*;
import static ddss.device.api.CatalogInteractionController.createRecord;
import static ddss.device.api.CatalogInteractionController.register;

public class DdssDeviceMenu {

    private static final String storageIpAddressWithPort = "";
    private static int catalogRecordId = -1;

    private static String username = DdssDeviceProps.USERNAME;
    private static String password = DdssDeviceProps.PASSWORD;

    private static Scanner in;

    public static void run(String[] args) {
        in = new Scanner(System.in);
        String m;

        do {
            System.out.print("1 -- Зарегистрироваться\n" +
                    "2 -- Создать запись в Каталоге\n" +
                    "0 -- Выйти\n" +
                    "Выбор ... ");
            m = in.nextLine();

            switch (m) {
                case "1":
                    menuReg();
                    break;

                case "2":
                    menuCreateRec();
                    break;

                default:
                    break;
            }

        } while (!m.equals("0"));
        in.close();
    }

    private static void menuReg() {
        System.out.println("Введите:");
        System.out.println("Логин ............... ");
        String newUsername = in.nextLine();
        System.out.println("Пароль .............. ");
        String newPassword = in.nextLine();

        if (register(newUsername, newPassword, "new user",
                IP_ADDRESS, PORT, false)) {
            username = newUsername;
            password = newPassword;

            System.out.println("ПОЛЬЗОВАТЕЛЬ ЗАРЕГИСТРИРОВАН");
            System.out.println();
        }
    }

    private static void menuCreateRec() {
        System.out.println("Введите:");
        System.out.println("О записи ............ ");
        String newAbout = in.nextLine();

        catalogRecordId = -1;
        catalogRecordId = createRecord(newAbout, PROTO_SCHEME, username, password);
        if (catalogRecordId >= 0) {
            System.out.println("ЗАПИСЬ СОЗДАНА, ЕЁ ID = " + catalogRecordId);
            System.out.println();
        }
    }
}
