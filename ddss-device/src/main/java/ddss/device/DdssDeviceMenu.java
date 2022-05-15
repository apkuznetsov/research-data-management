package ddss.device;

import ddss.device.domain.CatalogRecord;
import ddss.device.domain.CatalogStorage;

import java.util.Scanner;

import static ddss.device.DdssDeviceProps.*;
import static ddss.device.api.CatalogInteractionController.*;

public class DdssDeviceMenu {

    private static String storageIpAddressWithPort = "";
    private static Integer catalogRecordId = null;

    private static String username = DdssDeviceProps.USERNAME;
    private static String password = DdssDeviceProps.PASSWORD;

    private static Scanner in;

    public static void run(String[] args) {
        in = new Scanner(System.in);
        String m;

        do {
            System.out.print("1 -- Зарегистрироваться\n" +
                    "2 -- Создать запись в Каталоге (ид текущей записи = " + catalogRecordId + ")\n" +
                    "3 -- Получить адрес доступного Хранилища (адрес Хранилища = " + storageIpAddressWithPort + ")\n" +
                    "4 -- Отправить в Хранилище тестовые данные (адрес Хранилища = " + storageIpAddressWithPort + ")\n" +
                    "0 -- Выйти\n" +
                    "Выбор ... ");
            m = in.nextLine();
            System.out.println();

            switch (m) {
                case "1":
                    menuRegister();
                    break;
                case "2":
                    menuCreateRecord();
                    break;
                case "3":
                    menuGetAvailableStorage();
                    break;
                default:
                    break;
            }

        } while (!m.equals("0"));
        in.close();
    }

    private static void menuRegister() {
        System.out.println("Введите:");
        System.out.print("Логин ............... ");
        String newUsername = in.nextLine();
        System.out.print("Пароль .............. ");
        String newPassword = in.nextLine();
        System.out.println();

        if (register(newUsername, newPassword, "new user",
                IP_ADDRESS, PORT, false)) {
            username = newUsername;
            password = newPassword;

            System.out.println("ПОЛЬЗОВАТЕЛЬ ЗАРЕГИСТРИРОВАН");
            System.out.println();
        }
    }

    private static void menuCreateRecord() {
        System.out.println("Введите:");
        System.out.print("Описание ............ ");
        String newAbout = in.nextLine();
        System.out.println();

        catalogRecordId = -1;
        catalogRecordId = createRecord(newAbout, PROTO_SCHEME, username, password);
        if (catalogRecordId >= 0) {
            System.out.println("ЗАПИСЬ СОЗДАНА, ЕЁ ID = " + catalogRecordId);
            System.out.println();

            menuGetRecordById();
        }
    }

    private static void menuGetRecordById() {
        CatalogRecord catalogRecord = getRecordById(catalogRecordId, username, password);
        System.out.println("Номер ...... " + catalogRecord.getId());
        System.out.println("Описание ... " + catalogRecord.getAbout());
        System.out.println("Схема ...... " + catalogRecord.getProtoScheme());
        System.out.println("Создан ..... " + catalogRecord.getCreatedAt());
        System.out.println();
    }

    private static void menuGetAvailableStorage() {
        CatalogStorage availableStorage = getAvailableStorage(catalogRecordId, username, password);
        storageIpAddressWithPort = availableStorage.toString();

        System.out.println("Номер ....... " + availableStorage.getId());
        System.out.println("Описание .... " + availableStorage.getAbout());
        System.out.println("IP-адресс ... " + availableStorage.getIpAddress());
        System.out.println("Порт ........ " + availableStorage.getPort());
        System.out.println();
    }
}
