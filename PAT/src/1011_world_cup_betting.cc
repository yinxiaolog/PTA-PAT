#include <iostream>
#include <vector>
#include <map>
#include <cstdio>

using namespace std;

int main() {
    vector<double> key;
    vector<int> index;
    for (int i = 0; i < 3; i++) {
        double max = -1;
        int index_max = -1;
        for (int j = 0; j < 3; j++) {
            double tmp;
            cin >> tmp;
            if (tmp > max) {
                max = tmp;
                index_max = j;
            }
        }

        key.push_back(max);
        index.push_back(index_max);
    }

    double ans = 1;
    for (int i = 0; i < index.size(); i++) {
        if (index[i] == 0) {
            cout << "W ";
        }

        if (index[i] == 1) {
            cout << "T ";
        }

        if (index[i] == 2) {
            cout << "L ";
        }
        ans *= key[i];
    }

    ans = (ans * 0.65 - 1) * 2;
    printf("%.2f", ans);
    return 0;
}