#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Node {
    int id;
    int weight;
    int rank;

    Node(int _id, int _weight) {
        id = _id;
        weight = _weight;
    }
};

int Np, Ng;

void Solve(vector<Node> &mice, queue<int> q) {
    int tmp_Np = Np;

    while (q.size() > 1) {
        int groups = tmp_Np % Ng == 0 ? tmp_Np / Ng : tmp_Np / Ng + 1;
        for (int i = 0; i < groups; i++) {
            int max = q.front();
            for (int j = 0; j < Ng; j++) {
                if (i * Ng + j >= tmp_Np) {
                    break;
                }

                int front = q.front();
                if (mice[front].weight > mice[max].weight) {
                    max = front;
                }
                mice[front].rank = groups + 1;
                q.pop();
            }
            q.push(max);
        }

        tmp_Np = groups;
    }

    mice[q.front()].rank = 1;
}

int main() {
    cin >> Np >> Ng;
    vector<Node> mice;
    queue<int> q;
    for (int i = 0; i < Np; i++) {
        int x;
        cin >> x;
        mice.emplace_back(Node(i + 1, x));
    }
    for (int i = 0; i < Np; i++) {
        int x;
        cin >> x;
        q.push(x);
    }

    Solve(mice, q);
    for (int i = 0; i < mice.size(); i++) {
        if (i == 0) {
            cout << mice[i].rank;
        } else {
            cout << ' ' << mice[i].rank;
        }
    }
}