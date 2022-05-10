package ddss.storage.api;

import java.io.File;

public class CatalogController {

    public static void main(String[] args) {
        long availableBytes = 0;

        File[] roots = File.listRoots();
        for (File root : roots) {
            availableBytes += root.getFreeSpace();
        }

        long availableGigabytes = (long)((double)availableBytes/1024.0/1024.0);
        System.out.println("there are " + availableGigabytes + " available gigabytes");
    }
}
