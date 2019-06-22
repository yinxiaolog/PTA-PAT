#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <string>

using namespace std;

struct Node {
    string id;
    string date;
    string word;

    Node(string _id, string _date, string _word) {
        id = _id;
        date = _date;
        word = _word;
    }
};

int costs[23];
int day_cost[24 * 60 + 1] = {0};
map<string, vector<Node> > records;

bool cmp(Node &a, Node &b) {
    return a.date < b.date;
}

int Date2TimeStamp(const string &date) {
    int hh = stoi(date.substr(0, 2));
    int mm = stoi(date.substr(3, 5));
    return hh * 60 + mm;
}

void CalCost() {
    for (int i = 1; i <= 1440; i++) {
        int h = i / 60;
        if (h == 0) {
            day_cost[i] = i % 60 * costs[h];
        } else {
            if (i % 60 == 0) {
                day_cost[i] = day_cost[i - 1] + costs[h - 1];
            } else {
                day_cost[i] = day_cost[i - 1] + costs[h];
            }
        }
    }
}

int GetCost(const string &start, const string &end) {
    int start_day = stoi(start.substr(0, 2));
    int end_day = stoi(end.substr(0, 2));

    if (start_day == end_day) {
        return day_cost[Date2TimeStamp(end.substr(3))] - day_cost[Date2TimeStamp(start.substr(3))];
    }

    int sum = 0;
    for (int i = start_day; i <= end_day; i++) {
        if (i == start_day) {
            sum += day_cost[1440] - day_cost[Date2TimeStamp(start.substr(3))];
        } else if (i == end_day) {
            sum += day_cost[Date2TimeStamp(end.substr(3))] - day_cost[0];
        } else {
            sum += day_cost[1440] - day_cost[0];
        }
    }

    return sum;
}

void DeleteDirtyData(vector<Node> &v) {
    vector<Node> data;
    sort(v.begin(), v.end(), cmp);
    for (int i = 0; i < v.size() - 1; i++) {
        if (v[i].word == "on-line" && v[i + 1].word == "off-line") {
            data.push_back(v[i]);
            data.push_back(v[i + 1]);
        }
    }
    v = data;
}

int Time(string &start, string &end) {
    return stoi(end.substr(3, 5)) * 1440 + stoi(end.substr(6, 8)) * 60 + stoi(end.substr(9, 11)) - (
            stoi(start.substr(3, 5)) * 1440 + stoi(start.substr(6, 8)) * 60 + stoi(start.substr(9, 11))
    );
}

int main() {
    for (int i = 0; i < 24; i++) {
        int time;
        cin >> time;
        costs[i] = time;
    }

    int N;
    cin >> N;
    for (int i = 0; i < N; i++) {
        string id, date, word;
        cin >> id >> date >> word;
        records[id].push_back(Node(id, date, word));
    }
    CalCost();
    for (auto it = records.begin(); it != records.end(); it++) {
        vector<Node> &v = it->second;
        DeleteDirtyData(v);
        if (v.size() == 0) {
            continue;
        }
        double sum = 0;
        cout << it->first << " ";
        cout << v[0].date.substr(0, 2) << endl;

        for (int i = 0; i < v.size(); i += 2) {
            cout << v[i].date.substr(3) << " " << v[i + 1].date.substr(3) << " " << Time(v[i].date, v[i + 1].date);
            double amount = GetCost(v[i].date.substr(3), v[i + 1].date.substr(3)) / 100.0;
            sum += amount;
            printf(" $%.2f\n", amount);
        }

        printf("Total amount: $%.2f\n", sum);
    }
    return 0;
}

//tips：虽然题目说会保证有至少有一对on-line 和 off-line匹配，
//但是这一对不一定合法（on-line时间大于off-line时间，
//此时不应该输出此人账单