#include <iostream>
#include <string>
#include <cstdio>

using namespace std;

const string weeks[8] = {"", "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

int main() {
    string s1, s2, s3, s4;
    getline(cin, s1);
    getline(cin, s2);
    getline(cin, s3);
    getline(cin, s4);

    int day = 0, hh = 0, mm = 0;
    int i = 0;
    for (; i < s1.length() && i < s2.length(); i++) {
        if (s1[i] >= 'A' && s1[i] <= 'G' && s1[i] == s2[i]) {
            day = s1[i] - 'A' + 1;
            break;
        }
    }

    for (i += 1; i < s1.length() && i < s2.length(); i++) {
        if (s1[i] == s2[i] && ((s1[i] >= '0' && s1[i] <= '9') || (s1[i] >= 'A' && s1[i] <= 'N'))) {
            if (s1[i] <= '9') {
                hh = s1[i] - '0';
                break;
            } else {
                hh = s1[i] - 'A' + 10;
                break;
            }
        }
    }

    for (int j = 0; j < s3.length() && j < s4.length(); j++) {
        if (s3[j] == s4[j] && ((s3[j] >= 'a' && s3[j] <= 'z') || (s3[j] >= 'A' && s3[j] <= 'Z'))) {
            mm = j;
            break;
        }
    }

    cout << weeks[day];
    printf(" %02d:%02d", hh, mm);
    return 0;
}