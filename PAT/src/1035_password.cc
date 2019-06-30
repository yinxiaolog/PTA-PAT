#include <iostream>
#include <string>
#include <vector>

using namespace std;

struct Node {
    string name;
    string passwd;

    Node(string _name, string _passwd) {
        name = _name;
        passwd = _passwd;
    }
};

string Replace(string passwd) {
    for (int i = 0; i < passwd.length(); i++) {
        if (passwd[i] == '1') {
            passwd[i] = '@';
            continue;
        }

        if (passwd[i] == '0') {
            passwd[i] = '%';
            continue;
        }

        if (passwd[i] == 'l') {
            passwd[i] = 'L';
            continue;
        }

        if (passwd[i] == 'O') {
            passwd[i] = 'o';
            continue;
        }
    }

    return passwd;
}

int main() {
    int N;
    vector<Node> ans;
    cin >> N;
    for (int i = 0; i < N; i++) {
        string name, passwd;
        cin >> name >> passwd;
        string replaced_passwd = Replace(passwd);
        if (passwd != replaced_passwd) {
            ans.emplace_back(Node(name, replaced_passwd));
        }
    }

    if (ans.empty()) {
        if (N == 1) {
            cout << "There is 1 account and no account is modified";
        } else {
            cout << "There are " << N << " accounts and no account is modified";
        }
        return 0;
    }

    cout << ans.size() << endl;
    for (Node &node : ans) {
        cout << node.name << " " << node.passwd << endl;
    }
    return 0;
}