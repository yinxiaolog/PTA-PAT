#include <iostream>
#include <string>
#include <climits>
#include <algorithm>

using namespace std;

long long Radix2Decimal(string num, long long radix, long long ceil) {
    long long sum = 0;
    long long j = 1;
    for (int i = num.length() - 1; i >= 0; i--, j *= radix) {
        char c = num[i];
        if (c <= '9') {
            sum += (c - '0') * j;
        } else {
            sum += ((int) c - 87) * j;
        }

        if (sum < 0 || sum > ceil) {
            return -1;
        }
    }

    return sum;
}

int FindMaxDigit(string str) {
    char max = '0';

    for (char c : str) {
        if (c > max) {
            max = c;
        }
    }

    if (max <= '9') {
        return max - '0' + 1;
    } else {
        return (int) max - 87 + 1;
    }
}

int cmp(string num, long long radix, long long ceil) {
    long long ans = Radix2Decimal(num, radix, ceil);
    if (ans < 0) {
        return 1;
    }

    if (ceil > ans) {
        return -1;
    } else if (ceil == ans) {
        return 0;
    } else {
        return 1;
    }
}

long long FindRadix(string num, long long start, long long end, long long ceil) {
    long long mid;
    while (start <= end) {
        mid = (start + end) / 2;
        int flag = cmp(num, mid, ceil);
        if (flag == 0) {
            return mid;
        } else if (flag == -1) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }

    return -1;
}

int main() {
    string N1, N2;
    int tag, radix;
    cin >> N1 >> N2 >> tag >> radix;

    if (tag == 2) {
        string tmp = N2;
        N2 = N1;
        N1 = tmp;
    }

    if (N1 == N2) {
        cout << radix;
        return 0;
    }

    long long decimal_n1;
    decimal_n1 = Radix2Decimal(N1, radix, LONG_LONG_MAX);

    long long low = FindMaxDigit(N2);
    long long high = max(low, decimal_n1) + 1;
    long long ans = FindRadix(N2, low, high, decimal_n1);
    if (ans == -1) {
        cout << "Impossible";
    } else {
        cout << ans << endl;
    }
    return 0;
}