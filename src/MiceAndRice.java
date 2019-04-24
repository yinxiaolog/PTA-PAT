import util.Read;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiceAndRice {
    private static int Np;
    private static int Ng;

    private static class Mouse implements Comparable<Mouse> {
        int id;
        int weight;
        int rank = 0;

        @Override
        public int compareTo(Mouse another) {
            if (id < another.id) {
                return -1;
            } else if (id > another.id) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "id: " + id + " weight: " + weight + " rank: " + rank;
        }
    }

    private static List<Mouse> getRank(ArrayDeque<Mouse> queue) {
        int rank = Np;
        List<Mouse> ans = new ArrayList<>();
        if (rank == 1) {
            for (Mouse mouse : queue) {
                mouse.rank = 1;
                ans.add(mouse);
            }
            Collections.sort(ans);
            return ans;
        }
        while (rank > 1) {
            int groups = 0;
            if (rank % Ng == 0) {
                groups = rank / Ng;
            } else {
                groups = rank / Ng + 1;
            }

            int left = rank % Ng;
            rank = groups;

            ArrayDeque<Mouse> tmp = new ArrayDeque<>();
            if (left == 0) {
                for (int i = 0; i < groups; i++) {
                    Mouse max = queue.poll();
                    for (int j = 1; j < Ng; j++) {
                        if (queue.peek().weight > max.weight) {
                            max.rank = groups + 1;
                            ans.add(max);
                            max = queue.poll();
                        } else {
                            Mouse mouse = queue.poll();
                            mouse.rank = groups + 1;
                            ans.add(mouse);
                        }
                    }
                    max.rank = groups;
                    tmp.add(max);
                }
            } else {
                Mouse max;

                for (int i = 0; i < groups - 1; i++) {
                    max = queue.poll();
                    for (int j = 1; j < Ng; j++) {
                        if (queue.peek().weight > max.weight) {
                            max.rank = groups + 1;
                            ans.add(max);
                            max = queue.poll();
                        } else {
                            Mouse mouse = queue.poll();
                            mouse.rank = groups + 1;
                            ans.add(mouse);
                        }
                    }
                    max.rank = groups;
                    tmp.add(max);
                }

                max = queue.poll();
                for (int i = 1; i < left; i++) {
                    if (queue.peekLast().weight > max.weight) {
                        max.rank = groups + 1;
                        ans.add(max);
                        max = queue.pollLast();
                    } else {
                        Mouse mouse = queue.pollLast();
                        mouse.rank = groups + 1;
                        ans.add(mouse);
                    }
                }
                max.rank = groups;
                tmp.add(max);
            }

            queue = tmp;

        }

        ans.add(queue.poll());
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        Np = Read.nextInt();
        Ng = Read.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < Np; i++) {
            int weight = Read.nextInt();
            map.put(i, weight);
        }

        ArrayDeque<Mouse> queue = new ArrayDeque<>();
        for (int i = 0; i < Np; i++) {
            int order = Read.nextInt();
            Mouse mouse = new Mouse();
            mouse.id = order;
            mouse.weight = map.get(order);
            queue.add(mouse);
        }

        List<Mouse> ans = getRank(queue);
        for (int i = 0; i < ans.size() - 1; i++) {
            System.out.print(ans.get(i).rank + " ");
        }
        System.out.println(ans.get(ans.size() - 1).rank);
    }
}
