import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Table {
    private int No;
    private boolean isVip;
    private boolean isBusy;
    private int endTime = 28800;
    private int playersServed;

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getPlayersServed() {
        return playersServed;
    }

    public void setPlayersServed(int playersServed) {
        this.playersServed = playersServed;
    }

    public static String int2String(int time) {
        int h = time / 3600;
        String hh = String.format("%02d", h);
        time = time % 3600;
        int m = time / 60;
        String mm = String.format("%02d", m);
        h = time % 60;
        int s = h;
        String ss = String.format("%02d", s);
        return hh + ":" + mm + ":" + ss;
    }

    @Override
    public String toString() {
        return int2String(endTime) + " " + isVip;
    }
}

class Player implements Comparable<Player> {
    private String arrTimeStr;
    private int arrivingTime;
    private int playingTime;
    private int waitingTime;
    private int servingTime;
    private int isVip;
    private boolean sortBy = true;

    public String getArrTimeStr() {
        return arrTimeStr;
    }

    public void setArrTimeStr(String arrTimeStr) {
        this.arrTimeStr = arrTimeStr;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public int getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(int arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getServingTime() {
        return servingTime;
    }

    public void setServingTime(int servingTime) {
        this.servingTime = servingTime;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public boolean isSortBy() {
        return sortBy;
    }

    public void setSortBy(boolean sortBy) {
        this.sortBy = sortBy;
    }

    public static String int2String(int time) {
        int h = time / 3600;
        String hh = String.format("%02d", h);
        time = time % 3600;
        int m = time / 60;
        String mm = String.format("%02d", m);
        h = time % 60;
        int s = h;
        String ss = String.format("%02d", s);
        return hh + ":" + mm + ":" + ss;
    }

    @Override
    public int compareTo(Player another) {
        if (sortBy) {
            if (arrivingTime < another.arrivingTime) {
                return -1;
            } else if (arrivingTime > another.arrivingTime) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (servingTime < another.servingTime) {
                return -1;
            } else if (servingTime > another.servingTime) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        waitingTime = (servingTime - arrivingTime + 30) / 60;
        return arrTimeStr + " " + int2String(servingTime) + " " + waitingTime;
    }
}

public class TableTennis {
    private static final int STARTTIME = 28800;
    private static final int ENDTIME = 75600;

    private static int string2Int(String time) {
        String[] HHMMSS = time.split(":");
        int h = Integer.parseInt(HHMMSS[0]) * 3600;
        int m = Integer.parseInt(HHMMSS[1]) * 60;
        int s = Integer.parseInt(HHMMSS[2]);
        return h + m + s;
    }

    public static String int2String(int time) {
        int h = time / 3600;
        String hh = String.format("%02d", h);
        time = time % 3600;
        int m = time / 60;
        String mm = String.format("%02d", m);
        h = time % 60;
        int s = h;
        String ss = String.format("%02d", s);
        return hh + ":" + mm + ":" + ss;
    }

    private static Table shortestTime(List<Table> tables) {
        int min = Integer.MAX_VALUE;
        for (Table table : tables) {
            if (table.getEndTime() < min && !table.isVip()) {
                min = table.getEndTime();
            }
        }
        for (Table table : tables) {
            if (table.getEndTime() <= min && !table.isVip()) {
                return table;
            }
        }
        return null;
    }

    private static Table shortestTimeVip(List<Table> tables) {
        int min = Integer.MAX_VALUE;
        for (Table table : tables) {
            if (table.getEndTime() < min && table.isVip()) {
                min = table.getEndTime();
            }
        }
        for (Table table : tables) {
            if (table.getEndTime() <= min && table.isVip()) {
                return table;
            }
        }
        return null;
    }

    private static Table maxTimeTableOrdinary(List<Table> tables, int max) {
        for (Table table : tables) {
            if (table.getEndTime() <= max && !table.isVip()) {
                return table;
            }
        }
        return null;
    }

    private static Table maxTimeTableVip(List<Table> tables, int max) {
        for (Table table : tables) {
            if (table.getEndTime() <= max && table.isVip()) {
                return table;
            }
        }
        return null;
    }

    private static Table maxTimeTable(List<Table> tables, int max) {
        for (Table table : tables) {
            if (table.getEndTime() <= max) {
                return table;
            }
        }
        return null;
    }

    private static int maxEndTime(List<Table> tables) {
        int max = -1;
        for (Table table : tables) {
            if (table.getEndTime() > max) {
                max = table.getEndTime();
            }
        }
        return max;
    }

    private static Player playerMinArrivingTime(List<Player> players) {
        for (Player player : players) {
            if (player.getServingTime() <= 0 && player.getIsVip() == 0) {
                return player;
            }
        }
        return null;
    }

    private static Player vipPlayerMinArrivingTime(List<Player> players) {
        for (Player player : players) {
            if (player.getServingTime() <= 0 && player.getIsVip() == 1) {
                return player;
            }
        }
        return null;
    }

    private static Player waitingPlayer(List<Player> players) {
        for (Player player : players) {
            if (player.getServingTime() <= 0) {
                return player;
            }
        }
        return null;
    }

    private static List<Player> dealData(List<Table> tables, List<Player> players) {
        List<Player> result = new ArrayList<>();
        Collections.sort(players);
        while (players.size() > 0) {
            Table shortestTimeTable = shortestTime(tables);
            Table shortestTimeTableVip = shortestTimeVip(tables);
            Player player = waitingPlayer(players);
            int maxTime = 0;
            int minTime = 0;
            boolean flag = false;
            if (shortestTimeTable.getEndTime() < shortestTimeTableVip.getEndTime()) {
                maxTime = shortestTimeTableVip.getEndTime();
                minTime = shortestTimeTable.getEndTime();
            } else if (shortestTimeTable.getEndTime() > shortestTimeTableVip.getEndTime()) {
                maxTime = shortestTimeTable.getEndTime();
                minTime = shortestTimeTableVip.getEndTime();
            } else {
                maxTime = shortestTimeTable.getEndTime();
                minTime = shortestTimeTable.getEndTime();
                flag = true;
            }

            if (minTime >= ENDTIME || player.getArrivingTime() >= ENDTIME) {
                break;
            }

            if (player.getArrivingTime() >= minTime) {
                if (player.getIsVip() == 1) {
                    Table table = maxTimeTableVip(tables, player.getArrivingTime());
                    if (table != null) {
                        player.setServingTime(player.getArrivingTime());
                        table.setEndTime(player.getArrivingTime() + player.getPlayingTime());
                        table.setPlayersServed(table.getPlayersServed() + 1);
                    } else {
                        Table tableOrdinary = maxTimeTableOrdinary(tables, player.getArrivingTime());
                        player.setServingTime(player.getArrivingTime());
                        tableOrdinary.setEndTime(player.getArrivingTime() + player.getPlayingTime());
                        tableOrdinary.setPlayersServed(tableOrdinary.getPlayersServed() + 1);
                    }
                } else {
                    Table table = maxTimeTable(tables, player.getArrivingTime());
                    player.setServingTime(player.getArrivingTime());
                    table.setEndTime(player.getArrivingTime() + player.getPlayingTime());
                    table.setPlayersServed(table.getPlayersServed() + 1);
                }
                result.add(player);
                players.remove(player);

            } else {
                Player playerOrdinary = playerMinArrivingTime(players);
                Player playerVip = vipPlayerMinArrivingTime(players);
                if (playerVip != null && playerVip.getArrivingTime() <= minTime) {
                    if (flag) {
                        playerVip.setServingTime(shortestTimeTableVip.getEndTime());
                        shortestTimeTableVip.setEndTime(shortestTimeTableVip.getEndTime() + playerVip.getPlayingTime());
                        shortestTimeTableVip.setPlayersServed(shortestTimeTableVip.getPlayersServed() + 1);
                        result.add(playerVip);
                        players.remove(playerVip);
                        continue;
                    }

                    if (shortestTimeTable.getEndTime() < shortestTimeTableVip.getEndTime()) {
                        playerVip.setServingTime(shortestTimeTable.getEndTime());
                        shortestTimeTable.setEndTime(shortestTimeTable.getEndTime() + playerVip.getPlayingTime());
                        shortestTimeTable.setPlayersServed(shortestTimeTable.getPlayersServed() + 1);
                        result.add(playerVip);
                        players.remove(playerVip);
                        continue;
                    }

                    if (shortestTimeTable.getEndTime() > shortestTimeTableVip.getEndTime()) {
                        playerVip.setServingTime(shortestTimeTableVip.getEndTime());
                        shortestTimeTableVip.setEndTime(shortestTimeTableVip.getEndTime() + playerVip.getPlayingTime());
                        shortestTimeTableVip.setPlayersServed(shortestTimeTableVip.getPlayersServed() + 1);
                        result.add(playerVip);
                        players.remove(playerVip);
                        continue;
                    }
                }

                if (playerOrdinary != null && playerOrdinary.getArrivingTime() <= minTime) {
                    if (flag) {
                        playerOrdinary.setServingTime(shortestTimeTable.getEndTime());
                        shortestTimeTable.setEndTime(shortestTimeTable.getEndTime() + playerOrdinary.getPlayingTime());
                        shortestTimeTable.setPlayersServed(shortestTimeTable.getPlayersServed() + 1);
                        result.add(playerOrdinary);
                        players.remove(playerOrdinary);
                        continue;
                    }

                    if (shortestTimeTable.getEndTime() < shortestTimeTableVip.getEndTime()) {
                        playerOrdinary.setServingTime(shortestTimeTable.getEndTime());
                        shortestTimeTable.setEndTime(shortestTimeTable.getEndTime() + playerOrdinary.getPlayingTime());
                        shortestTimeTable.setPlayersServed(shortestTimeTable.getPlayersServed() + 1);
                        result.add(playerOrdinary);
                        players.remove(playerOrdinary);
                        continue;
                    }

                    if (shortestTimeTable.getEndTime() > shortestTimeTableVip.getEndTime()) {
                        playerOrdinary.setServingTime(shortestTimeTableVip.getEndTime());
                        shortestTimeTableVip.setEndTime(shortestTimeTableVip.getEndTime() + playerOrdinary.getPlayingTime());
                        shortestTimeTableVip.setPlayersServed(shortestTimeTableVip.getPlayersServed() + 1);
                        result.add(playerOrdinary);
                        players.remove(playerOrdinary);
                        continue;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Player player = new Player();
            String time = Read.next();
            player.setArrTimeStr(time);
            player.setArrivingTime(string2Int(time));
            int playingTime = Read.nextInt() * 60;
            if (playingTime > 120 * 60) {
                playingTime = 120 * 60;
            }
            player.setPlayingTime(playingTime);
            player.setIsVip(Read.nextInt());
            players.add(player);
        }

        List<Table> tables = new ArrayList<>();
        int K = Read.nextInt();
        for (int i = 1; i <= K; i++) {
            Table table = new Table();
            table.setNo(i);
            tables.add(table);
        }

        int M = Read.nextInt();
        for (int i = 1; i <= M; i++) {
            int vip = Read.nextInt();
            tables.get(vip - 1).setVip(true);
        }

        List<Player> ans = dealData(tables, players);

        for (Player player : players) {
            player.setSortBy(false);
        }
        Collections.sort(players);
        for (Player player : ans) {
            if (player.getServingTime() > 0) {
                System.out.println(player.toString());
            }
        }
        for (int i = 0; i < tables.size() - 1; i++) {
            System.out.print(tables.get(i).getPlayersServed() + " ");
        }
        System.out.print(tables.get(tables.size() - 1).getPlayersServed());
        System.out.println();
    }
}