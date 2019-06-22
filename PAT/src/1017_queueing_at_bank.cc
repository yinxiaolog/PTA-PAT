#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <climits>
#include <cstdio>

using namespace std;

const int kSize = 100;
const int kEndTime = 17 * 3600;

struct Window {
    int in_time = 8 * 3600;
    int out_time = 8 * 3600;
};

struct Customer {
    string time;
    int p;

    Customer(string _time, int _p) {
        time = _time;
        p = _p;
    }
};

int N, K;
Window windows[kSize];
vector<Customer> customers;

bool cmp(Customer &a, Customer &b) {
    return a.time < b.time;
}

int Time2Timestamp(string &str) {
    return stoi(str.substr(0, 2)) * 3600 + stoi(str.substr(3, 5)) * 60 + stoi(str.substr(6));
}

int FindMinQueue() {
    int min = INT_MAX;
    int index = -1;
    for (int i = 0; i < K; i++) {
        if (windows[i].out_time < min) {
            min = windows[i].out_time;
            index = i;
        }
    }
    return index;
}

double Solve() {
    int index = 0;
    double ans = 0;

    while (index < N) {
        int min_window = FindMinQueue();
        int time = Time2Timestamp(customers[index].time);
        if (time <= kEndTime) {
            if (time >= windows[min_window].out_time) {
                windows[min_window].in_time = time;
                windows[min_window].out_time = time + customers[index].p * 60;
            } else {
                ans += windows[min_window].out_time - time;
                windows[min_window].in_time = windows[min_window].out_time;
                windows[min_window].out_time += customers[index].p * 60;
            }
        } else {
            break;
        }
        index++;
    }

    return ans / 60 / index;
}

int main() {
    cin >> N >> K;
    for (int i = 0; i < N; i++) {
        string time;
        int p;
        cin >> time >> p;
        customers.emplace_back(time, p);
    }

    sort(customers.begin(), customers.end(), cmp);
    printf("%.1f", Solve());
}