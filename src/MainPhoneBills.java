import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainPhoneBills {
    private static class Record implements Comparable<Record> {
        String name;
        String time;
        String status;

        @Override
        public String toString() {
            return name + " " + time + " " + status;
        }

        @Override
        public int compareTo(Record another) {
            int result = time.compareTo(another.time);
            if (result < 0) {
                return -1;
            } else if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static int[] rates = new int[24];
    private static List<Record> list = new ArrayList<>();
    private static Map<String, List<Record>> map = new TreeMap<>();

    private static void setMap() {
        for (Record record : list) {
            List<Record> tempList = new ArrayList<>();
            String name = record.name;
            tempList = map.get(name);
            tempList.add(record);
            //System.out.println(tempList.toString());
            //System.out.println(map.toString());
            map.put(name, tempList);
        }
    }

    private static void calculateBill(String name, List<Record> list) {
        boolean flag = false;
        for (Record record : list) {
            if (record.status.equals("off-line")) {
                flag = true;
            }
        }
        if (!flag) {
            return;
        }
        Collections.sort(list);
        //System.out.println(list.toString());
        System.out.println(name + " " + list.get(0).time.split(":")[0]);
        double total = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).status.equals("off-line") && list.get(i - 1).status.equals("on-line")) {
                double money = calculateMoney(list.get(i)) - calculateMoney(list.get(i - 1));
                int minutes = calculateMinutes(list.get(i)) - calculateMinutes(list.get(i - 1));
                total += money;
                System.out.format("%s %s %d $%.2f\n", list.get(i - 1).time.substring(3), list.get(i).time.substring(3), minutes, money);
                //System.out.println();
            }
        }
        System.out.format("Total amount: $%.2f\n", total);
        //System.out.println();
        ////System.out.println(list.toString());

    }

    private static void calculateBills() {
        for (Map.Entry<String, List<Record>> entry : map.entrySet()) {
            //System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
            calculateBill(entry.getKey(), entry.getValue());
        }
    }

    private static double calculateMoney(Record record) {
        //String time = record.time;
        String[] time = record.time.split(":");
        int dd = Integer.parseInt(time[1]);
        int hh = Integer.parseInt(time[2]);
        int mm = Integer.parseInt(time[3]);
        double total = 0;
        //int minutes = dd * 60 + hh * hh + mm;
        int sum = 0;
        for (int i = 0; i < rates.length; i++) {
            sum += rates[i];
        }
        //System.out.println(sum);
        total += sum * 60 * dd;

        sum = 0;
        for (int i = 0; i < hh; i++) {
            sum += rates[i];
        }
        //System.out.println(sum);

        total += sum * 60;
        total += rates[hh] * mm;
        total /= 100;
        return total;
    }

    private static int calculateMinutes(Record record) {
        String[] time = record.time.split(":");
        int dd = Integer.parseInt(time[1]);
        int hh = Integer.parseInt(time[2]);
        int mm = Integer.parseInt(time[3]);
        int minutes = dd * 60 * 24 + hh * 60 + mm;
        return minutes;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        for (int i = 0; i < rates.length; i++) {
            rates[i] = Read.nextInt();
        }

        int N = Read.nextInt();

        String name = "";
        String time = "";
        String status = "";

        for (int i = 0; i < N; i++) {
            List<Record> tempList = new ArrayList<>();
            Record record = new Record();
            name = Read.next();
            time = Read.next();
            status = Read.next();
            record.name = name;
            record.time = time;
            record.status = status;
            map.put(name, tempList);
            list.add(record);
        }
        setMap();
        //System.out.println(map.toString());
        calculateBills();
    }
}
