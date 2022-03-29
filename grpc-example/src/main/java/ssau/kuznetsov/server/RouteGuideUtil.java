package ssau.kuznetsov.server;

import com.google.protobuf.util.JsonFormat;
import ssau.kuznetsov.proto.Feature;
import ssau.kuznetsov.proto.FeatureDatabase;
import ssau.kuznetsov.proto.Point;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RouteGuideUtil {

    private static final double COORD_FACTOR = 1e7;

    public static double getLatitude(Point location) {
        return location.getLatitude() / COORD_FACTOR;
    }

    public static double getLongitude(Point location) {
        return location.getLongitude() / COORD_FACTOR;
    }

    public static URL getDefaultFeaturesFile() {
        return RouteGuideServer.class.getResource("route_guide_db.json");
    }

    public static List<Feature> parseFeatures(URL file) throws IOException {
        try (InputStream input = file.openStream()) {
            try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                FeatureDatabase.Builder database = FeatureDatabase.newBuilder();
                JsonFormat.parser().merge(reader, database);
                return database.getFeatureList();
            }
        }
    }

    public static boolean exists(Feature feature) {
        return feature != null && !feature.getName().isEmpty();
    }

}
