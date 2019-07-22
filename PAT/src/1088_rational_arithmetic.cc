#include <iostream>
#include <cstdio>
#include <cmath>

using namespace std;

long long Gcd(long long a, long long b) {
    return a % b == 0 ? b : Gcd(b, a % b);
}

struct Node {
    long long numerators;
    long long denominators;

    Node(long long _numerators, long long _denominators) {
        long long gcd = Gcd(abs(_numerators), abs(_denominators));
        numerators = _numerators / gcd;
        denominators = _denominators / gcd;
        if (numerators * denominators < 0) {
            numerators = -abs(numerators);
            denominators = abs(denominators);
        } else {
            numerators = abs(numerators);
            denominators = abs(denominators);
        }
    }
};

string ToString(Node &node) {
    string ans;
    if (node.numerators == 0) {
        ans = "0";
        return ans;
    }

    long long quotient = node.numerators / node.denominators;
    if (quotient != 0 && node.numerators % node.denominators == 0) {
        ans += to_string(quotient);
    }

    if (quotient != 0 && node.numerators % node.denominators != 0) {
        ans += to_string(quotient);
        ans += " ";
        ans += to_string(abs(node.numerators - quotient * node.denominators));
        ans += "/";
        ans += to_string(node.denominators);
    }

    if (quotient == 0) {
        ans += to_string(node.numerators);
        ans += "/";
        ans += to_string(node.denominators);
    }
    if (node.numerators < 0) {
        ans.insert(0, "(");
        ans.insert(ans.length(), ")");
    }

    return ans;
}

void Add(Node &one, Node &another) {
    long long numerator = one.numerators * another.denominators + another.numerators * one.denominators;
    Node node = Node(numerator, one.denominators * another.denominators);
    cout << ToString(one) << " + " << ToString(another) << " = " << ToString(node) << endl;
}

void Sub(Node &one, Node &another) {
    long long numerator = one.numerators * another.denominators - another.numerators * one.denominators;
    Node node = Node(numerator, one.denominators * another.denominators);
    cout << ToString(one) << " - " << ToString(another) << " = " << ToString(node) << endl;
}

void Mul(Node &one, Node &another) {
    long long numerator = one.numerators * another.numerators;
    Node node = Node(numerator, one.denominators * another.denominators);
    cout << ToString(one) << " * " << ToString(another) << " = " << ToString(node) << endl;
}

void Div(Node &one, Node &another) {
    if (another.numerators * another.denominators == 0) {
        cout << ToString(one) << " / " << ToString(another) << " = " << "Inf" << endl;
        return;
    }

    Node node = Node(one.numerators * another.denominators, one.denominators * another.numerators);
    cout << ToString(one) << " / " << ToString(another) << " = " << ToString(node) << endl;
}

int main() {
    long long numerators, denominators;
    scanf("%lld/%lld", &numerators, &denominators);
    Node one = Node(numerators, denominators);
    scanf("%lld/%lld", &numerators, &denominators);
    Node another = Node(numerators, denominators);
    Add(one, another);
    Sub(one, another);
    Mul(one, another);
    Div(one, another);

    return 0;
}

//同为负数要化简
//(-1/3) / (-2/3) = (-1/-2),(-1/3) / (-2/3) = 1/2