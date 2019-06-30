#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <map>

using namespace std;

const int kSize = 2002;

struct Node {
    string from_str;
    string to_str;
    int from;
    int to;
    int weight;

    Node(string _from_str, string _to_str, int _weight) {
        from_str = _from_str;
        to_str = _to_str;
        weight = _weight;
    }
};

vector<Node> adj[kSize];
vector<int> vertex;
bool marked[kSize] = {false};
map<int, int> ans;

void Dfs(int v) {
    marked[v] = true;
    vertex.push_back(v);

    for (Node node : adj[v]) {
        if (!marked[node.to]) {
            Dfs(node.to);
        }
    }
}

void FindGang(vector<int> &v, int K) {
    if (v.size() <= 2) {
        return;
    }

    int max = -1, id = -1, sum = 0;
    for (int i : v) {
        int tmp = 0;
        for (Node &node : adj[i]) {
            tmp += node.weight;
        }
        sum += tmp;
        if (tmp > max) {
            max = tmp;
            id = i;
        }
    }

    if (sum > K * 2) {
        ans.insert(make_pair(id, v.size()));
    }
}

int main() {
    int N, K;
    cin >> N >> K;
    set<string> s;
    map<int, string> int2str;
    map<string, int> str2int;
    vector<Node> data;
    for (int i = 0; i < N; i++) {
        string from_str, to_str;
        int weight;
        cin >> from_str >> to_str >> weight;
        s.insert(from_str);
        s.insert(to_str);
        data.emplace_back(Node(from_str, to_str, weight));
    }

    int number = 0;
    for (string str : s) {
        int2str[number] = str;
        str2int[str] = number;
        number++;
    }
    for (Node node : data) {
        node.from = str2int[node.from_str];
        node.to = str2int[node.to_str];
        adj[node.from].push_back(node);
        Node another = Node(node.to_str, node.from_str, node.weight);
        another.from = str2int[another.from_str];
        another.to = str2int[another.to_str];
        adj[another.from].push_back(another);
    }


    for (int i = 0; i < s.size(); i++) {
        if (!marked[i]) {
            Dfs(i);
            FindGang(vertex, K);
            vertex.clear();
        }
    }
    cout << ans.size() << endl;
    for (auto it = ans.begin(); it != ans.end(); it++) {
        cout << int2str[it->first] << " " << it->second << endl;
    }
    return 0;
}