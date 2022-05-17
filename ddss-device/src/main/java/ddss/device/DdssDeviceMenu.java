package ddss.device;

import ddss.device.domain.CatalogRecord;
import ddss.device.domain.CatalogStorage;
import ddss.device.domain.Data;
import ddss.device.simulation.DeviceSimulation;

import java.util.List;
import java.util.Scanner;

import static ddss.device.DdssDeviceProps.*;
import static ddss.device.api.CatalogInteractionController.*;
import static ddss.device.api.StorageInteractionController.downloadAll;
import static ddss.device.api.StorageInteractionController.upload;

public class DdssDeviceMenu {

    private static String storageToUploadAddress = "";
    private static String storageToDownloadAddress = "";
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
                    "3 -- Получить адрес доступного Хранилища (адрес Хранилища = " + storageToUploadAddress + ")\n" +
                    "4 -- Отправить в Хранилище тестовые данные (адрес Хранилища = " + storageToUploadAddress + ")\n" +
                    "5 -- Получить адрес Хранилища c Записью=" + catalogRecordId + "\n" +
                    "6 -- Получить данные Записи=" + catalogRecordId + " из Хранилища=" + storageToDownloadAddress + "\n" +
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
                    menuGetStorageToUpload();
                    break;
                case "4":
                    menuUploadData();
                    break;
                case "5":
                    menuGetStorageToDownload();
                    break;
                case "6":
                    menuDownloadAllData();
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

    private static void menuGetStorageToUpload() {
        CatalogStorage storageToUpload = getStorageToUpload(catalogRecordId, username, password);
        storageToUploadAddress = storageToUpload.toString();
        print(storageToUpload);
    }

    private static void menuUploadData() {
        if (upload(DeviceSimulation.DATA, storageToUploadAddress, catalogRecordId, username, password)) {
            System.out.println("ДАННЫЕ СОХРАНЕНЫ");
        } else {
            System.out.println("ДАННЫЕ НЕ СОХРАНЕНЫ");
        }
        System.out.println();
    }

    private static void menuGetStorageToDownload() {
        CatalogStorage storageToDownload = getStorageToDownload(catalogRecordId, username, password);
        storageToDownloadAddress = storageToDownload.toString();
        print(storageToDownload);
    }

    private static void menuDownloadAllData() {
        List<Data> dataList = downloadAll(storageToDownloadAddress, catalogRecordId, username, password);
        if (dataList != null) {
            System.out.println("ДАННЫЕ СКАЧАНЫ");
            System.out.println(
                    DeviceSimulation.toString(dataList.get(0))
            );
        } else {
            System.out.println("ДАННЫЕ НЕ СКАЧАНЫ");
        }
        System.out.println();
    }

    private static void print(CatalogStorage storage) {
        System.out.println("Номер ....... " + storage.getId());
        System.out.println("Описание .... " + storage.getAbout());
        System.out.println("IP-адресс ... " + storage.getIpAddress());
        System.out.println("Порт ........ " + storage.getPort());
        System.out.println();
    }
}
