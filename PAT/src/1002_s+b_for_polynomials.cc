#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    double exp;
    double coe;

    Node(double _exp, double _coe) {
        exp = _exp;
        coe = _coe;
    }
};

vector<Node> a, b;

bool cmp(Node &a, Node &b) {
    return a.exp > b.exp;
}

vector<Node> add(vector<Node> pol_a, vector<Node> pol_b) {
    sort(pol_a.begin(), pol_a.end(), cmp);
    sort(pol_b.begin(), pol_b.end(), cmp);
    vector<Node> ans;

    for (int i = 0, j = 0; i < pol_a.size() || j < pol_b.size();) {
        if (i < pol_a.size() && j < pol_b.size()) {
            if (pol_a[i].exp < pol_b[j].exp) {
                ans.emplace_back(Node(pol_b[j].exp, pol_b[j].coe));
                j++;
            } else if (pol_a[i].exp > pol_b[j].exp) {
                ans.emplace_back(Node(pol_a[i].exp, pol_a[i].coe));
                i++;
            } else if (pol_a[i].exp == pol_b[j].exp) {
                ans.emplace_back(Node(pol_a[i].exp, pol_a[i].coe + pol_b[j].coe));
                i++;
                j++;
            }
        } else if (i == pol_a.size() && j < pol_b.size()) {
            ans.push_back(pol_b[j]);
            j++;
        } else if (j == pol_b.size() && i < pol_a.size()) {
            ans.push_back(pol_a[i]);
            i++;
        }
    }

    return ans;
}

int main() {
    int K;
    cin >> K;
    for (int i = 0; i < K; i++) {
        double exp, coe;
        cin >> exp >> coe;
        a.emplace_back(Node(exp, coe));
    }
    cin >> K;
    for (int i = 0; i < K; i++) {
        double exp, coe;
        cin >> exp >> coe;
        b.emplace_back(Node(exp, coe));
    }

    vector<Node> ans = add(a, b);
    int size = ans.size();
    for (Node node : ans) {
        if (node.coe == 0) {
            size--;
        }
    }
    cout << size;
    for (Node node : ans) {
        if (node.coe != 0) {
            printf(" %d %.1f", (int) node.exp, node.coe);
        }
    }
    return 0;
}