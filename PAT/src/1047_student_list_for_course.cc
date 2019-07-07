#include <iostream>
#include <cstdio>
#include <string>
#include <cstring>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int main() {
    int N, K;
    scanf("%d %d", &N, &K);
    map<int, vector<string> > course2student;

    for (int i = 1; i <= K; i++) {
        vector<string> v;
        course2student[i] = v;
    }
    for (int i = 0; i < N; i++) {
        string name;
        char str[5];
        scanf("%s", str);
        name = str;
        int C;
        scanf("%d", &C);
        for (int j = 0; j < C; j++) {
            int course;
            scanf("%d", &course);
            course2student[course].push_back(name);
        }
    }

    for (auto p : course2student) {
        printf("%d %d\n", p.first, (int) p.second.size());
        sort(p.second.begin(), p.second.end());
        for (string name : p.second) {
            char str[5];
            strncpy(str, name.c_str(), name.length());
            printf("%s\n", str);
        }
    }
    return 0;
}