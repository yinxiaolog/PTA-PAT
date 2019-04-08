import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GasStation implements Comparable<GasStation> {
    double price;
    double distance;

    @Override
    public int compareTo(GasStation another) {
        if (distance < another.distance) {
            return -1;
        } else if (distance > another.distance) {
            return 1;
        } else {
            if (price < another.price) {
                return -1;
            } else if (price > another.price) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

public class ToFillOrNotToFill {
    private static List<GasStation> stations = new ArrayList<>();
    private static double cMax;
    private static double dAvg;
    private static double destinaton;
    private static double maxDistance;

    private static String cheapestPrice() {
        Collections.sort(stations);
        double nowDis = 0.0;
        double maxDis = 0.0;
        double nowPrice = 0.0;
        double totalPrice = 0.0;
        double leftDis = 0.0;

        if (stations.get(0).distance != 0) {
            return "The maximum travel distance = 0.00";
        } else {
            nowPrice = stations.get(0).price;
        }

        while (nowDis < destinaton) {
            maxDis = nowDis + cMax * dAvg;
            double minPriceDis = 0.0;
            double minPrice = Double.MAX_VALUE;
            int flag = 0;
            for (int i = 1; i <= stations.size() && stations.get(i).distance <= maxDis; i++) {
                if (stations.get(i).distance <= nowDis) {
                    continue;
                }
                if (stations.get(i).price < nowPrice) {
                    totalPrice += (stations.get(i).distance - nowDis - leftDis) * nowPrice / dAvg;
                    //System.out.println("1: " + totalPrice);
                    leftDis = 0.0;
                    nowPrice = stations.get(i).price;
                    nowDis = stations.get(i).distance;
                    flag = 1;
                    break;
                }
                if (stations.get(i).price < minPrice) {
                    minPrice = stations.get(i).price;
                    minPriceDis = stations.get(i).distance;
                }
            }

            if (flag == 0 && minPrice != Double.MAX_VALUE) {
                totalPrice += (nowPrice * (cMax - leftDis / dAvg));
                //System.out.println("2: " + totalPrice);
                leftDis = cMax * dAvg - (minPriceDis - nowDis);
                nowPrice = minPrice;
                nowDis = minPriceDis;
            }
            if (flag == 0 && minPrice == Double.MAX_VALUE) {
                nowDis += cMax * dAvg;
                return String.format("The maximum travel distance = %.2f", nowDis);
            }
        }
        return String.format("%.2f", totalPrice);
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        cMax = Read.nextDouble();
        destinaton = Read.nextDouble();
        dAvg = Read.nextDouble();
        maxDistance = cMax * dAvg;
        int stationNum = Read.nextInt();

        for (int i = 0; i < stationNum; i++) {
            GasStation gasStation = new GasStation();
            gasStation.price = Read.nextDouble();
            gasStation.distance = Read.nextDouble();
            stations.add(gasStation);
        }
        GasStation gasStation = new GasStation();
        gasStation.price = -1;
        gasStation.distance = destinaton;
        stations.add(gasStation);
        System.out.println(cheapestPrice());
    }
}
