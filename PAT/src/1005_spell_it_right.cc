#include <iostream>
#include <string>
#include <stack>
#include <vector>

using namespace std;

const string digit[10] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

vector<int> solve(const string &str) {
    stack<int> st;
    vector<int> ans;
    int sum = 0;
    for (char c : str) {
        sum += c - '0';
    }

    if (sum == 0) {
        ans.push_back(sum);
        return ans;
    }
    while (sum > 0) {
        st.push(sum % 10);
        sum /= 10;
    }

    while (!st.empty()) {
        ans.push_back(st.top());
        st.pop();
    }
    return ans;
}

int main() {
    string N;
    cin >> N;
    vector<int> ans = solve(N);
    for (int i = 0; i < ans.size(); i++) {
        if (i != ans.size() - 1) {
            cout << digit[ans[i]] << " ";
        } else {
            cout << digit[ans[i]];
        }
    }
    cin >> N;
}