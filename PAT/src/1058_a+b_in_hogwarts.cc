#include <iostream>
#include <cstdio>

using namespace std;

struct Node {
    int galleon;
    int sickle;
    int knut;

    Node() {
        galleon = sickle = knut = -1;
    }

    Node(int _galleon, int _sickle, int _knut) {
        galleon = _galleon;
        sickle = _sickle;
        knut = _knut;
    }
};

Node Add(Node &one, Node &another) {
    Node ans = Node();

    ans.sickle = (one.knut + another.knut) / 29;
    ans.knut = (one.knut + another.knut) % 29;

    int carry = (ans.sickle + one.sickle + another.sickle) / 17;
    ans.sickle = (ans.sickle + one.sickle + another.sickle) % 17;
    ans.galleon = one.galleon + another.galleon + carry;

    return ans;
}

int main() {
    Node one, another;

    int galleon, sickle, knut;
    scanf("%d.%d.%d", &galleon, &sickle, &knut);
    one = Node(galleon, sickle, knut);
    scanf("%d.%d.%d", &galleon, &sickle, &knut);
    another = Node(galleon, sickle, knut);

    Node ans = Add(one, another);
    printf("%d.%d.%d", ans.galleon, ans.sickle, ans.knut);
}