#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <cstdio>

using namespace std;

const int kSize = 1001;

struct Window {
    int pop_time = 480;
    int end_time = 480;
    queue<int> q;
};

vector<Window> windows(20);
int N, M, K, Q;
int times[kSize];
int ans[kSize] = {0};
int indx = 1;

void init() {
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if (indx <= K) {
                windows[j].q.push(indx);
                if (windows[j].end_time >= 1020) {
                    ans[indx] = -1;
                }
                windows[j].end_time += times[indx];
                if (i == 0) {
                    windows[j].pop_time = windows[j].end_time;
                }
                if (ans[indx] != -1) {
                    ans[indx] = windows[j].end_time;
                }
                indx++;
            }
        }
    }
}

void Solve() {
    init();

    while (indx <= K) {
        int min_time = INT_MAX;
        int min_index = -1;
        for (int i = 0; i < N; i++) {
            if (windows[i].pop_time < min_time) {
                min_time = windows[i].pop_time;
                min_index = i;
            }
        }

        windows[min_index].q.pop();
        windows[min_index].q.push(indx);
        windows[min_index].pop_time += times[windows[min_index].q.front()];
        if (windows[min_index].end_time >= 1020) {
            ans[indx] = -1;
        }

        windows[min_index].end_time += times[indx];
        if (ans[indx] != -1) {
            ans[indx] = windows[min_index].end_time;
        }
        indx++;
    }

}

int main() {
    cin >> N >> M >> K >> Q;
    for (int i = 1; i <= K; i++) {
        int time;
        cin >> time;
        times[i] = time;
    }

    Solve();

    for (int i = 0; i < Q; i++) {
        int query;
        cin >> query;

        if (ans[query] == -1) {
            cout << "Sorry" << endl;
        } else {
            printf("%02d:%02d\n", ans[query] / 60, ans[query] % 60);
        }
    }
}