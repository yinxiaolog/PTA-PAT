#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <cstring>
#include <algorithm>

using namespace std;

const int kSize = 201;

struct People {
    string name;
    int age;
    int net_worth;

    People() {
        name = "";
        age = net_worth = -1;
    }

    People(string _name, int _age, int _net_worth) {
        name = _name;
        age = _age;
        net_worth = _net_worth;
    }
};

vector<People> all[kSize];
vector<People> people;

bool Cmp(People &A, People &B) {
    if (A.net_worth != B.net_worth) {
        return A.net_worth > B.net_worth;
    }

    if (A.age != B.age) {
        return A.age < B.age;
    }

    return A.name < B.name;
}

int main() {
    int N, K;
    scanf("%d %d", &N, &K);

    for (int i = 0; i < N; i++) {
        string name;
        char str[10];
        int age;
        int net_worth;
        scanf("%s %d %d", str, &age, &net_worth);
        name = str;
        people.emplace_back(People(name, age, net_worth));
    }

    sort(people.begin(), people.end(), Cmp);

    for (int i = 0; i < K; i++) {
        int M, Amin, Amax;
        scanf("%d %d %d", &M, &Amin, &Amax);
        printf("Case #%d:\n", i + 1);

        int count = 0;
        for (int j = 0; j < people.size() && count < M; j++) {
            if (people[j].age >= Amin && people[j].age <= Amax) {
                count++;
                char str[10];
                strncpy(str, people[j].name.c_str(), people[j].name.length() + 1);
                printf("%s %d %d\n", str, people[j].age, people[j].net_worth);
            }
        }

        if (count == 0) {
            printf("%s\n", "None");
        }
    }
}