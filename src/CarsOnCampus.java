import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CarsOnCampus {
    private static class Record implements Comparable<Record>{
        String plateNumber;
        String date;
        int timestamp;
        boolean isInOrOut;

        public Record(String plateNumber, String date, String flag) {
            this.plateNumber = plateNumber;
            this.date = date;
            this.timestamp = date2Timestamp(date);
            if (flag.equals("in")) {
                isInOrOut = true;
            } else {
                isInOrOut = false;
            }
        }

        @Override
        public int compareTo(Record another) {
            if (plateNumber.compareTo(another.plateNumber) < 0) {
                return -1;
            }

            if (plateNumber.compareTo(another.plateNumber) > 0) {
                return 1;
            }

            if (timestamp < another.timestamp) {
                return -1;
            }

            if (timestamp > another.timestamp) {
                return 1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return plateNumber + " " + date + " " + (isInOrOut ? "in" : "out");
        }
    }

    private static int date2Timestamp(String date) {
        String[] strings = date.split(":");
        int stamp = 0;
        stamp += 3600 * Integer.valueOf(strings[0]);
        stamp += 60 * Integer.valueOf(strings[1]);
        stamp += Integer.valueOf(strings[2]);

        return stamp;
    }
    private static List<Record> records = new ArrayList<>();
    private static List<Record> cleanRecords = new ArrayList<>();
    private static int[] count = new int[24*3600];
    private static int maxTimestamp = 0;

    private static Map<String, Integer> deleteDirtyData() {
        Collections.sort(records);

        Map<String, Integer> ans = new TreeMap<>();
        for (int i = 0; i < records.size() - 1; i++) {
            if (records.get(i).plateNumber.equals(records.get(i + 1).plateNumber) &&
                    records.get(i).isInOrOut && !records.get(i + 1).isInOrOut) {
                cleanRecords.add(records.get(i));
                cleanRecords.add(records.get(i + 1));

                Integer j = ans.get(records.get(i).plateNumber);
                if (j == null) {
                    ans.put(records.get(i).plateNumber, records.get(i + 1).timestamp - records.get(i).timestamp);
                } else {
                    ans.put(records.get(i).plateNumber, j + records.get(i + 1).timestamp - records.get(i).timestamp);
                }

                int k = ans.get(records.get(i).plateNumber);
                if (maxTimestamp < k) {
                    maxTimestamp = k;
                }
            }
        }

        return ans;
    }

    private static void count() {
        for (Record record : cleanRecords) {
            if (record.isInOrOut) {
                count[record.timestamp]++;
            } else {
                count[record.timestamp]--;
            }
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i -1];
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();

        for (int i = 0; i < N; i++) {
            records.add(new Record(Read.next(), Read.next(), Read.next()));
        }

        List<Integer> query = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            query.add(date2Timestamp(Read.next()));
        }
        Map<String, Integer> times = deleteDirtyData();
        count();
        for (Integer i : query) {
            System.out.println(count[i]);
        }

        for (Map.Entry<String, Integer> entry : times.entrySet()) {
            if (entry.getValue() == maxTimestamp) {
                System.out.print(entry.getKey() + " ");
            }
        }

        System.out.printf("%02d:%02d:%02d", maxTimestamp / 3600, maxTimestamp % 3600 / 60, maxTimestamp % 60);
    }
}
