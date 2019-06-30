#include <iostream>
#include <algorithm>
#include <vector>
#include <climits>
#include <cstdio>

using namespace std;

struct Node {
    double price;
    double dis;

    Node(double _price, double _dis) {
        price = _price;
        dis = _dis;
    }
};

double Cmax, D, Davg;
int N;
vector<Node> gas_stations;

bool Cmp(Node &a, Node &b) {
    return a.dis < b.dis;
}

int FindCheapest(int x) {
    double max = Cmax * Davg;
    double max_price = INT_MAX;

    int index = -1;
    for (int i = x + 1; gas_stations[i].dis <= gas_stations[x].dis + max; i++) {
        if (gas_stations[i].price < gas_stations[x].price) {
            return i;
        }
        if (gas_stations[i].price < max_price) {
            max_price = gas_stations[i].price;
            index = i;
        }
    }
    return index;
}

void Solve() {
    double max = Cmax * Davg;
    double ans = 0, tank = 0;
    int index = 0;
    sort(gas_stations.begin(), gas_stations.end(), Cmp);
    if (gas_stations[0].dis > 0) {
        printf("The maximum travel distance = %.2f", 0.00);
        return;
    }

    while (index < N) {
        int min_price_index = FindCheapest(index);
        if (min_price_index == -1) {
            printf("The maximum travel distance = %.2f", gas_stations[index].dis + max);
            return;
        }
        double need = (gas_stations[min_price_index].dis - gas_stations[index].dis) / Davg;
        if (gas_stations[min_price_index].price < gas_stations[index].price) {
            if (tank < need) {
                ans += (need - tank) * gas_stations[index].price;
                tank = 0;
            } else {
                tank -= need;
            }
        } else {
            ans += (Cmax - tank) * gas_stations[index].price;
            tank = Cmax - need;
        }
        index = min_price_index;
    }

    printf("%.2f", ans);
}

int main() {
    cin >> Cmax >> D >> Davg >> N;
    for (int i = 0; i < N; i++) {
        double price, dis;
        cin >> price >> dis;
        gas_stations.emplace_back(Node(price, dis));
    }
    gas_stations.emplace_back(Node(0, D));
    Solve();
    return 0;
}